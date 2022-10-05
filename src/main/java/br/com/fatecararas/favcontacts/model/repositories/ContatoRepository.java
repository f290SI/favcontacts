package br.com.fatecararas.favcontacts.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecararas.favcontacts.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer>{
    
}
