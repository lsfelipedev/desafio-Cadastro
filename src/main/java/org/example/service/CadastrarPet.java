package org.example.service;

import org.example.exception.ValidacoesHandler;
import org.example.model.Pet;
import org.example.service.tools.ValidacaoEnum;
import org.example.util.CapitalizaPalavras;
import org.example.util.CriaTituloArquivo;
import org.example.util.NovoEndereco;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CadastrarPet {

    public static void sistemaDeCadastro(Scanner scanner, Path file) {

        try (BufferedReader br = Files.newBufferedReader(file)) {
            criaArquivoPet(scanner, br);
            System.out.println("Cadastro do Pet foi Registrado com Sucesso!");

        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }

    public static void criaArquivoPet(Scanner scanner, BufferedReader bfReader) throws IOException {

        Pet newPet = criandoPet(scanner, bfReader);
        String nomeArquivoPet = CriaTituloArquivo.criaNomeArquivoPet(newPet);

        try (BufferedWriter bfw = Files.newBufferedWriter(Paths.get("petsCadastrados/"+ nomeArquivoPet + ".txt"))){
            bfw.write("1 - " + ValidacoesHandler.validarValoresNulos(newPet.getNome_sobrenome(), ""));
            bfw.newLine();
            bfw.write("2 - " + newPet.getTipo().getAnimal());
            bfw.newLine();
            bfw.write("3 - " + newPet.getSexo().getSexo());
            bfw.newLine();
            bfw.write("4 - " + newPet.getEndereco().toString());
            bfw.newLine();
            bfw.write("5 - " + ValidacoesHandler.validarValoresNulos(newPet.getIdade().toString(), " anos"));
            bfw.newLine();
            bfw.write("6 - " + ValidacoesHandler.validarValoresNulos(newPet.getPeso().toString(), "kg"));
            bfw.newLine();
            bfw.write("7 - " + ValidacoesHandler.validarValoresNulos(newPet.getRaca(), ""));
            bfw.flush();
        }
        catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

    private static Pet criandoPet(Scanner scanner, BufferedReader bfReader) throws IOException {

        Pet pet = new Pet();

        System.out.println(bfReader.readLine());
        scanner.nextLine(); // consume a quebra de linha.
        String nomeSobrenome = scanner.nextLine();
        ValidacoesHandler.validarNomeSobrenome(nomeSobrenome);
        pet.setNome_sobrenome(CapitalizaPalavras.Capitalizador(nomeSobrenome));

        System.out.println(bfReader.readLine());
        pet.setTipo(ValidacaoEnum.validandoTipoEnum(scanner));

        System.out.println(bfReader.readLine());
        pet.setSexo(ValidacaoEnum.validandoSexoEnum(scanner));

        System.out.println(bfReader.readLine());
        pet.setEndereco(NovoEndereco.verificaEndereco(scanner));

        System.out.println(bfReader.readLine());
        Float idade = ValidacoesHandler.validarIdade(scanner.nextFloat());
        pet.setIdade(idade);

        System.out.println(bfReader.readLine());
        Float peso = ValidacoesHandler.validarPeso(scanner.nextFloat());
        pet.setPeso(peso);

        System.out.println(bfReader.readLine());
        scanner.nextLine(); // consume a quebra de linha.
        String raca = scanner.nextLine();
        ValidacoesHandler.contemApenasLetras(raca);
        pet.setRaca(CapitalizaPalavras.Capitalizador(raca));

        bfReader.close();
        return pet;
    }
}
