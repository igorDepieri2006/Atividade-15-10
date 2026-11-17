package br.com.nexuscloud.model;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.exception.ServidorOfflineException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa um servidor de infraestrutura em nuvem.
 * 
 * Comentário detalhado: Gerencia a lista de containers e aplica validações de Fail-Fast
 * (status ligado e capacidade de RAM). Utiliza a Streams API para calcular a RAM em uso[cite: 15].
 * RA: 25225024-2
 */
public class Servidor {
    private String ip;
    private double ramTotal;
    private double precoPorHora;
    private boolean statusLigado;
    private List<Container> containers;

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false; // Nasce desligado conforme o roteiro[cite: 15, 16]
        this.containers = new ArrayList<>(); // Inicializado para evitar NullPointerException[cite: 15]
    }

    public void ligar() {
        this.statusLigado = true;
    }

    public void desligar() {
        this.statusLigado = false;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    /**
     * Calcula a RAM total utilizada atualmente somando os containers através da Streams API[cite: 15].
     */
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    /**
     * Aloca um container aplicando as políticas de Fail-Fast (Unchecked e Checked)[cite: 15].
     */
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        // 1. Regra de Status (Unchecked)[cite: 15]
        if (!statusLigado) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }

        // 2. Regra de Capacidade (Checked)[cite: 15]
        double ramEmUsoAtual = calcularRamEmUso();
        if ((ramEmUsoAtual + c.getRamNecessaria()) > ramTotal) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }

        containers.add(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servidor servidor = (Servidor) o;
        return Objects.equals(ip, servidor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}