# About
Dokusho is a reading app focusing on building foreign language skills through reading to understand vocabulary, grammar, and comprehension. 

# Techstack
- AndroidStudio (includes Android SDK)
- Java 
- Firebase

# Setup
- Download AndroidStudio version 3.5.3 for Mac (733 MB) or higher
- Download Java version 13.0.1 or higher

# Connect AndroidStudio to Firebase
- Go to Tools > Firebase > Connect your app to Firebase > Follow directions as prompted
- Click on Add Analytics to your app 
- Go to build.gradle(Module:app) file and add the following: 
  - implementation 'com.google.firebase:firebase-core:16.0.4'
  - implementation 'com.google.firebase:firebase-firestore:21.3.1'

# Add data seeds to database 
- In app project on Firebase create a new collection. 
- Import stories.json file to Firebase

# Remove later
Install MongoDB version 4.4.2 on MacOS with Homebrew
  - $ brew tap mongodb/brew
  - $ brew install mongodb-community@4.2

Run MongoDB
- To run with terminal command: $ mongod --config /usr/local/etc/mongod.conf --fork
- To run with Mongo services: $ brew services start mongodb-community@4.2

Connect MongoDb with a mongo shell (like console)
- $ mongo