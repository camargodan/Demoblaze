# Demoblaze E2E

Proyecto de automatización de pruebas end-to-end para el sitio **Demoblaze** utilizando **Serenity BDD**, **JUnit 5** y el patrón **Screenplay**.

---

## Requisitos Previos

### Software Necesario

| Software | Versión Mínima | Versión Recomendada | Enlace de Descarga |
|----------|----------------|---------------------|-------------------|
| **Java JDK** | 11 | 17 o superior | [Adoptium Temurin](https://adoptium.net/) |
| **Maven** | 3.6.0 | 3.9.x | [Maven Download](https://maven.apache.org/download.cgi) |
| **Google Chrome** | Última versión estable | Última versión | [Chrome](https://www.google.com/chrome/) |

### Versiones del Proyecto Actual

```xml
Java: OpenJDK 25.0.1 (compatible con Java 11+)
Serenity BDD: 4.2.0
JUnit: 4.13.2
Maven Compiler: Java 17 (target)
```

---

## Instalación y Configuración

### 1. Verificar Instalación de Java

Abre una terminal (CMD o PowerShell en Windows) y ejecuta:

```bash
java -version
```

**Salida esperada:**
```
openjdk version "17.0.x" (o superior)
```

Si no está instalado:
1. Descarga [Adoptium Temurin JDK 17](https://adoptium.net/)
2. Instala siguiendo el asistente
3. Verifica que la variable de entorno `JAVA_HOME` esté configurada:
   ```bash
   echo %JAVA_HOME%
   ```
   Debería mostrar algo como: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x`

---

### 2. Verificar Instalación de Maven

```bash
mvn -version
```

**Salida esperada:**
```
Apache Maven 3.9.x
Maven home: C:\...
Java version: 17.0.x
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

### 3. Clonar o Descargar el Proyecto

```bash
cd C:\Users\heber\Projects\prueba-tecnica
cd serenity-junit-screenplay-starter
```

---

### 4. Descargar Dependencias

Ejecuta el siguiente comando en la raíz del proyecto (donde está `pom.xml`):

```bash
mvn clean install -U
```

Esto:
- Limpia compilaciones anteriores (`clean`)
- Descarga todas las dependencias del `pom.xml` (`install`)
- Fuerza actualización desde repositorios remotos (`-U`)

**Tiempo estimado:** 2-5 minutos (primera vez)

---

## Ejecución de Pruebas

### Opción 1: Ejecutar Solo los Tests (Rápido)

```bash
mvn clean test
```

**Qué hace:**
- Compila el código
- Ejecuta todos los tests en `src/test/java`
- Genera reporte básico en consola

**Tiempo estimado:** 30-60 segundos

---

### Opción 2: Ejecutar Tests + Generar Reporte Completo (Recomendado)

```bash
mvn clean verify
```

**Qué hace:**
- Compila el código
- Ejecuta todos los tests
- Genera reporte HTML completo de Serenity

**Tiempo estimado:** 1-2 minutos

---

### Opción 3: Ejecutar un Test Específico

```bash
mvn clean test -Dtest=FlujoCompraE2ETest
```

Esto ejecuta solo la clase `FlujoCompraE2ETest`.

---

## Ver los Reportes

### Reporte de Serenity BDD (HTML)

Después de ejecutar `mvn clean verify`, abre:

```
target/site/serenity/index.html
```

**En Windows (desde CMD):**
```bash
start target\site\serenity\index.html
```

**En Windows (desde PowerShell):**
```powershell
Invoke-Item target\site\serenity\index.html
```

**En Linux/Mac:**
```bash
open target/site/serenity/index.html
```

El reporte incluye:
- Resultados de cada escenario
- Capturas de pantalla (en caso de fallo)
- Estadísticas de ejecución
- Detalles paso a paso de cada test

---

## Estructura del Proyecto

```
serenity-junit-screenplay-starter/
│
├── pom.xml                          # Configuración de Maven y dependencias
├── serenity.conf                    # Configuración de Serenity (opcional)
│
└── src/test/java/com/demoblaze/
    │
    ├── models/                      # Modelos de datos
    │   └── DatosCompra.java         # Objeto con información de compra
    │
    ├── ui/pages/                    # Page Objects (elementos UI)
    │   ├── ProductosPage.java       # Elementos de la página de productos
    │   ├── CarritoPage.java         # Elementos del carrito
    │   └── FormularioCompraPage.java # Elementos del formulario de compra
    │
    ├── interactions/                # Interacciones reutilizables
    │   └── EsperarYAceptarAlerta.java # Manejo de alertas JavaScript
    │
    ├── tasks/                       # Tasks (acciones de alto nivel)
    │   ├── AgregarProductos.java    # Agregar productos al carrito
    │   ├── VisualizarCarrito.java   # Navegar al carrito
    │   └── CompletarCompra.java     # Completar formulario de compra
    │
    ├── questions/                   # Questions (verificaciones)
    │   ├── CantidadProductosEnCarrito.java # Cuenta productos en carrito
    │   └── MensajeExito.java        # Verifica mensaje de confirmación
    │
    └── tests/                       # Tests
        └── FlujoCompraE2ETest.java  # Test E2E completo de compra
```

---

## Escenario de Prueba

**Test:** `flujoCompletoDeCompraDemoblaze()`
**Descripción:** Valida el flujo completo de compra en Demoblaze.

### Pasos del Test:

1. ✅ **Given:** Usuario navega a `https://www.demoblaze.com/`
2. ✅ **When:** Agrega 2 productos al carrito
   - Selecciona primer producto
   - Hace clic en "Add to cart"
   - Acepta alerta de confirmación
   - Vuelve al Home
   - Selecciona segundo producto
   - Hace clic en "Add to cart"
   - Acepta alerta de confirmación
3. ✅ **When:** Visualiza el carrito
4. ✅ **Then:** Verifica que hay al menos 2 productos en el carrito
5. ✅ **When:** Completa el formulario de compra con datos de prueba:
   - Nombre: Miguel Delgado
   - País: Colombia
   - Ciudad: Bogotá
   - Tarjeta: 4111111111111111
   - Mes: 11
   - Año: 2025
6. ✅ **Then:** Verifica que aparece el mensaje "Thank you for your purchase!"

---

## 🔧 Configuración Avanzada

### Ejecutar en Modo Headless (sin interfaz gráfica)

Edita `src/test/resources/serenity.conf`:

```hocon
headless.mode = true
```

Luego ejecuta normalmente:
```bash
mvn clean verify
```

---

### Cambiar el Navegador

Por defecto usa **Chrome**. Para cambiar a Firefox:

Edita `src/test/resources/serenity.conf`:

```hocon
webdriver {
  driver = firefox
  autodownload = true
}
```

O en el test, cambia la anotación:

```java
@Managed(driver = "firefox")
WebDriver driver;
```

---

### Ejecutar en Paralelo (más rápido)

Edita `pom.xml` y agrega:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M5</version>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>2</threadCount>
    </configuration>
</plugin>
```
---

## Modificar el Test

### Cambiar los datos de compra

Edita `FlujoCompraE2ETest.java`:

```java
DatosCompra datosCompra = new DatosCompra(
    "Tu Nombre",           // Nombre
    "Tu País",             // País
    "Tu Ciudad",           // Ciudad
    "4111111111111111",    // Tarjeta de prueba
    "12",                  // Mes
    "2026"                 // Año
);
```

### Agregar más productos al carrito

Edita `AgregarProductos.java` y duplica el bloque:

```java
// Tercer producto
Click.on(ProductosPage.ENLACE_HOME),
WaitUntil.the(ProductosPage.TERCER_PRODUCTO, isVisible())
    .forNoMoreThan(10).seconds(),
Click.on(ProductosPage.TERCER_PRODUCTO),
WaitUntil.the(ProductosPage.BOTON_AGREGAR_AL_CARRITO, isVisible())
    .forNoMoreThan(10).seconds(),
Click.on(ProductosPage.BOTON_AGREGAR_AL_CARRITO),
EsperarYAceptarAlerta.despuesDeAgregar()
```

Y agrega en `ProductosPage.java`:

```java
public static final Target TERCER_PRODUCTO = Target.the("tercer producto de la lista")
        .located(By.xpath("(//div[@class='card-block']//h4/a)[3]"));
```
