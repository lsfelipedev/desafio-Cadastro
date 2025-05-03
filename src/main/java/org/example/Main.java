package org.example;

import org.example.service.*;
import org.example.util.GerarFormulario;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        GerarFormulario.criaArquivoFormulario();

        while (true){

            Path file = Paths.get("formulario.txt");

            System.out.println("~~~~~~~~~~~~~~~~ Sistema de Cadastro ~~~~~~~~~~~~~~~~~");
            System.out.println("(1) Cadastrar um novo pet");
            System.out.println("(2) Alterar os dados do pet cadastrado");
            System.out.println("(3) Deletar um pet cadastrado");
            System.out.println("(4) Listar todos os pets cadastrados");
            System.out.println("(5) Listar pets por algum critério(idade, nome, raça)");
            System.out.println("(6) Sair");

            try {
                System.out.print("Digite o numero da opção do que deseja: ");
                int response = scanner.nextInt();

                switch(response){
                    case 1:
                        CadastrarPet.sistemaDeCadastro(scanner, file);
                        break;
                    case 2:
                        AlterarDadosPet.sistemaAlteracao(scanner);
                        break;
                    case 3:
                        DeletarPet.sistemaDeletePet(scanner);
                        break;
                    case 4:
                        ListarTodosPet.listagemPets();
                        break;
                    case 5:
                        ListarCriterioPet.arquivosFiltradoCriterio(scanner);
                        break;
                    case 6:
                        System.out.println("Saindo do Sistema..");
                        break;
                    default:
                        System.err.println("essa opção não existe!");
                }

            }catch (InputMismatchException e){
                System.err.println("Apenas NUMERO é permitido no Sistema de Menu.");
                scanner.nextLine(); // LIMPANDO O CACHE DO SCANNER
            }
            catch (RuntimeException e){
                System.err.println("Erro: " + e.getMessage());
            }
            Thread.sleep(1000);
        }
    }
}
