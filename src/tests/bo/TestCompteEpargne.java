package tests.bo;

import bo.*;

import java.io.IOException;
import java.sql.SQLException;

public class TestCompteEpargne {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CompteEpargne compteEpargne;

        System.out.printf("Test 1 - Création d'un compte épargne%n");
        compteEpargne = new CompteEpargne(1,1500.00f, new Agence(1, "CA114", "16 rue de Gaulle"), 5);
        System.out.println(compteEpargne.toString());

        System.out.printf("Test 2 - Calcul intérêt%n");
        System.out.println("Intérêt de " + compteEpargne.getTauxInteret() + "%");
        System.out.println("Pré-calcul intérêt : " + compteEpargne.getSolde() + "€");
        compteEpargne.calculInteret();
        System.out.println("Post-calcul intérêt : " + compteEpargne.getSolde() + "€");

    }
}
