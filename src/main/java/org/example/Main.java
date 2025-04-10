package org.example;

import org.example.service.alteracao.AlterarDadosPet;
import org.example.service.cadastro.CadastrarPet;
import org.example.service.GerarFormulario;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GerarFormulario.criaArquivoFormulario();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Sistema de Cadastro");
        System.out.println("(1) Cadastrar um novo pet");
        System.out.println("(2) Alterar os dados do pet cadastrado");
        System.out.println("(3) Deletar um pet cadastrado");
        System.out.println("(4) Listar todos os pets cadastrados");
        System.out.println("(5) Listar pets por algum critério(idade, nome, raça)");
        System.out.println("(6) Sair");
        System.out.print("Digite o numero da opção do que deseja: ");

        Path file = Paths.get("formulario.txt");

        int response = scanner.nextInt();;
        switch(response){
            case 1:
                CadastrarPet.sistemaDeCadastro(scanner, file);
                break;
            case 2:
                AlterarDadosPet.sistemaAlteracao(scanner);
                break;
            default:
                System.err.println("essa opção não existe!");
        }

        scanner.close();
    }
}
