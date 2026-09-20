# LINGOPILOT Backend

LINGOPILOT is an AI-powered language-learning platform designed to provide structured lessons, interactive exercises, personalized feedback, learner progression, and AI-assisted conversations.

This repository contains the REST API backend built with Spring Boot and SQL Server.

## Core Features

- JWT-based authentication and authorization
- User, role, and preference management
- Languages, levels, courses, modules, and lessons
- Structured lesson sections: explanations, examples, tips, and summaries
- Multiple-choice, fill-in-the-blank, and free-text exercises
- Exercise submission, grading, and learner progression
- AI-powered conversations and personalized feedback
- Skill assessment and lesson recommendations
- Vocabulary and learning-progress tracking
- Subscription plans, usage limits, and payment workflows
- Notifications and notification preferences

## Technology Stack

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT authentication
- Jakarta Validation
- Hibernate
- Microsoft SQL Server
- Maven
- OpenAI API

## Architecture

The application follows a layered architecture:

```text
Client
  |
  v
Controller
  |
  v
Request DTO
  |
  v
Service
  |
  v
Domain Model
  |
  v
Repository
  |
  v
SQL Server
```

Responses are returned through dedicated response DTOs. Request DTOs act as input whitelists, while sensitive entity fields are excluded from API responses.

## Project Structure

```text
src/main/java/com/example/AiLanguageApp/
├── AI/
│   ├── Client/
│   ├── Config/
│   ├── Context/
│   ├── Controller/
│   ├── DTO/
│   ├── Grading/
│   ├── Progression/
│   ├── Prompt/
│   ├── Rag/
│   └── Service/
├── Controller/
├── DTO/
├── DTOs/
├── Learning/
├── Mapper/
├── Monetization/
├── Payment/
├── Repository/
├── Service/
├── config/
├── exception/
├── model/
└── security/
```

## Database Architecture

The application uses the `AiLanguageApp` SQL Server database.

The schema contains 29 tables covering:

- Users, roles, and user preferences
- Languages, levels, skills, and vocabulary
- Courses, modules, lessons, and lesson sections
- Exercises, exercise options, and attempts
- AI conversations, messages, feedback, and assessments
- User progress and learning records
- Subscriptions, plans, usage, and payments
- Notifications and notification settings

The complete database definition is available in `database/schema.sql`. It includes tables, primary and foreign keys, unique and check constraints, default constraints, and indexes.

## Database Setup

Create the database in SQL Server:

```sql
CREATE DATABASE AiLanguageApp;
GO
```

Execute the following scripts in order:

```text
database/schema.sql
database/01_lesson_sections.sql
database/01_subscription_plan_entitlements.sql
database/02_seed_english_a2_past_simple.sql
database/02_seed_lingopilot_plans.sql
```

The schema script contains structure only and does not include user records, credentials, or production data.

## Configuration

The real `application.properties` file is intentionally excluded from Git.

Create your local configuration from the example:

```powershell
Copy-Item `
  "src/main/resources/application-example.properties" `
  "src/main/resources/application.properties"
```

Configure the required environment variables:

```powershell
$env:DB_URL="your-sql-server-jdbc-url"
$env:JWT_SECRET="your-secure-jwt-secret"
$env:OPENAI_API_KEY="your-openai-api-key"
```

Never commit actual credentials or API keys.

## Build

Build the application without running tests:

```powershell
.\mvnw.cmd clean package -DskipTests
```

The executable JAR is generated under:

```text
target/AiLanguageApp-backend-0.0.1-SNAPSHOT.jar
```

## Run

Start the application with Maven:

```powershell
.\mvnw.cmd spring-boot:run
```

Or run the packaged application:

```powershell
java -jar target/AiLanguageApp-backend-0.0.1-SNAPSHOT.jar
```

By default, the API runs at `http://localhost:2020`.

## Security

LINGOPILOT uses Spring Security and JWT authentication. Security measures include:

- Stateless JWT authentication
- Password hashing
- Request DTO input whitelisting
- Incoming-request validation
- Separation between entities and API responses
- Centralized exception handling
- Environment-based secret configuration
- Excluded local configuration files

## Build Status

The project currently compiles and packages successfully with Maven. Integration tests require a reachable SQL Server instance and valid local database configuration.

## Repository Safety

The repository does not contain:

- OpenAI API keys
- JWT secrets
- Database credentials
- Local `application.properties`
- User or production data
- Compiled Maven output

## Roadmap

- Expand multilingual learning content
- Improve AI grading and recommendations
- Add retrieval-augmented generation
- Introduce database migrations
- Add automated tests and CI/CD
- Containerize the application
- Deploy the backend and database to the cloud
- Integrate the React Native mobile application

## Author

**Bouazza BENACHOUR**

GitHub: [BouazzaBENACHOUR-Engineering](https://github.com/BouazzaBENACHOUR-Engineering)
