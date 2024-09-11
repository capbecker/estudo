package dto;

/**
 * Classe que determina uma conta poupança
 */
public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente, int agencia, int numero) {
		super(cliente, agencia, numero);
	}

	@Override
	public ContaPoupanca deposita(double valor) {
		super.saldo += valor;
		return this;
	}
	
	@Override
	public String toString() {
		return "ContaPoupanca, " + super.toString();
	}
	
}
