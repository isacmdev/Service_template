# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 24/04/2026, 10:31:15 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** e732d65 - feat(pipeline) test en normalidad y codigo listo. (16 hours ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 10
- **📝 Nuevos:** 8
- **✏️ Modificados:** 2
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +62
- **Líneas eliminadas:** -35
- **Balance neto:** +27 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| 🆕 | `coverage-report.md` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/controller/WidgetControllerTest.java` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/domain/WidgetConflictProblemTest.java` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/infrastructure/ProblemExceptionHandlerTest.java` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/infrastructure/repository/DatabaseExceptionTest.java` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/infrastructure/repository/WidgetRepositoryTest.java` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/mapper/WidgetMapperDtoTest.java` | nuevo | -0 | 0 |
| 🆕 | `src/test/java/proyect/template/unit/repository/WidgetEntityMapperTest.java` | nuevo | -0 | 0 |
| ✏️ | `build.gradle` | +60 | -33 | +27 |
| ✏️ | `.github/workflows/ci.yml` | +2 | -2 | 0 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (10)</summary>

**🆕 Nuevos:**
```
coverage-report.md
src/test/java/proyect/template/unit/controller/WidgetControllerTest.java
src/test/java/proyect/template/unit/domain/WidgetConflictProblemTest.java
src/test/java/proyect/template/unit/infrastructure/ProblemExceptionHandlerTest.java
src/test/java/proyect/template/unit/infrastructure/repository/DatabaseExceptionTest.java
src/test/java/proyect/template/unit/infrastructure/repository/WidgetRepositoryTest.java
src/test/java/proyect/template/unit/mapper/WidgetMapperDtoTest.java
src/test/java/proyect/template/unit/repository/WidgetEntityMapperTest.java
```

**✏️ Modificados:**
```
build.gradle
.github/workflows/ci.yml
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. 🆕 `coverage-report.md`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```md
# 📊 Informe de Cobertura de Tests — `template`

> **Fecha de análisis:** 24 de abril de 2026
> **Herramienta:** JaCoCo 0.8.11
> **Umbral configurado:** 80 % (instrucciones)
> **Framework de tests:** JUnit 5 + Mockito + Testcontainers

---

## 1. Resumen Ejecutivo

| Métrica          | Cubiertas | Perdidas | Total | **Cobertura %** | Estado |
|------------------|-----------|----------|-------|-----------------|--------|
| **Instrucciones**| 266       | 64       | 330   | **80.6 %**      | ✅ Pasa umbral |
| **Ramas (Branch)**| 5        | 3        | 8     | **62.5 %**      | ⚠️ Baja |
| **Líneas**        | 75       | 21       | 96    | **78.1 %**      | ⚠️ Cerca del umbral |
| **Complejidad**   | 20       | 10       | 30    | **66.7 %**      | ⚠️ Baja |
| **Métodos**       | 19       | 7        | 26    | **73.1 %**      | ⚠️ Bajo |
| **Clases**        | 8        | 2        | 10    | **80.0 %**      | ✅ Pasa umbral |

> ⚠️ La cobertura de instrucciones supera el umbral mínimo de 80% por muy poco margen (0.6 pp). Cualquier nueva clase sin tests podría hacer que falle la verificación de cobertura (`jacocoTestCoverageVerification`).

---

## 2. Cobertura por Paquete / Clase

### ✅ `proyect.template.application` — 100 %

| Clase | Instruc. | Ramas | Métodos | Estado |
|-------|----------|-------|---------|--------|
| `WidgetServiceUseCase` | 59/59 | 2/2 | 5/5 | ✅ Completo |

**Detalle:** Todos los métodos (`getById`, `create`, `update`) y sus caminos de error están cubiertos por `WidgetServiceUseCaseTest`. Es la clase mejor testeada del proyecto.

---

### ⚠️ `proyect.template.infrastructure.controller` — 82.4 % instrucciones / 60 % métodos

| Método | Instruc. Cubiertas | Estado |
|--------|--------------------|--------|
| `createWidget` | ✅ 16/16 | Cubierto (IT) |
| `getWidgetById` | ✅ 11/11 | Cubierto (IT) |
| `updateWidget` | ✅ 15/15 | Cubierto (IT) |
| `healthCheck` | ❌ 0/4 | **No cubierto** |
| `test500` | ❌ 0/5 | **No cubierto** |

**Problema:** No existe ningún test unitario dedicado al controlador. La cobertura proviene únicamente de los tests de integración. Los métodos `healthCheck` y `test500` no son invocados en ningún test.

---

### ⚠️ `proyect.template.infrastructure.exception` — 94.7 % instrucciones / 50 % ramas

| Clase | Instruc. | Ramas | Estado |
|-------|----------|-------|--------|
| `ProblemExceptionHandler` | 71/75 | 3/6 | ⚠️ Ramas parciales |

**Ramas no cubiertas:**
- `entity == null || entity.getBody() == null` → rama `true` (retorno anticipado con entidad nula)
- `span == null` → rama `true` (retorno anticipado sin span activo)

---

### ⚠️ `proyect.template.infrastructure.repository.repository` — 53.4 % instrucciones

| Método | Instruc. Cubiertas | Estado |
|--------|--------------------|--------|
| `findById` | 7/14 | ⚠️ Rama `catch DataAccessException` no cubierta |
| `save` | 12/25 | ⚠️ Ramas `catch DataIntegrityViolationException` y `catch DataAccessException` no cubiertas |
| `update` | 12/19 | ⚠️ Rama `catch DataAccessException` no cubierta |

**Problema:** No existen tests unitarios para `WidgetRepository`. Los paths felices están cubiertos por la integración, pero los bloques `catch` de excepciones de base de datos nunca se ejecutan.

---

### ❌ `proyect.template.domain.exception` — 52.9 % instrucciones

| Clase | Instruc. | Estado |
|-------|----------|--------|
| `WidgetNotFoundProblem` | ✅ 9/9 | Cubierto |
| `WidgetConflictProblem` | ❌ 0/8 | **0% — Sin cobertura** |

**Problema crítico:** `WidgetConflictProblem` nunca es instanciada en ningún test. El path que la lanza (en `WidgetRepository.save` cuando hay `DataIntegrityViolationException`) tampoco está cubierto.

---

### ❌ `proyect.template.infrastructure.repository.exception` — 0 %

| Clase | Instruc. | Estado |
|-------|----------|--------|
| `DatabaseException` | ❌ 0/5 | **0% — Sin cobertura** |

**Problema:** `DatabaseException` nunca es lanzada en los tests. Requiere tests que simulen fallos en la capa de acceso a datos.

---

### ⚠️ `proyect.template` (TemplateApplication) — 37.5 % instrucciones

| Método | Instruc. | Estado |
|--------|----------|--------|
| Constructor `<init>` | ✅ 3/3 | Cubierto (Spring lo instancia) |
| `main(String[])` | ❌ 0/5 | **No cubierto** |

**Nota:** Es aceptable no cubrir el método `main` en la mayoría de proyectos, ya que es el punto de entrada. Se puede excluir de JaCoCo si se desea.

---

### ✅ `proyect.template.infrastructure.mapper` — 87.5 % instrucciones

| Método | Estado |
|--------|--------|
| `toDomain` | ✅ Cubierto |
| `toResponse` | ✅ Cubierto |
| Constructor privado `<init>` | ❌ No cubierto (clase utilitaria) |

---

### ✅ `proyect.template.infrastructure.repository.mapper` — 90.9 % instrucciones

| Método | Estado |
|--------|--------|
| `toData` | ✅ Cubierto |
| `toDomain` | ✅ Cubierto |
| Constructor privado `<init>` | ❌ No cubierto (clase utilitaria) |

---

## 3. Inventario de Tests Existentes

| Clase de Test | Tipo | Clase Bajo Test | Escenarios Cubiertos |
|---------------|------|-----------------|----------------------|
| `WidgetServiceUseCaseTest` | **Unitario** | `WidgetServiceUseCase` | `getById` OK, `getById` NotFound, `getById` null, `create` timestamps, `update` OK, `update` NotFound |
| `WidgetControllerIT` | **Integración** | `WidgetController` + cadena completa | Crear+Obtener widget, Actualizar widget, 404 cuando no existe |
| `FlywayMigrationTest` | **Migración** | Esquema de BD | Idempotencia de migraciones, tablas/columnas/constraints |

---

## 4. Clases Sin Tests Unitarios Dedicados

| Clase | Cobertura Actual | Tipo de Test Faltante |
|-------|------------------|-----------------------|
| `WidgetController` | Parcial (IT) | Test unitario con `@WebMvcTest` + mock de `WidgetPortIn` |
| `WidgetRepository` | Parcial (IT) | Test unitario con mock de `WidgetJpaRepository` |
| `WidgetMapperDto` | 87.5% (IT) | Test unitario de mapper |
| `WidgetEntityMapper` | 90.9% (IT) | Test unitario de mapper |
| `ProblemExceptionHandler` | 94.7% (IT) | Test unitario para ramas null |
| `WidgetConflictProblem` | **0%** | Test unitario básico de construcción |
| `DatabaseException` | **0%** | Test unitario básico de construcción |

---

## 5. Áreas de Mejora Prioritarias

### 🔴 Prioridad Alta

#### 5.1 Test unitario para `WidgetRepository` — Cubrir manejo de excepciones

```java
// proyect.template.unit.infrastructure.WidgetRepositoryTest
@ExtendWith(MockitoExtension.class)
class WidgetRepositoryTest {

    @Mock
    private WidgetJpaRepository widgetJpaRepository;

    @InjectMocks
    private WidgetRepository widgetRepository;

    @Test
    void save_throwsWidgetConflictProblem_onDataIntegrityViolation() {
        Widget widget = Widget.builder().fullname("Duplicado").build();
        when(widgetJpaRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(WidgetConflictProblem.class, () -> widgetRepository.save(widget));
    }

    @Test
    void save_throwsDatabaseException_onDataAccessException() {
        Widget widget = Widget.builder().fullname("Error").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.save(widget));
    }

    @Test
    void findById_throwsDatabaseException_onDataAccessException() {
        UUID id = UUID.randomUUID();
        when(widgetJpaRepository.findById(id)).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.findById(id));
    }

    @Test
    void update_throwsDatabaseException_onDataAccessException() {
        UUID id = UUID.randomUUID();
        Widget widget = Widget.builder().fullname("Update").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.update(id, widget));
    }
}
```

#### 5.2 Cubrir `WidgetConflictProblem` y `DatabaseException`

```java
// proyect.template.unit.domain.WidgetConflictProblemTest
class WidgetConflictProblemTest {

    @Test
    void constructor_setsPropertiesCorrectly() {
        WidgetConflictProblem problem = new WidgetConflictProblem("Widget ya existe");

        assertEquals(Status.CONFLICT, problem.getStatus());
        assertEquals("Conflict", problem.getTitle());
        assertEquals("Widget ya existe", problem.getDetail());
        assertEquals(URI.create("/errors/conflict"), problem.getType());
    }
}

// proyect.template.unit.infrastructure.DatabaseExceptionTest
class DatabaseExceptionTest {

    @Test
    void constructor_setsMessageAndCause() {
        Throwable cause = new RuntimeException("causa original");
        DatabaseException ex = new DatabaseException("Error de DB", cause);

        assertEquals("Error de DB", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
```

---

### 🟡 Prioridad Media

#### 5.3 Test unitario para `WidgetController` con `@WebMvcTest`

```java
// proyect.template.unit.controller.WidgetControllerTest
@WebMvcTest(WidgetController.class)
class WidgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetPortIn widgetPortIn;

    @Test
    void healthCheck_returns200() throws Exception {
        mockMvc.perform(get("/healthz"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void test500_returns500() throws Exception {
        mockMvc.perform(get("/test-500"))
               .andExpect(status().isInternalServerError());
    }

    @Test
    void createWidget_returns400_whenNameIsBlank() throws Exception {
        mockMvc.perform(post("/v1/widgets")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"fullname\":\"\"}"))
               .andExpect(status().isBadRequest());
    }

    @Test
    void getWidgetById_returns404_whenNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(widgetPortIn.getById(id)).thenThrow(new WidgetNotFoundProblem(id));

        mockMvc.perform(get("/v1/widgets/" + id))
               .andExpect(status().isNotFound());
    }
}
```

#### 5.4 Test unitario para `ProblemExceptionHandler` — Ramas null

```java
// proyect.template.unit.infrastructure.ProblemExceptionHandlerTest
@ExtendWith(MockitoExtension.class)
class ProblemExceptionHandlerTest {

    @Mock
    private Tracer tracer;

    @InjectMocks
    private ProblemExceptionHandler handler;

    @Test
    void process_returnsEntityUnchanged_whenEntityIsNull() {
        ResponseEntity<Problem> result = handler.process(null);
        assertNull(result);
    }

    @Test
    void process_returnsEntityUnchanged_whenNoActiveSpan() {
        ResponseEntity<Problem> entity = ResponseEntity.ok(Problem.valueOf(Status.OK));
        when(tracer.currentSpan()).thenReturn(null);

        ResponseEntity<Problem> result = handler.process(entity);
        assertEquals(entity, result);
    }

    @Test
    void process_addsTraceInfo_whenSpanIsActive() {
        // Verifica que traceId y spanId se añaden al Problem response
    }
}
```

#### 5.5 Tests unitarios para Mappers

```java
// proyect.template.unit.mapper.WidgetMapperDtoTest
class WidgetMapperDtoTest {

    @Test
    void toDomain_mapsFullnameCorrectly() {
        WidgetRequestDto dto = new WidgetRequestDto("Test Widget");
        Widget result = WidgetMapperDto.toDomain(dto);

        assertEquals("Test Widget", result.getFullname());
        assertNull(result.getId());
    }

    @Test
    void toResponse_mapsAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        Widget widget = Widget.builder().id(id).fullname("Test").createdAt(now).updatedAt(now).build();

        WidgetResponseDto result = WidgetMapperDto.toResponse(widget);

        assertEquals(id, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(now, result.getCreatedAt());
        assertEquals(now, result.getUpdatedAt());
    }
}
```

---

### 🟢 Prioridad Baja

#### 5.6 Excluir clases del cálculo de cobertura

Considera excluir de JaCoCo las clases que no tienen lógica de negocio:

```groovy
// build.gradle
jacocoTestReport {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: it, exclude: [
                'proyect/template/TemplateApplication.class',        // main()
                'proyect/template/**/dto/**',                        // DTOs Lombok
                'proyect/template/**/entity/**',                     // Entidades Lombok
                'proyect/template/**/ports/**',                      // Interfaces
            ])
        }))
    }
}
```

#### 5.7 Añadir test de integración para error de conflicto (409)

```java
// En WidgetControllerIT
@Test
void shouldReturn409_whenCreatingDuplicateWidget() {
    WidgetRequestDto request = new WidgetRequestDto();
    request.setFullname("Widget Duplicado");
    restTemplate.postForEntity("/v1/widgets", request, WidgetResponseDto.class);

    // Segunda creación con mismo nombre si hay constraint unique
    ResponseEntity<String> secondResponse =
        restTemplate.postForEntity("/v1/widgets", request, String.class);

    assertEquals(HttpStatus.CONFLICT, secondResponse.getStatusCode());
}
```

---

## 6. Cobertura Esperada Post-Mejoras

| Métrica | Actual | Esperada (tras mejoras) | Δ |
|---------|--------|------------------------|---|
| Instrucciones | 80.6 % | ~90 % | +9.4 pp |
| Ramas | 62.5 % | ~85 % | +22.5 pp |
| Líneas | 78.1 % | ~88 % | +9.9 pp |
| Complejidad | 66.7 % | ~82 % | +15.3 pp |
| Métodos | 73.1 % | ~88 % | +14.9 pp |

---

## 7. Resumen de Acciones Recomendadas

| # | Acción | Impacto | Esfuerzo | Prioridad |
|---|--------|---------|----------|-----------|
| 1 | Crear `WidgetRepositoryTest` con mocks de JPA | +8-10 pp instrucciones | Bajo | 🔴 Alta |
| 2 | Crear `WidgetConflictProblemTest` | +2 pp clases | Muy bajo | 🔴 Alta |
| 3 | Crear `DatabaseExceptionTest` | +1.5 pp instrucciones | Muy bajo | 🔴 Alta |
| 4 | Crear `WidgetControllerTest` con `@WebMvcTest` | +3-4 pp métodos | Medio | 🟡 Media |
| 5 | Crear `ProblemExceptionHandlerTest` para ramas null | +5 pp ramas | Bajo | 🟡 Media |
| 6 | Crear `WidgetMapperDtoTest` y `WidgetEntityMapperTest` | +1 pp instrucciones | Muy bajo | 🟡 Media |
| 7 | Añadir test IT para 409 Conflict | Cobertura IT | Bajo | 🟢 Baja |
| 8 | Excluir DTOs/entidades/interfaces de JaCoCo | Mejora métricas reportadas | Muy bajo | 🟢 Baja |

---

*Informe generado automáticamente con base en el reporte JaCoCo en `build/reports/jacoco/test/jacocoTestReport.xml`.*


```

---

### 2. 🆕 `src/test/java/proyect/template/unit/domain/WidgetConflictProblemTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.domain;

import org.junit.jupiter.api.Test;
import proyect.template.domain.exception.WidgetConflictProblem;
import org.zalando.problem.Status;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WidgetConflictProblemTest {

    @Test
    void constructor_setsPropertiesCorrectly() {
        WidgetConflictProblem problem = new WidgetConflictProblem("Widget ya existe");

        assertEquals(Status.CONFLICT, problem.getStatus());
        assertEquals("Conflict", problem.getTitle());
        assertEquals("Widget ya existe", problem.getDetail());
        assertEquals(URI.create("/errors/conflict"), problem.getType());
    }
}


```

---

### 3. 🆕 `src/test/java/proyect/template/unit/controller/WidgetControllerTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import proyect.template.infrastructure.controller.WidgetController;
import proyect.template.domain.ports.WidgetPortIn;
import proyect.template.domain.exception.WidgetNotFoundProblem;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WidgetController.class)
class WidgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetPortIn widgetPortIn;

    @Test
    void healthCheck_returns200() throws Exception {
        mockMvc.perform(get("/healthz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void test500_returns500() throws Exception {
        mockMvc.perform(get("/test-500"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createWidget_returns400_whenNameIsBlank() throws Exception {
        mockMvc.perform(post("/v1/widgets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fullname\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWidgetById_returns404_whenNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(widgetPortIn.getById(id)).thenThrow(new WidgetNotFoundProblem(id));

        mockMvc.perform(get("/v1/widgets/" + id))
                .andExpect(status().isNotFound());
    }
}


```

---

### 4. 🆕 `src/test/java/proyect/template/unit/infrastructure/repository/WidgetRepositoryTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyect.template.infrastructure.repository.exception.DatabaseException;
import proyect.template.infrastructure.repository.repository.WidgetRepository;
import proyect.template.infrastructure.repository.repository.WidgetJpaRepository;
import proyect.template.domain.entity.Widget;
import proyect.template.domain.exception.WidgetConflictProblem;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WidgetRepositoryTest {

    @Mock
    private WidgetJpaRepository widgetJpaRepository;

    @InjectMocks
    private WidgetRepository widgetRepository;

    @Test
    void save_throwsWidgetConflictProblem_onDataIntegrityViolation() {
        Widget widget = Widget.builder().fullname("Duplicado").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataIntegrityViolationException("constraint"));

        assertThrows(WidgetConflictProblem.class, () -> widgetRepository.save(widget));
    }

    @Test
    void save_throwsDatabaseException_onDataAccessException() {
        Widget widget = Widget.builder().fullname("Error").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.save(widget));
    }

    @Test
    void findById_throwsDatabaseException_onDataAccessException() {
        UUID id = UUID.randomUUID();
        when(widgetJpaRepository.findById(id)).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.findById(id));
    }

    @Test
    void update_throwsDatabaseException_onDataAccessException() {
        UUID id = UUID.randomUUID();
        Widget widget = Widget.builder().fullname("Update").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.update(id, widget));
    }
}



```

---

### 5. 🆕 `src/test/java/proyect/template/unit/infrastructure/repository/DatabaseExceptionTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.infrastructure.repository;

import org.junit.jupiter.api.Test;
import proyect.template.infrastructure.repository.exception.DatabaseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseExceptionTest {

    @Test
    void constructor_setsMessageAndCause() {
        Throwable cause = new RuntimeException("causa original");
        DatabaseException ex = new DatabaseException("Error de DB", cause);

        assertEquals("Error de DB", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}


```

---

### 6. 🆕 `src/test/java/proyect/template/unit/mapper/WidgetMapperDtoTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.mapper;

import org.junit.jupiter.api.Test;
import proyect.template.infrastructure.dto.WidgetRequestDto;
import proyect.template.infrastructure.dto.WidgetResponseDto;
import proyect.template.infrastructure.mapper.WidgetMapperDto;
import proyect.template.domain.entity.Widget;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WidgetMapperDtoTest {

    @Test
    void toDomain_mapsFullnameCorrectly() {
        WidgetRequestDto dto = new WidgetRequestDto("Test Widget");
        Widget result = WidgetMapperDto.toDomain(dto);

        assertEquals("Test Widget", result.getFullname());
        assertNull(result.getId());
    }

    @Test
    void toResponse_mapsAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        Widget widget = Widget.builder().id(id).fullname("Test").createdAt(now).updatedAt(now).build();

        WidgetResponseDto result = WidgetMapperDto.toResponse(widget);

        assertEquals(id, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(now, result.getCreatedAt());
        assertEquals(now, result.getUpdatedAt());
    }
}


```

---

### 7. 🆕 `src/test/java/proyect/template/unit/infrastructure/ProblemExceptionHandlerTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.infrastructure;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import proyect.template.infrastructure.exception.ProblemExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProblemExceptionHandlerTest {

    @Mock
    private Tracer tracer;

    @Mock
    private Span span;

    @InjectMocks
    private ProblemExceptionHandler handler;

    @Test
    void process_returnsEntityUnchanged_whenEntityIsNull() {
        ResponseEntity<Problem> result = handler.process(null);
        assertNull(result);
    }

    @Test
    void process_returnsEntityUnchanged_whenNoActiveSpan() {
        ResponseEntity<Problem> entity = ResponseEntity.ok(Problem.valueOf(Status.OK));
        when(tracer.currentSpan()).thenReturn(null);

        ResponseEntity<Problem> result = handler.process(entity);
        assertEquals(entity, result);
    }

    @Test
    void process_addsTraceInfo_whenSpanIsActive() {
        Problem problem = Problem.builder()
                .withTitle("Test")
                .withStatus(Status.BAD_REQUEST)
                .withDetail("detail")
                .build();

        ResponseEntity<Problem> entity = ResponseEntity.status(400).body(problem);

        Tracer.SpanInScope scope = null; // not used, only mocking span

        when(tracer.currentSpan()).thenReturn(span);
        when(span.context()).thenReturn(new Span.Context() {
            @Override
            public String traceId() { return "trace-1"; }

            @Override
            public String spanId() { return "span-1"; }
        });

        ResponseEntity<Problem> result = handler.process(entity);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertTrue(result.getBody().getParameters().containsKey("traceId"));
        assertTrue(result.getBody().getParameters().containsKey("spanId"));
    }
}


```

---

### 8. 🆕 `src/test/java/proyect/template/unit/repository/WidgetEntityMapperTest.java`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```java
package proyect.template.unit.repository;

import org.junit.jupiter.api.Test;
import proyect.template.infrastructure.repository.entity.WidgetData;
import proyect.template.infrastructure.repository.mapper.WidgetEntityMapper;
import proyect.template.domain.entity.Widget;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WidgetEntityMapperTest {

    @Test
    void toData_and_toDomain_roundtrip() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        Widget widget = Widget.builder().id(id).fullname("X").createdAt(now).updatedAt(now).build();

        WidgetData data = WidgetEntityMapper.toData(widget);
        assertEquals(id, data.getId());
        assertEquals("X", data.getFullname());

        Widget domain = WidgetEntityMapper.toDomain(data);
        assertEquals(id, domain.getId());
        assertEquals("X", domain.getFullname());
    }
}


```

---

### 9. ✏️ `build.gradle`

**Estado:** modificado
**Cambios:** +60 / -33

```diff
-	id 'java'
-	id 'org.springframework.boot' version '3.3.5'
-	id 'io.spring.dependency-management' version '1.1.7'
-	id 'org.flywaydb.flyway' version '10.17.0'
+    id 'java'
+    id 'org.springframework.boot' version '3.3.5'
+    id 'io.spring.dependency-management' version '1.1.7'
+    id 'org.flywaydb.flyway' version '10.17.0'
+    id 'jacoco'
-	toolchain {
-		languageVersion = JavaLanguageVersion.of(17)
-	}
+    toolchain {
+        languageVersion = JavaLanguageVersion.of(17)
+    }
+}
+
+jacoco {
+    toolVersion = "0.8.11"
-	compileOnly {
-		extendsFrom annotationProcessor
-	}
+    compileOnly {
+        extendsFrom annotationProcessor
+    }
-	mavenCentral()
+    mavenCentral()
-	implementation 'org.springframework.boot:spring-boot-starter-web'
-	implementation 'org.springframework.boot:spring-boot-starter-validation'
-	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
-	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
-	implementation 'org.zalando:problem-spring-web-starter:0.29.1'
-	implementation 'net.logstash.logback:logstash-logback-encoder:7.4'
-	implementation 'org.flywaydb:flyway-core:10.17.0'
-	implementation 'org.flywaydb:flyway-database-postgresql:10.17.0'
-	implementation 'io.micrometer:micrometer-tracing-bridge-otel'
-	implementation 'org.springframework.boot:spring-boot-starter-actuator'
-	implementation 'io.micrometer:micrometer-registry-prometheus'
+    implementation 'org.springframework.boot:spring-boot-starter-web'
+    implementation 'org.springframework.boot:spring-boot-starter-validation'
+    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
+    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
+    implementation 'org.zalando:problem-spring-web-starter:0.29.1'
+    implementation 'net.logstash.logback:logstash-logback-encoder:7.4'
+    implementation 'org.flywaydb:flyway-core:10.17.0'
+    implementation 'org.flywaydb:flyway-database-postgresql:10.17.0'
+    implementation 'io.micrometer:micrometer-tracing-bridge-otel'
+    implementation 'org.springframework.boot:spring-boot-starter-actuator'
+    implementation 'io.micrometer:micrometer-registry-prometheus'
-	compileOnly 'org.projectlombok:lombok'
+    compileOnly 'org.projectlombok:lombok'
-	runtimeOnly 'io.opentelemetry:opentelemetry-exporter-otlp'
-	runtimeOnly 'org.postgresql:postgresql'
+    runtimeOnly 'io.opentelemetry:opentelemetry-exporter-otlp'
+    runtimeOnly 'org.postgresql:postgresql'
-	annotationProcessor 'org.projectlombok:lombok'
+    annotationProcessor 'org.projectlombok:lombok'
-	testImplementation 'org.springframework.boot:spring-boot-starter-test'
-	testImplementation 'org.springframework.boot:spring-boot-testcontainers'
-	testImplementation 'org.testcontainers:junit-jupiter:1.19.8'
-	testImplementation 'org.testcontainers:postgresql:1.19.8'
-	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
+    testImplementation 'org.springframework.boot:spring-boot-starter-test'
+    testImplementation 'org.springframework.boot:spring-boot-testcontainers'
+    testImplementation 'org.testcontainers:junit-jupiter:1.19.8'
+    testImplementation 'org.testcontainers:postgresql:1.19.8'
+    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
-	useJUnitPlatform()
-}
+    useJUnitPlatform()
+    finalizedBy jacocoTestReport
+}
+
+jacocoTestReport {
+    dependsOn test
+
+    reports {
+        xml.required = true
+        html.required = true
+    }
+}
+
+jacocoTestCoverageVerification {
+    violationRules {
+        rule {
+            limit {
+                minimum = 0.80
+            }
+        }
+    }
+}
+
+check.dependsOn jacocoTestCoverageVerification
```

---

### 10. ✏️ `.github/workflows/ci.yml`

**Estado:** modificado
**Cambios:** +2 / -2

```diff
-      - name: Build and test
-        run: ./gradlew clean test --no-daemon
+      - name: Build, test and verify coverage
+        run: ./gradlew clean check --no-daemon
```

---

*Última actualización: 24/04/2026, 10:31:15*
