package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Logica.Celda;
import Logica.Cronometro;
import Logica.Entidad_grafica;
import Logica.Entidad_reloj;
import Logica.InvalidFileException;
import Logica.Juego;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.BorderLayout;

public class GUI_Sudoku extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	private JLabel[][] matriz_labels;
	private JLabel label_anterior;
	private JButton[] opciones;
	private boolean en_juego;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Sudoku frame = new GUI_Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_Sudoku() {
		try {
			setIconImage(
					Toolkit.getDefaultToolkit().getImage(GUI_Sudoku.class.getResource("/Imagenes_complem/sudoku.png")));
			setTitle("Sudoku");
			setResizable(false);
			setLocationRelativeTo(null); // Crea la ventana al centro
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 851, 622); // Tamaño de la ventana
			contentPane = new JPanel();
			contentPane.setBackground(new Color(240, 248, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			label_anterior = new JLabel();
			matriz_labels = new JLabel[9][9];
			opciones = new JButton[10];
			en_juego = false;

			JPanel panel_cuadrante = new JPanel();
			panel_cuadrante.setOpaque(false);
			panel_cuadrante.setBackground(new Color(240, 248, 255));
			contentPane.add(panel_cuadrante); // agrega el panel
			panel_cuadrante.setLayout(new GridLayout(9, 0, 0, 0));
			panel_cuadrante.setBounds(25, 135, 428, 428);

			juego = new Juego();

			JPanel panel_der = new JPanel();
			panel_der.setOpaque(false);
			panel_der.setBackground(new Color(240, 248, 255));
			panel_der.setBounds(479, 135, 338, 428);
			contentPane.add(panel_der);
			panel_der.setLayout(null);

			JPanel panel_mensajes = new JPanel();
			panel_mensajes.setOpaque(false);
			panel_mensajes.setBounds(2, 223, 334, 208);
			panel_mensajes.setBorder(new LineBorder(new Color(240, 248, 255), 0));
			panel_mensajes.setBackground(new Color(240, 248, 255));
			panel_der.add(panel_mensajes);
			panel_mensajes.setLayout(new BorderLayout(0, 0));

			JLabel lbl_mensaje = new JLabel("");
			lbl_mensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl_mensaje.setBorder(new LineBorder(new Color(0, 0, 0), 0));
			panel_mensajes.add(lbl_mensaje, BorderLayout.CENTER);

			// ----------- PANEL AUXILIAR. (Contiene panel cronometro y panel botones).
			JPanel panel_aux = new JPanel();
			panel_aux.setOpaque(false);
			panel_aux.setBounds(0, 2, 334, 222);
			panel_aux.setBackground(new Color(240, 248, 255));
			panel_der.add(panel_aux);
			panel_aux.setLayout(new GridLayout(2, 0, 0, 0));

			JPanel panel_cronometro = new JPanel();
			panel_cronometro.setOpaque(false);
			panel_cronometro.setBackground(new Color(240, 248, 255));
			panel_cronometro.setBounds(0, 0, 334, 111);
			panel_cronometro.setLayout(null);
			ImageIcon imageIcon_num_0 = new ImageIcon(this.getClass().getResource("/Imagenes_cronometro/0.png"));

			JLabel lbl_hora_1 = new JLabel("h1"); // primer label de la hora.
			lbl_hora_1.setBounds(50, 34, 30, 30); // establezco el tamaño
			panel_cronometro.add(lbl_hora_1); // lo añado al panel cronometro
			lbl_hora_1.setIcon(imageIcon_num_0); // le establezco la imagen inicial en 0
			redimensionar(lbl_hora_1, imageIcon_num_0); // redimensiono el tamaño de la imagen

			JLabel lbl_hora_2 = new JLabel("h2"); // segundo label de la hora.
			lbl_hora_2.setBounds(78, 34, 30, 30);
			panel_cronometro.add(lbl_hora_2);
			lbl_hora_2.setIcon(imageIcon_num_0);
			redimensionar(lbl_hora_2, imageIcon_num_0);

			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Imagenes_cronometro/dos_p.png"));
			JLabel lbl_dos_puntos1 = new JLabel("separador_1");
			lbl_dos_puntos1.setForeground(new Color(0, 0, 0));
			lbl_dos_puntos1.setBounds(106, 34, 30, 30);
			panel_cronometro.add(lbl_dos_puntos1);
			lbl_dos_puntos1.setIcon(imageIcon);
			redimensionar(lbl_dos_puntos1, imageIcon);

			JLabel lbl_min_1 = new JLabel("m1"); // primer label de los minutos.
			lbl_min_1.setBounds(136, 34, 30, 30);
			panel_cronometro.add(lbl_min_1);
			lbl_min_1.setIcon(imageIcon_num_0);
			redimensionar(lbl_min_1, imageIcon_num_0);

			JLabel lbl_min_2 = new JLabel("m2"); // segundo label de los minutos.
			lbl_min_2.setBounds(165, 34, 30, 30);
			lbl_min_2.setBackground(new Color(240, 240, 240));
			panel_cronometro.add(lbl_min_2);
			lbl_min_2.setIcon(imageIcon_num_0);
			redimensionar(lbl_min_2, imageIcon_num_0);

			JLabel lbl_dos_puntos2 = new JLabel("separador_2");
			lbl_dos_puntos2.setBounds(192, 34, 30, 30);
			panel_cronometro.add(lbl_dos_puntos2);
			lbl_dos_puntos2.setIcon(imageIcon);
			redimensionar(lbl_dos_puntos1, imageIcon);

			JLabel lbl_seg_1 = new JLabel("s1"); // primer label de los segundos.
			lbl_seg_1.setBounds(221, 34, 30, 30);
			panel_cronometro.add(lbl_seg_1);
			lbl_seg_1.setIcon(imageIcon_num_0);
			redimensionar(lbl_seg_1, imageIcon_num_0);

			panel_aux.add(panel_cronometro);

			JLabel lbl_seg_2 = new JLabel("s2"); // segundo label de los segundos.
			lbl_seg_2.setBounds(251, 34, 30, 30);
			panel_cronometro.add(lbl_seg_2);
			lbl_seg_2.setIcon(imageIcon_num_0);
			redimensionar(lbl_seg_1, imageIcon_num_0);

			JPanel panel_botones = new JPanel();
			panel_botones.setOpaque(false);
			panel_botones.setBackground(new Color(240, 248, 255));
			panel_aux.add(panel_botones);
			panel_botones.setLayout(null);

			// ---------------------CRONOMETRO-----------------------
			Entidad_grafica hora_1 = new Entidad_reloj();
			Entidad_grafica hora_2 = new Entidad_reloj();
			Entidad_grafica min_1 = new Entidad_reloj();
			Entidad_grafica min_2 = new Entidad_reloj();
			Entidad_grafica seg_1 = new Entidad_reloj();
			Entidad_grafica seg_2 = new Entidad_reloj();

			Cronometro cronom = new Cronometro();
			TimerTask task = new TimerTask() {
				int t = 0;

				public void run() {

					cronom.tiempo(t); // inicializo el cronometro en 0.
					int s = cronom.get_segundos();
					int m = cronom.get_minutos();
					int h = cronom.get_horas();

					hora_2.actualizar(h % 10); // Obtengo el 2do digito de la hora y actualizo la imagen.
					lbl_hora_2.setIcon(hora_2.get_grafico()); // le establezco la imagen correspondiente al label hora
																// 2.
					redimensionar(lbl_hora_2, hora_2.get_grafico()); // redimensiono el tamaño de la imagen.
					lbl_hora_2.repaint();
					h = h / 10; // Obtengo el 1er digito de la hora.
					hora_1.actualizar(h); // actualizo la imagen.
					lbl_hora_1.setIcon(hora_1.get_grafico()); // le establezco la imagen correspondiente al label hora
																// 1.
					redimensionar(lbl_hora_1, hora_1.get_grafico()); // redimensiono el tamaño de la imagen.
					lbl_hora_1.repaint();

					min_2.actualizar(m % 10);
					lbl_min_2.setIcon(min_2.get_grafico());
					redimensionar(lbl_min_2, min_2.get_grafico());
					lbl_min_2.repaint();
					m = m / 10;
					min_1.actualizar(m);
					lbl_min_1.setIcon(min_1.get_grafico());
					redimensionar(lbl_min_1, min_1.get_grafico());
					lbl_min_1.repaint();

					seg_2.actualizar(s % 10);
					lbl_seg_2.setIcon(seg_2.get_grafico());
					redimensionar(lbl_seg_2, seg_2.get_grafico());
					lbl_seg_2.repaint();
					s = s / 10;
					seg_1.actualizar(s);
					lbl_seg_1.setIcon(seg_1.get_grafico());
					redimensionar(lbl_seg_1, seg_1.get_grafico());
					lbl_seg_1.repaint();
					t++;
				}
			};
			// ------------------FIN PANEL AUXILIAR--------------------------

			// ------------------BOTON JUGAR Y BOTON REINICIAR---------------
			JButton boton_reiniciar = new JButton("Reiniciar");
			boton_reiniciar.setEnabled(false);
			boton_reiniciar.setBounds(123, 51, 89, 23);
			panel_botones.add(boton_reiniciar);
			boton_reiniciar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (en_juego) { // Si esta en juego, habilito el boton "reiniciar".
						dispose(); // Destruyo la ventana actual.
						GUI_Sudoku new_sudoku = new GUI_Sudoku(); // Creo un nuevo frame.
						new_sudoku.setVisible(true);
					}
				}
			});
			JButton boton_jugar = new JButton("Jugar");
			boton_jugar.setBounds(123, 11, 89, 23);
			panel_botones.add(boton_jugar);
			boton_jugar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!en_juego) { // si no esta en juego, habilito el boton "jugar".
						habilitar_opciones(true); // habilito las opciones.
						runTimer(task, cronom); // inicio el cronometro.
						en_juego = true; // ahora esta en juego.
						boton_jugar.setEnabled(false); // deshabilito el boton "jugar".
						boton_reiniciar.setEnabled(true); // habilito el boton "reiniciar".
					}
				}
			});
			// ------------FIN BOTON JUGAR Y BOTON REINICIAR------------------------------

			// ------------Insertar celdas en el tablero----------------------------------
			// Organiza graficamente cada uno de los labels del tablero del juego.
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Celda c = juego.get_celda(i, j);
					ImageIcon grafico = c.get_entidad_grafica().get_grafico();
					JLabel label = new JLabel();
					if (!c.get_habilitada())
						label.setBackground(new Color(206, 206, 206));
					if ((i % 3) == 0 && (j % 3) == 0) {
						label.setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.DARK_GRAY));
					} else {
						if ((i % 3) == 0) {
							label.setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.DARK_GRAY));
						} else {
							if ((j % 3) == 0) {
								label.setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.DARK_GRAY));
							} else {
								label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
							}
						}
					}
					panel_cuadrante.add(label);
					label.setOpaque(true);
					matriz_labels[i][j] = label;
					label.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							redimensionar(label, grafico);
							label.setIcon(grafico);
						}
					});
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							ImageIcon imageIcon = new ImageIcon(
									this.getClass().getResource("/Imagenes_complem/opcion_incorrecta.png"));

							if (c.get_habilitada() && en_juego && !juego.termino()) {
								habilitar_opciones(true);
								if (!juego.get_continua_juego()) {
									if (label == label_anterior) {
										label_anterior.setBackground(new Color(160, 210, 255));
									} else {
										label_anterior.setBackground(Color.black);
										lbl_mensaje.setIcon(imageIcon);
										redimensionar(lbl_mensaje, imageIcon);
										label_anterior.setBackground(new Color(200, 0, 0));
									}
								} else {
									if (juego.get_celda_presionada() == null) {
										lbl_mensaje.setIcon(null);
										label_anterior = label;
										label_anterior.setBackground(new Color(160, 210, 255));
										juego.set_celda_presionada(c);
									} else {
										lbl_mensaje.setIcon(null);
										label_anterior.setBackground(null);
										label_anterior = label;
										label_anterior.setBackground(new Color(160, 210, 255));
										juego.set_celda_presionada(c);
									}
								}
							} else { // si no es una celda habilitada
								if (en_juego && !juego.termino()) {
									habilitar_opciones(false); // deshabilito los botones de las opciones
									lbl_mensaje.setIcon(imageIcon);// establezco imagen indicando que corrija la opcion
									redimensionar(lbl_mensaje, imageIcon);
								}
							}
						}
					});
				}
			}
			//
			JPanel panel_opciones = new JPanel();
			panel_opciones.setOpaque(false);
			panel_opciones.setBackground(new Color(240, 248, 255));
			panel_opciones.setBounds(25, 58, 792, 65);
			contentPane.add(panel_opciones);
			panel_opciones.setLayout(new GridLayout(0, 10, 0, 0));

			// ---------------- BOTONES DE LAS OPCIONES --------------------
			// Organiza graficamente cada uno de los botones de las opciones del juego.
			for (int i = 0; i < 10; i++) {
				Celda c = juego.get_opcion(i);
				JButton boton = new JButton();
				ImageIcon grafico = c.get_entidad_grafica().get_grafico();
				panel_opciones.add(boton);
				opciones[i] = boton;
				boton.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						redimensionar(boton, grafico);
						boton.setIcon(grafico);

					}
				});
				boton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// Vincula la celda que este seleccionada y cambia su imagen por la del boton.
						if (boton.isEnabled()) {
							int num_boton = c.get_valor(); // Obtengo el valor del boton que aprete.
							Celda celda_presionada = juego.get_celda_presionada(); // obtengo la celda presionada que
																					// quedo
																					// habilitada
							juego.accionar(celda_presionada, num_boton);
							JLabel label_presionado = matriz_labels[celda_presionada.get_fila()][celda_presionada
									.get_columna()];
							redimensionar(label_presionado, celda_presionada.get_entidad_grafica().get_grafico());
							label_presionado.repaint(); // Repinta el label
							label_presionado.setBackground(new Color(0, 0, 0));
							chequear_opcion(num_boton, celda_presionada, lbl_mensaje);// chequear que sea valida la
																						// opcion

							if (juego.termino() && juego.get_continua_juego()) { // si gano el juego
								cronom.parar_cronometro();
								habilitar_opciones(false);
								celda_presionada.set_habilitada(false);
								ImageIcon imageIcon = new ImageIcon(
										this.getClass().getResource("/Imagenes_complem/ganaste.png"));
								lbl_mensaje.setIcon(imageIcon);
								redimensionar(lbl_mensaje, imageIcon);
								JOptionPane.showMessageDialog(contentPane, "¡GANASTE!", "Juego terminado.",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				});
			}
			// --------------------
			JTextPane txtpnOpciones = new JTextPane();
			txtpnOpciones.setOpaque(false);
			txtpnOpciones.setForeground(Color.WHITE);
			txtpnOpciones.setEditable(false);
			txtpnOpciones.setFont(new Font("Aharoni", Font.PLAIN, 16));
			txtpnOpciones.setBackground(new Color(240, 248, 255));
			txtpnOpciones.setText("Opciones:");
			txtpnOpciones.setBounds(25, 37, 95, 20);
			contentPane.add(txtpnOpciones);

			JLabel label_sudoku = new JLabel("SUDOKU");
			label_sudoku.setForeground(Color.WHITE);
			label_sudoku.setBounds(362, 22, 140, 25);
			contentPane.add(label_sudoku);
			label_sudoku.setFont(new Font("Aharoni", Font.PLAIN, 27));

			JLabel lbl_fondo = new JLabel("");
			lbl_fondo.setIcon(new ImageIcon(GUI_Sudoku.class.getResource("/Imagenes_complem/fondo.jpg")));
			lbl_fondo.setBounds(0, 0, 845, 593);
			contentPane.add(lbl_fondo);
			habilitar_opciones(false);
		} catch (InvalidFileException error) {
			JOptionPane.showMessageDialog(contentPane, "Archivo invalido. ", "Error", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

	}

	/**
	 * Chequea si la opcion es valida o no. Marca las celdas donde hay errores.
	 * 
	 * @param num              Numero del boton presionado.
	 * @param celda_presionada Ultima celda presionada
	 */
	private void chequear_opcion(int num_boton, Celda celda_presionada, JLabel lbl_mensaje) {
		boolean cuadrante_valido, fila_valida, columna_valida;

		fila_valida = juego.fila_valida(num_boton, celda_presionada);
		columna_valida = juego.columna_valida(num_boton, celda_presionada);
		cuadrante_valido = juego.cuadrante_valido(num_boton, celda_presionada);

		if (fila_valida && columna_valida && cuadrante_valido || num_boton == 9) { // si la opcion es correcta
			lbl_mensaje.setIcon(null);
			juego.set_continua_juego(true);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Celda celda_actual;
					celda_actual = juego.get_celda(i, j);
					celda_actual.set_invalida(false);
					if (celda_actual.get_habilitada()) {
						matriz_labels[i][j].setBackground(null);
					} else {
						matriz_labels[i][j].setBackground(new Color(206, 206, 206));
					}
				}
			}
		} else { // si la opcion es incorrecta
			juego.set_continua_juego(false);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Celda celda_actual;
					celda_actual = juego.get_celda(i, j);
					if (celda_actual.get_invalida()) {
						matriz_labels[i][j].setBackground(new Color(255, 0, 0));
					} else {
						if (celda_actual.get_habilitada())
							matriz_labels[i][j].setBackground(null);
						else {
							matriz_labels[i][j].setBackground(new Color(206, 206, 206));
						}
					}
				}
			}
			matriz_labels[celda_presionada.get_fila()][celda_presionada.get_columna()]
					.setBackground(new Color(180, 0, 0));
		}
	}

	/**
	 * Habilita o deshabilita las opciones del juego.
	 * 
	 * @param es
	 */
	public void habilitar_opciones(boolean es) {
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].setEnabled(es);
		}
	}

	/**
	 * Adapta el tamaño de la imagen al tamaño del label.
	 * 
	 * @param boton
	 * @param grafico
	 */
	private void redimensionar(JButton boton, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image new_img = image.getScaledInstance(boton.getWidth(), boton.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(new_img);
			boton.repaint();
		}
	}

	/**
	 * Adapta el tamaño de la imagen al tamaño del label.
	 * 
	 * @param label
	 * @param grafico
	 */
	private void redimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image new_img = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(new_img);
			label.repaint();
		}
	}

	/**
	 * Inicia el cronometro.
	 * 
	 * @param task
	 * @param cronom
	 */
	public void runTimer(TimerTask task, Cronometro cronom) {
		cronom.get_timer().schedule(task, 0, 1000);
	}
}
