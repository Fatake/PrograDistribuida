@echo off
echo [!] Este programa requiere JDK 1.3
echo [!] Ponga la ruta del JDK 1.3 si tiene una diferente en el script
pause

echo "[!] Borrando .class y FileInterfacePackage"
del *.class 
rmdir /S /Q FileInterfacePackage

echo "[i] Compilando idl servidor"
start C:\jdk1.3.1_28\bin\idlj.exe -fserver FileInterface.idl
ping 192.0.2.1 -n 1 -w 250 >nul 

echo "[i] Colmpilando idl cliente"
start C:\jdk1.3.1_28\bin\idlj.exe -fclient FileInterface.idl
ping 192.0.2.1 -n 1 -w 250 >nul  

echo "[i] Compilando servidor"
start C:\jdk1.3.1_28\bin\javac.exe FileServer.java
ping 192.0.2.1 -n 1 -w 250 >nul 

echo "[i] Compilando cliente"
start C:\jdk1.3.1_28\bin\javac.exe FileClient.java
ping 192.0.2.1 -n 1 -w 250 >nul 

echo "[+] Lanzando tname server"
start C:\jdk1.3.1_28\bin\tnameserv.exe -ORBinitialPort 2500
ping 192.0.2.1 -n 1 -w 250 >nul 

echo "[+] Lanzando servidor"
start C:\jdk1.3.1_28\bin\java.exe FileServer
ping 192.0.2.1 -n 1 -w 250 >nul 

echo "[+] Lanzando cliente"
start C:\jdk1.3.1_28\bin\java.exe FileClient hello.txt -ORBinitialPort 2500
ping 192.0.2.1 -n 1 -w 250 >nul 
pause
