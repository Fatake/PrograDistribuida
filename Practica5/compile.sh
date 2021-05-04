#!/bin/bash

echo "Creando idl"
idlj -fserver FileInterface.idl


tnameserv -ORBinitialPort 2500

javac *.java

java FileServer -ORBInitialPort 2500

idlj -fclient FileInterface.idl

java FileClient hello.txt -ORBInitialPort 2500
 ava FileClient hello.txt -ORBInitialHost gosling -ORBInitialPort 4500