package org.example.service.tools;

import org.example.model.Tipo;
import org.example.util.RemovedorAcentos;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BuscadorPet {


    public static Map<String, String> pesquisarPalavra(int[] valores, Scanner scanner){

        Map<String, String> pesquisa = new HashMap<>();
        scanner.nextLine(); // Consome a quebra de linha pendente

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

    public static Path arquivosSelecionados(Tipo tipo,
                                             Path arquivo,
                                             Map<String, String> pesquisarPalavras) {

        String linha;
        boolean palavraEncontrada = false, tipoEncontrado = false;

        try (BufferedReader br = Files.newBufferedReader(arquivo)) {
            while ((linha = br.readLine()) != null) {
                linha = linha.toLowerCase();

                for (var keyValue : pesquisarPalavras.entrySet()) {

                    linha = RemovedorAcentos.tirarAcentosPalavras(linha);

                    if (linha.startsWith(keyValue.getKey()) &&
                            linha.contains(keyValue.getValue().toLowerCase()))
                        palavraEncontrada = true;

                    if (linha.contains(tipo.toString().toLowerCase()))
                        tipoEncontrado = true;

                    if(palavraEncontrada && tipoEncontrado)
                        return arquivo;
                }
            }
        }
        catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + arquivo.getFileName().toString());
            e.printStackTrace();
        }
        return null;
    }

    public static void lerArquivo(Path arquivo) {
        try (BufferedReader br = Files.newBufferedReader(arquivo)) {
            String linha;
            while ((linha = br.readLine()) != null) {

                if (linha.startsWith("1")) {
                    System.out.print(linha.substring(3));
                    continue;
                }
                System.out.print(linha.substring(1));
            }
        }
        catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + arquivo.getFileName().toString());
            e.printStackTrace();
        }
    }
}
