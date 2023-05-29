# Marvel Superheroes App

This is an Android application that allows you to browse a list of superheroes and view detailed information about each character. The app integrates with the Marvel API to fetch the data.

## Features

- Display a list of superheroes with their names and images.
- View detailed information about a selected superhero, including their description.
- Load and display superhero images using Glide.
- Use LiveData to observe and update the UI.
- Retrieve superhero data from the Marvel API using Retrofit.
- Unit test the ViewModel using Mockito and JUnit Jupiter.
- Follows the MVVM Clean Architecture pattern.

## Libraries and Technologies Used

- Kotlin
- Hilt for dependency injection
- Glide for image loading and caching
- LiveData for observing and updating the UI
- Retrofit for making API requests
- Mockito for mocking dependencies in unit tests
- JUnit Jupiter for writing unit tests

## Prerequisites

Before running the application, make sure you have the following:

- Marvel API key: You need to obtain an API key from the Marvel Developer Portal (https://developer.marvel.com) and update it in the project's configuration.
- Android Studio: The latest version of Android Studio installed on your machine.

## Setup and Configuration

1. Clone the repository or download the source code.

2. Open the project in Android Studio.

3. Update the `local.properties` file with your Marvel API key: api_key="YOUR_API_KEY"

4. Build and run the project on an Android device or emulator.

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).