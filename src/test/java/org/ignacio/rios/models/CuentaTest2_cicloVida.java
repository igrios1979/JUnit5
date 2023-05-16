package org.ignacio.rios.models;

//import org.junit.jupiter.api.Assertions;

import org.ignacio.rios.exepciones.dieneroinsuficiente;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.*;
import static  org.junit.jupiter.api.Assumptions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest2_cicloVida {

    Cuenta cuenta;
    @BeforeEach
    void init_test(){
        System.out.println(" Iniciando" );
        this.cuenta = new Cuenta("ignacio", new BigDecimal("1000.2235"));
    }

    @AfterEach
    void after_m(){
        System.out.println("Despues decada metodo finalizando " );
    }

    @BeforeAll
   static void beforeAll() {
        System.out.println("inicializando el Test antes qeu nada----->>>>>>");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando....... el Test");
    }

    @Test
    @DisplayName("Probando nombre cuenta")
    void test_nombre_cuenta() {

        //  cuenta.setPersona("ignacio");
        String esperado = "ignacio";
        String real = cuenta.getPersona();
        //assertEquals(real);
        assertEquals(esperado, real);
        assertTrue(real.equals("ignacio"));

    }

    @Test
    void test_saldo() {

        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.2235, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(ZERO) > 0);

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
        //fail();
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


    @Test
    void imprimeSystemProperties() {
        Properties prop = System.getProperties();
        prop.forEach((k,v)-> System.out.println(k +" ---> " + v));
    }

    @Test
    @EnabledIfSystemProperty(named = "java.version",matches ="20") // machea con expresiones regulares
    void systemProperties() {
        System.out.println("Propiedades -------> version de java 20 "  );

    }

    @Test
    @EnabledIfSystemProperty(named="ENV",matches = "dev")
    void testDev() {

    }

    @Test
    void obtenervaramb() {

      Map<String,String> getenv =   System.getenv();
      getenv.forEach((a,b)-> System.out.println("variables de ambiente " + a +" -- "+ b));

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "JAVA_HOME" ,matches = ".*jre1.8.0_351.*" )
    void testJavaHome() {
    }
    @Test
    @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS" ,matches = "4" )
    void testProccessorNumber() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENVIROMENT" ,matches = "dev" )
    void testAmbiente() {
    }


    @ParameterizedTest(name = "numero{index} ejecuntado con valor {0} -- {argumentsWithNames}")
    @ValueSource(strings ={"100","200","300","1000","1001"})
    void Testdebitocta(String monto){
        cuenta.debito(new BigDecimal(monto));
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo( ZERO )>0);
    }

    @Nested // inner class anidada
    class saldoPrueba {
        //@Test se quita la @Test en un inner
        @DisplayName("Prueba saldo con Assumptions")
        @RepeatedTest(value=5, name="{displayName} - Repeticion Numero  = {currentRepetition}")
        void testSaldo(RepetitionInfo info) {
            if(info.getCurrentRepetition()==3){

                System.out.println("Estoy en la repeticion nro  ->>>>>>>>>  " + info.getCurrentRepetition());
            }


            boolean esDev = "dev".equals(System.getProperty("ENV"));
            assumeTrue(esDev);
            assertNotNull(cuenta.getSaldo());

            assertEquals(1000.2235, cuenta.getSaldo().doubleValue());
            assertFalse(cuenta.getSaldo().compareTo(ZERO) < 0);
            assertTrue(cuenta.getSaldo().compareTo(ZERO) > 0);

        }


        //@Test
        @DisplayName("Prueba AssumingTHAT")
        @RepeatedTest(value=15, name="{displayName} - Repeticion Numero  = {currentRepetition} de {totalRepetitions}")
            //Prueba con variables de entorno si falla programaticamente no se ejecuta el codigo
        void testSaldo2() {
            boolean esDev = "dev".equals(System.getProperty("ENV"));
            assumingThat(esDev, () -> { // no se ejecuta si da false la variable de entorno no existe
                assertNotNull(cuenta.getSaldo());
                assertEquals(1000.2235, cuenta.getSaldo().doubleValue());
                assertFalse(cuenta.getSaldo().compareTo(ZERO) < 0);
                assertTrue(cuenta.getSaldo().compareTo(ZERO) > 0);
                System.out.println("se ejecuto los asserts  " + " -- " + "adentro");

            });
            System.out.println("esDev = " + esDev);

        }





    }
}