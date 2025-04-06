package org.example.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GerarFormulario {

    public static boolean criaArquivoFormulario(){

        if(new File("formulario.txt").exists())
            return false;

        File file = new File("formulario.txt");

        try(FileWriter fileWriter = new FileWriter(file)) {
            getBufferedWriter(fileWriter);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getBufferedWriter(FileWriter fileWriter) throws IOException {
        BufferedWriter bfWriter = new BufferedWriter(fileWriter);
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
        bfWriter.close();
    }
}
