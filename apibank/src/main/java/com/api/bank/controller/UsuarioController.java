package com.api.bank.controller;

import com.api.bank.model.Transferencia;
import com.api.bank.model.Usuario;
import com.api.bank.service.TransferenciaService;
import com.api.bank.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/usuarios")
// Define a classe como um controller e mapeia todas as URLs para iniciar com /usuarios
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("")
    public String login() {
        return "paginaLogin";
    }
    // Mapeia a requisição GET para a página de login

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("senha") String senha,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        try {
            Usuario usuario = usuarioService.findByEmail(email);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                redirectAttributes.addAttribute("id", usuario.getId());
                return "redirect:/usuarios/pagina-inicial/" + usuario.getId();
            } else {
                model.addAttribute("erro", "Usuário ou senha incorretos");
                return "paginaLogin";
            }
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao tentar realizar login: " + e.getMessage());
            return "paginaLogin";
        }
    }

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

    @PostMapping("/enviar-pix")
    public String enviarPix(@RequestParam("chavePix") String chavePix,
                            @RequestParam("valor") double valor,
                            @RequestParam("origem") Long origemId,
                            RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioService.findById(origemId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + origemId));

        Transferencia transferencia = new Transferencia();
        transferencia.setOrigem(usuario.getId().toString());
        transferencia.setDestino(chavePix);
        transferencia.setValor(valor);

        String resultadoTransferencia = transferenciaService.transferirPix(transferencia);

        if (resultadoTransferencia.startsWith("Erro")) {
            redirectAttributes.addFlashAttribute("error", resultadoTransferencia);
        } else {
            redirectAttributes.addFlashAttribute("success", resultadoTransferencia);
        }

        return "redirect:/usuarios/pagina-inicial/" + usuario.getId();
    }



    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        model.addAttribute("usuario", usuario);
        return "editar.html";
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizar(usuario);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/usuarios/pagina-inicial/" + usuarioAtualizado.getId());
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
            return new ResponseEntity<>("Erro ao atualizar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Mapeia a requisição POST para atualizar um usuário e trata as exceções de forma adequada
}
