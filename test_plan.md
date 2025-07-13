# API Test Plan: Room & Booking Management System

## 1. Overview
This test plan covers **automated UI/API testing** for a hotel room management and booking system. The system allows creating rooms, adding bookings, retrieving them, and deleting them through REST APIs. The framework is built using **Selenium**,**Java**, **TestNG**, **RestAssured**, and custom `RoomAPI` and `BookingAPI` classes.

---

## 2. Objectives

- Validate the creation of rooms with various attributes.
- Test creation and retrieval of bookings for rooms.
- Verify booking details match submitted data.
- Ensure error handling and response codes are appropriate.
- Confirm deletions remove associated resources.

---

## 3. Tools and Framework

| Tool/Library      | Purpose                              |
|-------------------|---------------------------------------|
| Java              | Programming language                  |
| Selenium          | UI Automation                         |
| TestNG            | Test runner & assertion framework     |
| RestAssured       | API testing library                   |
| Json Path	        | JSON serialization/deserialization    |


---

## 4. API Endpoints Under Test

| Endpoint                   | Method | Description                        |
|----------------------------|--------|------------------------------------|
| `/api/room`                | POST   | Create a room                      |
| `/api/room/{name}`         | GET    | Retrieve room by name              |
| `/api/booking`             | POST   | Create a booking                   |
| `/api/booking`             | GET    | Retrieve bookings (by roomId)      |
| `/api/booking/{id}`        | GET    | Retrieve single booking by ID      |
| `/api/booking/{id}`        | DELETE | Delete booking by ID               |

---

## 5. Scope

### ✅ In Scope
- Room and booking creation with valid and edge-case inputs.
- Assertions on response codes and returned content.
- Verifying data integrity after create/retrieve operations.
- Handling multiple bookings for a room.
- Verifying deletion functionality.
- UI validation (frontend).

### ❌ Out of Scope

- Database or back-end integration not exposed via API.
- Performance or load testing.

---

## 6. Test Data

 supported using excel data provider classes
- **Rooms**:
  - Valid room names and types
  - Accessible: `true` / `false`
  - Features: e.g., WiFi, TV, Mini-bar

- **Bookings**:
  - Valid first/last names, emails, phone numbers
  - Date ranges (checkin < checkout)
  - With/without data

---

## 7. Test Cases

| Tests  | Description                                           | Input Data                                 | Expected Result                                                                 |
|--------|-------------------------------------------------------|--------------------------------------------|----------------------------------------------------------------------------------|
| API/UI  | Create a valid room                                   | Name, Type, Price, Features                | HTTP 200 Created; room retrievable with correct values                          |
| API/UI  | Create multiple bookings for a room                   | Multiple guest sets                        | Each booking returns HTTP 200; bookings are retrievable and match input         |
| API  | Retrieve bookings by room ID                          | Valid roomId                               | Returns list of bookings with correct roomId                                    |
| API  | Retrieve booking by booking ID                        | Valid bookingId                            | Booking details match what was submitted                                        |
| UI  | Booking with missing required fields                  | Missing first name/email                   | HTTP 400 Bad Request or validation failure                                      |
| UI  | Booking with invalid dates                            | Checkout date before checkin               | HTTP 400; error message                                                         |
| API  | Delete a booking and confirm deletion                 | BookingId                                  | HTTP 200;                                                                       |
                      |

---

## 8. Assertions Strategy

For each test, assert:
- HTTP Status Codes (200, etc.)
- Non-null and expected structure of response
- Field-level correctness (e.g., name, email, dates)

---

## 9. Test Data Provider Integration

Use TestNG’s `@DataProvider` to supply:
- Multiple room combinations
- Booking test sets per room
- Invalid inputs for negative tests

---

## 10. Execution Strategy

- Group tests using TestNG groups: `@Test(groups = {"smoke", "regression", "negative"})`
- Use parallel execution for bookings via `parallel = true` in DataProvider if needed
- Run daily in CI pipeline (GitHub Actions, Jenkins, etc.)

---

## 11. Reporting & Logging

- Log all request/response bodies using RestAssured’s `.log().all()`
- Generate reports with TestNG default reports or Allure (optional)
- Capture failed test details and response bodies

---

## 12. Risks and Mitigations

| Risk                               | Mitigation                                     |
|------------------------------------|------------------------------------------------|
| API changes without notice         | Add contract tests or schema validation        |
| Backend latency affecting test     | Add retry logic or increase timeouts           |
| Booking overlap not allowed        | Use unique dates per test to avoid collision   |

---

## ✅ Summary
This test plan supports a scalable, maintainable API test suite focused on CRUD operations for rooms and bookings. It leverages strong assertions, clean test separation, and reusable APIs.

