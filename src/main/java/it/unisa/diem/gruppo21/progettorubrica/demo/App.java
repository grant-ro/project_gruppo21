package it.unisa.diem.gruppo21.progettorubrica.demo;

import it.unisa.diem.gruppo21.progettorubrica.controller.RubricaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Scene scene; // Variabile di istanza per la scena
    private RubricaController controller; // Variabile di istanza per il controller

    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("RubricaView"), 900, 650);

        
        // Assicurati che la rubrica venga caricata all'avvio
        if (controller != null) {
            controller.avviaInterfaccia(); // Chiamata al metodo avviaInterfaccia() per caricare i dati
        }

        // Imposta l'evento di chiusura della finestra per il salvataggio della rubrica
        stage.setOnCloseRequest(event -> {
            // Chiama il metodo del controller per salvare la rubrica
            if (controller != null) {
                controller.chiudiInterfaccia(); // Assicurati che il controller sia stato caricato
            }
        });
        
        
        stage.setScene(scene);
        stage.show();
    }

    
    // Metodo di istanza per cambiare la scena
    void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    
    private Parent loadFXML(String fxml) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/it/unisa/diem/gruppo21/progettorubrica/demo/RubricaView.fxml"));
       FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
       Parent root = fxmlLoader.load();
        
        // Recupera il controller dal FXML
        controller = fxmlLoader.getController(); // Recupera il controller

        return root;
    }

    
    public static void main(String[] args) {
        launch();
    }
}
