package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Modela el juego sudoku.
 * 
 * @author Agustin Escobar.
 */
public class Juego {

	private Celda[][] tablero;
	private Celda[] opciones;
	private int[][] matriz_archivo;
	private Celda celda_presionada;
	private boolean continua_juego; // Indica si puede continuar jugando.

	public Juego() throws InvalidFileException {

		tablero = new Celda[9][9];
		matriz_archivo = new int[9][9];
		String ruta = "File/archivo.txt";

		// Inicializo el tablero de celdas con los valores de la matriz del archivo.
		if (validar_archivo(ruta)) {

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Random rand = new Random();
					int value = rand.nextInt(2); 
					tablero[i][j] = new Celda();
					// Seteo a cada celda su fila y columna en el tablero.
					tablero[i][j].set_fila(i);
					tablero[i][j].set_columna(j);
					setear_cuadrante(i, j, tablero[i][j]);
					if (value == 0) { // De acuerdo a value decidir si asignar un valor o no
						tablero[i][j].set_valor(9);
					} else {
						tablero[i][j].set_valor(matriz_archivo[i][j] - 1);
						tablero[i][j].set_habilitada(false);
					}
				}
			}
			// A cada boton del panel de opciones, le establezco una celda con su imagen
			// correspondiente.
			opciones = new Celda[10];
			for (int i = 0; i < 10; i++) {
				opciones[i] = new Celda();
				opciones[i].set_valor(i);
			}
			continua_juego = true;
		} else {
			throw new InvalidFileException("Archivo invalido. ");
		}
	}

	/**
	 * Consulta si el tablero esta completo y es correcto para indicar si termino el juego.
	 * 
	 * @return Verdadero si el tablero esta completo y la solucion es valida, falso
	 *         en caso contrario.
	 */
	public boolean termino() {
		// Recorro el tablero consultando si alguna celda es nula.
		// Si encuentra una celda nula, no termino el juego.
		boolean esta = true;
		for (int i = 0; i < 9 && esta; i++) {
			for (int j = 0; j < 9 && esta; j++) {
				// Si la celda tiene una imagen "nula".
				if (tablero[i][j].get_valor() == 9) {
					esta = false;
				}
			}
		}
		return esta;
	}

	/**
	 * Actualiza la celda presionada.
	 * 
	 * @param c     Celda a actualizar.
	 * @param valor Valor del boton apretado.
	 */
	public void accionar(Celda c, int valor) {
		c.actualizar(valor);
	}

	/**
	 * Establece la celda presionada con la celda parametrizada.
	 * 
	 * @param c Celda presionada.
	 */
	public void set_celda_presionada(Celda c) {
		celda_presionada = c;
	}

	/**
	 * Obtiene la celda presionada.
	 * 
	 * @return
	 */
	public Celda get_celda_presionada() {
		return celda_presionada;
	}

	/**
	 * Establece si continua el juego, con el valor parametrizado.
	 * 
	 * @param continua Es verdadero en caso de continuar, falso caso contrario.
	 */
	public void set_continua_juego(boolean continua) {
		continua_juego = continua;
	}

	/**
	 * Consulta si las celdas del juego son correctas para poder seguir jugando.
	 * 
	 * @return Verdadero si continua el juego, falso en caso contrario.
	 */
	public boolean get_continua_juego() {
		return continua_juego;
	}

	/**
	 * Obtiene la celda en la posicion i del arreglo de opciones.
	 * 
	 * @param i Posicion del arreglo de opciones
	 * @return Celda
	 */
	public Celda get_opcion(int i) {
		return this.opciones[i];
	}

	/**
	 * Obtiene la celda en la posicion [i][j] de la matriz tablero.
	 * 
	 * @param i fila de la celda.
	 * @param j columna de la celda.
	 * @return
	 */
	public Celda get_celda(int i, int j) {
		return this.tablero[i][j];
	}

	/**
	 * Setea el cuadrante en el que se encuentra cada celda del tablero.
	 * 
	 * @param fila Fila de la celda.
	 * @param col  Columna de la celda.
	 * @param c    Ultima celda presionada.
	 */
	private void setear_cuadrante(int fila, int col, Celda c) {
		if (fila < 3) {
			if (col < 3)
				c.set_cuadrante(1);
			else if (col > 2 && col < 6)
				c.set_cuadrante(2);
			else
				c.set_cuadrante(3);
		} else if (fila > 2 && fila < 6) {
			if (col < 3)
				c.set_cuadrante(4);
			else if (col > 2 && col < 6)
				c.set_cuadrante(5);
			else
				c.set_cuadrante(6);
		} else {
			if (col < 3)
				c.set_cuadrante(7);
			else if (col > 2 && col < 6)
				c.set_cuadrante(8);
			else
				c.set_cuadrante(9);
		}
	}

	/**
	 * Consulta si el archivo es valido.
	 * 
	 * @param ruta ubicacion del archivo.
	 * @return Verdadero si el archivo es valido, falso en caso contrario.
	 */
	private boolean validar_archivo(String ruta) {
		BufferedReader br = null;
		String[] arreglo;
		String linea;
		boolean archivo_valido = true;
		int f = 0, c = 0;

		try {
			// Apertura del archivo y creacion de BufferedReader para poder hacer una
			// lectura comoda (disponer del metodo readLine())
			InputStream in = Juego.class.getClassLoader().getResourceAsStream(ruta);
			InputStreamReader inr = new InputStreamReader(in);
			br = new BufferedReader(inr);
			// Lectura del archivo
			while ((linea = br.readLine()) != null && f < matriz_archivo.length && archivo_valido) {
				arreglo = linea.split(" "); // Crea un arreglo que almacena las palabras del archivo por linea, //
											// separandolas por espacios.
				if (arreglo.length == 9) { // Paso el contenido del archivo a matriz_archivo.
					for (int i = 0; i < arreglo.length; i++) {
						if (!is_numeric(arreglo[i])) {
							archivo_valido = false;
						} else {
							matriz_archivo[f][c] = Integer.parseInt(arreglo[i]);
							c++;
						}
					}
					f++;
					c = 0;
				} else {
					archivo_valido = false;
				}
			}
			if (linea != null) {
				archivo_valido = false;
			}
			for (int i = 0; i < 9 && archivo_valido; i++) {
				archivo_valido = validar_fila(i);
			}
			for (int i = 0; i < 9 && archivo_valido; i++) {
				archivo_valido = validar_columna(i);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return archivo_valido;
	}

	/**
	 * Consulta si el String recibido es un numero.
	 * 
	 * @param cadena
	 * @return Verdadero si el String es un numero, falso en caso contrario.
	 */
	private static boolean is_numeric(String cadena) {
		boolean resultado;
		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}
		return resultado;
	}

	/**
	 * Consulta si la fila tiene numeros repetidos.
	 * 
	 * @param num              Numero del boton presionado.
	 * @param celda_presionada Celda presionada.
	 * @return Verdadero si la fila no tiene numeros repetidos, falso en caso
	 *         contrario.
	 */
	public boolean fila_valida(int num, Celda celda_presionada) {
		boolean es_valida = true;
		int fila = celda_presionada.get_fila();
		for (int i = 0; i < 9; i++) {
			if (tablero[fila][i].get_valor() == num && tablero[fila][i] != celda_presionada) {
				tablero[fila][i].set_invalida(true);
				es_valida = false;
			} else {
				tablero[fila][i].set_invalida(false);
			}
		}
		return es_valida;
	}

	/**
	 * Consulta si la comlumna tiene numeros repetidos.
	 * 
	 * @param num              Numero del boton presionado.
	 * @param celda_presionada Celda presionada.
	 * @return Verdadero si la columna no tiene numeros repetidos, falso en caso
	 *         contrario.
	 */
	public boolean columna_valida(int num, Celda celda_presionada) {
		boolean es_valida = true;
		int col = celda_presionada.get_columna();
		for (int i = 0; i < 9; i++) {
			if (tablero[i][col].get_valor() == num && tablero[i][col] != celda_presionada) {
				tablero[i][col].set_invalida(true);
				es_valida = false;
			} else {
				tablero[i][col].set_invalida(false);
			}
		}
		return es_valida;
	}

	/**
	 * Consulta si el cuadrante es valido.
	 * 
	 * @param num              Es el numero de contiene la celda presionada.
	 * @param celda_presionada Celda presionada.
	 * @return Verdadero si el cuadrante es valido, falso caso contrario.
	 */
	public boolean cuadrante_valido(int num, Celda celda_presionada) {
		int cuadrante = celda_presionada.get_cuadrante() - 1;
		boolean es_valido = true;
		int fila = (cuadrante / 3) * 3;
		int columna = (cuadrante % 3) * 3;

		for (int i = fila; i < fila + 3; i++) {
			for (int j = columna; j < columna + 3; j++) {
				if (tablero[i][j].get_valor() == num && tablero[i][j] != celda_presionada) {
					es_valido = false;
					tablero[i][j].set_invalida(true);
				} else {
					tablero[i][j].set_invalida(false);
				}
			}
		}
		return es_valido;
	}

	/**
	 * Consulta si la fila del archivo es valida. Asegurandose que no contenga
	 * numeros repetidos entre el 1 y el 9.
	 * 
	 * @param fil Fila a verificar.
	 * @return Verdadero si la fila es valida, falso en caso contrario.
	 */
	public boolean validar_fila(int fil) {
		boolean es_valida = true;
		boolean esta_num = false;
		int num = 1;

		while (es_valida && num < 10) {
			for (int i = 0; i < 9 && !esta_num; i++) {
				if (matriz_archivo[fil][i] == num) {
					esta_num = true;
					num++;
				} else {
					if (i == 8)
						es_valida = false;
				}
			}
			esta_num = false;
		}
		return es_valida;
	}

	/**
	 * Consulta si la columna del archivo es valida. Asegurandose que no contenga
	 * numeros repetidos entre el 1 y el 9.
	 * 
	 * @param col Columna a verificar.
	 * @return Verdadero si la columna es valida, falso en caso contrario.
	 */
	public boolean validar_columna(int col) {
		boolean es_valida = true;
		boolean esta_num = false;
		int num = 1;

		while (es_valida && num < 10) {
			for (int i = 0; i < 9 && !esta_num; i++) {
				if (matriz_archivo[i][col] == num) {
					esta_num = true;
					num++;
				} else {
					if (i == 8)
						es_valida = false;
				}
			}
			esta_num = false;
		}
		return es_valida;
	}
}
