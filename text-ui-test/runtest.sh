#!/usr/bin/env bash

# get the directory of this script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$SCRIPT_DIR/.."   # assuming script is in text-ui-test/

BIN_DIR="$PROJECT_ROOT/bin"
SRC_DIR="$PROJECT_ROOT/src/main/java"
INPUT_FILE="$SCRIPT_DIR/input.txt"
EXPECTED_FILE="$SCRIPT_DIR/EXPECTED.TXT"
ACTUAL_FILE="$SCRIPT_DIR/ACTUAL.TXT"

# create bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

# delete previous output
rm -f "$ACTUAL_FILE"

# compile all java files in src/main/java
if ! javac -d "$BIN_DIR" "$SRC_DIR"/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run program
java -cp "$BIN_DIR" Jimjam < "$INPUT_FILE" > "$ACTUAL_FILE"

# convert line endings to UNIX
dos2unix "$ACTUAL_FILE" "$EXPECTED_FILE"

# compare output
if diff "$ACTUAL_FILE" "$EXPECTED_FILE" >/dev/null; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
