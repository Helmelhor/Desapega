package com.desapega.Desapega_System.ui;

import com.desapega.Desapega_System.DesapegaApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import java.net.URL; // Importe a classe URL

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(DesapegaApplication.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws Exception {


        URL fxmlLocation = JavaFxApplication.class.getResource("/ui/home-view.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);


        loader.setControllerFactory(context::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Desapega - Sistema de Gestão");
        stage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}