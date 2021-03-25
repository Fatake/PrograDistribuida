#!/bin/bash
#start rmiregistry 9878
rm -rf *.class
javac *.java
cp *.class ..
cp cli.sh ..
echo "Compilado Correcto"
java -Djava.security.policy=politica.txt FileServer