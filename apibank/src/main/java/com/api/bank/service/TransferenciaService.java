package com.api.bank.service;

import com.api.bank.model.Usuario;
import com.api.bank.model.Transferencia;
import com.api.bank.repository.UsuarioRepository;
import com.api.bank.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String transfer(Transferencia transferencia) {
        // Buscar o usuário pagador
        Long origemId = Long.parseLong(transferencia.getOrigem());
        var sender = usuarioRepository.findById(origemId);
        if (sender.isEmpty()) {
            return "Erro: Usuário pagador não encontrado com ID: " + transferencia.getOrigem();
        }

        // Buscar o usuário beneficiário
        Long destinoId = Long.parseLong(transferencia.getDestino());
        var receiver = usuarioRepository.findById(destinoId);
        if (receiver.isEmpty()) {
            return "Erro: Usuário beneficiário não encontrado com ID: " + transferencia.getDestino();
        }

        Usuario senderModel = sender.get();
        Usuario receiverModel = receiver.get();

        senderModel.debit(transferencia.getValor());
        receiverModel.credit(transferencia.getValor());

        transferRepository.save(transferencia);

        return "Transferência realizada com sucesso: Valor: " + transferencia.getValor() +
                ", ID do pagador: " + transferencia.getOrigem() +
                ", ID do beneficiário: " + transferencia.getDestino();
    }

    @Transactional(readOnly = true)
    public List<Transferencia> getTransfersByUserId(String userId) {
        return transferRepository.findAllByOrigemOrDestino(userId, userId);
    }
}
