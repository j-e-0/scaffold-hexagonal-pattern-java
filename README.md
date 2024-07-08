# Hexagonal Java Project

This project is an example of implementing a hexagonal architecture using Java and Spring Boot. It includes a basic setup for user and product management, demonstrating principles of SOLID and clean architecture.

## Features

- **Hexagonal Architecture**: The project follows the hexagonal architecture pattern to separate the core logic from the infrastructure.
- **Spring Boot**: Utilizes Spring Boot for application setup and configuration.
- **H2 Database**: Uses H2 in-memory database for data persistence.
- **RESTful API**: Provides a RESTful API for managing users and products.
- **DTO Layer**: Includes a Data Transfer Object (DTO) layer for data validation and transfer.
- **JUnit Tests**: Comprehensive tests for application, domain, and infrastructure layers.

## Technologies Used

- **Java 22**: Core programming language.
- **Spring Boot 3.1.1**: Framework for building the application.
- **H2 Database**: In-memory database for development and testing.
- **JUnit 5**: Framework for unit and integration testing.

## Project Structure

```
hexagonal-java-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── example/
│   │   │   │       ├── application/
│   │   │   │       │   ├── controller/
│   │   │   │       │   └── dto/
│   │   │   │       ├── domain/
│   │   │   │       │   ├── model/
│   │   │   │       │   ├── service/
│   │   │   │       │   └── port/
│   │   │   │       ├── infrastructure/
│   │   │   │       │   ├── adapter/
│   │   │   │       │   ├── entity/
│   │   │   │       │   ├── mapper/
│   │   │   │       │   └── repository/
│   │   │   │       └── HexagonalJavaApplication.java
│   ├── resources/
│   │   └── application.properties
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── application/
│   │   │           │   ├── controller/
│   │   │           ├── domain/
│   │   │           │   ├── service/
│   │   │           ├── infrastructure/
│   │   │           │   ├── adapter/
│   │   │           │   └── repository/
├── pom.xml
```

### Folder Responsibilities

- **application**: Contains the application's entry points, such as controllers and DTOs.
  - **controller**: Defines RESTful API endpoints.
  - **dto**: Data Transfer Objects used for data validation and transfer between layers.

- **domain**: Contains the core business logic and domain models.
  - **model**: Defines the main business entities.
  - **service**: Implements the business logic and use cases.
  - **port**: Defines interfaces for the application to interact with the infrastructure (e.g., repository interfaces).

- **infrastructure**: Contains the implementation details and external system integrations.
  - **adapter**: Implements the interfaces defined in the domain layer, providing the actual interaction with the database.
  - **entity**: Defines the JPA entities corresponding to the database tables.
  - **mapper**: Contains mappers to convert between domain models and entities.
  - **repository**: JPA repository interfaces for data access.

- **HexagonalJavaApplication.java**: The main class that starts the Spring Boot application.

## Getting Started

### Prerequisites

- Java 22
- Maven 3.9.8

### Installation

1. Clone the repository:

```bash
git clone https://github.com/j-e-0/scaffold-hexagonal-pattern-java
cd scaffold-hexagonal-pattern-java
```

2. Build the project:

```bash
mvn clean install
```

3. Run the application:

```bash
mvn spring-boot:run
```

### Configuration

The application is configured to use the H2 in-memory database by default. The configuration can be found in the `src/main/resources/application.properties` file.

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```

### Running Tests

To run the tests, use the following command:

```bash
mvn test
```

## API Endpoints

The project exposes the following RESTful API endpoints:

### Users

- `GET /api/v1/users` - Get all users
- `GET /api/v1/users/{id}` - Get user by ID
- `POST /api/v1/users` - Create a new user
- `DELETE /api/v1/users/{id}` - Delete user by ID

### Products

- `GET /api/v1/products` - Get all products
- `GET /api/v1/products/{id}` - Get product by ID
- `POST /api/v1/products` - Create a new product
- `DELETE /api/v1/products/{id}` - Delete product by ID

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
