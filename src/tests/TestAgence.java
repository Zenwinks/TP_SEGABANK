package tests;

import bo.*;
import dao.AgenceDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestAgence {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        /**
         * Test BO
         */
        Agence agence1;
        List<Compte> comptes = new ArrayList<>();
        CompteEpargne compteEpargne = new CompteEpargne(1,4700f, AgenceDAO.getAgenceById(3), 1);
        CompteSimple compteSimple = new CompteSimple(2,1890f, AgenceDAO.getAgenceById(1), 200f);
        ComptePayant comptePayant = new ComptePayant(3,3250f, AgenceDAO.getAgenceById(2));

        comptes.add(compteEpargne);
        comptes.add(compteSimple);
        comptes.add(comptePayant);


        System.out.printf("Test - Création d'une agence%n");
        agence1 = new Agence(1, "001A", "3 rue des potiers", comptes);
        System.out.println(agence1.toString());

        /**
         * Test DAO
         */
        System.out.println("TEST - GetAgenceById (1)");
        System.out.println(AgenceDAO.getAgenceById(1));

    }
}
