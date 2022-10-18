package br.com.fatecararas.favcontacts.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fatecararas.favcontacts.model.Contato;
import br.com.fatecararas.favcontacts.services.ContatoService;

@Controller
@RequestMapping("/")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping("/adicionar")
    public String formulariocadastro(Contato contato) {
        return "adicionar";
    }

    @GetMapping("/todos")
    public String listarContatos(Model model) {
        List<Contato> contatos = service.buscarTodos();
        model.addAttribute("contatos", contatos);
        return "index";
    }

    @PostMapping("/add")
    public String adicionarContato(@Valid Contato contato, BindingResult result) {
        if(result.hasErrors()) {
            return "adicionar";
        }
        service.salvar(contato);
        return "redirect:/todos";
    }


    @GetMapping("/delete/{id}")
    public String apagar(@PathVariable("id") String id) {
        service.apagar(Integer.valueOf(id));
        return "redirect/todos";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") String id, Model model) {
        Contato contato = service.buscarPorId(Integer.valueOf(id));
        model.addAttribute("contato", contato);
        return "adicionar";
    }

    @GetMapping("/valor/{id}")
    public String obterValoresPelaUrl(@PathVariable("id") String id) {
        System.out.println("ID: " + id);
        return "index";
    }

    @GetMapping("/soma")
    public String obterValoresPelaUrl2(@RequestParam("a") String a, @RequestParam("b") String b) {
        Integer x = Integer.parseInt(a);
        Integer y = Integer.parseInt(b);
        System.out.println(String.format("%d + %d é %d.", x, y, (x + y)));
        return "index";
    }
}
