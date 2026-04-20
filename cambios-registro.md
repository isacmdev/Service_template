# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** 20/04/2026, 15:41:08 
**Proyecto:** C:\Users\Usuario\Desktop\Proyectos\Java\template 
**Último commit:** 4d16a2b - feat: add OpenTelemetry tracing with local export (4 days ago) 

## 📊 RESUMEN DE CAMBIOS PENDIENTES

- **Total archivos:** 2
- **📝 Nuevos:** 2
- **✏️ Modificados:** 0
- **🗑️ Eliminados:** 0
- **Líneas añadidas:** +0
- **Líneas eliminadas:** -0
- **Balance neto:** 0 líneas

### 📝 DETALLE POR ARCHIVO

| Estado | Archivo | Añadidas | Eliminadas | Neto |
|--------|---------|----------|------------|------|
| 🆕 | `README.md` | nuevo | -0 | 0 |
| 🆕 | `monitor.js` | nuevo | -0 | 0 |

### 📁 LISTA COMPLETA

<details>
<summary>Ver todos los archivos (2)</summary>

**🆕 Nuevos:**
```
README.md
monitor.js
```

</details>

---

## 📋 CAMBIOS DETALLADOS POR ARCHIVO

### 1. 🆕 `README.md`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```md
[No se pudo leer el archivo]
```

---

### 2. 🆕 `monitor.js`

**Estado:** nuevo
**Tipo:** Archivo nuevo

```js
const { exec } = require('child_process');
const fs = require('fs');
const path = require('path');

const OUTPUT_FILE = 'cambios-registro.md';
const PROJECT_PATH = process.cwd();
let lastChanges = '';
let lastCommitHash = '';
let debounceTimer = null;
let commitCheckTimer = null;
let checkInProgress = false;
let pendingCheck = false;

console.log('🔍 Monitoreando proyecto (incluye archivos nuevos)...');
console.log(`📝 Registro: ${OUTPUT_FILE}`);
console.log('🛑 Ctrl+C para detener\n');
console.log('📌 El registro se actualizará con el estado actual (sin historial)\n');

// Función para obtener el último commit hash
function getLastCommitHash(callback) {
    exec('git rev-parse HEAD', (err, stdout) => {
        if (err) {
            callback('');
            return;
        }
        callback(stdout.trim());
    });
}

// Función para verificar si hubo un nuevo commit
function checkForNewCommit() {
    getLastCommitHash((currentHash) => {
        if (currentHash && currentHash !== lastCommitHash) {
            if (lastCommitHash !== '') {
                console.log('\n📦 Nuevo commit detectado! Actualizando archivo de registro...\n');

                exec('git log -1 --pretty=format:"%h - %s (%cr)"', (err, commitInfo) => {
                    const commitMessage = err ? 'Commit realizado' : commitInfo;

                    lastChanges = '';

                    // Después de un commit, actualizar para mostrar que no hay cambios
                    updateOutputFile([], commitMessage);

                    console.log(`✅ Archivo actualizado con información del commit: ${commitMessage}`);
                    console.log('📝 Registro muestra estado limpio después del commit\n');
                });
            }
            lastCommitHash = currentHash;
        }
    });
}

// Función para obtener archivos nuevos (untracked)
function getUntrackedFiles(callback) {
    exec('git ls-files --others --exclude-standard', (err, stdout) => {
        if (err) {
            callback([]);
            return;
        }

        const files = stdout.split('\n')
            .filter(f => f.trim() && !f.includes('cambios-registro.md'))
            .filter((f, i, self) => self.indexOf(f) === i);

        callback(files);
    });
}

// Función para obtener estadísticas detalladas de cambios
function getGitStats(callback) {
    exec('git diff --numstat && git diff --staged --numstat',
        { maxBuffer: 1024 * 1024 },
        (err, stdout) => {
            if (err) {
                callback([]);
                return;
            }

            const files = [];
            const lines = stdout.split('\n').filter(line => line.trim());

            lines.forEach(line => {
                const parts = line.split('\t');
                if (parts.length >= 3) {
                    const added = parts[0] === '-' ? 0 : parseInt(parts[0]) || 0;
                    const deleted = parts[1] === '-' ? 0 : parseInt(parts[1]) || 0;
                    const filename = parts[2];

                    if (filename && !filename.includes('cambios-registro.md')) {
                        files.push({
                            nombre: filename,
                            añadidas: added,
                            eliminadas: deleted,
                            estado: 'modificado',
                            extension: path.extname(filename)
                        });
                    }
                }
            });

            callback(files);
        });
}

// Función para obtener archivos eliminados
function getDeletedFiles(callback) {
    exec('git ls-files --deleted', (err, stdout) => {
        if (err) {
            callback([]);
            return;
        }

        const files = stdout.split('\n')
            .filter(f => f.trim() && !f.includes('cambios-registro.md'));

        callback(files);
    });
}

// Función para obtener archivos en staging
function getStagedFiles(callback) {
    exec('git diff --staged --name-only', (err, stdout) => {
        if (err) {
            callback([]);
            return;
        }

        const files = stdout.split('\n')
            .filter(f => f.trim() && !f.includes('cambios-registro.md'));

        callback(files);
    });
}

// Función para obtener el diff de un archivo (solo cambios reales)
function getFileDiff(filename, callback) {
    // Primero verificar si es staged o no
    exec(`git diff --staged --name-only`, (err, stagedOutput) => {
        const isStaged = stagedOutput && stagedOutput.includes(filename);
        const diffCommand = isStaged
            ? `git diff --staged "${filename}"`
            : `git diff "${filename}"`;

        exec(diffCommand, { maxBuffer: 5 * 1024 * 1024 }, (err, stdout) => {
            if (err || !stdout) {
                callback('');
                return;
            }

            // Filtrar solo las líneas que son cambios reales
            const lines = stdout.split('\n');
            const cleanDiff = lines
                .filter(line => {
                    // Excluir headers de git
                    if (line.startsWith('diff --git')) return false;
                    if (line.startsWith('index ')) return false;
                    if (line.startsWith('--- ')) return false;
                    if (line.startsWith('+++ ')) return false;
                    if (line.startsWith('@@')) return false;

                    // Solo incluir líneas que empiecen con + o -
                    return line.startsWith('+') || line.startsWith('-');
                })
                .join('\n');

            callback(cleanDiff || '[Sin cambios visibles]');
        });
    });
}

// Función para leer el contenido de un archivo nuevo
function readNewFile(filename, callback) {
    const fullPath = path.join(PROJECT_PATH, filename);
    fs.readFile(fullPath, 'utf8', (err, content) => {
        if (err) {
            callback('');
            return;
        }
        // Limitar a primeras 500 líneas para archivos muy grandes
        const lines = content.split('\n');
        const limitedContent = lines.slice(0, 500).join('\n');
        const truncated = lines.length > 500 ? '\n\n... (archivo truncado, mostrando primeras 500 líneas) ...' : '';
        callback(limitedContent + truncated);
    });
}

// Función para actualizar el archivo de salida (sobrescribe completamente)
function updateOutputFile(allFiles, commitMessage = null) {
    const timestamp = new Date().toLocaleString('es-CO', {
        hour12: false,
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });

    // Obtener mensaje del último commit si no se proporcionó
    if (!commitMessage) {
        exec('git log -1 --pretty=format:"%h - %s (%cr)"', (err, msg) => {
            const finalCommitMsg = err ? 'No hay commits' : msg;
            writeFile(allFiles, timestamp, finalCommitMsg);
        });
    } else {
        writeFile(allFiles, timestamp, commitMessage);
    }
}

function writeFile(allFiles, timestamp, commitMessage) {
    let content = `# 📊 ESTADO ACTUAL DE CAMBIOS 
**Actualizado:** ${timestamp} 
**Proyecto:** ${PROJECT_PATH} 
**Último commit:** ${commitMessage} 

`;

    if (allFiles.length === 0) {
        content += `## ✅ Repositorio limpio\n\n`;
        content += `No hay cambios sin commitear. Todos los archivos están sincronizados con git.\n\n`;
        content += `---\n`;
        content += `*Última actualización: ${timestamp}*`;

        // Escribir el archivo completo (sobrescribir)
        fs.writeFileSync(OUTPUT_FILE, content);
        fs.fsyncSync(fs.openSync(OUTPUT_FILE, 'r+'));
    } else {
        // Calcular estadísticas
        const totalAñadidas = allFiles.reduce((sum, f) => sum + f.añadidas, 0);
        const totalEliminadas = allFiles.reduce((sum, f) => sum + f.eliminadas, 0);
        const totalNeto = totalAñadidas - totalEliminadas;

        const nuevos = allFiles.filter(f => f.estado.includes('nuevo')).length;
        const modificados = allFiles.filter(f => f.estado.includes('modificado')).length;
        const eliminados = allFiles.filter(f => f.estado === 'eliminado').length;
        const staged = allFiles.filter(f => f.estado.includes('staged')).length;

        content += `## 📊 RESUMEN DE CAMBIOS PENDIENTES\n\n`;
        content += `- **Total archivos:** ${allFiles.length}\n`;
        content += `- **📝 Nuevos:** ${nuevos}\n`;
        content += `- **✏️ Modificados:** ${modificados}\n`;
        content += `- **🗑️ Eliminados:** ${eliminados}\n`;
        if (staged > 0) {
            content += `- **✅ En staging:** ${staged} (listos para commit)\n`;
        }
        content += `- **Líneas añadidas:** +${totalAñadidas}\n`;
        content += `- **Líneas eliminadas:** -${totalEliminadas}\n`;
        content += `- **Balance neto:** ${totalNeto > 0 ? '+' : ''}${totalNeto} líneas\n\n`;

        content += `### 📝 DETALLE POR ARCHIVO\n\n`;
        content += `| Estado | Archivo | Añadidas | Eliminadas | Neto |\n`;
        content += `|--------|---------|----------|------------|------|\n`;

        allFiles.sort((a, b) => {
            const estadoOrder = {
                'nuevo (staged)': 0,
                'nuevo': 1,
                'modificado (staged)': 2,
                'modificado': 3,
                'eliminado': 4
            };
            return (estadoOrder[a.estado] || 99) - (estadoOrder[b.estado] || 99) ||
                (b.añadidas + b.eliminadas) - (a.añadidas + a.eliminadas);
        });

        allFiles.forEach(f => {
            const neto = f.añadidas - f.eliminadas;
            const netoStr = neto > 0 ? `+${neto}` : neto.toString();

            // Emoji según estado
            let estadoEmoji = '✏️';
            if (f.estado.includes('nuevo')) estadoEmoji = '🆕';
            if (f.estado.includes('eliminado')) estadoEmoji = '🗑️';
            if (f.estado.includes('staged')) estadoEmoji = '✅ ' + estadoEmoji;

            // Truncar nombre
            let nombreDisplay = f.nombre;
            if (nombreDisplay.length > 50) {
                const parts = nombreDisplay.split(path.sep);
                if (parts.length > 3) {
                    nombreDisplay = `.../${parts.slice(-3).join('/')}`;
                }
            }

            const añadidasDisplay = f.estado.includes('nuevo') ? 'nuevo' : `+${f.añadidas}`;
            const eliminadasDisplay = f.estado === 'eliminado' ? 'eliminado' : `-${f.eliminadas}`;

            content += `| ${estadoEmoji} | \`${nombreDisplay}\` | ${añadidasDisplay} | ${eliminadasDisplay} | ${netoStr} |\n`;
        });

        content += `\n### 📁 LISTA COMPLETA\n\n`;
        content += `<details>\n`;
        content += `<summary>Ver todos los archivos (${allFiles.length})</summary>\n\n`;

        // Separar por tipo
        const tipos = {
            '🆕 Nuevos (staged)': allFiles.filter(f => f.estado === 'nuevo (staged)'),
            '🆕 Nuevos': allFiles.filter(f => f.estado === 'nuevo'),
            '✅ Modificados (staged)': allFiles.filter(f => f.estado === 'modificado (staged)'),
            '✏️ Modificados': allFiles.filter(f => f.estado === 'modificado'),
            '🗑️ Eliminados': allFiles.filter(f => f.estado === 'eliminado')
        };

        for (const [titulo, archivos] of Object.entries(tipos)) {
            if (archivos.length > 0) {
                content += `**${titulo}:**\n\`\`\`\n`;
                archivos.forEach(f => {
                    content += `${f.nombre}\n`;
                });
                content += '```\n\n';
            }
        }

        content += `</details>\n\n`;
        content += `---\n`;

        // Ahora obtener el diff de cada archivo de forma asíncrona
        let filesProcessed = 0;
        const totalFiles = allFiles.length;

        // Función para procesar un archivo y obtener su diff
        const processFileDiff = (fileInfo, callback) => {
            if (fileInfo.estado === 'eliminado') {
                callback({
                    ...fileInfo,
                    diff: '[Archivo eliminado - no hay contenido para mostrar]'
                });
            } else if (fileInfo.estado.includes('nuevo')) {
                // Leer el contenido del archivo nuevo
                readNewFile(fileInfo.nombre, (content) => {
                    callback({
                        ...fileInfo,
                        diff: content || '[No se pudo leer el archivo]',
                        isNewFile: true
                    });
                });
            } else {
                // Obtener el diff del archivo modificado
                getFileDiff(fileInfo.nombre, (diff) => {
                    callback({
                        ...fileInfo,
                        diff: diff || '[No hay cambios para mostrar]'
                    });
                });
            }
        };

        // Procesar todos los archivos y agregar sus diffs
        const filesWithDiffs = [];
        allFiles.forEach((file, index) => {
            processFileDiff(file, (fileWithDiff) => {
                filesWithDiffs.push(fileWithDiff);
                filesProcessed++;

                // Cuando todos los archivos estén procesados, escribir el contenido final
                if (filesProcessed === totalFiles) {
                    content += `\n## 📋 CAMBIOS DETALLADOS POR ARCHIVO\n\n`;

                    // Ordenar igual que la tabla anterior
                    filesWithDiffs.sort((a, b) => {
                        const estadoOrder = {
                            'nuevo (staged)': 0,
                            'nuevo': 1,
                            'modificado (staged)': 2,
                            'modificado': 3,
                            'eliminado': 4
                        };
                        return (estadoOrder[a.estado] || 99) - (estadoOrder[b.estado] || 99) ||
                            (b.añadidas + b.eliminadas) - (a.añadidas + a.eliminadas);
                    });

                    filesWithDiffs.forEach((f, idx) => {
                        const emoji = f.estado.includes('nuevo') ? '🆕' :
                            f.estado === 'eliminado' ? '🗑️' : '✏️';
                        const stagedLabel = f.estado.includes('staged') ? ' ✅ (staged)' : '';

                        content += `### ${idx + 1}. ${emoji} \`${f.nombre}\`${stagedLabel}\n\n`;
                        content += `**Estado:** ${f.estado}\n`;

                        if (!f.estado.includes('eliminado')) {
                            if (f.isNewFile) {
                                content += `**Tipo:** Archivo nuevo\n\n`;
                                const extension = path.extname(f.nombre).substring(1) || 'text';
                                content += `\`\`\`${extension}\n${f.diff}\n\`\`\`\n\n`;
                            } else {
                                content += `**Cambios:** +${f.añadidas} / -${f.eliminadas}\n\n`;
                                content += `\`\`\`diff\n${f.diff}\n\`\`\`\n\n`;
                            }
                        } else {
                            content += `\n${f.diff}\n\n`;
                        }

                        content += `---\n\n`;
                    });

                    content += `*Última actualización: ${timestamp}*\n`;

                    // Escribir el archivo completo
                    fs.writeFileSync(OUTPUT_FILE, content);
                    fs.fsyncSync(fs.openSync(OUTPUT_FILE, 'r+'));
                }
            });
        });
    }
}

// Función optimizada para verificar cambios
function checkAllChanges() {
    if (checkInProgress) {
        pendingCheck = true;
        return;
    }

    checkInProgress = true;

    Promise.all([
        new Promise(resolve => getGitStats(resolve)),
        new Promise(resolve => getUntrackedFiles(resolve)),
        new Promise(resolve => getDeletedFiles(resolve)),
        new Promise(resolve => getStagedFiles(resolve))
    ]).then(([modifiedFiles, untrackedFiles, deletedFiles, stagedFiles]) => {

        // Convertir archivos
        const newFiles = untrackedFiles.map(f => ({
            nombre: f,
            añadidas: 0,
            eliminadas: 0,
            estado: 'nuevo',
            extension: path.extname(f)
        }));

        const deletedFilesFormatted = deletedFiles.map(f => ({
            nombre: f,
            añadidas: 0,
            eliminadas: 0,
            estado: 'eliminado',
            extension: path.extname(f)
        }));

        // Marcar archivos en staging
        const stagedSet = new Set(stagedFiles);
        const allFiles = [...modifiedFiles, ...newFiles, ...deletedFilesFormatted].map(f => {
            if (stagedSet.has(f.nombre)) {
                return { ...f, estado: f.estado === 'nuevo' ? 'nuevo (staged)' : 'modificado (staged)' };
            }
            return f;
        });

        // Crear identificador único basado en los archivos
        const changesId = allFiles.map(f =>
            `${f.nombre}:${f.estado}:${f.añadidas}:${f.eliminadas}`
        ).join('|');

        // Si hay cambios y son diferentes a los últimos registrados, o si no hay cambios pero antes había
        if (changesId !== lastChanges) {
            // Actualizar el archivo con el estado actual
            updateOutputFile(allFiles);

            // Mostrar en consola
            const timestamp = new Date().toLocaleString('es-CO', {
                hour12: false,
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
            });

            if (allFiles.length === 0) {
                console.log(`\n🕐 ${timestamp}`);
                console.log(`✅ Repositorio limpio - No hay cambios pendientes`);
            } else {
                const totalAñadidas = allFiles.reduce((sum, f) => sum + f.añadidas, 0);
                const totalEliminadas = allFiles.reduce((sum, f) => sum + f.eliminadas, 0);
                const totalNeto = totalAñadidas - totalEliminadas;
                const nuevos = allFiles.filter(f => f.estado.includes('nuevo')).length;
                const modificados = allFiles.filter(f => f.estado.includes('modificado')).length;
                const eliminados = allFiles.filter(f => f.estado === 'eliminado').length;
                const staged = allFiles.filter(f => f.estado.includes('staged')).length;

                console.log(`\n🕐 ${timestamp}`);
                console.log(`📊 Total: ${allFiles.length} archivos (🆕 ${nuevos}, ✏️ ${modificados}, 🗑️ ${eliminados})`);
                if (staged > 0) {
                    console.log(` ✅ ${staged} archivos en staging (listos para commit)`);
                }
                console.log(` Líneas: +${totalAñadidas} / -${totalEliminadas} (${totalNeto > 0 ? '+' : ''}${totalNeto})`);
            }

            lastChanges = changesId;
        }

        checkInProgress = false;
        if (pendingCheck) {
            pendingCheck = false;
            setTimeout(checkAllChanges, 50);
        }
    }).catch(() => {
        checkInProgress = false;

... (archivo truncado, mostrando primeras 500 líneas) ...
```

---

*Última actualización: 20/04/2026, 15:41:08*
