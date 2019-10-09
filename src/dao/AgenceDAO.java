package dao;

import bo.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AgenceDAO {
    private static final String GET_BY_ID_AGENCE_QUERY = "SELECT * FROM agence WHERE id = ?";
    private static final String GET_BY_CODE_AGENCE_QUERY = "SELECT * FROM agence WHERE code = ?";
    private static final String GET_ALL_AGENCE_QUERY = "SELECT * FROM agence";
    private static final String INSERT_AGENCE_QUERY = "INSERT INTO agence(code, adresse) VALUES(?, ?)";
    private static final String UPDATE_AGENCE_QUERY = "UPDATE agence SET code = ? AND adresse = ? WHERE id = ?";
    private static final String DELETE_AGENCE_QUERY = "DELETE * FROM agence WHERE id = ?";

    public static Agence getAgenceById(int id) throws SQLException, IOException, ClassNotFoundException {
        Agence agence = new Agence();
        Connection connection = PersistenceManager.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID_AGENCE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            //Préparation de la requête
            ps.setInt(1, id);

            //Envoie de la requête
            ps.executeUpdate();

            //Récupération du résultat
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    agence.setId(id);
                    agence.setCode(rs.getString(1));
                    agence.setAdresse(rs.getString(2));
                    agence.setComptes(CompteDAO.getComptesByAgence(agence));
                }
            }
        }

        PersistenceManager.closeConnection();

        return agence;
    }

//    public Agence getAgenceByCode(String code) {
//
//    }
//
//    public Agence getAgenceByCompte(Compte compte) {
//
//    }
//
//    public List<Agence> getAll() {
//
//    }

    public void insert(Agence agence) {

    }

    public void update(Agence agence) {

    }

    public void delete(Agence agence) {

    }
}
