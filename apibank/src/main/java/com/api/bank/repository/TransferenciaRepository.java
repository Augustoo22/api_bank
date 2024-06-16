package com.api.bank.repository;

import com.api.bank.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findAllByOrigemOrDestino(String origem, String destino);
}
