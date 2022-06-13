# Documentation

In order to test the Android application locally, follow the guide bellow:
1. Before cloning this repo, you must clone the [Node.js server](https://github.com/leonardusluwianto/URMAJOR-App-Backend) repo first by enter the following command in Git Bash:<br>`git clone https://github.com/leonardusluwianto/URMAJOR-App-Backend.git`
2. Follow the steps to run the server in https://github.com/leonardusluwianto/URMAJOR-App-Backend.git
3. Then, clone this repo:<br>`https://github.com/leonardusluwianto/URMAJOR-Survey-App.git`
4. Open the Android project (URMAJOR-Survey-App) in Android Studio
5. Open `MainActivity.java` file at path app/src/main/java/com/example/urmajorsurveyapp/MainActivity.java
6. Replace the BASE_URL in line 33 into "http://10.0.2.2:5000"<br>Result: `private String BASE_URL = "http://10.0.2.2:5000";`
7. Click the **Run 'app'** (Shift + F10) above to run the Android app in the emulator.
8. Fill all the survey fields, then click **Submit** button.
9. If the data is successfully submitted, the alert *Jawaban Anda telah tercatat* will be shown.