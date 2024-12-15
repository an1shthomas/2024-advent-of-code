#!/usr/bin/env bash

regex='mul\(([0-9]+),([0-9]+)\)'

input=$(< /dev/stdin)

echo "Input received: $input"
echo "Using regex: $regex"

sum=0
while [[ $input =~ $regex ]]; do
    echo "Match found"
    echo "Matched: ${BASH_REMATCH[0]}"
    echo "Group 1: ${BASH_REMATCH[1]}"
    echo "Group 2: ${BASH_REMATCH[2]}"
    num1=$((${BASH_REMATCH[1]}))
    num2=$((${BASH_REMATCH[2]}))
    result=$((num1 * num2))
    sum=$((sum + result))
    input=${input#*"${BASH_REMATCH[0]}"}
done

echo "Sum: $sum"