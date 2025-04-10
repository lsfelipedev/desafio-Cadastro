package org.example.service.alteracao;

import org.example.exception.ValidacoesHandler;
import org.example.model.Endereco;
import org.example.model.Pet;
import org.example.model.Tipo;
import org.example.util.CapitalizaPalavras;
import org.example.util.CriaTituloArquivo;
import org.example.util.NovoEndereco;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AlterarDadosPet {

    private static ValidacoesHandler validacoesHandler = new ValidacoesHandler();
    private static CriterioBuscaPet criterioBuscaPet = new CriterioBuscaPet();
    private static BuscadorPet buscadorPet = new BuscadorPet();


    public static void sistemaAlteracao(Scanner scanner){
       try {

        Tipo tipo = criterioBuscaPet.selecioneTipoAnimal(scanner);
        int[] opcoes = {criterioBuscaPet.primeiroCriterio(scanner),
                criterioBuscaPet.segundoCriterio(scanner)};

        Map<String, String> palavrasPesquisar = buscadorPet.pesquisarPalavra(opcoes, scanner);

        List<Path> files = arquivosFiltradoFormatado(tipo, palavrasPesquisar);

        alterarDadosPet(files, scanner);

       }
       catch (IOException e){
           throw new RuntimeException("Fala ao alterar o arquivo. \nErro: " + e.getMessage());
       }
    }

    private static List<Path> arquivosFiltradoFormatado(Tipo tipo, Map<String, String> palavrasPesquisar) throws IOException {
        Path pasta = Paths.get("petsCadastrados");
        DirectoryStream<Path> paths = Files.newDirectoryStream(pasta, "*.txt");
        List<Path> filesSelecionados = new ArrayList<>();
        AtomicInteger num = new AtomicInteger(1);

        for (Path file : paths) {

            Path arquivoBruto = buscadorPet.arquivosSelecionados(tipo, file, palavrasPesquisar);
            if(arquivoBruto != null)
                filesSelecionados.add(arquivoBruto);
        }
        paths.close();

        if(filesSelecionados.size() > 1)
            filesSelecionados.stream().filter(Objects::nonNull)
                    .forEach(s-> {
                        System.out.print(num.getAndIncrement() + ".");
                        AlterarDadosPet.buscadorPet.lerArquivo(s);
                        System.out.println();
                    });

        return filesSelecionados;
    }

    private static void alterarDadosPet(List<Path> files, Scanner scanner){

        int opcaoArquivo = 1;

        if(files.size() > 1){
            System.out.println("Seleciona o numero do arquivo que deseja alterar??");
            opcaoArquivo = scanner.nextInt();
        }

        Path arquivoParaModificar = files.get(opcaoArquivo-1);

        List<String> linhaLista = lerArquivoPadrao(arquivoParaModificar);
        selecionaLinhaDados(arquivoParaModificar, scanner, linhaLista);
    }


    private static List<String> lerArquivoPadrao(Path file){

        try (BufferedReader br = Files.newBufferedReader(file)){
            String linha;
            List<String> linhaLista = new ArrayList<>();
            int count = 1;
            while ((linha = br.readLine()) != null){

                if(linha.startsWith("2") || linha.startsWith("3"))
                    continue;
                linhaLista.add(linha);
                linha = linha.substring(1);
                System.out.print(count++);
                System.out.println(linha);
            }
            return linhaLista;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void selecionaLinhaDados(Path file, Scanner scanner, List<String> linhaLista){

        System.out.print("Seleciona o numero que quer alterar (1 a 5): ");
        int opcao = scanner.nextInt();

        String linhaParaAlterar= "", novaFrase = "";

        Pet pet = new Pet();

        switch (opcao){
            case 1:
                scanner.nextLine(); // Quebra de Linha
                linhaParaAlterar = linhaLista.getFirst();
                System.out.print("Novo Nome e Sobrenome: ");

                novaFrase = scanner.nextLine();
                validacoesHandler.contemApenasLetras(novaFrase);
                validacoesHandler.validarNomeSobrenome(novaFrase);

                novaFrase = ValidacoesHandler.validarValoresNulos(novaFrase, "");

                novaFrase = "1 - " + CapitalizaPalavras.Capitalizador(novaFrase);
                alteraLinhaDados(file, linhaParaAlterar, novaFrase);
                renomearArquivo(file, novaFrase);
                break;

            case 2:
                linhaParaAlterar = linhaLista.get(1);
                Endereco endereco = NovoEndereco.verificaEndereco(scanner);
                novaFrase ="4 - " + endereco.toString();

                alteraLinhaDados(file, linhaParaAlterar, novaFrase);
                break;

            case 3:
                System.out.print("Nova Idade: ");
                linhaParaAlterar = linhaLista.get(2);
                pet.setIdade(validacoesHandler.validarIdade(scanner.nextFloat()));

                novaFrase = ValidacoesHandler.validarValoresNulos(pet.getIdade().toString(), " anos");

                novaFrase = "5 - " + novaFrase;
                alteraLinhaDados(file, linhaParaAlterar, novaFrase);
                break;

            case 4:
                linhaParaAlterar = linhaLista.get(3);
                break;

            case 5:
                linhaParaAlterar = linhaLista.get(4);
                break;

            default:
                System.err.println("Opção Invalida!! Escolha entre 1 a 5.");
        }
    }

    private static void renomearArquivo(Path arquivo, String nome){

        try{
        Pet pet = new Pet();
        pet.setNome_sobrenome(nome);

        Path tituloNovo = Paths.get("petsCadastrados/"+ CriaTituloArquivo.criaNomeArquivoPet(pet) + ".txt");
        Path path = Files.move(arquivo, tituloNovo);
        System.out.println("Arquivo renomeado para: " + path);
        }
        catch (IOException e){
            throw new RuntimeException("Erro ao renomear: " + e.getMessage());
        }
    }

    private static void alteraLinhaDados(Path file,
                                         String linhaParaAlterar,
                                         String novaFrase){

        try {
            String conteudo = new String(Files.readAllBytes(file));
            String conteudoEditado = conteudo.replace(linhaParaAlterar, novaFrase);
            Files.write(file, conteudoEditado.getBytes());

        }
        catch (IOException e){
            throw new RuntimeException("falha ao fazer alteração! \nErro: " + e.getMessage());
        }
    }
}
