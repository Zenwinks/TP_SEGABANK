package dao;

import bo.Agence;
import bo.Compte;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {
    private static final String GET_COMPTES_BY_AGENCE_COMPTE_QUERY = "SELECT * FROM compte WHERE id_agence = ?";
//    private final String GET_BY_CODE_AGENCE_QUERY = "SELECT * FROM agence WHERE code = ?";
//    private final String GET_ALL_AGENCE_QUERY = "SELECT * FROM agence";
//    private final String INSERT_AGENCE_QUERY = "INSERT INTO agence(code, adresse) VALUES(?, ?)";
//    private final String UPDATE_AGENCE_QUERY = "UPDATE agence SET code = ? AND adresse = ? WHERE id = ?";
//    private final String DELETE_AGENCE_QUERY = "DELETE * FROM agence WHERE id = ?";

    public static List<Compte> getComptesByAgence(Agence agence) throws SQLException, IOException, ClassNotFoundException {
        List<Compte> comptes = new ArrayList<>();
        Compte compte = new Compte();
        Connection connection = PersistenceManager.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_COMPTES_BY_AGENCE_COMPTE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            //Préparation de la requête
            ps.setInt(1, agence.getId());

            //Envoie de la requête
            ps.executeUpdate();

            //Récupération du résultat
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    compte.setId(rs.getInt("id"));
                    compte.setSolde(rs.getFloat("solde"));
                    compte.setAgence(agence);
                    comptes.add(compte);
                }
            }
        }

        PersistenceManager.closeConnection();

        return comptes;
    }
}
