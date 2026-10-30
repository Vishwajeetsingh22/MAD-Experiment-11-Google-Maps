# MAD Experiment 11 – Display Google Maps

## 📌 Experiment Details

| Detail | Information |
|---|---|
| **Experiment No.** | 11 |
| **Experiment Name** | Develop an Application to Display Google Maps |
| **Subject** | Mobile Application Development (MAD) |
| **Platform** | Android Studio |
| **Programming Language** | Kotlin |
| **UI Design** | XML |
| **API** | Google Maps SDK for Android |

## 🎯 Aim

To develop an Android application using Kotlin that integrates Google Maps SDK to display interactive Google Maps with different map types, current device location, location markers, and zoom controls.

## 📖 Theory

Google Maps is a mapping service that allows users to explore geographical locations, roads, landmarks, and satellite imagery. The Google Maps SDK for Android enables developers to integrate interactive maps into Android applications.

Using Google Maps SDK, an application can display geographical maps, show the device's current location, add markers, change map types, zoom in and out, and display satellite imagery.

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

| Map Type | Description |
|---|---|
| **Normal** | Displays standard roads, buildings, and geographical labels. |
| **Satellite** | Displays satellite imagery of geographical locations. |
| **Terrain** | Displays geographical terrain and elevation-related information. |
| **Hybrid** | Combines satellite imagery with road and location labels. |

### 4. Current Location
- Retrieves the device's geographical location when permission is granted.
- Displays a location marker on Google Maps.
- Allows the map to center on the current GPS location.

### 5. Zoom Controls
- Zoom In button to enlarge the map.
- Zoom Out button to reduce the map scale.
- Supports interactive map navigation.

### 6. Location Search and City Shortcuts
- Provides a search field for entering geographical locations.
- Includes predefined city shortcuts such as Bengaluru, New Delhi, and Mumbai.

---

## 🖥️ Application Screenshots

The images below are loaded directly from the screenshot files already stored in the root of this GitHub repository. No separate `screenshots` folder is required.

<table>
  <tr>
    <td align="center" width="50%">
      <strong>1. Location Permission</strong><br>
      <img src= "1.permission to  acces location.png" alt="1. Location Permission" width="260"><br>
      <sub>Permission request to access the device location.</sub>
    </td>
    <td align="center" width="50%">
      <strong>2. Normal Map View</strong><br>
      <img src="https://raw.githubusercontent.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps/main/2.Normal%20View.png" alt="2. Normal Map View" width="260"><br>
      <sub>Standard map with roads, buildings, landmarks, and current location marker.</sub>
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <strong>3. Satellite Map View</strong><br>
      <img src="https://raw.githubusercontent.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps/main/3.satelite%20view.png" alt="3. Satellite Map View" width="260"><br>
      <sub>Satellite imagery of the geographical location.</sub>
    </td>
    <td align="center" width="50%">
      <strong>4. Terrain Map View</strong><br>
      <img src="https://raw.githubusercontent.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps/main/4.Terrain%20view.png" alt="4. Terrain Map View" width="260"><br>
      <sub>Terrain map showing geographical features.</sub>
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <strong>5. Hybrid Map View</strong><br>
      <img src="https://raw.githubusercontent.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps/main/5.Hybrid%20view.png" alt="5. Hybrid Map View" width="260"><br>
      <sub>Satellite imagery with road names and labels.</sub>
    </td>
    <td align="center" width="50%">
      <strong>6. Zoom-Out Satellite View</strong><br>
      <img src="https://raw.githubusercontent.com/Vishwajeetsingh22/MAD-Experiment-11-Google-Maps/main/6.%20Zoomout%20Satelite%20view.png" alt="6. Zoom-Out Satellite View" width="260"><br>
      <sub>Zoomed-out satellite map showing a wider geographical area.</sub>
    </td>
  </tr>
</table>

---

## 📂 Project Structure

```text
MAD-Experiment-11-Google-Maps/
│
├── .idea/
├── app/
├── gradle/
├── 1.permission to acces location.png
├── 2.Normal View.png
├── 3.satelite view.png
├── 4.Terrain view.png
├── 5.Hybrid view.png
├── 6. Zoomout Satelite view.png
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

## 🔑 Google Maps API Configuration

A valid Google Maps API key is required to display Google Maps.

1. Open the [Google Cloud Console](https://console.cloud.google.com/).
2. Create or select a Google Cloud project.
3. Enable **Maps SDK for Android**.
4. Configure billing as required by Google Maps Platform.
5. Create an API key.
6. Configure the key locally in the Android project.
7. Add the required location permissions.
8. Run the application on an Android device.

**Security Note:** Do not commit an actual API key, service-account credential, signing key, or `local.properties` file to GitHub. Keep credentials local and restrict the API key to the required Android application and APIs.

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
7. Run the application.
8. Grant location permission when requested.
9. Explore the different Google Maps viewing modes.

## 📊 Expected Output

After successfully running the application:

- Google Maps is displayed on the application screen.
- The device's current location is displayed when location permission is granted.
- Normal, Satellite, Terrain, and Hybrid views can be selected.
- Zoom In and Zoom Out controls operate.
- Users can view geographical locations.
- The application requests location permission at runtime.

## 📝 Result

Thus, an Android application was developed using Kotlin and Google Maps SDK to display interactive Google Maps with multiple map types, current device location, location markers, and zoom functionality.

## 🚀 Future Enhancements

- Continuous real-time location tracking.
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
