#!/bin/bash

#start rmiregistry
rm -rf *.class
javac *.java
java -Djava.security.policy=politica.txt FileServer

