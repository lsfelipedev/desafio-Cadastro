package org.example.service;

import org.example.service.tools.BuscadorPet;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListarTodosPet {

    public static void listagemPets(){

        Path pasta = Paths.get("petsCadastrados");
        int count = 1;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(pasta, "*.txt");){

            for (Path path : paths) {
                System.out.print(count++ + ".");
                BuscadorPet.lerArquivo(path);
                System.out.println();
            }
        }
        catch (IOException e){
            throw new RuntimeException("Falha ao listar todo os Pets.\nErro: " + e.getMessage());
        }
    }
}
