package com.api.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.bank.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável por gerenciar as operações de persistência da entidade Usuario.
 * Estende JpaRepository para fornecer operações CRUD (Create, Read, Update, Delete)
 * e outras funcionalidades relacionadas à persistência no banco de dados.
 *
 * A entidade Usuario é mapeada para uma tabela no banco de dados e possui um identificador do tipo Long.
 * Este repositório contém um método personalizado para buscar um usuário com base no e-mail.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
/**
 * Retorna um Optional contendo o usuário que possui o e-mail especificado,
 * se existir no banco de dados.
 **/
    Optional<Usuario> findByEmail(String email);
}
