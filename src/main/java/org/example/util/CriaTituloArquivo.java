package org.example.util;

import org.example.model.Pet;
import java.time.LocalDateTime;

public class CriaTituloArquivo {

    public static String criaNomeArquivoPet(Pet pet){

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
}
