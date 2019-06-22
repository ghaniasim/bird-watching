# Bird Watching
An Android app to keep track of observed birds.

## Features
List of features can be found from backlog management system from the following link:

https://github.com/ghaniasim/bird-watching/projects/1?fullscreen=true

## Experience
It was challenging but fun to make this app as it includes combination of skills to build.

## Screenshots

<image src="screenshots/listView.jpeg" width=300>    <image src="screenshots/addNewBird.jpeg" width=300>
    <image src="screenshots/cameraOptions.jpeg" width=300>    

## How to Install/ Run via Github on Android Studio 

1. Clone or download the project on your local machine via following link
```
https://github.com/ghaniasim/bird-watching.git
```
2. Unzip the file
3. Open the project with Android Studio
4. Build and run on emulator or debugging enabled devive

## How to install this app on your device with APK

1. Download the `apk` file from the following link

    https://www.

2. Install on your device
3. Allow installation from "unknown sources" from your device settings

## Dependencies

```
implementation "com.android.support:support-core-utils:28.0.0"
implementation 'com.github.dhaval2404:imagepicker-support:1.1'

def room_version = "1.1.1"
implementation "android.arch.persistence.room:runtime:$room_version"
kapt "android.arch.persistence.room:compiler:$room_version"
annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'
implementation 'android.arch.lifecycle:viewmodel:1.1.1'
annotationProcessor 'android.arch.lifecycle:compiler:1.1.1'
implementation 'com.android.support:recyclerview-v7:28.0.0'
implementation 'android.arch.lifecycle:viewmodel:1.1.1'
annotationProcessor 'android.arch.lifecycle:compiler:1.1.1'
implementation 'android.arch.lifecycle:extensions:1.1.1'

implementation "android.arch.lifecycle:extensions:1.1.1"
kapt "android.arch.lifecycle:compiler:1.1.1"

implementation "android.arch.persistence.room:runtime:$room_version"
kapt "android.arch.persistence.room:compiler:$room_version"
```

```
allprojects {
   repositories {
      	jcenter()
       	maven { url "https://jitpack.io" }
   }
}
```

## Special mention: for third party libraries ( link ) 
ImagePicker library https://github.com/Dhaval2404/ImagePicker
