package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */

import java.util.List;
import java.util.ArrayList;


/**
 * @file Contatto.java
 * @brief Questo file contiene la definizione della classe Contatto.
 * 
 * @class Contatto
 * @details Un contatto possiede i seguenti campi: nome, cognome, una lista di numeri di telefono e una lista di indirizzi email.
 * La classe fornisce metodi per ottenere e modificare queste informazioni.
 *

 * 
 * @invariant Il nome non deve essere null: nome != null
 * @invariant Il cognome non deve essere null: cognome != null
 * @invariant Il nome o il cognome non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
 * 
 * @invariant La lista numeriTelefono non deve essere null: numeriTelefono != null
 * @invariant Ogni numero di telefono deve essere una stringa non null, non vuota e non composta da soli spazi bianchi
 * @invariant Ogni numero di telefono deve essere numerico (composto solo da cifre)
 * @invariant La lista numeriTelefono non deve superare il numero massimo di numeri consentiti: numeriTelefono.size() <= maxNumeri
 * 
 * @invariant La lista indirizziEmail non deve essere null: indirizziEmail != null
 * @invariant Ogni indirizzo email deve essere una stringa non null,non vuota e non composta da soli spazi bianchi
 * @invariant La lista indirizziEmail non deve superare il numero massimo di indirizzi consentiti: indirizziEmail.size() <= maxEmail
 * 
 * 
 */
public class Contatto implements Comparable <Contatto> {

    private String nome; ///< Nome del contatto

    private String cognome; ///< Cognome del contatto

    private List<String> numeriTelefono; ///< Lista di numeri di telefono associati al contatto

    private List<String> indirizziEmail; ///< Lista di indirizzi email associati al contatto

    private static final int maxNumeri=3; ///< Numero massimo di numeri di telefono consentiti per contatto

    private static final int maxEmail=3; ///< Numero massimo di indirizzi email consentiti per contatto

    /**
     * @brief Costruttore della classe `Contatto`.
     * Crea e inizializza un'istanza della classe Contatto con i parametri forniti.
     * 
     * @param[in] nome Nome del contatto.
     * @param[in] cognome Cognome del contatto.
     * @param[in] numeriTelefono Lista di numeri di telefono.
     * @param[in] indirizziEmail Lista di indirizzi email.
     * 
     * 
     * @pre 'nome' non deve essere null: nome != null
     * @pre 'cognome' non deve essere null: cognome != null
     * @pre 'numeriTelefono' non deve essere null(numeriTelefono != null), nè contenere elementi che sono stringhe null,vuote o composte da soli spazi bianchi.
     * @pre 'indirizziEmail' non deve essere null (indirizziEmail != null),nè contenere elementi che sono stringhe null,vuote o composte da soli spazi bianchi.
     * 
     * 
     * @post Il contatto viene creato con nome, cognome, numeriTelefono e indirizziEmail specificati.
     * 
     * 
     * @throws ContattoNonValidoException se 'nome' e 'cognome' sono contemporaneamente vuoti o composti solo da spazi bianchi: nome.trim().isEmpty() && cognome.trim().isEmpty() 
     * @throws ContattoNonValidoException se la lista `numeriTelefono` contiene almeno un elemento che non è una stringa numerica (composta solo da cifre)
     * @throws ContattoNonValidoException numeriTelefono.size() <= maxNumeri
     * @throws ContattoNonValidoException indirizziEmail.size() <= maxEmail
     * 
     * 
     * @throws IllegalArgumentException se nome == null
     * @throws IllegalArgumentException se cognome == null
     * @throws IllegalArgumentException se numeriTelefono == null
     * @throws IllegalArgumentException se indirizziEmail == null
     * 
     */
    public Contatto(String nome, String cognome, List<String> numeriTelefono, List<String> indirizziEmail) throws ContattoNonValidoException {

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
     * @pre 'nome' non deve essere null: nome != null   
     * 
     * @post Il nome del contatto è aggiornato con il valore fornito, solo se nome fornito e cognome del contatto non sono contemporaneamente vuoti o composti solo da spazi bianchi.
     * @post Gli altri campi del contatto non vengono modificati.
     * 
     * @throw ContattoNonValidoException, senza modificare il campo nome corrente del contatto, se nome fornito e cognome del contatto sono contemporaneamente vuoti o composti solo da spazi bianchi
     * @throws IllegalArgumentException se nome == null
     */
    public void setNome(String nome) throws ContattoNonValidoException{

    }

    /**
     * @brief Restituisce il cognome del contatto.
     *
     * @return cognome del contatto.
     *
     */
    public String getCognome() {

    }

    /**
     * @brief Modifica il cognome del contatto.
     *
     * @param[in] cognome Nuovo cognome del contatto.
     * 
     * @pre 'cognome' non deve essere null: cognome != null 
     *
     * @post Il cognome del contatto è aggiornato con il valore fornito, solo se nome del contatto e cognome fornito non sono contemporaneamente vuoti o composti solo da spazi bianchi.
     * @post Gli altri campi del contatto non vengono modificati.
     * 
     * @throws ContattoNonValidoException, senza modificare il campo cognome corrente del contatto, se nome del contatto e cognome fornito sono contemporaneamente vuoti o composti solo da spazi bianchi
     * @throws IllegalArgumentException se cognome == null.
     *
     **/
    
    
    public void setCognome(String cognome) throws ContattoNonValidoException{

    }

    /**
     * @brief Restituisce la lista dei numeri di telefono del contatto.
     *
     * @return La lista dei numeri di telefono del contatto.
     *
     */
    public List<String> getNumeriTelefono() {

    }

     /**
     * @brief Aggiorna la lista dei numeri di telefono del contatto con la lista fornita.
     *
     * @param[in] numeriTelefono Nuova lista di numeri di telefono.
     * 
     * @pre 'numeriTelefono' non deve essere null(numeriTelefono != null), nè contenere elementi che sono stringhe null,vuote o composte da soli spazi bianchi.
     *
     * @post La lista dei numeri di telefono del contatto contiene soli e tutti i valori della lista fornita.
     * @post Gli altri campi del contatto non vengono modificati.
     * 
     * @throws ContattoNonValidoException se numeriTelefono.size() <= maxNumeri, senza aggiornare la lista dei numeri di telefono del contatto
     * @throws ContattoNonValidoException se la lista `numeriTelefono` contiene almeno un elemento che non è una stringa numerica (composta solo da cifre), senza aggiornare la lista dei numeri di telefono del contatto
     */
    public void setNumeriTelefono(List<String> numeriTelefono) throws ContattoNonValidoException {

    }

    /**
     * @brief Restituisce la lista degli indirizzi email del contatto.
     *
     * @return La lista degli indirizzi email del contatto.
     *
     */
    public List<String> getIndirizziEmail() {

    }

    /**     
     * @brief Aggiorna la lista degli indirizzi email del contatto con la lista fornita.
     *
     * @param[in] indirizziEmail Nuova lista di indirizzi email.
     *
     * @pre 'indirizziEmail' non deve essere null (indirizziEmail != null),nè contenere elementi che sono stringhe null,vuote o composte da soli spazi bianchi.
     * 
     * @post La lista degli indirizzi email del contatto contiene soli e tutti i valori della lista fornita.
     * @post Gli altri campi del contatto non vengono modificati.
     *
     * @throws ContattoNonValidoException indirizziEmail.size() <= maxEmail, senza aggiornare la lista degli indirizzi email del contatto
     */
    public void setIndirizziEmail(List<String> indirizziEmail) throws ContattoNonValidoException {

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
     *Segue un criterio di ordine alfabetico (da A a Z) per cognome e nome. 
     * L’ordine è determinato dalle seguenti operazioni: confronto del campo “Cognome” (dei contatti dotati di solo cognome o cognome e nome)  e del campo “Nome” (dei contatti privi di cognome),
     * senza priorità e distinzione alcuna dovuta ai campi considerati per il confronto. 
     * In caso di conflitto per cognomi omografi, si prosegue al confronto dei nomi dei contatti in conflitto.
     *
     * @param[in] c contatto con cui confrontare il contatto corrente.
     *
     *@return -1 se il contatto corrente precede il contatto specificato, 0 se sono uguali, +1 se il contatto corrente segue il contatto specificato,  secondo il criterio descritto.
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
    *La stringa sarà formattata nel seguente modo:
    *       - Il nome e il cognome del contatto sono mostrati come "Nome: this.nome " e "Cognome: this.cognome", separati da una nuova riga.
    *       - La lista dei numeri di telefono è mostrata come "Numeri di telefono: numero1, numero2, ..." se esistono numeri, 
    *         altrimenti come "Numeri di telefono: Nessun numero di telefono".
    *       - La lista degli indirizzi email è mostrata come "Indirizzi email: email1, email2, ..." se esistono email, 
    *         altrimenti come "Indirizzi email: Nessun indirizzo email".
    *       - Ogni sezione (nome, cognome, numeri di telefono e indirizzi email) è separata da una nuova riga.
    * 
    * Esempio di output:
    * Contatto con nome "Mario", cognome "Rossi", numeri di telefono "123456789" e "987654321" e email "mario.rossi@gmail.com":
    * 
    * Nome: Mario
    * Cognome: Rossi
    * Numeri di telefono: 123456789, 987654321
    * Indirizzi email: mario.rossi@gmail.com
    * 
    * 
    *@return Una stringa che  rappresenta il contatto corrente
    */

    @Override
    public String toString() 
    
    /**
    *@brief Confronta il contatto corrente con un altro contatto per verificarne l'uguaglianza.
    * L'uguaglianza è definita se il nome, cognome, numeri di telefonon e indirizzi email sono identici tra i due oggetti.
    *
    * @param[in] obj L'oggetto da confrontare con l'oggetto corrente. 
    * 
    * @pre obj != null 
    * @pre obj è un'istanza della classe Contatto.
    * 
    * @return true se l'oggetto corrente è uguale all'oggetto passato come parametro, 
    *         false altrimenti. 
    */
    
    @Override
    public boolean equals(Object obj)

    
     /**
     * @brief Calcola il codice hash per il contatto.
     * 
     * Questo metodo calcola un codice hash univoco per il contatto, utilizzando i seguenti attributi:
     * - nome
     * - cognome
     * - numeri di telefono
     * - indirizzi email
     * 
     * Il codice hash è calcolato utilizzando la combinazione dei codici hash dei vari campi.
     * Se un attributo è null, il suo valore hash è trattato come 0.
     * 
     * @return Il codice hash del contatto, calcolato dai campi nome, cognome, numeri di telefono e indirizzi email.
     */
    @Override
    public int hashCode() {
       
    }

