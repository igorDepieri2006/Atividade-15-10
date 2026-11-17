package br.com.nexuscloud.service;

import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Servidor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Gerenciador principal de clientes e servidores da NexusCloud.
 * 
 * Comentário detalhado: Utiliza um Map para relacionar Clientes a um Conjunto (Set) de Servidores
 * sem duplicações. Utiliza Streams API para calcular a fatura ativa dos servidores ligados[cite: 15].
 * RA: 25225024-2
 */
public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados;

    public CloudManager() {
        this.bancoDeDados = new HashMap<>();
    }

    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    public void provisionarServidor(Cliente c, Servidor s) {
        bancoDeDados.computeIfAbsent(c, k -> new HashSet<>()).add(s);
    }

    /**
     * Calcula a fatura ativa de um cliente somando o preço por hora apenas dos servidores ligados[cite: 15].
     */
    public double calcularFaturaAtiva(Cliente c) {
        Set<Servidor> servidores = bancoDeDados.get(c);
        if (servidores == null) {
            return 0.0;
        }

        return servidores.stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}