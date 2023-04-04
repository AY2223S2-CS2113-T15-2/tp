#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test

java -ea -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input2.txt > ACTUAL2.TXT

diff EXPECTED2.TXT ACTUAL2.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
