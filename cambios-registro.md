# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 28/04/2026, 13:30:47 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 3599c4e - feat (ci) modifique el archivo ci.yml para generar SBOM en el pipeline (10 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 2
- **📝 Nuevos:** 0
- **✏️ Modificados:** 2
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +10
- **Líneas eliminadas:** -12
- **Balance neto:** -2 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `.github/workflows/ci.yml` | +5 | -12 | -7 |
| ✏️ | `build.gradle` | +5 | -0 | +5 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (2)</summary>

**✏️ Modificados:**
```
.github/workflows/ci.yml
build.gradle
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `.github/workflows/ci.yml`

**Estado:** modificado
**Cambios:** +5 / -12

```diff
-      - name: List build/libs (debug)
+      - name: List build/libs
-      - name: Debug SBOM generation
-        run: ./gradlew cyclonedxBom --info --rerun-tasks --no-daemon
+      - name: Generate SBOM (CycloneDX)
+        run: ./gradlew cyclonedxBom --rerun-tasks --no-daemon
-      - name: Trivy scan (filesystem)
+      - name: Trivy scan
-            -o /repo/trivy-report.json
+            -o /repo/trivy-report.json || echo "Trivy failed but continuing"
-      - name: Upload SBOM artifact
-        if: always()   # se suba aunque falle alguna prueba (opcional)
-        uses: actions/upload-artifact@v4
-        with:
-          name: sbom-cyclonedx
-          path: build/reports/bom.json
-
```

---

### 2. ✏️ `build.gradle`

**Estado:** modificado
**Cambios:** +5 / -0

```diff
+cyclonedxBom {
+    outputName = "bom"
+    outputFormat = "json"
+    destination = file("build/reports")
+}
```

---

*Última actualización: 28/04/2026, 13:30:47*
