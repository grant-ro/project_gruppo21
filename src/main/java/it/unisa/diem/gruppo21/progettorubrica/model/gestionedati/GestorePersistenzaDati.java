package it.unisa.diem.gruppo21.progettorubrica.model.gestionedati;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Contatto;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Rubrica;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @file GestorePersistenzaDati.java
 * @brief Questo file contiene la classe che fornisce metodi per la persistenza dei dati all'interno di una rubrica.
 *
 *  
 */
public class GestorePersistenzaDati {

    private static final String nomeFileMemoria = "rubrica_dati.bin";
    
    public static String getNomeFileMemoria(){
        return nomeFileMemoria;
    }
    
/**
 * @brief Carica i contatti della rubrica da un file di memoria.
 * 
 * @param[out] Rubrica Rubrica su cui caricare i contatti letti da file di memoria.
 * @pre  nomeFileMemoria  deve essere il nome di un file preesistente localmente.
 * @pre Il file di memoria deve essere accessibile.
 * @pre Il nome del file contiene un'estensione corretta: .bin.
 * @post La rubrica contiene i contatti caricati dal file di memoria.
 *
 * @return true se i dati dal file .bin sono caricati con successo, false altrimenti
 * @throws IOException se si verifica un errore durante il caricamento;
 * @throws FileNotFoundException se il file di memoria non viene trovato.
 * @throws ClassNotFoundException se il file utilizzato per il caricamento dei dati non è nel formato corretto : .bin.
 */
     
    public static boolean carica(Rubrica rubrica) throws IOException, ClassNotFoundException {
     
    //user.home mi restituisce la directory home dell'utente corrente
    File file = new File(System.getProperty("user.home"), "rubrica_dati.bin"); // Percorso assoluto nella home directory
    
    if (!file.exists()) {
        System.out.println("Il file rubrica_dati.bin non esiste.");
        return false; // Se il file non esiste, ritorna false
    }

    try (FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         DataInputStream dis = new DataInputStream(bis)) {
        
        int numeroContatti = dis.readInt(); // Leggi il numero di contatti
        List<Contatto> contatti = new ArrayList<>(); //in contatti inserisco tutti i contatti letti dal file

        // Leggi ogni campo, creo un contatto e lo inserisco in rubrica
        for (int i = 0; i < numeroContatti; i++) {
            String nome = dis.readUTF();            // Leggi il nome
            String cognome = dis.readUTF();         // Leggi il cognome

            // Leggi i numeri di telefono
            int numTelefono = dis.readInt();
            List<String> numeriTelefono = new ArrayList<>();
            for (int j = 0; j < numTelefono; j++) {
                numeriTelefono.add(dis.readUTF()); // Leggi ciascun numero
            }

            // Leggi gli indirizzi email
            int numEmail = dis.readInt();
            List<String> indirizziEmail = new ArrayList<>();
            for (int j = 0; j < numEmail; j++) {
                indirizziEmail.add(dis.readUTF()); // Leggi ciascun indirizzo email
            }

            // Crea il contatto e aggiungilo alla lista
            Contatto contatto = new Contatto(nome, cognome, numeriTelefono, indirizziEmail);
            rubrica.inserisciContatto(contatto);
        }
        
        
       // rubrica.getContatti().addAll(contatti);
        

        System.out.println("Rubrica caricata con successo.");
        return true;

    } catch (IOException e) {
        System.err.println("Errore durante il caricamento della rubrica: " + e.getMessage());
        return false;
    }
    }
   


 /**
  * @brief Memorizza la rubrica su un file di memoria.
  * 
  * 
  * @param[in] Rubrica Rubrica da memorizzare localmente
  *        
  * @pre Il file di memoria deve essere accessibile.
  * @pre Il nome del file contiene un'estensione corretta: .bin.
  * @post Il file nomeFileMemoria  contiene la rubrica aggiornata.
  * @throws IOException se si verifica un errore durante il salvataggio.
  **/
    
    public static void salva(Rubrica rubrica) throws IOException {
    
    //user.home mi restituisce la directory home dell'utente corrente
    File file = new File(System.getProperty("user.home"), "rubrica_dati.bin"); // Percorso assoluto nella home directory
    
    try (FileOutputStream fos = new FileOutputStream(file);
         BufferedOutputStream bos = new BufferedOutputStream(fos);
         DataOutputStream dos = new DataOutputStream(bos)) {
        
        // Scrivi il numero di contatti
        List<Contatto> contatti = rubrica.getContatti();
        dos.writeInt(contatti.size());  // Scrive il numero di contatti
        
        // Scrivi ogni contatto
        for (Contatto contatto : contatti) {
            // Scrivi il nome e cognome
            dos.writeUTF(contatto.getNome());
            dos.writeUTF(contatto.getCognome());
            
            // Scrivi i numeri di telefono
            List<String> numeriTelefono = contatto.getNumeriTelefono();
            dos.writeInt(numeriTelefono.size());  // Scrive il numero di numeri di telefono
            for (String numero : numeriTelefono) {
                dos.writeUTF(numero);  // Scrive ciascun numero di telefono
            }

            // Scrivi gli indirizzi email
            List<String> email = contatto.getIndirizziEmail();
            dos.writeInt(email.size());  // Scrive il numero di email
            for (String mail : email) {
                dos.writeUTF(mail);  // Scrive ciascun indirizzo email
            }
        }
        
        System.out.println("Rubrica salvata con successo.");
    } catch (IOException e) {
        System.err.println("Errore durante il salvataggio della rubrica: " + e.getMessage());
        e.printStackTrace();
    }
}

}




