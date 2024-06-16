package com.api.bank.controller;

import com.api.bank.model.Usuario;
import com.api.bank.service.UsuarioService;
import com.api.bank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
// Define a classe como um controller e mapeia todas as URLs para iniciar com /usuarios
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("")
    public String login() {
        return "paginaLogin";
    }
    // Mapeia a requisição GET para a página de login

    @GetMapping("/cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastroUser";
    }
    // Mapeia a requisição GET para a página de cadastro e adiciona um objeto usuário vazio ao modelo

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }
    // Mapeia a requisição POST para salvar um usuário e redireciona para a página inicial

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestParam String email, @RequestParam String senha, Model model) {
        boolean loginValido = usuarioService.validarLogin(email, senha);
        if (!loginValido) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "paginaLogin";
        }

        UserDetails userDetails = usuarioService.loadUserByUsername(email);
        String jwt = jwtUtil.generateToken(userDetails);

        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null) {
            model.addAttribute("jwt", jwt);
            return "redirect:/usuarios/pagina-inicial/" + usuario.get().getEmail() + "?token=" + jwt;
        } else {
            return "paginaLogin";
        }
    }




    // Mapeia a requisição POST para autenticar um usuário e redireciona para a página inicial com um token JWT

    @GetMapping("/pagina-inicial/{id}")
    public String paginaInicial(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        model.addAttribute("bank", usuario);
        return "pagina_inicial";
    }
    // Mapeia a requisição GET para a página inicial do usuário com base no ID fornecido

    @GetMapping("/registro/{id}")
    public ModelAndView paginaRegistro(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        ModelAndView modelAndView = new ModelAndView("registro.html");
        modelAndView.addObject("bank", usuario);
        return modelAndView;
    }
    // Mapeia a requisição GET para a página de registro com base no ID fornecido

    @GetMapping("/pix/{id}")
    public ModelAndView paginaPix(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        ModelAndView modelAndView = new ModelAndView("pix.html");
        modelAndView.addObject("bank", usuario);
        return modelAndView;
    }
    // Mapeia a requisição GET para a página PIX com base no ID fornecido

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        model.addAttribute("usuario", usuario);
        return "editar.html";
    }
    // Mapeia a requisição GET para a página de edição de usuário com base no ID fornecido

    @PostMapping("/atualizar")
    public ResponseEntity<String> atualizarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.atualizar(usuario);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/usuarios/pagina-inicial/" + usuario.getId());
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (ResponseStatusException e) {
            model.addAttribute("erro", e.getReason());
            return new ResponseEntity<>("Erro ao atualizar usuário: " + e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
            return new ResponseEntity<>("Erro ao atualizar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Mapeia a requisição POST para atualizar um usuário e trata as exceções de forma adequada
}

