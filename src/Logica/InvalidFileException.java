package Logica;
/**
 * Modela la excepción producida por un archivo inválido.
 */
public class InvalidFileException extends Exception{
	/**
	 * Inicializa la excepcion indicando el origen de error.
	 * @param msg - Mensaje a mostrar al producir la excepcion.
	 */
	public InvalidFileException(String msg) {
		super(msg);
	}
}
