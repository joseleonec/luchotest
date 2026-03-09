package co.com.sofka.luchotest.domain.exception;

/**
 * Domain exception thrown when an account has insufficient balance for a transaction.
 */
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
