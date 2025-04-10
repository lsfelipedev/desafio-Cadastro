package org.example.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GerarFormulario {

    public static boolean criaArquivoFormulario(){

        Path file = Paths.get("formulario.txt");
        if(Files.exists(file))
            return false;

        try (BufferedWriter bfWriter = Files.newBufferedWriter(file)) {
            escreveDadoFormulario(bfWriter);
            return true;
        }
         catch (IOException e) {
            throw new RuntimeException("Falha ao escrever os dados no arquivo formulario.txt");
        }
    }

    private static void escreveDadoFormulario(BufferedWriter bfWriter) throws IOException {
        bfWriter.write("Qual o nome e sobrenome do pet?");
        bfWriter.newLine();
        bfWriter.write("Qual o tipo do pet (Cachorro/Gato)?");
        bfWriter.newLine();
        bfWriter.write("Qual o sexo do animal(Macho/Fêmea)?");
        bfWriter.newLine();
        bfWriter.write("Qual endereço e bairro que ele foi encontrado?");
        bfWriter.newLine();
        bfWriter.write("Qual a idade aproximada do pet?");
        bfWriter.newLine();
        bfWriter.write("Qual o peso aproximado do pet?");
        bfWriter.newLine();
        bfWriter.write("Qual a raça do pet?");
        bfWriter.flush();
    }
}
