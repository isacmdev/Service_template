# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 28/04/2026, 17:18:19 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 5c96141 - feat(ci) modifique el archivo ci.yml para agregar scan de imagen de contenedor (7 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 1
- **📝 Nuevos:** 0
- **✏️ Modificados:** 1
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +44
- **Líneas eliminadas:** -23
- **Balance neto:** +21 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `.github/workflows/ci.yml` | +44 | -23 | +21 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (1)</summary>

**✏️ Modificados:**
```
.github/workflows/ci.yml
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `.github/workflows/ci.yml`

**Estado:** modificado
**Cambios:** +44 / -23

```diff
+# =============================================================================
+# CI/CD Pipeline para build, análisis, SBOM, Docker y escaneo de seguridad
+# =============================================================================
-  contents: read
-  security-events: write
+  contents: read          # Permite leer el código
+  security-events: write  # Permite subir resultados de escaneo a la pestaña Security
-      - uses: actions/checkout@v4
+      # 1. Checkout del código fuente
+      - name: Checkout repository
+        uses: actions/checkout@v4
-      - uses: actions/setup-java@v4
+      # 2. Configurar JDK 17 con caché de Gradle
+      - name: Set up JDK 17
+        uses: actions/setup-java@v4
-      - run: chmod +x ./gradlew
+      # 3. Dar permisos de ejecución a Gradle wrapper
+      - name: Make gradlew executable
+        run: chmod +x ./gradlew
+      # 4. Compilar, ejecutar tests y generar artefactos
+      # 5. Generar SBOM (Software Bill of Materials) en formato CycloneDX
-      - name: Verify SBOM file
-        run: test -f build/reports/bom.json && echo "SBOM file exists" || (echo "SBOM file is missing" && exit 1)
+      # (Opcional) Verificar que el SBOM se haya creado - debug
+      # - name: Verify SBOM file
+      #   run: test -f build/reports/bom.json
+      # 6. Análisis estático con SonarQube
+      # 7. Quality Gate (solo en push a main)
+      # 8. Construir imagen Docker (sin push al registry)
-      - name: Build Docker image (no push)
+      - name: Build Docker image
-          load: true
+          load: true                               # Carga la imagen en el runner
+      # 9. Escanear imagen Docker con Trivy (vulnerabilidades HIGH/CRITICAL)
+        continue-on-error: true                    # No falla aún, lo evaluamos después
-            aquasec/trivy:latest image \
+            aquasec/trivy:0.58.1 image \
-            myapp:ci-${{ github.sha }} \
-          && echo "TRIVY_EXIT_CODE=0" >> $GITHUB_ENV \
-          || echo "TRIVY_EXIT_CODE=1" >> $GITHUB_ENV
+            myapp:ci-${{ github.sha }}
-      - name: Fail job if HIGH/CRITICAL vulnerabilities found
-        if: env.TRIVY_EXIT_CODE == '1'
-        run: exit 1
+      - name: Fail if HIGH/CRITICAL vulnerabilities found in image
+        if: steps.trivy-image.outcome == 'failure'
+        run: |
+          echo "❌ Se encontraron vulnerabilidades HIGH/CRITICAL en la imagen Docker"
+          exit 1
+      # 10. Escanear sistema de archivos (dependencias, código, config)
+      #   - Se ejecuta siempre, incluso si falló el escaneo anterior.
-            -o /repo/trivy-fs-report.json || echo "Trivy FS failed but continuing"
+            -o /repo/trivy-fs-report.json \
+          || echo "⚠️ Trivy FS encontró problemas, pero el pipeline continúa (solo reporte)"
-      - name: Upload Trivy report
+      - name: Upload Trivy filesystem report
-          name: trivy-dependency-report
-          path: trivy-report.json
+          name: trivy-fs-report
+          path: trivy-fs-report.json
-      - name: Rename SBOM with version
+      # 11. Renombrar SBOM con el short SHA del commit y subirlo como artifact
+      - name: Rename SBOM with git version
-          cp build/reports/bom.json "sbom-$VERSION.json"
-          echo "SBOM_FILE=sbom-$VERSION.json" >> $GITHUB_ENV
+          cp build/reports/bom.json "sbom-${VERSION}.json"
+          echo "SBOM_FILE=sbom-${VERSION}.json" >> $GITHUB_ENV
```

---

*Última actualización: 28/04/2026, 17:18:19*
