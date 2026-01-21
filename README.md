📒 Notes Organizer App

Overview

Notes Organizer App is an offline-first Android application built using Kotlin, Jetpack Compose, and Room Database.
The app allows users to create, edit, organize, and manage notes efficiently through a modern declarative UI and a scalable MVVM-based architecture.

This project focuses on production-ready Android development practices, including clean architecture, local data persistence, and predictable UI state management.

🚀 Features
📝 Note Management

Create Notes – Add notes with a title, description and image.
![WhatsApp Image 2026-01-21 at 7 45 27 PM](https://github.com/user-attachments/assets/715f667b-ae2f-4d7f-81f1-381131f1d599)

Edit Notes – Update existing notes seamlessly with state preservation.
![WhatsApp Image 2026-01-21 at 7 45 23 PM (1)](https://github.com/user-attachments/assets/bdf90043-b5d7-4eb9-a598-7cea35d07748)

Delete Notes – Remove notes using intuitive swipe-to-dismiss interactions.
![WhatsApp Image 2026-01-21 at 7 45 24 PM (1)](https://github.com/user-attachments/assets/40d9d9da-e2f7-4746-b383-abe020b8eb07)
![WhatsApp Image 2026-01-21 at 7 45 24 PM](https://github.com/user-attachments/assets/062d7a51-f7f4-4ef1-a3ea-57e923e82170)

Bottomsheet to handle image capturing or image selection.
![WhatsApp Image 2026-01-21 at 7 45 24 PM (2)](https://github.com/user-attachments/assets/d3376f44-790a-4f3a-b76d-1619c0ee2e24)

Long Press the Image to open the image in Gallery or in other image-supported App.
![WhatsApp Image 2026-01-21 at 7 45 23 PM](https://github.com/user-attachments/assets/e3f505ac-197d-4090-9d9d-24ffab886e9e)

Dark Mode/Light Mode and Consistent UI and theme across the whole app.
![WhatsApp Image 2026-01-21 at 7 45 24 PM](https://github.com/user-attachments/assets/5d420d9f-9149-491c-a8c7-dfb389d34124)
![WhatsApp Image 2026-01-21 at 7 45 24 PM (1)](https://github.com/user-attachments/assets/918ae55c-59ea-41d6-b0b6-22b27042a74b)

Empty UI Handling.
![WhatsApp Image 2026-01-21 at 7 45 31 PM](https://github.com/user-attachments/assets/9b718a04-9245-4d68-ba5c-1338a4678082)
![WhatsApp Image 2026-01-21 at 7 45 31 PM (1)](https://github.com/user-attachments/assets/d09b62fa-0c01-4bd8-9f25-3513be405065)



📦 Data & Architecture

Offline-First Architecture – Notes are persisted locally using Room (SQLite).

Efficient List Rendering – Uses LazyColumn for optimized list performance.

State-Driven UI – UI reacts to state changes exposed by ViewModels.

Clean State Management – Based on MVVM, ensuring separation of concerns.

🎨 UI & UX

Modern Declarative UI – Built entirely with Jetpack Compose.

Dark / Light Theme Support – Adaptive theming using Material Design.




🧠 Architecture Overview

The app follows MVVM (Model–View–ViewModel) architecture:

Model

Room entities

DAO interfaces

Database configuration

ViewModel

Manages UI state

Handles business logic

Uses Kotlin Coroutines for async operations

View (UI Layer)

Jetpack Compose screens

Stateless composables driven by ViewModel state

Repository

Abstracts data access

Acts as a single source of truth for local persistence

This structure ensures the codebase is scalable, testable, and maintainable.

🛠️ Tech Stack

Language: Kotlin

UI Framework: Jetpack Compose

Architecture: MVVM

Database: Room (SQLite)

Asynchronous Programming: Kotlin Coroutines

UI Components: LazyColumn, Scaffold, SwipeToDismiss

Theming: Material Design (Light/Dark mode)

📁 Project Structure
app/
├── data/
│   ├── local/              # Room entities, DAO, database
│   └── repository/         # Data access abstraction
├── ui/
│   ├── screens/            # Compose screens
│   └── components/         # Reusable UI components
├── viewmodel/              # ViewModels (state & logic)
└── MainActivity.kt         # Application entry point

🔮 Future Enhancements

Full-text search for notes

Categorization and tagging

Note lifecycle states (active, archived)

Backend sync using Spring Boot

User authentication and cloud backup

👨‍💻 Author

Shubh Tandon
Android & Backend Developer
GitHub: ShubhDev2604

📄 License

This project is open-source and intended for learning and portfolio demonstration purposes.
