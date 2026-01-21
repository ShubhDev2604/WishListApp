![WhatsApp Image 2026-01-21 at 7 45 31 PM (1)](https://github.com/user-attachments/assets/8253893a-3ae9-493e-9523-d6b6b010be4e)📒 Notes Organizer App
Notes Organizer App is an offline-first Android application built using Kotlin, Jetpack Compose, and Room Database.
It allows users to create, organize, update, and manage notes efficiently with a clean, modern UI and a scalable architecture based on MVVM.

This project demonstrates production-ready Android practices, focusing on clean architecture, local persistence, and maintainable UI state management.

🚀 Features
Create Notes – Add notes with title and description.
![WhatsApp Image 2026-01-21 at 7 45 27 PM](https://github.com/user-attachments/assets/aa445287-7bea-4009-ac78-e088486d9159)
Edit Notes – Update existing notes seamle![WhatsApp Image 2026-01-21 at 7 45 23 PM (1)](https://github.com/user-attachments/assets/4636cb31-9fff-4bb1-b282-c4b7c73c866e)
ssly.
Delete Notes – Remove notes using intuitive swipe actions.
![WhatsApp Image 2026-01-21 at 7 45 24 PM](https://github.com/user-attachments/assets/24883da8-c480-4d7d-8d52-7cfaf53ff5b1)
Offline-First Architecture – Notes are stored locally using Room Database.
Organized List View – Efficient rendering using LazyColumn.
Modern UI – Built entirely with Jetpack Compose.
Dark / Light Theme Support – Adaptive UI for better usability.
![WhatsApp Image 2026-01-21 at 7 45 31 PM](https://github.com/user-attachments/assets/7983a625-451f-4416-9086-ae66766b88b7)
![WhatsApp Image 2026-01-21 at 7 45 31 PM (1)](https://github.com/user-attachments/assets/b47d4c4c-bb77-47d0-8010-8093f1b271af)
Clean State Management – MVVM-based architecture.![WhatsApp Image 2026-01-21 at 7 45 24 PM (2)](https://github.com/user-attachments/assets/174c5d33-30ea-4218-a94d-da64786eeb54)


🛠️ Tech Stack
Language: Kotlin
UI: Jetpack Compose
Architecture: MVVM
Database: Room (SQLite)
Asynchronous: Kotlin Coroutines
UI Components: LazyColumn, Scaffold, SwipeToDismiss
Theming: Material Design (Light/Dark mode)

📁 Project Structure
app/
├── data/
│   ├── local/              # Room entities, DAO, database
│   └── repository/         # Data access layer
├── ui/
│   ├── screens/            # Compose screens
│   └── components/         # Reusable UI components
├── viewmodel/              # ViewModels
└── MainActivity.kt         # App entry point

👨‍💻 Author
Shubh Tandon
Android & Backend Developer
GitHub: ShubhDev2604

📄 License
This project is open-source and intended for learning and portfolio demonstration purposes.
