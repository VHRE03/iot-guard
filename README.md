# iot-guard

Microservicio de monitoreo IoT de VHRE. Gestiona **readings** (lecturas de sensores) y
**audit-readings** (auditoria de lecturas consumidas) con CRUD
REST completo, soft-delete, auditoría automática y migraciones Flyway. Construido sobre
[`base-project-spring-boot-starter`](https://github.com/VHRE03/base-project-spring-boot)
(`BaseEntity`, `BaseDTO`, `BaseMapper`, `BaseRepository`, `BaseService/BaseServiceImpl`,
`BaseController`), con la estructura de Kafka lista para que el desarrollador implemente
la lógica de producción/consumo.

## Servicios del docker-compose

| Servicio | Imagen | Puerto en tu máquina | Para qué sirve |
|---|---|---|---|
| `postgres` | `postgres:16-alpine` | **5432** → 5432 | Base de datos `iotguard_db` (usuario `iotguard_user` / contraseña `iotguard_pass`) |
| `kafka` | `apache/kafka:latest` | **9092** → 9092 | Broker de mensajería (KRaft, broker único). **9092** es el listener para la aplicación que corre en tu máquina; el listener interno `kafka:29092` solo se usa entre contenedores (no se expone al host) y el `9093` es del controller interno |
| `kafka-ui` | `provectuslabs/kafka-ui:latest` | **8085** → 8080 | Interfaz web para inspeccionar el broker: topics, mensajes en vivo, consumer groups y LAG — <http://localhost:8085> |

### Puertos de la aplicación (fuera de Docker)

| Qué | URL |
|---|---|
| API REST (base) | <http://localhost:8080/api/v1/...> |
| Swagger UI | <http://localhost:8080/swagger-ui.html> |
| OpenAPI (JSON) | <http://localhost:8080/v3/api-docs> |
| Actuator (health) | <http://localhost:8080/actuator/health> |
| Kafka UI | <http://localhost:8085> |

> La app usa el **8080**; por eso Kafka UI se mapea al **8085**.

### Detalles de infraestructura

- **PostgreSQL** persiste en el volumen `postgres_data` (los datos sobreviven a `docker compose down`).
- **Kafka** corre sin volumen: sus topics son efímeros y se recrean al reiniciarlo. La
  aplicación todavía no declara topics (la `KafkaConfig` está vacía a propósito); los
  creará el desarrollador junto con la lógica de Kafka.
- El broker anuncia **dos listeners**: `localhost:9092` para clientes en tu máquina (la app)
  y `kafka:29092` para clientes dentro de la red de Docker (kafka-ui). Sin esto, la UI no
  podría conectarse porque el `localhost` del broker no es el mismo `localhost` de un contenedor.
- Credenciales del cluster en la UI: no requiere (broker local sin autenticación), cluster
  `iot-guard-local`.

## Cómo levantar todo

```bash
# 1. Infraestructura (postgres + kafka + kafka-ui)
docker compose up -d

# Opcional: esperar a que el broker este listo antes de arrancar la app
docker logs -f iot-guard-kafka       # hasta ver "Kafka Server started"

# 2. Aplicacion (requiere JDK 21)
./mvnw spring-boot:run
```

Detener todo:

```bash
docker compose down                # para los contenedores (datos de postgres se conservan)
docker compose down -v             # ademas borra los volumenes (resetea la BD por completo)
```

> **Requisito del starter:** el `pom.xml` consume `com.vhre:base-project-spring-boot-starter`
> desde GitHub Packages, que siempre exige autenticación. Se necesita `~/.m2/settings.xml` con
> un PAT **classic** (`ghp_...`, scope `read:packages`) en el `<server><id>github</id>` — ver
> el README del starter para el paso a paso.

## Esquema de base de datos (Flyway)

El esquema lo gestionan las migraciones de `src/main/resources/db/migration`; Hibernate solo
valida (`spring.jpa.hibernate.ddl-auto=validate`):

| Migración | Tabla | Contenido |
|---|---|---|
| `V1__create_readings_table.sql` | `readings` | `id` (UUID), `sensor_id`, `temperature`, `humidity`, auditoría y soft-delete |
| `V2__create_audit_readings_table.sql` | `audit_readings` | `id` (UUID), `sensor_id`, `temperature`, `humidity`, `timestamp`, `processed_at` (DEFAULT NOW()), auditoría y soft-delete |
| `V3__drop_timestamp_from_readings.sql` | `readings` | Elimina la columna `timestamp`: el instante de la lectura se representa con `created_at` |

Todas las entidades extienden `BaseEntity`: `id` UUID autogenerado, `created_at`/`updated_at`
(poblados automáticamente, sin `@EnableJpaAuditing`) y `is_deleted` (soft-delete gestionado
por el servicio base). En `readings` el momento de la lectura es `created_at`: el registro
se crea justo cuando llega el `POST`, así que ambos instantes coinciden (y `updated_at`
permite saber si una lectura fue corregida después).

## API REST

Cada módulo expone los endpoints generados por `BaseController`:

| Módulo | Endpoints |
|---|---|
| Readings | `/api/v1/readings`, `/api/v1/readings/{id}` — CRUD completo (`GET` paginado, `GET /{id}`, `POST` 201, `PUT /{id}`, `DELETE /{id}`) |
| AuditReadings | `/api/v1/audit-readings`, `/api/v1/audit-readings/{id}` — solo lectura y creación (`PUT`/`DELETE` responden 405; los registros de auditoría no se modifican ni borran por REST) |

Operaciones por recurso: `GET` (paginado, `?page=0&size=20&sort=field,asc`), `GET /{id}`,
`POST` (201), `PUT /{id}` y `DELETE /{id}` (soft-delete → 404 en consultas posteriores).

El manejo de errores es el mismo que en `fin-pay`: el `GlobalExceptionHandler` del starter
responde 404 (recurso inexistente), 400 (validación), 405 (endpoint deshabilitado),
409 (integridad) y 500 (inesperado).

Ejemplo — crear una lectura (el `id` UUID y la auditoría se generan automáticamente):

```json
POST /api/v1/readings
{
  "sensorId": "SENSOR-001",
  "temperature": 22.5,
  "humidity": 60.2
}
```

## Kafka (estructura lista, sin lógica)

- **Dependencia:** `spring-boot-starter-kafka` (la misma que usa `fin-pay`).
- **Paquetes:** `kafka/producer/` y `kafka/consumer/` están vacíos a propósito; el
  desarrollador añadirá ahí productores y consumidores.
- **`KafkaConfig`:** vacía (solo `@Configuration`); el desarrollador completará sus beans.
- **Sin `spring.kafka.*` en `application.properties`:** como en `fin-pay`, la app usa el
  `bootstrap-servers` por defecto (`localhost:9092`), que coincide con el listener
  `PLAINTEXT` del docker-compose.
