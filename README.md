## Observabilidad

### Métricas (Prometheus)
Endpoint disponible en:
[http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)
[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)


**Permite visualizar:**
- Request Rate
- Error Rate (por código HTTP)
- Latencia de requests

### Trazas (Jaeger)
UI disponible en:
[http://localhost:16686/search](http://localhost:16686/search)

**Permite visualizar:**
- Trazas distribuidas
- Spans por request

### Dashboards (Grafana)

Se incluye un dashboard RED exportado en:

observability/grafana/dashboards/red-dashboard-template.json

Este dashboard permite visualizar:

- Request Rate
- Error Rate (5xx)
- Latencia (p95)

Para usarlo:

1. Abrir Grafana
2. Ir a **(+) → Import dashboard**
3. Subir el archivo `red-dashboard-template.json` o pegar su contenido
4. Seleccionar Prometheus como datasource

> Nota: es necesario tener Prometheus configurado como datasource en Grafana.