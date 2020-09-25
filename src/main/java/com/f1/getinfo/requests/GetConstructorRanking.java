package com.f1.getinfo.requests;

import com.f1.getinfo.classes.ConstructorRanking;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public class GetConstructorRanking {
    private static String SendRequest(){
        HttpGet request = new HttpGet("http://ergast.com/api/f1/current/constructorStandings.json");
        try{
            return GetLastRaceDetails.SendRequest(request);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static ConstructorRanking SendRequestAndMapToConstructorRanking(int constructorPlace) {
        String response = SendRequest();
        return mapResponseToConstructorRanking(response, constructorPlace);
    }


    private static ConstructorRanking mapResponseToConstructorRanking(String response, int constructorPlace){
        if(response == null){
            return new ConstructorRanking(null,null,null,null,null);
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode mrDataNode = jsonNode.get("MRData");
            JsonNode standingTableNode = mrDataNode.get("StandingsTable");
            JsonNode standingsListNode = standingTableNode.get("StandingsLists");
            JsonNode standingsListIndex = standingsListNode.get(0);
            JsonNode constructorStandingsTable = standingsListIndex.get("ConstructorStandings");
            JsonNode constructorStandingsIndex = constructorStandingsTable.get(constructorPlace);
            String position =constructorStandingsIndex.get("position").asText();
            String points =constructorStandingsIndex.get("points").asText();
            String wins =constructorStandingsIndex.get("wins").asText();
            JsonNode constructorDetailsNode = constructorStandingsIndex.get("Constructor");
            String constructorName = constructorDetailsNode.get("name").asText();
            String constructorNationality = constructorDetailsNode.get("nationality").asText();

            return new ConstructorRanking(position,constructorName,constructorNationality,points,wins);
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (Throwable e){
            System.out.println(response);
            throw e;
        }
    }
}
