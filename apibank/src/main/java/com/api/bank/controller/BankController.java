package com.api.bank.controller;

import com.api.bank.entity.BankEntityUser;
import com.api.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/")
    public String cadastro() {

        return "cadastro.html";
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestParam("nome") String nome,
                                         @RequestParam("idade") int idade,
                                         @RequestParam("email") String email,
                                         @RequestParam("senha") String senha,
                                         @RequestParam("saldo") float saldo) {

        BankEntityUser bank = new BankEntityUser();
        bank.setUsuario(nome, idade, email, senha, saldo);
        bankRepository.save(bank);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/lista")
    public ModelAndView listaCadastro() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<BankEntityUser> bankIt = bankRepository.findAll();
        modelAndView.addObject("bank", bankIt);
        modelAndView.setViewName("lista.html");
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<BankEntityUser> cadastroOpt = bankRepository.findById(id);
        if (cadastroOpt.isEmpty()) {
            return new ModelAndView("redirect:/lista");
        }
        BankEntityUser cadastro = cadastroOpt.get();
        ModelAndView modelAndView = new ModelAndView("editar.html");
        modelAndView.addObject("cadastro", cadastro);
        return modelAndView;
    }

    @PostMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestParam("id") Long id,
                                            @RequestParam("nome") String nome,
                                            @RequestParam("idade") int idade,
                                            @RequestParam("email") String email,
                                            @RequestParam("senha") String senha,
                                            @RequestParam("saldo") float saldo) {

        Optional<BankEntityUser> bankOpt = bankRepository.findById(id);

        if (bankOpt.isPresent()) {
            BankEntityUser bank = bankOpt.get();
            bank.setUsuario(nome, idade, email, senha, saldo);
            bankRepository.save(bank);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/lista");
            return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
        }

        return ResponseEntity.badRequest().body("Cadastro não encontrado.");
    }

    @GetMapping("/delete/{id}")
    public String deletar(@PathVariable("id") Long id) {
        bankRepository.deleteById(id);
        return "redirect:/lista";
    }

    @GetMapping("/pesquisa")
    public ModelAndView pesquisaForm() {
        return new ModelAndView("pesquisa.html");
    }

    @PostMapping("/pesquisar")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
        List<BankEntityUser> resultados = bankRepository.findByNomeContainingIgnoreCase(nomepesquisa);
        ModelAndView modelAndView = new ModelAndView("pesquisa.html");
        modelAndView.addObject("bankPesquisa", resultados);
        return modelAndView;
    }

    @GetMapping("/pagina_inicial/{id}")
    public ModelAndView paginaInicial(@PathVariable("id") Long id) {
        Optional<BankEntityUser> bankOpt = bankRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("pagina_inicial");
        if (bankOpt.isPresent()) {
            BankEntityUser bank = bankOpt.get();
            modelAndView.addObject("bank", bank);
        } else {
            modelAndView.addObject("message", "Usuário não encontrado");
        }
        return modelAndView;


    }

    @GetMapping("/pix/{id}")
    public ModelAndView pix(@PathVariable("id") Long id) {
        Optional<BankEntityUser> bankOpt = bankRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("pix");
        if (bankOpt.isPresent()) {
            BankEntityUser bank = bankOpt.get();
            modelAndView.addObject("bank", bank);
        } else {
            modelAndView.addObject("message", "Usuário não encontrado");
        }
        return modelAndView;
    }




}


