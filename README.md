# XKCD Comic Viewer – Final Project for CS 377

This Android app was developed as a final project for CS 377. It serves as a comic viewer for [XKCD](https://xkcd.com), allowing users to browse, search, and favorite comics using the XKCD API.

## Features

- Search comics by title or number
- Browse the latest XKCD comics
- Favorite and unfavorite comics
- View your saved favorites in a separate screen

## Tech Stack

- Kotlin
- MVVM Architecture
- Retrofit (networking)
- Room (local storage)
- Coil (image loading)
- Navigation Components + Fragments
- ViewBinding

## Getting Started

### Prerequisites

- Android Studio (Arctic Fox or newer)
- Android SDK 33+
- Internet connection (for API access)

### Installation

1. Clone the repo:

```
   git clone https://github.com/AustinCarlile/Carlile_FinalProject377.git
```

2. Open the project in Android Studio.
   
![image](https://github.com/user-attachments/assets/8e50a4a5-c177-4059-9de7-1e07853a0e2e)

![image](https://github.com/user-attachments/assets/937d511d-85c3-4040-84e9-bbb787887872)

4. Sync Gradle and build the project.
   
![image](https://github.com/user-attachments/assets/ff2819cf-e558-4dd2-808e-4b8c6e3c19fc)

6. Run the app on an emulator or physical device.
   
![image](https://github.com/user-attachments/assets/5dc6f9d9-de53-4be6-b946-0a15c3d9b008)


### Functionality

Opening the app will bring you to the home page with the latest XKCD comic.
Selecting the hamburger menu in the top left will allow you to switch between pages.

Home- Allows you to view the most recent XKCD comic as a comic of the day.

Browse- See the top ten most recent comics and favorite them if wanted.

Search- Search by comic ID and favorite comics if wanted.

Favorites- See your favorited comics and unfavorite if wanted.


### Troubleshooting

If no comics are being displayed and all comic information is showing up as placeholder values, here are some possible Solutions:

* Check Android Studio for updates and update if available.
* Make sure that your device or emulator has an internet connection.
* Check www.xkcd.com to make sure that the website is up.
* Test specific API functionality:
   - Check https://xkcd.com/info.0.json for a correct HTTP Response with the latest comic.
   - Check https://xkcd.com/{id_num}/info.0.json (replace {id_num}) for a correct HTTP Response with a specific comic by ID.
* Rebuild the project.
* Delete and reclone the project.

### Contribution Guidelines

If any code is added or changed by any user other than AustinC, Cole2303 and noschwa:

Leave a comment with your github username, detailing what code you added or changed.




