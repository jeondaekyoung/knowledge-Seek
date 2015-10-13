package alanglang.naree.util.exception;

public class AlanglangWebException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5749807819968678797L;

	public AlanglangWebException(String message){
		super(message, null, false, false);
	}
	
	public AlanglangWebException(Exception exception){
		//super(exception.getClass().getName() + " : " + exception.getMessage(), null, false, false);
		super(exception.getMessage(), null, false, false);
		
	}
	
}
