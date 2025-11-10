# Problem 1: Enterprise Reporting System

## Problem Statement

An enterprise reporting system integrates with multiple databases (MySQL, Oracle) and can generate reports in different formats (PDF, HTML). The client application must deal with complex APIs for database connectivity and multiple report generators.

## Design Pattern Solution: Simple Factory

### Why Simple Factory (Not Abstract Factory)?

**Key Analysis:**
- **DatabaseConnection** and **ReportGenerator** are **independent products**
- Report formats (PDF, HTML) are **not database-specific** - they work with any database
- There is **no family relationship** that needs to be enforced
- Both MySQL and Oracle factories create the **same** report generator types

**Abstract Factory is for:**
- Creating **families of related products** that must work together
- Ensuring products come from the same "family" (e.g., all Windows widgets or all Mac widgets)
- Example: Button + Checkbox must both be from Windows or both from Mac

**Simple Factory is sufficient when:**
- Products are independent (database type doesn't affect report format)
- You just need to create different types of objects
- No need to enforce family consistency

### Why Simple Factory?

The Simple Factory pattern is the best solution because:

1. **Independent Products**: Database connections and report generators are independent - any database can use any report format
2. **Single Product Types**: Each factory creates one type of product (database OR report), not families
3. **Simplicity**: No need for complex factory hierarchies when products are independent
4. **Easy Extension**: Adding new databases or formats is straightforward

### Solution Overview

- **Simple Factory for Databases**: `DatabaseConnectionFactory` creates MySQL or Oracle connections
- **Simple Factory for Reports**: `ReportGeneratorFactory` creates PDF or HTML generators
- **Abstract Products**: `DatabaseConnection` and `ReportGenerator` interfaces
- **Concrete Products**: MySQL/Oracle connections and PDF/HTML generators

The client code uses both factories independently to create database connections and report generators as needed.

### Key Benefits

- Client code is decoupled from concrete implementations
- Simple and straightforward - no unnecessary complexity
- Products can be created independently
- Easy to add new database types or report formats

### When Would Abstract Factory Be Needed?

Abstract Factory would be appropriate if:
- Report generators were database-specific (e.g., MySQLReportGenerator, OracleReportGenerator)
- You needed to ensure a MySQL connection always uses a MySQL-specific report generator
- There was a family relationship between database and report format

## Reference

For more details on the Simple Factory pattern, see: [../../Factory/README.md](../../Factory/README.md)
