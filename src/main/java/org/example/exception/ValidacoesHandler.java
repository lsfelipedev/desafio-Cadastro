package org.example.exception;


public class ValidacoesHandler {

    public static void validarNomeSobrenome(String nomeSobrenome){

        contemApenasLetras(nomeSobrenome);
        String[] splits = nomeSobrenome.split(" ");

        if(!nomeSobrenome.isEmpty() && splits.length == 1)
            throw new IllegalArgumentException("NOME e o SOBRENOME é Obrigatório.");
    }

    public static void contemApenasLetras(String letras){
        if(!letras.isEmpty() && !letras.matches("^[a-zA-Z ]+$"))
            throw new IllegalArgumentException("So é permitido letras de A-Z e sem acentos.");
    }

    public static Float validarPeso(Float peso){
        if (peso > 60 || peso < 0.5)
            throw new RuntimeException("Só é permitido peso entre 0.5kg e 60kg.");
        return peso;
    }

    public static  Float validarIdade(Float idade){
        if (idade > 20)
            throw new RuntimeException("Só é permitido menor de 20 anos.");
        return idade;
    }

    public static String validarValoresNulos(String pet, String sufixo) {

        if(!pet.isEmpty())
            return pet + sufixo;
        return "NÃO INFORMADO";
    }
}

