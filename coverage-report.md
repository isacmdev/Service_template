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

