package br.com.nexuscloud.model;

import java.util.Objects;

/**
 * Representa uma aplicação (container) alocada em um servidor.
 * 
 * Comentário detalhado: A identidade do container é regida pelo seu identificador único (id).
 * Sobrescrevemos equals() e hashCode() baseados no ID para evitar duplicidades lógicas[cite: 14].
 * RA: 25225024-2
 */
public class Container {
    private String id;
    private String nomeApp;
    private double ramNecessaria;

    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId() {
        return id;
    }

    public String getNomeApp() {
        return nomeApp;
    }

    public double getRamNecessaria() {
        return ramNecessaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(id, container.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}