package com.godspeed.gameschhalaang.community;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.godspeed.gameschhalaang.community.adapter.PersonAdapter;
import com.godspeed.gameschhalaang.community.model.Person;
import com.godspeed.gameskraftchhalaang.R;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {

    private String TAG = CommunityActivity.class.getSimpleName();

    private static final double DEFAULT_LATITUDE = 20.53;
    private static final double DEFAULT_LONGITUDE = 78.96;

//    Latitude: 20.5937 (Approximate latitude of India's center)
//    Longitude: 78.9629 (Approximate longitude of India's center)
    private Spinner locationRangeSpinner;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private List<Person> allUsers;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.community_dashboard);

        locationRangeSpinner = findViewById(R.id.locationRangeSpinner);
        recyclerView = findViewById(R.id.user_in_loc_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allUsers = loadUserData();

        List<Person> personList = generateDummyData(); // You need to implement this method to generate your data
        personAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(personAdapter);

        // Set up the Spinner
        setUpSpinner();

        // Set up the listener for the locationRangeSpinner
        locationRangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected range from the spinner
                String selectedRange = (String) adapterView.getItemAtPosition(position);
                // Call matchUsers function with the selected range
                matchUsers(selectedRange);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case where nothing is selected (optional)
            }
        });
    }

    private void setUpSpinner() {

        // Create a list of items for the Spinner
        List<String> locationRangeList = new ArrayList<>();
        locationRangeList.add("Within 100 km");
        locationRangeList.add("Within 200 km");
        locationRangeList.add("Within 300 km");
        locationRangeList.add("Within 400 km");
        locationRangeList.add("Within 500 km");
        locationRangeList.add("Within 600 km");
        locationRangeList.add("Within 700 km");
        locationRangeList.add("Within 800 km");
        locationRangeList.add("Within 900 km");
        locationRangeList.add("Within 1000 km");

        // Add more items as needed
        // Create an ArrayAdapter using the list and a default Spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationRangeList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the Spinner
        locationRangeSpinner.setAdapter(adapter);
    }


    // Generate dummy data for RecyclerView
    private List<Person> generateDummyData() {
        List<Person> personList = new ArrayList<>();
//        personList.add(new Person("Person 1"));
//        personList.add(new Person("Person 2"));

        // Add more persons as needed
        return personList;
    }

    private List<Person> loadUserData() {
        List<Person> userList = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.user_preference);
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            String[] nextRecord;

            //skip header line
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                int userId = Integer.parseInt(nextRecord[0]);
                int maxDistance = nextRecord[1].isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(nextRecord[1]);
                double latitude = Double.parseDouble(nextRecord[2]);
                double longitude = Double.parseDouble(nextRecord[3]);
                userList.add(new Person(userId, maxDistance, latitude, longitude));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void matchUsers(String selectedRange) {
        // Get the maximum distance from the selected range
        int maxDistance = getMaxDistanceFromRange(selectedRange);

        // Perform matchmaking
        List<Person> matchedUsers = new ArrayList<>();
        for (Person user : allUsers) {
            // Calculate distance between user and default location (or current location if available)
            double distance = calculateDistance(user.getLatitude(), user.getLongitude(), DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
            // Check if the user is within the max distance range
            if (distance <= maxDistance) {
                matchedUsers.add(user); // Add the user to the list of matched users
            }
        }

        for (int i = 0; i<matchedUsers.size(); i++){
            matchedUsers.get(i);
            Log.i(TAG,"matchedUsers : i: "+matchedUsers.get(i).getUserId() + " ");
        }

        Log.i(TAG, "matchedUsers list size: "+matchedUsers.size());
        // Update the RecyclerView with matched users
        personAdapter.setPersonList(matchedUsers);
        personAdapter.notifyDataSetChanged();
    }


    private int getMaxDistanceFromRange(String range) {
        // Implement logic to extract the maximum distance from the selected range
        // For example, if range is "Within 100 km", return 100
        // Implement similar logic for other ranges

        String[] parts = range.split(" ");
        int maxDistance = Integer.parseInt(parts[1]);
        return maxDistance;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Implement Haversine formula to calculate distance between two sets of latitude and longitude coordinates
        // Return the distance in kilometers

        final int R = 6371;

        // Convert latitude and longitude from degrees to radians
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        // Calculate the Haversine formula
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }
}
