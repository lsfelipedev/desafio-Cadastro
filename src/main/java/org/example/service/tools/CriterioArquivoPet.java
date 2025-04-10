package org.example.service.tools;

import org.example.model.Tipo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CriterioArquivoPet {

    public static List<Path> arquivosFiltradoFormatado(Tipo tipo, Map<String, String> palavrasPesquisar) throws IOException {
        Path pasta = Paths.get("petsCadastrados");
        DirectoryStream<Path> paths = Files.newDirectoryStream(pasta, "*.txt");
        List<Path> filesSelecionados = new ArrayList<>();
        AtomicInteger num = new AtomicInteger(1);

        for (Path file : paths) {

            Path arquivoBruto = BuscadorPet.arquivosSelecionados(tipo, file, palavrasPesquisar);
            if(arquivoBruto != null)
                filesSelecionados.add(arquivoBruto);
        }
        paths.close();

        if(filesSelecionados.size() > 1)
            filesSelecionados.stream().filter(Objects::nonNull)
                    .forEach(s-> {
                        System.out.print(num.getAndIncrement() + ".");
                        BuscadorPet.lerArquivo(s);
                        System.out.println();
                    });

        return filesSelecionados;
    }
}
