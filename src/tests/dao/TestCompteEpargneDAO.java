package tests.dao;

import bo.Agence;
import bo.Compte;
import bo.CompteEpargne;
import dao.AgenceDAO;
import dao.CompteEpargneDAO;
import dao.IDAO;

import java.io.IOException;
import java.sql.SQLException;

public class TestCompteEpargneDAO {

    public static void main(String[] args){
        IDAO<Integer, CompteEpargne> dao = new CompteEpargneDAO();
        CompteEpargne createCompteEpargne = new CompteEpargne();
        Agence agence = new Agence(1,"CA114","2 rue de la modification");
        createCompteEpargne.setAgence(agence);
        createCompteEpargne.setTauxInteret(12);
        createCompteEpargne.setSolde(1000);
        try {
            dao.create(createCompteEpargne);
            System.out.println("Créer : " + createCompteEpargne);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        CompteEpargne updateCompteEpargne = new CompteEpargne();
        updateCompteEpargne.setId(11);
        updateCompteEpargne.setAgence(agence);
        updateCompteEpargne.setTauxInteret(10);
        updateCompteEpargne.setSolde(950);
        try {
            dao.update(updateCompteEpargne);
            System.out.println("Modification : " + updateCompteEpargne);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(dao.findAll());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(dao.findById(3));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
