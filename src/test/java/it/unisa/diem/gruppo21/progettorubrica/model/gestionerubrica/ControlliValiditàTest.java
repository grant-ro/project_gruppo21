/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica;

import static it.unisa.diem.gruppo21.progettorubrica.model.gestionerubrica.ControlliValidità.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author granturco-roberta
 */
public class ControlliValiditàTest {
    
    /**
     * Test of controlloNumero method, of class ControlliValidità.
     */
    @Test
    public void testControlloNumeroValido() {
        // Verifica che il metodo restituisca true quando viene passato un numero di Telefono valido.
        assertTrue(controlloNumeroTelefonico("1234567890"),"La stringa '123457890' dovrebbe essere considerata valida.");
    }
    
    @Test
    public void testControlloNumeroNonValido() {
        // Verifica che il metodo restituisca false per un indirizzo email non valido.
        // Caso 1: Stringa vuota
        assertFalse(controlloNumeroTelefonico(""), "La stringa vuota non dovrebbe essere considerata valida.");
        
        //Caso 2: Stringa con soli spazi bianchi
        assertFalse(controlloNumeroTelefonico("  "), "La stringa con soli spazi bianchi non dovrebbe essere considerata valida.");
        
        //Caso 3: Stringa con spazi interni
        assertFalse(controlloNumeroTelefonico("123 456 78"), "La stringa con spazi bianchi al suo interno non dovrebbe essere considerata valida.");
        
        // Caso 4: Stringa con caratteri non numerici
        assertFalse(controlloNumeroTelefonico("123a45"), "La stringa con caratteri numerici non dovrebbe essere considerata valida.");
        
        //Caso 5: Stringa con più di dieci cifre
        assertFalse(controlloNumeroTelefonico("123456789000"), "La stringa con più di dieci cifre non dovrebbe essere considerata valida.");
        
        //Caso 5: Stringa con meno di dieci cifre
        assertFalse(controlloNumeroTelefonico("1234"), "La stringa con meno di dieci cifre non dovrebbe essere considerata valida.");
    }

    @Test
    public void testControlloNumeroNull() {
        // Verifica che il metodo lanci una IllegalArgumentException quando viene passato null.
        assertThrows(IllegalArgumentException.class, () -> {controlloNumeroTelefonico(null);},
                 "Il metodo deve lanciare un'IllegalArgumentException quando viene passato null come parametro.");
    }

    @Test
    public void testControlloIndirizzoEmailValido() {
        // Verifica che il metodo restituisca true per un indirizzo email valido.
        assertTrue(controlloIndirizzoEmail("test@example.com"),"La stringa 'test@example.com' dovrebbe essere considerata valida");
    }

    @Test
    public void testControlloIndirizzoEmailNonValido() {
        // Verifica che il metodo restituisca false per un indirizzo email non valido.
        // Caso 1: Stringa vuota
        assertFalse(controlloIndirizzoEmail(""), "La stringa vuota non dovrebbe essere considerata valida.");
        
        //Caso 2: Stringa con soli spazi bianchi
        assertFalse(controlloIndirizzoEmail("  "), "La stringa con soli spazi bianchi non dovrebbe essere considerata valida.");
        
    }

    @Test
    public void testControlloIndirizzoEmailNull() {
        // Verifica che il metodo lanci un'IllegalArgumentException quando viene passato null.
        assertThrows(IllegalArgumentException.class, () -> {controlloIndirizzoEmail(null);},
            "Il metodo deve lanciare un'IllegalArgumentException quando viene passato null come parametro.");
    }

    
    
    /**
     * Test of controlloRiempimento method, of class ControlliValidità.
     */
    @Test
    public void testControlloRiempimentoValido (){
        // Verifica che il metodo restituisca true quando vengono passate due stringhe che non sono entrambe vuote o formate da soli spazi bianchi
        // Caso 1:Solo la seconda contiene del testo
        assertTrue(controlloRiempimento(" ", "Test"), "La seconda contiene del testo, il risultato dovrebbe essere true.");
    
        // Caso 2: Solo la prima contiene del testo
        assertTrue(controlloRiempimento("Test", " "), "La prima contiene del testo, il risultato dovrebbe essere true.");
    
        // Caso 3: Entrambe le stringhe contengono del testo
        assertTrue(controlloRiempimento("Test1", "Test2"), "Entrambe le stringhe contengono del testo, il risultato dovrebbe essere true.");
        
    }
    
    @Test
    public void testControlloRiempimentoNonValido (){
        // Caso 1: Entrambe le stringhe sono vuote
        assertFalse(controlloRiempimento("", ""), "Entrambe le stringhe sono vuote, il risultato dovrebbe essere false.");
        
        // Caso 2: Entrambe le stringhe contengono solo spazi bianchi
        assertFalse(controlloRiempimento("   ", "   "), "Entrambe le stringhe contengono solo spazi bianchi, il risultato dovrebbe essere false");
    }
    
    @Test
    public void testControlloRiempimentoNull(){
        // Verifica che il metodo lanci una IllegalArgumentException quando viene passato null come uno dei due parametri
        assertThrows(IllegalArgumentException.class, () -> {controlloRiempimento(null,"Test");},
                 "Il metodo deve lanciare un'IllegalArgumentException quando viene passato null come primo parametro.");
    
        assertThrows(IllegalArgumentException.class, () -> {controlloRiempimento("Test",null);},
                 "Il metodo deve lanciare un'IllegalArgumentException quando viene passato null come secondo parametro.");
    }
}    
    
        
    