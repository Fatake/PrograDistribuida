@echo off
echo [!] Este programa requiere JDK 1.3
echo [!] Ponga la ruta del JDK 1.3 si tiene una diferente en el script

echo [i] Compilando idl servidor
start D:\jdk1.3.1_28\bin\idlj.exe -fserver FileInterface.idl


echo [i] Colmpilando idl cliente
start D:\jdk1.3.1_28\bin\idlj.exe -fclient FileInterface.idl


echo [i] Compilando servidor
start D:\jdk1.3.1_28\bin\javac.exe FileServer.java


echo [i] Compilando cliente
start D:\jdk1.3.1_28\bin\javac.exe FileClient.java
pause

echo [+] Lanzando tname server
start D:\jdk1.3.1_28\bin\tnameserv.exe -ORBinitialPort 2500


echo [+] Lanzando servidor
start D:\jdk1.3.1_28\bin\java.exe FileServer
pause

echo [+] Lanzando cliente
start D:\jdk1.3.1_28\bin\java.exe FileClient hello.txt -ORBinitialPort 2500
ping 192.0.2.1 -n 1 -w 250 >nul 

