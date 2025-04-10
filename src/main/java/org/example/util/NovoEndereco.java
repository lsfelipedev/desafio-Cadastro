package org.example.util;

import org.example.model.Endereco;

import java.util.Scanner;

public class NovoEndereco {

    public static Endereco verificaEndereco(Scanner scanner){

        System.out.print("Digite o Número da casa: ");
        int numCasa = scanner.nextInt();
        scanner.nextLine(); // consume a quebra de linha.
        System.out.print("Digite a Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite a Rua: ");
        String rua = scanner.nextLine();

        return new Endereco(
                numCasa,
                CapitalizaPalavras.Capitalizador(cidade),
                CapitalizaPalavras.Capitalizador(rua));
    }
}
