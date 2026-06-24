# Bajaj Finserv Health - Qualifier 1 API

A REST API built with **Java Spring Boot** for the Bajaj Finserv Health Developer Challenge - Qualifier 1.

---

## Developer Details

| Field | Value |
|-------|-------|
| Name | Aditya Kochhar |
| Roll Number | 2310991511 |
| Email | aditya1511.be23@chitkara.edu.in |
| College | Chitkara University |

---

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.2.5
- **Build Tool:** Maven
- **Deployment:** Render (Docker)

---

## Live API

**Base URL:** `https://bajaj-qualifier-1-hl0p.onrender.com`

> Note: Free tier on Render spins down after inactivity. First request may take ~50 seconds to wake up.

---

## Endpoints

### 1. POST /bfhl

Processes an array of strings and returns categorized results.

- **Method:** `POST`
- **URL:** `https://bajaj-qualifier-1-hl0p.onrender.com/bfhl`
- **Content-Type:** `application/json`

**Request Body:**
```json
{
    "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response:**
```json
{
    "is_success": true,
    "user_id": "aditya_kochhar_13122005",
    "email": "aditya1511.be23@chitkara.edu.in",
    "roll_number": "2310991511",
    "odd_numbers": ["1"],
    "even_numbers": ["334", "4"],
    "alphabets": ["A", "R"],
    "special_characters": ["$"],
    "sum": "339",
    "concat_string": "Ra"
}
```

---

### 2. GET /health

Health check endpoint to verify the server is running.

- **Method:** `GET`
- **URL:** `https://bajaj-qualifier-1-hl0p.onrender.com/health`

**Response:**
```json
{
    "status": "OK"
}
```

---

## API Logic

| Field | Logic |
|-------|-------|
| `user_id` | `fullname_ddmmyyyy` in lowercase |
| `odd_numbers` | Elements that are numbers and odd |
| `even_numbers` | Elements that are numbers and even |
| `alphabets` | Elements with only letters, returned in uppercase |
| `special_characters` | Everything that is not a number or alphabet |
| `sum` | Sum of all numeric elements, returned as string |
| `concat_string` | All alphabetical characters collected → reversed → alternating caps (uppercase at even index) |
| `is_success` | `true` for valid requests, `false` for errors |

---

## Project Structure

```
src/
├── main/java/com/bajaj/
│   ├── BajajApplication.java          # Entry point
│   ├── controller/
│   │   └── BfhlController.java        # POST /bfhl and GET /health
│   ├── dto/
│   │   ├── BfhlRequest.java           # Request DTO
│   │   └── BfhlResponse.java          # Response DTO
│   ├── exception/
│   │   └── GlobalExceptionHandler.java # Error handling
│   └── service/
│       ├── BfhlService.java           # Service interface
│       └── BfhlServiceImpl.java       # Business logic
└── test/java/com/bajaj/service/
    └── BfhlServiceTest.java           # 16 test cases
```

---

## Test Cases

### Example A
**Input:** `["a", "1", "334", "4", "R", "$"]`
**Output:** odd `["1"]`, even `["334","4"]`, alphabets `["A","R"]`, special `["$"]`, sum `"339"`, concat `"Ra"`

### Example B
**Input:** `["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]`
**Output:** odd `["5"]`, even `["2","4","92"]`, alphabets `["A","Y","B"]`, special `["&","-","*"]`, sum `"103"`, concat `"ByA"`

### Example C
**Input:** `["A", "ABCD", "DOE"]`
**Output:** alphabets `["A","ABCD","DOE"]`, sum `"0"`, concat `"EoDdCbAa"`

---

## How to Run Locally

### Prerequisites
- Java 17+
- Maven 3.9+

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/adityakochhar/Bajaj-Qualifier-1.git
cd Bajaj-Qualifier-1
```

**2. Build the project**
```bash
mvn clean package -DskipTests
```

**3. Run the server**
```bash
java -jar target/bajaj-qualifier-1.0.0.jar
```

Server starts at `http://localhost:8080`

**4. Run tests**
```bash
mvn test
```

---

## Postman Testing

### POST /bfhl
Set method to `POST`, URL to the endpoint, Body → raw → JSON and paste the request body.

### GET /health
Set method to `GET`, URL to the health endpoint. No body required.

---

## Error Handling

| Scenario | Status Code | Response |
|----------|-------------|----------|
| Missing `data` field | `400` | `{"is_success": false, "error": "data field is required"}` |
| Server error | `500` | `{"is_success": false, "error": "<message>"}` |
