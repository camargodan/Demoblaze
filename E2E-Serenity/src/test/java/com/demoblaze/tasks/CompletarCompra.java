package com.demoblaze.tasks;

import com.demoblaze.models.DatosCompra;
import com.demoblaze.ui.pages.CarritoPage;
import com.demoblaze.ui.pages.FormularioCompraPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletarCompra implements Task {

    private final DatosCompra datos;

    public CompletarCompra(DatosCompra datos) {
        this.datos = datos;
    }

    public static CompletarCompra conLosDatos(DatosCompra datos) {
        return instrumented(CompletarCompra.class, datos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(CarritoPage.BOTON_PLACE_ORDER),
            WaitUntil.the(FormularioCompraPage.CAMPO_NOMBRE, isVisible())
                .forNoMoreThan(5).seconds(),
            Enter.theValue(datos.getNombre()).into(FormularioCompraPage.CAMPO_NOMBRE),
            Enter.theValue(datos.getPais()).into(FormularioCompraPage.CAMPO_PAIS),
            Enter.theValue(datos.getCiudad()).into(FormularioCompraPage.CAMPO_CIUDAD),
            Enter.theValue(datos.getTarjeta()).into(FormularioCompraPage.CAMPO_TARJETA),
            Enter.theValue(datos.getMes()).into(FormularioCompraPage.CAMPO_MES),
            Enter.theValue(datos.getAnio()).into(FormularioCompraPage.CAMPO_ANIO),
            Click.on(FormularioCompraPage.BOTON_PURCHASE),
            WaitUntil.the(FormularioCompraPage.MENSAJE_EXITO, isVisible())
                .forNoMoreThan(10).seconds()
        );
    }
}
