package views;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.HuespedController;

public class RelacionHuespedReservaFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JTable tablaRelacion;
    private DefaultTableModel modelo;

    private HuespedController huespedController;

    public RelacionHuespedReservaFrame(Busqueda busqueda) {
        super("Relación Huespedes Reservas");

        this.huespedController = new HuespedController();

        Container container = getContentPane();
        setLayout(null);

        tablaRelacion = new JTable();
        tablaRelacion.setBounds(0, 0, 600, 400);
        container.add(tablaRelacion);

        modelo = (DefaultTableModel) tablaRelacion.getModel();
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");

        cargarTablaRelacionHuepedReservas();

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(busqueda);
    }

	private void cargarTablaRelacionHuepedReservas() {
		System.out.println("Entra");
		var contenido= huespedController.cargarRelacion();
		System.out.println("Sale");
			
			contenido.forEach(huesped -> {
				modelo.addRow(new Object[] { huesped });
				
				var reservas = huesped.getReservas();
				
				reservas.forEach(reserva -> modelo.addRow(
						new Object[] {
								"",
								reserva.getFechaEntrada(),
								reserva.getFechaSalida(),
								reserva.getValor(),
								reserva.getFormaPago()
						}));
						
			});
			System.out.println(contenido);
		}
}
