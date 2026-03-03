# Java Employee Payroll Service

A Java-based Employee Payroll Service demonstrating File IO operations, Watch Service, and Console IO using Git Flow branching strategy.

## Project Structure

```
Java-Employee-Payroll-Service/
├── src/
│   ├── main/java/com/payroll/
│   │   ├── EmployeePayrollData.java        # UC1 - Employee data model
│   │   ├── EmployeePayrollService.java     # UC1/UC4/UC5/UC6 - Core service
│   │   ├── EmployeePayrollFileIOService.java # UC4/UC5/UC6 - File IO
│   │   └── Java8WatchServiceExample.java  # UC3 - Watch Service
│   └── test/java/com/payroll/
│       ├── NIOFileAPITest.java             # UC2 - NIO File tests
│       ├── WatchServiceTest.java           # UC3 - Watch Service tests
│       └── EmployeePayrollServiceTest.java # UC4/UC5/UC6 - Payroll tests
└── pom.xml
```

## Use Cases

| Branch | Description |
|--------|-------------|
| `UC1-Payroll-System` | Console-based Employee Payroll Read/Write |
| `UC2-File-Operations` | NIO File API JUnit Tests |
| `UC3-Watch-Service` | Java 8 File Watch Service |
| `UC4-Write-Payroll-To-File` | Write Employee Payroll to File |
| `UC5-Print-And-Count-Payroll` | Print Payroll and Count Entries |
| `UC6-Read-Payroll-File` | Read Payroll File for Analysis |

## Git Flow

- `master` — README only
- `develop` — integration branch (all UCs merged here)
- `feature/UCx-*` — individual feature branches (remain after merge)

## Prerequisites

- Java 8+
- Maven 3.x

## Run

```bash
mvn test
```
