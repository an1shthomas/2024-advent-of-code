#!/usr/bin/env bash

# Enable debug mode
# set -x
# Exit on error
# set -e
# Exit on undefined variables
set -u

# returns 0 if safe, 1 if unsafe
process_report(){
    local level=("$@")
    local len=${#level[@]}
    
    if((len < 2)); then
        echo "DEBUG: Array too short"
        echo "DEBUG: About to return 1"
        return 1
    fi

    local ascending
    local num1=${level[0]}
    local num2=${level[1]}
    echo "DEBUG: First two numbers: $num1, $num2"
    
    if((num1 < num2)); then
        ascending=true
        echo "DEBUG: Sequence is ascending"
    elif((num1 > num2)); then
        ascending=false
        echo "DEBUG: Sequence is descending"
    else
        echo "DEBUG: First two numbers are equal"
        echo "DEBUG: About to return 1"
        return 1
    fi

    local i
    for((i=1; i<len; i++)); do
        local prev=${level[i-1]}
        local curr=${level[i]}
        local diff=$((prev - curr))
        local dist=${diff#-}
        echo "DEBUG: Comparing prev=$prev curr=$curr diff=$diff dist=$dist ascending=$ascending"
        if((dist<1 || dist>3)); then
            echo "DEBUG: Distance $dist out of range"
            echo "DEBUG: About to return 1"
            return 1
        fi
        if $ascending && ((prev > curr)); then
            return 1
        elif ! $ascending && (( prev < curr)); then
            return 1
        fi

    done
    echo "DEBUG: Report is safe"
    echo "DEBUG: About to return 0"
    return 0
}

numsafe=0
while read -ra levels; do
    echo "DEBUG: Processing line: ${levels[@]}"
    if process_report "${levels[@]}"; then
        ((numsafe++))
    fi
done

echo "number of safe reports: $numsafe"