package dao;

import bo.Agence;
import bo.CompteSimple;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteSimpleDAO implements IDAO<Integer, CompteSimple> {

    private static final String INSERT = "INSERT INTO compte (solde, decouvertAutorise, type, id_agence) VALUES(?,?,?,?)";
    private static final String UPDATE = "UPDATE compte SET solde = ?, decouvertAutorise = ? WHERE id = ?";
    private static final String UPDATE_SOLDE = "UPDATE compte SET solde = ? WHERE id = ?";
    private static final String REMOVE = "DELETE FROM compte WHERE id = ? AND type = 1";
    private static final String FIND = "SELECT * FROM compte WHERE id = ? AND type = 1";
    private static final String FIND_ALL = "SELECT * FROM compte WHERE type = 1";

    @Override
    public void create(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, compteSimple.getSolde());
                ps.setFloat(2, compteSimple.getDecouvertAutorise());
                ps.setInt(3, 1);
                ps.setInt(4, compteSimple.getAgence().getId());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compteSimple.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
                ps.setFloat(1, compteSimple.getSolde());
                ps.setFloat(2, compteSimple.getDecouvertAutorise());
                ps.setInt(3, compteSimple.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE)) {
                ps.setInt(1, compteSimple.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Object findById(int id) throws SQLException, IOException, ClassNotFoundException {
        CompteSimple compteSimple = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteSimple = new CompteSimple();
                        compteSimple.setId(rs.getInt("id"));
                        compteSimple.setSolde(rs.getFloat("solde"));
                        compteSimple.setDecouvertAutorise(rs.getFloat("decouvertAutorise"));
                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteSimple.setAgence(agence);
                    }
                }
            }
        }
        return compteSimple;
    }

    @Override
    public List<CompteSimple> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<CompteSimple> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CompteSimple compteSimple = new CompteSimple();
                        compteSimple.setId(rs.getInt("id"));
                        compteSimple.setSolde(rs.getFloat("solde"));
                        compteSimple.setDecouvertAutorise(rs.getFloat("decouvertAutorise"));
                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteSimple.setAgence(agence);
                        list.add(compteSimple);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void updateSolde(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_SOLDE)) {
                ps.setFloat(1, compteSimple.getSolde());
                ps.setInt(2, compteSimple.getId());
                ps.executeUpdate();
            }
        }
    }
}
