# ForoHub API

## Descripción
ForoHub API es una API REST que permite la gestión de foros de discusión. La API proporciona endpoints para crear, leer, actualizar y eliminar tópicos de discusión, así como gestionar usuarios y cursos asociados.

## Características Principales
- Gestión completa de tópicos (CRUD)
- Paginación de resultados
- Validación de datos
- Respuestas HTTP estandarizadas
- Manejo de relaciones entre entidades (Usuarios, Cursos y Tópicos)

## Endpoints

### Tópicos

#### Crear un Tópico
```http
POST /topicos
```
- Requiere un cuerpo JSON con título, mensaje, ID del autor y ID del curso
- Retorna los detalles del tópico creado y la URL de acceso

#### Obtener un Tópico
```http
GET /topicos/{id}
```
- Retorna los detalles completos de un tópico específico

#### Listar Tópicos
```http
GET /topicos
```
- Soporta paginación (tamaño predeterminado: 2 elementos por página)
- Retorna una lista paginada de tópicos

#### Actualizar un Tópico
```http
PUT /topicos/{id}
```
- Requiere un cuerpo JSON con los datos actualizados
- Retorna los detalles del tópico actualizado

#### Eliminar un Tópico
```http
DELETE /topicos/{id}
```
- Elimina un tópico específico
- Retorna un código 204 (No Content) si la operación fue exitosa

## Estructura de Datos

### Tópico
```json
{
  "titulo": "string",
  "mensaje": "string",
  "fecha": "date",
  "status": "enum",
  "autor": "Usuario",
  "curso": "Curso"
}
```

## Respuestas y Códigos de Estado
- 200: Operación exitosa
- 201: Recurso creado exitosamente
- 204: Operación exitosa sin contenido de respuesta
- 400: Error en la solicitud
- 404: Recurso no encontrado

## Dependencias Principales
- Spring Boot
- Spring Data JPA
- Jakarta Validation
- Spring Web

## Consideraciones
- La API implementa validación de datos para asegurar la integridad de la información
- Todos los endpoints requieren que los IDs de usuario y curso sean válidos
- La paginación es configurable a través de parámetros en la URL
