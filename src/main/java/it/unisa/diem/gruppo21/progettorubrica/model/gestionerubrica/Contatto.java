package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */

import java.util.ArrayList;

/**
 * @file Contatto.java
 * @brief Questo file contiene la definizione della classe Contatto.
 *
 * Un contatto possiede i seguenti campi: nome, cognome, una lista di numeri di telefono e una lista di indirizzi email.
 * La classe fornisce metodi per ottenere e modificare queste informazioni.
 *
 * @invariant numeriTelefono.size()<= maxNumeri.
 * @invariant indirizziEmail.size()<= maxEmail.
 * @invariant i campi nome e cognome non devono essere contemporaneamente vuoti.
 * @invariant nessun campo della classe (nome, cognome, numeriTelefono, indirizziEmail) deve essere null.
 * @invariant ogni elemento di numeriTelefono deve essere una stringa non null.
 * @invariant ogni elemento di indirizziEmail deve essere una stringa non null.
 * @invariant ogni elemento di numeriTelefono deve essere una stringa numerica.
 */
public class Contatto implements Comparable <Contatto> {

    private String nome; ///< Nome del contatto

    private String cognome; ///< Cognome del contatto

    private ArrayList<String> numeriTelefono; ///< Lista di numeri di telefono associati al contatto

    private ArrayList<String> indirizziEmail; ///< Lista di indirizzi email associati al contatto

    private static final int maxNumeri; ///< Numero massimo di numeri di telefono consentiti per contatto

    private static final int maxEmail; ///< Numero massimo di indirizzi email consentiti per contatto

    /**
     * @brief Costruttore della classe `Contatto`.
     * Crea e inizializza un'istanza della classe Contatto con i dati forniti.
     * 
     * @param[in] nome Nome del contatto.
     * @param[in] cognome Cognome del contatto.
     * @param[in] numeriTelefono Lista di numeri di telefono.
     * @param[in] indirizziEmail Lista di indirizzi email.
     * @throws it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.ContattoNonValidoException
     * @brief Costruttore della classe `Contatto`.
     *
     * @pre  numeriTelefono non possiede elementi null
     * @pre  indirizziEmail non possiede elementi null
     * @pre numeriTelefono.size()<= maxNumeri
     * @pre indirizziEmail.size()<= maxEmail
     *
     * @post il contatto viene creato con nome, cognome, numeriTelefono e indirizziEmail specificati, se non viene sollevata un’eccezione.
     * @post il contatto viene creato con campo nome uguale a una stringa vuota se nome==null.
     * @post il contatto viene creato con campo cognome uguale a una stringa vuota se cognome==null.
     * 
     * @throw ContattoNonValidoException se nome e cognome sono entrambe stringhe vuote.
     * @throw ContattoNonValidoException se la lista numeriTelefono contiene almeno un elemento che è una stringa non numerica.
     *
     */
    public Contatto(String nome, String cognome, ArrayList<String> numeriTelefono, ArrayList<String> indirizziEmail) throws ContattoNonValidoException {

    }

    /**
     * @brief Restituisce il nome del contatto.
     *
     * @return nome del contatto.
     *
     */
    public String getNome(){

    }
 
    /**
     * @brief Modifica il nome del contatto.
     *
     * @param[in] nome Nuovo nome del contatto.
     *
     * 
     *
     * @post Il nome del contatto è aggiornato con il valore fornito, se non nullo.
     * @post Il nome del contatto è aggiornato con una stringa vuota se nome==null.
     * @post Gli altri campi del contatto non vengono modificati.
     * @throw ContattoNonValidoException, senza modificare il campo nome corrente del contatto, se nome fornito è una stringa nulla o vuota e cognome del contatto è una stringa vuota.
     */
    public void setNome(String nome) throws ContattoNonValidoException{

    }

    /**
     * @brief Restituisce il cognome del contatto.
     *
     * @return cognome del contatto.
     *
     *
     */
    public String getCognome() {

    }

    /**
     * @brief Modifica il cognome del contatto.
     *
     * @param[in] cognome Nuovo cognome del contatto.
     * @post Il cognome del contatto è aggiornato con il valore fornito, se non nullo.
     * @post Il cognome del contatto è aggiornato con una stringa vuota, se cognome==null.
     * @post Gli altri campi del contatto non vengono modificati.
     * @throw ContattoNonValidoException, senza modificare il campo cognome corrente del contatto, se nome del contatto è una stringa vuota e cognome fornito è una stringa nulla o vuota.
     * 
     *
     **/
    
    public void setCognome(String cognome) throws ContattoNonValidoException{

    }

    /**
     * @brief Restituisce la lista dei numeri di telefono associati al contatto.
     *
     * @return La lista dei numeri di telefono del contatto.
     *
     */
    public ArrayList<String> getNumeriTelefono() {

    }

    /**
     * @brief Aggiorna la lista dei numeri di telefono del contatto con la lista fornita.
     *
     * @param[in] numeriTelefono Nuova lista di numeri di telefono.

     * @pre numeriTelefono.size()<= maxNumeri.
     * @pre numeriTelefono non possiede elementi null.
     *
     * @post La lista dei numeri di telefono del contatto contiene soli e tutti i valori della lista fornita.
     * @post Gli altri campi del contatto non vengono modificati.
     */
    public void setNumeriTelefono(ArrayList<String> numeriTelefono) {

    }

    /**
     * @brief Restituisce la lista degli indirizzi email.
     *
     * @return La lista degli indirizzi email del contatto.
     *
     */
    public ArrayList<String> getIndirizziEmail() {

    }

    /**     

     * @brief Aggiorna la lista degli indirizzi email del contatto con la lista fornita.
     *
     * @param[in] indirizziEmail Nuova lista di indirizzi email.
     *
     * @pre  indirizziEmail.size()<= maxEmail.
     * @pre  indirizziEmail non possiede elementi null.
     * @post La lista degli indirizzi email del contatto contiene soli e tutti i valori della lista fornita.
     * @post Gli altri campi del contatto non vengono modificati.
     *
     */
    public void setIndirizziEmail(ArrayList<String> indirizziEmail) {

    }

    /**
     * @brief Restituisce il numero massimo di numeri di telefono consentiti per contatto.
     *
     * @return Il numero massimo di numeri di telefono consentiti per contatto.
     */
    public int getMaxNumeri(){

    }

    /**
     * @brief Restituisce il numero massimo di indirizzi email consentiti per contatto.
     *
     * @return Il numero massimo di numeri di indirizzi email consentiti per contatto.
     */
    public int getMaxEmail(){

    }

    /**
     *@brief Confronta il contatto corrente con il contatto specificato secondo un criterio di ordinamento
     *Segue un criterio di ordine alfabetico (da A a Z) per cognome e nome. L’ordine è determinato dalle seguenti operazioni: confronto del campo “Cognome” (dei contatti dotati di solo cognome o cognome e nome)  e del campo “Nome” (dei contatti privi di cognome), senza priorità e distinzione alcuna dovuta ai campi considerati per il confronto. In caso di conflitto per cognomi omografi, si prosegue al confronto dei nomi dei contatti in conflitto.
     *@param[in] c contatto con cui confrontare il contatto corrente.
     *@return  -1 se il contatto corrente precede il contatto specificato, 0 se sono uguali, +1 se il contatto corrente segue il contatto specificato,  secondo il criterio descritto.
     *
     *
     */
    
    

    @Override
    public int compareTo(Contatto c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    


/**
*@brief Fornisce una descrizione del contatto
*
*@return Una stringa che  rappresenta il contatto corrente

*/

public String toString(){
}

}



