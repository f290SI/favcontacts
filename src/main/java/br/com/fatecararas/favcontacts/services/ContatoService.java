package br.com.fatecararas.favcontacts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecararas.favcontacts.model.Contato;
import br.com.fatecararas.favcontacts.model.repositories.ContatoRepository;

@Service
public class ContatoService {
    
    @Autowired
    private ContatoRepository repository;

    public Contato salvar(Contato contato) {
        return repository.save(contato);
    }

    public List<Contato> buscarTodos() {
        return repository.findAll();
    }
}
