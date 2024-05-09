package org.example.eshopfinal.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String nom;
    private String prenom;
    private String email;
    private  Integer numtel;
    private Integer codepostal;
    private Date datenais=new Date();
    private Integer cin;
    private boolean flag;
    private String addresse;

    @OneToMany
    private List <Commande> c;

    @OneToOne
    private UserInfo user;


}