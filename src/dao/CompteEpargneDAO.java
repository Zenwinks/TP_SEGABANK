package dao;

import bo.Agence;
import bo.CompteEpargne;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CompteEpargneDAO implements IDAO<Integer, CompteEpargne> {

    private static final String INSERT = "INSERT INTO compte (solde, taux_interet, type, id_agence) VALUES(?,?,?,?)";
    private static final String UPDATE= "UPDATE compte SET solde = ?, taux_interet = ? WHERE id = ?";
    private static final String REMOVE = "DELETE FROM compte WHERE id = ? AND type = 2";
    private static final String FIND = "SELECT * FROM compte WHERE id = ? AND type = 2";
    private static final String FIND_ALL = "SELECT * FROM compte WHERE type = 2";

    @Override
    public void create(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
                ps.setFloat(1, compteEpargne.getSolde());
                ps.setFloat(2, compteEpargne.getTauxInteret());
                ps.setFloat(3, 2);
                ps.setInt(4, compteEpargne.getAgence().getId());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compteEpargne.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
                ps.setFloat(1, compteEpargne.getSolde());
                ps.setFloat(2, compteEpargne.getTauxInteret());
                ps.setInt(3, compteEpargne.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE)) {
                ps.setInt(1, compteEpargne.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public CompteEpargne findById(int id) throws SQLException, IOException, ClassNotFoundException {
        CompteEpargne compteEpargne = null;
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getFloat("solde"));
                        compteEpargne.setTauxInteret(rs.getFloat("taux_interet"));
                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteEpargne.setAgence(agence);
                    }
                }
            }
        }
        return compteEpargne;
    }

    @Override
    public List<CompteEpargne> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<CompteEpargne> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CompteEpargne compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getInt("solde"));
                        compteEpargne.setTauxInteret(rs.getInt("taux_interet"));

                        AgenceDAO agenceDAO = new AgenceDAO();
                        Agence agence = agenceDAO.findById(rs.getInt("id_agence"));
                        compteEpargne.setAgence(agence);

                        list.add(compteEpargne);
                    }
                }
            }
        }
        return list;
    }
}
