package com.demoblaze.ui.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductosPage {
    
    public static final Target PRIMER_PRODUCTO = Target.the("primer producto de la lista")
            .located(By.xpath("(//div[@class='card-block']//h4/a)[1]"));
    
    public static final Target SEGUNDO_PRODUCTO = Target.the("segundo producto de la lista")
            .located(By.xpath("(//div[@class='card-block']//h4/a)[2]"));
    
    public static final Target BOTON_AGREGAR_AL_CARRITO = Target.the("botón agregar al carrito")
            .located(By.xpath("//a[contains(text(),'Add to cart')]"));
    
    public static final Target ENLACE_HOME = Target.the("enlace a la página principal")
            .located(By.xpath("//a[@class='nav-link' and contains(text(),'Home')]"));
    
    public static final Target ENLACE_CARRITO = Target.the("enlace al carrito")
            .located(By.id("cartur"));
}
