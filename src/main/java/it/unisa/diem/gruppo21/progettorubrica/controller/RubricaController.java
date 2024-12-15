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
  
    //Metodo che gestisce la ricerca di un contatto nella ListView
    @FXML
    private void ricerca(ActionEvent event) {
    String inputRicerca = ricerca.getText().toLowerCase();  //Acquisisci l'input di ricerca in minuscolo
    
    if (inputRicerca.isEmpty()) {
        //Se l'input di ricerca è vuoto, mostra tutti i contatti
        listaContatti.setItems(rubrica.getContatti());
    } else {
        //Filtra i contatti sulla base di nome, cognome, telefono o email
        FilteredList<Contatto> risultatiRicerca = rubrica.getContatti().filtered(contatto -> {
            //Rendi la ricerca case-sensitive, e cerca in più campi
            boolean nomeMatch = contatto.getNome().toLowerCase().startsWith(inputRicerca);
            boolean cognomeMatch = contatto.getCognome().toLowerCase().startsWith(inputRicerca);
            boolean telefonoMatch = contatto.getNumeriTelefono().stream()
                                              .anyMatch(telefono -> telefono.toLowerCase().startsWith(inputRicerca));
            boolean emailMatch = contatto.getIndirizziEmail().stream()
                                          .anyMatch(email -> email.toLowerCase().startsWith(inputRicerca));
            //Restituisci true se uno dei campi corrisponde
            return nomeMatch || cognomeMatch || telefonoMatch || emailMatch;
        });

        //Aggiorna la ListView con i risultati filtrati
        listaContatti.setItems(risultatiRicerca);
    }
}
//Metodo che gestisce il salvataggio dei contatti della rubrica alla chiusura dell'Interfaccia Utente
    //Garantisce che alla chiusura dell'interfaccia la rubrica memorizzi i dati presenti in quell'istante, se disponibili.
    public void chiudiInterfaccia() {
    try {
        // Verifica se la variabile rubrica è null e inizializzala se necessario
        if (rubrica == null) {
            rubrica = new Rubrica(); // Crea una nuova rubrica se non è già stata inizializzata
            System.out.println("Rubrica inizializzata.");
        }

        // Salvataggio della rubrica tramite GestorePersistenzaDati
        GestorePersistenzaDati.salva(rubrica);
        //System.out.println("Rubrica salvata con successo in locale");

    } catch (IOException e) {
        // Gestione degli errori durante il salvataggio
        System.err.println("Errore durante il salvataggio della rubrica: " + e.getMessage());
        e.printStackTrace(); // Aggiungi un trace per avere più informazioni sull'errore

    } catch (Exception e) {
        // Gestione di qualsiasi altra eccezione non prevista
        System.err.println("Errore imprevisto durante il salvataggio: " + e.getMessage());
        e.printStackTrace(); // Aggiungi un trace per avere più informazioni sull'errore
    }
}

   //Metodo che gestisce l'esportazione della rubrica da un file CSV
    @FXML
    public void esporta(ActionEvent event) {
    ordinaContatti();    
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Salva Rubrica");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File CSV", "*.csv"));

    //Configurazione del fileChooser
    fileChooser.setTitle("Esporta Rubrica");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File CSV", "*.csv"));

    //Apri la finestra di dialogo per la selezione del file
    File file = fileChooser.showSaveDialog(new Stage());
       
    if (file != null) {  
        try {
            //Usa il metodo scrivi per esportare i dati della rubrica
            operatoreFile.scrivi(file.getAbsolutePath(), rubrica);
            System.out.println("Esportazione completata: " + file.getAbsolutePath());

            //Mostra finestra di successo
            mostraMessaggio("Successo", "Rubrica esportata con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante l'esportazione del file: " + e.getMessage());
            e.printStackTrace();
            
            // Mostra finestra di errore se c'è stato un problema
            mostraErrore("Errore durante l'esportazione del file.");
        }
    } else { 
        System.out.println("Nessun file selezionato.");
    }
}
    
  
    //Metodo per l'abilitazione di tutti i campi
    private void abilitaCampi() {
    campoNome.setDisable(false);
    campoCognome.setDisable(false);
    campoTelefono1.setDisable(false);
    campoTelefono2.setDisable(false);
    campoTelefono3.setDisable(false);
    campoIndirizzoEmail1.setDisable(false);
    campoIndirizzoEmail2.setDisable(false);
    campoIndirizzoEmail3.setDisable(false);
}
  
    //Metodo che gestisce i messaggi di ogni tipo
    private void mostraMessaggio(String titolo, String messaggio) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titolo);
    alert.setHeaderText(null);
    alert.setContentText(messaggio);
    alert.showAndWait();
    }

  
     //Metodo che gestisce i pop-up del bottone Indietro 
    @FXML
    private void indietroButton() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Conferma Uscita");
    alert.setHeaderText("Sei sicuro di tornare indietro?");
    alert.setContentText(" ");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        //Nascondi la rightVBox per tornare alla schermata principale
        rightVBox.setVisible(false);
        rightVBox.setManaged(false);

        //Deseleziona il contatto attualmente selezionato
        listaContatti.getSelectionModel().clearSelection();

        //Ricarica la lista dei contatti
        listaContatti.setItems(rubrica.getContatti());
        listaContatti.refresh();

        //Resetta il contatto selezionato
        contattoSelezionato = null;
    }
}


    //Metodo che gestisce il caricamento dei contatti della rubrica all'avvio dell'Interfaccia Utente
    //Garantisce che all'avvio dell'interfaccia la rubrica contenga i dati salvati in precedenza, se disponibili.
    public void avviaInterfaccia() {
    try {
        //Assicurati che la rubrica sia inizializzata
        if (rubrica == null) {
            rubrica = new Rubrica(); //Inizializza la rubrica se è null
        }

        //Carica i contatti della rubrica dalla memoria
        boolean caricata = GestorePersistenzaDati.carica(rubrica);

        if (!caricata) {
            System.out.println("Nessun dato precedente trovato. Rubrica vuota.");
        } else {
            
            //Verifica se la rubrica è vuota dopo il caricamento
            if (rubrica.getContatti().isEmpty()) {
                System.out.println("Il file rubrica_dati.bin è vuoto.");
            } 
        }
        
        listaContatti.setItems(rubrica.getContatti()); 
        
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Errore durante il caricamento della rubrica: " + e.getMessage());
    }
}


    //Metodo che gestisce l'importazione della rubrica su un file CSV
    @FXML
    public void importa(ActionEvent event) {
    ordinaContatti();    
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File CSV", "*.csv"));
    fileChooser.setTitle("Importa Rubrica");

    //Mostra la finestra di dialogo per la selezione del file
    File file = fileChooser.showOpenDialog(new Stage());

    //Se l'utente ha selezionato un file
    if (file != null){
    try {
        //Legge i contatti dal file e crea una nuova rubrica
        Rubrica rubricaDaImportare = operatoreFile.leggi(file.getAbsolutePath());

        //Aggiunge i contatti validi alla rubrica principale
        for (Contatto contatto : rubricaDaImportare.getContatti()) {
            rubrica.inserisciContatto(contatto);
        }
        ordinaContatti();
        listaContatti.setItems(FXCollections.observableArrayList(rubrica.getContatti()));
       
        //Notifica all'utente il successo dell'importazione
        System.out.println("Importazione completata con successo.");
     }catch (IOException e) {
        //Gestione dell'eccezione in caso di problemi con il file
        System.err.println("Errore durante la lettura del file: " + e.getMessage());
     }
    }
}


    //Metodo per la pulizia di tutti i campi
    private void clearFields() {
    campoNome.clear();
    campoCognome.clear();
    campoTelefono1.clear();
    campoTelefono2.clear();
    campoTelefono3.clear();
    campoIndirizzoEmail1.clear();
    campoIndirizzoEmail2.clear();
    campoIndirizzoEmail3.clear();
}


    //Metodo che disabilita i campi
    private void disabilitaCampi() {
    campoNome.setDisable(true);
    campoCognome.setDisable(true);
    campoTelefono1.setDisable(true);
    campoTelefono2.setDisable(true);
    campoTelefono3.setDisable(true);
    campoIndirizzoEmail1.setDisable(true);
    campoIndirizzoEmail2.setDisable(true);
    campoIndirizzoEmail3.setDisable(true);
} 

  
    //Metodo che gestisce i messaggi di Errore
    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Errore");
        alert.setContentText(messaggio);
        alert.showAndWait();
}


    @FXML
    private void campoTelefono1(ActionEvent event) {
    }

    
    @FXML
    private void campoTelefono2(ActionEvent event) {
    }

    
    @FXML
    private void campotelefono3(ActionEvent event) {
    }

  
    @FXML
    private void campoIndirizzoEmail1(ActionEvent event) {
    }

    
    @FXML
    private void campoIndirizzoEmail2(ActionEvent event) {
    }

    
    @FXML
    private void campoIndirizzoEmail3(ActionEvent event) {
    }
    
  
    @FXML
    private void aggiungiNome(ActionEvent event) {
    }

    @FXML
    private void aggiungiCognome(ActionEvent event) {
    }
}
