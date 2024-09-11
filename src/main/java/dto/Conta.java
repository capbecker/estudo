package dto;

import dto.exception.SaldoInsuficienteException;

import java.io.Serializable;
import java.util.Locale;

/**
 * Classe representa a moldura de uma conta
 */
public abstract class Conta extends Object implements Comparable<Conta>, Serializable {

    private static final long serialVersionUID = 2L;

    protected double saldo;
    private int agencia;
    private int numero;
    private transient Cliente titular; // Determina que o cliente nao vai ser gravado no objeto
    private static int total = 0;
    
    /**
     * Construtor para inicializar o objeto Conta a partir da agencia e numero.
     * 
     * @param agencia
     * @param numero
     */
    public Conta(Cliente cliente, int agencia, int numero){
        Conta.total++;
        this.titular = cliente;
        this.agencia = agencia;
        this.numero = numero;
    }

    public abstract Conta deposita(double valor);

    /**
     * Valor precisa ser maior do que o saldo.
     * 
     * @param valor
     * @throws SaldoInsuficienteException
     */
    public void saca(double valor) throws SaldoInsuficienteException {
    	
        if(this.saldo < valor) {
            throw new SaldoInsuficienteException("Saldo: " + this.saldo + ", Valor: " + valor);
        } 
        
        this.saldo -= valor;       
    }

    /**
     * Transfere um valor de uma conta para outra
     *
     * @param valor
     * @param destino
     * @throws SaldoInsuficienteException
     */
    public void transfere(double valor, Conta destino) throws SaldoInsuficienteException{
        this.saca(valor);
        destino.deposita(valor);
    }

    public double getSaldo(){
        return this.saldo;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero){
        if(numero <= 0) {
            System.out.println("Nao pode valor menor igual a 0");
            return;
        }
        this.numero = numero;
    }

    public int getAgencia(){
        return this.agencia;
    }

    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    public Cliente getTitular(){
        return this.titular;
    }

    public static int getTotal(){
        return Conta.total;
    }  
    
    @Override
    public boolean equals(Object ref) {
    	
    		Conta outra = (Conta) ref;
    		
    		if(this.agencia != outra.agencia) {
    			return false;
    		}
    		
    		if(this.numero != outra.numero) {
    			return false;
    		}
    		
    		return true;
    }
    
    @Override
    public int compareTo(Conta outra) {
    		return Double.compare(this.saldo, outra.saldo);
    }
    
    @Override
	public String toString() {
        return String.format(new Locale("pt","BR", "\\."),"[%s,%03d,%08d,%1.2f]",titular, agencia, numero, saldo);
	}

}