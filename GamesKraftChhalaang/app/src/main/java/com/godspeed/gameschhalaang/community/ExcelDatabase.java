package com.godspeed.gameschhalaang.community;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExcelDatabase {
    private Context context;

    public ExcelDatabase(Context context) {
        this.context = context;
    }

    public List<String[]> queryData(String searchTerm) {
        List<String[]> results = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("data.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);

            String[] record;
            while ((record = csvReader.readNext()) != null) {
                // Assuming data is in the format: ID,Name,Value
                // Adjust as per your actual data structure
                if (record.length >= 3 && record[1].equalsIgnoreCase(searchTerm)) {
                    results.add(record);
                }
            }

            csvReader.close();
        } catch (IOException e) {
            Log.e("ExcelDatabase", "Error reading CSV file", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}

