# Advent of Code 2024 - Bash Solutions

This directory contains solutions for the [Advent of Code 2024](https://adventofcode.com/2024) challenges implemented in Bash.

## Project Structure

```
bash/
├── solutions/       # Directory containing solution scripts
│   ├── day01/      # Solutions for Day 1
│   ├── day02/      # Solutions for Day 2
│   ├── day03/      # Solutions for Day 3
│   └── ...         # Additional day solutions
├── input/          # Directory containing input files
│   ├── day01/      # Input files for Day 1
│   │   ├── input.txt
│   │   └── example.txt
│   ├── day02/      # Input files for Day 2
│   │   ├── input.txt
│   │   └── example.txt
│   ├── day03/      # Input files for Day 3
│   │   ├── input.txt
│   │   └── example.txt
│   └── ...         # Additional day inputs
└── run.sh          # Main script to run solutions
```

## Requirements

- Bash version 4.0 or higher
- Standard Unix utilities (grep, sed, awk, etc.)

## Running Solutions

1. Make sure the solution scripts are executable:
   ```bash
   chmod +x solutions/day**/*.sh
   ```

2. Run solutions using input files through pipes:
   ```bash
   # Using example input
   cat input/day<XX>/example-input.txt | ./solutions/day<XX>/<script-name>.sh

   # Using actual puzzle input
   cat input/day<XX>/input.txt | ./solutions/day<XX>/<script-name>.sh
   ```

   Example:
   ```bash
   # Run Day 1's similarity score calculation with example input
   cat input/day01/example-input.txt | ./solutions/day01/calculate-similarity-score.sh

   # Run Day 1's distance calculation with actual input
   cat input/day01/input.txt | ./solutions/day01/calculate-distance.sh

   # Run Day 2's safety reports with example input
   cat input/day02/example-input.txt | ./solutions/day02/calculate-safe-reports.sh
   cat input/day02/example-input.txt | ./solutions/day02/calculate-safe-reports-dampener.sh
   ```

Each solution script is designed to read input from standard input (stdin), allowing for easy piping of input files.

## Solutions

### Day 1: Calibration Values
- `calculate-distance.sh`: Calculate calibration values from input sequences
- `calculate-similarity-score.sh`: Compute similarity scores for calibration data

### Day 2: Safety Reports
- `calculate-safe-reports.sh`: Analyze sequences for safety based on ascending/descending patterns and distance rules
- `calculate-safe-reports-dampener.sh`: Enhanced version that implements the Problem Dampener feature
  - Allows one violation to be removed if it makes an unsafe report safe
  - Uses array manipulation to test each possible dampening position
  - Maintains original safety rules for distance and sequence patterns

### Day 3: Multiplication Instructions
- `multiplier.sh`: Process multiplication instructions from corrupted memory
- `multiplier2.sh`: Enhanced version that handles do() and don't() instructions
  - Tracks state of multiplication (enabled/disabled)
  - Processes instructions line by line after splitting on parentheses
  - Handles complex patterns with regex matching

## Input Files

- Place your puzzle input in the corresponding day's directory under `input/`
- Each day should have:
  - `input.txt`: Your personal puzzle input
  - `example.txt`: Example input from the puzzle description

## Features

- Modular structure with separate directories for each day
- Automatic input file handling via pipes
- Support for both example and actual puzzle inputs
- Debug output to help understand solution logic
- Array manipulation for complex sequence processing

## Contributing

1. Create a new directory for the day under `solutions/`
2. Add your solution scripts named `part-a.sh` and `part-b.sh`
3. Create corresponding input directory under `input/`
4. Make sure your scripts are executable
5. Test with both example and actual inputs

## License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

## Notes

- Each solution script should read input from standard input or a file specified as an argument
- Add comments to explain complex logic or algorithms
- Consider performance for larger inputs
- Scripts use bash arrays and arithmetic operations
- Debug output can be enabled/disabled via commented set -x
- Error handling for invalid inputs
