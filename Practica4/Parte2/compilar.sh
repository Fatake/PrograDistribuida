#!/bin/bash

rm -rf *.class
javac *.java
rmic FileImpl
rmiregistry 9878
