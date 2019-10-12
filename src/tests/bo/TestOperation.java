package tests.bo;

import bo.Agence;
import bo.ComptePayant;
import bo.Operation;

import java.io.IOException;
import java.sql.SQLException;

public class TestOperation {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Operation operation;

        System.out.printf("Test 1 - Création d'opération versement %n");
        Operation.TypeOperation typeOperation = Operation.TypeOperation.values()[0];
        operation = new Operation(1,typeOperation,1,1500);
        System.out.println(operation.toString());

        System.out.printf("Test 2 - Création d'opération retrait %n");
        Operation.TypeOperation typeOperation2 = Operation.TypeOperation.values()[1];
        operation = new Operation(2,typeOperation2,1,1000);
        System.out.println(operation.toString());

    }
}
