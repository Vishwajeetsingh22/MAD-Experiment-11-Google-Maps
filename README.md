# MAD Experiment 11 – Display Google Maps

## 📌 Experiment Details

**Experiment No.:** 11  
**Experiment Name:** Develop an Application to Display Google Maps  
**Subject:** Mobile Application Development (MAD)  
**Platform:** Android Studio  
**Programming Language:** Kotlin  
**UI Design:** XML  
**API:** Google Maps SDK for Android  

---

## 🎯 Aim

To develop an Android application using Kotlin that integrates Google Maps SDK to display interactive Google Maps with different map types, current device location, location markers, and zoom controls.

## 📖 Theory

Google Maps is a web-based mapping service provided by Google that allows users to explore geographical locations, roads, landmarks, and satellite imagery.

The Google Maps SDK for Android enables developers to integrate interactive maps into Android applications.

Using Google Maps SDK, an application can:

- Display geographical maps.
- Show the user's current GPS location.
- Add markers to specific locations.
- Change map types.
- Zoom in and zoom out.
- Display satellite imagery.
- Interact with geographical locations.

This experiment demonstrates the integration of Google Maps into an Android application using Kotlin.

## 🛠️ Technologies Used

- Android Studio
- Kotlin Programming Language
- XML Layout
- Google Maps SDK for Android
- Google Play Services Location API
- FusedLocationProviderClient
- Google Cloud Console
- Android SDK

## ⚙️ Software Requirements

1. Android Studio
2. Android SDK
3. Kotlin
4. Google Maps API Key
5. Android Smartphone or Emulator
6. Internet Connection
7. GPS / Location Services

## ✨ Features of the Application

### 1. Google Maps Integration
- Displays actual Google Maps inside the Android application.
- Provides an interactive map interface.
- Displays geographical roads, buildings, and landmarks.

### 2. Location Permission
- Requests location permission from the user.
- Supports precise and approximate location access.
- Allows the application to access the device location when permitted.

### 3. Multiple Map Types

The application supports four different Google Maps viewing modes:

| Map Type | Description |
|---|---|
| Normal | Displays standard roads, buildings, and geographical labels. |
| Satellite | Displays satellite imagery of geographical locations. |
| Terrain | Displays geographical terrain and elevation-related information. |
| Hybrid | Combines satellite imagery with road and location labels. |

### 4. Current Location
- Retrieves the device's actual geographical location.
- Displays a location marker on Google Maps.
- Shows the user's location information.
- Allows the map to center on the current GPS location.

### 5. Zoom Controls
- Zoom In button to enlarge the map.
- Zoom Out button to reduce the map scale.
- Supports interactive map navigation.

### 6. Location Search
- Provides a search field for entering geographical locations.
- Allows users to search for places.

### 7. City Shortcuts
Provides quick access to predefined locations such as:

- Bengaluru
- New Delhi
- Mumbai

## 🖥️ Application Screenshots

### 1. Location Permission

The application requests permission to access the device's location.

![Location Permission](screenshots/1.permission%20to%20%20acces%20location.png)

### 2. Normal Map View

Displays the standard Google Maps view with roads, buildings, landmarks, and the current location marker.

![Normal Map View](screenshots/2.Normal%20View.png)

### 3. Satellite Map View

Displays satellite imagery of the geographical location.

![Satellite Map View](screenshots/3.satelite%20view.png)

### 4. Terrain Map View

Displays geographical terrain information and natural land features.

![Terrain Map View](screenshots/4.Terrain%20view.png)

### 5. Hybrid Map View

Displays satellite imagery combined with road names, geographical labels, and landmarks.

![Hybrid Map View](screenshots/5.Hybrid%20view.png)

### 6. Zoom-Out Satellite View

Demonstrates the zoom-out functionality using satellite map mode to view a larger geographical area.

![Zoom Out Satellite View](screenshots/6.%20Zoomout%20Satelite%20view.png)

## 📂 Project Structure

```text
MAD-Experiment-11-Google-Maps/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/googlemapsapp_exp_11/
│           │       ├── MainActivity.kt
│           │       ├── LocationHelper.kt
│           │       └── PlacesHelper.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   ├── menu/
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
├── screenshots/
│   ├── 1.permission to acces location.png
│   ├── 2.Normal View.png
│   ├── 3.satelite view.png
│   ├── 4.Terrain view.png
│   ├── 5.Hybrid view.png
│   └── 6. Zoomout Satelite view.png
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── README.md
```

## 🔑 Google Maps API Configuration

To display Google Maps, a valid Google Maps API key is required.

Steps:

1. Open Google Cloud Console.
2. Create a Google Cloud project.
3. Enable Maps SDK for Android.
4. Configure billing as required.
5. Generate a Google Maps API key.
6. Configure the API key in the Android application.
7. Add the required location permissions.
8. Run the application on an Android device.

**Security Note:** Never upload your actual Google Maps API key to GitHub. Store it locally in `local.properties` and use Gradle manifest placeholders.

## ▶️ How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps.git
```

2. Open the project in Android Studio.

3. Configure your Google Maps API key locally.

4. Allow Gradle to synchronize dependencies.

5. Connect an Android smartphone or start an emulator.

6. Enable internet and location services.

7. Click the Run button in Android Studio.

8. Grant location permission when requested.

9. Explore different Google Maps viewing modes.

## 📊 Expected Output

After successfully running the application:

- Google Maps is displayed on the application screen.
- The user's current location is displayed using a map marker.
- Normal, Satellite, Terrain, and Hybrid views work.
- Zoom In and Zoom Out controls operate successfully.
- Users can view geographical locations.
- Location permission is requested at runtime.

## 📝 Result

Thus, an Android application was successfully developed using Kotlin and Google Maps SDK to display interactive Google Maps with multiple map types, current device location, location markers, and zoom functionality.

## 🚀 Future Enhancements

- Real-time location tracking.
- Nearby restaurants, hospitals, petrol pumps, and ATMs.
- Place search using Google Places API.
- Route planning and navigation.
- Traffic information.
- Distance and travel-time calculation.

## 👨‍💻 Author

**Vishwajeet Singh**  
MCA Student  
Jain (Deemed-to-be University)

## 🔗 GitHub Repository

[ MAD-Experiment-11-Google-Maps ](https://github.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps)

---

⭐ If you find this project useful, consider giving the repository a star!
