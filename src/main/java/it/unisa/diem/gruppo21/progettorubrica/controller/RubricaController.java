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
  

 //Metodo che gestisce se il contatto inserito sia valido
    private boolean isValidInput() {
    //Verifica che tutti i campi siano validi prima di aggiungere il contatto
    String nome = campoNome.getText();
    String cognome = campoCognome.getText();
    String telefono = campoTelefono.getText();

    return ControlliValidità.controlloRiempimento(nome, cognome) && ControlliValidità.controlloNumeroTelefonico(telefono);
    }
    
    
    //Metodo che gestisce l'aggiunta di un contatto nella rubrica
    @FXML
    private void aggiungiContatto(ActionEvent event) {
    //Svuota tutti i campi di default
    clearFields();
    
    //Resetta il contatto selezionato, in modo che venga trattato come un nuovo contatto
    contattoSelezionato = null;

    //Abilita tutti i campi per l'inserimento dei dettagli del contatto
    abilitaCampi();

    //La VBox di destra risulta visibile per l'inserimento dei dati
    rightVBox.setVisible(true);
    rightVBox.setManaged(true);  //Assicurati che la VBox sia gestita nel layout

    //Disabilita tutti i bottoni fino a quando non termino le modifiche
    modificaContatto.setDisable(true);
    confermaButton.setDisable(true);  //Conferma ed Elimina, nella fase di aggiunta del contatto, inizialmente sono disabilitati
    eliminaContatto.setDisable(true);

    //Deseleziona eventuali contatti nella lista
    listaContatti.getSelectionModel().clearSelection();
    
    //Pulisci la barra di ricerca
    ricerca.setText(""); //Cancella il testo nel campo di ricerca

    //Mostra nuovamente tutti i contatti nella lista (ripristino lista completa)
    ObservableList<Contatto> tuttiContatti = rubrica.getContatti();
    
    //Ordina la lista prima di renderla visibile nella listaContatti
    ordinaContatti();
    
    //Imposta la lista ordinata nella ListView
    listaContatti.setItems(tuttiContatti);

    //Aggiornamento della visualizzazione
    listaContatti.refresh();

    //Il bottone "Conferma" si attiva solo se viene scritto qualcosa nel campi 
    campoNome.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoCognome.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoTelefono1.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoTelefono2.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoTelefono3.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoIndirizzoEmail1.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoIndirizzoEmail2.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
    campoIndirizzoEmail3.textProperty().addListener((observable, oldValue, newValue) -> abilitaConfermaButton());
}
  
  //Metodo che gestisce l'rdinamento dei contatti per cognome e, a parità di cognome, per nome
    //(in assenza del cognome, compaiono prima questi contatti, sempre in ordine alfabetico)
  
    private void ordinaContatti() {
    //Ordina la lista dei contatti prima per cognome e poi per nome
    listaContatti.getItems().sort((contatto1, contatto2) -> {
        //Prendi il cognome di contatto1, usa il nome se il cognome è vuoto
        String cognome1 = contatto1.getCognome();
        if (cognome1 == null || cognome1.isEmpty()) {
            cognome1 = contatto1.getNome(); // Usa il nome come "cognome"
        }

        //Prendi il cognome di contatto2, usa il nome se il cognome è vuoto
        String cognome2 = contatto2.getCognome();
        if (cognome2 == null || cognome2.isEmpty()) {
            cognome2 = contatto2.getNome(); // Usa il nome come "cognome"
        }

        //Confronto per cognome (o nome se il cognome è vuoto)
        int cognomeCompare = cognome1.compareToIgnoreCase(cognome2);
        if (cognomeCompare != 0) {
            return cognomeCompare; // Se i cognomi sono diversi, ritorna il risultato del confronto
        }

        //Se i cognomi (o i nomi) sono uguali, ordina per nome
        return contatto1.getNome().compareToIgnoreCase(contatto2.getNome());
    });

    //Aggiornamento della ListView
    listaContatti.refresh();
}
  //Metodo che gestisce l'aggiornamento della lista di contatti
    private void aggiornaListaContatti() {
    //Riordina i contatti e aggiorna la lista
    ordinaContatti();
    listaContatti.setItems(FXCollections.observableArrayList(rubrica.getContatti()));
}   

  
    //Metodo che gestisce la visualizzazione dei dettagli di un contatto selezione
    private void mostraDettagliContatto(Contatto contatto) {
    if (contatto != null) {
        //La rightVBox deve essere visibile
        rightVBox.setVisible(true);
        rightVBox.setManaged(true);

        //Popola i campi Nome e Cognome del contatto
        campoNome.setText(contatto.getNome() != null ? contatto.getNome() : "");
        campoCognome.setText(contatto.getCognome() != null ? contatto.getCognome() : "");

        // Popola i Numeri di telefono
        List<String> numeriTelefono = contatto.getNumeriTelefono() != null ? contatto.getNumeriTelefono() : new ArrayList<>();
        campoTelefono1.setText(numeriTelefono.size() > 0 ? numeriTelefono.get(0) : "");
        campoTelefono2.setText(numeriTelefono.size() > 1 ? numeriTelefono.get(1) : "");
        campoTelefono3.setText(numeriTelefono.size() > 2 ? numeriTelefono.get(2) : "");

        //Popola gli Indirizzi Email
        List<String> indirizziEmail = contatto.getIndirizziEmail() != null ? contatto.getIndirizziEmail() : new ArrayList<>();
        campoIndirizzoEmail1.setText(indirizziEmail.size() > 0 ? indirizziEmail.get(0) : "");
        campoIndirizzoEmail2.setText(indirizziEmail.size() > 1 ? indirizziEmail.get(1) : "");
        campoIndirizzoEmail3.setText(indirizziEmail.size() > 2 ? indirizziEmail.get(2) : "");

        //Disabilita tutti i campi
        disabilitaCampi();

        //Abilita i pulsanti di modifica ed eliminazione
        modificaContatto.setDisable(false);
        eliminaContatto.setDisable(false);
        confermaButton.setDisable(true); // Nascondi il pulsante 'Conferma'
      } else {
        //Nascondi la rightVBox se deselezioniamo il contatto
        rightVBox.setVisible(false);
        rightVBox.setManaged(false);
      }
}
//Metodo che gestisce la modifica di un contatto esistente nella lista di contatti
    @FXML
    private void modificaContatto(ActionEvent event) {
    // Ottieni il contatto selezionato dalla lista
    contattoSelezionato = listaContatti.getSelectionModel().getSelectedItem();

    if (contattoSelezionato != null) {
        //La rightVBox deve essere visibile
        rightVBox.setVisible(true);
        rightVBox.setManaged(true);

        //Vengono popolati i contenuti dei campi di ogni contatto
        campoNome.setText(contattoSelezionato.getNome() != null ? contattoSelezionato.getNome() : "");
        campoCognome.setText(contattoSelezionato.getCognome() != null ? contattoSelezionato.getCognome() : "");

        List<String> numeriTelefono = contattoSelezionato.getNumeriTelefono() != null ? contattoSelezionato.getNumeriTelefono() : new ArrayList<>();
        campoTelefono1.setText(numeriTelefono.size() > 0 ? numeriTelefono.get(0) : "");
        campoTelefono2.setText(numeriTelefono.size() > 1 ? numeriTelefono.get(1) : "");
        campoTelefono3.setText(numeriTelefono.size() > 2 ? numeriTelefono.get(2) : "");

        List<String> indirizziEmail = contattoSelezionato.getIndirizziEmail() != null ? contattoSelezionato.getIndirizziEmail() : new ArrayList<>();
        campoIndirizzoEmail1.setText(indirizziEmail.size() > 0 ? indirizziEmail.get(0) : "");
        campoIndirizzoEmail2.setText(indirizziEmail.size() > 1 ? indirizziEmail.get(1) : "");
        campoIndirizzoEmail3.setText(indirizziEmail.size() > 2 ? indirizziEmail.get(2) : "");

        //Abilita tutti i campi per la modifica
        abilitaCampi();

        //Disabilita i pulsanti Modifica ed Elimina, mentre viene abilitato Conferma
        modificaContatto.setDisable(true);
        eliminaContatto.setDisable(true);
        confermaButton.setDisable(false);

        //Il pulsante Indietro è visibile
        indietroButton.setVisible(true);
        indietroButton.setManaged(true);

       } else {
        //Mostra un messaggio di errore se nessun contatto è stato selezionato
        mostraErrore("Nessun contatto selezionato per la modifica.");
       }
}
   
    
    //Metodo che gestisce l'annullamento delle modifiche effettuate ad un contatto
    private void annullaModifica(ActionEvent event) {
    //Nascondi la rightVBox e torna alla schermata principale
    indietroButton();

    //Quando annullo la modifica, ritorno alla visualizzazione iniziale
    System.out.println("Modifica annullata. Torno alla schermata principale.");
}


    //Metodo che gestisce l'eliminazione dei contatti
    @FXML
    private void eliminaContatto(ActionEvent event) {
    //Ottieni il contatto selezionato
    Contatto contattoSelezionato = listaContatti.getSelectionModel().getSelectedItem();

    if (contattoSelezionato != null) {
        //Chiedi conferma all'utente tramite un pop-up
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questo contatto?");
        alert.setContentText("Questa azione è irreversibile.");

        //Attendi la risposta dell'utente
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            //Rimuovi il contatto dalla rubrica
            rubrica.eliminaContatto(contattoSelezionato);

            //Aggiorna la lista dei contatti
            listaContatti.setItems(rubrica.getContatti());

            //Nascondi la VBox di destra
            rightVBox.setVisible(false);

            //Mostra messaggio di successo
            Alert successo = new Alert(Alert.AlertType.INFORMATION);
            successo.setTitle("Eliminazione Completata");
            successo.setHeaderText(null);
            successo.setContentText("Il contatto è stato eliminato con successo.");
            successo.showAndWait();

            System.out.println("Contatto eliminato con successo.");
          } else {
            //Se l'utente annulla l'operazione, torna alla schermata principale
            System.out.println("Operazione di eliminazione annullata. Torna alla schermata principale.");

            //Nascondi la rightVBox
            rightVBox.setVisible(false);

            //Mostra solo la lista dei contatti
            listaContatti.setVisible(true);

            //Mostra messaggio di annullamento
            Alert annullamento = new Alert(Alert.AlertType.INFORMATION);
            annullamento.setTitle("Operazione Annullata");
            annullamento.setHeaderText(null);
            annullamento.setContentText("Eliminazione del contatto annullata.");
            annullamento.showAndWait();
          }
           } else {
            mostraErrore("Nessun contatto selezionato per l'eliminazione.");
           }
}

  //Metodo che gestisce la Conferma per l'inserimento di un nuovo contatto o per le modifiche ad un contatto esistente
    @FXML
    private void conferma(ActionEvent event) {
    //Ottieni i valori dai campi di input
    String nome = campoNome.getText();
    String cognome = campoCognome.getText();

    //Numeri telefono
    String telefono1 = campoTelefono1.getText();
    String telefono2 = campoTelefono2.getText();
    String telefono3 = campoTelefono3.getText();

    //Indirizzi Email
    String email1 = campoIndirizzoEmail1.getText();
    String email2 = campoIndirizzoEmail2.getText();
    String email3 = campoIndirizzoEmail3.getText();

    //Verifica validità dei campi
    if (!ControlliValidità.controlloRiempimento(nome, cognome)) {
        mostraErrore("Nome e Cognome non possono essere vuoti.");
        return;
    }

    ArrayList<String> numeriTelefono = new ArrayList<>();
     for (String telefono : Arrays.asList(telefono1, telefono2, telefono3)) {
       if (!telefono.isEmpty()) {
         if (!ControlliValidità.controlloNumeroTelefonico(telefono)) {
            mostraErrore("Il numero di telefono \"" + telefono + "\" non è valido.");
            return;
         }
         numeriTelefono.add(telefono);
       }
      }
  
    ArrayList<String> indirizziEmail = new ArrayList<>();
     for (String email : Arrays.asList(email1, email2, email3)) {
       if (!email.isEmpty()) {
         if (email.trim().isEmpty()) {
            mostraErrore("L'indirizzo email \"" + email + "\" non può essere vuoto.");
            return;
         }
         indirizziEmail.add(email);
       }
     }

     
    // Modifica contatto esistente
    if (contattoSelezionato != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Modifica");
        alert.setHeaderText("Sei sicuro di voler modificare questo contatto?");
        alert.setContentText("Nome: " + nome + "\nCognome: " + cognome);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            contattoSelezionato.setNome(nome);
            contattoSelezionato.setCognome(cognome);
            contattoSelezionato.setNumeriTelefono(numeriTelefono);
            contattoSelezionato.setIndirizziEmail(indirizziEmail);

            ordinaContatti();
            listaContatti.setItems(rubrica.getContatti());
            listaContatti.refresh();

            rightVBox.setVisible(false);
            rightVBox.setManaged(false);

            Alert successo = new Alert(Alert.AlertType.INFORMATION);
            successo.setTitle("Modifica Completata");
            successo.setHeaderText(null);
            successo.setContentText("Il contatto è stato modificato con successo.");
            successo.showAndWait();
        }
      } else {
        //Inserimento nuovo contatto
        Contatto nuovoContatto = new Contatto(nome, cognome, numeriTelefono, indirizziEmail);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Inserimento");
        alert.setHeaderText("Sei sicuro di voler aggiungere questo contatto?");
        alert.setContentText("Nome: " + nome + "\nCognome: " + cognome);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean successo = rubrica.inserisciContatto(nuovoContatto);

            if (successo) {
                ordinaContatti();
                listaContatti.setItems(rubrica.getContatti());
                listaContatti.refresh();

                rightVBox.setVisible(false);
                rightVBox.setManaged(false);
                clearFields();

                contattoSelezionato = null;
            }
        } else {
            rightVBox.setVisible(false);
            rightVBox.setManaged(false);
            listaContatti.setVisible(true);
        }
      }
}

  
    //Metodo per abilitare/disabilitare il bottone di Conferma
    private void abilitaConfermaButton() {
    //Verifica se almeno uno dei campi non è vuoto
    boolean campiValidi = !campoNome.getText().trim().isEmpty() ||
                          !campoCognome.getText().trim().isEmpty() ||
                          !campoTelefono1.getText().trim().isEmpty() ||
                          !campoTelefono2.getText().trim().isEmpty() ||
                          !campoTelefono3.getText().trim().isEmpty() ||
                          !campoIndirizzoEmail1.getText().trim().isEmpty() ||
                          !campoIndirizzoEmail2.getText().trim().isEmpty() ||
                          !campoIndirizzoEmail3.getText().trim().isEmpty();

    //Abilita o disabilita Conferma in base alla validità dei campi
    confermaButton.setDisable(!campiValidi);
}


    //Metodo che gestisce l'aggiunta di un contatto alla ListView, dopo essere stato modificato
    private void aggiungiNuovoContatto(ActionEvent event) {
    // Resetta il contatto selezionato, in modo che venga trattato come un nuovo contatto
    contattoSelezionato = null;
    
    // Mostra i campi per inserire un nuovo contatto
    rightVBox.setVisible(true);
    rightVBox.setManaged(true);
    
    //Svuota i campi di input
    clearFields();
}

  

  
  
    @FXML
    private void aggiungiNome(ActionEvent event) {
    }

    @FXML
    private void aggiungiCognome(ActionEvent event) {
    }
}
