package br.com.nexuscloud.main;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;
import br.com.nexuscloud.service.CloudManager;

/**
 * Campo de testes oficial (MainCloud) estipulado pelo briefing[cite: 15, 16].
 * 
 * Comentário detalhado: Executa o roteiro passo a passo validando exceções unchecked,
 * checked, controle de estado e faturamento via Streams[cite: 15, 16].
 * RA: 25225024-2
 */
public class MainCloud {
    public static void main(String[] args) {
        // 1. Instancie o CloudManager[cite: 15].
        CloudManager manager = new CloudManager();

        // 2. Instancie 1 Cliente[cite: 15].
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");

        // 3. Instancie 1 Servidor (IP "192.168.0.1", RAM Total 16.0 GB, Preço R$ 5.50)[cite: 15].
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // 4. Registre o cliente no banco de dados do Manager e adicione o servidor a ele[cite: 15].
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        // 5. Instancie 2 Containers diferentes (C-01 com 10.0 RAM e C-02 com 8.0 RAM)[cite: 15, 16].
        Container c1 = new Container("C-01", "AppWeb", 10.0);
        Container c2 = new Container("C-02", "BancoDeDados", 8.0);

        /*
         * 6. Teste Falha Unchecked: Tente alocar o Container 1 no Servidor (desligado)[cite: 16].
         * Deixamos estourar a exceção sem try-catch para exibir a mensagem vermelha no console[cite: 16].
         * (Após testar, comente este bloco para seguir para o passo 7, conforme exigido no briefing[cite: 16]).
         */
        // servidor.alocarContainer(c1);

        // 7. Siga em frente: Chame o método ligar() do servidor[cite: 16].
        servidor.ligar();

        // 8. Alocou com sucesso: Aloque o Container 1 no Servidor[cite: 16].
        try {
            servidor.alocarContainer(c1);
            System.out.println("Sucesso: Container 1 alocado com sucesso!");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        // 9. Teste Falha Checked: Tente alocar o Container 2 dentro de try-catch[cite: 16].
        try {
            servidor.alocarContainer(c2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Erro capturado com elegância: " + e.getMessage());
        }

        // 10. Imprima no console o valor retornado por calcularFaturaAtiva (Deve ser 5.50)[cite: 16].
        double fatura = manager.calcularFaturaAtiva(cliente);
        System.out.println("Fatura Ativa do Cliente: R$ " + fatura);
    }
}