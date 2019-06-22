# Bird Watching
This is an android app for bird watcher, where they can save their watches, add photos, rarity and notes for each bird they watch.

## Features
List of features can be found from backlog management system from the following link:

https://github.com/ghaniasim/bird-watching/projects/1?fullscreen=true

## Experience
This application was built as an assignment, it was a simple app but half way through the app, I decided to implement a better architecture, which made it a bit more exciting for me.

## How to Use the App 

Adding a Bird
- Click Floating Action button in the main screen (+) 
- Add name, select rarity from drop down and enter your notes
- To Add a picture click on ADD IMAGE 
- Choose Camera or Gallery
- To Crop Image, select the photo and adjust/ crop accordingly

Delete a Bird
- To delete in birds listing view, swipe left to delete 
- Note: There is no confirmation dialog or UNDO feature in this version

Sort by Most Recent/Oldest first 
- Default app will sort all observations by recent first 
- In action bar, click sorting icon to switch between most recent and oldest observations

Data Storage
- All Data is stored in your device and in this version it is not saved on any remote storages
- Deleting the app will also delete all your data

## Screenshots

<image src="screenshots/listView.jpeg" width=300>    <image src="screenshots/addNewBird.jpeg" width=300>
    <image src="screenshots/cameraOptions.jpeg" width=300>  <image src="screenshots/imageCropping.jpeg" width=300> 
        
## Architecture
Application is built using architecture component ViewModel, LiveData and Room Database. It's explained in the image below

<image src="screenshots/architecture.jpeg" width=700>
    
Photo Credits: https://codelabs.developers.google.com/

## How to Install/ Run via Github on Android Studio 

1. Clone or download the project on your local machine via following link
```
https://github.com/ghaniasim/bird-watching.git
```
2. Unzip the file
3. Open the project with Android Studio
4. Build and run on emulator or debugging enabled devive

## How to install this app on your device with APK
minSdkVersion 23 required to run this app

1. Download the `apk` file from the following link

    https://github.com/ghaniasim/bird-watching/raw/development/apk/bird-watching.apk

2. Install on your device
3. Allow installation from "unknown sources" from your device settings

## Testing
There are no tests implemented at the moment

## Dependencies

```
apply plugin: 'kotlin-kapt'

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
