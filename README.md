# Realtime Counter (Kotlin Android App)
This is an android application that showcases how to implement a realtime counter using Pusher. It has a single Activity which contains a button and a count `TextView`. You would see the count update in realtime as you and other users tap on the button. See [here](https://pusher.com/tutorials/counter-kotlin/) for a tutorial on how this project works.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
You need to have the following before you can setup and test this project on your local machine:

- [A free Pusher account](https://pusher.com)
- [Android Studio](https://developer.android.com/studio/index.html) version 2.3 or above 
- Android MinSdkVersion: 19
- Android TargetSdkVersion: 26

### Setup Instructions
1. Create an app on Pusher and copy your app key.
2. Start the server at [https://github.com/perfectmak/kotlin-realtime-counter-server-pusher](https://github.com/perfectmak/kotlin-realtime-counter-server-pusher).
3. Clone this repository and open it with Android Studio.
4. You would need to update the SERVER_URL and PUSHER_API_KEY static variables in the MainActivity.kt file to match the those of your server and pusher setup.
5. Build the project and run it.

### Testing the Application
To test it out, ensure that the [realtime server](https://github.com/perfectmak/kotlin-realtime-counter-server-pusher) is running and the Android application’s SERVER_URL is updated accordingly.

Now, run the application on multiple devices. You would notice that as you click on the button, the count increases in realtime across all the devices

## Built With
* [Kotlin](https://kotlinlang.org/) - Static Typed programming language
* [Android Studio](https://developer.android.com/studio/index.html) - Android Development IDE
* [Pusher](https://pusher.com/) - APIs to enable devs building realtime features


