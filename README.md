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

3. Sync Gradle and build the project.
4. Run the app on an emulator or physical device.


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
   - Check https://xkcd.com/info.0.json for the latest comic for a correct HTTP Response.
   - Check https://xkcd.com/{id_num}/info.0.json for a specic comic for a correct HTTP Response.
* Rebuild the project.
* Delete and reclone the project.

### Contribution Guidelines

If any code is added or changed by any user other than AustinC, Cole2303 and noschwa:

Leave a comment with your github username, detailing what code you added or changed.




