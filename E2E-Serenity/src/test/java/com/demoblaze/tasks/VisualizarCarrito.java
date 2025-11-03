package com.demoblaze.tasks;

import com.demoblaze.ui.pages.CarritoPage;
import com.demoblaze.ui.pages.ProductosPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VisualizarCarrito implements Task {

    public static VisualizarCarrito deCompras() {
        return instrumented(VisualizarCarrito.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(ProductosPage.ENLACE_CARRITO),
            WaitUntil.the(CarritoPage.BOTON_PLACE_ORDER, isVisible())
                .forNoMoreThan(10).seconds()
        );
    }
}
