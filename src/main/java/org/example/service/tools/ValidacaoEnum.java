package org.example.service.tools;

import org.example.model.Sexo;
import org.example.model.Tipo;
import java.util.Scanner;

import static org.example.model.Tipo.Cachorro;
import static org.example.model.Tipo.Gato;

public class ValidacaoEnum {

     public static Tipo validandoTipoEnum(Scanner scanner) {
        while (true){
            String tipo = scanner.next().toUpperCase();

            if (tipo.equals("CACHORRO"))
                return Cachorro;
            else if (tipo.equals("GATO"))
                return Gato;
            else
                System.err.println("Esse animal não é uma opção.\n" +
                        "Escolha entre Cachorro ou Gato!");

        }
    }

    public static Sexo validandoSexoEnum(Scanner scanner) {
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
}
