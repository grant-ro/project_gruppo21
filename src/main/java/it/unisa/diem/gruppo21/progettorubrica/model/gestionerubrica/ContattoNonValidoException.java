/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

/**
 *
 * @author granturco-roberta
 */

/**@file ContattoNonValidoException
 * @brief Questo file contiene la definizione di un'eccezione personalizzata controllata per segnalare errori di validità nei contatti.
 * @class ContattoNonValidoException
 * 
 */


public class ContattoNonValidoException extends Exception{
  
    /**
     * @brief Costruttore di default
     * 
     * Questo costruttore crea una nuova eccezione senza un messaggio specifico.
     */
    public ContattoNonValidoException() {
        super();
    }

     /**
     * @brief Costruttore con messaggio
     * 
     * Questo costruttore permette di passare un messaggio specifico per indicare il motivo dell'errore legato al contatto non valido.
     * 
     * @param[in] message Il messaggio di errore che descrive il motivo dell'eccezione.
     * 
     */
    public ContattoNonValidoException(String message) {
        super(message);
    }
}
