package com.musso.megliodellavisa.model;


import com.musso.megliodellavisa.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransazioniService {
    public static void nuovaTransazione(String utente, String operazione, String numerocarta) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "INSERT INTO transazione (utente, operazione, timestamp,numerocarta) VALUES (?,?,?,?)";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,utente);
        query.setString(2,operazione);
        query.setString(3,String.valueOf(System.currentTimeMillis()));
        query.setString(4,numerocarta);
        query.executeUpdate();
        query.close();
        connection.close();
    }
    public static List<Transazioni> getTransazione(String Utente) throws Exception {
        DatabaseConnector dbc = new DatabaseConnector();
        List<Transazioni> transazioni = new ArrayList<>();
        Connection connection = dbc.getConnection();
        String queryString = "SELECT * FROM transazione WHERE utente=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,Utente);
        ResultSet resultSet = query.executeQuery();
        while(resultSet.next()) {
            Transazioni t = new Transazioni();
            t.setId(resultSet.getLong("id"));
            t.setTimestamp(resultSet.getLong("timestamp"));
            t.setUtente(resultSet.getString("utente"));
            t.setOperazione(resultSet.getString("operazione"));
            t.setNumeroCarta(resultSet.getString("numerocarta"));
            transazioni.add(t);
        }

        query.close();
        resultSet.close();
        connection.close();

        return transazioni;

    }
}
