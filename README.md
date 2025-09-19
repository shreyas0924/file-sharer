# P2P File Sharing Application

A peer-to-peer file sharing application that allows users to share files directly between devices using a simple code system.

## Project Structure

- `src/main/java/p2p`: Java backend code
  - `App.java`: Main application entry point
  - `controller/`: API controllers
  - `service/`: Business logic services
  - `utils/`: Utility classes
- `ui/`: Next.js frontend application
  - `src/app`: Next.js app router pages
  - `src/components`: React components

## Features

- Drag and drop file upload
- File sharing via invite codes (port numbers)
- File downloading using invite codes
- Modern, responsive UI
- Direct peer-to-peer file transfer

## Prerequisites

- Java 11+ (for the backend)
- Node.js 18+ and npm (for the frontend)
- Maven (for building the Java project)

## Getting Started

### Docker Setup

#### Linux/macOS/Windows:
```bash
docker compose up 
```

### Manual Setup

#### Backend Setup

1. Build the Java project:
   ```bash
   cd backend
   mvn clean package
   ```

2. Run the backend server:
   ```bash
   java -jar target/{name}.jar
   ```

   The backend server will start on port 8080.

#### Frontend Setup

1. Install dependencies:
   ```bash
   cd client
   npm install
   ```

2. Run the development server:
   ```bash
   npm run dev
   ```

   The frontend will run at [http://localhost:3000](http://localhost:3000).

## How It Works

1. **File Upload**:
   - User uploads a file through the UI
   - The file is sent to the Java backend
   - The backend assigns a unique port number (invite code)
   - The backend starts a file server on that port

2. **File Sharing**:
   - The user shares the invite code with another user
   - The other user enters the invite code in their UI

3. **File Download**:
   - The UI connects to the specified port
   - The file is transferred directly from the host to the recipient

## Architecture

<img width="1744" height="688" alt="image" src="https://github.com/user-attachments/assets/ad3b02e2-85e6-4ec1-806a-f712e602f146" />


