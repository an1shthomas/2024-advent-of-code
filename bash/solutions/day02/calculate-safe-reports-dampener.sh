#!/usr/bin/env bash

# Exit on undefined variables
set -u

# Check if sequence is safe without dampener
check_sequence() {
    local -a nums=("$@")
    local len=${#nums[@]}
    
    if((len < 2)); then
        return 1
    fi

    local ascending
    local num1=${nums[0]}
    local num2=${nums[1]}
    
    if((num1 < num2)); then
        ascending=1
    elif((num1 > num2)); then
        ascending=0
    else
        return 1
    fi

    local prev=${nums[0]}
    local i
    for((i=1; i<len; i++)); do
        local curr=${nums[i]}
        local diff=$((prev - curr))
        local dist=${diff#-}
        
        if((dist<1 || dist>3)); then
            return 1
        fi
        if ((ascending == 1)) && ((prev > curr)); then
            return 1
        elif ((ascending == 0)) && ((prev < curr)); then
            return 1
        fi
        prev=$curr
    done
    
    return 0
}

# returns 0 if safe (either directly or with dampener), 1 if unsafe
process_report() {
    local -a level=("$@")
    local len=${#level[@]}
    
    # First try without dampener
    if check_sequence "${level[@]}"; then
        echo "DEBUG: Safe without dampener"
        return 0
    fi
    
    # If unsafe, try removing each level one at a time
    local i
    for((i=0; i<len; i++)); do
        local -a dampened_level=()
        local j
        for((j=0; j<len; j++)); do
            if((j != i)); then
                dampened_level+=("${level[j]}")
            fi
        done
        
        echo "DEBUG: Trying without level ${level[i]}: ${dampened_level[*]}"
        if check_sequence "${dampened_level[@]}"; then
            echo "DEBUG: Safe after removing level ${level[i]}"
            return 0
        fi
    done
    
    echo "DEBUG: Unsafe even with dampener"
    return 1
}

numsafe=0
while read -ra levels; do
    echo "DEBUG: Processing line: ${levels[*]}"
    if process_report "${levels[@]}"; then
        ((numsafe++))
        echo "DEBUG: Safe report found, total: $numsafe"
    fi
done

echo "number of safe reports: $numsafe"