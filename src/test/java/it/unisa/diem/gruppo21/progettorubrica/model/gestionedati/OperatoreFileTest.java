import it.unisa.diem.gruppo21.progettorubrica.model.gestionedati.OperatoreFile;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Contatto;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Rubrica;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OperatoreFileTest {

    private OperatoreFile operatoreFile;
    private String fileTest = "test_rubrica.csv";
    private Rubrica rubrica;

    // Metodo di setup che viene eseguito prima di ogni test
    @BeforeEach
    public void setup() {
        operatoreFile = new OperatoreFile();
        rubrica = new Rubrica();
    }

    // Metodo di teardown che viene eseguito dopo ogni test
    @AfterEach
    public void cleanup() {
        // Rimuove il file di test dopo ogni test
        File file = new File("src/test/resources/test_rubrica.csv");

        if (file.exists()) {
            file.delete();
        }
    }

    // Test per la lettura da file CSV
    @Test
    public void testLeggiFileCsv() throws IOException {
        // Preparare un file di test con alcuni contatti    
        rubrica.inserisciContatto(new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario@example.com")));
        rubrica.inserisciContatto(new Contatto("Giulia", "Bianchi", Arrays.asList("0987654321"), Arrays.asList("giulia@example.com")));
        rubrica.inserisciContatto(new Contatto("Luca", "Verdi", Arrays.asList("5555555550"), Arrays.asList("luca@example.com")));
        
        // Verifica che il file abbia l'estensione .csv prima di scrivere
        if (!fileTest.endsWith(".csv")) {
            fileTest += ".csv";
        }
        
        operatoreFile.scrivi(fileTest, rubrica);
       
        // Ora prova a leggere dal file CSV
        Rubrica rubricaImportata = operatoreFile.leggi(fileTest);

        // Verifica che i contatti siano stati letti correttamente
        assertNotNull(rubricaImportata);  //verifica che la rubrica non sia null
        assertFalse(rubricaImportata.getContatti().isEmpty()); //verifico che la rubrica non sia vuota, che contiene almeno un contatto
        assertEquals(3, rubricaImportata.getContatti().size()); //verifico che la rubrica ha esattamente tre contatto (quelli che ho inserito nel file di test)
 
        // Verifica che i contatti desiderati siano presenti, indipendentemente dall'ordine

        // Contatto 1
        Contatto contatto1 = new Contatto("Mario", "Rossi", Arrays.asList("1234567890"), Arrays.asList("mario@example.com"));
        // Contatto 2
        Contatto contatto2 = new Contatto("Giulia", "Bianchi", Arrays.asList("0987654321"), Arrays.asList("giulia@example.com"));
        // Contatto 3
        Contatto contatto3 = new Contatto("Luca", "Verdi", Arrays.asList("5555555550"), Arrays.asList("luca@example.com"));
        
        assertTrue(rubricaImportata.getContatti().contains(contatto1));
        assertTrue(rubricaImportata.getContatti().contains(contatto2));
        assertTrue(rubricaImportata.getContatti().contains(contatto3));

    }

    
    // Test per la gestione di un file CSV vuoto
    @Test
    public void testLeggiFileCsvVuoto() throws IOException {
        
        File file = new File(fileTest);
        
        // Se il file già esiste, viene eliminato e successivamente creato un file vuoto
        if(file.exists())
            file.delete();
        
        file.createNewFile();

        // Prova a leggere dal file CSV vuoto
        Rubrica rubricaImportata = operatoreFile.leggi(fileTest);
        
        // Verifica che la rubrica letta sia vuota
        assertNotNull(rubricaImportata);
        assertTrue(rubricaImportata.getContatti().isEmpty()); //verifico che la rubrica sia vuota dato che il file da cui ho letto è vuoto
    }

    // Test per la scrittura su file CSV
    @Test
    public void testScriviFileCsv() throws IOException {
        // Aggiungi alcuni contatti alla rubrica
        rubrica.inserisciContatto(new Contatto("Giovanni", "Bianchi", Arrays.asList("0987654321"), Arrays.asList("giovanni@example.com")));
        rubrica.inserisciContatto(new Contatto("Lucia", "Verdi",Arrays.asList("1122334455"), Arrays.asList("lucia@example.com")));

        // Verifica che il file abbia estensione .csv
        if (!fileTest.endsWith(".csv")) {
            fileTest += ".csv"; // Assicurati che il file abbia estensione .csv
        }

        // Scrivi la rubrica su un file
        operatoreFile.scrivi(fileTest, rubrica);

        // Verifica che il file sia stato creato
        File file = new File(fileTest);
        assertTrue(file.exists());

        // Ora prova a leggere il file CSV
        Rubrica rubricaImportata = operatoreFile.leggi(fileTest);

        // Verifica che i contatti siano correttamente letti
        assertNotNull(rubricaImportata); //// Verifica che la rubrica importata non sia null
        assertEquals(2, rubricaImportata.getContatti().size()); //// Verifica che il numero di contatti letti dal file sia 2, cioè quelli che ho inserito in rubrica

        Contatto contatto1 = rubricaImportata.getContatti().get(0);
        assertEquals("Giovanni", contatto1.getNome());
        assertEquals("Bianchi", contatto1.getCognome());
        assertTrue(contatto1.getNumeriTelefono().contains("0987654321"));
        assertTrue(contatto1.getIndirizziEmail().contains("giovanni@example.com"));

        Contatto contatto2 = rubricaImportata.getContatti().get(1);
        assertEquals("Lucia", contatto2.getNome());
        assertEquals("Verdi", contatto2.getCognome());
        assertTrue(contatto2.getNumeriTelefono().contains("1122334455"));
        assertTrue(contatto2.getIndirizziEmail().contains("lucia@example.com"));
    }

    
    // Test per la gestione di un file CSV con formato errato
    @Test
    public void testLeggiFileCsvFormatoErrato() throws IOException {
        // Scrivi un file CSV con formato errato
        try (PrintWriter writer = new PrintWriter(fileTest)) {
            writer.println("NOME;COGNOME;NUMERO TELEFONO_1;NUMERO TELEFONO_2;NUMERO TELEFONO_3;INDIRIZZO EMAIL_1;INDIRIZZO EMAIL_2;INDIRIZZO EMAIL_3");
            // Scrive un contatto con dati corretti
            writer.println("Mario;Rossi;12345;12345;;mario@example.com;;");
            // Scrive un contatto con un campo mancante (email vuota)
            writer.println("Giovanni;;123456;;");
        }

        // Prova a leggere dal file CSV con formato errato
        Rubrica rubricaImportata = operatoreFile.leggi(fileTest);

        // Verifica che la rubrica sia stata importata correttamente nonostante il formato errato
        assertNotNull(rubricaImportata);
        assertEquals(2, rubricaImportata.getContatti().size());
    }
}
