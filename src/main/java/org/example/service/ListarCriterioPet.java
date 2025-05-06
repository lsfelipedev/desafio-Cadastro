package org.example.service;

import org.example.util.RemovedorAcentos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ListarCriterioPet {

    public static void arquivosFiltradoCriterio(Scanner scanner){

        Path path = Paths.get("petsCadastrados");

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(path, "*.txt")){

            int escolha = escolherCriterio(scanner);
            String palavra = pesquisarPalavra(escolha, scanner);

            List<Path> pathList = listaPetFiltrado(paths, palavra);

            if(pathList.isEmpty())
                throw new RuntimeException("Nenhum Pet foi encontrado com esses dados.");

            System.out.println("=============== PETS ENCONTRADOS ===============");
            for (Path file : pathList) {
                System.out.println("Arquivo: " + file.getFileName().toString());
                lerArquivoPadrao(file);
                System.out.println("");
            }
            System.out.println("================================================");
        }
        catch (IOException e){
            throw new RuntimeException("Falha ao Listar os Pets por Critério.\nErro: " + e.getMessage());
        }
    }

    private static List<Path> listaPetFiltrado(DirectoryStream<Path> paths,
                                               String palavra) throws IOException {

        List<Path> fileList = new ArrayList<>();

        for (Path file : paths) {
            if(validaListaFiltradas(file, palavra))
                fileList.add(file);
        }
        return fileList;
    }

    private static boolean validaListaFiltradas(Path file, String palavra) throws IOException {

        BufferedReader bfReader = Files.newBufferedReader(file);
        String linha;
        while((linha = bfReader.readLine()) != null){

            linha = RemovedorAcentos.tirarAcentosPalavras(linha.toLowerCase());
            palavra = RemovedorAcentos.tirarAcentosPalavras(palavra.toLowerCase());

            if(linha.startsWith(String.valueOf(palavra.charAt(0))))
                if(linha.contains(palavra.substring(1))) {
                    bfReader.close();
                    return true;
                }
        }
        bfReader.close();
        return false;
    }

    private static void lerArquivoPadrao(Path file){
        String linha;
        try (BufferedReader bfReader = Files.newBufferedReader(file)){
        while ((linha = bfReader.readLine()) != null)
            System.out.println(linha);
        }
        catch (IOException e) {
            throw new RuntimeException("Falha no BufferedReader no lerArquivoPadrao\nErro: " + e.getMessage());
        }
    }

    private static int escolherCriterio(Scanner scanner){

        while (true){
            System.out.println("Seleciona o numero do critério de busca do pet para atualizar:");
            System.out.println("1 - Nome ou sobrenome");
            System.out.println("2 - Tipo(Cachorro/Gato)");
            System.out.println("3 - Sexo(Macho/Femea)");
            System.out.println("4 - Endereço");
            System.out.println("5 - Idade");
            System.out.println("6 - Peso");
            System.out.println("7 - Raça");
            int criterio = scanner.nextInt();

            if (criterio >= 1 && criterio <= 7)
                return criterio;
        }
    }

    public static String pesquisarPalavra(int valor, Scanner scanner){

        String pesquisa = "";
        scanner.nextLine(); // Consome a quebra de linha pendente

        switch (valor){
            case 1:
                System.out.print("Pesquisa o nome: ");
                pesquisa = "1" + scanner.nextLine();
                break;
            case 2:
                System.out.print("Pesquisa o tipo: ");
                pesquisa = "2" + scanner.nextLine();
                break;
            case 3:
                System.out.print("Pesquisa o sexo: ");
                pesquisa = "3" + scanner.nextLine();
                break;
            case 4:
                System.out.print("Pesquisa o endereço: ");
                pesquisa = "4" + scanner.nextLine();
                break;
            case 5:
                System.out.print("Pesquisa a idade: ");
                pesquisa = "5" + scanner.nextLine();
                break;
            case 6:
                System.out.print("Pesquisa o peso: ");
                pesquisa = "6" + scanner.nextLine();
                break;
            case 7:
                System.out.print("Pesquisa a raça: ");
                pesquisa = "7" + scanner.nextLine();
                break;
            case 0:
                break;
            default:
                System.err.println("Não existe essa opção!");
        }
        return pesquisa;
    }
}
