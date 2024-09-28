# Meeting Scheduling System

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup](#setup)

## Introduction
The Meeting Scheduling System is a backend service that allows users to schedule meetings while ensuring there are no collisions between participants' or rooms' availability. The system is designed to handle scheduling for single or multiple users, manage room availability, and detect conflicts in real-time.

This project can be adapted to a variety of applications, such as employee meeting management, conference room booking, or resource scheduling in an office environment.

## Features
- **Single-user meeting scheduling**: Schedule meetings for a single user with day, start time, and end time.
- **Multi-user support**: Add multiple users to a meeting and check availability across all participants.
- **Room booking**: Schedule meetings with room availability taken into account.
- **Collision detection**: Detect if there is any collision (time overlap) with existing meetings for participants and rooms.
- **Modular design**: The system is designed with OOP principles, making it easy to add new features.

## Architecture
The system follows a service-oriented architecture. Here's a high-level view of the components:

1. **Entities**:
    - `User`: Represents users (participants) in the system.
    - `MeetingRoom`: Represents rooms available for meetings.
    - `Meeting`: Represents a scheduled meeting.
    - `Availability`: Tracks available time slots for both users and meeting rooms.

2. **Services**:
    - `MeetingService`: Handles the scheduling of meetings, updates to availability, and conflict detection.
    - `MeetingRoomService`: Handles creation of meeting room and updation of availabilities.
    - `UserService`: Handles creation of users and updation of availabilities.
    - `CollisionDetectionService`: Checks if the room or users are available during the desired time.

3. **Repositories**:
    - `UserRepository`: Manages CRUD operations for users.
    - `MeetingRoomRepository`: Manages CRUD operations for meeting rooms.
    - `MeetingRepository`: Manages CRUD operations for meetings.

## Technologies Used
- **Java 17**: Primary programming language.
- **Spring Boot**: Web framework to build the service.
- **JPA/Hibernate**: ORM tool for database interaction.
- **PSQL**: Postgres database for development and testing.
- **Maven**: Build tool for managing dependencies and running the project.

## Setup
### Prerequisites
- Java 17 or higher
- Maven
- IDE (IntelliJ, Eclipse, or any Java-supporting IDE)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/meeting-scheduling-system.git
   cd meeting-scheduling-system
2. Update your DB user and password in application.properties.
   - Replace ${YOUR_DB_USER} and ${YOUR_DB_PASSWORD} with actual user and password.
3. Build the project using Maven:
  ```bash
  mvn clean install
  ```
4. Run the application:
  ```bash
  mvn spring-boot:run
  ```
5. The application will start running on http://localhost:8977
6. Hit the URL - http://localhost:8977/swagger-ui/index.html
