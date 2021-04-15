#!/bin/bash

start rmiregistry
rm -rf *.class
javac *.java


java HelloServer