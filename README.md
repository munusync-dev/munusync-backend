# Munusync Backend

A Spring Boot application for the Munusync project.

## Prerequisites

- **Java 21** or higher
- **Maven 3.6+** (or use the included Maven wrapper)
- **IDE**: VS Code with Java extensions or IntelliJ IDEA

## Project Structure

```
munusync-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/munusync/backend/
│   │   │       └── MunusyncApplication.java
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
├── pom.xml
├── mvnw (Maven wrapper for Unix)
├── mvnw.cmd (Maven wrapper for Windows)
└── README.md
```

## Running the Application

### 1. Terminal/Command Line

#### Unix/Linux/macOS

```bash
# Using Maven wrapper (recommended)
./mvnw spring-boot:run

```

#### Windows

```cmd
# Using Maven wrapper (recommended)
mvnw.cmd spring-boot:run
```

### 2. VS Code

#### Prerequisites

Make sure you have the following VS Code extensions installed:

- **Extension Pack for Java** (includes Language Support for Java, Debugger for Java, etc.)
- **Spring Boot Extension Pack** (includes Spring Boot Tools, Spring Boot Dashboard, etc.)

#### Method 1: Using Spring Boot Dashboard

1. Open the project in VS Code
2. In the Explorer panel, look for the **Spring Boot Dashboard** section
3. Click on the `munusync-backend` application
4. Click the **Run** (▶️) button

#### Method 2: Using Run and Debug Panel

1. Press `Ctrl+Shift+D` (or `Cmd+Shift+D` on macOS) to open Run and Debug
2. Select one of the available configurations:
   - `Run MunusyncApplication`
   - `Debug MunusyncApplication`
3. Click the **Run** (▶️) button

#### Method 3: Using Command Palette

1. Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on macOS)
2. Type "Spring Boot Dashboard"
3. Select "Spring Boot Dashboard: Open"
4. Run the application from the dashboard

#### Method 4: Using Code Lens

1. Open `MunusyncApplication.java`
2. Look for "Run | Debug" code lens above the `main` method
3. Click **Run**

### 3. IntelliJ IDEA

#### Method 1: Using Run Configuration

1. Open the project in IntelliJ IDEA
2. Wait for Maven import to complete
3. Right-click on `MunusyncApplication.java`
4. Select **Run 'MunusyncApplication.main()'**

#### Method 2: Using Spring Boot Run Configuration

1. Go to **Run** → **Edit Configurations**
2. Click **+** → **Spring Boot**
3. Set:
   - **Name**: `MunusyncApplication`
   - **Main class**: `com.munusync.backend.MunusyncApplication`
   - **Module**: `backend`
4. Click **OK**
5. Click the **Run** (▶️) button in the toolbar

#### Method 3: Using Maven Panel

1. Open the **Maven** panel (usually on the right side)
2. Navigate to `backend` → **Plugins** → **spring-boot**
3. Double-click on **spring-boot:run**

#### Method 4: Using Terminal in IntelliJ

1. Open the terminal in IntelliJ (`Alt+F12`)
2. Run the Maven command:

   ```bash
   # Unix/macOS
   ./mvnw spring-boot:run

   # Windows
   mvnw.cmd spring-boot:run
   ```

## Building the Application

### Unix/Linux/macOS

```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package (creates JAR file)
./mvnw clean package

# Run the JAR file
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### Windows

```cmd
# Clean and compile
mvnw.cmd clean compile

# Run tests
mvnw.cmd test

# Package (creates JAR file)
mvnw.cmd clean package

# Run the JAR file
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## Troubleshooting

### Common Issues

1. **"Could not find or load main class" error**

   - Solution: Make sure the project is compiled first using `./mvnw clean compile`

2. **VS Code shows "non-project file" error**

   - Solution: Reload the Java projects in VS Code:
     - Press `Ctrl+Shift+P`
     - Type "Java: Reload Projects"
     - Select and execute the command

3. **Port already in use (default: 8080)**

   - Solution: Change the port in `application.yaml`:
     ```yaml
     server:
       port: 8081
     ```

4. **Maven wrapper permission denied (Unix)**
   - Solution: Make the wrapper executable:
     ```bash
     chmod +x mvnw
     ```

### Verification

Once the application starts successfully, you should see output similar to:

```
Started MunusyncApplication in X.XXX seconds (JVM running for X.XXX)
```

The application will be available at: `http://localhost:8080`

## Development

### Hot Reload (Development Mode)

For development with automatic restart on file changes, use:

```bash
# Unix/macOS
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev"

# Windows
mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev"
```

### Debug Mode

To run in debug mode, add the debug flag:

```bash
# Unix/macOS
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Windows
mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

Then attach your IDE debugger to port 5005.

## Dependencies

- **Spring Boot 3.5.0**
- **Spring Web** - For REST API endpoints
- **Lombok** - For reducing boilerplate code
- **Java 21** - Language version

## License

[Add your license information here]
