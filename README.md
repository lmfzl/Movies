# Movies

A small Android app for browsing movies.

## Features

- Fetches a movie catalog from the Poiskkino API
- Shows movie details, trailers, and reviews
- Lets users save movies to a local favorites list

## Tech stack

- Java
- Android SDK
- Retrofit
- OkHttp
- RxJava 3 
- Room
- Glide.

## API key setup

1. Get an API key from Poiskkino telegram bot.
2. Open the root `local.properties` file. Keep the existing `sdk.dir` entry.
3. Add your key:

   ```properties
   POISKINO_API_KEY=your_api_key
   ```

4. Sync the project with Gradle and run the app.

## Build

Open the project in Android Studio, add the API key to `local.properties`, sync Gradle
