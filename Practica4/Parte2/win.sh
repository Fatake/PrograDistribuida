#!/bin/bash

#start rmiregistry
rm -rf *.class
javac *.java
cp *.class ..
cp cli.sh ..
echo "[i] Compilado Correcto"

java -Djava.security.policy=politica.txt FileServer