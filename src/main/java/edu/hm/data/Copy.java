package edu.hm.data;

public class Copy {
	private Medium medium;
	private String owner;
	
	public Copy(String owner, Medium medium){
		this.medium = medium;
		this.owner = owner;
	}

	/**
	 * @return the medium
	 */
	public Medium getMedium() {
		return medium;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
}
