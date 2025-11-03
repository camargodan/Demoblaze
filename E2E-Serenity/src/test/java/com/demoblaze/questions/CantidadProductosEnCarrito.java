package com.demoblaze.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class CantidadProductosEnCarrito implements Question<Integer> {

    private final Target elementosCarrito;

    public CantidadProductosEnCarrito(Target elementosCarrito) {
        this.elementosCarrito = elementosCarrito;
    }

    @Override
    public Integer answeredBy(Actor actor) {
        // Cuenta cuántos elementos coinciden con el target
        return elementosCarrito.resolveAllFor(actor).size();
    }

    public static CantidadProductosEnCarrito es(Target elementosCarrito) {
        return new CantidadProductosEnCarrito(elementosCarrito);
    }
}
