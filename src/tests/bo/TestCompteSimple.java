package tests.bo;

import bo.*;

import java.io.IOException;
import java.sql.SQLException;

public class TestCompteSimple {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        CompteSimple compteSimple;

        System.out.printf("Test 1 - Création d'un compte simple%n");
        compteSimple = new CompteSimple(1,1400f, new Agence(1, "CA114", "16 rue de Gaulle"), 200f);
        System.out.println(compteSimple.toString());

    }
}
