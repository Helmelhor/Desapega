
package com.desapega.Desapega_System;

//encontra a classe JavaFxApplication no lugar certo
import com.desapega.Desapega_System.ui.JavaFxApplication;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesapegaApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}
}