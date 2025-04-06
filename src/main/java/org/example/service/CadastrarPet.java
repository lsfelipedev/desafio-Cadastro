package org.example.service;

import org.example.model.Endereco;
import org.example.model.Pet;
import org.example.model.Sexo;
import org.example.model.Tipo;
import org.example.util.CapitalizaPalavras;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.example.model.Tipo.*;

public class CadastrarPet {

    private static CapitalizaPalavras capitalizaPalavras = new CapitalizaPalavras();

    public static void sistemaDeCadastro(Scanner scanner, File file) {

        try (FileReader fileReader = new FileReader(file)) {
            criaArquivoPet(scanner, fileReader);
            System.out.println("Cadastro do Pet foi Registrado com Sucesso!");

        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }

    public static void criaArquivoPet(Scanner scanner, FileReader fileReader) throws IOException {

        Pet newPet = criandoPet(scanner, fileReader);
        String nomeArquivoPet = criaNomeArquivoPet(newPet);

        try (FileWriter fw = new FileWriter(nomeArquivoPet + ".txt")) {

            BufferedWriter bfw = new BufferedWriter(fw);

            bfw.write("1 - " + newPet.getNome_sobrenome());
            bfw.newLine();
            bfw.write("2 - " + newPet.getTipo().getAnimal());
            bfw.newLine();
            bfw.write("3 - " + newPet.getSexo().getSexo());
            bfw.newLine();
            bfw.write("4 - " + newPet.getEndereco().toString());
            bfw.newLine();
            bfw.write("5 - " + newPet.getIdade().toString() + " anos");
            bfw.newLine();
            bfw.write("6 - " + newPet.getPeso().toString() + "kg");
            bfw.newLine();
            bfw.write("7 - " + newPet.getRaca());
            bfw.flush();
            bfw.close();
        }
        catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

    private static Pet criandoPet(Scanner scanner, FileReader fileReader) throws IOException {

        Pet pet = new Pet();

        BufferedReader bfReader = new BufferedReader(fileReader);

        System.out.println(bfReader.readLine());
        scanner.nextLine(); // consume a quebra de linha.
        pet.setNome_sobrenome(capitalizaPalavras.Capitalizador(scanner.nextLine()));

        System.out.println(bfReader.readLine());
        pet.setTipo(validandoTipoEnum(scanner));

        System.out.println(bfReader.readLine());
        pet.setSexo(validandoSexoEnum(scanner));

        System.out.println(bfReader.readLine());
        pet.setEndereco(verificaEndereco(scanner));

        System.out.println(bfReader.readLine());
        pet.setIdade(scanner.nextFloat());

        System.out.println(bfReader.readLine());
        pet.setPeso(scanner.nextFloat());

        System.out.println(bfReader.readLine());
        scanner.nextLine(); // consume a quebra de linha.
        pet.setRaca(capitalizaPalavras.Capitalizador(scanner.nextLine()));

        bfReader.close();
        return pet;
    }

    private static String criaNomeArquivoPet(Pet pet){

        LocalDateTime localDateTime = LocalDateTime.now();

        return "" +
                localDateTime.getYear() +
                localDateTime.getDayOfMonth() +
                localDateTime.getDayOfMonth() +
                "T" +
                localDateTime.getHour() +
                localDateTime.getMinute() +
                "-" +
                pet.getNome_sobrenome().toUpperCase().replaceAll(" ", "");
    }

    private static Sexo validandoSexoEnum(Scanner scanner) {
        while (true){
            String sexo = scanner.next().toUpperCase();

            if (sexo.equals("MACHO"))
                return Sexo.Macho;
            else if (sexo.equals("GATO"))
                return Sexo.Femea;
            else
                System.err.println("Esse sexo não é uma opção.\nEscolha entre Macho ou Femea!");
        }
    }

    private static Tipo validandoTipoEnum(Scanner scanner) {
        while (true){
            String tipo = scanner.next().toUpperCase();

            if (tipo.equals("CACHORRO"))
                return Cachorro;
            else if (tipo.equals("GATO"))
                return Gato;
            else
                System.err.println("Esse animal não é uma opção.\nEscolha entre Cachorro ou Gato!");

        }
    }

    private static Endereco verificaEndereco(Scanner scanner){

        System.out.print("Digite o Número da casa: ");
        int numCasa = scanner.nextInt();
        scanner.nextLine(); // consume a quebra de linha.
        System.out.print("Digite a Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite a Rua: ");
        String rua = scanner.nextLine();

        return new Endereco(numCasa, capitalizaPalavras.Capitalizador(cidade), capitalizaPalavras.Capitalizador(rua));
    }
}
