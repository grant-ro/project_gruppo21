package it.unisa.diem.gruppo21.progettorubrica.model.gestionedati;

/**
 *
 * @author granturco-roberta
 */

import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Rubrica;
import java.io.IOException;

/**
 * @file GestorePersistenzaDati.java
 * @brief Questo file contiene la classe che fornisce metodi per la persistenza dei dati all'interno di una rubrica.
 *
 *  
 */
public class GestorePersistenzaDati {

    
/**
 * @brief Carica i contatti della rubrica da un file di memoria.
 * 
 * @param[in] nomeFileMemoria Il nome del file da cui caricare la rubrica.
 * @param[inout] Rubrica Rubrica su cui caricare i contatti letti da file di memoria.
 * @pre  nomeFileMemoria  deve essere il nome di un file preesistente localmente.
 * @pre Il file di memoria deve essere accessibile.
 * @pre Il nome del file contiene un'estensione corretta: .bin.
 * @post La rubrica contiene i contatti caricati dal file di memoria.
 *
 * @throws IOException se si verifica un errore durante il caricamento
 */
     
    public static void carica(String nomeFileMemoria, Rubrica rubrica) throws IOException {

    }
 /**
  * @brief Memorizza la rubrica su un file di memoria.
  * 
  *  
  * @param[in] nomeFileMemoria Nome del file di memoria locale in cui salvare la rubrica.
  * @param[in] Rubrica Rubrica da memorizzare localmente
  *        
  * @pre nomeFileMemoria  deve essere il nome di un file preesistente localmente.
  * @pre Il file di memoria deve essere accessibile.
  * @pre Il nome del file contiene un'estensione corretta: .bin.
  * @post Il file nomeFileMemoria  contiene la rubrica aggiornata.
  * @throws IOException se si verifica un errore durante il salvataggio.
  **/
    
    public static void salva( String nomeFileMemoria, Rubrica rubrica)  throws IOException {

    }
}

