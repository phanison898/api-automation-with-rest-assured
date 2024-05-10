### API Automation Framework

![](https://img.shields.io/github/languages/code-size/phanison898/api-automation-with-rest-assured?style=flat-square)
![](https://img.shields.io/github/last-commit/phanison898/api-automation-with-rest-assured?style=flat-square)
![](https://img.shields.io/github/languages/top/phanison898/api-automation-with-rest-assured?style=flat-square)
![](https://img.shields.io/github/license/phanison898/api-automation-with-rest-assured?style=flat-square)

---

#### 🚥 About

API automation framework developped using Rest-Assured, Java, TestNG and Maven
---

#### 📜 Tools

- Programming Language  : Java
- Test Framework        : TestNG
- Built Tool            : Maven
- Development Type      : Test Driven Development (TDD)
- Extras                : Configured local REST API server using json-server node application

---

#### ⛔ Pre-requisites

1. **Appium** installation
   - Install Java JDK
   - Install Maven
   - Install json-server using Node.js
---

#### ✅ How to make use of this repo?

1. Clone the repository
   ```bash
   git clone https://github.com/phanison898/api-automation-with-rest-assured.git
   ```
2. CD into cloned directory
   ```bash
   cd /api-automation-with-rest-assured
   ```
3. Start the dummy REST API server
   ```bash
   json-server --watch src/main/resources/json/db.json
   ```
4. Run the Maven command and start the automation

   ```bash
       mvn clean test
   ```
---
