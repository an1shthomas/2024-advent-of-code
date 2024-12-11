# Advent of Code 2024 - Bash Solutions

This directory contains solutions for the [Advent of Code 2024](https://adventofcode.com/2024) challenges implemented in Bash.

## Project Structure

```
bash/
├── solutions/       # Directory containing solution scripts
│   ├── day01/      # Solutions for Day 1
│   └── ...         # Additional day solutions
├── input/          # Directory containing input files
│   ├── day01/      # Input files for Day 1
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
   ```

Each solution script is designed to read input from standard input (stdin), allowing for easy piping of input files.

## Input Files

- Place your puzzle input in the corresponding day's directory under `input/`
- Each day should have:
  - `input.txt`: Your personal puzzle input
  - `example.txt`: Example input from the puzzle description

## Features

- Modular structure with separate directories for each day
- Automatic input file handling via pipes
- Support for both example and actual puzzle inputs

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
