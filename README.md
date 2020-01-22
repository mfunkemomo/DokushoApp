# About
Dokusho is a reading app focusing on building foreign language skills through reading to understand vocabulary, grammar, and comprehension by allowing users to read and guess the translation. Dokusho currently supports only Japanese, consists of 2 reading levels with 4 stories each, and provides one translation hint for the page. Each page allows the user to hear the sentence if desired and gives a mini quiz to guess the translation. 

The future visionary of this app supports a variety of languages, a minimum of 5 reading levels, and ability to tap any word on the page for a translation with an API, and other app features such as instructions, improved UX/UI design, and other small features.

# Techstack
- AndroidStudio (includes Android SDK)
- Java 
- Firebase

# Setup
- Download AndroidStudio version 3.5.3 for Mac (733 MB) or higher
- Download Java version 13.0.1 or higher
- Sign up for Google Cloud Platform Services for access to Firebase

# Connect AndroidStudio to Firebase
- Go to Tools > Firebase > Connect your app to Firebase > Follow directions as prompted
- Go to build.gradle(Module:app) file and add the following under DEPENDENCIES: 
  - implementation ''com.google.firebase:firebase-core:17.2.1' or higher
  - implementation 'com.google.firebase:firebase-firestore:21.3.1'
  - implementation 'com.google.firebase:firebase-analytics:17.2.1'
- Make sure minSdkVersion is at least 21
- If set up still doesn't work, try deleting all data from your emulator and rerun program

Add data to database 
- In app project on Firebase create a new collection. 
- Import stories.json file to Firebase if using RealTime Database
- Will need to manually add stories to FireStore Cloud Database
- Note: stories are currently incomplete but you may manually add stories as needed

# Deployment to physical device 
- Follow Android deployment instructions here: https://developer.android.com/studio/run/device
- Ensure that phone language input includes Japanese (Settings>Language and Input>Language)
- If you are deploying to a Samsung phone, change text-to-speech default from Samsung-text-to-speech to Google-Text-to-Speech (Setting>Language and Input>Text-to-Speech>)