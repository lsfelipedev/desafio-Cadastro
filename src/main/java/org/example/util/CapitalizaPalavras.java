package org.example.util;

import java.util.regex.Pattern;

public class CapitalizaPalavras {

    public static String Capitalizador(String texto) {
        return texto == null ? null :
                Pattern.compile("(\\b\\p{Ll})(\\p{L}*)")
                        .matcher(texto.toLowerCase())
                        .replaceAll(mr -> mr.group(1).toUpperCase() + mr.group(2));
    }
}
