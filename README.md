# Game of Thrones Characters App

A modular Android application that fetches characters from the [Thrones API](https://thronesapi.com/) using a clean architecture and MVI pattern.

## 🧱 Project Structure

- **App**: Entry point, DI setup, navigation
- **Core**: Shared utilities (network, database, models)
- **Feature**: Screen-specific logic (UI, ViewModel, UseCases)

## 🔧 Tech Stack

- **UI**: Jetpack Compose  
- **Architecture**: MVI + Clean Architecture  
- **DI**: Hilt  
- **Async**: Kotlin Coroutines, Flow  
- **Networking**: Retrofit, OkHttp  
- **Image Loading**: Coil  
- **Persistence**: Room  
- **Code Quality**: Detekt  
- **CI/CD**: GitHub Actions  
  - Lint with Detekt  
  - Run unit tests  
  - Upload build to Firebase App Distribution  
- **Firebase**:  
  - Analytics  
  - Crashlytics  
  - Performance Monitoring  
  - Notifications  

## ✅ Testing

- Unit tests written for UseCases

## 📦 Dependency Management

- All dependencies versioned using `libs.versions.toml` file

## 🚀 Getting Started

1. Clone the repo  
2. Add your `google-services.json`  
3. Build & run!

## 🔗 API

- [Thrones API](https://thronesapi.com/)

---

