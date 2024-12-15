/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.controller;

import it.unisa.diem.gruppo21.progettorubrica.model.gestionedati.OperatoreFile;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionedati.GestorePersistenzaDati;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Contatto;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.ControlliValidità;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Rubrica;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gruppo21
 */

public class RubricaController implements Initializable {

  
    private Rubrica rubrica = new Rubrica(); //rubrica è l'oggetto che gestisce i contatti
    private OperatoreFile operatoreFile = new OperatoreFile();  //l'oggetto che gestisce le operazioni sui file
    
    private VBox telefonoSection; //Sezione per i numeri di telefono

    private VBox emailSection; //Sezione per gli indirizzi email

    private int phoneCount = 1; //Contatore per i numeri di telefono
    private int emailCount = 1; //Contatore per gli indirizzi email

    private Button aggiungiTelefono; //Bottone per aggiungere telefono
    private Button aggiungiEmail; //Bottone per aggiungere email
    @FXML
    private MenuButton menu;
    @FXML
    private MenuItem importa;
    @FXML
    private MenuItem esporta;
    @FXML
    private Button nuovoContatto;
    @FXML
    private Button modificaContatto;
    @FXML
    private Button eliminaContatto;
    @FXML
    private TextField ricerca;
    @FXML
    private ListView<Contatto> listaContatti;
    @FXML
    private Label nome;
    @FXML
    private TextField campoNome;
    @FXML
    private Label cognome;
    @FXML
    private TextField campoCognome;
    private TextField campoTelefono;
    private TextField campoEmail;
    @FXML
    private VBox rightVBox;
    @FXML
    private Button confermaButton;
    private Contatto contattoSelezionato; //Dichiarazione della variabile
    @FXML
    private Button indietroButton;
    @FXML
    private TextField campoTelefono1;
    @FXML
    private TextField campoTelefono2;
    @FXML
    private TextField campoTelefono3;
    @FXML
    private TextField campoIndirizzoEmail1;
    @FXML
    private TextField campoIndirizzoEmail2;
    @FXML
    private TextField campoIndirizzoEmail3;

 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //inizialmente la VBox di destra è nascosta
    rightVBox.setVisible(false);
            
    //Aggiungi un listener alla ListView per rilevare la selezione di un contatto
    listaContatti.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            mostraDettagliContatto(newSelection);
        }
        
    importa.setOnAction(event -> importa(event)); //Collega manualmente l'azione al metodo importa
    esporta.setOnAction(event -> esporta(event)); //Collega manualmente l'azione al metodo esporta
    });


    listaContatti.setCellFactory(param -> new ListCell<Contatto>() {
      @Override
      protected void updateItem(Contatto contatto, boolean empty) {
         super.updateItem(contatto, empty);
         if (empty || contatto == null) {
            setText(null); //Non mostrare nulla se il contatto è vuoto
         } else {
            //Mostra prima il cognome e poi il nome
            setText(contatto.getCognome() + " " + contatto.getNome());
         }
      }
    });

    }
  

    @FXML
    private void importa(ActionEvent event) {
    }

    @FXML
    private void esporta(ActionEvent event) {
    }

    @FXML
    private void aggiungiContatto(ActionEvent event) {
            // Mostra la VBox di destra
            rightVBox.setVisible(true);
    }

    @FXML
    private void modificaContatto(ActionEvent event) {
    }

    @FXML
    private void eliminaContatto(ActionEvent event) {
    }

    @FXML
    private void ricerca(ActionEvent event) {
    }

    @FXML
    private void aggiungiNome(ActionEvent event) {
    }

    @FXML
    private void aggiungiCognome(ActionEvent event) {
    }
}
