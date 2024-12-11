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
     * @brief Verifica che la stringa fornita sia una stringa numerica.
     *
     * @param[in] numero La stringa da validare.
     * @return true se numero è una stringa numerica, altrimenti false.
     *
     * @pre La stringa numero non deve essere null.
     */
    public static boolean controlloNumero(String numero) {
        return numero != null && numero.matches("\\d+");
    }

    /**
     * @brief Verifica che le stringhe fornite non siano entrambe vuote o composte da soli spazi bianchi.
     *
     * @param[in] a prima stringa per il controllo.
     * @param[in] b seconda stringa per il controllo.
     * @return true se almeno una delle stringhe non è vuota o composta da soli spazi vuoti, altrimenti false.
     *
     * @pre Le stringhe a e b non devono essere null.
     */
     public static boolean controlloRiempimento(String a, String b) {
         return (a != null && !a.trim().isEmpty()) || (b != null && !b.trim().isEmpty());
     }
}
