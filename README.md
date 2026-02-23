# Product API Assessment

This repository contains a Spring Boot-based product management REST API. The API allows users to perform CRUD operations on products and items related to those products. Additionally, it includes JWT authentication for secure access to the API endpoints. The application also includes API versioning, Flyway database migrations, and Swagger-based API documentation.

## Table of Contents

1. [Technologies Used](#technologies-used)
2. [Project Setup](#project-setup)
   - [Prerequisites](#prerequisites)
   - [Build and Run](#build-and-run)
3. [Features Implemented](#features-implemented)
4. [API Versioning](#api-versioning)
5. [Swagger Documentation](#swagger-documentation)
6. [API Endpoints](#api-endpoints)
7. [Conclusion](#conclusion)

## Technologies Used

- **Spring Boot 3.2.5** - Framework for building the REST API.
- **Java 17** - The programming language version used for development.
- **Maven 3.8.7** or higher - Build tool for dependency management and project build automation.
- **JUnit 5 & Mockito** - Used for writing unit tests (however, the test cases were not included due to issues encountered).
- **JWT (JSON Web Token)** - Used for securing API endpoints.
- **MySQL** / **Flyway**  - Database used to store product and item information and Database migration tool to manage and apply schema changes.
- **Swagger/OpenAPI** - API documentation tool to generate interactive API docs.

## Project Setup

### Prerequisites

Before running the application, ensure you have the following installed on your local machine:

- **Java 17** (JDK 17)
- **Maven 3.8.7** or higher
- **Docker** (if running with Docker)
- **MySQL** (or use Docker to run MySQL)

### Build and Run

To build and run the application locally, follow the steps below:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/muralitharn/Technical_assessment_zest
   cd Technical_assessment_zest
   ```

2. **Build the project** using Maven (with skipping tests):
   ```bash
   mvn clean install -DskipTests
   ```

3. **Run the Spring Boot application**:
   ```bash
   mvn spring-boot:run
   ```

   By default, the application will run on [http://localhost:8080](http://localhost:8080).


## Features Implemented

- **Product CRUD Operations**: The API supports all basic CRUD operations for products (`GET`, `POST`, `PUT`, `DELETE`).
- **JWT Authentication**: The API is secured with JWT authentication. Users can log in to receive an access token and refresh token for accessing protected endpoints.
- **Role-based Authorization**: The API implements role-based access control using roles like `ADMIN` and `USER`.
- **API Versioning**: The API is versioned with `/api/v1` to maintain backward compatibility and allow for future updates.
- **Swagger/OpenAPI Documentation**: The API has auto-generated Swagger-based documentation for easy interaction and testing.
- **Flyway Database Migrations**: Flyway is configured to automatically manage database schema changes and migrations.

## API Versioning

The API follows the versioning strategy with the base path `/api/v1/`, ensuring that future versions of the API can coexist with existing versions. This helps maintain backward compatibility when changes are introduced in later versions.

Example of versioning in the base path:
- `GET /api/v1/products` - Fetch all products from version 1 of the API.
- `GET /api/v1/products/{id}` - Fetch a product by its ID in version 1.

You can upgrade to newer versions of the API by changing the version number in the URL path, e.g., `/api/v2/`.

## Swagger Documentation

Swagger is used for generating interactive API documentation that can be accessed through the following URL when the application is running:

**Swagger UI URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Swagger automatically generates documentation for all the available API endpoints and allows you to test them directly from the browser.


## API Endpoints

1. **Login**:
   - **Endpoint**: `POST /api/v1/auth/login`
   - **Description**: Authenticates the user and returns an access token and a refresh token.

2. **Refresh Token**:
   - **Endpoint**: `POST /api/v1/auth/refresh-token`
   - **Description**: Takes a refresh token and provides a new access token and refresh token.

3. **Get All Products**:
   - **Endpoint**: `GET /api/v1/products`
   - **Description**: Fetches a list of all products.

4. **Get Product by ID**:
   - **Endpoint**: `GET /api/v1/products/{id}`
   - **Description**: Fetches a product by its ID.

5. **Create Product**:
   - **Endpoint**: `POST /api/v1/products`
   - **Description**: Creates a new product (accessible only to `ADMIN` role).

6. **Update Product**:
   - **Endpoint**: `PUT /api/v1/products/{id}`
   - **Description**: Updates an existing product (accessible only to `ADMIN` role).

7. **Delete Product**:
   - **Endpoint**: `DELETE /api/v1/products/{id}`
   - **Description**: Deletes a product by its ID (accessible only to `ADMIN` role).

8. **Get Items by Product ID**:
   - **Endpoint**: `GET /api/v1/products/{id}/items`
   - **Description**: Fetches all items associated with a product.

"Note: Due to time constraints, as I started this assessment in the morning, I wasn't able to include the test cases as part of the deliverables."

## Conclusion

This project implements a simple but secure product management API with JWT-based authentication and MySQL integration. Docker and Docker Compose make it easy to deploy the application along with the MySQL database. The application can be extended further by adding more features such as product categorization, advanced search filters, and more.

---
