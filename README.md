# NFC Hostel Entry System 📲🔐
## Project Overview
The **NFC Hostel Entry System** is an Android application developed using **Kotlin** in **Android Studio**. The app simulates an NFC-based access control system for hostels, allowing authorized users to gain entry while logging access attempts. This project is designed for **academic purposes** and demonstrates the use of Android fundamentals, NFC concepts, and basic access verification logic.

## Objectives
* To understand Android app development using Kotlin
* To simulate NFC-based authentication
* To implement access verification logic
* To design a simple and clean user interface using XML
* To prepare a project suitable for final-year / semester submission

## Features
* 📶 NFC tag detection (simulated)
* ✅ Access verification logic (`verifyAccess`)
* 🟢 Displays access status (Granted / Denied)
* 🧪 Test access button for simulation
* 📱 XML-based UI (no Jetpack Compose)

## Technologies Used
* **Android Studio**
* **Kotlin**
* **XML (UI Layouts)**
* **Android SDK**
* **NFC Concepts (Simulation)**

## Project Structure
app/
 ├── src/main/
 │   ├── java/com/example/nfc_hostel_entry/
 │   │   └── MainActivity.kt
 │   ├── res/layout/
 │   │   └── activity_main.xml
 │   └── AndroidManifest.xml
```

---

## How It Works (Logic)

1. The app launches with a simple UI.
2. A button simulates an NFC scan.
3. The `verifyAccess()` function checks whether the tag ID is authorized.
4. Access status is displayed on the screen.

---

## How to Run the Project

1. Open **Android Studio**
2. Select **Open Existing Project**
3. Choose the project folder
4. Sync Gradle
5. Run the app on an emulator or physical device

---

## Future Enhancements

* 🔐 Real NFC tag integration
* 🗄️ Backend database (PHP + MySQL)
* 📊 Access logs and history screen
* 👤 User roles (Student / Admin)

---

## Academic Use

This project is intended for **educational purposes only** and can be used as:

* Mini Project
* Final Semester Project
* NFC-based System Demonstration

---

## Author

**DENZEL CHIDZIKWE**
**Lazarus RUPIYA**

---

## License

This project is free to use for learning and academic submission.
