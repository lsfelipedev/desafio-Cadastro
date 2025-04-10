package org.example.service.alteracao;

import org.example.model.Tipo;
import java.util.Scanner;

public class CriterioBuscaPet {

    public static Tipo selecioneTipoAnimal(Scanner scanner){
        System.out.println("Primeiramente, Qual o TIPO de ANIMAL vc quer selecionar??");
        System.out.println("1 - Cachorro");
        System.out.println("2 - Gato");
        int tipo = scanner.nextInt();

        switch (tipo){
            case 1:
                return Tipo.Cachorro;
            case 2:
                return Tipo.Gato;
            default:
                System.err.println("Não existe essa opção, escolhe entre 1 e 2.");
                selecioneTipoAnimal(scanner);
        }
        throw new RuntimeException("Falha no sistema do método do selecioneTipoAnimal()");
    }

    public static int primeiroCriterio(Scanner scanner) {

        while (true){
            System.out.println("Seleciona o numero do critério de busca do pet para atualizar:");
            System.out.println("1 - Nome ou sobrenome");
            System.out.println("2 - Sexo");
            System.out.println("3 - Idade");
            System.out.println("4 - Peso");
            System.out.println("5 - Raça");
            System.out.println("6 - Endereço");
            int criterio = scanner.nextInt();

            if(criterio <= 6 && criterio >= 1){
                return criterio;
            }
            System.err.println("Esse numero não uma opção. Escolha entre 1 e 6.");
        }
    }


    public static int segundoCriterio(Scanner scanner){
        String resposta;
        while (true) {
            System.out.println("que adicionar mais 1 critério de busca?? [S/N]");
            resposta = scanner.next().toUpperCase();

            if (resposta.equals("S"))
                return primeiroCriterio(scanner);
            else if (resposta.equals("N"))
                break;
            else
                System.err.println("Escolha entre 'S' ou 'N' como resposta.");
        }
        return 0;
    }
}
