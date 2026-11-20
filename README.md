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

## License

MIT License

Copyright (c) 2026 lmfzl

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.