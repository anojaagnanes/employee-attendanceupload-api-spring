# Employee Attendance Upload System

## Overview

The Employee Attendance Upload System is a Spring Boot backend application that enables users to upload an Excel sheet containing attendance records, store the data in a MySQL database, and filter attendance records based on search queries. The frontend is built with React (TypeScript) and displays the data in a tabular format with filtering options.

## Tech Stack

**Backend:** Spring Boot, Java, JPA (Hibernate), Apache POI (Excel processing)

**Frontend:** React.js (TypeScript)

**Database:** MySQL

**DevOps:** GitLab

## Features

* Upload an Excel file containing employee attendance data.
* Store the parsed data into a MySQL database.
* Retrieve all attendance records via a REST API.
* Filter attendance records by name, email, and attendance type (Physical, Online, Absent).
* EmpId	TeamRole Email	Stack	  Destination	Name	Tier	AttendanceDate	AttendanceType
  ![image](https://github.com/user-attachments/assets/a66384cb-ca15-4b4f-8f00-a81db0331b45)




  

## Project Structure

├── src/main/java/com/sdp/wdm/employee_attendance_upload
│   ├── controller
│   │   ├── AttendanceController.java
│   ├── entity
│   │   ├── AttendanceFile.java
│   ├── repository
│   │   ├── AttendanceFileRepository.java
│   ├── service
│   │   ├── impl
│   │   │   ├── FileStorageService.java
│   ├── utility
│   │   ├── ExcelHelper.java
│   ├── Application.java
├── src/main/resources
│   ├── application.properties
├── pom.xml

## Setup and Installation

### Prerequisites

* Java 17+
* MySQL
* Maven


### Backend Setup

1.  Clone the repository:

    ```bash
    git clone [https://github.com/your-repo/employee-attendance-upload.git](https://github.com/your-repo/employee-attendance-upload.git)
    cd employee-attendance-upload
    ```

2.  Configure MySQL in `application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/attendance_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  Build and run the Spring Boot application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
   
2.  Install dependencies:

    ```bash
    npm install
    ```

3.  Start the React application:

    ```bash
    npm start
    ```

## API Endpoints

### Upload Attendance File

* **POST** `/api/attendance/upload`
    * **Request:** Multipart file (file parameter)
    * **Response:** Success message

### Get Filtered Attendance Records

* **GET** `/api/attendance/filter`
    * **Query Parameters:**
        * `searchQuery` (optional): Filter by name or email
        * `attendanceType` (optional): Filter by attendance type (Physical, Online, Absent)
    * **Response:** List of filtered attendance records

## License

This project is licensed under the MIT License.

## Author

Anojaa - Developer of the Employee Attendance Upload System 🚀

