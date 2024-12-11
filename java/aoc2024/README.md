# Advent of Code 2024 - Java Solutions

This repository contains my solutions to the [Advent of Code 2024](https://adventofcode.com/2024) challenges implemented in Java.

## Project Structure

```
aoc2024/
├── src/
│   ├── main/
│   │   ├── java/org/an1sht/
│   │   │   ├── util/           # Utility classes
│   │   │   ├── day07/          # Solutions for Day 7
│   │   │   └── ...            # Solutions for other days
│   │   └── resources/
│   │       └── input/          # Input files for each day
│   └── test/                   # Test cases
└── pom.xml                     # Maven configuration
```

## Running the Solutions

Each day's solution is contained in its own package under `org.an1sht`. To run a specific day's solution:

```bash
mvn compile exec:java -Dexec.mainClass="org.an1sht.day07.Day07a"  # For Day 7, Part A
mvn compile exec:java -Dexec.mainClass="org.an1sht.day07.Day07b"  # For Day 7, Part B
```

## Input Files

Input files should be placed in the `src/main/resources/input/dayXX/` directory, where `XX` is the two-digit day number (e.g., `day07` for Day 7). The following files are expected:

- `input.txt` - Your puzzle input
- `example-input.txt` - Example input from the puzzle description

## Features

- Uses Maven for dependency management and building
- Includes logging with SLF4J and Logback
- Utility classes for common operations (e.g., file reading)
- Support for large numbers using BigInteger
- Organized package structure for each day's solutions

## Dependencies

- Java 21
- Maven
- SLF4J and Logback for logging
- JUnit for testing (if needed)

## License

This project is open source and available under the MIT License.
