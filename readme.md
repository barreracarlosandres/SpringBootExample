
## Paso 3

### Objetivo
- Implementar un ejemplo básico de APIRest de un Post con datos desde un arreglo
- Se separó en un objeto InitialPostsData el arreglo donde se tiene la información base
- Se ajustaron los Test separando pruebas unitarias de pruebas funcionales

### Métodos implementados
| Método (podría con postman) | Cómo usarlo                                                                                                           | Retorna                                              |
|-----------------------------|-----------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| GET                         | localhost:8090/posts                                                                                                  | Muestra por pantalla todos los posts                 |
| GET                         | localhost:8090/posts/1                                                                                                | Muestra sólo la información del Post con id=1        |
| PUT                         | localhost:8090/posts<br/>{<br/>"postId":1,<br/>"title":"Título de prueba",<br/>"buddy":"Texto del post"<br/>}         | Ingresar un nuevo post                               |
| PUT                         | localhost:8090/posts/1<br/>{<br/>"postId":1,<br/>"title":"Título de prueba",<br/>"buddy":"Nuevo Texto del post"<br/>} | actualizar un un Post según el id ingresado |
| DELETE                      | localhost:8090/posts/1                                                                                                | Elimina el Post con el id 1                          |

### ¿Cómo consultar los datos?
- Se cambió el puerto del tomcat por el puerto 8090
- ingresar en navegador localhost:8090/posts


## Tecnología usada
- OpenJDK 1.8
- Gradle 7.4.1
- Spring boot 2.7.1
- Lombok 1.18.24
- Junit 5.8.2