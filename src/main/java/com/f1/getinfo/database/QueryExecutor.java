package com.f1.getinfo.database;

import com.f1.getinfo.classes.ConstructorRanking;
import com.f1.getinfo.classes.RaceDetails;
import com.f1.getinfo.classes.RaceResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecutor {
    public static void insertRaceResults(RaceResults raceResults) {
        //language=PostgresSQL
        String insertQuery = " insert into race_results" +
                "(position,name,surname,constructor_name,status,time)" +
                " values(?,?,?,?,?,?)";
        try {
            Connection connection = DbConnect.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, raceResults.getPosition());
            preparedStatement.setString(2, raceResults.getDriverName());
            preparedStatement.setString(3, raceResults.getDriverSurname());
            preparedStatement.setString(4, raceResults.getConstructorName());
            preparedStatement.setString(5, raceResults.getStatus());
            preparedStatement.setString(6, raceResults.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void insertRaceDetails(RaceDetails raceDetails) {
        //language=PostgresSQL
        String insertQuery = "insert into race_details" +
                "(season, round, race_name," +
                "circuit_name, circuit_city, circuit_country," +
                "race_date,race_time" +
                ") values (?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DbConnect.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, raceDetails.getSeason());
            preparedStatement.setString(2, raceDetails.getRound());
            preparedStatement.setString(3, raceDetails.getRaceName());
            preparedStatement.setString(4, raceDetails.getCircuitName());
            preparedStatement.setString(5, raceDetails.getCircuitCity());
            preparedStatement.setString(6, raceDetails.getCircuitCountry());
            preparedStatement.setString(7, raceDetails.getRaceDate());
            preparedStatement.setString(8, raceDetails.getRaceTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearAllTables(){
        //language=PostgresSQL
        String deleteQuery = "TRUNCATE race_details, race_results, constructor_ranking CASCADE";
        try{
            Connection connection = DbConnect.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void insertConstructorRanking(ConstructorRanking constructorRanking){
        //language=PostgresSQL
        String insertQuery ="insert into constructor_ranking(" +
                "position,constructor_name,nationality,points,wins) " +
                "values(?,?,?,?,?)";
        try{
            Connection connection = DbConnect.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, constructorRanking.getPosition());
            preparedStatement.setString(2, constructorRanking.getConstructorName());
            preparedStatement.setString(3, constructorRanking.getNationality());
            preparedStatement.setString(4, constructorRanking.getPoints());
            preparedStatement.setString(5, constructorRanking.getWins());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

