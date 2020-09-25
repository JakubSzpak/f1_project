package com.f1.getinfo;

import com.f1.getinfo.database.QueryExecutor;

import java.io.IOException;

public class F1GeneralRunner {
    public static void main(String[] args) throws IOException {
        QueryExecutor.clearAllTables();
        LastRaceInfoRunner.main(null);
        ConstructorRankingRunner.main(null);

    }
}
