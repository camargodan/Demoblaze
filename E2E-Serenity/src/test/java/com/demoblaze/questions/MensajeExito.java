package com.demoblaze.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

public class MensajeExito implements Question<Boolean> {

    private final Target mensajeTarget;

    public MensajeExito(Target mensajeTarget) {
        this.mensajeTarget = mensajeTarget;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // Verifica si el mensaje está visible
        return Visibility.of(mensajeTarget)
            .answeredBy(actor);
    }

    public static MensajeExito esVisible(Target mensajeTarget) {
        return new MensajeExito(mensajeTarget);
    }
}
