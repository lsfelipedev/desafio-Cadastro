package org.example.model;

public class Endereco {

    private int numeroCasa;
    private String cidade;
    private String rua;

    public Endereco(int numeroCasa, String cidade, String rua) {
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        if(numeroCasa == 0)
            return rua + ", NÃO INFORMADO , " + cidade;
        return rua + ", " + numeroCasa + ", " + cidade;
    }
}
