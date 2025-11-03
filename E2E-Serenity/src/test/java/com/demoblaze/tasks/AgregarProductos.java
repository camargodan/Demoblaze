package com.demoblaze.tasks;

import com.demoblaze.interactions.EsperarYAceptarAlerta;
import com.demoblaze.ui.pages.ProductosPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductos implements Task {

    public static AgregarProductos alCarrito() {
        return instrumented(AgregarProductos.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            // Agregar primer producto
            Click.on(ProductosPage.PRIMER_PRODUCTO),
            WaitUntil.the(ProductosPage.BOTON_AGREGAR_AL_CARRITO, isVisible())
                .forNoMoreThan(10).seconds(),
            Click.on(ProductosPage.BOTON_AGREGAR_AL_CARRITO),
            EsperarYAceptarAlerta.despuesDeAgregar(),
            
            // Volver al home y esperar que cargue
            Click.on(ProductosPage.ENLACE_HOME),
            WaitUntil.the(ProductosPage.SEGUNDO_PRODUCTO, isVisible())
                .forNoMoreThan(10).seconds(),
            
            // Agregar segundo producto
            Click.on(ProductosPage.SEGUNDO_PRODUCTO),
            WaitUntil.the(ProductosPage.BOTON_AGREGAR_AL_CARRITO, isVisible())
                .forNoMoreThan(10).seconds(),
            Click.on(ProductosPage.BOTON_AGREGAR_AL_CARRITO),
            EsperarYAceptarAlerta.despuesDeAgregar()
        );
    }
}
