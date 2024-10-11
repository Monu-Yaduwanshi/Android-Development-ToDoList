# Android-Development-ToDoList
# To-Do List App

## Overview
This To-Do List app is a simple yet effective application that allows users to manage their tasks easily. Built using **Kotlin** and **Jetpack Compose**, it provides a modern, responsive user interface. The app leverages **Firebase Database** for storing and retrieving tasks, ensuring data persistence and real-time updates.
# ✅ To-Do List App 📋

Welcome to the **To-Do List App**—a beautifully simple yet powerful tool to help you manage your daily tasks and stay organized. Built with the modern **Kotlin** programming language and **Jetpack Compose**, this app offers a seamless and intuitive user experience. With the integration of **Firebase Database**, your tasks are securely stored and available in real-time, allowing for effortless task management, anytime and anywhere.

---

## ✨ Features

- **📝 Add Tasks**  
  Quickly add new tasks by providing a task name and time, making it easy to keep track of your to-dos.

- **✏️ Edit Tasks**  
  Modify your tasks with ease to ensure you stay on top of everything.

- **❌ Delete Tasks**  
  Effortlessly remove completed or unnecessary tasks from your list.

- **📱 Responsive UI**  
  Designed with **Jetpack Compose**, the UI delivers a smooth, modern experience that adapts to all devices.

- **💾 Firebase Database**  
  Enjoy real-time data persistence with tasks stored and synced across devices using **Firebase Database**.

---

## 🛠️ Technologies Used

- **Android Studio**: The development environment used for building and running the app.
- **Kotlin**: The primary language for app development.
- **Jetpack Compose**: A modern toolkit for building beautiful, responsive Android UIs.
- **Firebase Realtime Database**: Cloud-hosted database that ensures data persistence and real-time synchronization.

---

## 🚀 Getting Started

To get the **To-Do List App** up and running locally, follow these simple steps:

### 📋 Prerequisites

- **Android Studio**: Ensure Android Studio is installed on your machine. You can download it [here](https://developer.android.com/studio).
- **Firebase**: Set up a Firebase project and add your Android app to it.

### ⚙️ Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/todolist.git

Open the project in Android Studio.

Set up Firebase:

Go to the Firebase Console, create a new project, and add your Android app.
Follow the instructions to download the google-services.json file and place it in the app/ directory.
Add Dependencies:
Ensure your build.gradle (app-level) contains the following dependencies:
dependencies {
    implementation platform('com.google.firebase:firebase-bom:32.1.1')
    implementation 'com.google.firebase:firebase-database-ktx'
    implementation 'androidx.compose.ui:ui:1.4.0'
    implementation 'androidx.compose.material3:material3:1.1.0'
    implementation 'androidx.activity:activity-compose:1.6.0'
    // Other dependencies...
}
Sync & Run:
Sync your project and run it on an emulator or a physical Android device.
📲 Usage
Add New Tasks: Open the app, click the "Add Task" button, and create a new task by entering the name and time.
Edit or Delete Tasks: Manage existing tasks by using the Edit or Delete buttons available on each task item.
