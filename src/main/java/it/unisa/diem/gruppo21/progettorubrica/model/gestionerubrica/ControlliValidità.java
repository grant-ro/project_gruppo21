package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */

/**
 * @file ControlliValidità.java
 * @brief Questo file contiene la classe che fornisce metodi di utilità per la validazione di dati forniti.
 * @class ControlliValidità
 */
public class ControlliValidità {

    /**
     * @brief Verifica che la stringa fornita sia un numero di telefono valido.
     *
     * @param[in] numeroTelefonico La stringa da validare.
     * @return true se numeroTelefonico è una stringa che soddisfa tutta le seguenti condizioni, altrimenti false: 
     *             -  numeroTelefonico è una stringa non vuota e non composta da soli spazi bianchi.
     *             -  numeroTelefonico è una stringa numerica (composta solo da cifre).
     *             -  numeroTelefonico è una stringa composta da 10 cifre.
     *
     * @pre La stringa numero non deve essere null.
     * 
     * @throws IllegalArgumentException se numeroTelefonico è null
     */
    public static boolean controlloNumeroTelefonico(String numeroTelefonico) {
        if (numeroTelefonico== null) {
            throw new IllegalArgumentException("Il parametro numeroTelefoncio non deve essere null.");
        }
        String trimmedNumero=numeroTelefonico.trim();
        return !trimmedNumero.isEmpty()&& trimmedNumero.matches("\\d{10}");
    }
    
    /**
     * @brief Verifica che la stringa fornita sia un indirizzo email valido.
     *
     * @param[in] indirizzoEmail La stringa da validare.
     * @return true se indirizzoEmail è una stringa che soddisfa tutta le seguenti condizioni, altrimenti false: 
     *             -  indirizzoEmail è una stringa non vuota e non composta da soli spazi bianchi.
     *
     * @pre La stringa indirizzoEmail non deve essere null.
     */
    public static boolean controlloIndirizzoEmail(String indirizzoEmail) {
        if (indirizzoEmail== null) {
            throw new IllegalArgumentException("Il parametro indirizzoEmail non deve essere null.");
        }
        return !indirizzoEmail.trim().isEmpty();
    }
    

    /**
     * @brief Verifica che le stringhe fornite non siano entrambe vuote o composte da soli spazi bianchi.
     *
     * @param[in] a prima stringa per il controllo.
     * @param[in] b seconda stringa per il controllo.
     * @return true se almeno una delle stringhe non è vuota o composta da soli spazi vuoti, altrimenti false.
     *
     * @pre Le stringhe a e b non devono essere null.
     * 
     * @throws IllegalArgumentException se a==null ||b==null
     */
    public static boolean controlloRiempimento(String a, String b) {
        if (a== null||b==null) {
            throw new IllegalArgumentException("Nessun parametro per controlloRiempimento deve essere null.");
        }
        
         return (!a.trim().isEmpty() || !b.trim().isEmpty());
     }
}
     
    
