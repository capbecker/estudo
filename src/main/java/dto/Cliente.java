package dto;

/**
 * Classe que representa um cliente no Bytebank.
 */
public class Cliente {

    private String nome;
    private String cpf;
    private String profissao;
    private Boolean vivo = true;

    public Cliente(String nome, String cpf, String profissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.profissao = profissao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Boolean getVivo() {
        return vivo;
    }

    public Cliente setVivo(Boolean vivo) {
        this.vivo = vivo;
        return this;
    }

    @Override
    public int hashCode() {
        return this.getCpf().hashCode();
    }

    @Override
    public String toString() {
        return "["+this.nome+","+this.cpf+","+this.profissao+"]";
    }

}
