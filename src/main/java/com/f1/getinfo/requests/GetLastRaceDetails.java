package com.f1.getinfo.requests;

import com.f1.getinfo.classes.RaceDetails;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetLastRaceDetails {

    public static String SendRequest(){
        HttpGet request = new HttpGet("http://ergast.com/api/f1/current/last/results.json");

        try{
            return SendRequest(request);
        }catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String SendRequest(HttpGet request) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();

            if(entity != null && response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }

    public static RaceDetails SendRequestAndMapToRaceDetails() throws IOException {
        String response = SendRequest();
        return mapResponseToRaceDetails(response);
    }

    private static RaceDetails mapResponseToRaceDetails(String response){
        if(response == null){
            return new RaceDetails(null,null,null, null, null,null,null,null);
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode mrDataNode = jsonNode.get("MRData");
            JsonNode raceTableNode = mrDataNode.get("RaceTable");
            String season = raceTableNode.get("season").asText();
            String round = raceTableNode.get("round").asText();
            JsonNode racesArrayNode = raceTableNode.get("Races");
            JsonNode racesArrayObject = racesArrayNode.get(0);
            String raceName = racesArrayObject.get("raceName").asText();
            String raceDate = racesArrayObject.get("date").asText();
            String raceTime = racesArrayObject.get("time").asText();
            JsonNode circuitNode = racesArrayObject.get("Circuit");
            String circuitName = circuitNode.get("circuitName").asText();
            JsonNode locationNode = circuitNode.get("Location");
            String circuitCity = locationNode.get("locality").asText();
            String circuitCountry = locationNode.get("country").asText();


            return new RaceDetails(season, round, raceName,circuitName,circuitCity,circuitCountry, raceDate,raceTime);
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (Throwable e){
            System.out.println(response);
            throw e;
        }


    }


}
