package testeException.experiencia;

import testeException.experiencia.annotations.Campo;
import testeException.experiencia.annotations.Construtor;
import testeException.experiencia.annotations.Metodo;

import java.time.LocalDate;

public class Pessoa {

    @Campo("nome")
    private String nome;
    private LocalDate nascimento;

    @Construtor("pessoa")
    public Pessoa(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }


    @Metodo("getNome")
    public String getNome() {
        return nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Integer getIdade() {
        LocalDate hoje = LocalDate.now();
        Integer dia = hoje.getDayOfMonth()-nascimento.getDayOfMonth();
        Integer mes = (hoje.getMonthValue()-nascimento.getMonthValue()) - (dia>=0?0:1);
        Integer ano = hoje.getYear()-nascimento.getYear() - (mes>=0?0:1);
        return ano;
    }


}
