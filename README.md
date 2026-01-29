# Notes Management App

A **full-stack, offline-first Notes application** with an Android client built using modern Android development practices and a secure, production-style backend.

---

## ✨ Features

### Android App

* Offline-first notes management with **RoomDB** as the local source of truth
* Modern UI built with **Jetpack Compose + Material 3**, supporting **dynamic theming** that adapts automatically to system light/dark mode
* **Theme preference persistence** using SharedPreferences to restore the last selected mode on app launch
* Home screen built using **Scaffold** with **Floating Action Button (FAB)** for adding new notes
* **Swipe-to-delete** interactions with reactive UI updates
* **Bottom Sheet** for note image actions (capture via camera or upload from gallery)

  * Camera images are stored in the app’s internal directory
  * Gallery images persist their URI reference
* **Implicit Intents** for enhanced user interaction:

  * Long-press on note image opens external image viewer options
  * Share notes via system Share Intent (text / content)
* Secure API communication using **Retrofit**
* **JWT tokens stored using EncryptedSharedPreferences** (token expiry: **15 minutes**)
* Dependency Injection using **Hilt**
* Image loading with **Coil**
* Navigation implemented using **NavHostController**
* Network inspection and debugging using **Chucker****

### Backend

* RESTful APIs built with **Spring Boot**
* **Stateless authentication** using **Spring Security + JWT**
* **JWT token validation** with custom authentication filter (15-minute expiry)
* **Role-based access control (ADMIN / USER)**

  * ADMIN-only endpoint to fetch all users
* Password hashing using **bcrypt**
* Custom **Security Filter Chain** configuration
* Centralized exception handling using **@ControllerAdvice**
* JWT-layer authentication and authorization error handling with controller advice
* JWT-layer authorization and authentication error handling

---

## 🛠 Tech Stack

### Android

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Room Database
* Retrofit & OkHttp
* Hilt (Dependency Injection)
* Coil
* Coroutines
* Chucker

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA & Hibernate
* JWT
* Maven

### Infrastructure

* Docker
* Render (Backend deployment)
* Neon (PostgreSQL database)

---

## 🏗 Architecture Overview

* **Android** follows an **MVVM + Repository** pattern with Room as the single source of truth.
* **Offline-first design** ensures full usability without network connectivity.
* **Build Variants**:

  * **dev** → connects to local backend and local **MySQL** instance
  * **prod** → connects to deployed backend on Render and **PostgreSQL (Neon)**
* **Backend** exposes secure REST APIs consumed by the Android app via Retrofit.
* Authentication is handled using **stateless JWT tokens**, securely stored on the client using EncryptedSharedPreferences.
* Authentication is handled using **stateless JWT tokens**, stored securely on the client.

---

## 🚀 Deployment

### Backend

* Spring Boot application **Dockerized locally**
* Deployed on **Render (Free Tier)** using a custom Docker image
* Production database hosted on **Neon (PostgreSQL)**

⚠️ **Cold Start Note**
Since the backend is hosted on Render’s free tier, the **first login/signup request may timeout** due to cold start. Please wait **1–2 minutes** and retry the request.

---

## 📦 APK & Demo

* A **debug production APK** is available via **GitHub Releases**.
* Enable **notifications** after installing the app to inspect API calls using **Chucker**.
* App demo is available via **Github Releases**.

---

## 🔐 Authentication & App Launch Flow

1. App launches → **Splash Screen** is displayed
2. App automatically calls **GET /api/auth/me**
3. Backend validates JWT:

   * **200 OK** → user is redirected to Home screen
   * **401 Unauthorized** → user is redirected to Login screen
4. Login / Signup endpoints issue a JWT token upon success
5. JWT is stored securely using **EncryptedSharedPreferences**
6. Role-based authorization controls access to protected endpoints

---

## 📂 Repository Structure (High-level)

```
android-app/
 ├── ui
 ├── viewmodel
 ├── repository
 ├── data (Room, Retrofit)
backend/
 ├── controller
 ├── service
 ├── repository
 ├── security
 └── exception
```

android-app/
backend/
├── controller
├── service
├── repository
├── security
└── exception

```

---

## 👨‍💻 Author

**Shubh Tandon**  
Android / Backend / Full-Stack Developer

- GitHub: https://github.com/ShubhDev2604
- LinkedIn: https://www.linkedin.com/in/shubh-tandon-8133b8201

---

## 📌 Notes

This project emphasizes **real-world engineering practices**, including:
- Offline-first mobile architecture
- Secure authentication & authorization
- Environment-based configurations (dev/prod)
- Cloud deployment using Docker
- Debugging and observability using Chucker

The focus is on **production readiness**, not demo-only or tutorial-based implementations.

```
