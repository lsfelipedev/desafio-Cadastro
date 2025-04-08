package org.example.service;

import org.example.model.Tipo;
import org.example.util.CriterioBuscaPet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AlterarDadosPet {

    private static CriterioBuscaPet criterioBuscaPet = new CriterioBuscaPet();

    public static void atualizaDadosPet(Scanner scanner) {
        Tipo tipo = criterioBuscaPet.selecioneTipoAnimal(scanner);
        int[] opcoes = {criterioBuscaPet.primeiroCriterio(scanner), criterioBuscaPet.segundoCriterio(scanner)};

        Map<String, String> palavrasPesquisar = arquivoSelecionado(opcoes, scanner);


        File pasta = new File("petsCadastrados");
        File[] files = pasta.listFiles((dir, nome) -> nome.endsWith(".txt"));
        for (File file : files) {
            lerArquivo(tipo ,file, palavrasPesquisar);
        }
    }

    private static List<File> lerArquivo(Tipo tipo, File arquivo, Map<String, String> pesquisarPalavras) {

        List<File> files = new ArrayList<>();
        String linha;
        String pesquisar;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            System.out.println("\n--- Conteúdo de " + arquivo.getName() + " ---");

            while ((linha = br.readLine()) != null) {
                System.out.println(linha.toLowerCase());

                for (var key : pesquisarPalavras.keySet()) {
                    if (linha.startsWith(key)) {
                        pesquisar = pesquisarPalavras.get(key).toLowerCase();
                        if(linha.contains(pesquisar))
                            files.add(arquivo);
                    }
                }
            }
            return files;

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + arquivo.getName());
            e.printStackTrace();
        }
        return files;
    }

    private static Map<String, String> arquivoSelecionado(int[] valores, Scanner scanner){

        Map<String, String> pesquisa = new HashMap<>();

        for (Integer valor : valores) {

            switch (valor){
                case 1:
                    System.out.print("Pesquisa o nome: ");
                    pesquisa.put("1", scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Pesquisa o sexo: ");
                    pesquisa.put("3", scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Pesquisa a idade: ");
                    pesquisa.put("5", scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Pesquisa o peso: ");
                    pesquisa.put("6", scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Pesquisa a raça: ");
                    pesquisa.put("7", scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Pesquisa o endereço: ");
                    pesquisa.put("4", scanner.nextLine());
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Não existe essa opção!");
            }
        }

        return pesquisa;
    }
}
