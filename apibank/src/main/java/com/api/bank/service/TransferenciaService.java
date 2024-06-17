package com.api.bank.service;

import com.api.bank.model.Transferencia;
import com.api.bank.model.Usuario;
import com.api.bank.repository.TransferenciaRepository;
import com.api.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;




    @Transactional
    public String transferirPix(Transferencia transferencia) {
        // Verifica se o usuário pagador existe e tem saldo suficiente
        Usuario pagador = usuarioRepository.findById(Long.parseLong(transferencia.getOrigem()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário pagador não encontrado com ID: " + transferencia.getOrigem()));
        double saldoPagador = pagador.getSaldo();
        double valorTransferencia = transferencia.getValor();
        if (saldoPagador < valorTransferencia) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
        }

        // Busca o usuário beneficiário
        Usuario beneficiario = usuarioRepository.findById(Long.parseLong(transferencia.getDestino()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário beneficiário não encontrado com ID: " + transferencia.getDestino()));

        // Realiza a transferência
        pagador.debit(valorTransferencia);
        beneficiario.credit(valorTransferencia);

        // Salva a transferência no banco de dados
        transferRepository.save(transferencia);

        return "Transferência PIX realizada com sucesso: Valor: " + valorTransferencia +
                ", da conta de ID: " + pagador.getId() + " para a conta de ID: " + beneficiario.getId();
    }
}
