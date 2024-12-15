/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.model.gestionedati;

        
 /**
 *
 * @author  gruppo21
 */

import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @file OperatoreFile.java
 * @brief Questo file contiene la classe che fornisce metodi per la gestione della lettura e scrittura di file CSV.
 */
public class OperatoreFile {

        
    public OperatoreFile(){
    
    }
        
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

    public Rubrica leggi(String nomeFile) throws IOException {
        
    Rubrica rubrica = new Rubrica();
    
    // Controlla se il file esiste prima di procedere
        File file = new File(nomeFile);
        if (!file.exists()) {
            throw new FileNotFoundException("Il file " + nomeFile + " non esiste.");
        }
        
    try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
        String line;

        // Salta la prima riga (intestazione del CSV)
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");

            // Sostituisco eventuali null con stringhe vuote
            for (int i = 0; i < data.length; i++) {
                if (data[i] == null) {
                    data[i] = ""; // Sostituisce null con stringa vuota
                } else {
                    data[i] = data[i].trim(); // Rimuove spazi bianchi
                }
            }

            

            Contatto c = new Contatto();
            
            // Aggiungi il controllo per nome e cognome usando il metodo controlloRiempimento
            if (ControlliValidità.controlloRiempimento(data.length > 0 ? data[0] : "", data.length > 1 ? data[1] : "")) {
                c.setNome(data.length > 0 ? data[0] : "");
                c.setCognome(data.length > 1 ? data[1] : "");
            } else {
                // Se nome e cognome sono entrambi vuoti vado al prossimo contatto senza inserirlo in rubrica
                continue; // Salta questa riga del CSV e passa al prossimo contatto
            }
            
            

            ArrayList<String> numeriTelefono = new ArrayList<>();
            ArrayList<String> indirizziEmail = new ArrayList<>();

            // Validazione numeri di telefono
            for (int i = 2; i < 5; i++) {
                if (data.length > i) {
                    String telefono = data[i];
                    if (ControlliValidità.controlloNumeroTelefonico(telefono)) {
                        numeriTelefono.add(telefono);
                    }
                }
            }

            // Validazione email
            for (int i = 5; i < 8; i++) {
                if (data.length > i) {
                    String email = data[i];
                    if (!ControlliValidità.controlloIndirizzoEmail(email)) {
                        indirizziEmail.add(email);
                    }
                }
            }

            c.setNumeriTelefono(numeriTelefono);
            c.setIndirizziEmail(indirizziEmail);

            rubrica.inserisciContatto(c);
        }
    }

    return rubrica;
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
        if (!nomeFile.endsWith(".csv")) {
             throw new IllegalArgumentException("Il file deve avere estensione .csv");
        }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))){
        writer.write("NOME;COGNOME;NUMERO TELEFONO_1;NUMERO TELEFONO_2;NUMERO TELEFONO_3;INDIRIZZO EMAIL_1;INDIRIZZO EMAIL_2;INDIRIZZO EMAIL_3");
        writer.newLine();
        // Itera su tutti i contatti della rubrica per ottenere ogni contatto
        for (Contatto contatto : rubrica.getContatti()) {
            // Estraggo i dati del contatto e li salvo in nome, cognome ecc
            String nome = contatto.getNome();
            String cognome = contatto.getCognome();
            List<String> numeriTelefono = contatto.getNumeriTelefono();
            String telefono1 = numeriTelefono.size() > 0 ? numeriTelefono.get(0) : "";
            String telefono2 = numeriTelefono.size() > 1 ? numeriTelefono.get(1) : "";
            String telefono3 = numeriTelefono.size() > 2 ? numeriTelefono.get(2) : "";
            List<String> indirizziEmail = contatto.getIndirizziEmail();
            String email1 = indirizziEmail.size() > 0 ? indirizziEmail.get(0) : "";
            String email2 = indirizziEmail.size() > 1 ? indirizziEmail.get(1) : "";
            String email3 = indirizziEmail.size() > 2 ? indirizziEmail.get(2) : "";

            // Scrivo i dati del contatto come una riga CSV cioè separati dal ;
            String line =String.join(";", nome, cognome, telefono1, telefono2, telefono3, email1, email2, email3);
            writer.write(line);
            writer.newLine();
            
            
        }
    } catch (IOException e) {
        throw new IOException("Errore durante la scrittura del file: " + nomeFile, e);
    } 
}   
}



