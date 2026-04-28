# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 28/04/2026, 12:55:51 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** e512a7f - feat(sotest) arregle test que estaban fallando (18 hours ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 2
- **📝 Nuevos:** 0
- **✏️ Modificados:** 2
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +26
- **Líneas eliminadas:** -0
- **Balance neto:** +26 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `.github/workflows/ci.yml` | +25 | -0 | +25 |
| ✏️ | `build.gradle` | +1 | -0 | +1 |

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
**Cambios:** +25 / -0

```diff
+      - name: Generate SBOM (CycloneDX)
+        run: ./gradlew cyclonedxBom --no-daemon
+
+      - name: Verify SBOM file
+        run: test -f build/reports/bom.json && echo "SBOM generated" || exit 1
+
+      - name: Rename SBOM with version
+        run: |
+          VERSION=${GITHUB_REF_NAME:-$(git rev-parse --short HEAD)}
+          cp build/reports/bom.json "sbom-$VERSION.json"
+          echo "SBOM_FILE=sbom-$VERSION.json" >> $GITHUB_ENV
+
+      - name: Upload versioned SBOM
+        uses: actions/upload-artifact@v4
+        with:
+          name: sbom-cyclonedx
+          path: ${{ env.SBOM_FILE }}
+
+      - name: Upload SBOM artifact
+        if: always()   # se suba aunque falle alguna prueba (opcional)
+        uses: actions/upload-artifact@v4
+        with:
+          name: sbom-cyclonedx
+          path: build/reports/bom.json
+
```

---

### 2. ✏️ `build.gradle`

**Estado:** modificado
**Cambios:** +1 / -0

```diff
+    id 'org.cyclonedx.bom' version '1.8.2'
```

---

*Última actualización: 28/04/2026, 12:55:51*
