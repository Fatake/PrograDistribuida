### El modelo cliente/servidor con sockets 

Actualmente,  el  modelo  cliente/servidor  es  el  más  utilizado  para  el  desarrollo  de aplicaciones  en  red.  Un  servidor  es  un  proceso  que  ofrece  una  o  más  operaciones accesibles a través  de la red.  Un  cliente es un proceso que invoca de forma remota las operaciones ofrecidas por un servidor.

El servidor estará en la espera de recibir invocaciones de sus clientes. En el momento de recibir una invocación,  internamente la procesará y devolverá  al proceso cliente un resultado. La  comunicación  entre  los  procesos  cliente  y  servidor  es  mediante  sockets.  Estos representan  los  extremos  de  la  conexión  que  se  establece  para  llevar  a  cabo  esta comunicación.  Cuando  dos  procesos  requieren  comunicarse  solicitan  al  sistema operativo la  creació n de un socket. La respuesta a esta solicitud es un identificador que permite al proceso hacer referencia al nuevo socket creado.  De acuerdo a los  protocolos de Internet existen dos tipos de sockets. Aquellos orientados a la comunicación síncrona y asíncrona:

##### Los  sockets  orientados  a  la  comunicación  síncrona

 proporcionan  una transmisión bidireccional, continua y fiable (los datos se comunican ordenados  y sin  duplicados)  con  conexión  mediante  el  protocolo  TCP  (Transport  Control Protocol).  En  Java  se  implementan  mediante  las  clases  java.net.Socket  y java.net.ServerSocket.

##### Los  sockets  orientados  a  la  comunicación  asíncrona

proporcionan  una transmisión  bidireccional,    no  fiable,  de  longitud  máxima  prefijada  y  sin conexión  mediante  el  protocolo  UDP  (User  Datagram  Protocol).  En  Java  se implementan  mediante la clase java.net.DatagramSocket. Ambos tipos de sockets tienen asociados un objeto java.io.OutputStream  y un objeto java.io.InputStream  mediante  los  cuales  se  pueden  emitir  y  recibir  cadenas  de  bytes, respectivamente.

##### **Comunicación síncrona en programas distribuidos**

* Implementación de la comunicación entre un cliente y servidor basándose en el protocolo TCP en Java.
* Manejar el API y la documentación de Java para la información necesaria.

La  comunicación  síncrona  entre  procesos  cliente  y  servidor  con  las  clases java.net.Socket y java.net.ServerSocket para el cliente y el servidor, respectivamente. En [Servidor.java](https://econtinua.cs.buap.mx/moodle/pluginfile.php/70402/mod_assign/intro/Servidor.java) se muestra un proceso servidor que escucha peticiones de un cliente en un puerto específico, mientras que en [Cliente.java](https://econtinua.cs.buap.mx/moodle/pluginfile.php/70402/mod_assign/intro/Cliente.java) se muestra a un cliente que utiliza un servicio. La instrucción String  SERVER_ADDRESS = " localhost "; obliga a que el cliente se ejecute en la misma máquina que el servidor. El  proceso  servidor  está  asociado  al  número  de  puerto  indicado  en  la  variable SERVER PORT. El proceso cliente solicitará al proceso servidor que cuente  la cantidad de  vocales  de  las  frases  que  introduce  el  usuario  por  la  entrada  estándar.  El  proceso servidor atiende  a  las peticiones del cliente y le comunica la respuesta   hasta que reciba la secuencia END OF SERVICE. Cuando recibe   esta secuencia el  servidor finaliza su ejecución.

El  cliente,  establece  una  conexión  con  el  servidor  para  solicitar  sus  servicios.  Envía  las  diferentes  peticiones  de  servicio,  recibirá  las  respuestas  del servidor e informara  al usuario (mediante la  salida estándar)  la cantidad  de vocales por parte  del  servidor  para  cada  una  de  las  frases  introducidas.  Una  vez  que  el  usuario introduce la secuencia END OF SERVICE, el cliente finaliza su ejecución.

1.- **Ejecutar **el servidor en una terminal. Posteriormente, abrir otra terminal para ejecutar el cliente. En este caso el servidor y el cliente se ejecutaran en la misma máquina (en local). Analizar el comportamiento de ambos procesos y las comunicaciones que se establecen entre ellos.

2.- **Modificar ambos programas**. En el caso del servidor, el puerto será un parámetro de invocación desde la línea de comandos. En el caso del cliente, tomará dos parámetros: el IP donde se encuentra el servidor y el puerto en el que escucha peticiones. Para probar su ejecución, se proporcionará la dirección IP de la computadora en la que se va a ejecutar el servidor (mediante el comando ***ipconfig***). El cliente se ejecutará en otra computadora para que invoque el servicio que ofrece el servidor. Analizar el comportamiento de ambos procesos y la comunicación que se establece.

3.- Modificar el proceso servidor, de modo que pueda mantener la conexión y atender a varios clientes.
