package org.example.service.cadastro;

import org.example.exception.ValidacoesHandler;
import org.example.model.Endereco;
import org.example.model.Pet;
import org.example.util.CapitalizaPalavras;
import org.example.util.CriaTituloArquivo;

import java.io.*;
import java.util.Scanner;

public class CadastrarPet {

    private static CapitalizaPalavras capitalizaPalavras = new CapitalizaPalavras();
    private static ValidacoesHandler validacoesHandler = new ValidacoesHandler();
    private static ValidacaoEnum validacaoEnum = new ValidacaoEnum();


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
        String nomeArquivoPet = CriaTituloArquivo.criaNomeArquivoPet(newPet);

        try (FileWriter fw = new FileWriter("petsCadastrados/"+ nomeArquivoPet + ".txt")) {

            BufferedWriter bfw = new BufferedWriter(fw);

            bfw.write("1 - " + ValidacoesHandler.validarValoresNulos(newPet.getNome_sobrenome()));
            bfw.newLine();
            bfw.write("2 - " + newPet.getTipo().getAnimal());
            bfw.newLine();
            bfw.write("3 - " + newPet.getSexo().getSexo());
            bfw.newLine();
            bfw.write("4 - " + newPet.getEndereco().toString());
            bfw.newLine();
            bfw.write("5 - " + ValidacoesHandler.validarValoresNulos(newPet.getIdade().toString()) + " anos");
            bfw.newLine();
            bfw.write("6 - " + ValidacoesHandler.validarValoresNulos(newPet.getPeso().toString()) + "kg");
            bfw.newLine();
            bfw.write("7 - " + ValidacoesHandler.validarValoresNulos(newPet.getRaca()));
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
        String nomeSobrenome = scanner.nextLine();
        validacoesHandler.validarNomeSobrenome(nomeSobrenome);
        pet.setNome_sobrenome(capitalizaPalavras.Capitalizador(nomeSobrenome));

        System.out.println(bfReader.readLine());
        pet.setTipo(validacaoEnum.validandoTipoEnum(scanner));

        System.out.println(bfReader.readLine());
        pet.setSexo(validacaoEnum.validandoSexoEnum(scanner));

        System.out.println(bfReader.readLine());
        pet.setEndereco(verificaEndereco(scanner));

        System.out.println(bfReader.readLine());
        Float idade = validacoesHandler.validarIdade(scanner.nextFloat());
        pet.setIdade(idade);

        System.out.println(bfReader.readLine());
        Float peso = validacoesHandler.validarPeso(scanner.nextFloat());
        pet.setPeso(peso);

        System.out.println(bfReader.readLine());
        scanner.nextLine(); // consume a quebra de linha.
        String raca = scanner.nextLine();
        validacoesHandler.contemApenasLetras(raca);
        pet.setRaca(capitalizaPalavras.Capitalizador(raca));

        bfReader.close();
        return pet;
    }

    private static Endereco verificaEndereco(Scanner scanner){

        System.out.print("Digite o Número da casa: ");
        int numCasa = scanner.nextInt();
        scanner.nextLine(); // consume a quebra de linha.
        System.out.print("Digite a Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite a Rua: ");
        String rua = scanner.nextLine();

        return new Endereco(
                numCasa,
                capitalizaPalavras.Capitalizador(cidade),
                capitalizaPalavras.Capitalizador(rua));
    }
}
