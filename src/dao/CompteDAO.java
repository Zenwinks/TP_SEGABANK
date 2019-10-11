package dao;

import bo.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM compte WHERE id = ?";

    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Compte> list = new ArrayList<>();

        CompteEpargneDAO compteEpargneDAO = new CompteEpargneDAO();
        List<CompteEpargne> listCompteEpargneDAO = compteEpargneDAO.findAll();
        for (CompteEpargne compte : listCompteEpargneDAO) {
            list.add(compte);
        }

        ComptePayantDAO comptePayantDAO = new ComptePayantDAO();
        List<ComptePayant> listComptePayantDAO = comptePayantDAO.findAll();
        for (ComptePayant compte : listComptePayantDAO) {
            list.add(compte);
        }

        CompteSimpleDAO compteSimpleDAO = new CompteSimpleDAO();
        List<CompteSimple> listCompteSimpleDAO = compteSimpleDAO.findAll();
        for (CompteSimple compte : listCompteSimpleDAO) {
            list.add(compte);
        }

        return list;
    }

    public int getType(Integer id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int type = rs.getInt("type");

                        return type;
                    }
                }
            }
        }

        return -1;
    }

    public boolean exist(int id) throws SQLException, IOException, ClassNotFoundException  {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_QUERY)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {

                    //return true si existe
                    return rs.next();
                }
            }
        }

        return false;
    }
}
