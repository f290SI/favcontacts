package br.com.fatecararas.favcontacts.services;

import java.util.List;
import java.util.Optional;

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

    public Contato buscarPorId(Integer id) {
        Optional<Contato> optContato = repository.findById(id);

        if (optContato.isEmpty()) {
            throw new RuntimeException(String
                    .format("Contato com ID: %s não localizado.", id));
        }

        return optContato.get();
    }

    public void apagar(Integer id) {
        Optional<Contato> contato = repository.findById(id);
        if (contato.isEmpty()) {
            throw new RuntimeException("Contato não localizado");
        }

        Contato c = contato.get();
        repository.delete(c);
    }
}
