package org.example.service;

import org.example.model.Tipo;
import org.example.util.CriterioBuscaPet;

import java.util.Scanner;

public class AlterarDadosPet {

    private static CriterioBuscaPet criterioBuscaPet = new CriterioBuscaPet();

    public static void atualizaDadosPet(Scanner scanner) {
        Tipo tipo = criterioBuscaPet.selecioneTipoAnimal(scanner);
        int valor01 = criterioBuscaPet.primeiroCriterio(scanner);
        int valor02 = criterioBuscaPet.segundoCriterio(scanner);

        System.out.println(tipo);
        System.out.println(valor01);
        System.out.println(valor02);
    }
}
