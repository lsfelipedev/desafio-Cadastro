package org.example.model;

public class Pet {

    /**Nas perguntas sobre NOME, RAÇA, PESO, IDADE e ENDEREÇO (somente o campo número),
    caso o usuário NÃO INFORME ou deixe em branco, você deverá preencher com NÃO INFORMADO (deve ser uma constante)!
    **/

    //Não pode conter caracteres especiais, Somente letras de A-Z
    private String nome_sobrenome;

    private Tipo tipo;

    private Sexo sexo;

    private Endereco endereco;

    //Exceção caso o pet tenha mais de 20 anos
    private Float idade;

    //Exceção caso o pet tenha peso maior que 60kg e menor que 0.5kg
    private Float peso;

    //Exceção caso seja digitado numero ou caracteres especiais
    private String raca;

    public Pet(String nome_sobrenome, Tipo tipo, Sexo sexo, Endereco endereco, Float idade, Float peso, String raca) {
        this.nome_sobrenome = nome_sobrenome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet() {}

    public String getNome_sobrenome() {
        return nome_sobrenome;
    }

    public void setNome_sobrenome(String nome_sobrenome) {
        this.nome_sobrenome = nome_sobrenome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Float getIdade() {
        return idade;
    }

    public void setIdade(Float idade) {
        this.idade = idade;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}

