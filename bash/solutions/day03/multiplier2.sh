#!/usr/bin/env bash

mul_regex='mul\(([0-9]+),([0-9]+)\)'
do_regex='do\(\)'
dont_regex='don'\''t\(\)'

input=$(< /dev/stdin)

regex="(${do_regex})|(${dont_regex})|(${mul_regex})"

echo "Input received: $input"
echo "Using regex: ${regex}"

#input=${input//)/)$'\n'}
#
#echo $input
#
#enabled=true
#
#sum=0
#while read -r line; do
#    if [[ $line =~ $dont_regex ]]; then
#        enabled=false
#    elif [[ $line =~ $do_regex ]]; then
#        enabled=true
#    elif [[ $line =~ $mul_regex ]] && $enabled; then
#        num1=${BASH_REMATCH[1]}
#        num2=${BASH_REMATCH[2]}
#        product=$((num1 * num2))
#        ((sum+=product))
#    fi
#done <<< $input
#echo "Sum: $sum"

sum=0
enabled=true
while [[ $input =~ $regex ]]; do
    #echo "Matched: ${BASH_REMATCH[0]}"
    if [[ ${BASH_REMATCH[1]} ]]; then
        enabled=true
    fi
    if [[ ${BASH_REMATCH[2]} ]]; then
        enabled=false
    fi
    if [[ ${BASH_REMATCH[3]} ]] && [[ "$enabled" == true ]]; then
        num1=$((${BASH_REMATCH[4]}))
        num2=$((${BASH_REMATCH[5]}))
        #echo "num1: $num1 num2: $num2"
        result=$((num1 * num2))
        sum=$((sum + result))
    fi
    input=${input#*"${BASH_REMATCH[0]}"}
done

echo "Sum: $sum"