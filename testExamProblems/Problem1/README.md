# Problem 1: Enterprise Reporting System

## Problem Statement

An enterprise reporting system integrates with multiple databases (MySQL, Oracle) and can generate reports in different formats (PDF, HTML). The client application must deal with complex APIs for database connectivity and multiple report generators.

## Design Pattern Solution: Abstract Factory

### Why Abstract Factory?

The Abstract Factory pattern is the best solution because:

1. **Family of Related Objects**: We need to create families of related objects (database connection + report generator) that must work together consistently
2. **Platform Independence**: The client should not know about concrete database or report format implementations
3. **Consistency Guarantee**: Ensures MySQL connections always use MySQL-compatible report generators, and Oracle connections use Oracle-compatible ones
4. **Easy Extension**: Adding new databases (PostgreSQL) or formats (XML) doesn't require changing client code

### Solution Overview

- **Abstract Factory Interface**: `ReportFactory` with methods to create database connections and report generators
- **Concrete Factories**: `MySQLReportFactory` and `OracleReportFactory` that create matching pairs
- **Abstract Products**: `DatabaseConnection` and `ReportGenerator` interfaces
- **Concrete Products**: MySQL/Oracle connections and PDF/HTML generators

The client code works only with abstract interfaces, ensuring it can switch between database/report combinations without modification.

### Key Benefits

- Client code is decoupled from concrete implementations
- Ensures database and report format compatibility
- Easy to add new database/report combinations
- Maintains consistency across the system

## Reference

For more details on the Abstract Factory pattern, see: [../../AbstractFactory/README.md](../../AbstractFactory/README.md)

