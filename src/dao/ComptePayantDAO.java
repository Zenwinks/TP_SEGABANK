package dao;

import bo.Agence;
import bo.ComptePayant;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComptePayantDAO implements IDAO<Integer, ComptePayant> {

    private static final String INSERT = "INSERT INTO compte (solde, type, id_agence) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE compte SET solde = ? WHERE id = ?";
    private static final String UPDATE_SOLDE = "UPDATE compte SET solde = ? WHERE id = ?";
    private static final String REMOVE = "DELETE FROM compte WHERE id = ? AND type = 3";
    private static final String FIND = "SELECT * FROM compte WHERE id = ? AND type = 3";
    private static final String FIND_ALL = "SELECT * FROM compte WHERE type = 3";

    @Override
    public void create(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, comptePayant.getSolde());
                ps.setInt(2, 3);
                ps.setInt(3, comptePayant.getAgence().getId());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        comptePayant.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
                ps.setFloat(1, comptePayant.getSolde());
                ps.setInt(2, comptePayant.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void updateSolde(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_SOLDE)) {
                ps.setFloat(1, comptePayant.getSolde());
                ps.setInt(2, comptePayant.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE)) {
                ps.setInt(1, comptePayant.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Object findById(int id) throws SQLException, IOException, ClassNotFoundException {
        ComptePayant comptePayant = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        comptePayant = new ComptePayant();
                        comptePayant.setId(rs.getInt("id"));
                        comptePayant.setSolde(rs.getFloat("solde"));
                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        comptePayant.setAgence(agence);
                    }
                }
            }
        }
        return comptePayant;
    }

    @Override
    public List<ComptePayant> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<ComptePayant> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ComptePayant comptePayant = new ComptePayant();
                        comptePayant.setId(rs.getInt("id"));
                        comptePayant.setSolde(rs.getInt("solde"));
                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        comptePayant.setAgence(agence);
                        list.add(comptePayant);
                    }
                }
            }
        }
        return list;
    }
}
