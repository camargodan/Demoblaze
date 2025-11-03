package com.demoblaze.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CarritoPage {
    
    public static final Target PRODUCTOS_EN_CARRITO = Target.the("productos en el carrito")
            .located(By.xpath("//tbody[@id='tbodyid']/tr"));
    
    public static final Target BOTON_PLACE_ORDER = Target.the("botón Place Order")
            .located(By.xpath("//button[contains(text(),'Place Order')]"));
}
