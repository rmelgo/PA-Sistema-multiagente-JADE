# Diseño de un sistema multiagente para la gestión de un hospital con JADE

![18po_hospital-recoletas-burgos](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/57a8d5f1-34b5-44cb-9ef1-6329fa009589)

# - Introducción

Proyecto realizado en la asignatura de ***Programación Avanzada*** del grado de Ingenieria Informática de la Universidad de Salamanca. El enunciado del proyecto se encuentra subido en el repositorio en un archivo PDF llamado <a href="https://github.com/rmelgo/PA-Sistema-multiagente-JADE/blob/main/enunciadoPr%C3%A1cticaAgentesPA2021-2022.pdf" target="_blank">*enunciadoPrácticaAgentesPA2021-2022.pdf*</a>.
  
El principal objetivo de este proyecto es la realización de un sistema multiagente donde los distintos agentes colaboren entre sí para proporcionar un servicio.
En este caso, el servicio que se va a proporcionar es un pequeño servicio de consulta de hospital, en el que los usuarios pueden concertar citas con un médico especialista de un determinado área.
Dentro del proyecto, tambien se realizará un pequeño artículo científico donde se explica con mayor detalle el proyecto realizado.

# - Comentarios sobre el entorno de ejecución

Para ejecutar este programa, se requerirá de la herramienta de desarrollo **Eclipse** y una versión de **Java 1.7** o superior.  

También se requerirán un par de librerías que implementas las funcionalidades de ***JADE*** y la creación y comunicación entre agentes.

# Comentarios sobre el material adjuntado

El proyecto cuenta con los siguientes ficheros:

- Una carpeta llamada ***Trabajo JADE*** que contiene la implementación del sistema multiagente. Este sistema multiagente a su vez esta compuesto por 5 clases principales:

  - La clase **AgenteConsulta.java** implementa un *Agente Consulta* que solicitará al usuario el tipo de médico requerido y enviará la información al *Agente Buscador*. El *Agente Buscador* le devolverá un médico experto adecuado al usuario. 
  - La clase **AgenteBuscador.java** implementa un *Agente Buscador* que se comunicará con el *Agente Doctor*, y su propósito es encontrar un médico conveniente dada la información enviada por *Agente Consulta*. Una vez que lo tenga, lo devolverá a *Agente Consulta*.
  - La clase **AgenteDoctor.java** implementa un *Agente Doctor* que obtendrá una cita que coincida con la información enviada por *Agente Buscador* y devolverá los datos al *Agente Buscador*.
  - La clase **Doctor.java** que actuará como base de datos ya que almacena información de los distintos médicos que trabajan en el hospital (nombre, apellidos, especialidad, fechas y consultas disponibles).
  - La clase **Utils.java** que contiene una serie de funciones auxiliares que permiten el intercambio de mensajes entre los agentes así como el registro de los servicios.
 
- Un documento llamado ***A_Multi_Agent_System_for_Hospital_Organization.pdf*** que contiene un artículo basado en un sistema multiagente destinado a una organización hospitalaria. Este artículo ha sido la base a partir de la cual se ha elaborado el diseño del sistema multiagente.
- Un documento llamado ***A Multi-Agent System For Arranging Doctor Appointments.pdf*** que contiene un artículo donde se explicada de manera detallada la estructura y el funcionamiento del sistema multiagente desarrollado.
    
# - Parámetros de ejecución

Para poder poner en marcha el sistema multiagente en el entorno de ***Eclipse***, es necesario establecer las configuraciones de ejecución necesarias para lanzar cada uno de los 3 agentes que forman el sistema multiagente.  

Para crear una nueva configuración en Eclipse, se debe acceder a *Run -> Run Configurations*. Se desplegará una nueva ventana donde se podrán crear distintas configuraciones.

## Configuración del Agente Consulta

Para configurar el ***Agente Consulta***, se debe crear una nueva configuración de tipo *Java Application*. 

Pasos a seguir para realizar la configuración del Agente Consulta:

- **Paso 1**: Asociar la nueva configuración creada al proyecto ***Trabajo JADE***
- **Paso 2**: Establecer como clase principal la clase *jade.Boot*
- **Paso 3**: En la sección de argumentos, introducir los siguientes parámetros: ```-gui consulta:Trabajo_final_JADE.AgenteConsulta```

![Configuración 1](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/e492df9e-e4dd-45fb-b6b2-15e65930ebfb)

![Configuración 2](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/537b9d7e-d851-431f-9477-4d3bfff1ffbd)

## Configuración del Agente Buscador

Para configurar el ***Agente Buscador***, se debe crear una nueva configuración de tipo *Java Application*. 

Pasos a seguir para realizar la configuración del Agente Buscador:

- **Paso 1**: Asociar la nueva configuración creada al proyecto ***Trabajo JADE***
- **Paso 2**: Establecer como clase principal la clase *jade.Boot*
- **Paso 3**: En la sección de argumentos, introducir los siguientes parámetros: ```-main false -host 127.0.0.1 buscador:Trabajo_final_JADE.AgenteBuscador```

![Configuración 3](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/8803f689-3a86-4c10-9b8c-b90e5a056db5)

![Configuración 4](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/83fba49b-eb10-4a2e-9d0c-3c37afc07d46)

## Configuración del Agente Doctor

Para configurar el ***Agente Doctor***, se debe crear una nueva configuración de tipo *Java Application*. 

Pasos a seguir para realizar la configuración del Agente Doctor:

- **Paso 1**: Asociar la nueva configuración creada al proyecto ***Trabajo JADE***
- **Paso 2**: Establecer como clase principal la clase *jade.Boot*
- **Paso 3**: En la sección de argumentos, introducir los siguientes parámetros: ```-main false -host 127.0.0.1 doctor:Trabajo_final_JADE.AgenteDoctor```

![Configuración 5](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/14fc04b8-e7c2-4c25-836f-f9d85cefa721)

![Configuración 6](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/50fefed0-8b4b-44be-8eae-27091d1dcf0a)

# - Pasos necesarios para ejecutar el proyecto

**Paso 1: Lanzar el Agente Consulta**  

Para ello, se debe ejecutar la configuración creada anteriormente para el *Agente Consulta*. 

Al lanzar el *Agente Consulta* se deplegará una interfaz que nos muestra los agentes lanzados y que nos permite visualizar el intercambio de mensajes entre los agentes. Su aspecto es el siguiente:

<p align="center">
  <img src="https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/f616f15e-9a93-4753-8c14-b33d8b2680f1">
</p>

**Nota**: Esta interfaz es totalmente complementaria ya que el funcionamiento del sistema multiagente es totalmente independiente.

**Paso 2: Lanzar el Agente Buscador**  

Para ello, se debe ejecutar la configuración creada anteriormente para el *Agente Buscador*.

**Paso 3: Lanzar el Agente Doctor**  

Para ello, se debe ejecutar la configuración creada anteriormente para el *Agente Doctor*.

**Finalización del proyeccto**

Para finalizar la ejecución del proyecto simplemente bastará con finalizar la ejecución de cada uno de los agentes.

# - Ejemplo de ejecución

Tras haber lanzado los 3 agentes que forman el sistema multiagente correctamente, se nos presenta lo siguiente:

![Ejemplo ejecucion 2 con borde](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/8074984a-70c9-4497-b108-6cd5c98a492a)

Como se puede observar, el sistema multiagente solicita al usuario que introduzca el tipo de especialidad o servicio del cual desea realizar una consulta.

Una vez el usuario introduce uno de los servicios disponibles, el sistema multiagente se encarga de buscar un médico que proporcione el servicio buscado por el usuario. Una vez encuentra el médico correspondiente, el sistema multiagente proporciona una cita al usuario con dicho médico en la fecha mas cercana disponible. Esto se puede observar en la siguiente imagen:

![Ejemplo ejecucion 3 con borde](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/3df1672b-646f-4860-b0d7-75ace11c2318)

Con la interfaz despleguada al lanzar el *Agente Buscador*, se puede capturar el intercambio de mensajes realizado entre los agentes. En este ejemplo, el intercambio de mensajes realizado por los agentes es el siguiente:

<p align="center">
  <img src="https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/c7fc873d-8ca7-4f39-9d0a-adacba87ae0b">
</p>

Si el usuario solicita un servicio o especialidad que no está disponible, el sistema multiagente responderá indicando que no existen médicos que pertenezcan a la especialidad introducida. Aquí se muestra un ejemplo:

![Ejemplo ejecucion 5 con borde](https://github.com/rmelgo/PA-Sistema-multiagente-JADE/assets/145989723/b14d64ba-0589-4c6e-9470-79f48fdf8ad3)

# - Participantes

<table>
  <td align="center"><a href="https://github.com/rmelgo"><img src="https://avatars.githubusercontent.com/u/145989723?s=400&u=e5c06adba3f3c418207178abc845d398b3d5f77f&v=4" width="100px;" alt=""/><br /><sub><b>Raúl Melgosa</b></sub></a><br/> 
  <td align="center"><img src="https://avatars.githubusercontent.com/u/84237179?v=4" width="100px;" alt=""/><br /><sub><b>Juan Carlos Velasco</b></sub></a><br/> 
  <td align="center"><img src="https://avatars.githubusercontent.com/u/84237179?v=4" width="100px;" alt=""/><br /><sub><b>Alberto Lorenzo</b></sub></a><br/> 
</table>
