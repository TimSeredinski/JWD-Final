package by.training.epam.seredinski.exception;

public class NotDBDriverException extends RuntimeException{
	private static final long serialVersionUID = -7223276245503461734L;

	public NotDBDriverException() {
		super();
	}

	public NotDBDriverException(String message) {
		super(message);
	}

	public NotDBDriverException(Exception e) {
		super(e);
	}

	public NotDBDriverException(String message, Exception e) {
		super(message, e);
	}
}
