# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 27/04/2026, 10:48:13 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 04fb098 - test sonar (23 minutes ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 3
- **📝 Nuevos:** 0
- **✏️ Modificados:** 3
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +24
- **Líneas eliminadas:** -11
- **Balance neto:** +13 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| ✏️ | `.github/workflows/ci.yml` | +18 | -11 | +7 |
| ✏️ | `build.gradle` | +3 | -0 | +3 |
| ✏️ | `src/main/java/proyect/template/application/WidgetServiceUseCase.java` | +3 | -0 | +3 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (3)</summary>

**✏️ Modificados:**
```
.github/workflows/ci.yml
build.gradle
src/main/java/proyect/template/application/WidgetServiceUseCase.java
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. ✏️ `.github/workflows/ci.yml`

**Estado:** modificado
**Cambios:** +18 / -11

```diff
+permissions:
+  contents: read
+
-      - name: Clean Gradle cache
-        run: rm -rf ~/.gradle/caches/
-
-      - name: Set up JDK 21 for Sonar
+      - name: Set up JDK 17
-          java-version: '21'
+          java-version: '17'
-      - name: Build, test and verify coverage
+      - name: Build and run tests
-      - name: Debug Gradle version
-        run: ./gradlew --version
-
-      - name: Sonar analysis
+      - name: SonarQube analysis
-        run: ./gradlew sonar --no-daemon
+        run: ./gradlew sonar --no-daemon
+
+      - name: SonarQube Quality Gate check
+        id: quality-gate
+        uses: SonarSource/sonarqube-quality-gate-action@v1.2.0
+        with:
+          scanMetadataReportFile: build/sonar/report-task.txt
+          pollingTimeoutSec: 300
+        env:
+          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
+          SONAR_HOST_URL: ${{ vars.SONAR_HOST_URL }}
```

---

### 2. ✏️ `build.gradle`

**Estado:** modificado
**Cambios:** +3 / -0

```diff
+    dependsOn test
+
+        property "sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/test/jacocoTestReport.xml"
```

---

### 3. ✏️ `src/main/java/proyect/template/application/WidgetServiceUseCase.java`

**Estado:** modificado
**Cambios:** +3 / -0

```diff
+        String x = null;
+        x.length();
+        if (true == true) {}
```

---

*Última actualización: 27/04/2026, 10:48:13*
