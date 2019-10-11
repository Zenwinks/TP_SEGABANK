package tests.dao;

import bo.Agence;
import dao.AgenceDAO;
import dao.IDAO;

import java.io.IOException;
import java.sql.SQLException;

public class TestAgenceDAO {

    public static void main(String[] args) {

        IDAO<Long, Agence> dao = new AgenceDAO();
        try {
            System.out.println(dao.findAll());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(dao.findById(8));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        Agence createAgence = new Agence("CA584", "2 rue du Test");
        try {
            dao.create(createAgence);
            System.out.println("Créer : " + createAgence);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        Agence updateAgence = new Agence(1, "CA114", "2 rue de la modification");
        try {
            dao.update(updateAgence);
            System.out.println("Modification : " + updateAgence);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        Agence removeAgence = new Agence(createAgence.getId(), createAgence.getCode(), createAgence.getAdresse());
        try {
            dao.remove(removeAgence);
            System.out.println("Suppression : " + removeAgence);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
