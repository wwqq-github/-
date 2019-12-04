package cn.tedu.service.exception;

public class SQLException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2882737604553411648L;

	public SQLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SQLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SQLException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SQLException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SQLException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
