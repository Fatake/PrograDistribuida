#!/bin/bash

rm -rf *.class
javac *.java
rmic FileImpl
fork(){
    echo "Ejecutando Servidor"
    
}
rmiregistry 9878 & java -Djava.security.policy=politica.txt FileServer  
