package org.example.eshopfinal.repository;

import org.example.eshopfinal.entities.Commande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandeRepository extends CrudRepository <Commande, Long> {

}