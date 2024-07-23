# Backup y Restauración de Base de Datos PostgreSQL en Docker

## Prerrequisitos

- Docker instalado en tu sistema.
- Acceso a la base de datos PostgreSQL en Docker.
- Conocimiento básico de la terminal o línea de comandos.

## Configuración de Conexión

### Datos de Conexión

- **Host:** localhost
- **Port:** 5432
- **Database:** postgres
- **Nombre de usuario:** postgres
- **Contraseña:** smartpokeadmin

## Pasos para Crear un Backup

1. **Identificar el Contenedor de PostgreSQL**

   Asegúrate de conocer el nombre del contenedor que está ejecutando PostgreSQL. Puedes listar todos los contenedores en ejecución con el siguiente comando:

   ```sh
   docker ps
