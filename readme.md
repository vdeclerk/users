Para probar la aplicación luego de clonarla ejecutarla con
```bash
./mvnw spring-boot:run
```
Luego de esto se podrá acceder a la página de swagger [aquí](http://localhost:8080/swagger-ui/index.html)

Desde allí se puede ver la documentación y también probar la API.


La aplicación usa migraciones de [liquibase](https://www.liquibase.com/community) por lo que no es necesario inicializar la base de datos en memoria manualmente.
El script se encuentra en `src/main/resources/changelog/db.changelog.sql`

Para correr los test de unidad
```bash
./mvnw test
```

Para correr los test de unidad y los de integración
```bash
./mvnw verify
```

[Aquí](./diagram.png) está el diagrama de la solución.