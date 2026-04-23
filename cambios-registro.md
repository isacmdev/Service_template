# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 23/04/2026, 18:20:33 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** c34119b - feat(pipeline)  romper un test (para validar CI). (28 seconds ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 1
- **📝 Nuevos:** 0
- **✏️ Modificados:** 1
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +3
- **Líneas eliminadas:** -3
- **Balance neto:** 0 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `src/test/java/proyect/template/integration/WidgetControllerIT.java` | +3 | -3 | 0 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (1)</summary>

**✏️ Modificados:**
```
src/test/java/proyect/template/integration/WidgetControllerIT.java
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `src/test/java/proyect/template/integration/WidgetControllerIT.java`

**Estado:** modificado
**Cambios:** +3 / -3

```diff
-                    .withDatabaseName("tempate")
+                    .withDatabaseName("template")
-        WidgetRequestDto request = new WidgetRequesDto();
+        WidgetRequestDto request = new WidgetRequestDto();
-                restTemplate.postForEntity("/v1/widets", createRequest, WidgetResponseDto.class);
+                restTemplate.postForEntity("/v1/widgets", createRequest, WidgetResponseDto.class);
```

---

*Última actualización: 23/04/2026, 18:20:33*
