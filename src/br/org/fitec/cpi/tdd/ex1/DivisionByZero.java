package br.org.fitec.cpi.tdd.ex1;

public class DivisionByZero extends RuntimeException {

	private static final long serialVersionUID = -1085582280603884689L;

	public DivisionByZero() {
	}

	public DivisionByZero(String message) {
		super(message);
	}

	public DivisionByZero(Throwable cause) {
		super(cause);
	}

	public DivisionByZero(String message, Throwable cause) {
		super(message, cause);
	}

	public DivisionByZero(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
