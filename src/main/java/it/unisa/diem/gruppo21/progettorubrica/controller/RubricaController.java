/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Chiara
 */
public class RubricaController implements Initializable {

   @FXML
    private VBox telefonoSection; // Sezione per i numeri di telefono

    @FXML
    private VBox emailSection; // Sezione per gli indirizzi email

    private int phoneCount = 1; // Contatore per i numeri di telefono
    private int emailCount = 1; // Contatore per gli indirizzi email

    @FXML
    private Button aggiungiTelefono; // Bottone per aggiungere telefono
    @FXML
    private Button aggiungiEmail; // Bottone per aggiungere email
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
    private ListView<?> listaContatti;
    @FXML
    private Label nome;
    @FXML
    private TextField campoNome;
    @FXML
    private Label cognome;
    @FXML
    private TextField campoCognome;
    @FXML
    private TextField campoTelefono;
    @FXML
    private TextField campoEmail;
    @FXML
    private VBox rightVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // Nascondi inizialmente la VBox di destra
            rightVBox.setVisible(false);
    }

    // Metodo che si attiva quando l'utente clicca sul bottone "+" per i numeri di telefono
    @FXML
    public void aggiungiTelefono(ActionEvent event) {
           if (phoneCount < 3) {
            phoneCount++;

            // Crea una nuova riga per il telefono
            HBox newPhoneRow = new HBox();
            newPhoneRow.setSpacing(10);

            // Crea un nuovo campo di testo per il numero di telefono
            TextField newPhoneField = new TextField();
            newPhoneField.setPrefWidth(165.0);
            newPhoneField.setPromptText("Inserisci numero di telefono");

            // Aggiungi il campo alla riga
            newPhoneRow.getChildren().add(newPhoneField);

            // Aggiungi la riga alla sezione del telefono
            telefonoSection.getChildren().add(newPhoneRow);
    }
    }        

    // Metodo che si attiva quando l'utente clicca sul bottone "+" per gli indirizzi email
    @FXML
    public void aggiungiEmail(ActionEvent event) {
        if (emailCount < 3) {
            emailCount++;

            // Crea una nuova riga per l'email
            HBox newEmailRow = new HBox();
            newEmailRow.setSpacing(10);

            // Crea un nuovo campo di testo per l'indirizzo email
            TextField newEmailField = new TextField();
            newEmailField.setPromptText("Inserisci indirizzo email");

            // Aggiungi il campo alla riga
            newEmailRow.getChildren().add(newEmailField);

            // Aggiungi la riga alla sezione dell'email
            emailSection.getChildren().add(newEmailRow);
        }
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