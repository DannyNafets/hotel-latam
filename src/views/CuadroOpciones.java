package views;

import javax.swing.JOptionPane;

public class CuadroOpciones {
	private int opcion;
	
	public void deseaContinuar() {
		Object[] options = {"No", "Si"};
		this.opcion = JOptionPane.showOptionDialog(null, "Desea salir de la Aplicación", "Warning", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
				null, options, options[0]);
	}

	public int getOpcion() {
		return this.opcion;
	}
	
	
	
}
