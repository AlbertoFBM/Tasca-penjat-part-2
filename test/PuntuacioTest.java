package test;
import com.jaume.penjat.Puntuacio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

class PuntuacioTest {

    private Puntuacio puntuacio;

    @DisplayName("Dificultat del penjat")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void mostrarDificultat(int dificultat){
    }


    @DisplayName("Calcular la puntació")
    @Test
    void calcularPuntuacioString(String[] paraulaEndevinada){
    assertTrue(String paraulaEndevinada);
    }
}
