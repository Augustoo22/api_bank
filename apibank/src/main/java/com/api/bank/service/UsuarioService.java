package com.api.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.bank.model.Usuario;
import com.api.bank.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

@Service
// @Service indica que é uma classe é um serviço do Spring onde ocorre a logica da aplicação
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    // Injeta automaticamente a dependência do UsuarioRepository

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    // Retorna uma lista de todos os usuarios do banco de dados chamando através do 'usuarioRepository'

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    // Salva ou atualiza o usuario do banco de dados através do 'usuarioRepository'

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    // Faz a busca por id do usuario se não encontrado retorna null

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    // Faz a busca por email do usuario se não encontrado retorna null

    public boolean validarLogin(String email, String senha) {
        Optional<Usuario> usuario = buscarPorEmail(email);
        return usuario.isPresent() && passwordEncoder.matches(senha, usuario.get().getSenha());
    }
    // Faz a busca por email e senha do usuario se não encontrado retorna null

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
    // Através do ID ele deleta o usuario


    // Implementação de UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = buscarPorEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado com email: " + email)
        );
        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
    //Implementa o método de interface UserDetailsService para carregar os detalhes do usuário pelo email, UserDetail
    // Retorna objeto do Spring Security que contém as informações do usuario.
}
