package com.musso.megliodellavisa.model;

import com.musso.megliodellavisa.utils.DatabaseConnector;

import java.sql.*;

public class CartaService {
    public static Float creditoResiduo(String numeroCarta) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        Float credito = null;
        String queryString = "SELECT creditoResiduo FROM carta WHERE numeroCarta=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,numeroCarta);
        ResultSet resultSet = query.executeQuery();
        if(resultSet.next()){
            credito=resultSet.getFloat(1);
        }

        query.close();
        resultSet.close();
        connection.close();
        return credito;
    }
    public static Integer getUltimoNumeroCarta() throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "SELECT numeroCarta FROM carta ORDER BY id DESC LIMIT 1";
        PreparedStatement query= connection.prepareStatement(queryString);
        ResultSet resultSet = query.executeQuery();
        String numCarta = null;
        if(resultSet.next()){
            numCarta=resultSet.getString(1);
        }
        query.close();
        resultSet.close();
        connection.close();
        return Integer.valueOf(numCarta);

    }
    public static void creaCarta(Float credito) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "INSERT INTO carta (numeroCarta, attiva, creditoResiduo) VALUES (?,?,?)";
        PreparedStatement query= connection.prepareStatement(queryString);

        String numeroCarta = String.valueOf(getUltimoNumeroCarta()+1);

        query.setString(1,numeroCarta);
        query.setString(2,"1");
        query.setString(3,String.valueOf(credito));
        query.executeUpdate();
        query.close();
        connection.close();
    }

    public static Boolean checkStatus(String numeroCarta) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "SELECT attiva FROM carta WHERE numeroCarta=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,numeroCarta);
        ResultSet resultSet = query.executeQuery();
        Boolean attiva = null;
        if(resultSet.next()){
            attiva = resultSet.getBoolean(1);
        }
        query.close();
        resultSet.close();
        connection.close();
        return attiva;
    }

    public static void cambiaStato(String numeroCarta) throws SQLException{
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "UPDATE carta set attiva = ? WHERE numeroCarta=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(2,numeroCarta);
        Boolean status = checkStatus(numeroCarta);
        if (status!=null){
            if (status){
                query.setString(1,"0");
            }
            else{
                query.setString(1,"1");
            }
            query.executeUpdate();
            query.close();
            connection.close();
        }
    }

    public static void editCredito(String numeroCarta, String editCredito) throws SQLException{
        Float credito = creditoResiduo(numeroCarta);
        Float nuovoCredito = credito + Float.valueOf(editCredito);
        System.out.println(credito);
        System.out.println(nuovoCredito);
        DatabaseConnector dbc = new DatabaseConnector();
        Connection connection = dbc.getConnection();
        String queryString = "UPDATE carta set creditoResiduo = ? WHERE numeroCarta=?";
        PreparedStatement query= connection.prepareStatement(queryString);
        query.setString(1,String.valueOf(nuovoCredito));
        query.setString(2,numeroCarta);
        query.executeUpdate();
        query.close();
        connection.close();
    }
    


}
