package com.demoblaze.tests;

import com.demoblaze.models.DatosCompra;
import com.demoblaze.questions.CantidadProductosEnCarrito;
import com.demoblaze.questions.MensajeExito;
import com.demoblaze.tasks.AgregarProductos;
import com.demoblaze.tasks.CompletarCompra;
import com.demoblaze.tasks.VisualizarCarrito;
import com.demoblaze.ui.pages.CarritoPage;
import com.demoblaze.ui.pages.FormularioCompraPage;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@ExtendWith(SerenityJUnit5Extension.class)
public class FlujoCompraE2ETest {

    @Managed(driver = "chrome")
    WebDriver driver;

    Actor usuario;

    @BeforeEach
    public void setUp() {
        usuario = Actor.named("Heber QA")
                .whoCan(BrowseTheWeb.with(driver));
    }

    @Test
    public void flujoCompletoDeCompraDemoblaze() {
        // Preparar datos de compra
        DatosCompra datosCompra = new DatosCompra(
            "Heber Camargo",
            "Colombia",
            "Cucuta",
            "6111111111111111",
            "11",
            "2025"
        );

        // Given: Usuario navega al sitio
        givenThat(usuario).wasAbleTo(
            Open.url("https://www.demoblaze.com/")
        );

        // When: Agrega productos al carrito
        when(usuario).attemptsTo(
            AgregarProductos.alCarrito(),
            VisualizarCarrito.deCompras()
        );

        // Then: Verifica que hay productos en el carrito
        then(usuario).should(
            seeThat("La cantidad de productos en el carrito", 
                CantidadProductosEnCarrito.es(CarritoPage.PRODUCTOS_EN_CARRITO), 
                greaterThanOrEqualTo(2))
        );

        // When: Completa la compra
        when(usuario).attemptsTo(
            CompletarCompra.conLosDatos(datosCompra)
        );

        // Then: Verifica mensaje de éxito
        then(usuario).should(
            seeThat("El mensaje de compra exitosa es visible", 
                MensajeExito.esVisible(FormularioCompraPage.MENSAJE_EXITO), 
                equalTo(true))
        );
    }
}
