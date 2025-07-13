# 🧪 Booking Platform Test Automation Framework

This project is an end-to-end test automation suite built for validating the **Booking Web Application**, covering both **UI** and **API** functionalities.

It uses the Page Object Model (POM) design pattern, integrates Allure Reporting, Jasypt encryption for secure credentials, and supports data-driven testing via Excel.

---

## 📌 Features

- ✅ **UI Automation** with Selenium & TestNG
- ✅ **API Testing** using REST Assured
- ✅ **Page Object Model** for maintainable code
- ✅ **Allure Reports** with screenshots on failure
- ✅ **TestNG Listeners** for dynamic logging and screenshot capture
- ✅ **Excel DataProvider** for room and booking test data
- ✅ **Jasypt**-encrypted credentials with auto-decryption
- ✅ **Modular utility classes** (CryptoUtil, ExcelReader, ConfigReader)
- ✅ **Environment agnostic** (can run locally or in CI/CD)


---

## 🧪 Sample Tests

- 🔹 **testBooking**: Book a room from front page
- 🔹 **testCreateBookingFromAdmin**: Admin creates room, then makes a booking
- 🔹 **Validation tests**: Required field checks
- 🔹 **No rooms available**: Assert empty search results


---

## 📷 Screenshot on Failure

When any test fails:
- Screenshot is auto-captured via TestNG listener
- Embedded into the Allure report
- Optional: saved in `/screenshots` directory

---

## 🔐 Credential Management

- Encrypted using **Jasypt**
- Stored in `config.properties`:
  
## properties
admin.username=ENC(abc123encrypted)
admin.password=ENC(def456encrypted)
Auto-decrypted by ConfigReader at runtime
--
## 🔧 Prerequisites

- **Java JDK 21**
- **Maven** 
- IDE like **IntelliJ IDEA** or **Eclipse**
- Browsers: Chrome, Firefox, Edge


---

## Framework Structure
- **base**: Base test and driver setup classes using ThreadLocal for parallel execution.
- **pages**: Page Object Model classes encapsulating UI interactions and assertions.
- **api**: API client classes for backend verification and setup.
- **model**: POJO models for data transfer.
- **utils**: Utility classes including encryption, Excel reading, and config management.
- **webtests**: Test classes covering high business value flows.

## 📦 Getting Started

### 1. Clone the repository

```bash

git clone https://github.com/SumaKondapaneni/SeleniumTestNGFramework.git

Then, navigate into the project folder:

cd SeleniumTestNGFramework
```

### 2. Install dependencies

**Using Maven:**

```bash
mvn clean install
```

### 3. Configure browser drivers

 Use **WebDriverManager**.

### 4. Set up environment configuration

Edit the `config.properties` or `testng.xml` file to update:
- Base URLs
- Browser type
- Encrypted user id and password 

use CryptoUtil to encrypt/decrypt 

---
### 🔐 CryptoUtil – Usage Instructions

The CryptoUtil class provides simple encryption and decryption capabilities using the Jasypt library.

📦 Prerequisites
Java 8 or higher
Jasypt dependency (include in your pom.xml for Maven):
<dependency>
    <groupId>org.jasypt</groupId>
    <artifactId>jasypt</artifactId>
    <version>1.9.3</version>
</dependency>
⚙️ Supported Operations

Encrypt a plaintext string
Decrypt an encrypted string (Jasypt format)
🚀 How to Use

✅ Set Secret Key
The utility requires a secret key, which can be provided via an environment variable or passed as an argument.

# Linux/macOS
export JASYPT_SECRET=mySecretKey

# Windows CMD
set JASYPT_SECRET=mySecretKey
🔒 Encrypt a Value
java CryptoUtil encrypt mySecretValue
Output:

Encrypted: ENC(Ad98gLk89jH=)
⚠️ The result is automatically wrapped in ENC(...) to match common usage in configuration files.

Decrypt a Value
java CryptoUtil decrypt Ad98gLk89jH=

Output:

Decrypted: mySecretValue
⚠️ When decrypting, do not include the ENC() wrapper — only pass the internal encrypted string.
---
## ▶️ Running Tests

### Using Maven:

```bash

mvn clean test

```

### From your IDE:
- Right-click the test class or `testng.xml`
- Run as TestNG Test


### Generate and serve the Allure report:
```bash
mvn allure:serve

This command will generate the Allure report from the test results and automatically open it in your default browser.

If you want to just generate the report without opening the browser, use:

mvn allure:report
```


---

## 📁 Project Structure

```
UI-automation/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── api/                 # API client classes (BookingAPI, RoomAPI, etc.)
│   │   │   ├── base/                # Base classes (TestBase, DriverFactory, etc.)
│   │   │   ├── model/               # POJO models (Booking, BookingDates, Room, etc.)
│   │   │   ├── pages/               # Page Object Model classes (HomePage, BookingPage, etc.)
│   │   │   ├── utils/               # Utilities (ConfigReader, CryptoUtil, ExcelReader, etc.)
│   │   │
│   │   └── resources/
│   │       ├── config.properties    # Configurations & encrypted credentials
│   │       └── roomData.xlsx        # Test data for rooms and bookings
│   │
│   └── test/
│       └── java/
│           ├── webtests/            # UI Test classes (UITests, BookingUITests, etc.)
│           └── apitests/            # API Test classes (BookingAPITests, RoomAPITests, etc.)
│
├── pom.xml                         # Maven build file with dependencies
├── README.md                       # Project overview and instructions
└── .gitignore                     # Files and folders to ignore in Git
```

---

## 🧪 Usage Tips

- Create page objects for each screen under `pages/`.
- Write test classes using TestNG annotations:
  - `@BeforeMethod`, `@AfterMethod`, `@Test`, etc.
- can use `@DataProvider` or external files for test data.
- Keep all config and test data outside code for reusability.
- Use loggers and reporting hooks for maintainability.

---

## 📊 Reporting

- **TestNG** generates:
 	 - HTML and XML reports under `test-output/`
- **Allure Reports** (optional, for rich UI and insights):
  - Add Allure dependencies
  - Run:
    ```bash
    allure serve target/allure-results
    ```

---

## 📚 Dependencies and Versions

| Dependency           | Version      |
|----------------------|--------------|
| Selenium WebDriver   | 4.33.0       |
| TestNG               | 7.11.0       |
| Allure TestNG        | 2.25.0       |
| RestAssured          | 5.4.0        |
| JsonPath             | 5.4.0        |
| WebDriverManager     | 5.7.1        |


See `pom.xml` for the complete list.






