#!/usr/bin/env bash

lhs=()
rhs=()

while read -r num1 num2; do
    lhs+=("$num1")
    rhs+=("$num2")
done

mapfile -t leftsorted  < <(printf '%s\n' "${lhs[@]}" | sort -n)
mapfile -t rightsorted < <(printf '%s\n' "${rhs[@]}" | sort -n)

#echo "Left sorted: ${leftsorted[@]}"
#echo "Right sorted: ${rightsorted[@]}"

len=${#leftsorted[@]}

accum=0
for((i = 0; i < $len; i++)); do
    left=${leftsorted[$i]}
    right=${rightsorted[$i]}

    diff=$((left - right))

    #abs value
    dist=${diff#-}

    ((accum += dist))
done

echo "Distance: $accum"
