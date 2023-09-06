package com.musso.megliodellavisa.model;
import com.musso.megliodellavisa.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteService {


    public static void creaUtente(String Nome, String Cognome, String Email, String Password) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "INSERT INTO Utente (Nome,Cognome, email, password, ruolo) VALUES (?,?,?,?,?)";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,Nome);
        query.setString(2,Cognome);
        query.setString(3,Email);
        query.setString(4,Password);
        query.setString(5,"utente");
        query.executeUpdate();
        query.close();
        connection.close();
    }

    public static Boolean checkEmail(String email) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "SELECT email FROM utente where email=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,email);
        ResultSet resultSet = query.executeQuery();
        Boolean result = null;
        if (resultSet.next()) {
            result = true;
        }
        query.close();
        resultSet.close();
        connection.close();
        return result;
    }

    public static String getRuolo(String email) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "SELECT ruolo FROM utente where email=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,email);
        ResultSet resultSet = query.executeQuery();
        String ruolo = null;
        if (resultSet.next()) {
            ruolo = resultSet.getString(1);
        }
        query.close();
        resultSet.close();
        connection.close();
        return ruolo;
    }

    public static void cambiaRuolo(String Ruolo, String email)throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "UPDATE utente set ruolo = ? WHERE email=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,Ruolo);
        query.setString(2,email);
        query.executeUpdate();
        query.close();
        connection.close();
    }

    public static Utente getUtente(String email,String password) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "SELECT * FROM utente where email=? AND password = ?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,email);
        query.setString(2,password);
        ResultSet resultSet = query.executeQuery();
        Utente utente = new Utente();
        if (resultSet.next()) {
            utente.setId(resultSet.getLong("id"));
            utente.setNome(resultSet.getString("nome"));
            utente.setCognome(resultSet.getString("cognome"));
            utente.setEmail(resultSet.getString("email"));
            utente.setRuolo(resultSet.getString("ruolo"));
                    }
        System.out.println(utente);

        query.close();
        resultSet.close();
        connection.close();
        return utente;
    }

}
