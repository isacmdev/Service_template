# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 21/04/2026, 15:35:07 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** dd6883f - feat: add Prometheus metrics support and expand Actuator endpoints exposure (23 hours ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 4
- **📝 Nuevos:** 0
- **✏️ Modificados:** 4
- **🗑️ Eliminados:** 0
- **✅ En staging:** 4 (listos para commit)
- **Líneas añadidas:** +388
- **Líneas eliminadas:** -9
- **Balance neto:** +379 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✅ ✏️ | `observability/grafana/dashboards/red-dashboard-template.json` | +358 | -0 | +358 |
| ✅ ✏️ | `README.md` | +29 | -6 | +23 |
| ✅ ✏️ | `build.gradle` | +0 | -2 | -2 |
| ✅ ✏️ | `src/main/resources/application.properties` | +1 | -1 | 0 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (4)</summary>

**✅ Modificados (staged):**
```
observability/grafana/dashboards/red-dashboard-template.json
README.md
build.gradle
src/main/resources/application.properties
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `observability/grafana/dashboards/red-dashboard-template.json` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +358 / -0

```diff
+{
+  "annotations": {
+    "list": [
+      {
+        "builtIn": 1,
+        "datasource": {
+          "type": "grafana",
+          "uid": "-- Grafana --"
+        },
+        "enable": true,
+        "hide": true,
+        "iconColor": "rgba(0, 211, 255, 1)",
+        "name": "Annotations & Alerts",
+        "type": "dashboard"
+      }
+    ]
+  },
+  "editable": true,
+  "fiscalYearStartMonth": 0,
+  "graphTooltip": 0,
+  "id": 0,
+  "links": [],
+  "panels": [
+    {
+      "datasource": {
+        "type": "prometheus",
+        "uid": "afjs6k2hwqubkf"
+      },
+      "fieldConfig": {
+        "defaults": {
+          "color": {
+            "mode": "palette-classic"
+          },
+          "custom": {
+            "axisBorderShow": false,
+            "axisCenteredZero": false,
+            "axisColorMode": "text",
+            "axisLabel": "",
+            "axisPlacement": "auto",
+            "barAlignment": 0,
+            "barWidthFactor": 0.6,
+            "drawStyle": "line",
+            "fillOpacity": 0,
+            "gradientMode": "none",
+            "hideFrom": {
+              "legend": false,
+              "tooltip": false,
+              "viz": false
+            },
+            "insertNulls": false,
+            "lineInterpolation": "linear",
+            "lineWidth": 1,
+            "pointSize": 5,
+            "scaleDistribution": {
+              "type": "linear"
+            },
+            "showPoints": "auto",
+            "showValues": false,
+            "spanNulls": false,
+            "stacking": {
+              "group": "A",
+              "mode": "none"
+            },
+            "thresholdsStyle": {
+              "mode": "off"
+            }
+          },
+          "mappings": [],
+          "thresholds": {
+            "mode": "absolute",
+            "steps": [
+              {
+                "color": "green",
+                "value": 0
+              }
+            ]
+          },
+          "unit": "req/s"
+        },
+        "overrides": []
+      },
+      "gridPos": {
+        "h": 8,
+        "w": 8,
+        "x": 0,
+        "y": 0
+      },
+      "id": 1,
+      "options": {
+        "legend": {
+          "calcs": [],
+          "displayMode": "list",
+          "placement": "bottom",
+          "showLegend": true
+        },
+        "tooltip": {
+          "hideZeros": false,
+          "mode": "single",
+          "sort": "none"
+        }
+      },
+      "pluginVersion": "12.3.1+security-01",
+      "targets": [
+        {
+          "datasource": {
+            "type": "prometheus",
+            "uid": "afjs6k2hwqubkf"
+          },
+          "editorMode": "code",
+          "expr": "sum(rate(http_server_requests_seconds_count{uri!~\"/actuator.*\"}[5m])) by (uri)",
+          "hide": false,
+          "instant": false,
+          "interval": "",
+          "legendFormat": "__auto",
+          "range": true,
+          "refId": "A"
+        },
+        {
+          "datasource": {
+            "type": "prometheus",
+            "uid": "afjs6k2hwqubkf"
+          },
+          "editorMode": "code",
+          "expr": "sum(rate(http_server_requests_seconds_count{uri!~\"/actuator.*\"}[5m])) by (method)",
+          "hide": false,
+          "instant": false,
+          "legendFormat": "__auto",
+          "range": true,
+          "refId": "B"
+        }
+      ],
+      "title": "Rate",
+      "type": "timeseries"
+    },
+    {
+      "datasource": {
+        "type": "prometheus",
+        "uid": "afjs6k2hwqubkf"
+      },
+      "fieldConfig": {
+        "defaults": {
+          "color": {
+            "mode": "palette-classic"
+          },
+          "custom": {
+            "axisBorderShow": false,
+            "axisCenteredZero": false,
+            "axisColorMode": "text",
+            "axisLabel": "",
+            "axisPlacement": "auto",
+            "barAlignment": 0,
+            "barWidthFactor": 0.6,
+            "drawStyle": "line",
+            "fillOpacity": 0,
+            "gradientMode": "none",
+            "hideFrom": {
+              "legend": false,
+              "tooltip": false,
+              "viz": false
+            },
+            "insertNulls": false,
+            "lineInterpolation": "linear",
+            "lineWidth": 1,
+            "pointSize": 5,
+            "scaleDistribution": {
+              "type": "linear"
+            },
+            "showPoints": "auto",
+            "showValues": false,
+            "spanNulls": false,
+            "stacking": {
+              "group": "A",
+              "mode": "none"
+            },
+            "thresholdsStyle": {
+              "mode": "off"
+            }
+          },
+          "mappings": [],
+          "thresholds": {
+            "mode": "absolute",
+            "steps": [
+              {
+                "color": "green",
+                "value": 0
+              }
+            ]
+          },
+          "unit": "percentunit"
+        },
+        "overrides": []
+      },
+      "gridPos": {
+        "h": 8,
+        "w": 8,
+        "x": 8,
+        "y": 0
+      },
+      "id": 3,
+      "options": {
+        "legend": {
+          "calcs": [],
+          "displayMode": "list",
+          "placement": "bottom",
+          "showLegend": true
+        },
+        "tooltip": {
+          "hideZeros": false,
+          "mode": "single",
+          "sort": "none"
+        }
+      },
+      "pluginVersion": "12.3.1+security-01",
+      "targets": [
+        {
+          "editorMode": "code",
+          "expr": "sum(rate(http_server_requests_seconds_count{status=~\"5..\", uri!~\"/actuator.*\"}[5m]))\r\n/\r\nsum(rate(http_server_requests_seconds_count{uri!~\"/actuator.*\"}[5m]))",
+          "legendFormat": "__auto",
+          "range": true,
+          "refId": "A"
+        }
+      ],
+      "title": "Errors",
+      "type": "timeseries"
+    },
+    {
+      "datasource": {
+        "type": "prometheus",
+        "uid": "afjs6k2hwqubkf"
+      },
+      "fieldConfig": {
+        "defaults": {
+          "color": {
+            "mode": "palette-classic"
+          },
+          "custom": {
+            "axisBorderShow": false,
+            "axisCenteredZero": false,
+            "axisColorMode": "text",
+            "axisLabel": "",
+            "axisPlacement": "auto",
+            "barAlignment": 0,
+            "barWidthFactor": 0.6,
+            "drawStyle": "line",
+            "fillOpacity": 0,
+            "gradientMode": "none",
+            "hideFrom": {
+              "legend": false,
+              "tooltip": false,
+              "viz": false
+            },
+            "insertNulls": false,
+            "lineInterpolation": "linear",
+            "lineWidth": 1,
+            "pointSize": 5,
+            "scaleDistribution": {
+              "type": "linear"
+            },
+            "showPoints": "auto",
+            "showValues": false,
+            "spanNulls": false,
+            "stacking": {
+              "group": "A",
+              "mode": "none"
+            },
+            "thresholdsStyle": {
+              "mode": "off"
+            }
+          },
+          "mappings": [],
+          "thresholds": {
+            "mode": "absolute",
+            "steps": [
+              {
+                "color": "green",
+                "value": 0
+              }
+            ]
+          },
+          "unit": "s"
+        },
+        "overrides": [
+          {
+            "__systemRef": "hideSeriesFrom",
+            "matcher": {
+              "id": "byNames",
+              "options": {
+                "mode": "exclude",
+                "names": [
+                  "histogram_quantile(0.95, sum(rate(http_server_requests_seconds_bucket{uri!~\"/actuator.*\"}[5m])) by (le))"
+                ],
+                "prefix": "All except:",
+                "readOnly": true
+              }
+            },
+            "properties": [
+              {
+                "id": "custom.hideFrom",
+                "value": {
+                  "legend": false,
+                  "tooltip": true,
+                  "viz": true
+                }
+              }
+            ]
+          }
+        ]
+      },
+      "gridPos": {
+        "h": 8,
+        "w": 8,
+        "x": 16,
+        "y": 0
+      },
+      "id": 2,
+      "options": {
+        "legend": {
+          "calcs": [],
+          "displayMode": "list",
+          "placement": "bottom",
+          "showLegend": true
+        },
+        "tooltip": {
+          "hideZeros": false,
+          "mode": "single",
+          "sort": "none"
+        }
+      },
+      "pluginVersion": "12.3.1+security-01",
+      "targets": [
+        {
+          "editorMode": "code",
+          "expr": "histogram_quantile(0.95, sum(rate(http_server_requests_seconds_bucket{uri!~\"/actuator.*\"}[5m])) by (le))",
+          "legendFormat": "__auto",
+          "range": true,
+          "refId": "A"
+        }
+      ],
+      "title": "Duration",
+      "type": "timeseries"
+    }
+  ],
+  "preload": false,
+  "schemaVersion": 42,
+  "tags": [],
+  "templating": {
+    "list": []
+  },
+  "time": {
+    "from": "2026-04-21T19:52:44.949Z",
+    "to": "2026-04-21T19:54:38.440Z"
+  },
+  "timepicker": {},
+  "timezone": "browser",
+  "title": "RED template",
+  "uid": "adpgp5r",
+  "version": 18
+}
```

---

### 2. ✏️ `README.md` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +29 / -6

```diff
-http://localhost:8080/actuator/prometheus
+[http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)
+[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
+
-- Número de requests HTTP
-- Latencia
-- Status codes
+- Request Rate
+- Error Rate (por código HTTP)
+- Latencia de requests
-http://localhost:16686/search
+[http://localhost:16686/search](http://localhost:16686/search)
-- Spans por request
+- Spans por request
+
+### Dashboards (Grafana)
+
+Se incluye un dashboard RED exportado en:
+
+observability/grafana/dashboards/red-dashboard-template.json
+
+Este dashboard permite visualizar:
+
+- Request Rate
+- Error Rate (5xx)
+- Latencia (p95)
+
+Para usarlo:
+
+1. Abrir Grafana
+2. Ir a **(+) → Import dashboard**
+3. Subir el archivo `red-dashboard-template.json` o pegar su contenido
+4. Seleccionar Prometheus como datasource
+
+> Nota: es necesario tener Prometheus configurado como datasource en Grafana.
```

---

### 3. ✏️ `build.gradle` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +0 / -2

```diff
-	implementation 'org.springframework.boot:spring-boot-starter-actuator'
-	implementation 'io.micrometer:micrometer-tracing-bridge-otel'
```

---

### 4. ✏️ `src/main/resources/application.properties` ✅ (staged)

**Estado:** modificado (staged)
**Cambios:** +1 / -1

```diff
-//=Actuator configuration
+management.metrics.tags.application=template
```

---

*Última actualización: 21/04/2026, 15:35:07*
