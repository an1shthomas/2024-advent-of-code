#!/usr/bin/env bash

lhs=()
rhs=()

while read -r num1 num2; do
    lhs+=("$num1")
    rhs+=("$num2")
done

declare -A rhscount
for value in "${rhs[@]}"; do
    ((rhscount[$value]++))
done

# printing key value pairs of the rhscount associative array
#for key in "${!rhscount[@]}"; do
#    echo "$key : ${rhscount[$key]}"
#done

accum=0
for value in "${lhs[@]}"; do
    ((accum += value * rhscount[$value]))
done

echo "Similarity score: $accum"