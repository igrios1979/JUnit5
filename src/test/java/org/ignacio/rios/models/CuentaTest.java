package org.ignacio.rios.models;

//import org.junit.jupiter.api.Assertions;

import org.ignacio.rios.exepciones.dieneroinsuficiente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    @DisplayName("Probando nombre cuenta")
    void test_nombre_cuenta() {
        Cuenta cuenta = new Cuenta("ignacio", new BigDecimal("1000.2235"));
        //  cuenta.setPersona("ignacio");
        String esperado = "ignacio";
        String real = cuenta.getPersona();
        //assertEquals(real);
        assertEquals(esperado, real);
        assertTrue(real.equals("ignacio"));

    }

    @Test
    void test_saldo() {
        Cuenta cuenta = new Cuenta("ignacio", new BigDecimal("1000.2235"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.2235, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);

    }

    @Test
    void referencia_de_cuenta() {

        Cuenta cuenta1 = new Cuenta("juan", new BigDecimal("580030"));
        Cuenta cuenta2 = new Cuenta("juan", new BigDecimal("580030"));
        // assertNotEquals(cuenta1,cuenta2);

        assertEquals(cuenta1, cuenta2);

    }


    @Test
    void test_debito_cuenta() {
        Cuenta cuenta1 = new Cuenta("ignacio", new BigDecimal("1000.45612"));
        cuenta1.debito(new BigDecimal("100"));
        assertNotNull(cuenta1.getSaldo());
        assertEquals(900, cuenta1.getSaldo().intValue());
        assertEquals("900.45612", cuenta1.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("ignacio", new BigDecimal("1000.123456"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.123456", cuenta.getSaldo().toPlainString());
    }


    @Test
    void Exepcion_test() {
        Cuenta cuenta = new Cuenta("ignacio", new BigDecimal("1000.123456"));
        Exception exception = assertThrows(dieneroinsuficiente.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(actual, esperado);

    }

    @Test
    void transferir_dineroCuenta() {
        Cuenta cuentaUno = new Cuenta("ignacio rios ", new BigDecimal("2500"));
        Cuenta cuentaDos = new Cuenta("andres guszman", new BigDecimal("1500"));

        Banco banco1 = new Banco();
        banco1.setNombre("santander");
        banco1.transferir(cuentaUno, cuentaDos, new BigDecimal(500));
        assertEquals("2000", cuentaDos.getSaldo().toPlainString());
        assertEquals("2000", cuentaUno.getSaldo().toPlainString());


    }


    @Test
    void testrelacionbancoCuenta() {
        Cuenta cuentaUno = new Cuenta("ignacio rios ", new BigDecimal("2500"));
        Cuenta cuentaDos = new Cuenta("andres guszman", new BigDecimal("1500"));

        Banco banco1 = new Banco();
        banco1.addCuenta(cuentaUno);
        banco1.addCuenta(cuentaDos);
        /* ---------------------------------------------*/
        banco1.setNombre("santander");
        banco1.transferir(cuentaUno, cuentaDos, new BigDecimal(500));
        assertEquals("2000", cuentaDos.getSaldo().toPlainString());
        assertEquals("2000", cuentaUno.getSaldo().toPlainString());
        assertEquals(2, banco1.getCuentas().size());
        assertEquals("santander", cuentaUno.getBanco().getNombre());


    }

    @Test
    void testConStreams() {

        Cuenta cuentaUno = new Cuenta("ignacio rios", new BigDecimal("2500"));
        Banco bcoUno = new Banco();
        bcoUno.addCuenta(cuentaUno);
        assertEquals(1, bcoUno.getCuentas().size());
        assertEquals("ignacio rios", bcoUno.getCuentas().stream()
                .filter(cta -> cta.getPersona().equals("ignacio rios"))
                .findFirst().get().getPersona());

        assertTrue(bcoUno.getCuentas().stream()
                .filter(cta -> cta.getPersona().equals("ignacio rios"))
                .findFirst().isPresent());

        assertTrue(bcoUno.getCuentas().stream()
                .anyMatch(cta -> cta.getPersona().equals("ignacio rios")));

    }


    @Test
    void testAssertAll() {

        Cuenta cuentaUno = new Cuenta("ignacio rios", new BigDecimal("2500"));
        Cuenta cuentaDos = new Cuenta("andres guszman", new BigDecimal("1500"));

        Banco bcoUno = new Banco();
        bcoUno.addCuenta(cuentaUno);
        bcoUno.addCuenta(cuentaDos);

        assertAll(() -> assertEquals(2, bcoUno.getCuentas().size(),"Error nro de cuentas.."),
                  () -> assertTrue(bcoUno.getCuentas().stream().filter(cta -> cta.getPersona().equals("ignacio rios")).findFirst().isPresent(),"Encontro el primero prueba 2"),
                  () -> assertTrue(bcoUno.getCuentas().stream().anyMatch(cta -> cta.getPersona().equals("ignacio rios")),"error 3 ")
                  );


    }

}