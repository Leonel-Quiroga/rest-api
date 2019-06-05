package ar.edu.unnoba.exceptions;

public class EntityNotFoundException extends Exception {
	
	private static final long serialVersionUID = 8158009188549279139L;

	public EntityNotFoundException(String mensaje) {
		super(mensaje);
	}

}
