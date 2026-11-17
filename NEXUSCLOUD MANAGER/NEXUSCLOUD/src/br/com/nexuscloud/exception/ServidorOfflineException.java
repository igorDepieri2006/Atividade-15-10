package br.com.nexuscloud.exception;

/**
 * Exceção disparada quando se tenta alocar um container em um servidor desligado.
 * 
 * Comentário detalhado: Por herdar de RuntimeException, trata-se de uma exceção do tipo
 * Unchecked, onde o compilador não obriga o tratamento imediato com try-catch[cite: 15].
 * RA: 25225024-2
 */
public class ServidorOfflineException extends RuntimeException {
    public ServidorOfflineException(String msg) {
        super(msg);
    }
}