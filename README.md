# 🎮 Admin Portal – Internal Dashboard for Game & Community Management

An internal web portal for managing game-related vouchers, user rewards, interactive questions, and integrations with platforms like **Discord**, **Steam**, and potentially **YouTube**, **Facebook**, and **Epic Games**.

> This project is built with **Spring Boot**, **Thymeleaf**, **PostgreSQL**, and is designed to serve as a **secure company-only internal tool**.

---

## ✨ Features

### 🧾 Voucher System
- Create, update, delete, and manage vouchers.
- Track claimed vs. unclaimed vouchers.
- Attach rewards to specific vouchers.

### ❓ Question Tool
- Create and manage questions (quizzes, polls, etc.).
- Organize by type: multiple choice, boolean, text.
- Attach questions to quests or campaigns.

### 🎁 Reward Management
- Create and attach rewards to vouchers or quests.
- Separate reward types: digital, physical, in-game.

### 📊 Dashboard & Statistics
- Overview of:
    - Number of claimed vouchers
    - Question response analytics
    - Real-time data from Discord and Steam
- Visualizations via **Chart.js** or **Google Charts**

### 🔐 Authentication
- OAuth2 login via Google (restricted to company email domain, e.g. `@wanderers.ai`)
- Spring Security for role-based access control

---

## 🌐 Platform Integrations

| Platform     | Integration Type | Notes |
|--------------|------------------|-------|
| **Discord**   | JDA (Java Discord API) | Show active users, events, message stats |
| **Steam**     | Steam Web API    | Show player count, achievements, game stats |
| **YouTube**   | Google API       | Show subscribers, video views (optional) |
| **Facebook**  | Facebook Graph API | Requires app review and business account |
| **Epic Games**| Not directly supported | No public API for stats |
| **Twitter/X** | Paid API only    | Not implemented due to cost restrictions |

---

## 🏗️ Technologies Used

- **Spring Boot**
- **Thymeleaf**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **Chart.js**
- **JDA (Discord)**
- **Steam Web API**
- GitHub + GitHub Desktop
- IntelliJ IDEA

---

## 🚀 Getting Started

### 🧱 Dependencies via Spring Initializr

- Spring Web
- Thymeleaf
- Spring Data JPA
- PostgreSQL Driver
- Spring Security
- Spring Boot DevTools
- Validation
- (Optional) OAuth2 Client

### 🛠️ Build & Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
