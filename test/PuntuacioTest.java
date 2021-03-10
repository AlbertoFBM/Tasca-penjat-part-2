package test;

import com.jaume.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PuntuacioTest {

    private Puntuacio puntuacio;


    /*
    Amb @BeforeEach, el mètode reiniciarPuntuacio() s'executa abans
    de cada @Test, @RepeatedTest, @ParameterizedTest i @TestFactory
     */
    @BeforeEach
    void reiniciarPuntuacio(){

        puntuacio = new Puntuacio();
    }

    /*
    @DisplayName serveix per donar-li un nom descriptiu al mètode.
    @ParameterizedTest serveix per fer una prova parametritzada. S'utilitza
    amb @ValueSource per donar-li valors.
     */
    @DisplayName("Dificultat del penjat")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testParaulaSecretaDificultat(int dificultat) {
        puntuacio.getParaulaSecretaDificultat(dificultat);
        int intents = 0;
        switch (dificultat) {
            case 1:
                intents = 5;
                break;
            case 2:
                intents = 4;
                break;
            case 3:
                intents = 3;
                break;
            case 4:
                intents = 2;
            default:
                break;
        }
        if (intents < 0) {
            assertEquals("Error", this.puntuacio.getParaulaSecretaDificultat(dificultat));
        } else {
            assertEquals(intents, this.puntuacio.getIntents());
        }
    }
    /*
    @Test indica que el mètode és un mètode de prova
     */
    @DisplayName("Calcular la puntació")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testParaulaSecretaParaula(int dificultat) {
        String paraulaAleatoria = this.puntuacio.getParaulaSecretaDificultat(dificultat);
        switch (dificultat) {
            case 1:
                assertTrue(Arrays.asList(this.puntuacio.getParaules1()).contains(paraulaAleatoria));
                break;
            case 2:
                assertTrue(Arrays.asList(this.puntuacio.getParaules2()).contains(paraulaAleatoria));
                break;
            case 3:
                assertTrue(Arrays.asList(this.puntuacio.getParaules3()).contains(paraulaAleatoria));
                break;
            default:
                break;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testPuntuacioSi(int dificultat){
        puntuacio.getParaulaSecretaDificultat(dificultat);
        puntuacio.setParaula("paraulaRandom");
        String[] paraulaSecreta =
                {"p","a","r","a","u","l","a","S","e","c","r","e","t","a"};
        float puntuacioSi = 0.0f;
        switch (dificultat){
            case 1:
                puntuacioSi = 100.0f;
                break;
            case 2:
                puntuacioSi = 200.0f;
                break;
            case 3:
                puntuacioSi = 300.0f;
                break;
            default:
                break;
        }
        assertEquals(puntuacioSi, puntuacio.calcularPuntuacio(paraulaSecreta, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testPuntuacioSiTemps(int dificultat) throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(dificultat);
        puntuacio.setParaula("paraulaRandom");
        String[] paraulaSecreta =
                {"p","a","r","a","u","l","a","S","e","c","r","e","t","a"};
        float puntuacioTemps = 0.0f;
        switch (dificultat){
            case 1:
                puntuacioTemps = 50.0f;
                break;
            case 2:
                puntuacioTemps = 100.0f;
                break;
            case 3:
                puntuacioTemps = 150.0f;
                break;
            default:
                break;
        }
        TimeUnit.SECONDS.sleep(10);
        assertEquals(puntuacioTemps, puntuacio.calcularPuntuacio(paraulaSecreta, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testPuntuacioNo(int dificultat){
        puntuacio.getParaulaSecretaDificultat(dificultat);
        puntuacio.setParaula("parulaNoEndevinada");
        String[] paraulaSecreta =
                {"p","a","r",null,"u","l","a",null,"o",null,"n",null,"e","v","i",null,"a","d","a"};
        float puntuacioNo = 0.0f;
        switch (dificultat){
            case 1:
                puntuacioNo = 30.0f;
                break;
            case 2:
                puntuacioNo = 40.0f;
                break;
            case 3:
                puntuacioNo = 50.0f;
                break;
            default:
                break;
        }
        assertEquals(puntuacioNo, puntuacio.calcularPuntuacio(paraulaSecreta, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testNingunaAccertada(int dificultat){
        puntuacio.getParaulaSecretaDificultat(dificultat);
        puntuacio.setParaula("res");
        String[] paraulaSecreta = {null, null, null};
        float ningunaPuntuacio = 0.0f;
        assertEquals(ningunaPuntuacio, puntuacio.calcularPuntuacio(paraulaSecreta, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testPuntuacioExtra(int dificultat){
        puntuacio.getParaulaSecretaDificultat(dificultat);
        puntuacio.setParaula("malhauradament");
        String[] paraulaSecreta =
                {"m","a","l","h","a","u","r","a","d","a","m","e","n","t"};
        float puntuacioExtra = 0.0f;
        switch (dificultat){
            case 1:
                puntuacioExtra = 200.0f;
                break;
            case 2:
                puntuacioExtra = 300.0f;
                break;
            case 3:
                puntuacioExtra = 400.0f;
                break;
            default:
                break;
        }
        assertEquals(puntuacioExtra, puntuacio.calcularPuntuacio(paraulaSecreta, 1));
    }
}

