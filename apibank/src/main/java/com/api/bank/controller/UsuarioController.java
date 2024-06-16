package com.api.bank.controller;

import com.api.bank.model.Usuario;
import com.api.bank.service.UsuarioService;
import com.api.bank.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
        }
        return "paginaLogin";
    }

    @PostMapping("")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorEmail(email);
        System.out.println(usuarioOptional);
        if (usuarioOptional.isEmpty() || !passwordEncoder.matches(senha, usuarioOptional.get().getSenha())) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "redirect:/usuarios?error";
        }

        Usuario usuario = usuarioOptional.get();
        session.setAttribute("usuarioId", usuario.getId());

        UserDetails userDetails = usuarioService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails);

        return "redirect:/usuarios/pagina-inicial/" + usuario.getId() + "?token=" + token;
    }

    @GetMapping("/pagina-inicial/{id}")
    public String paginaInicial(@PathVariable("id") Long id, @RequestParam("token") String token, Model model) {
        Optional<Usuario> cadastro = usuarioService.buscarPorId(id);
        if (cadastro.isEmpty()) {
            return "redirect:/usuarios?error";
        }
        model.addAttribute("bank", cadastro.get());
        model.addAttribute("token", token);
        return "pagina_inicial";
    }

    @GetMapping("/cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastroUser";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isEmpty()) {
            return "redirect:/usuarios?error";
        }
        model.addAttribute("usuario", usuario.get());
        return "editar";
    }

    @PostMapping("/atualizar")
    public String atualizarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.salvar(usuario);
            return "redirect:/usuarios/pagina-inicial/" + usuario.getId();
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
            return "editar";
        }
    }

    @GetMapping("/registro/{id}")
    public String paginaRegistro(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> cadastro = usuarioService.buscarPorId(id);
        if (cadastro.isEmpty()) {
            return "redirect:/usuarios?error";
        }
        model.addAttribute("bank", cadastro.get());
        return "registro";
    }

    @GetMapping("/pix/{id}")
    public String paginaPix(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> cadastro = usuarioService.buscarPorId(id);
        if (cadastro.isEmpty()) {
            return "redirect:/usuarios?error";
        }
        model.addAttribute("bank", cadastro.get());
        return "pix";
    }
}
