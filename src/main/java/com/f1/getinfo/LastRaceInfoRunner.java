package com.f1.getinfo;

import com.f1.getinfo.classes.RaceDetails;
import com.f1.getinfo.classes.RaceResults;
import com.f1.getinfo.database.QueryExecutor;
import com.f1.getinfo.requests.GetLastRaceDetails;
import com.f1.getinfo.requests.GetLastRaceResults;

import java.io.IOException;

public class LastRaceInfoRunner {
    public static void main(String[] args) throws IOException {
        RaceDetails raceDetails = GetLastRaceDetails.SendRequestAndMapToRaceDetails();
        System.out.println(raceDetails);
        QueryExecutor.insertRaceDetails(raceDetails);

        int place = 0;
        while (place<20){
            RaceResults raceResults = GetLastRaceResults.SendRequestAndMapToRaceResults(place);
            System.out.println(raceResults);
            QueryExecutor.insertRaceResults(raceResults);
            place++;
        }
    }
}
