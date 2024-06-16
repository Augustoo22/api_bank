package com.api.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.bank.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
