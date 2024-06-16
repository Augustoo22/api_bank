    package com.api.bank.controller;
    
    import com.api.bank.model.Usuario;
    import com.api.bank.service.UsuarioService;
    import com.api.bank.util.JwtUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.ModelAndView;

    import java.util.Optional;

    @Controller
    @RequestMapping("/usuarios")
    // Aqui está incicando que a classe é um controlador do Spring
    // Indica que todas URL's ira começar com /usuarios

    public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtil jwtUtil;
        //Os tres @Autowired são para o Spring injetar instâncias nessa classe controller

        @GetMapping("")
        public String login() {
            return "paginaLogin";
        }
        //Realiza o mapeamento de requisição GET, para acessar /usuarios
        // No 'return' ele está retornando a a pagina de login

        @GetMapping("/cadastro")
        public String cadastroForm(Model model) {
            model.addAttribute("usuario", new Usuario());
            return "cadastroUser";
        }
        //Mapeia outra requisicão GET para renderizar outra pagina só que dessa vez de cadastro
        // para acessar /usuarios/cadastro
        // Está retornando a tela de cadastro

        @PostMapping("/salvar")
        public String salvar(@ModelAttribute("usuario") Usuario usuario) {
            usuarioService.salvar(usuario);
            return "redirect:/usuarios";
        }
        //Mapeia a requesição POST para que seja possivel salvar os dados a requisição dele
        //e feita através do botão salvar
        //que está na pagina 'cadastroUser', ele salva o usuario que é recebido pelo formulario
        //através do serviço 'UsuarioService' e redireciona para pagina login

        @PostMapping("")
        public String createAuthenticationToken(@RequestParam String email, @RequestParam String senha, Model model) {
            boolean usuario = usuarioService.validarLogin(email, senha);
            if (usuario == false) {
                model.addAttribute("erro", "Usuário ou senha inválidos");
                return "paginaLogin";
            }

            UserDetails userDetails = usuarioService.loadUserByUsername(email);
            String jwt = jwtUtil.generateToken(userDetails);

            model.addAttribute("jwt", jwt);
            return "redirect:/usuarios/pagina-inicial/" + usuario.getId();
        }
        //Mapeia requisição POST, ele está buscando o usuário pelo email e senha usando o 'usuarioService'
        //caso não exista ele retorna uma mensagem de erro, mas se o usuario é encontrado carrega as informações
        //dele e gera p token

        @GetMapping("/pagina-inicial/{id}")
        public String paginaInicial(@PathVariable("id") Long id, Model model) {
            Optional<Usuario> cadastro = usuarioService.buscarPorId(id);
            model.addAttribute("bank", cadastro);
            return "pagina_inicial";
        }
        //Mapeia o GET para '/usuarios/pagina-inicial/{id} o id é do usuario para acessar a pagina da api apos ser logado
        //e tambem retorna a pagina inicial

        @GetMapping("/registro/{id}")
        public ModelAndView paginaRegistro(@PathVariable("id") Long id, Model model) {
            Optional<Usuario> cadastro = usuarioService.buscarPorId(id);
            ModelAndView modelAndView = new ModelAndView("registro.html");
            modelAndView.addObject("bank", cadastro);
            return modelAndView;
        }
        //Mapeia o GET para '/usuarios/registro/{id} o id é do usuario para conseguir fazer a navegação do usuario
        //Retorna a pagina 'registro'

        @GetMapping("/pix/{id}")
        public ModelAndView paginaPix(@PathVariable("id") Long id, Model model) {
            Optional<Usuario> cadastro = usuarioService.buscarPorId(id);
            ModelAndView modelAndView = new ModelAndView("pix.html");
            modelAndView.addObject("bank", cadastro);
            return modelAndView;
        }
        //Mapeia o GET para '/usuarios/pix/{id} o id é do usuario para conseguir fazer a navegação do usuario e nessa
        //pagina onde será possivel ele realizar a transferencia do saldo para outro usuario

        @GetMapping("/editar/{id}")
        public String editarUsuario(@PathVariable("id") Long id, Model model) {
            Optional<Usuario> usuario = usuarioService.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            return "editar.html";
        }
        // Aqui ele é direcionado para /usuarios/editar/{id} onde ele terá acesso as informações dele e podera editar
        // retorna a pagina 'editar.html'

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
        //Mapeia um POST que está no formulario de edição para que é acionado após o botão salvar for apertado
        //pegando as informações do formulario e se não tiver erros ele salva no usuarioService e redireciona para
        //pagina inicial mas caso de erro retorna uma mensagem de erro.

    }
