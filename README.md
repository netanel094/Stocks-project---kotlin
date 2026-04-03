# Stocks App

An Android app for managing and tracking a personal stock portfolio.

## Features

- View a list of stocks with company logos, symbols, and prices
- Add new stocks with custom symbol, company name, price, and logo
- Swipe to delete stocks (with confirmation dialog)
- Tap any stock to view its details
- Data persists locally using Room database

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM + Repository pattern
- **Database:** Room (SQLite)
- **UI:** Fragments + Android Navigation Component
- **Image loading:** Glide
- **Design:** Material Design 3

## Getting Started

### Requirements

- Android Studio (latest stable)
- Android device or emulator running API 24+

### Run the app

1. Clone the repository
2. Open the project in Android Studio
3. Wait for Gradle sync to complete
4. Press **Run** (▶) or `Shift+F10`

## Project Structure

```
app/src/main/java/com/example/buildingblocks/
├── data/
│   └── model/
│       ├── Item.kt               # Stock data model
│       ├── local_db/             # Room DAO and Database
│       └── repository/           # ItemRepository
└── ui/
    ├── MainActivity.kt
    ├── ItemsViewModel.kt
    ├── all_characters/           # Stock list screen
    ├── add_character/            # Add stock screen
    └── single_character/         # Stock detail screen
```
