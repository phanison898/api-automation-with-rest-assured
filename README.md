### API Automation using REST Assured Java Library

#### 1. Prerequisites

- Node
- Java
- Maven

### 2. Setup Fake API server

- Install json-server node package

```bash
    npm install -g json-server
```

- Navigate src/java/resources folder and create db.json file

- Run the server

```bash
   json-server --watch db.json

```

#### 3. Run Maven tests

```bash
mvn clean test
```
