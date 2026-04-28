# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 28/04/2026, 13:39:01 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 9ee9f0a - feat (ci) modifique el archivo ci.yml para generar SBOM en el pipeline (7 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 1
- **📝 Nuevos:** 0
- **✏️ Modificados:** 1
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +4
- **Líneas eliminadas:** -1
- **Balance neto:** +3 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `.github/workflows/ci.yml` | +4 | -1 | +3 |

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
**Cambios:** +4 / -1

```diff
+      - name: Clean previous SBOM (if any)
+        run: rm -f build/reports/bom.json
+
-          VERSION=${GITHUB_REF_NAME:-$(git rev-parse --short HEAD)}
+          VERSION=$(git rev-parse --short HEAD)
```

---

*Última actualización: 28/04/2026, 13:39:01*
