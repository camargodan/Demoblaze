# Demoblaze API Automation

Proyecto de automatización de pruebas API para el sistema **Demoblaze** utilizando **Karate Framework** y diseño BDD con archivos `.feature`. Permite validar flujos de autenticación (`signup`, `login`) y combinaciones de datos sobre endpoints REST.

---

## Requisitos Previos

### Software Necesario

| Software | Versión Mínima | Versión Recomendada | Enlace de Descarga |
|----------|----------------|---------------------|-------------------|
| **Java JDK** | 17 | 21 | [OpenJDK 21](https://learn.microsoft.com/es-es/java/openjdk/download) |
| **Maven** | 3.6.0 | 3.9.x | [Apache Maven](https://maven.apache.org/download.cgi) |
| **Karate Framework** | 1.4.1 | 1.5.0+ | [Karate Docs](https://karatelabs.github.io/karate/) |

### Versiones del Proyecto Actual

```xml
Java: OpenJDK 21
Karate: 1.4.1
JUnit: 5.10.1
Maven Compiler: Java 17 (target)
```

---

## Instalación y Configuración

### 1. Verificar Instalación de Java

Abre una terminal y ejecuta:

```bash
java -version
```

**Salida esperada:**
```
openjdk version "21.0.x"
```

Si no está instalado:
1. Descarga [OpenJDK 21](https://learn.microsoft.com/es-es/java/openjdk/download)
2. Instala siguiendo el asistente
3. Verifica que la variable de entorno `JAVA_HOME` esté configurada:
   ```bash
   echo %JAVA_HOME%
   ```
   Debería mostrar algo como: `C:\Program Files\Microsoft\jdk-21.0.x`

---

### 2. Verificar Instalación de Maven

```bash
mvn -version
```

**Salida esperada:**
```
Apache Maven 3.9.x
Maven home: C:\...
Java version: 21.0.x
```

Si no está instalado:

#### Windows:
1. Descarga [Apache Maven](https://maven.apache.org/download.cgi) (archivo `.zip`)
2. Extrae en `C:\Program Files\Apache\maven`
3. Agrega a las variables de entorno:
   - Variable `MAVEN_HOME`: `C:\Program Files\Apache\maven`
   - Agrega a `Path`: `%MAVEN_HOME%\bin`
4. Reinicia la terminal y verifica con `mvn -version`

#### Linux/Mac:
```bash
# Con SDKMAN (recomendado)
curl -s "https://get.sdkman.io" | bash
sdk install maven

# O con Homebrew (Mac)
brew install maven
```

---

### 3. Clonar el Proyecto

```bash
git clone https://github.com/camargodan/demoblaze-api.git
cd demoblaze-api
```

---

### 4. Descargar Dependencias

Ejecuta el siguiente comando en la raíz del proyecto (donde está `pom.xml`):

```bash
mvn clean install
```

Esto:
- Limpia compilaciones anteriores (`clean`)
- Descarga todas las dependencias del `pom.xml` (`install`)
- Compila el proyecto

**Tiempo estimado:** 1-3 minutos (primera vez)

---

## Ejecución de Pruebas

### Opción 1: Ejecutar Todos los Tests

```bash
mvn test
```

**Qué hace:**
- Compila el código
- Ejecuta todos los tests en `src/test/resources/features`
- Genera reporte en consola

**Tiempo estimado:** 20-60 segundos

---

### Opción 2: Ejecutar con Reporte Detallado

```bash
mvn clean test
```

**Qué hace:**
- Limpia archivos anteriores
- Ejecuta todos los tests
- Genera logs detallados

---

### Opción 3: Ejecutar un Feature Específico

```bash
mvn test -Dkarate.options="classpath:features/auth.feature"
```

Esto ejecuta solo el archivo `auth.feature`.

---

## Ver los Reportes

Después de ejecutar los tests, los reportes se generan en:

```
target/karate-reports/
```

Abre el archivo HTML principal:

**En Windows (desde CMD):**
```bash
start target\karate-reports\karate-summary.html
```

**En Windows (desde PowerShell):**
```powershell
Invoke-Item target\karate-reports\karate-summary.html
```

**En Linux/Mac:**
```bash
open target/karate-reports/karate-summary.html
```

El reporte incluye:
- Resultados de cada escenario
- Detalles de requests y responses
- Estadísticas de ejecución
- Timeline de ejecución

---

## Estructura del Proyecto

```
demoblaze-api/
│
├── pom.xml                          # Configuración de Maven y dependencias
├── .gitignore                       # Archivos ignorados por Git
├── README.md                        # Este archivo
│
└── src/test/
    │
    ├── java/runner/
    │   └── DemoblazeRunner.java     # Test runner de JUnit
    │
    └── resources/
        │
        ├── features/
        │   └── auth.feature         # Escenarios de autenticación
        │
        └── karate-config.js         # Configuración global de Karate
```

---

## Escenarios de Prueba

### Feature: Pruebas de Autenticación en Demoblaze API

El archivo `auth.feature` incluye los siguientes escenarios:

#### 1. **Crear un nuevo usuario exitosamente** ✅
- **Método:** POST `/signup`
- **Validación:** Response vacío `""`
- **Datos:** Usuario y contraseña generados dinámicamente

#### 2. **Intentar crear un usuario que ya existe** ✅
- **Método:** POST `/signup`
- **Validación:** Mensaje de error en JSON
- **Resultado esperado:** `{"errorMessage":"This user already exist."}`

#### 3. **Login exitoso con credenciales válidas** ✅
- **Método:** POST `/login`
- **Validación:** Token de autenticación en response
- **Flujo:** Crear usuario → Login → Verificar token

#### 4. **Login fallido con password incorrecto** ✅
- **Método:** POST `/login`
- **Validación:** Mensaje de error `{"errorMessage":"Wrong password."}`

#### 5. **Login fallido con usuario inexistente** ✅
- **Método:** POST `/login`
- **Validación:** Mensaje de error `{"errorMessage":"User does not exist."}`

#### 6. **Pruebas con diferentes combinaciones (Scenario Outline)** ✅
- Valida múltiples combinaciones de usuarios y contraseñas
- Usa tablas de ejemplos para data-driven testing

---


