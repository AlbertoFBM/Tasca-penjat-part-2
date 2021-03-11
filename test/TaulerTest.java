package test;

import com.jaume.penjat.Tauler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alberto Serrano
 * @since  1.0
 */
class TaulerTest {

    private Tauler tauler;

    /**
     * reinicia el Tauler
     */
    @BeforeEach
    void reiniciar(){
        this.tauler = new Tauler();
        this.tauler.inicialitzarPartida("paraula", 4);
    }

    /**
     * inicia la partida posant una paraula
     */
    @Test
    void inicialitzarPartidaParaulaSecreta() {
        assertArrayEquals(new char[]{'p', 'a', 'r', 'a', 'u', 'l', 'a'}, this.tauler.getParaulaSecreta());
    }

    /**
     * inicia la partida posant 4 intents
     */
    @Test
    void inicialitzarPartidaNombreIntents() {
        assertEquals(4, this.tauler.getIntents());
    }

    /**
     * si l'Usuari posa una lletra incorrecta, s'imprimeix el texte
     */
    @Test
    void verificarEntradaIncorrecte() {
        assertEquals("Lletra incorrecte",this.tauler.verificar("ll"));
    }

    /**
     * si l'Usuari posa una lletra correcta, s'imprimeix la lletra
     */
    @Test
    void verificarEntradaCorrecteEncertat() {
        this.tauler.verificar("a");
        assertArrayEquals(new String[]{ null,"a",null,"a",null,null,"a" },this.tauler.getPalabraEndevinada());
    }

    /**
     * Si l'Usuari falla, se li resta un intent
     */
    @Test
    void verificarEntradaCorrecteErrada() {
        this.tauler.verificar("n");
        assertEquals(3,this.tauler.getIntents());
    }

    /**
     * Si l'Usuari no encerta cap
     */
    @Test
    void imprimirCapEncert() {
        assertEquals("_______", this.tauler.imprimir());
    }

    /**
     * imprimir lletres
     */
    @Test
    void imprimirAmbLletres() {
        this.tauler.verificar("a");
        assertEquals("_a_a__a", this.tauler.imprimir());
    }

    /**
     * imprimir tota la paraula perque ho ha acertat tot
     */
    @Test
    void imprimirTotEncertat() {
        this.tauler.verificar("a");
        this.tauler.verificar("p");
        this.tauler.verificar("r");
        this.tauler.verificar("u");
        this.tauler.verificar("l");
        assertEquals("paraula", this.tauler.imprimir());
    }

    /**
     * Imprimeix un text quan té totes les vides
     */
    @Test
    void imprimirVidesPlural() {
        assertEquals("Et queden 4 vides de 4", this.tauler.imprimirVides());
    }

    /**
     * Imprimeix un text quan té una vida
     */
    @Test
    void imprimirVidesSingular() {
        this.tauler.verificar("n");
        this.tauler.verificar("n");
        this.tauler.verificar("n");
        assertEquals("Et queda 1 vida de 4", this.tauler.imprimirVides());
    }

    /**
     * resta un intent quan l'usuari falla
     */
    @Test
    void restarIntents() {
        this.tauler.verificar("n");
        assertEquals(3, this.tauler.getIntents());
    }

    /**
     * verifica que l'usuari ha guanyat
     */
    @Test
    void hasGuanyatTrue() {
        this.tauler.verificar("a");
        this.tauler.verificar("p");
        this.tauler.verificar("r");
        this.tauler.verificar("u");
        this.tauler.verificar("l");
        assertTrue(this.tauler.hasGuanyat());
    }

    /**
     * verifica que l'usuari NO ha guanyat
     */
    @Test
    void hasGuanyatFalse() {
        assertFalse(this.tauler.hasGuanyat());
    }
}