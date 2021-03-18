#!/bin/bash
rm -rf *.class
javac *.java
cp *.class ..
start rmiregistry & sleep 2 & java -Djava.security.policy=politica.txt FileServer