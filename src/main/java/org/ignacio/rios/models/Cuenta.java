package org.ignacio.rios.models;

import org.ignacio.rios.exepciones.dieneroinsuficiente;

import java.math.BigDecimal;

public class Cuenta {

    private String persona;
    private BigDecimal saldo;

    private Banco banco;

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto) {
        BigDecimal saldoNvo = this.saldo.subtract(monto);
        if (saldoNvo.compareTo(BigDecimal.ZERO) < 0) {
            throw new dieneroinsuficiente("Dinero Insuficiente");
        }
        this.saldo = saldoNvo;


    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);

    }


    /**
     * Compara el objeto actual con otro objeto especificado para determinar si son iguales.
     *
     * @param obj el objeto a comparar con el objeto actual
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        // Verifica si el objeto especificado es una instancia de la clase Cuenta
        if (!(obj instanceof Cuenta)) {
            return false;
        }

        // Realiza un casting del objeto a una instancia de la clase Cuenta
        Cuenta otraCuenta = (Cuenta) obj;

        // Verifica si tanto la persona como el saldo de la cuenta actual no son nulos
        if (this.persona == null || this.saldo == null) {
            return false;
        }

        // Compara la persona y el saldo de la cuenta actual con la otra cuenta
        return this.persona.equals(otraCuenta.getPersona()) && this.saldo.equals(otraCuenta.getSaldo());
    }


}