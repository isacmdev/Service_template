# Rollback técnico de migraciones

## 1. Contexto

Este proyecto utiliza Flyway para versionar y ejecutar migraciones de base de datos.

Flyway trabaja bajo un modelo **forward-only**, por lo que no existe rollback automático (`down`).  
La reversión de cambios se maneja mediante estrategias controladas según el tipo de migración y su impacto.

---

## 2. Qué se revierte

Dependiendo de la migración, pueden verse afectados:

### Estructura
- tablas (creación o eliminación)
- columnas (alta, baja o modificación)
- índices y constraints

### Datos
- actualizaciones masivas
- migraciones entre tablas
- transformaciones de valores

En este proyecto, los cambios típicos incluyen:
- creación de nuevas columnas
- ajustes en tipos de datos
- actualizaciones sobre registros existentes

---

## 3. Estrategias de rollback

### 3.1 Migración compensatoria (principal)

_Dado que Flyway no permite rollback automático, la forma estándar de revertir cambios es crear una nueva migración que deshaga el efecto de la anterior._

#### Ejemplo

Migración original:

```sql
-- V2__add_phone_number.sql
ALTER TABLE users ADD COLUMN phone_number VARCHAR(20);
```


Migración de reversión:
```sql
-- V3__rollback_phone_number.sql
ALTER TABLE users DROP COLUMN phone_number;
```

#### Ejecución del rollback
```bash
./gradlew flywayMigrate
```
#### **Consideraciones**
* Si la columna ya contiene datos, estos se perderán
* No se recomienda para cambios con información crítica
* Debe evaluarse antes de ejecutar en producción

### 3.2 Rollback manual

_Se utiliza cuando no es viable crear una migración compensatoria o se requiere una intervención inmediata._

#### **Procedimiento**

1. Identificar el cambio aplicado
2. Ejecutar SQL correctivo directamente en la base de datos

#### Ejemplo:
```sql
ALTER TABLE users DROP COLUMN phone_number;
```
3. Validar integridad de datos
4. Revisar logs de la aplicación

### 3.3 Restauración desde backup

_Se aplica en escenarios donde hubo pérdida de datos o inconsistencias graves._

#### **Procedimiento**
1. Detener la aplicación
2. Restaurar un backup de la base de datos
3. Levantar nuevamente los servicios
4. Validar estado del sistema

## 4. Qué no se puede revertir automáticamente

_Existen cambios que no pueden deshacerse de forma segura:_

* eliminación de columnas con datos
* transformaciones destructivas
* cambios que sobrescriben información sin respaldo
#### **Ejemplo**
```sql
UPDATE users SET status = 1 WHERE status = 'active';
```
Después de ejecutar esta operación, no es posible recuperar el valor original sin un backup previo.

## 5. Riesgos
* pérdida de datos
* inconsistencias entre aplicación y base de datos
* fallos en producción
* posibles tiempos de indisponibilidad


## 6. Validación post-rollback
_Después de cualquier rollback se debe verificar:_

* la aplicación inicia correctamente
* endpoints principales responden
* no hay errores en logs
* la estructura de la base de datos es consistente

## 7. Buenas prácticas
* realizar backup antes de migraciones críticas
* evitar cambios destructivos sin plan de reversión
* dividir migraciones complejas en pasos pequeños
* probar migraciones en entornos no productivos
* coordinar cambios de base de datos con despliegues de código

## 8. Comandos relevantes

Aplicar migraciones:

```bash
./gradlew flywayMigrate
```

Ver estado:

```bash
./gradlew flywayInfo
```
Validar migraciones:

```bash
./gradlew flywayValidate
```
Limpiar base de datos (solo desarrollo):

```bash
./gradlew flywayClean
```

## 9. Cuándo ejecutar rollback

_Se debe considerar rollback cuando:_

* el despliegue falla después de aplicar migraciones
* aparecen errores relacionados con cambios en base de datos
* se detectan inconsistencias en los datos
* el sistema presenta degradación tras la migración