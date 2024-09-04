package com.api.bank.repository;

import com.api.bank.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável por gerenciar as operações de persistência da entidade Transferencia.
 * Estende JpaRepository para fornecer operações CRUD (Create, Read, Update, Delete)
 * e outras funcionalidades relacionadas à persistência no banco de dados.
 *
 * A entidade Transferencia é mapeada para uma tabela no banco de dados e possui um identificador do tipo Long.
 * Este repositório contém métodos personalizados para buscar transferências com base em diferentes critérios.
 */
@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
/**
 * Retorna uma lista de todas as transferências que possuem a origem informada.
 **/
    List<Transferencia> findAllByOrigem(String origem);
/**
 * Retorna uma lista de todas as transferências que possuem o destino informado.
 **/
    List<Transferencia> findAllByDestino(String destino);
}