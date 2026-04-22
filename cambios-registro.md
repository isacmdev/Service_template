# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 22/04/2026, 17:56:27 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 3f5be79 - feat(docker): agrega compose con postgres y ajusta datasource interno (73 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 4
- **📝 Nuevos:** 0
- **✏️ Modificados:** 4
- **🗑️ Eliminados:** 0
- **✅ En staging:** 4 (listos para commit)
- **Líneas añadidas:** +80
- **Líneas eliminadas:** -1
- **Balance neto:** +79 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✅ ✏️ | `compose.observability.yml` | +48 | -0 | +48 |
| ✅ ✏️ | `otel/otel-collector-config.yml` | +23 | -0 | +23 |
| ✅ ✏️ | `prometheus/prometheus.yml` | +8 | -0 | +8 |
| ✅ ✏️ | `src/main/resources/application.properties` | +1 | -1 | 0 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (4)</summary>

**✅ Modificados (staged):**
```
compose.observability.yml
otel/otel-collector-config.yml
prometheus/prometheus.yml
src/main/resources/application.properties
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `compose.observability.yml` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +48 / -0

```diff
+services:
+  app:
+    environment:
+      MANAGEMENT_OTLP_TRACING_ENDPOINT: http://otel-collector:4318/v1/traces
+  otel-collector:
+    image: otel/opentelemetry-collector-contrib:latest
+    container_name: otel-collector
+    command: ["--config=/etc/otel-collector-config.yml"]
+    volumes:
+      - ./otel/otel-collector-config.yml:/etc/otel-collector-config.yml:ro
+    ports:
+      - "4318:4318"
+      - "4317:4317"
+    networks:
+      - app-net
+
+  jaeger:
+    image: jaegertracing/all-in-one:latest
+    ports:
+      - "16686:16686"
+    networks:
+      - app-net
+
+  prometheus:
+    image: prom/prometheus:latest
+    container_name: prometheus
+    volumes:
+      - ./prometheus/prometheus.yml:/etc/prometheus/prometheus.yml:ro
+    ports:
+      - "9090:9090"
+    networks:
+      - app-net
+
+  grafana:
+    image: grafana/grafana:latest
+    container_name: grafana
+    environment:
+      GF_SECURITY_ADMIN_USER: admin
+      GF_SECURITY_ADMIN_PASSWORD: admin
+    ports:
+      - "3000:3000"
+    networks:
+      - app-net
+    depends_on:
+      - prometheus
+
+networks:
+  app-net:
```

---

### 2. ✏️ `otel/otel-collector-config.yml` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +23 / -0

```diff
+receivers:
+  otlp:
+    protocols:
+      http:
+        endpoint: 0.0.0.0:4318
+      grpc:
+        endpoint: 0.0.0.0:4317
+
+processors:
+  batch:
+
+exporters:
+  otlp:
+    endpoint: jaeger:4317
+    tls:
+      insecure: true
+
+service:
+  pipelines:
+    traces:
+      receivers: [otlp]
+      processors: [batch]
+      exporters: [otlp]
```

---

### 3. ✏️ `prometheus/prometheus.yml` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +8 / -0

```diff
+global:
+  scrape_interval: 5s
+
+scrape_configs:
+  - job_name: "template"
+    metrics_path: "/actuator/prometheus"
+    static_configs:
+      - targets: ["template-app:8080"]
```

---

### 4. ✏️ `src/main/resources/application.properties` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +1 / -1

```diff
-management.otlp.tracing.endpoint=http://localhost:4318/v1/traces
+management.otlp.tracing.endpoint=http://otel-collector:4318/v1/traces
```

---

*Última actualización: 22/04/2026, 17:56:27*
