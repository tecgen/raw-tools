#!/bin/bash

for f in *.R*
do
        java -jar target/file-name-converter-1.0.0-SNAPSHOT.jar $f $2
done
