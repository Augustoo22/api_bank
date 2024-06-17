package com.api.bank.service;

import com.api.bank.model.Transferencia;
import com.api.bank.model.Usuario;
import com.api.bank.model.dto.TransferenciaDTO;
import com.api.bank.repository.TransferenciaRepository;
import com.api.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String transferirPix(Transferencia transferencia) {
        Usuario pagador = usuarioRepository.findById(Long.parseLong(transferencia.getOrigem()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário pagador não encontrado com ID: " + transferencia.getOrigem()));
        double saldoPagador = pagador.getSaldo();
        double valorTransferencia = transferencia.getValor();
        if (saldoPagador < valorTransferencia) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
        }

        Usuario beneficiario = usuarioRepository.findById(Long.parseLong(transferencia.getDestino()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário beneficiário não encontrado com ID: " + transferencia.getDestino()));

        pagador.debit(valorTransferencia);
        beneficiario.credit(valorTransferencia);

        transferencia.setDataHora(LocalDateTime.now());
        transferenciaRepository.save(transferencia);

        return "Transferência PIX realizada com sucesso: Valor: " + valorTransferencia +
                ", da conta de ID: " + pagador.getId() + " para a conta de ID: " + beneficiario.getId();
    }

    @Transactional
    public List<TransferenciaDTO> getTransferenciasByUser(String userId) {
        List<Transferencia> transferenciasOrigem = transferenciaRepository.findAllByOrigem(userId);
        List<Transferencia> transferenciasDestino = transferenciaRepository.findAllByDestino(userId);
        transferenciasOrigem.addAll(transferenciasDestino);
        return transferenciasOrigem.stream().map(TransferenciaDTO::new).collect(Collectors.toList());
    }
}
