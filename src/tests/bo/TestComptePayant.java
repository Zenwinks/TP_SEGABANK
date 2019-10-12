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

    }
}
