package br.com.nexuscloud.model;

import java.util.Objects;

/**
 * Representa um cliente da NexusCloud.
 * 
 * Comentário detalhado: A regra de identidade absoluta determina que dois clientes
 * são considerados iguais se possuem o mesmo CPF. Para garantir isso em coleções baseadas
 * em hash, sobrescrevemos os métodos equals() e hashCode() utilizando exclusivamente o campo cpf[cite: 14].
 * RA: 25225024-2
 */
public class Cliente {
    private String cpf;
    private String nome;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}