package org.example.service.alteracao;

import org.example.exception.ValidacoesHandler;
import org.example.model.Endereco;
import org.example.model.Pet;
import org.example.model.Tipo;
import org.example.util.CapitalizaPalavras;
import org.example.util.CriaTituloArquivo;
import org.example.util.NovoEndereco;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AlterarDadosPet {

    private static CapitalizaPalavras capitalizaPalavras = new CapitalizaPalavras();
    private static ValidacoesHandler validacoesHandler = new ValidacoesHandler();

    private static CriterioBuscaPet criterioBuscaPet = new CriterioBuscaPet();
    private static BuscadorPet buscadorPet = new BuscadorPet();

    public static void sistemaAlteracao(Scanner scanner){
        Tipo tipo = criterioBuscaPet.selecioneTipoAnimal(scanner);
        int[] opcoes = {criterioBuscaPet.primeiroCriterio(scanner),
                criterioBuscaPet.segundoCriterio(scanner)};

        Map<String, String> palavrasPesquisar = buscadorPet.pesquisarPalavra(opcoes, scanner);

        List<File> files = arquivosFiltradoFormatado(tipo, palavrasPesquisar);

        alterarDadosPet(files, scanner);
    }

    private static List<File> arquivosFiltradoFormatado(Tipo tipo, Map<String, String> palavrasPesquisar){
        File pasta = new File("petsCadastrados");
        File[] files = pasta.listFiles((dir, nome) -> nome.endsWith(".txt"));
        List<File> filesSelecionados = new ArrayList<>();
        AtomicInteger num = new AtomicInteger(1);

        for (File file : files) {

            File arquivoBruto = buscadorPet.arquivosSelecionados(tipo, file, palavrasPesquisar);
            if(arquivoBruto != null)
                filesSelecionados.add(arquivoBruto);
        }

        if(filesSelecionados.size() > 1)
            filesSelecionados.stream().filter(Objects::nonNull)
                    .forEach(s-> {
                        System.out.print(num.getAndIncrement() + ".");
                        AlterarDadosPet.buscadorPet.lerArquivo(s);
                        System.out.println();
                    });

        return filesSelecionados;
    }

    private static void alterarDadosPet(List<File> files, Scanner scanner){

        int opcaoArquivo = 1;

        if(files.size() > 1){
            System.out.println("Seleciona o numero do arquivo que deseja alterar??");
            opcaoArquivo = scanner.nextInt();
        }

        File arquivoParaModificar = files.get(opcaoArquivo-1);

        List<String> linhaLista = lerArquivoPadrao(arquivoParaModificar);
        selecionaLinhaDados(arquivoParaModificar, scanner, linhaLista);
    }


    private static List<String> lerArquivoPadrao(File file){

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
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

    private static void selecionaLinhaDados(File file, Scanner scanner, List<String> linhaLista){

        System.out.print("Seleciona o numero que quer alterar (1 a 5): ");
        int opcao = scanner.nextInt();

        String linhaParaAlterar= "", novaFrase = "";
        int num = 0;

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

    private static void renomearArquivo(File arquivo, String nome){

        Pet pet = new Pet();
        pet.setNome_sobrenome(nome);

        File tituloNovo = new File("petsCadastrados/"+ CriaTituloArquivo.criaNomeArquivoPet(pet) + ".txt");
        if(arquivo.renameTo(tituloNovo)) {
            System.out.println("Arquivo renomeado com o novo Nome e Sobrenome!");
        }
        else {
            System.err.println("Falha ao renomear. Verifique se não há arquivo com o novo nome");
        }
    }

    private static void alteraLinhaDados(File file,
                                         String linhaParaAlterar,
                                         String novaFrase){

        try {
            Path path = file.toPath();
            String conteudo = new String(Files.readAllBytes(path));
            String conteudoEditado = conteudo.replace(linhaParaAlterar, novaFrase);
            Files.write(path, conteudoEditado.getBytes());

        }
        catch (IOException e){
            throw new RuntimeException("falha ao fazer alteração! \nErro: " + e.getMessage());
        }
    }
}
