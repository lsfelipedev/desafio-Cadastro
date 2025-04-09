package org.example.util;

import java.text.Normalizer;

public class RemovedorAcentos {

    public static String tirarAcentosPalavras(String palavras){

        palavras = Normalizer.normalize(palavras, Normalizer.Form.NFD);
        palavras = palavras.replaceAll("[^\\p{ASCII}]", "");
        return palavras;
    }
}
