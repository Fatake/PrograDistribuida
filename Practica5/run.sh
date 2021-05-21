function pause(){
    read -s -n 1 -p "Press any key to continue . . ."
    echo ""
}

echo "[!] Script que Limpia archivos de compilacion"
echo "[!] Solo se puede Ejecutar con bash emulator como git-bash "
pause

echo "[i] Creando Backup"
mkdir backup
cp FileClient.java ./backup/
cp FileServant.java ./backup/
cp FileServer.java ./backup/
cp FileInterface.idl ./backup/
cp *.txt ./backup/
read -t 1 -p "."
read -t 2 -p "."
read -t 1 -p "."
echo

echo "[!] Borrando .class y FileInterfacePackage"
rm *.class 
rm *.java
rm *.idl
rm -rf FileInterfacePackage

echo "[i] Restaurando Backup"
cp ./backup/* .
rm -rf backup

echo "<-------------------------->"
echo "[i] Compilando..."
echo ""

./compilar.bat
