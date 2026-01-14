

````markdown
# 🚗 Parking System Java

A robust **Parking Management System** built in Java that simulates real-world parking operations such as vehicle entry, exit, ticketing, fee calculation, payment handling, and parking spot management — with **multi-threading support** for concurrent access.

---

## 📌 Overview

This project models a real parking lot where **multiple cars can enter and exit simultaneously**.  
To reflect real-world conditions, the system integrates **Java multithreading** to ensure data consistency, correctness, and performance under concurrent usage.

The architecture follows clean **OOP principles**, DAO patterns, and service layers.

---

## 🧠 Features

- Car entry with ticket generation  
- Car exit with automatic fee calculation  
- Payment creation and persistence  
- Parking spot allocation & release  
- Thread-safe operations  
- DAO & Service layer separation  
- Extensible and maintainable design  

---

## 🧵 Concurrency & Threading

### Why Threads?

In real parking systems:
- Multiple cars may enter at the same time
- Multiple exits and payments may happen simultaneously
- Parking spots must not be double-assigned

Threads are used to **simulate and safely manage these scenarios**.

---

### 🛡 Thread Safety Mechanisms

The system uses:
- `synchronized` methods / blocks  
- Controlled access to shared resources (spots, tickets)
- Atomic operations in critical sections

This prevents:
- Race conditions  
- Double spot assignment  
- Data corruption  
- Inconsistent payments  

---

### 🧪 Example Concurrent Scenario

```java
Thread car1 = new Thread(() -> parkCar(carA));
Thread car2 = new Thread(() -> parkCar(carB));

car1.start();
car2.start();
````

Both cars attempt to park **simultaneously**, while the system guarantees safe spot allocation.

---

## 🗂 Project Structure

```
parking-system-java
├── src
│   └── main/java/com/najim
│       ├── dao          // Thread-safe data access logic
│       ├── model        // Entities (Car, Ticket, Payment, Spot)
│       ├── service      // Business logic & concurrency handling
│       ├── thread       // Thread simulation
│       └── util         // Utilities & helpers
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

* Java 11+
* Maven

---

### 📥 Installation

```bash
git clone https://github.com/NAJIMx0/parking-system-java
cd parking-system-java
mvn clean install
```

---

## ▶ Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.najim.App"
```

(Replace the main class if different.)

---

## 🔁 Exit Process Flow

1. Retrieve car by plate number
2. Fetch active ticket
3. Calculate parking duration
4. Generate payment
5. Free parking spot (thread-safe)
6. Persist changes

---

## 🛠 Design Principles

* Object-Oriented Programming (OOP)
* Separation of Concerns
* DAO Pattern
* Service Layer
* Thread Safety & Concurrency Control

---

## 📈 Future Enhancements

* ExecutorService thread pool
* Concurrent collections (`ConcurrentHashMap`)
* Database transactions
* REST API (Spring Boot)
* Real-time monitoring dashboard

---

## 🤝 Contributions

Contributions are welcome.

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

## 📄 License

Open-source project for learning and educational purposes.

---

## 👨‍💻 Author

**Najim**
Software Engineering Student | Java Developer
GitHub: [https://github.com/NAJIMx0](https://github.com/NAJIMx0)

```
