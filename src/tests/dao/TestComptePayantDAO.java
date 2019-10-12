package tests.dao;

import bo.Agence;
import bo.CompteEpargne;
import bo.ComptePayant;
import dao.CompteEpargneDAO;
import dao.ComptePayantDAO;
import dao.IDAO;

import java.io.IOException;
import java.sql.SQLException;

public class TestComptePayantDAO {

    public static void main(String[] args){
        IDAO<Integer, ComptePayant> dao = new ComptePayantDAO();
        ComptePayant createComptePayant = new ComptePayant();
        Agence agence = new Agence(1,"CA114","2 rue de la modification");
        createComptePayant.setAgence(agence);
        createComptePayant.setSolde(1000);
        try {
            dao.create(createComptePayant);
            System.out.println("Créer : " + createComptePayant);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        ComptePayant updateComptePayant = new ComptePayant();
        updateComptePayant.setId(7);
        updateComptePayant.setAgence(agence);
        updateComptePayant.setSolde(950);
        try {
            dao.update(updateComptePayant);
            System.out.println("Modification : " + updateComptePayant);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(dao.findAll());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(dao.findById(7));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
