# Observabilidad - Template Spring Boot

## 🚀 Ejecución completa (recomendado)

## 🟢 Levanta toda la plataforma (app + base de datos + observabilidad):

```bash
docker compose -f compose.yml -f compose.observability.yml up --build
```

## ✅ Verificación manual

1. Estado de la aplicación

   `http://localhost:8080/actuator/health`

   Respuesta esperada:

   ```json
   {"status":"UP"}
   ```

2. Métricas (Prometheus)

   `http://localhost:8080/actuator/prometheus`

   Ejemplos de métricas:

   - `http_server_requests_seconds`
   - `jvm_memory_used_bytes`

3. Trazas (Jaeger)

   `http://localhost:16686`

   Pasos:

   - Seleccionar servicio: `template`
   - Click en Find Traces

4. Grafana

   `http://localhost:3000`

   Credenciales:

   - `user: admin`
   - `pass: admin`

## 📊 Dashboard RED (Grafana)

Ubicación:

`observability/grafana/dashboards/red-dashboard-template.json`

Para usarlo:

- Abrir Grafana
- Ir a (+) → Import dashboard
- Subir el archivo red-dashboard-template.json o pegar su contenido

Incluye:

- Request Rate
- Error Rate (5xx)
- Latencia (p95)

## 🛑 Apagar entorno

```bash
docker compose -f compose.yml -f compose.observability.yml down
```

## 🐳 Ejecución aislada de la app (debug)

Solo para pruebas puntuales sin observabilidad.

Build

```bash
docker build -t template-app .
```

Run

```bash
docker run -p 8080:8080 template-app
```

Verificación:

`http://localhost:8080/actuator/health`

## 🔍 Verificación de imagen (multi-stage)

Validar que la imagen final no incluye herramientas de build:

```bash
docker ps
docker exec -it <container_id> sh
gradle -v
```

Resultado esperado:

```text
gradle: not found
```
