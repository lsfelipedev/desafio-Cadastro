package org.example.service.alteracao;

import org.example.model.Tipo;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AlterarDadosPet {

    private static CriterioBuscaPet criterioBuscaPet = new CriterioBuscaPet();
    private static BuscadorPet buscadorPet = new BuscadorPet();

    public static void atualizaDadosPet(Scanner scanner) {
        Tipo tipo = criterioBuscaPet.selecioneTipoAnimal(scanner);
        int[] opcoes = {criterioBuscaPet.primeiroCriterio(scanner), criterioBuscaPet.segundoCriterio(scanner)};

        Map<String, String> palavrasPesquisar = buscadorPet.pesquisarPalavra(opcoes, scanner);

        File pasta = new File("petsCadastrados");

        File[] files = pasta.listFiles((dir, nome) -> nome.endsWith(".txt"));

        List<File> filesSelecionados = new ArrayList<>();
        for (File file : files) {
            filesSelecionados.add(buscadorPet.arquivosSelecionados(tipo, file, palavrasPesquisar));
        }

        AtomicInteger num = new AtomicInteger(1);

        filesSelecionados.stream().filter(Objects::nonNull)
                .forEach(s-> {
                    System.out.print(num.getAndIncrement() + ".");
                    AlterarDadosPet.buscadorPet.lerArquivo(s);
                    System.out.println();
                });
    }

}
