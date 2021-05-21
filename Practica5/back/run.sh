function pause(){
    read -s -n 1 -p "Press any key to continue . . ."
    echo ""
}

echo "[!] Script que Limpia archivos de compilacion"
echo "[!] Solo se puede Ejecutar con bash emulator como git-bash "
pause


mkdir backup
cp FileClient.java ./backup/
cp FileServant.java ./backup/
cp FileServer.java ./backup/
cp FileInterface.idl ./backup/
cp *.txt ./backup/
read -t 1 -p "."
rm *.class 
rm *.java
rm *.idl
rm -rf FileInterfacePackage

cp ./backup/* .
rm -rf backup

echo "<-------------------------->"
echo "[i] Compilando..."
echo ""

#./compilar.bat
