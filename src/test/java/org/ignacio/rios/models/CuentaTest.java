package org.ignacio.rios.models;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void test_nombre_cuenta(){
        Cuenta cuenta = new Cuenta("ignacio",new BigDecimal("1000.2235"));
      //  cuenta.setPersona("ignacio");
        String esperado  = "ignacio";
        String real = cuenta.getPersona();
       assertEquals(esperado,real);
       assertTrue(real.equals("ignacio"));

    }

@Test
    void test_saldo(){
        Cuenta cuenta = new Cuenta("ignacio",new BigDecimal("1000.2235"));
        assertEquals(1000.2235,cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
         assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);

}


}