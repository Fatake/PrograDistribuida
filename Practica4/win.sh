#!/bin/bash
start rmiregistry
rm -rf *.class
javac *.java
cp *.class ..
echo "Compilado Correcto"
java -Djava.security.policy=politica.txt FileServer