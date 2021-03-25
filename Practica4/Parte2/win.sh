#!/bin/bash

#start rmiregistry 9878
rm -rf *.class
javac *.java
cp *.class ..
cp cli.sh ..
echo "[i] Compilado Correcto"

java -Djava.security.policy=politica.txt -Djava.rmi.server.hostname=127.0.0.1 FileServer