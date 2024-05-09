package org.example.eshopfinal.service;

import java.util.List;

import org.example.eshopfinal.entities.Couleur;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.example.eshopfinal.repository.CouleurRepository;

@Service
public class CouleurServiceImpl implements CouleurService {
    @Autowired
    CouleurRepository RepCouleur;
    @Override
    public Couleur ajoutercouleur(Couleur c) {
        RepCouleur.save(c);
        return c;
    }

    @Transactional
    @Override
    public void desactiver(String CodeCl) {
        Couleur couleurToUpdate = RepCouleur.findById(CodeCl).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"couleur existe pas dans la base de données")

        );

    }


    @Transactional
    @Override
    public void updatecouleur(String CodeCl, Couleur c) {
        Couleur couleurToUpdate = RepCouleur.findById(CodeCl).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"couleur existe pas dans la base de données")

        );
        if(c.getLibcl()!=null && !c.getLibcl().isEmpty()){
            couleurToUpdate.setLibcl(c.getLibcl());
        }

    }

    @Override
    public List<Couleur> getAllcouleur() {
        return (List<Couleur>) RepCouleur.findAll();
    }
}