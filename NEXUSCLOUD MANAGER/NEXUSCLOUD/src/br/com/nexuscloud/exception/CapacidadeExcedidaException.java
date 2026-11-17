package br.com.nexuscloud.exception;

/**
 * Exceção disparada quando a memória RAM do servidor é excedida.
 * 
 * Comentário detalhado: Por herdar diretamente de Exception, trata-se de uma exceção do tipo
 * Checked, o que obriga o desenvolvedor a tratá-la via try-catch ou declará-la na assinatura[cite: 15].
 * RA: 25225024-2
 */
public class CapacidadeExcedidaException extends Exception {
    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}