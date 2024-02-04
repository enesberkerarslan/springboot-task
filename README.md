# Airway Flight Planning API

Flight Management REST API project that includes input validation, synchronized operations, central exception handling, and logging features for improved functionality and reliability.

## Features

### FLIGHT

- **Creating And Updating Flight:**
    - If the id is sent in the request, the flight belonging to that id is updated. If the id is not given, a new flight is created with the information provided.
    - Create and Update operations are processed synchronously.
    - Business rules:
        - There must be daily at most 3 flights for an airline between 2 destinations.
        - If the plane has a flight between the given dates, it cannot create a new flight.

- **List all flights**
- **Filtering flights by arrival and departure airport**
- **Deleting flight by id**

### AIRPORT

- **Filtering airports by airport code**
- **List all airports**

### AIRPLANE

- **List all airplanes**
- **Filtering airplanes by airplane and airline codes**

### AIRLINE

- **List all airlines**
- **Filtering airlines by airline code**

## Technologies Used

1. Java (Programming Language)
2. Spring Boot (Application Platform)
3. Spring Data JPA (Data persistence)
4. Swagger (Documentation)

## Getting Started

The source code can be checked out to your local machine and then built and run the application either from your IDE after importing it as a Gradle project or just from a command line. Follow these steps for the command-line option:

### Prerequisites

1. Java 21
2. Gradle
3. Git

### Installing & Running

#### Clone this repo into your local:

```bash
git clone https://github.com/enesberkerarslan/springboot-task.git

# Navigate into the project directory:
cd springboot-task

# Build the project using Gradle:
./gradlew build

# Run the Spring Boot application:
./gradlew bootRun

# Access the application:
# Visit http://localhost:8081 in your web browser.

# Test the Endpoints:
#First http://localhost:8081/swagger-ui/index.html#/ u can open this and find all api endpoints or -->
# All Flights: http://localhost:8081/api/flights
# Search Flights: http://localhost:8081/api/search/flights?departure=Istanbul&arrival=Izmir&departureDate=2024-02-07T15%3A45%3A00

# Feel free to customize it further according to your project's structure and requirements.
