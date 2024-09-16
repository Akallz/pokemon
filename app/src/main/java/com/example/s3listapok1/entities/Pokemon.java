package com.example.s3listapok1.entities;

public class Pokemon {
    public int id;
    public String name;
    public String habilidad;

    public Pokemon(String name, String type) {
        this.name = name;
        this.habilidad = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }
}
