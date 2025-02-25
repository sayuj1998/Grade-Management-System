# Grade Management System

## Overview
A Java-based application for managing and calculating student grades. It reads course data from files, calculates final grades using weighted categories, assigns letter grades, and outputs class statistics.

## Features
- Reads and writes course data via files
- Calculates final and letter grades (A–F scale)
- Generates class statistics: average, minimum, maximum
- Follows OOP principles: encapsulation and inheritance

## Technologies
- Java
- Object-Oriented Programming (OOP)
- File I/O

## Getting Started
1. Clone the repo:
```
git clone https://github.com/sayuj1998/Grade-Management-System
```
2. Compile and run:
```
javac GradeManagementSystem.java  
java GradeManagementSystem
```
## Customization ##
After compiling and running the program, you can modify the following details to suit your needs:
* Course Names: Update course titles (e.g., CS1050, CS2040) to any course of your choice.
* Instructor Details: Change the instructor's name and email.
* Student Names: Edit student names (e.g., Stack Exception, Binary Beast) to represent any students.
* Grading Categories: Adjust grading components (e.g., Participation, Quizzes, Final Exam) and their weight percentages.
* Grade Inputs: Modify individual student grades for each category to see updated final results.
* Letter Grade Ranges: Update the grading scale if a different grading system is required.

## Example Output
```
Setting up course CS1050
Total Students in CS1050: 3.0

**********************************
CS1050 Final Grade Calculator
Instructor Name:John Smith jdoe@university.edu
**********************************
------------------------------
Category:Percent
------------------------------
Class Participation Grade: 0.12
Guided Exploration Grade: 0.22
Quizzes Grade: 0.22
Project Percent Grade: 0.22
Final Exam Grade: 0.22

-------------------------------
Letter Grade Range
-------------------------------
A: 90 to 100
B: 80 to < 90
C: 70 to < 80
D: 60 to < 70
F: < 60

***** CS1050 Final Grades *******
Instructor Name:John Smith jdoe@university.edu

Stack Exception: 88.11  B
Binary Beast: 95.78  A
Heap Memory: 73.58  C

Class average: 85.82
Class min: 73.58
Class max: 95.78

File located at C:\Users\Name\IdeaProjects\Grade_Management_System\CS1050_summary.txt

Setting up course CS2040
Course is full. Couldn't add student: Null Pointer pointer@msudenver.edu

Total Students in CS2040: 4.0

**********************************
CS2040 Final Grade Calculator
Instructor Name:Jane Doe jdoe@university.edu
**********************************
------------------------------
Category:Percent
------------------------------
Class Participation Grade: 0.15
Homework Grade: 0.25
Midterm Grade: 0.3
Final Exam Grade: 0.3

-------------------------------
Letter Grade Range
-------------------------------
A: 90 to 100
B: 80 to < 90
C: 70 to < 80
D: 60 to < 70
F: < 60

***** CS2040 Final Grades *******
Instructor Name:Jane Doe jdoe@university.edu

Stack Exception: 86.74  B
Binary Beast: 90.49  A
Heap Memory: 64.45  D
Try Catch: 88.51  B

Class average: 82.55
Class min: 64.45
Class max: 90.49

File located at C:\Users\Name\IdeaProjects\Grade_Management_System\CS2040_summary.txt
```

## Future add-ons
- GUI for better interaction
- Database integration for storage