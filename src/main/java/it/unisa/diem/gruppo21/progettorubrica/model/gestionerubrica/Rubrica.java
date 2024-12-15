package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import static it.unisa.diem.gruppo21.progettorubrica.model.gestionedati.GestorePersistenzaDati.*;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionedati.OperatoreFile;

/**
 * @file Rubrica.java
 * @brief Questo file contiene la definizione della classe Rubrica.
 * 
 * @class Rubrica
 * @details Una rubrica è una collezione di contatti. @see Contatto
 * La classe fornisce metodi per aggiungere, modificare, eliminare e cercare contatti, oltre che a salvare, caricare, importare ed esportare la rubrica.
 * 
 * @invariant La rubrica è sempre ordinata secondo il criterio d’ordine naturale dell’oggetto contatto. 
 * @invariant Contatti non deve mai essere null: contatti != null
 * 
 */
public class Rubrica {

    private ObservableList <Contatto> contatti; ///< Lista osservabile di contatti
    
    /**
     * @brief Costruttore della classe `Rubrica`.
     * Inizializza la rubrica con una lista di contatti vuota.
     *      
     * @post La rubrica non possiede alcun contatto.
     */
    public Rubrica() {
        this.contatti = FXCollections.observableArrayList();
    }

    /**
    * @brief Restituisce un'`ObservableList` che rappresenta lo stato attuale della rubrica. 
    * @details Qualsiasi modifica alla rubrica  sarà automaticamente riflessa  sull'observable list restituita da questo metodo. 
    *
    * @return Un'ObservableList di oggetti `Contatto`, contenente tutti i contatti della rubrica corrente.
    */
    public ObservableList<Contatto> getContatti(){
        return contatti;
    }
    
    /**
     * @brief Aggiunge un nuovo contatto alla rubrica.
     * 
     * @param[in] nuovoContatto Contatto da aggiungere.
 
     * @return true se il contatto è stato inserito nella rubrica, altrimenti false.
     * 
     * @pre nuovoContatto non deve essere null: nuovoContatto!=null.
     * @pre nuovoContatto  deve essere un contatto valido, ossia soddisfare le invarianti definite per l'oggetto Contatto. @see Contatto
     * 
     * @post nuovoContatto è inserito nella rubrica.
     * @post L'inserimento del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al nuovoContatto. 
     *         La lista dei contatti rimane invariata ad eccezione del contatto inserito.
     *
     * @throws IllegalArgumentException se 'nuovoContatto' è null.
     */
    public boolean inserisciContatto(Contatto nuovoContatto) {
        if (nuovoContatto == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
        
        // Aggiunge il contatto alla rubrica
        boolean aggiunto = contatti.add(nuovoContatto);
    
        // Riordina la lista se è il contatto è stato inserito
        if (aggiunto) {
             contatti.sort(null);  // Riordina la lista usando l'ordine naturale definito in Contatto
        }
        return aggiunto;
    }
        
     /**
     * @brief Modifica di un contatto esistente nella rubrica.
     * 
     * @param[in] contattoSelezionato Contatto da modificare.
     * @param[in] contattoModificato Nuovo contatto che sostituirà quello selezionato..
     * 
     * @return true se il contattoSelezionato era presente nella rubrica ed è stato modificato,false se il contatto non era presente nella rubrica 
     * 
     * @pre contattoModificato deve essere un contatto valido, ossia soddisfare le invarianti definite per l'oggetto Contatto.
     * @pre contattoSelezionato non deve essere null: contattoSelezionato != null.
     * @pre contattoModificato non deve essere null: contattoModificato != null.
     * 
     * 
     * @post Il contattoSelezionato è modificato in modo da essere uguale (contiene le stesse info) al contattoModificato.
     * @post La modifica del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al contattoSelezionato. 
     *
     * @throws IllegalArgumentException se 'contattoSelezionato' o 'contattoModificato' sono null.
     */
    public boolean modificaContatto(Contatto contattoSelezionato, Contatto contattoModificato){  
        if (contattoSelezionato == null || contattoModificato == null) {
           throw new IllegalArgumentException("Contatto selezionato e contatto modificato non possono essere null.");
        }
        
        // Trova l'indice del contatto selezionato nella rubrica
         int index = contatti.indexOf(contattoSelezionato);

       
        if (index == -1) {     //index=-1 se il contatto selezionato non esiste
            return false; //Restituisce false se il contatto selezionato non esiste in rubrica
        } else {
              contatti.set(index, contattoModificato); // Sostituisce il contatto selezionato con quello modificato se il contatto selezionato esiste in rubrica
              contatti.sort(null); // Riordina la lista usando l'ordine naturale definito in Contatto
              return true;
        }
    }
    
    /**
     * @brief Elimina un contatto dalla rubrica.
     * 
     * Questo metodo rimuove il contatto specificato dalla rubrica,senza rimuoverne eventuali duplicati
     * 
     * @param[in] contattoSelezionato è il contatto da eliminare.
     *  
     * @return true se il contatto è stato rimosso dalla rubrica,false se il contatto non era presente nella rubrica quando il metodo stato invocato. 
     * 
     * @pre contattoSelezionato non deve essere null: contattoSelezionato!=null.
     * 
     * @post contattoSelezionato non è più presente nella rubrica.
     * @post Eventuali duplicati del contattoSelezionato non sono stati rimossi dalla rubrica.
     * @post L'eliminazione del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al contattoSelezionato. 
     *         La lista dei contatti rimane invariata ad eccezione del contatto rimosso.
     * 
     * @throws IllegalArgumentException se 'contattoSelezionato' è null.
     */
    public boolean eliminaContatto(Contatto contattoSelezionato) {
        if (contattoSelezionato == null) {
            throw new IllegalArgumentException("Il contatto non può essere null.");
        }
    
         // Verifica se il contatto esiste nella rubrica e lo rimuove
        return contatti.remove(contattoSelezionato); //Restituisce true se il contatto esiste in rubrica,altrimenti false
    }
    

    /**
     *@brief Filtra la rubrica secondo una stringa di ricerca.
     * 
     *@param[in] input Stringa per l’effettuazione della ricerca.
     *
     *@return Rubrica contenente tutti e soli i contatti della rubrica corrente che hanno campo nome e/o campo cognome che iniziano con la stringa di ricerca fornita;non è casesensitive.
     * 
     *@pre La stringa di ricerca non può essere null:input!=null
     *
     *@post La rubrica corrente non viene modificata. 
     *
     *
     *@throws IllegalArgumentException se 'input' è null. 
     */
     public Rubrica ricercaContatti(String input) {
        if (input == null) {
            throw new IllegalArgumentException("La stringa di ricerca non può essere null.");
        }
        
        //Crea una nuova rubrica che conterrà i risultati della ricerca
        Rubrica rubricaRisultati = new Rubrica();

        // Converte l'input in minuscolo per realizzare una ricerca ricerca case-insensitive
        String inputLower = input.toLowerCase();

        for (Contatto contatto : contatti) {
            //Rende i campi cognome e nome in minuscolo per rendere la ricerca case-insensitive
            //Verifica se nome o cognome iniziano con la stringa di ricerca
            if (contatto.getNome().toLowerCase().startsWith(inputLower) || contatto.getCognome().toLowerCase().startsWith(inputLower)) {
                rubricaRisultati.inserisciContatto(contatto); 
            }
        }
        return rubricaRisultati;
     }   

    /**
    * @brief Carica la rubrica in un file di persistenza predefinito.
    * 
    * 
    * @details Questo metodo tenta di caricare i dati della rubrica da un file di persistenza predefinito, scritto in una precedente sessione dell'applicazione come effetto dell'invocazione del metodo salvaRubrica. 
    * Se il file non esiste, è possibile che il metodo salvaRubrica non sia mai stato invocato prima, quindi il file non è stato creato. In tal caso, il metodo restituisce `false`. 
    * Se il file esiste e viene caricato correttamente, restituisce `true`.
    * Lancia IOException Se ci sono problemi nel caricamento della rubrica:leggere il file: permessi insufficienti, file danneggiato, formato errato.
    * 
    * @return `true` se il file predefinito di persistenza esiste e la rubrica è stata caricata correttamente da esso, 'false` se il file non esiste. 
    * 
    * 
    * @post Se il file predefinito di persistenza esiste e viene letto correttamente(il metodo restituisce true), la rubrica contiene tutti i contatti letti dal file di persitenza.
    * @post Se il file predefinito di persistenza non esiste, la rubrica corrente non viene modficata.
    * 
    */
    public  boolean caricaRubrica() throws IOException, ClassNotFoundException {
            return carica(this);
    }
        
    /**
     * @brief Salva la rubrica in un file di persistenza predefinito
     * 
     * @details  Questo metodo salva la rubrica corrente in un file di persistenza predefinito. 
     * Se il file non esiste,verrà creato,altrimenti il suo contenuto verrà sovrascritto con i contatti della rubrica corrente.
     * La rubrica viene salvata in un file predefinito, che include tutti i dati necessari per ricaricare la rubrica corrente nell'applicazione.
     * Lancia IOException se si verifica un errore durante il salvataggio della rubrica: permessi insufficienti o errore di I/O
     * 
     *        
     * @post Un file di persistenza predefinito è stato creato, se non esisteva prima dell'invocazione del metodo,altrimenti sovrascritto.
     * @post Il file di persistenza predefinito contiene esattamente i contatti correnti della rubrica in memoria, formattati correttamente per essere caricati in rubrica con un'operazione di caricaRubrica.
     * @post La rubrica corrente non viene modificata
     * 
     */
     public void salvaRubrica() throws IOException {
            salva(this);
     }


    /**
     * @brief Importa in rubrica contatti da un file locale.
     * 
     * @param[in] nomeFile Nome del file da cui importare i contatti in rubrica.
     * 
     * @pre nomeFile deve essere il nome di un file preesistente localmente.
     * @pre Il nome del file contiene un'estensione corretta: .csv. 
     * 
     * @post I contatti dal file CSV sono aggiunti alla rubrica esistente.
     * 
     * @return true se tutti i contatti sono stati inseriti con successo, altrimenti false.
     *
     * @throws IOException se si verifica un errore durante l’importazione.
     *
    public boolean importaRubrica(String nomeFile) throws IOException{
        return (new OperatoreFile().leggi(nomeFile,this));
    }*/


    /**
     * @brief Esporta la rubrica su un file locale.
     * 
     * @param[in] nomeFile Nome del file su cui esportare la rubrica.
     * 
     * @pre nomeFile deve essere un nome valido per la creazione di un file.
     * @pre Il nome del file contiene un'estensione corretta: .csv.  
     * @post Viene creato un nuovo file CSV, di nome nomeFile.
     * @post  Il file CSV creato contiene la rubrica aggiornata.
     *
     * @throws IOException se si verifica un errore durante l’esportazione.
     */
    public void esportaRubrica(String nomeFile) throws IOException {
         new OperatoreFile().scrivi(nomeFile,this);
    }

    /**
    *@brief Fornisce una descrizione del contatto
    *
    * Questo metodo restituisce una rappresentazione della rubrica in formato stringa come una concatenazione delle informazioni di ciascun contatto.
    * Ogni sezione di informazioni di contatto  è separata da una nuova riga. 
    * La formattazione generale è la seguente:
    * 
    * Contatto1  
    * Contatto2 
    * ...
    * ContattoX 
    * 
    * Dove "ContattoX" è una rappresentazione stringa di un singolo contatto, che è ottenuta dal metodo toString() della classe `Contatto`. 
    * 
    * 
    *@return Una stringa che rappresenta lo stato della rubrica corrente
    */
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
    
        //Scorre la rubrica e ricava la stringa descittiva di ogni contatto di rubrica 
        for (Contatto contatto : contatti) {
            sb.append(contatto.toString()).append("\n"); //Al termine di ogni contatto inserisce i separatori per la leggibilità
        }
            
        return sb.toString();
    }


}



