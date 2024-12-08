package it.unisa.diem.gruppo21.progettorubrica.model.gestionedati;
        
 /**
 *
 * @author granturco-roberta
 */

import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Rubrica;
import java.io.IOException;

/**
 * @file OperatoreFile.java
 * @brief Questo file contiene la classe che fornisce metodi per la gestione della lettura e scrittura di file CSV.
 */
public class OperatoreFile {

 /**
 * @brief Importa in rubrica contatti da un file locale.
 * 
 * @param[in] nomeFile Nome del file da cui importare i contatti in rubrica.
 * @param[inout] Rubrica su cui importare i contatti.
 * 
 * @pre nomeFile deve essere il nome di un file preesistente localmente.
 * @pre Il file di importazione deve essere accessibile.
 * @pre Il nome del file contiene un'estensione corretta: .csv  
 * 
 * @post I contatti dal file CSV sono aggiunti alla rubrica esistente.
 * @return true se tutti contatti sono stati inseriti con successo, altrimenti false.
 * @throws IOException se si verifica un errore durante l’importazione.
 */


    public boolean leggi(String nomeFile, Rubrica rubrica)  throws IOException {

    }

 /**
 * @brief Esporta la rubrica su un file locale.
 * 
 * @param[in] nomeFile Nome del file su cui esportare la rubrica.
 * @param[in] Rubrica da cui esportare i contatti.
 * 
 * @pre nomeFile deve essere un nome valido per la creazione di un file.
 * @pre Il nome del file contiene un'estensione corretta: .csv.
 * @post Viene creato un nuovo file CSV, di nome nomeFile.
 * @post  Il file CSV creato  contiene la rubrica aggiornata.
 *
 * @throws IOException se si verifica un errore durante l’esportazione.
 */
    public void scrivi(String nomeFile, Rubrica rubrica)  throws IOException{

    }
}


