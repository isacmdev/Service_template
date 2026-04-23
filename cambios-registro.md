# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 23/04/2026, 18:19:42 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 1a27b1c - feat(pipeline)  rmper un test o el código (para validar CI). (2 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 2
- **📝 Nuevos:** 0
- **✏️ Modificados:** 2
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +3
- **Líneas eliminadas:** -5
- **Balance neto:** -2 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `src/test/java/proyect/template/integration/WidgetControllerIT.java` | +3 | -3 | 0 |
| ✏️ | `src/main/java/proyect/template/application/WidgetServiceUseCase.java` | +0 | -2 | -2 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (2)</summary>

**✏️ Modificados:**
```
src/test/java/proyect/template/integration/WidgetControllerIT.java
src/main/java/proyect/template/application/WidgetServiceUseCase.java
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `src/test/java/proyect/template/integration/WidgetControllerIT.java`

**Estado:** modificado
**Cambios:** +3 / -3

```diff
-                    .withDatabaseName("template")
+                    .withDatabaseName("tempate")
-        WidgetRequestDto request = new WidgetRequestDto();
+        WidgetRequestDto request = new WidgetRequesDto();
-                restTemplate.postForEntity("/v1/widgets", createRequest, WidgetResponseDto.class);
+                restTemplate.postForEntity("/v1/widets", createRequest, WidgetResponseDto.class);
```

---

### 2. ✏️ `src/main/java/proyect/template/application/WidgetServiceUseCase.java`

**Estado:** modificado
**Cambios:** +0 / -2

```diff
-    int x = "esto rompe";
-
```

---

*Última actualización: 23/04/2026, 18:19:42*
