# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 22/04/2026, 15:21:10 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 0d8080d - chfeat(dockerfile agrega Dockerfile y actualiza configuración y documentación (3 hours ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 2
- **📝 Nuevos:** 0
- **✏️ Modificados:** 2
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +4
- **Líneas eliminadas:** -1
- **Balance neto:** +3 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `Dockerfile` | +4 | -0 | +4 |
| ✏️ | `src/main/resources/application.properties` | +0 | -1 | -1 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (2)</summary>

**✏️ Modificados:**
```
Dockerfile
src/main/resources/application.properties
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `Dockerfile`

**Estado:** modificado
**Cambios:** +4 / -0

```diff
+RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
+
+HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
+  CMD curl -f http://localhost:8080/actuator/health/liveness || exit 1
```

---

### 2. ✏️ `src/main/resources/application.properties`

**Estado:** modificado
**Cambios:** +0 / -1

```diff
-
```

---

*Última actualización: 22/04/2026, 15:21:10*
