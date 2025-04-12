package org.example.model;

public enum Tipo {
    Gato("Gato"),
    Cachorro("Cachorro");

    private String animal;

    Tipo(String animal) {
        this.animal = animal;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
