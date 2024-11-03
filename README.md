# Ejercicio M DEIN

Este proyecto es una aplicación de gestión de aeropuertos y aviones desarrollada en Java utilizando JavaFX para la interfaz gráfica de usuario y MySQL como sistema de gestión de bases de datos.

## Estructura del Proyecto

El proyecto se organiza en diferentes paquetes, cada uno con su propia funcionalidad:

- **application**: Contiene las clases principales para las distintas funcionalidades de la aplicación.
    - `M_ActivarDesactivarAvion.java`
    - `M_AddAeropuerto.java`
    - `M_AddAvion.java`
    - `M_BorrarAvion.java`
    - `M_EditarAeropuerto.java`
    - `M_ListadoAeropuertos.java`
    - `M_Login.java`

- **bbdd**: Contiene la clase de conexión a la base de datos.
    - `ConexionBD.java`

- **controllers**: Controladores para manejar la lógica de la interfaz de usuario.
    - `M_ActivarDesactivarAvionController.java`
    - `M_AddAeropuertoController.java`
    - `M_AddAvionController.java`
    - `M_BorrarAvionController.java`
    - `M_EditAeropuertoController.java`
    - `M_ListadoAeropuertosController.java`
    - `M_LoginController.java`

- **dao**: Objetos de acceso a datos, responsables de las operaciones CRUD con la base de datos.
    - `AeropuertosDao.java`
    - `AeropuertosPrivadosDao.java`
    - `AeropuertosPublicosDao.java`
    - `AvionesDao.java`
    - `DireccionesDao.java`
    - `UsuariosDAO.java`

- **model**: Modelos que representan las entidades de la aplicación.
    - `Aeropuertos.java`
    - `AeropuertosPrivados.java`
    - `AeropuertosPublicos.java`
    - `Aviones.java`
    - `Direcciones.java`
    - `InformacionAeropuertosPrivados.java`
    - `InformacionAeropuertosPublicos.java`
    - `Usuarios.java`

- **util**: Utilidades generales para el proyecto.
    - `Propiedades.java`

- **css**: Archivos de estilo CSS para la aplicación.
    - `M.css`

- **fxml**: Archivos FXML para definir las interfaces de usuario.
    - `M_ActivarDesactivarAvion.fxml`
    - `M_AddAeropuerto.fxml`
    - `M_AddAvion.fxml`
    - `M_BorrarAvion.fxml`
    - `M_EditarAeropuerto.fxml`
    - `M_ListadoAeropuertos.fxml`
    - `M_Login.fxml`

- **images**: Imágenes utilizadas en la aplicación.
    - `avion.png`
    - `plane.png`

- **module-info.java**: Archivo de configuración del módulo Java.

## Requisitos

- JDK 22 o superior.
- MySQL Server.
- Maven para la gestión de dependencias y construcción del proyecto.

## Uso

Una vez que la aplicación esté en funcionamiento, podrás iniciar sesión y gestionar aeropuertos y aviones utilizando las distintas funcionalidades disponibles en la interfaz gráfica.
