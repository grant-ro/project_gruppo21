/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.model.gestionedati;

import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Contatto;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Rubrica;
import static it.unisa.diem.gruppo21.progettorubrica.model.gestionedati.GestorePersistenzaDati.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

/**
 *
 * @author granturco-roberta
 */
public class GestorePersistenzaDatiTest {
    File file; 
    Rubrica rubrica;
    List<Contatto> contattiPrima;
    
    @BeforeEach
    void setUp() {
        // Assicura che il file non esista prima di iniziare il test
            file = new File(getNomeFileMemoria());
            if (file.exists()) {
            file.delete(); // Elimina il file se esiste
            }
        
        // Prima di ogni test, crea una nuova istanza di Rubrica
         rubrica = new Rubrica();
        
        //Prima di ogni test,crea dei contatti validi (che soddisfano le invarianti della clase contatto) 
        Contatto contatto1 = new Contatto("Marioprin", "Rossiprin",Arrays.asList("1000000000"), Arrays.asList("mario.rossi@example.com"));
        Contatto contatto2 = new Contatto("PrinLuigi", "Verdi", Arrays.asList("2000000000"), Arrays.asList("luigi.verdi@example.com"));
        Contatto contatto3 = new Contatto("Peach", "Princess", Arrays.asList("3000000000"), Arrays.asList("peach@example.com"));
        Contatto contatto4 = new Contatto("Daisy", "principessa", Arrays.asList("4000000000"), Arrays.asList("daisy@example.com"));
        
        rubrica.inserisciContatto(contatto1);
        rubrica.inserisciContatto(contatto2);
        rubrica.inserisciContatto(contatto3);
        rubrica.inserisciContatto(contatto4);
        contattiPrima=new ArrayList<>(rubrica.getContatti());
    }    
        
    @AfterEach
    void tearDown() {
        // Elimina il file di persistenza dopo il test
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testPrimoAvvioRubrica() throws IOException, ClassNotFoundException {
        // Carica la rubrica dal file
        assertFalse(carica(rubrica), "La rubrica non dovrebbe essere caricata, poichè  non esiste già un file di persistenza dati.");
        }
            
    @Test
    void salvaECaricaTest() throws IOException, ClassNotFoundException {
        // Salva la rubrica nel file
        salva(rubrica);
        
        // Verifica che la rubrica non venga modificata per effetto dell'operazione di salvataggio
        List<Contatto> contattiDopo = rubrica.getContatti();
        assertEquals(contattiPrima, contattiDopo, "La rubrica dovrebbe contenere esattamente gli stessi contatti che posseedeva prima del salvataggio.");
    
        // Verifica che il file esista dopo il salvataggio
        assertTrue(file.exists(), "Il file dovrebbe esistere dopo il salvataggio.");

        // Crea una nuova rubrica per caricare i contatti dal file
        Rubrica rubricaCaricata = new Rubrica();
        
        // Carica la rubrica dal file
        assertTrue(carica(rubricaCaricata), "La rubrica dovrebbe essere caricata correttamente, poichè esiste già un file di persistenza dati.");

        // Verifica che la rubrica caricata contenga gli stessi contatti
        List<Contatto> contattiCaricati = rubricaCaricata.getContatti();
        assertEquals(contattiPrima, contattiCaricati);
    }
    
    @Test
    void getNomeFileMemoriaTest() {
        assertEquals(System.getProperty("user.home")+"/rubrica_dati.bin", getNomeFileMemoria());
    }
}
    

