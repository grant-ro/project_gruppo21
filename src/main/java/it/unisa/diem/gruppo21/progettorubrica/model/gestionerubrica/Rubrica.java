
package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */

import java.io.IOException;
import java.util.Iterator;
import javafx.collections.ObservableList;

/**
 * @file Rubrica.java
 * @brief Questo file contiene la definizione della classe Rubrica. 
 * Una rubrica è una collezione di contatti.
 * La classe fornisce metodi per aggiungere, modificare, eliminare e cercare contatti, oltre che a salvare, caricare, importare ed esportare la rubrica.
 * @invariant La rubrica è sempre ordinata secondo il criterio d’ordine naturale dell’oggetto contatto.
 * 
 */
public class Rubrica {

    private ObservableList <Contatto> contatti; ///< Lista osservabile di contatti
    
     /**
     * @brief Costruttore della classe `Rubrica`.
     * 
     *      
     * @post La rubrica viene creata ed inizializzata.
     */
    public Rubrica() {
 
    }

    /**
     * @brief Aggiunge un nuovo contatto alla rubrica.
     * 
     * @param[in] nuovoContatto Contatto da aggiungere.
     * 
     * 
     * @pre nuovoContatto è un contatto valido (soddisfa le invarianti di Contatto).
     * @post nuovoContatto è inserito nella rubrica.
     *
     * @return true se il contatto è stato inserito nella rubrica, altrimenti false.
     */
    public Contatto inserisciContatto(Contatto nuovoContatto) {

    }

    /**
     * @brief Modifica di un contatto esistente nella rubrica.
     * 
     * @param[in] contattoSelezionato è il contatto da modificare.
     * @param[in] contattoModificato è il contatto aggiornato.
     * 
     * @pre contattoSelezionato appartiene alla rubrica corrente.    
     * @pre contattoModificato è un contatto valido (soddisfa le invarianti di Contatto)
     *      
     * @post Il contattoSelezionato è modificato in modo da essere uguale (contiene le stesse info) al contattoModificato.
     *
     * @return true se il contatto è stato modficato con successo nella rubrica,altrimenti false
     */
    public true modificaContatto(Contatto contattoSelezionato, Contatto contattoModificato) {

    }

    /**
     * @brief Elimina un contatto esistente dalla rubrica.
     * 
     * @param[in] contattoSelezionato è il contatto da eliminare.
     * 
     * @pre contattoSelezionato appartiene alla rubrica corrente.   
     *      
     * @post Il contatto è rimosso dalla rubrica.
     *
     * @return true se il contatto è stato rimosso dalla rubrica, altrimenti false.
     */
    public boolean eliminaContatto(Contatto contattoSelezionato) {

    }

    /**
     * @brief Filtra la rubrica secondo una stringa di ricerca.
     * 
     * @param[in] input Stringa per l’effettuazione della ricerca.
     * 
     *@pre input è una stringa non vuota.
     *@post La rubrica corrente non viene modificata.
     *@return Rubrica contenente tutti e soli i contatti della rubrica corrente che hanno campo nome e/o campo cognome che iniziano con la stringa di ricerca fornita.
     */
    public Rubrica ricercaContatti(String input) {

    }
    
    /**
     * @brief Memorizza la rubrica su un file di memoria.
     * 
     *  
     * @param[in] nomeFileMemoria Nome del file di memoria locale in cui salvare la rubrica.
     * 
     *        
     * @pre nomeFileMemoria Deve essere il nome di un file preesistente localmente.
     * @pre Il file di memoria deve essere accessibile.
     * @pre Il nome del file contiene un'estensione corretta: .bin   
     * @post Il file nomeFileMemoria contiene la rubrica aggiornata.
     * 
     * @throws IOException se si verifica un errore durante il salvataggio
     * */
    
     public void salvaRubrica( String nomeFileMemoria) throws IOException {

    }
    
/**
 * @brief Carica i contatti di rubrica da un file di memoria.
 * 
 * @param[in] nomeFileMemoria Il nome del file da cui caricare la rubrica.
 * @pre  nomeFileMemoria  deve essere il nome di un file preesistente localmente.
 * @pre Il file di memoria deve essere accessibile.
 * @pre Il nome del file contiene un'estensione corretta: .bin.
 * @post La rubrica contiene i contatti caricati dal file di memoria.
 * 
 * @throws IOException se si verifica un errore durante il caricamento.
 */
    public void caricaRubrica(String nomeFileMemoria) throws IOException {

    }

/**
 * @brief Importa in rubrica contatti da un file locale.
 * 
 * @param[in] nomeFile Nome del file da cui importare i contatti in rubrica.
 * 
 * @pre nomeFile deve essere il nome di un file preesistente localmente.
 * @pre Il file di importazione deve essere accessibile.
 * @pre Il nome del file contiene un'estensione corretta: .csv. 
 * @post I contatti dal file CSV sono aggiunti alla rubrica esistente.
 * 
 * @return true se tutti i contatti sono stati inseriti con successo, altrimenti false.
 *
 * @throws IOException se si verifica un errore durante l’importazione.
 */
public boolean importaRubrica(String nomeFile) throws IOException{
}


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
}

/**    
 * @brief Restituisce un iteratore per la collezione rubrica.
 * @return Un iteratore sugli elementi di tipo Contatto della classe rubrica corrente.
 * 
 **/ 
 Iterator<Contatto> getIterator(){
     
 }

/**
 * @brief Fornisce una descrizione della Rubrica.
 *
 *@return Una stringa che  rappresenta la rubrica corrente.
 */


public String toString(){
    
}

