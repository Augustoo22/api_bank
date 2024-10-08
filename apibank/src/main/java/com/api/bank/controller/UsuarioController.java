package com.api.bank.controller;

import com.api.bank.model.Transferencia;
import com.api.bank.model.Usuario;
import com.api.bank.model.dto.TransferenciaDTO;
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

import java.util.List;

/**
 * O `UsuarioController` é responsável por gerenciar as interações do usuário com a aplicação
 * através de páginas da web. Ele lida com operações relacionadas a usuários, login, transferências Pix,
 * visualização de registros e edição de informações do usuário.
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TransferenciaService transferenciaService;

    /**
     * Exibe a página de login.
     */
    @GetMapping("")
    public String login() {
        return "paginaLogin";
    }

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

    /**
     * Exibe o formulário de cadastro de usuário.
     */
    @GetMapping("/cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastroUser";
    }

    /**
     * Salva um novo usuário.
     */
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }

    /**
     * Exibe a página inicial do usuário autenticado.
     */
    @GetMapping("/pagina-inicial/{id}")
    public String paginaInicial(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        model.addAttribute("bank", usuario);
        return "pagina_inicial";
    }

    /**
     * Exibe a página de registros de transferências do usuário.
     */
    @GetMapping("/registro/{id}")
    public ModelAndView mostrarPaginaRegistro(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.getOneUser(id);
        List<TransferenciaDTO> transferencias = transferenciaService.getTransferenciasByUser(usuario.getId().toString());

        ModelAndView modelAndView = new ModelAndView("registro");
        modelAndView.addObject("bank", usuario);
        modelAndView.addObject("transferencias", transferencias);
        return modelAndView;
    }

    /**
     * Exibe a página de transferência Pix.
     */
    @GetMapping("/pix/{id}")
    public ModelAndView paginaPix(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        ModelAndView modelAndView = new ModelAndView("pix.html");
        modelAndView.addObject("bank", usuario);
        return modelAndView;
    }

     /**
     * Processa a transferência via Pix.
     */
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

    /**
     * Exibe o formulário de edição de usuário.
     */
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.getOneUser(id);
        model.addAttribute("usuario", usuario);
        return "editar";
    }

    /**
     * Atualiza as informações do usuário.
     */
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
}
