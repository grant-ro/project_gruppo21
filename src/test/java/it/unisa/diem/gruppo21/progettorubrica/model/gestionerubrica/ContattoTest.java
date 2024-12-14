/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;

/**
 *
 * @author grant
 */
public class ContattoTest {
    
    Contatto contatto;
    List<String> numeriTelefono; 
    List<String> indirizziEmail;
    String nome;
    String cognome;
    
    public ContattoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    

    
    @BeforeEach
    public void setUp() {
              // Crea una lista di numeri di telefono ritenuti validi secondo le precondizioni del metodo:
        /*pre: La lista numeriTelefono non deve essere null: numeriTelefono != null
         *pre: Ogni numero di telefono deve essere una stringa non null, non vuota e non composta da soli spazi bianchi
         *pre: Ogni numero di telefono deve essere numerico (composto solo da cifre) e composto da sole 10 cifre.
         *pre: La lista numeriTelefono non deve superare il numero massimo di numeri consentiti: numeriTelefono.size() ≤ maxNumeri
         */
        numeriTelefono = Arrays.asList("1234567890", "0987654321");
        
        // Crea una lista di numeri di indirizzi email ritenuti validi secondo le precondizioni del metodo:
        /*
         *pre: La lista indirizziEmail non deve essere null: indirizziEmail != null
         *pre: Ogni indirizzo email deve essere una stringa non null,non vuota e non composta da soli spazi bianchi
         *pre: La lista indirizziEmail non deve superare il numero massimo di indirizzi consentiti: indirizziEmail.size() ≤ maxEmail
        */
        indirizziEmail = Arrays.asList("test1@example.com","test2@example.com");

        // Crea un oggetto Contatto, inserendo nome e cognome che rispettano le precondizioni del metodo:
        /*pre: Il nome non deve essere null: nome != null
         *pre:Il cognome non deve essere null: cognome != null
         *pre: Il nome o il cognome non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
        */
        nome="Mario";
        cognome="Rossi";
        
        contatto = new Contatto(nome, cognome, numeriTelefono, indirizziEmail);
    }
    
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of constructo method of class Contatto.
     * 
     */
    @Test
    public void testContattoConstructor() {
     
        // Verifica che il contatto è stato crato con i dati forniti->post: Il contatto viene creato con nome, cognome, numeriTelefono e indirizziEmail specificati.
        assertEquals(nome, contatto.getNome());
        assertEquals(cognome, contatto.getCognome());
        assertEquals(numeriTelefono, contatto.getNumeriTelefono()); 
        assertEquals(indirizziEmail, contatto.getIndirizziEmail());
    }
    
        
    /**
     * Test of getter methods:
     * i metodi get nome,cognome,indirizziEmail,numeriTelefonici, sono sufficientemente testati nel loro utilizzo per il testRubrica e rispettivi setter test
     */    
    
    /*
     * Test of getNome method, of class Contatto.
     *
     *@Test
     *public void testGetNome() {
     *}
    */
    
    /*
     * Test of getCognome method, of class Contatto.
     *
     *@Test
     *public void testGetCognome() {
     *}
    */
    
    /*
     * Test of getNumeriTelefono method, of class Contatto.
     *
     *@Test
     *public void testGetNumeriTelefono() {
     *}
    */
    
    /*
     * Test of getIndirizziEmail method, of class Contatto.
     *
     *@Test
     *public void testGetIndirizziEmail() {
     *}
    */
    
     
    
    /**
     * Test of setNome method, of class Contatto.
     */
    @Test
    public void testSetNome() {
        /*Invoco setNome fornendo un nome che soddisfa i prerequisiti del metodo:
        * pre: 'nome' non deve essere null: nome != null
        * pre:  nome fornito e cognome corrente del contatto non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
        */ 
        contatto.setNome("Luigi");
        
        // Verifica che il nome venga correttamente aggiornato: post Il nome del contatto è aggiornato con il valore fornito
        assertEquals("Luigi", contatto.getNome());
        
        // Verifica che i restanti campi non siano stati modificati->post: Gli altri campi del contatto non vengono modificati.
        assertEquals(cognome, contatto.getCognome());
        assertEquals(numeriTelefono, contatto.getNumeriTelefono());
        assertEquals(indirizziEmail, contatto.getIndirizziEmail());
    }

    
    
    /**
     * Test of setCognome method, of class Contatto.
     */
    @Test
    public void testSetCognome() {
        /*Invoco setCognome fornendo un nome che soddisfa i prerequisiti del metodo:
        * pre: 'cognome' non deve essere null: cognome != null
        * pre:  nome corrente del contatto e cognome fornito non devono essere contemporaneamente vuoti o composti solo da spazi bianchi.
        */
        contatto.setCognome("Verdi");
        
        // Verifica che il cognome venga correttamente aggiornato-> post: Il cognome del contatto è aggiornato con il valore fornito.
        assertEquals("Verdi", contatto.getCognome());
        
        // Verifica che i restanti campi non siano stati modificati->post: Gli altri campi del contatto non vengono modificati.
        assertEquals(nome, contatto.getNome());
        assertEquals(numeriTelefono, contatto.getNumeriTelefono());
        assertEquals(indirizziEmail, contatto.getIndirizziEmail());
    }

   
    
    /**
     * Test of setNumeriTelefono method, of class Contatto.
     */
    @Test
    public void testSetNumeriTelefono() {
        /*Invoco setNumeriTelefono fornendo una lista che soddisfa i prerequisiti del metodo:
        *pre: La lista numeriTelefono non deve essere null: numeriTelefono != null
         *pre: Ogni numero di telefono deve essere una stringa non null, non vuota e non composta da soli spazi bianchi
         *pre: Ogni numero di telefono deve essere numerico (composto solo da cifre) e composto da sole 10 cifre.
         *pre: La lista numeriTelefono non deve superare il numero massimo di numeri consentiti: numeriTelefono.size() ≤ maxNumeri
         */
        List<String> nuoviNumeri = Arrays.asList("1122334455", "5566778899");
        contatto.setNumeriTelefono(nuoviNumeri);
        
        // Verifica che i numeri siano stati correttamente aggiornati->post La lista dei numeri di telefono del contatto contiene soli e tutti i valori della lista fornita.
        assertEquals(nuoviNumeri, contatto.getNumeriTelefono());
        
        // Verifica che i restanti campi non siano stati modificati->post: Gli altri campi del contatto non vengono modificati.
        assertEquals(nome, contatto.getNome());
        assertEquals(cognome, contatto.getCognome());
        assertEquals(indirizziEmail, contatto.getIndirizziEmail());

    }

    

    /**
     * Test of setIndirizziEmail method, of class Contatto.
     */
    @Test
    public void testSetIndirizziEmail() {
        /*Invoco setIndirizziEmail fornendo una lista che soddisfa i prerequisiti del metodo:
         *pre: La lista indirizziEmail non deve essere null: indirizziEmail != null
         *pre: Ogni indirizzo email deve essere una stringa non null,non vuota e non composta da soli spazi bianchi
         *pre: La lista indirizziEmail non deve superare il numero massimo di indirizzi consentiti: indirizziEmail.size() ≤ maxEmail
        */
        List<String> nuoviIndirizzi = Arrays.asList("TEST1@EXAMPLE.com","TEST2@EXAMPLE.com");
        contatto.setIndirizziEmail(nuoviIndirizzi);
        
        // Verifica che gli indirizzi email siano stati correttamente aggiornati-> post La lista degli indirizzi email del contatto contiene soli e tutti i valori della lista fornita.
        assertEquals(nuoviIndirizzi, contatto.getIndirizziEmail());
        
        // Verifica che i restanti campi non siano stati modificati->post: Gli altri campi del contatto non vengono modificati.
        assertEquals(nome, contatto.getNome());
        assertEquals(cognome, contatto.getCognome());
        assertEquals(numeriTelefono, contatto.getNumeriTelefono());
    }
        
    

    /**
     * Test of getMaxNumeri method, of class Contatto.
     */
    @Test
    public void testGetMaxNumeri() {
        // Verifica che il valore restituito dal getter sia quello atteso
        assertEquals(3, Contatto.getMaxNumeri());
    }

    /**
     * Test of getMaxEmail method, of class Contatto.
     */
    @Test
    public void testGetMaxEmail() {
        // Verifica che il valore restituito dal getter sia quello atteso
        assertEquals(3, Contatto.getMaxEmail());
    }
    
    /**
     * Test of toString method, of class Contatto.
     */
    @Test
    public void testToString() {
        String expected = "Nome: Mario\nCognome: Rossi\nNumeri di telefono: 1234567890, 0987654321\nIndirizzi email: test1@example.com, test2@example.com\n";
        assertEquals(expected, contatto.toString());
        
    }
    
    @Test
    public void testToStringConListeVuote() {
        List<String> numeriTelefonoVuoti = new ArrayList<>();
        List<String> indirizziEmailVuoti = new ArrayList<>();
    
        contatto.setIndirizziEmail(indirizziEmailVuoti);
        contatto.setNumeriTelefono(numeriTelefonoVuoti);
        
        String expected = "Nome: Mario\nCognome: Rossi\nNumeri di telefono: Nessun numero di telefono\nIndirizzi email: Nessun indirizzo email\n";
        assertEquals(expected, contatto.toString());
        
        
    }
    
    
    @Nested
        class RelazioniContattiTest{
        Contatto contattoUguale;
        Contatto contattoDiverso;
        
        @BeforeEach
        public void setUp() {
            contattoUguale = new Contatto(nome,cognome,numeriTelefono, indirizziEmail); //Crea un contatto identico all'oggetto contatto già creato
            contattoDiverso = new Contatto("Luigi","Verdi", Arrays.asList("1111111111"), Arrays.asList("luigi@example.com"));//Crea un oggetto diverso dall'oggetto contatto
            
        }
            
        /**
         * Test of equals method, of class Contatto.
         */
        @Test
        public void testEquals() {
            //Caso 1: contatti con stesso nome,cognome, stessa lista di numeri telefonici e di indirizzi email
            assertTrue(contatto.equals(contattoUguale), "I contatti con stessi attributi dovrebbero essere uguali.");
        
            //Caso 2: contatti con attributi diversi
            assertFalse(contatto.equals(contattoDiverso),  "I contatti con diversi attributi non dovrebbero essere uguali."); 
        }

        /** 
        * Test of hashCode method, of class Contatto.
        */
        @Test
        public void testHashCode() {
            //Caso 1: contatti con stesso nome,cognome, stessa lista di numeri telefonici e di indirizzi email
            assertEquals(contatto.hashCode(), contattoUguale.hashCode(), "I contatti con attributi uguali dovrebbero avere codici hash uguali.");
        
            //Caso 2: contatti con attributi diversi
            assertNotEquals(contatto.hashCode(), contattoDiverso.hashCode(), "I contatti con attributi diversi dovrebbero avere codici hash diversi.");
        }
        
         /**
         * Test of compareTo method, of class Contatto.
         */
        @Test 
        public void testCompareTo(){
            
            //Caso 1: contatti con nome e cognome uguali
            assertTrue(contatto.compareTo(contattoUguale) == 0, "Il metodo deve restituire 0 per due contatti con stesso nome e cognome ");
            //->l'operazione di confronto è riflessiva
       
            //Caso 2: contatti con nome e cognome diversi
            assertTrue(contatto.compareTo(contattoDiverso) <0, "Il contatto:" +contatto.getCognome()+contatto.getNome()+"deve precedere il contatto"+contattoDiverso.getCognome()+contattoDiverso.getNome());
            assertTrue(contattoDiverso.compareTo(contatto) >0, "Il contatto:" +contatto.getCognome()+contatto.getNome()+"deve seguire il contatto"+contattoDiverso.getCognome()+contattoDiverso.getNome());
            //->l'operazione di confronto è antisimmetrica
            
            //Caso 3:contatti con stesso cognome
            Contatto contattoAntecedente = new Contatto("Antonio",cognome,numeriTelefono, indirizziEmail);
            assertTrue(contattoAntecedente.compareTo(contatto) <0, "Il contatto:" +contattoAntecedente.getCognome()+contattoAntecedente.getNome()+"deve precedere il contatto"+contatto.getCognome()+contatto.getNome());
            //-> il confronto tra cognomi è seguito dal confronto per nome
            
            //Caso 4: contatto senza cognome
            Contatto contattoSoloNome = new Contatto("Tiziano","   ",numeriTelefono, indirizziEmail);
            assertTrue(contattoSoloNome.compareTo(contatto) >0, "Il contatto:" +contattoSoloNome.getCognome()+contattoSoloNome.getNome()+"deve seguire il contatto"+contatto.getCognome()+contatto.getNome());
            assertTrue(contattoSoloNome.compareTo(contattoDiverso) <0, "Il contatto:" +contattoSoloNome.getCognome()+contattoSoloNome.getNome()+"deve precedere il contatto"+contatto.getCognome()+contatto.getNome());
            //->Il confronto non è influenzato dal fatto che un contatto abbia il cognome vuoto, questo può infatti:
            //seguire un contatto con cognome:Tiziano segue Rossi Mario
            //antecedere un contatto con cognome:Tiziano antecede Verdi Luigi
            
            
            //Caso 5: contatto con spazi interni/esterni nei suoi campi:
            Contatto contattoSimile = new Contatto("Ma Rio","Ro Ssi",numeriTelefono, indirizziEmail);
            assertTrue(contatto.compareTo(contattoSimile) == 0, "Il metodo deve restituire 0 per due contatti che differiscono per spazi interni in nome e cognome");
            contattoSimile = new Contatto("  Mario "," Rossi",numeriTelefono, indirizziEmail);
            assertTrue(contatto.compareTo(contattoSimile) == 0, "Il metodo deve restituire 0 per due contatti che differiscono per spazi iniziali/finali in nome e cognome");
            //-> il confronto non tiene in considerazione gli spazi
            
            //Caso 6: verifica confronto case insensitive
            contattoSimile = new Contatto("  MARIO "," rossi",numeriTelefono, indirizziEmail);
            assertTrue(contatto.compareTo(contattoSimile) == 0, "Il metodo deve restituire 0 per due contatti che differiscono per il case dei suoi campi");
        }

    
    }
}

 
   

