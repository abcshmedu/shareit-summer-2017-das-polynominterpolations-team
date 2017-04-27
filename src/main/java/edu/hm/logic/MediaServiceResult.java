package edu.hm.logic;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Dies ist unsere Implementierung des MediaServiceResult-Enums.
 * @author Sebastian Becker
 * @author Peter Straßer
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MediaServiceResult {
	OK(200), FAIL(300), TEAPOT(418), NOOB(1338);
	
	/** Diese Variable enthält den Error-Code jedes MediaServiceResult-Objekts.	 */
	private int errorCode;
	
	/** Diese Variable enthält zusätzliche Informationen bezüglich des Error-Codes.	 */
	private String detail;
	
	/**
	 * Default Ctor des MediaServiceResult-Enums.
	 * @param errorCode
	 */
	MediaServiceResult(int errorCode){
		this.errorCode = errorCode;
		this.detail = "";
	}
	
	/**
	 * Diese Methode ermöglicht es dem Enum eine Error-Message mitzugeben.
	 * @param message
	 */
	public void setDetail(String message){
		this.detail = message;
	}
	
	/**
	 * Getter für den Error-Code.
	 * @return Liefert den Error-Code zurück
	 */
	public int getCode(){
		return errorCode;
	}
	
	/**
	 * Getter für die Error-Message.
	 * @return Liefert die Error-Message zurück
	 */
	public String getDetail(){
		return detail;
	}
}
