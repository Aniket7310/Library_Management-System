## 📚 Library Management System

*A complete Java-based library management system that allows administrators and users to manage books, issue/return records, and user accounts efficiently. The project follows proper modular structure, database connectivity (JDBC), and clean UI.*


## 🚀 Features
### 🔐 User Module

- Login & Registration
- Browse available books
- Search books by title, author, or category
- Request book issue
- View issued books and return status

### 🛠 Admin Module

- Add / Update / Delete books
- View all books
- Manage users
- Approve issue/return requests
- View daily reports

### 💾 Database

- MySQL or SQLite supported
- JDBC integrated
- Tables included:
- users
- books
- issue_records
- admins

### 📂 Project Structure

```
Library_Management_System/
│── src/
│   ├── model/
│   │     ├── Book.java
│   │     ├── User.java
│   │     ├── IssueRecord.java
│   ├── dao/
│   │     ├── BookDao.java
│   │     ├── UserDao.java
│   │     ├── IssueDao.java
│   ├── ui/
│   │     ├── LoginPage.java
│   │     ├── AdminDashboard.java
│   │     ├── UserDashboard.java
│   ├── Main.java
│
├── database/
│     └── library.sql
│
├── README.md
└── LICENSE
```

## 🗄 Database Setup

**1.** Open MySQL/SQLite

**2.** Run the SQL file located in:
```bash
database/library.sql
```

**3.** Update your DB configuration in:
```bash
src/dao/DBConnection.java
```
**Example:**
```bash
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/librarydb",
    "root",
    "password"
);
```

## 🧑‍💻 How to Run

### ▶ Compile
```
javac -cp ".;lib/mysql-connector-j.jar" src\Main.java
```
### ▶ Run
```
java -cp ".;lib/mysql-connector-j.jar" Main
```
## Login Page

![image alt](https://github.com/Aniket7310/Library_Management-System/blob/5098518c4d5c9b4ad1a22172eebfcec6d53276e2/Login.jpeg)



