package com.api.bank.controller;


import com.api.bank.entity.CertificationRule;
import com.api.bank.repository.CertificationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BankController {

    @Autowired
    private CertificationRuleRepository certificationRuleRepository;

    @GetMapping("/")
    public String cadastro() {

        return "cadastro.html";
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestParam("name") String name,
                                         @RequestParam("content") String content,
                                         @RequestParam("track") int track,
                                         @RequestParam("length") int length,
                                         @RequestParam("position") int position,
                                         @RequestParam("active") int active,
                                         @RequestParam("templateid") int templateid,
                                         @RequestParam("templateparams") String templateparams,
                                         @RequestParam("templatedesc") String templatedesc) {

        CertificationRule certificationRule = new CertificationRule();
        certificationRule.setCertification(name, content, track, length, position, active, templateid, templateparams, templatedesc);
        certificationRuleRepository.save(certificationRule);

        // Retornar o redirecionamento para "/Lista"
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }


    @GetMapping("/lista")
    public ModelAndView listaCadastro(){
        ModelAndView modelAndView = new ModelAndView();
        Iterable<CertificationRule> certificationRulesIt = certificationRuleRepository.findAll();
        modelAndView.addObject("certificationRules", certificationRulesIt);
        modelAndView.setViewName("lista.html");
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        CertificationRule cadastro = certificationRuleRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("editar.html");
        modelAndView.addObject("cadastro", cadastro);
        return modelAndView;
    }

    @GetMapping("/editarPesquisa/{id}")
    public ModelAndView editarPesquisa(@PathVariable("id") Long id) {
        CertificationRule cadastro = certificationRuleRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("editar.html");
        modelAndView.addObject("cadastro", cadastro);
        return modelAndView;
    }

    @PostMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestParam("id") Long id,
                                            @RequestParam("name") String name,
                                            @RequestParam("content") String content,
                                            @RequestParam("track") int track,
                                            @RequestParam("length") int length,
                                            @RequestParam("position") int position,
                                            @RequestParam("active") int active,
                                            @RequestParam("templateid") int templateid,
                                            @RequestParam("templateparams") String templateparams,
                                            @RequestParam("templatedesc") String templatedesc) {

        CertificationRule certificationRule = certificationRuleRepository.findById(id).orElse(null);

        if (certificationRule != null) {
            certificationRule.setCertification(name, content, track, length, position, active, templateid, templateparams, templatedesc);
            certificationRuleRepository.save(certificationRule);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/lista");
            return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
        }

        return ResponseEntity.badRequest().body("Cadastro não encontrado.");
    }

    @GetMapping("/delete/{id}")
    public String deletar(@PathVariable("id") Long id) {
        certificationRuleRepository.deleteById(id);
        return "redirect:/lista";

    }

    @GetMapping("/deletePesquisa/{id}")
    public String deletarPesquisa(@PathVariable("id") Long id) {
        certificationRuleRepository.deleteById(id);
        return "redirect:/pesquisa";

    }

    @GetMapping("/pesquisa")
    public ModelAndView pesquisaForm() {
        return new ModelAndView("pesquisa.html");
    }

    @PostMapping("/pesquisar")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
        List<CertificationRule> resultados = certificationRuleRepository.findByNameContainingIgnoreCase(nomepesquisa);
        ModelAndView modelAndView = new ModelAndView("pesquisa.html");
        modelAndView.addObject("resultados", resultados);
        return modelAndView;
    }

}
