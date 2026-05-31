# University Registration System

A Java-based University Registration System that manages students, courses, and course enrollments using custom data structures. The project demonstrates low-level implementation of linked structures, sorting algorithms, and stack operations without relying on Java collection frameworks such as ArrayList or LinkedList.

---

# Features

## Student & Course Management
- Add and remove students dynamically
- Add and remove courses dynamically
- Manage entities using custom linked structures

## Enrollment Management
- Enroll students into courses
- Prevent enrollment when courses reach maximum capacity
- Validate student enrollment limits

## Data Sorting
- Sort students by ID inside a course using Bubble Sort
- Sort courses by ID for a student using Insertion Sort

## Undo & Redo Operations
- Track enrollment operations using Stack structures
- Restore or reapply actions dynamically

---

# Tech Stack

* **Language:** Java
* **Core Concepts:**
- Custom Linked Lists
- Stack Operations
- Sorting Algorithms
- Object-Oriented Programming (OOP)

---

# Project Structure

```
├── Course.class
├── EnrollmentNode.class
├── Main.class
├── Student.class
└── University.class
```

---

# Architecture and Data Design

The system implements a custom interconnected structure to simulate many-to-many relationships between students and courses.

```
   University List
   [Students Head] -> [Student: 101] -> [Student: 102] -> null
                           │
                           └──> [Enrolled Courses Head] -> [Enrollment Node] -> [Enrollment Node] -> null
                                                                │                    │
                                            ┌───────────────────┘                    │
                                            ▼                                        ▼
   [Courses Head]  -> [Course: 4001] ────────────────────────────────────────────────┘
                           │
                           └──> [Enrolled Students Head] -> [Enrollment Node] -> null
                                                                │
                                            ┌───────────────────┘
                                            ▼
                      [Course: 4002] <──────┘
```

## System Design Overview

- `University` acts as the main controller that manages student and course chains.
- Each `Student` maintains a linked list of enrolled courses.
- Each `Course` maintains a linked list of enrolled students.
- `EnrollmentNode` acts as a bridge between both entities.

---

# Educational Purpose

This project was developed for educational purposes to demonstrate:
- Custom linked list implementation
- Pointer manipulation
- Cross-referenced node structures
- Manual data management
- Sorting algorithms
- Stack-based undo and redo operations
- Low-level algorithm design without high-level collection frameworks
