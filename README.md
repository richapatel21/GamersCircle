# GamersCircle
A Unique gaming community!

[![Click to watch the video](https://img.youtube.com/vi/9Sm0gyuPbRg/0.jpg)](https://youtu.be/9Sm0gyuPbRg)


## Description
An unique gaming community app designed exclusively for gamers who share a passion for both gaming and building meaningful connections. The app aims to bring together gamers with similar gaming interests, providing a platform where gamers can connect and make friends.

## logic
The matchUsers() method in the CommunityActivity class is responsible for filtering the list of users based on the selected range from the spinner and updating the RecyclerView with the matched users. Here's how the code works:

1. *Get Maximum Distance from Selected Range*: The method getMaxDistanceFromRange() extracts the maximum distance from the selected range string. For example, if the selected range is "Within 100 km", it extracts 100 as the maximum distance.

2. *Perform Matchmaking*: The method loops through all users loaded from the CSV file (allUsers list). For each user, it calculates the distance between the user's location (latitude and longitude) and a default location (center of India, specified by DEFAULT_LATITUDE and DEFAULT_LONGITUDE). It uses the calculateDistance() method, which implements the Haversine formula to calculate the distance between two sets of latitude and longitude coordinates.

3. *Filter Users*: If the calculated distance is less than or equal to the maximum distance obtained from the selected range, the user is considered within the selected range and added to the matchedUsers list.

4. *Update RecyclerView*: After all users are processed, the RecyclerView is updated with the matched users using the setPersonList() method of the PersonAdapter. The adapter is then notified of the data change with notifyDataSetChanged().

5. *Show/Hide No Data TextView*: Depending on whether there are matched users or not, the visibility of the "No data present" TextView is set accordingly. If matchedUsers is empty, the TextView is made visible, indicating no users are within the selected range. Otherwise, it is hidden.

6. *Logging*: The method also logs information about the matched users to the console for debugging purposes. It logs the user IDs of matched users and the total number of matched users (matchedUsers list size).

In summary, the matchUsers() method calculates the distance between each user's location and a default location, filters users based on the selected range, updates the RecyclerView with the matched users, and handles visibility of the "No data present" TextView.

## Technologies Used
- Front End: Andriod Native
- Backend: Firebase
- DB: Firebase
- VCS: Github


## Demo
APK Link: [https://drive.google.com/file/d/1cNVSuadi0UuTazzvmBHhSQEgtmjtpypC/view?usp=sharing](https://drive.google.com/file/d/1cNVSuadi0UuTazzvmBHhSQEgtmjtpypC/view?usp=sharing)

