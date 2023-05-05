**TDD (Test-Driven Development)** es una técnica de desarrollo de software que se basa en escribir primero pruebas automatizadas para el código que se desea implementar y, a continuación, desarrollar el código que haga que esas pruebas pasen satisfactoriamente.

**El proceso típico de TDD es el siguiente:**

**1**.  Escribir una prueba automatizada que describa el comportamiento que se espera del software.

**2.**  Ejecutar la prueba. Esta debería fallar ya que el código aún no ha sido implementado.

**3**.  Escribir el código mínimo necesario para que la prueba pase satisfactoriamente.

**4.**  Ejecutar la prueba de nuevo para asegurarse de que pasa satisfactoriamente.

**5.**  Refactorizar el código si es necesario.

El ciclo se repite varias veces, escribiendo nuevas pruebas para describir nuevos comportamientos, desarrollando el código mínimo necesario para hacer que esas pruebas pasen y refactorizando el código si es necesario. De esta manera, se asegura que el código se escribe con pruebas de calidad y se garantiza que el software funciona correctamente según los requisitos del usuario.

Entre los beneficios de **TDD** se incluyen la reducción de errores en el código, la mejora de la calidad del software, la reducción del tiempo de desarrollo y la mejora de la documentación del software a través de las pruebas automatizadas.


----------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------

En Java con JUnit, uno de los hooks más comunes es el método @Before y @After, que se utilizan para configurar y limpiar el estado antes y después de cada prueba. Estos hooks se ejecutan automáticamente por JUnit antes y después de cada prueba unitaria.

Aquí hay un ejemplo de cómo usar el hook @Before en una clase de prueba de JUnit: