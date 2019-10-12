package tests.dao;

import bo.Agence;
import bo.Operation;
import dao.IDAO;
import dao.OperationDAO;

import java.io.IOException;
import java.sql.SQLException;

public class TestOperationDAO {

    public static void main(String[] args) {

        IDAO<Long, Operation> dao = new OperationDAO();
        try {
            System.out.println(dao.findAll());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(dao.findById(1));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        Operation.TypeOperation typeOperation = Operation.TypeOperation.values()[0];
        Operation createOperation = new Operation(typeOperation, 7, 500);
        try {
            dao.create(createOperation);
            System.out.println("Créer : " + createOperation);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        Operation updateOperation = new Operation(typeOperation, 7, 8);
        try {
            dao.update(updateOperation);
            System.out.println("Modification : " + updateOperation);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        Operation removeOperation = new Operation(createOperation.getId(), createOperation.getTypeOperation(), createOperation.getCodeCompte(), createOperation.getMontant());
        try {
            dao.remove(removeOperation);
            System.out.println("Suppression : " + removeOperation);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
