package dao;

import bo.Operation;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO implements IDAO<Long, Operation> {
    private static final String INSERT_QUERY = "INSERT INTO operation (type, id_compte, montant) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE operation SET type = ?, id_compte = ?, montant = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM operation WHERE id = ?";
    private static final String FIND_QUERY = "SELECT * FROM operation WHERE id_compte = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM operation";

    @Override
    public void create(bo.Operation operation) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, operation.getTypeOperation().toString());
                ps.setInt(2, operation.getCodeCompte());
                ps.setInt(3, operation.getMontant());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        operation.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(bo.Operation operation) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setString(1, operation.getTypeOperation().toString());
                ps.setInt(2, operation.getCodeCompte());
                ps.setInt(3, operation.getMontant());
                ps.setInt(4, operation.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(bo.Operation operation) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, operation.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Object findById(int id) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    public List<bo.Operation> findByCompteId(int codeCompte) throws SQLException, IOException, ClassNotFoundException {
        bo.Operation operation = null;
        List<bo.Operation> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, codeCompte);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        operation = new bo.Operation();
                        operation.setId(rs.getInt("id"));
                        operation.setTypeOperation((bo.Operation.TypeOperation.valueOf(rs.getString("type"))));
                        operation.setCodeCompte(rs.getInt("id_compte"));
                        operation.setMontant(rs.getInt("montant"));
                        list.add(operation);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<bo.Operation> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<bo.Operation> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        bo.Operation operation = new bo.Operation();
                        operation.setId(rs.getInt("id"));
                        operation.setTypeOperation((bo.Operation.TypeOperation.valueOf(rs.getString("typeOperation"))));
                        operation.setCodeCompte(rs.getInt("codeCompte"));
                        operation.setMontant(rs.getInt("montant"));
                        list.add(operation);
                    }
                }
            }
        }
        return list;
    }
}
