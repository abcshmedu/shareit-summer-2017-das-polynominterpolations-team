package edu.hm.logic;

public enum MediaServiceResult {
	OK(200), FAIL(300), TEAPOT(418), NOOB(1338);
	
	private int errorCode;
	
	MediaServiceResult(int errorCode){
		this.errorCode = errorCode;
	}
	
	public int getCode(){
		return errorCode;
	}
}
