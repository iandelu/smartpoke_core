
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
- **Contraseña:** (proporcionada por el usuario)

## Pasos para Crear un Backup

1. **Identificar el Contenedor de PostgreSQL**

   Asegúrate de conocer el nombre del contenedor que está ejecutando PostgreSQL. Puedes listar todos los contenedores en ejecución con el siguiente comando:

   ```sh
   docker ps
   ```

   Supongamos que el nombre del contenedor es `postgres-container`.

2. **Crear el Backup**

   Ejecuta el siguiente comando para crear un backup de la base de datos:

   ```sh
   docker exec -t postgres-container pg_dump -U postgres -d postgres > backup.sql
   ```

   Esto creará un archivo `backup.sql` en tu directorio actual con el contenido de la base de datos `postgres`.

## Pasos para Restaurar el Backup

1. **Copiar el Backup al Nuevo Entorno**

   Transfiere el archivo `backup.sql` al nuevo equipo donde deseas restaurar la base de datos. Puedes usar herramientas como `scp`, `rsync`, o servicios de almacenamiento en la nube.

2. **Levantar un Nuevo Contenedor de PostgreSQL**

   En el nuevo equipo, inicia un contenedor de PostgreSQL:

   ```sh
   docker run --name postgres-container -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=tu_contraseña -d postgres
   ```

3. **Copiar el Backup al Contenedor**

   Copia el archivo `backup.sql` al contenedor:

   ```sh
   docker cp backup.sql postgres-container:/backup.sql
   ```

4. **Restaurar el Backup**

   Ingresa al contenedor:

   ```sh
   docker exec -it postgres-container bash
   ```

   Dentro del contenedor, restaura la base de datos usando `psql`:

   ```sh
   psql -U postgres -d postgres < /backup.sql
   ```

5. **Verificar la Restauración**

   Puedes verificar que la base de datos se ha restaurado correctamente conectándote a PostgreSQL y revisando los datos:

   ```sh
   docker exec -it postgres-container psql -U postgres -d postgres
   ```

   Dentro de `psql`, puedes listar las tablas y revisar el contenido para asegurarte de que todo se haya restaurado correctamente.

## Notas

- Asegúrate de que el nombre del contenedor (`postgres-container`) y el nombre de la base de datos (`postgres`) coincidan con tu configuración.
- La contraseña utilizada en los comandos debe coincidir con la configurada al levantar el contenedor.

Con estos pasos, habrás creado un backup de tu base de datos PostgreSQL y la habrás restaurado en otro equipo.
