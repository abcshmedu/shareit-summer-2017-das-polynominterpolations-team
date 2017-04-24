package edu.hm.logic;

public enum MediaServiceResult {
	OK, FAIL, NOOB, TEAPOT;
	
	public int getCode(){
		if(this == OK){
			return 201;
		}
		return 0;
	}
}
