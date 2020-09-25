package com.f1.getinfo;

import com.f1.getinfo.classes.ConstructorRanking;
import com.f1.getinfo.database.QueryExecutor;
import com.f1.getinfo.requests.GetConstructorRanking;

import java.io.IOException;

public class ConstructorRankingRunner {
    public static void main(String[] args) throws IOException {
        int constructorPlace = 0;
        while(constructorPlace<10){
            ConstructorRanking constructorRanking = GetConstructorRanking.SendRequestAndMapToConstructorRanking(constructorPlace);
            System.out.println(constructorRanking);
            QueryExecutor.insertConstructorRanking(constructorRanking);
            constructorPlace++;
        }

    }
}
