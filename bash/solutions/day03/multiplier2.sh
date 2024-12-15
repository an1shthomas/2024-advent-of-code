#!/usr/bin/env bash

mul_regex='mul\(([0-9]+),([0-9]+)\)'
do_regex='do\(\)'
dont_regex='don'\''t\(\)'

input=$(< /dev/stdin)

echo "Input received: $input"

input=${input//)/)$'\n'}

echo $input

enabled=true

sum=0
while read -r line; do
    if [[ $line =~ $dont_regex ]]; then
        enabled=false
    elif [[ $line =~ $do_regex ]]; then
        enabled=true
    elif [[ $line =~ $mul_regex ]] && $enabled; then
        num1=${BASH_REMATCH[1]}
        num2=${BASH_REMATCH[2]}
        product=$((num1 * num2))
        ((sum+=product))
    fi
done <<< $input
echo "Sum: $sum"
