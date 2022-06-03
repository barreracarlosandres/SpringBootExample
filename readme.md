
## Paso 1

### Objetivo
- Implementar un ejemplo básico de APIRest de un Post con datos desde un arreglo

### Métodos implementados
| Método (podría ser postman) | Cómo usarlo                       | Retorna                                        |
|-----------------------------|-----------------------------------|------------------------------------------------|
| GET                         | localhost:8090/posts              | Mustra por pantalla todos los posts            |
| POST                        | localhost:8090/posts/1            | Muetra sólo la información del Post con id=1   |
| PUT                         | - localhost:8090/posts/1 | Ingresa modifica el Post según el id ingresado |
| DELETE                      | localhost:8090/posts/1            | Elimina el Post con el id 1                    |

### ¿Cómo consultar los datos?
- Se cambió el puerto del tomcat por el puerto 8090
- ingresar en navegador localhost:8090/posts


## Tecnología usada
- Java 1.8
- Gradle 2.7.0
- Lombok