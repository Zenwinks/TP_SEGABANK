package tests.bo;

import bo.*;

import java.io.IOException;
import java.sql.SQLException;

public class TestComptePayant {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        ComptePayant comptePayant;

        System.out.printf("Test 1 - Création d'un compte payant%n");
        comptePayant = new ComptePayant(1,1000.00f, new Agence(1, "CA114", "16 rue de Gaulle"));
        System.out.println(comptePayant.toString());

        System.out.printf("Test 2 - Versement de 300.00€%n");
        System.out.println("Pré-versement : " + comptePayant.getSolde() + "€");
        comptePayant.versement(300f);
        System.out.println("Post-versement : " + comptePayant.getSolde() + "€");

        System.out.printf("Test 3 - Retrait de 950€%n");
        System.out.println("Pré-retrait : " + comptePayant.getSolde() + "€");
        comptePayant.retrait(950.00f);
        System.out.println("Post-retrait : " + comptePayant.getSolde() + "€");

        System.out.printf("Test 4 - Retrait supérieur au solde%n");
        System.out.println("Pré-retrait : " + comptePayant.getSolde() + "€");
        comptePayant.retrait(4000f);
        System.out.println("Post-retrait : " + comptePayant.getSolde() + "€");
    }
}
