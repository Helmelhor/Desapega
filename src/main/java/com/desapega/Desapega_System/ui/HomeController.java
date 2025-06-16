package com.desapega.Desapega_System.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

// @Component é a anotação do Spring que diz: "Ei, Spring, gerencie esta classe!"
// Isso é o que permite que a injeção de dependências (@Autowired) funcione aqui.
@Component
public class HomeController {

    // @FXML conecta este método ao onAction="#handleVerificarEstoque" no arquivo FXML.
    // O nome do método deve ser exatamente o mesmo.
    @FXML
    void handleVerificarEstoque() {
        // Quando o botão for clicado, vamos mostrar um pop-up de alerta.
        // Isso prova que a View (FXML) e o Controller (Java) estão conectados
        // e que a integração com o Spring está funcionando.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso!");
        alert.setHeaderText("Conexão estabelecida");
        alert.setContentText("A interface JavaFX está sendo controlada por um Bean do Spring!");
        alert.showAndWait();
    }

    // injetar seus serviços aqui, por exemplo:
    //
    // @Autowired
    // private ProdutoService produtoService;
    //
    // E usar o produtoService dentro dos seus métodos.
}