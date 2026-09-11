# Document Upload API

A Spring Boot REST API for uploading documents using `MultipartFile` and `multipart/form-data`.

## Tech Stack

* Java 17 / 21
* Spring Boot
* Spring Web
* Maven
* SLF4J / Lombok
* REST API

## Features

* Upload documents using `multipart/form-data`
* Validate empty file uploads
* Store uploaded files in the local `uploads/` directory
* Log upload activity using SLF4J
* Return appropriate HTTP responses

## API

### Upload Document

**Endpoint**

```text
POST /documents/upload
```

**Content-Type**

```text
multipart/form-data
```

**Request**

Form-data:

| Key  | Type | Value                |
| ---- | ---- | -------------------- |
| file | File | Select your document |

Example:

```text
POST http://localhost:8080/documents/upload
```

## Example Response

Successful upload:

```text
File uploaded successfully: resume.pdf
```

If no file is provided:

```text
File is empty
```

## Project Structure

```text
DocumentUpload
│
├── src
│   └── main
│       └── java
│           └── ...
│               └── DocumentController.java
│
├── uploads
├── pom.xml
├── .gitignore
└── README.md
```

## How to Run

### 1. Clone the repository

```bash
git clone <your-github-repository-url>
```

### 2. Open the project

Open the project in IntelliJ IDEA or another Java IDE.

### 3. Run the application

Run the Spring Boot main class.

The application will start on:

```text
http://localhost:8080
```

### 4. Test the API

Use Postman:

```text
POST http://localhost:8080/documents/upload
```

Select:

```text
Body → form-data → file → File
```

Then select the document you want to upload.

## Important

Uploaded documents are stored locally and the `uploads/` directory should be excluded from Git using `.gitignore`.

```text
uploads/
target/
.idea/
*.iml
```
