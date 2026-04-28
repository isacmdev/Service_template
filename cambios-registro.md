# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 28/04/2026, 17:08:53 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 3498a21 - feat (ci) modifique el archivo ci.yml para generar SBOM en el pipeline (3 hours ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 3
- **📝 Nuevos:** 0
- **✏️ Modificados:** 3
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +50
- **Líneas eliminadas:** -14
- **Balance neto:** +36 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `.github/workflows/ci.yml` | +49 | -11 | +38 |
| ✏️ | `compose.observability.yml` | +1 | -1 | 0 |
| ✏️ | `compose.yml` | +0 | -2 | -2 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (3)</summary>

**✏️ Modificados:**
```
.github/workflows/ci.yml
compose.observability.yml
compose.yml
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `.github/workflows/ci.yml`

**Estado:** modificado
**Cambios:** +49 / -11

```diff
-      - name: Trivy scan
+      - name: Set up Docker Buildx
+        uses: docker/setup-buildx-action@v3
+
+      - name: Build Docker image (no push)
+        uses: docker/build-push-action@v6
+        with:
+          context: .
+          load: true
+          tags: myapp:ci-${{ github.sha }}
+          cache-from: type=gha
+          cache-to: type=gha,mode=max
+
+      - name: Trivy image scan
+        id: trivy-image
+        run: |
+          mkdir -p scan-results
+          docker run --rm \
+            -v /var/run/docker.sock:/var/run/docker.sock \
+            -v ${{ github.workspace }}/scan-results:/results \
+            aquasec/trivy:latest image \
+            --severity HIGH,CRITICAL \
+            --ignore-unfixed \
+            --format sarif \
+            --output /results/trivy-image-results.sarif \
+            myapp:ci-${{ github.sha }} \
+          && echo "TRIVY_EXIT_CODE=0" >> $GITHUB_ENV \
+          || echo "TRIVY_EXIT_CODE=1" >> $GITHUB_ENV
+
+      - name: Upload Trivy image SARIF to Security tab
+        uses: github/codeql-action/upload-sarif@v3
+        with:
+          sarif_file: scan-results/trivy-image-results.sarif
+
+      - name: Fail job if HIGH/CRITICAL vulnerabilities found
+        if: env.TRIVY_EXIT_CODE == '1'
+        run: exit 1
+
+      - name: Trivy filesystem scan
+        if: always()
-            aquasec/trivy:latest fs /repo \
+            aquasec/trivy:0.58.1 fs /repo \
-            -o /repo/trivy-report.json || echo "Trivy failed but continuing"
+            -o /repo/trivy-fs-report.json || echo "Trivy FS failed but continuing"
+
+      - name: Upload Trivy report
+        if: always()
+        uses: actions/upload-artifact@v4
+        with:
+          name: trivy-dependency-report
+          path: trivy-report.json
-          path: ${{ env.SBOM_FILE }}
-
-      - name: Upload Trivy report
-        if: always()
-        uses: actions/upload-artifact@v4
-        with:
-          name: trivy-dependency-report
-          path: trivy-report.json
+          path: ${{ env.SBOM_FILE }}
```

---

### 2. ✏️ `compose.observability.yml`

**Estado:** modificado
**Cambios:** +1 / -1

```diff
-      - "3000:3000"
+      - "3001:3001"
```

---

### 3. ✏️ `compose.yml`

**Estado:** modificado
**Cambios:** +0 / -2

```diff
-version: "3.9"
-
```

---

*Última actualización: 28/04/2026, 17:08:53*
