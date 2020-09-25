package com.f1.getinfo.requests;

import com.f1.getinfo.classes.RaceResults;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GetLastRaceResults {

    public static RaceResults SendRequestAndMapToRaceResults(int place) {
        String response = GetLastRaceDetails.SendRequest();
        return mapResponseToRaceResults(response, place);
    }

    private static RaceResults mapResponseToRaceResults(String response, int place) {
        if (response== null){
            return new RaceResults(null,null,null,null,null,null);
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode mrDataNode = jsonNode.get("MRData");
            JsonNode raceTableNode = mrDataNode.get("RaceTable");
            JsonNode racesArrayNode = raceTableNode.get("Races");
            JsonNode racesArrayObject = racesArrayNode.get(0);
            JsonNode resultsNode = racesArrayObject.get("Results");
            JsonNode resultsObject = resultsNode.get(place);
            String position = resultsObject.get("position").asText();
            JsonNode driverNode = resultsObject.get("Driver");
            String name = driverNode.get("givenName").asText();
            String surname = driverNode.get("familyName").asText();
            JsonNode constructorNode = resultsObject.get("Constructor");
            String constructorName = constructorNode.get("name").asText();
            String status = resultsObject.get("status").asText();
            JsonNode timeNode = resultsObject.get("Time");
            String time = "0.0";
            if (timeNode!=null){
                time = timeNode.get("time").asText();
            }
            return new RaceResults(position, name, surname, constructorName, status, time);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            System.out.println(response);
            throw e;
        }
    }
}
