package Logica;

import javax.swing.ImageIcon;

/**
 * Modela una entidad grafica.
 * 
 * @author Agustin Escobar.
 *
 */
public class Entidad_grafica {
	protected ImageIcon grafico; // Permite guardar la imagen.
	protected String[] imagenes; // Arreglo de string, cada indice retorna una imagen.

	public Entidad_grafica() {
		this.grafico = new ImageIcon();
	}

	/**
	 * Actualiza la imagen de la celda.
	 * 
	 * @param indice Indica la posicion del arreglo de imagenes.
	 */
	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}

	/**
	 * Obtiene la imagen de la entidad grafica.
	 * 
	 * @return
	 */
	public ImageIcon get_grafico() {
		return this.grafico;
	}

	/**
	 * Establece la imagen de la entidad grafica.
	 * 
	 * @param grafico
	 */
	public void set_grafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	/**
	 * Obtiene las imagenes de la entidad grafica.
	 * @return
	 */
	public String[] get_imagenes() {
		return this.imagenes;
	}

	public void set_imagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}
