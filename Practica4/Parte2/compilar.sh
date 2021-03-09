#!/bin/bash

javac *.java
rmic FileImpl
rmiregistry 1099
