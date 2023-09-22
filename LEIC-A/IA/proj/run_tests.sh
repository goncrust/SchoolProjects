#!/bin/bash

SCRIPT="bimaru.py"
TEST_DIR="instances-students"
OUTPUT_DIR="instances-students"

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# check if the -v flag is specified
if [[ $1 == "-v" ]]; then
  VERBOSE=true
else
  VERBOSE=false
fi

for INPUT_FILE in "${TEST_DIR}"/instance*.txt; do
    FILENAME=$(basename "$INPUT_FILE")
    INSTANCE="${FILENAME%.*}"
    OUTPUT_FILE="${OUTPUT_DIR}/${INSTANCE}.out"

    START_TIME=$(date +%s%3N)
    python3.7 "$SCRIPT" < "$INPUT_FILE" > tmp_output.txt
    END_TIME=$(date +%s%3N)
    ELAPSED_TIME_MS=$((END_TIME - START_TIME))

    # convert milliseconds to seconds
    ELAPSED_TIME=$(bc <<< "scale=2; $ELAPSED_TIME_MS / 1000")

    if diff -q tmp_output.txt "$OUTPUT_FILE" > /dev/null; then
        echo -e "Test ${INSTANCE}: ${GREEN}PASSED${NC} (${ELAPSED_TIME} seconds)"
        else
        echo -e "Test ${INSTANCE}: ${RED}FAILED${NC} (${ELAPSED_TIME} seconds)"
    fi

    if [ "$VERBOSE" = true ]; then
        echo -e "Output:\n$(cat tmp_output.txt)"
        echo -e "Desired Output:\n$(cat "$OUTPUT_FILE")"
        echo
    fi

    # delete temp file
    rm tmp_output.txt
done
