# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 22/04/2026, 18:11:34 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 8355a2a - feat(observability): agrega stack OTel/Prometheus/Grafana y ajusta endpoint OTLP (13 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 1
- **📝 Nuevos:** 0
- **✏️ Modificados:** 1
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +66
- **Líneas eliminadas:** -50
- **Balance neto:** +16 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `README.md` | +66 | -50 | +16 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (1)</summary>

**✏️ Modificados:**
```
README.md
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `README.md`

**Estado:** modificado
**Cambios:** +66 / -50

```diff
-## Observabilidad
+# Ejecución Local con Observabilidad
-### Métricas (Prometheus)
-Endpoint disponible en:
-[http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)
-[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
+## 🚀 Ejecución completa (recomendado)
-**Permite visualizar:**
-- Request Rate
-- Error Rate (por código HTTP)
-- Latencia de requests
+Levanta toda la plataforma (app + base de datos + observabilidad):
-### Trazas (Jaeger)
-UI disponible en:
-[http://localhost:16686/search](http://localhost:16686/search)
+```bash
+docker compose -f compose.yml -f compose.observability.yml up --build
+```
-**Permite visualizar:**
-- Trazas distribuidas
-- Spans por request
+## ✅ Verificación manual
-### Dashboards (Grafana)
+1. Estado de la aplicación
-Se incluye un dashboard RED exportado en:
+   `http://localhost:8080/actuator/health`
-observability/grafana/dashboards/red-dashboard-template.json
+   Respuesta esperada:
-Este dashboard permite visualizar:
+   ```json
+   {"status":"UP"}
+   ```
-- Request Rate
-- Error Rate (5xx)
-- Latencia (p95)
+2. Métricas (Prometheus)
-Para usarlo:
+   `http://localhost:8080/actuator/prometheus`
-1. Abrir Grafana
-2. Ir a **(+) → Import dashboard**
-3. Subir el archivo `red-dashboard-template.json` o pegar su contenido
-4. Seleccionar Prometheus como datasource
+   Ejemplos de métricas:
-> Nota: es necesario tener Prometheus configurado como datasource en Grafana.
+   - `http_server_requests_seconds`
+   - `jvm_memory_used_bytes`
+3. Trazas (Jaeger)
-## 🐳 Docker
+   `http://localhost:16686`
-### Build de la imagen
+   Pasos:
-```bash
-docker build -t template-app .
-```
+   - Seleccionar servicio: `template`
+   - Click en Find Traces
+
+4. Grafana
+
+   `http://localhost:3000`
+
+   Credenciales:
----
+   - `user: admin`
+   - `pass: admin`
-### Ejecutar la app
+## 📊 Dashboard RED (Grafana)
+
+Ubicación:
+
+`observability/grafana/dashboards/red-dashboard-template.json`
+
+Para usarlo:
+
+- Abrir Grafana
+- Ir a (+) → Import dashboard
+- Subir el archivo red-dashboard-template.json o pegar su contenido
+
+Incluye:
+
+- Request Rate
+- Error Rate (5xx)
+- Latencia (p95)
+
+## 🛑 Apagar entorno
-docker run -p 8080:8080 template-app
+docker compose -f compose.yml -f compose.observability.yml down
-App disponible en:
+## 🐳 Ejecución aislada de la app (debug)
-http://localhost:8080
+Solo para pruebas puntuales sin observabilidad.
----
+Build
-### Verificar que funciona
+```bash
+docker build -t template-app .
+```
-Health check:
+Run
-http://localhost:8080/actuator/health
+```bash
+docker run -p 8080:8080 template-app
+```
-Respuesta esperada:
+Verificación:
-```json
-{"status":"UP"}
-```
+`http://localhost:8080/actuator/health`
----
+## 🔍 Verificación de imagen (multi-stage)
-### Verificar multi-stage (sin Gradle en runtime)
+Validar que la imagen final no incluye herramientas de build:
-```
+```text
----
```

---

*Última actualización: 22/04/2026, 18:11:34*
