# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 22/04/2026, 16:42:13 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 9d27af2 - feat(docker): agrega healthcheck de liveness y ajusta application.properties (79 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 2
- **📝 Nuevos:** 0
- **✏️ Modificados:** 2
- **🗑️ Eliminados:** 0
- **✅ En staging:** 2 (listos para commit)
- **Líneas añadidas:** +45
- **Líneas eliminadas:** -3
- **Balance neto:** +42 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✅ ✏️ | `compose.yml` | +43 | -0 | +43 |
| ✅ ✏️ | `src/main/resources/application.properties` | +2 | -3 | -1 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (2)</summary>

**✅ Modificados (staged):**
```
compose.yml
src/main/resources/application.properties
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `compose.yml` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +43 / -0

```diff
+version: "3.9"
+
+services:
+  postgres:
+    image: postgres:16
+    container_name: postgres
+    restart: unless-stopped
+    environment:
+      POSTGRES_DB: template
+      POSTGRES_USER: postgres
+      POSTGRES_PASSWORD: root
+    ports:
+      - "5432:5432"
+    volumes:
+      - postgres_data:/var/lib/postgresql/data
+    networks:
+      - app-net
+    healthcheck:
+      test: ["CMD-SHELL", "pg_isready -U postgres -d template"]
+      interval: 10s
+      timeout: 5s
+      retries: 5
+
+  app:
+    build: .
+    container_name: template-app
+    depends_on:
+      postgres:
+        condition: service_healthy
+    environment:
+      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/template
+      SPRING_DATASOURCE_USERNAME: postgres
+      SPRING_DATASOURCE_PASSWORD: root
+    ports:
+      - "8080:8080"
+    networks:
+      - app-net
+
+volumes:
+  postgres_data:
+
+networks:
+  app-net:
```

---

### 2. ✏️ `src/main/resources/application.properties` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +2 / -3

```diff
-spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/template
+spring.datasource.url=jdbc:postgresql://postgres:5432/template
-management.otlp.tracing.endpoint=http://localhost:4318/v1/traces
-
+management.otlp.tracing.endpoint=http://localhost:4318/v1/traces
```

---

*Última actualización: 22/04/2026, 16:42:13*
