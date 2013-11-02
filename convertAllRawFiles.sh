#!/bin/bash

for f in *.R*
do
        java -jar target/raw-tools-1.0.0-SNAPSHOT.jar $f $2
done
