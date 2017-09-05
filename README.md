# Realtime Counter (Kotlin Android App)

This is an android application that showcases how to implement a realtime counter using Pusher.
It has a single Activity which contains a button and a count `TextView`.
You would see the count update in realtime as you and other users tap on the button.


## Requirements
- [A free Pusher account](https://pusher.com)
- [Android Studio](https://developer.android.com/studio/index.html) version 2.3 or above 
- MinSdkVersion: 19
- TargetSdkVersion: 26

## Setup Instructions
1. Create an app on Pusher and copy your app key.
2. Start the server at [https://github.com/perfectmak/kotlin-realtime-counter-server-pusher](https://github.com/perfectmak/kotlin-realtime-counter-server-pusher).
3. Clone this repository and open it with Android Studio.
4. You would need to update the SERVER_URL and PUSHER_API_KEY static variables in the MainActivity.java file to match the those of your server and pusher setup.
5. Build the project and run it.