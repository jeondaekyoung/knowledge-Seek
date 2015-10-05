package gakkoga.naree.util.exception;

public class GakkoGaWebException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5749807819968678797L;

	public GakkoGaWebException(String message){
		super(message, null, false, false);
	}
	
	public GakkoGaWebException(Exception exception){
		//super(exception.getClass().getName() + " : " + exception.getMessage(), null, false, false);
		super(exception.getMessage(), null, false, false);
		
	}
	
}
