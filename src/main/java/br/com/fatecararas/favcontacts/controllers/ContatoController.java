package br.com.fatecararas.favcontacts.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listarContatos() {        
        return "index";
    }

    @PostMapping("/add")
    public String adicionarContato(@Valid Contato contato,
            BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("falha", "Falha na inclusão de Contato!");
            return "redirect:/adicionar";
        }
        
        service.salvar(contato);
        attributes.addFlashAttribute("sucesso", "Contato inserido com sucesso!");
        return "redirect:/adicionar";
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

    @PostMapping("/edit")
    public String editar(Contato contato,  RedirectAttributes attributes) {
        service.salvar(contato);
        attributes.addFlashAttribute("sucesso", "Registro atualizado com sucesso!");
        return "redirect:/adicionar";
    }

    @ModelAttribute("contatos")
    public List<Contato> getContatos() {
        return service.buscarTodos();
    }
}
