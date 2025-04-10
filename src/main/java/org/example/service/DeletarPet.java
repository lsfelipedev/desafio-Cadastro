package org.example.service;

import org.example.model.Tipo;
import org.example.service.tools.BuscadorPet;
import org.example.service.tools.CriterioArquivoPet;
import org.example.service.tools.CriterioBuscaPet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DeletarPet {

    public static void sistemaDeletePet(Scanner scanner){

        Tipo tipo = CriterioBuscaPet.selecioneTipoAnimal(scanner);
        int[] opcoes = {CriterioBuscaPet.primeiroCriterio(scanner),
                CriterioBuscaPet.segundoCriterio(scanner)};

        Map<String, String> palavrasPesquisar = BuscadorPet.pesquisarPalavra(opcoes, scanner);

        try {
        List<Path> files = CriterioArquivoPet.arquivosFiltradoFormatado(tipo, palavrasPesquisar);
        deletePet(files, scanner);
        }
        catch (IOException e){
            throw new RuntimeException("falha ao lista os arquivos do resultado da busca.\nErro: " + e.getMessage());
        }
    }

    private static void deletePet(List<Path> files, Scanner scanner){

        System.out.print("Escolha o numero do PET registrado que deseja deletar: ");
        int opcao = scanner.nextInt();
        Path fileToDelete = files.get(opcao - 1);

        try {
            Files.delete(fileToDelete);
            System.out.println(fileToDelete.getFileName().toString() + " Deletado Com Sucesso!");
        }
        catch (IOException e){
            throw new RuntimeException("Falha ao tentar deletar o arquivo\nErro: " + e.getMessage());
        }
    }
}
