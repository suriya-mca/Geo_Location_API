# Geo Location API

## Overview

Geo Location API is a simple and effective IP Geolocation Service built using Spring Boot and Maven. It retrieves users' IP addresses and provides approximate location details, including city, region, and country.

## Features

- **IP Geolocation**: Retrieve approximate location details based on users' IP addresses.
- **City, Region, and Country Details**: Provides detailed information such as city, region, and country.
- **Easy Integration**: Built with Spring Boot, making it easy to integrate into existing Spring applications.
- **Maven**: Dependency management and build automation using Maven.

## Technologies Used

- **Spring Boot**: Framework for creating standalone, production-grade Spring-based applications.
- **Maven**: Dependency management and build automation tool.
- **Java**: Programming language used for backend development.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21
- Maven installed
- IDE (like IntelliJ IDEA or Eclipse or VSCode)

### Installation Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/suriya-mca/Geo_Location_API.git
   ```

2. **Navigate to Project Directory**

   ```bash
   cd geo-location-api
   ```

3. **Build the Project**

   ```bash
   mvn clean install
   ```

4. **Run the Application**

   ```bash
   java -jar target/geo-location-api-0.0.1-SNAPSHOT.jar
   ```

## Usage

### Once the application is running, you can access the Geo Location API endpoints using the following URLs:

   ```bask
   GET /api/v1/findMyLocation
   ```