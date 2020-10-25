package Logica;

/**
 * Modela una celda del juego sudoku.
 * 
 * @author Agustin Escobar.
 *
 */
public class Celda {
	private Integer valor; // Indica el indice del arreglo de imagenes.
	private Entidad_celda entidadGrafica;
	private int fila;
	private int columna;
	private int cuadrante;
	private boolean habilitada; // Habilitadas son las que no te establece por default el juego.
	private boolean invalida; // Es una celda erronea.

	public Celda() {
		this.valor = null;
		this.entidadGrafica = new Entidad_celda();
		fila = 0;
		columna = 0;
		cuadrante = 0;
		habilitada = true;
		invalida = false;
	}

	/**
	 * Establece la imagen a la celda que corresponde con la opcion seleccionada.
	 * 
	 * @param valor_numero Valor del boton, indica el indice del arreglo de
	 *                     imagenes.
	 */
	public void actualizar(int valor_numero) {
		Integer v = valor_numero;
		if (habilitada) {
			valor = v;
			entidadGrafica.actualizar(valor); // Actualiza la imagen de la entidad grafica, con el valor (indice)
			// de arreglo de imagenes.
		}
	}

	/**
	 * Obtiene la cantidad de imagenes que tiene la entidad grafica.
	 * 
	 * @return cantidad de imagenes.
	 */
	public int get_cant_elementos() {
		return this.entidadGrafica.get_imagenes().length;
	}

	/**
	 * Obtiene el valor de la celda.
	 * 
	 * @return valor de la celda.
	 */
	public Integer get_valor() {
		return this.valor;
	}

	/**
	 * Establece el valor de la celda.
	 * 
	 * @param valor a establecer.
	 */
	public void set_valor(Integer valor) {
		if (valor != null && valor < this.get_cant_elementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		} else {
			this.valor = null;
		}
	}

	/**
	 * Obtiene la entidad grafica celda.
	 * 
	 * @return
	 */
	public Entidad_celda get_entidad_grafica() {
		return this.entidadGrafica;
	}

	/**
	 * Establece la entidad grafica celda.
	 * 
	 * @param g
	 */
	public void set_grafica(Entidad_celda g) {
		this.entidadGrafica = g;
	}

	/**
	 * Obtiene la fila en la que se encuentra la celda.
	 * 
	 * @return
	 */
	public int get_fila() {
		return fila;
	}

	/**
	 * Establece la fila de la celda.
	 * 
	 * @param f
	 */
	public void set_fila(int f) {
		fila = f;
	}

	/**
	 * Obtiene la columna en la que se encuentra la celda.
	 * 
	 * @return
	 */
	public int get_columna() {
		return columna;
	}

	/**
	 * Establece la columna de la celda.
	 * 
	 * @param c
	 */
	public void set_columna(int c) {
		columna = c;
	}

	/**
	 * Obtiene el cuadrante donde se encuentra la celda.
	 * 
	 * @return
	 */
	public int get_cuadrante() {
		return cuadrante;
	}

	/**
	 * Establece el cuadrante de la celda.
	 * 
	 * @param cuadrante
	 */
	public void set_cuadrante(int cuadrante) {
		this.cuadrante = cuadrante;
	}

	/**
	 * Establece la celda en habilitada. (Puede ser modificada)
	 * 
	 * @param es
	 */
	public void set_habilitada(boolean es) {
		habilitada = es;
	}

	/**
	 * Consulta si la celda esta habilitada. Esto es, la celda no fue establecida
	 * por default en el juego. Por lo tanto puede ser modificada.
	 * 
	 * @return Verdadero si la celda esta habilitada, falso en caso contrario.
	 */
	public boolean get_habilitada() {
		return habilitada;
	}

	/**
	 * Establece la celda como invalida. (Con error)
	 * 
	 * @param es
	 */
	public void set_invalida(boolean es) {
		invalida = es;
	}

	/**
	 * Consulta si la celda es invalida (con error). 
	 * 
	 * @return Verdadero si la celda es invalida, falso en caso contrario.
	 */
	public boolean get_invalida() {
		return invalida;
	}

}
