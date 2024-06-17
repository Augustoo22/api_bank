package com.api.bank.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.api.bank.model.Usuario;
import com.api.bank.repository.UsuarioRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
// @Service indica que é uma classe é um serviço do Spring onde ocorre a lógica da aplicação
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    // Injeta automaticamente a dependência do UsuarioRepository

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }


    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    // Retorna uma lista de todos os usuários do banco de dados chamando através do 'usuarioRepository'

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    // Salva ou atualiza o usuário no banco de dados através do 'usuarioRepository'
    // A senha do usuário é codificada antes de ser salva

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    // Faz a busca por ID do usuário, se não encontrado retorna Optional.empty()

    public Usuario getOneUser(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id));
    }
    // Retorna um único usuário pelo ID, lança uma exceção se não encontrado

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    // Faz a busca por email do usuário, se não encontrado retorna Optional.empty()

    public Usuario atualizar(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + usuario.getId());
        }
        return usuarioRepository.save(usuario);
    }
    // Atualiza o usuário no banco de dados, lança uma exceção se o ID do usuário não for encontrado

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
    // Através do ID ele deleta o usuário, lança uma exceção se o ID não for encontrado

    // Implementação de UserDetailsService


}
