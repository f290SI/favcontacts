package br.com.fatecararas.favcontacts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fatecararas.favcontacts.model.Contato;

@Controller
@RequestMapping("/")
public class ContatoController {
    
    @GetMapping("/adicionar")
    public String formulariocadastro(Contato contato) {
        return "adicionar";
    }

    @GetMapping("/todos")
    public String listarContatos() {
        return "index";
    }
}
