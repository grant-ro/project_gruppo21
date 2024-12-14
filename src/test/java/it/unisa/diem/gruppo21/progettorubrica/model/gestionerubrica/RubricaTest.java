
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.Contatto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Nested;

/**
 *
 * @author grant
 */
public class RubricaTest {
    private Rubrica rubrica; 
    private Contatto contatto1;
    private Contatto contatto2;
    private Contatto contatto3;
    private Contatto contatto4;
    
    public RubricaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Prima di ogni test, crea una nuova istanza di Rubrica
        rubrica = new Rubrica();
        
        
        //Prima di ogni test,crea dei contatti validi (che soddisfano le invarianti della clase contatto) 
        contatto1 = new Contatto("Marioprin", "Rossiprin",Arrays.asList("10000000"), Arrays.asList("mario.rossi@example.com"));
        contatto2 = new Contatto("PrinLuigi", "Verdi", Arrays.asList("20000000"), Arrays.asList("luigi.verdi@example.com"));
        contatto3 = new Contatto("Peach", "Princess", Arrays.asList("30000000"), Arrays.asList("peach@example.com"));
        contatto4 = new Contatto("Daisy", "principessa", Arrays.asList("40000000"), Arrays.asList("daisy@example.com"));
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    /**
     * Test of Rubrica costructor 
     */
    @Test
    public void Rubrica(){
        
        assertNotNull(rubrica.getContatti(), "La lista di contatti non dovrebbe essere null."); //invariant: Contatti non deve mai essere null: contatti != null
 
        assertTrue(rubrica.getContatti().isEmpty(), "La rubrica dovrebbe essere inizializzata vuota."); //post: La rubrica non possiede alcun contatto
        
        //Se la rubrica è vuota essa è anche ordinata->invariant: La rubrica è sempre ordinata secondo il criterio d’ordine naturale dell’oggetto contatto.
    }
    
    /*
     * Test of getContatti method, of class Rubrica.
     *
     *public void testGetContatti();
     *Il metodo getContatti è sufficientemente testato nel suo utilizzo per il testRubrica e testInserisciContatto
     */
    
    /**
     * Test of inserisciContatto method, of class Rubrica.
     */
    @Test
    public void testInserisciContattiValidi() {
        
        // Verifica che i contatti siano aggiunti correttamente->return: true se il contatto è stato inserito nella rubrica, altrimenti false.
        assertTrue(rubrica.inserisciContatto(contatto1), "Il contatto dovrebbe essere inserito correttamente.");
        assertTrue(rubrica.inserisciContatto(contatto2), "Il contatto dovrebbe essere inserito correttamente.");
        
        
        //Verifico che il contatto da inserire è stato effettivamente inserito nella rubrica->post: nuovoContatto è inserito nella rubrica.
        assertTrue(rubrica.getContatti().contains(contatto1), "La rubrica dovrebbe contenere il contatto1 inserito");
        assertTrue(rubrica.getContatti().contains(contatto2), "La rubrica dovrebbe contenere il contatto2 inserito");

        //Verifico la post: L'inserimento del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al nuovoContatto. La lista dei contatti rimane invariata ad eccezione del contatto inserito,
        //La lista contiene ambo i contatti inseriti e ha dimenzione 2, dunque il secondo inserimento non ha modificato gli altri contatti della rubrica  ha avuto solo l'effetto di inserimento desiderato.
        assertEquals(2, rubrica.getContatti().size(), "La rubrica dovrebbe contenere due contatti.");
        
        //Verifica l'invariante di ordinamento della Rubrica
        verificaOrdinamento(rubrica);
    }

    
    @Test
    public void testInserisciContattoNull() {
        // Prova a inserire un contatto null
        assertThrows(IllegalArgumentException.class, () -> {rubrica.inserisciContatto(null);},
                "Dovrebbe essere sollevata una IllegalArgumentException quando si prova ad aggiungere un contatto null.");  //throws: IllegalArgumentException se 'nuovoContatto' è null.
    }

    
    @Nested
    class RubricaPreriempitaTest{
        List<Contatto> contattiPrima;
        Contatto contatto5;
    
        @BeforeEach
        public void setUp() {
            
           
            rubrica.inserisciContatto(contatto1);
            rubrica.inserisciContatto(contatto2);
            rubrica.inserisciContatto(contatto3);
            rubrica.inserisciContatto(contatto4);
            contattiPrima=new ArrayList<>(rubrica.getContatti());
            
            //Creo un nuovo contatto che non inserisco in rubrica
            contatto5 = new Contatto("Eiffel", "Tower", Arrays.asList("50000000"), Arrays.asList("fifiy@example.com"));
            
        }
    
        /**
        * Test of modificaContatto method, of class Rubrica.
        */
        @Test
        public void testModificaContattoPresente() {
            // Verifica che il contatto sia stato modificato correttamente->return: true se il contattoSelezionato era presente nella rubrica ed è stato modificato
            assertTrue(rubrica.modificaContatto(contatto3, contatto5), "Il contatto dovrebbe essere stato modificato correttamente.");
        
            
            // Verifica che il contatto modificato sia presente nella rubrica e il contatto originale no-> post: Il contattoSelezionato è modificato in modo da essere uguale (contiene le stesse info) al contattoModificato
            assertTrue(rubrica.getContatti().contains(contatto5), "La rubrica dovrebbe contenere il contatto modificato.");
            assertFalse(rubrica.getContatti().contains(contatto3), "La rubrica non dovrebbe contenere più il contatto originale.");
        
            //Verifica la post: La modifica del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al contattoSelezionato. 
            //La lista dei contatti rimane invariata ad eccezione del contatto modificato.
            for(Contatto contatto: contattiPrima) {
                if(!contatto.equals(contatto3)){
                    assertTrue(rubrica.getContatti().contains(contatto));
                }
            }
            assertEquals(contattiPrima.size(), rubrica.getContatti().size(), "La rubrica dovrebbe contenere lo stesso numero di contatti.");
        
            // Verifica l'ordinamento dei contatti nella rubrica->invariant: La rubrica è sempre ordinata secondo il criterio d’ordine naturale dell’oggetto contatto.
            verificaOrdinamento(rubrica);
        }
        
        @Test
        public void testModificaContattoAssente() {
            // Testa il caso in cui contattoSelezionato non è presente nella rubrica
            // Verifica che il contatto non sia stato modificato->return: false se il contattoSelezionato non era presente nella rubrica.
            assertFalse(rubrica.modificaContatto(contatto5, contatto3), "Il contatto5 non è presente in rubrica, dunque nulla deve essere stato modificato.");
        
             //Verifica la post: La modifica del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al contattoSelezionato. 
            //La lista dei contatti rimane invariata ad eccezione del contatto modificato. 
            //In questo caso vuol dire che l'intera lista contatti non è stata modificata
            assertEquals(contattiPrima, rubrica.getContatti(),"La lista contatti non deve essere stat modificata poichè il contatto da modificare non era presente");
        }
        
        @Test
        public void testModificaContattoNull(){
            assertThrows(IllegalArgumentException.class, () -> {
                rubrica.modificaContatto(null, contatto1);
                }, "Doveva essere lanciata un'eccezione IllegalArgumentException per contattoSelezionato null.");

            assertThrows(IllegalArgumentException.class, () -> {
                rubrica.modificaContatto(contatto2, null);
                }, "Doveva essere lanciata un'eccezione IllegalArgumentException per contattoModificato null.");
        }

        @Test
        public void testModificaContattoConDuplicati() {
            // Aggiungi il contatto due volte alla rubrica,affinchè essa contenga duplicati di uno stesso contatto
            rubrica.inserisciContatto(contatto3);
    
            rubrica.modificaContatto(contatto3,contatto4);
            assertEquals(1, rubrica.getContatti().stream().filter(c -> c.equals(contatto3)).count(), "Doveva rimanere un duplicato del contatto.");
        }
        
        
        /**
         * Test of eliminaContatto method, of class Rubrica.
         */
        @Test
        public void testEliminaContattoPresente() {
            // Verifica che il contatto sia stato rimosso->return:true se il contatto è stato rimosso dalla rubrica
            assertTrue(rubrica.eliminaContatto(contatto3), "Il contatto esisteva nella rubrica e il metodo dovrebbe restituire true.");
            assertFalse(rubrica.getContatti().contains(contatto3), "Il contatto non dovrebbe essere più nella rubrica.");
            
            
            //Verifica la post: La rimozione del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al contattoSelezionato. 
            //La lista dei contatti rimane invariata ad eccezione del contatto rimosso.
            for(Contatto contatto: contattiPrima) {
                if(!contatto.equals(contatto3)){
                    assertTrue(rubrica.getContatti().contains(contatto));
                }
            }
            assertEquals(contattiPrima.size()-1, rubrica.getContatti().size(), "La rubrica dovrebbe contenere un contatto in meno.");
        
            // Verifica l'ordinamento dei contatti nella rubrica->invariant: La rubrica è sempre ordinata secondo il criterio d’ordine naturale dell’oggetto contatto.
            verificaOrdinamento(rubrica);
            
        }
    
        @Test
        public void testEliminaContattoNonEsistente() {
            
            // Verifica che il metodo restituisca false perchè il contatto da rimuovere non era presente->return:false se il contatto non era presente
            assertFalse(rubrica.eliminaContatto(contatto5), "Il contatto non esisteva nella rubrica e il metodo dovrebbe restituire false.");
            
            //Verifica la post: La rimozione del contatto non modifica, rimuove o aggiunge alcun altro contatto nella rubrica, oltre al contattoSelezionato. 
            //La lista dei contatti rimane invariata ad eccezione del contatto rimosso. 
            //In questo caso vuol dire che l'intera lista contatti non è stata modificata
            assertEquals(contattiPrima, rubrica.getContatti(),"La lista contatti non deve essere stat modificata poichè il contatto da eliminare non era presente");
        }
            
        @Test
        public void testEliminaContattoNull() {
             assertThrows(IllegalArgumentException.class, () -> {
                    rubrica.eliminaContatto(null);
                    }, "Doveva essere lanciata un'eccezione IllegalArgumentException per contattoSelezionato null.");
        }    
    
        @Test
        public void testEliminaContattoConDuplicati() {
            // Aggiungi il contatto due volte alla rubrica,affinchè essa contenga duplicati di uno stesso contatto
            rubrica.inserisciContatto(contatto3);
    
            rubrica.eliminaContatto(contatto3);
            assertEquals(1, rubrica.getContatti().stream().filter(c -> c.equals(contatto3)).count(), "Doveva rimanere un duplicato del contatto.");
        }
    
        
        /**
        * Test of ricercaContatti method, of class Rubrica.
        */
        @Test
        public void testRicercaContatti_StringaValida() {
            // Cerchiamo "Prin"
            String input="Prin";
            
            Rubrica risultati = rubrica.ricercaContatti(input);
        
            //Caso 1: la rubrica contiene un contatto con nome che inizia con la stringa di ricerca
            assertTrue(risultati.getContatti().contains(contatto2), "La ricerca per "+input+" dovrebbe contenere il contatto PrinLuigi Verdi.");
            
            //Caso 2: la rubrica contiene un contatto con cognome che inizia con la stringa di ricerca
            assertTrue(risultati.getContatti().contains(contatto3), "La ricerca per "+input+" dovrebbe contenere il contatto Peach Princess.");
            
            //Caso 3: la rubrica contiene un contatto con cognome/nome che inizia con la stringa di ricerca però con un case diverso-> la ricerca è case insensitive
            assertTrue(risultati.getContatti().contains(contatto4), "La ricerca per "+input+" dovrebbe contenere il contatto Daisy princess.");
            
            //Caso 4: la rubrica contiene un contatto con cognome/nome che contiene la stringa di ricerca, ma non all'inizio
            assertFalse(risultati.getContatti().contains(contatto1), "La ricerca per "+input+" non dovrebbe contenere il contatto Marioprin Marioprin.");
            
            //Verifica che la rubrica creata sia ordinata:
            verificaOrdinamento(risultati);
            
            //Verifica che la ricerca non abbia modificato la rubrica su cui è stata effettuta l'operazione
            assertEquals(contattiPrima, rubrica.getContatti(),"La lista contatti non deve essere stata modificata per effetto dell'operazione di ricerca effettuata");
        }
    
        @Test
        public void testRicercaContatti_inputNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                rubrica.ricercaContatti(null);
            }, "Dovrebbe lanciare un'IllegalArgumentException quando l'input è null.");
        }

        /**
         * Test of caricaRubrica method, of class Rubrica.
         */
        @Test
        public void testCaricaRubrica() throws Exception {
        
        }

    /**
     * Test of salvaRubrica method, of class Rubrica.
     */
    @Test
    public void testSalvaRubrica() throws Exception {
        
    }

    /**
     * Test of importaRubrica method, of class Rubrica.
     *
    @Test
    public void testImportaRubrica() throws Exception {
           }
    */

    /**
     * Test of esportaRubrica method, of class Rubrica.
     *
    @Test
    public void testEsportaRubrica() throws Exception {
    }
    */
    
    
    /**
     * Test of toString method, of class Rubrica.
     */
        @Test
        public void testToString() {
            /*Avendo inserito i seguenti contatti in rubrica:
            contatto1 = new Contatto("Marioprin", "Rossiprin",Arrays.asList("10000000"), Arrays.asList("mario.rossi@example.com"));
            contatto2 = new Contatto("PrinLuigi", "Verdi", Arrays.asList("20000000"), Arrays.asList("luigi.verdi@example.com"));
            contatto3 = new Contatto("Peach", "Princess", Arrays.asList("30000000"), Arrays.asList("peach@example.com"));
            contatto4 = new Contatto("Daisy", "principessa", Arrays.asList("40000000"), Arrays.asList("daisy@example.com"));
            */
            
            List<Contatto> contattiOrdinati = Arrays.asList(contatto1,contatto2,contatto3,contatto4);
            Collections.sort(contattiOrdinati);
            
            String expected=""; 
            // Invoca il toString  su ogni contatto per costruire la stringa attesa
            for (Contatto contatto : contattiOrdinati) {
                expected += contatto.toString()+"\n";
            }    
                
            // Verifica che la rappresentazione della rubrica sia corretta
            assertEquals(expected, rubrica.toString(), "Il metodo toString() non restituisce la stringa corretta.");
        }
    }
    
    /*
    *Verifica l'ordinamento dei contatti nella rubrica->invariant: La rubrica è sempre ordinata secondo il criterio d’ordine naturale dell’oggetto contatto.
    * 
    */
    private void verificaOrdinamento(Rubrica rubrica) {
    // Crea una copia dei contatti nella rubrica in una lista ordinabile
    List<Contatto> contattiOrdinati = new ArrayList<>(rubrica.getContatti());
    
    // Ordina la lista per confrontarla con quella originale
    Collections.sort(contattiOrdinati);
    
    // Confronta la lista ordinata con la lista nella rubrica
    assertEquals(contattiOrdinati, rubrica.getContatti(), "I contatti nella rubrica non sono ordinati correttamente.");
    }
}

