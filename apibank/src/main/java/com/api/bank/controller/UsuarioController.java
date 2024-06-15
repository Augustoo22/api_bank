    package com.api.bank.controller;
    
    import com.api.bank.model.Usuario;
    import com.api.bank.service.UsuarioService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.ModelAndView;

    @Controller
    @RequestMapping("/usuarios")
    public class UsuarioController {
    
        @Autowired
        private UsuarioService usuarioService;
    
        @GetMapping("/")
        public String login() {
            return "paginaLogin.html";
        }
    
        @GetMapping("/cadastro")
        public String cadastroForm(Model model) {
            model.addAttribute("usuario", new Usuario());
            return "cadastroUser.html";
        }
    
        @PostMapping("/salvar")
        public ResponseEntity<String> salvar(@ModelAttribute("usuario") Usuario usuario, Model model) {
            try {
                usuarioService.salvar(usuario);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/");
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            } catch (Exception e) {
                model.addAttribute("erro", "Erro ao cadastrar usuário: " + e.getMessage());
                return new ResponseEntity<>("Erro ao cadastrar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



        @GetMapping("/pagina-inicial/{id}")
        public String paginaInicial(@PathVariable("id") Long id, Model model) {
            Usuario cadastro = usuarioService.buscarPorId(id);
            model.addAttribute("bank", cadastro);
            return "pagina_inicial.html";
        }


        @GetMapping("/registro/{id}")
        public ModelAndView paginaRegistro(@PathVariable("id") Long id, Model model) {
            Usuario cadastro = usuarioService.buscarPorId(id);
            ModelAndView modelAndView = new ModelAndView("registro.html");
            modelAndView.addObject("bank", cadastro);
            return modelAndView;
        }

        @GetMapping("/pix/{id}")
        public ModelAndView paginaPix(@PathVariable("id") Long id, Model model) {
            Usuario cadastro = usuarioService.buscarPorId(id);
            ModelAndView modelAndView = new ModelAndView("pix.html");
            modelAndView.addObject("bank", cadastro);
            return modelAndView;
        }

        @GetMapping("/editar/{id}")
        public String editarUsuario(@PathVariable("id") Long id, Model model) {
            Usuario usuario = usuarioService.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            return "editar.html";
        }

        @PostMapping("/atualizar")
        public ResponseEntity<String> atualizarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
            try {
                usuarioService.salvar(usuario);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/usuarios/pagina-inicial/" + usuario.getId());
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            } catch (Exception e) {
                model.addAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
                return new ResponseEntity<>("Erro ao atualizar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    }
