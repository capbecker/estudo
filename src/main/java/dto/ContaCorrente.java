package dto;

import dto.exception.SaldoInsuficienteException;

import java.io.Serializable;

/**
 * Classe que determina uma conta corrente
 */
public class ContaCorrente extends Conta implements Tributavel, Serializable {

	public ContaCorrente(Cliente cliente, int agencia, int numero) {
		super(cliente, agencia, numero);
	}

	@Override
	public void saca(double valor) throws SaldoInsuficienteException {
		double valorASacar = valor + 0.2;
		super.saca(valorASacar);
	}

	@Override
	public ContaCorrente deposita(double valor) {
        super.saldo += valor;
        return this;
    }

	@Override
	public double getValorImposto() {	
		return super.saldo * 0.01;
	}
	
	@Override
	public String toString() {
		return "ContaCorrente, " + super.toString();
	}
	
}
