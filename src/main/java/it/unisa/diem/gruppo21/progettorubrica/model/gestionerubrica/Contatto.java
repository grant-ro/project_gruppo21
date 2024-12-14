package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import static it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.ControlliValidità.*;



/**
 * @file Contatto.java
 * @brief Questo file contiene la definizione della classe Contatto.
 * 
 * @class Contatto
 * @details Un contatto possiede i seguenti campi: nome, cognome, una lista di numeri di telefono e una lista di indirizzi email.
 * La classe fornisce metodi per ottenere e modificare queste informazioni.
 *

 * 
 * @invariant Il nome non deve essere null: nome != null.
 * @invariant Il cognome non deve essere null: cognome != null.
 * @invariant Il nome o il cognome non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
 * 
 * @invariant La lista numeriTelefono non deve essere null: numeriTelefono != null.
 * @invariant Ogni numero di telefono deve essere una stringa non null, non vuota e non composta da soli spazi bianchi.
 * @invariant Ogni numero di telefono deve essere numerico (composto solo da cifre) e composto da sole 10 cifre.
 * @invariant La lista numeriTelefono non deve superare il numero massimo di numeri consentiti: numeriTelefono.size() ≤ maxNumeri.
 * 
 * @invariant La lista indirizziEmail non deve essere null: indirizziEmail != null.
 * @invariant Ogni indirizzo email deve essere una stringa non null,non vuota e non composta da soli spazi bianchi.
 * @invariant La lista indirizziEmail non deve superare il numero massimo di indirizzi consentiti: indirizziEmail.size() ≤ maxEmail.
 * 
 * 
 */
public class Contatto implements Comparable <Contatto> {

    private String nome; ///< Nome del contatto

    private String cognome; ///< Cognome del contatto

    private List<String> numeriTelefono; ///< Lista di numeri di telefono associati al contatto

    private List<String> indirizziEmail; ///< Lista di indirizzi email associati al contatto

    private static final int MAX_NUMERI=3; ///< Numero massimo di numeri di telefono consentiti per contatto

    private static final int MAX_EMAIL=3; ///< Numero massimo di indirizzi email consentiti per contatto

    /**
     * @brief Costruttore della classe `Contatto`.
     * Inizializza un'istanza della classe Contatto con i parametri forniti.
     * 
     * @param[in] nome Nome del contatto.
     * @param[in] cognome Cognome del contatto.
     * @param[in] numeriTelefono Lista di numeri di telefono.
     * @param[in] indirizziEmail Lista di indirizzi email.
     * 
     * 
     * @pre Il nome non deve essere null: nome != null.
     * @pre Il cognome non deve essere null: cognome != null.
     * @pre Il nome o il cognome non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
     * 
     * @pre La lista numeriTelefono non deve essere null: numeriTelefono != null.
     * @pre Ogni numero di telefono deve essere una stringa non null, non vuota e non composta da soli spazi bianchi.
     * @pre Ogni numero di telefono deve essere numerico (composto solo da cifre) e composto da sole 10 cifre.
     * @pre La lista numeriTelefono non deve superare il numero massimo di numeri consentiti: numeriTelefono.size() ≤ maxNumeri.
     * 
     * @pre La lista indirizziEmail non deve essere null: indirizziEmail != null.
     * @pre Ogni indirizzo email deve essere una stringa non null,non vuota e non composta da soli spazi bianchi.
     * @pre La lista indirizziEmail non deve superare il numero massimo di indirizzi consentiti: indirizziEmail.size() ≤ maxEmail.
     * 
     * @post Il contatto viene iniziallizato con nome, cognome, numeriTelefono e indirizziEmail forniti.
     * 
     */
    public Contatto(String nome, String cognome, List<String> numeriTelefono, List<String> indirizziEmail) {
        
        this.nome = nome;
        this.cognome = cognome;
        
        // Crea nuovi ArrayList con le informazioni passate
        this.numeriTelefono = new ArrayList<>(numeriTelefono);
        this.indirizziEmail = new ArrayList<>(indirizziEmail);
    }

    /**
     * @brief Restituisce il nome del contatto.
     *
     * @return nome del contatto.
     *
     */
    public String getNome(){
        return nome;
    }
 
    /**
     * @brief Modifica il nome del contatto.
     *
     * @param[in] nome Nuovo nome del contatto.
     *
     * 
     * @pre 'nome' non deve essere null: nome != null.
     * @pre  nome fornito e cognome corrente del contatto non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
     * 
     * @post Il nome del contatto è aggiornato con il valore fornito.
     * @post Gli altri campi del contatto non vengono modificati.
     * 
     */
    public void setNome(String nome) {
        // Verifica che nome non sia null e che nome e cognome non diventino contemporaneamente vuoti o composti da soli spazi bianchi per effetto di tale set.
        if (nome == null || !controlloRiempimento(nome,this.cognome) ) {
            throw new IllegalArgumentException("Parametro cognome non valido per il contatto");
        }

        // Aggiorna il nome del contatto dopo che tutti i controlli sono stati passati.
        this.nome = nome;
    }
    

    /**
     * @brief Restituisce il cognome del contatto.
     *
     * @return cognome del contatto.
     *
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @brief Modifica il cognome del contatto.
     *
     * @param[in] cognome Nuovo cognome del contatto.
     * 
     * @pre 'cognome' non deve essere null: cognome != null.
     * @pre  nome corrente del contatto e cognome fornito non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
     *
     * @post Il cognome del contatto è aggiornato con il valore fornito.
     * @post Gli altri campi del contatto non vengono modificati.
     * 
     */
    public void setCognome(String cognome) {
        // Verifica che  cognome non sia null e che nome e cognome non diventino contemporaneamente vuoti o composti da soli spazi bianchi per effetto di tale set.
        if (cognome == null || !controlloRiempimento(this.nome,cognome) ) {
            throw new IllegalArgumentException("parametro cognome non valido per il contatto");
        }

        // Aggiorna il nome del contatto dopo che tutti i controlli sono stati passati.
        this.cognome = cognome;
    }
    
    /**
     * @brief Restituisce la lista dei numeri di telefono del contatto.
     *
     * @return La lista dei numeri di telefono del contatto.
     *
     */
    public List<String> getNumeriTelefono() {
        return numeriTelefono;
    }

     /**
     * @brief Aggiorna la lista dei numeri di telefono del contatto con la lista fornita.
     *
     * @param[in] numeriTelefono Nuova lista di numeri di telefono.
     * 
     * @pre La lista numeriTelefono non deve essere null: numeriTelefono != null.
     * @pre Ogni numero di telefono deve essere una stringa non null, non vuota e non composta da soli spazi bianchi.
     * @pre Ogni numero di telefono deve essere numerico (composto solo da cifre) e composto da sole 10 cifre.
     * @pre La lista numeriTelefono non deve superare il numero massimo di numeri consentiti: numeriTelefono.size() ≤ maxNumeri.
     * 
     * @post La lista dei numeri di telefono del contatto contiene soli e tutti i valori della lista fornita.
     * @post Gli altri campi del contatto non vengono modificati.
     * 
     */
    public void setNumeriTelefono(List<String> numeriTelefono) {
        // Crea un nuovo ArrayList con le informazioni passate.
        this.numeriTelefono = new ArrayList<>(numeriTelefono);
    }

    /**
     * @brief Restituisce la lista degli indirizzi email del contatto.
     *
     * @return La lista degli indirizzi email del contatto.
     *
     */
    public List<String> getIndirizziEmail() {
        return indirizziEmail;
    }

    /**     
     * @brief Aggiorna la lista degli indirizzi email del contatto con la lista fornita.
     *
     * @param[in] indirizziEmail Nuova lista di indirizzi email.
     *
     * @pre La lista indirizziEmail non deve essere null: indirizziEmail != null.
     * @pre Ogni indirizzo email deve essere una stringa non null,non vuota e non composta da soli spazi bianchi.
     * @pre La lista indirizziEmail non deve superare il numero massimo di indirizzi consentiti: indirizziEmail.size() ≤ maxEmail.
     * 
     * @post La lista degli indirizzi email del contatto contiene soli e tutti i valori della lista fornita.
     * @post Gli altri campi del contatto non vengono modificati.
     */
    public void setIndirizziEmail(List<String> indirizziEmail)  {
        // Crea un nuovo ArrayList con le informazioni passate.
        this.indirizziEmail = new ArrayList<>(indirizziEmail);
    }

    /**
     * @brief Restituisce il numero massimo di numeri di telefono consentiti per contatto.
     *
     * @return Il numero massimo di numeri di telefono consentiti per contatto.
     */
    public static int getMaxNumeri(){
        return MAX_NUMERI;
    }

    /**
     * @brief Restituisce il numero massimo di indirizzi email consentiti per contatto.
     *
     * @return Il numero massimo di indirizzi email consentiti per contatto.
     */
    public static int getMaxEmail(){
        return MAX_EMAIL;
    }

     /**
     *@brief Confronta il contatto corrente con il contatto specificato secondo il seguente criterio di ordinamento
     *@details Segue un criterio di ordine alfabetico (da A a Z) per cognome e nome. 
     * L’ordine è determinato dalle seguenti operazioni: confronto del campo “Cognome” (dei contatti dotati di solo cognome o cognome e nome)  e del campo “Nome” (dei contatti privi di cognome),
     * senza priorità e distinzione alcuna dovuta ai campi considerati per il confronto. 
     * In caso di conflitto per cognomi omografi, si prosegue al confronto dei nomi dei contatti in conflitto.
     * Il criterio di ordinamento è case insensitive.
     *
     * @param[in] c contatto con cui confrontare il contatto corrente.
     *
     *@return numero minore di zero se il contatto corrente precede il contatto specificato, 0 se sono uguali, numero maggiore di zero se il contatto corrente segue il contatto specificato,  secondo il criterio descritto.
     *
     *
     */
    @Override
    public int compareTo(Contatto c) {
        // Rimuove gli spazi bianchi interni ed esterni da nome e cognome, concatena nome e cognome.
        String thisIDCompleto = (this.cognome + this.nome).replaceAll("\\s+", "");
        String otherIDCompleto = (c.cognome + c.nome).replaceAll("\\s+", "");

        // Confronta le stringhe risultanti in modo case insensitive
        return thisIDCompleto.compareToIgnoreCase(otherIDCompleto);
    }
    
    


    /**
    *@brief Fornisce una descrizione del contatto
    *
    *@details La stringa sarà formattata nel seguente modo:
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
    *@return Una stringa che  rappresenta il contatto corrente.
    */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        //Nome e Cognome
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Cognome: ").append(cognome).append("\n");

        // Lista dei numeri di telefono
        sb.append("Numeri di telefono: ");
        if (!numeriTelefono.isEmpty()) {
           sb.append(String.join(", ", numeriTelefono));
         } else {
           sb.append("Nessun numero di telefono");
        }
        sb.append("\n");

        // Lista degli indirizzi email
        sb.append("Indirizzi email: ");
        if (!indirizziEmail.isEmpty()) {
            sb.append(String.join(", ", indirizziEmail));
        } else {
            sb.append("Nessun indirizzo email");
        }
        sb.append("\n");

        return sb.toString();
    }
    
    /**
    *@brief Confronta il contatto corrente con un altro contatto per verificarne l'uguaglianza.
    * Due contatti sono definiti uguali se il nome, cognome, numeri di telefonon e indirizzi emaildi questi sono identici.
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
    public boolean equals(Object obj) {
         if (this == obj) {
            return true;  // Il contatto corrente e 'obj' sono lo stesso riferimento
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  // 'obj' è null o non è della stessa class,dunque non possono essere uguali
        }
        
        Contatto altro = (Contatto) obj;
        // Confronta il nome e cognome, considerando anche eventuali spazi interni
        // Confronta che le liste di numeri telefonici e indirizzi email contengano rispettivamente gli stessi elementi nello steesso ordinee.
        // Restituisce true se tutti i confronti terminano con successo.
         return this.cognome.equals(altro.cognome) && this.nome.equals(altro.nome) && this.numeriTelefono.equals(altro.numeriTelefono) && this.indirizziEmail.equals(altro.indirizziEmail); 
        }

    
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
            return Objects.hash(nome, cognome, numeriTelefono, indirizziEmail);
        }
}


