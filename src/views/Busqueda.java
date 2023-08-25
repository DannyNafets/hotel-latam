package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import controller.UsuarioController;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private JTable tbUsuarios;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloUsuario;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private UsuarioController usuarioController;
	private ReservaController reservaController;
	private HuespedController huespedController;
	int cont = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		
		this.usuarioController = new UsuarioController();
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		int idReserva = 0;
		var reservas = this.reservaController.listar();
		
		for (Reserva reserva : reservas) {
			idReserva = reserva.getId();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(302, 62, 305, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
		JTabbedPane panel1 = new JTabbedPane(JTabbedPane.TOP);
		panel1.setBackground(new Color(12, 138, 199));
		panel1.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel1.setBounds(20, 169, 865, 328);
		contentPane.add(panel1);
		
		JTabbedPane panel2 = new JTabbedPane(JTabbedPane.TOP);
		panel2.setBackground(new Color(12, 138, 199));
		panel2.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel2.setBounds(20, 169, 865, 328);
		contentPane.add(panel2);
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		cargarTablaReservas();
		tbReservas.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cont = 1;
				System.out.println(cont);
				
			}

		});
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		cargarTablaHuespedes();
		tbHuespedes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cont = 2;
				System.out.println(cont);
				
			}

		});
		
		tbUsuarios = new JTable();
		tbUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbUsuarios.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloUsuario = (DefaultTableModel) tbUsuarios.getModel();
		modeloUsuario.addColumn("Id de Usuario");
		modeloUsuario.addColumn("Usuario");
		modeloUsuario.addColumn("Nombre");
		modeloUsuario.addColumn("Fecha de Nacimiento");
		modeloUsuario.addColumn("Nacionalidad");
		modeloUsuario.addColumn("Telefono");
		JScrollPane scroll_tableUsuarios = new JScrollPane(tbUsuarios);
		panel.addTab("Usuarios", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableUsuarios, null);
		scroll_tableUsuarios.setVisible(true);
		
		cargarTabla();
		tbUsuarios.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cont = 3;
				System.out.println(cont);
				
			}

		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar();
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		JPanel btnSeleccionar = new JPanel();
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dato=String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),0));
				System.out.println(dato);
			}
		});
		btnSeleccionar.setLayout(null);
		btnSeleccionar.setBackground(new Color(12, 138, 199));
		btnSeleccionar.setBounds(489, 508, 134, 35);
		btnSeleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnSeleccionar);
		
		JLabel lblSeleccionar = new JLabel("SELECCIONAR");
		lblSeleccionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionar.setForeground(Color.WHITE);
		lblSeleccionar.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblSeleccionar.setBounds(0, 0, 134, 35);
		btnSeleccionar.add(lblSeleccionar);
		setResizable(false);
	}
	
	// clase Buscar
	
	protected void buscar() {
		if (txtBuscar.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Debes Ingresar Apellido o numero de Reserva");
		}
		
		String buscarString = " ";
		int buscarNumero = 0;
		
		if (isNumeric(txtBuscar.getText())) {
			buscarNumero = Integer.parseInt(txtBuscar.getText());
		}else {
			buscarString = txtBuscar.getText();
		}

		var huespedes = this.huespedController.buscar(buscarString, buscarNumero);
		
		System.out.println(modeloHuesped.getRowCount());
	
		for (int i = 0; i < modeloHuesped.getRowCount(); i++) {
			modeloHuesped.removeRow(i);
		}
		
		huespedes.forEach(huesped -> modeloHuesped.addRow(
        		new Object[] { 
        				huesped.getId(), 
        				huesped.getNombre(), 
        				huesped.getApellido(),
        				huesped.getFechaNacimiento(),
        				huesped.getNacionalidad(),
        				huesped.getTelefono(),
        				huesped.getNreserva()
	    		})); 
		
	}
	
	 public static boolean isNumeric(String str) {
	        try {
	            Double.parseDouble(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

	private void editar() {
		
		if (cont == 1) {
			if (tieneFilaElegidaReserva()) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	                    Object fechaEntrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1);
	                    Object fechaSalida = modelo.getValueAt(tbReservas.getSelectedRow(), 2);
	                    Double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
	                    String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);

	                    int filasModificadas = this.reservaController.editar(id, fechaEntrada, fechaSalida, valor, formaPago);

	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
		
		if (cont == 2) {
			if (tieneFilaElegidaHuesped()) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
	                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
	                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
	                    Object fechaNacimiento = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
	                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
	                    String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
	                    Integer nReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
	                    
	                    int filasModificadas = this.huespedController.editar(
	                    		id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, nReserva
	                    		);

	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
		
		if (cont == 3) {
			if (tieneFilaElegidaUsuario()) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), tbUsuarios.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 0).toString());
	                    String usuario = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 1);
	                    String nombre = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 2);
	                    Object fechaNacimiento = modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 3);
	                    String nacionalidad = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 4);
	                    String telefono = (String) modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 5);
	            
	                    int filasModificadas = this.usuarioController.editar(
	                    		id, usuario, nombre, fechaNacimiento, nacionalidad, telefono
	                    		);

	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}

	// elimina una fila seleccionada de cualquier tabla  
	
	private void eliminar() {
		
		if(cont == 1) {
			
			if (tieneFilaElegidaReserva()) {
                JOptionPane.showMessageDialog(this, "Por favor, elije un item");
                return;
            }
			
        	Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
        	.ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            
			int cantidadEliminada = this.reservaController.eliminar(id);
			
            modelo.removeRow(tbReservas.getSelectedRow());

            JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
                	
        }

        if (cont == 2) {
        	
        	if (tieneFilaElegidaHuesped()) {
                JOptionPane.showMessageDialog(this, "Por favor, elije un item");
                return;
            }
        	
        	Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
                Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                
				int cantidadEliminada = this.huespedController.eliminar(id);
				
				modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
            }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));        	
        }
		
        
        if (cont == 3) {
        	if (tieneFilaElegidaUsuario()) {
                JOptionPane.showMessageDialog(this, "Por favor, elije un item");
                return;
            }
        	
	        Optional.ofNullable(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), tbUsuarios.getSelectedColumn()))
	    		.ifPresentOrElse(fila -> {
		        Integer id = Integer.valueOf(modeloUsuario.getValueAt(tbUsuarios.getSelectedRow(), 0).toString());
		        
				int cantidadEliminada = this.usuarioController.eliminar(id);
				
				modeloUsuario.removeRow(tbUsuarios.getSelectedRow());
		
		        JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
		    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        }
		
	}
	 
	 // carga la tabla de Ususarios
	
	 private boolean tieneFilaElegidaReserva() {
		 return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	 }
	
	 private boolean tieneFilaElegidaHuesped() {
		 return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	 }
	 
	 private boolean tieneFilaElegidaUsuario() {
		 return tbUsuarios.getSelectedRowCount() == 0 || tbUsuarios.getSelectedColumnCount() == 0;
	 }

	private void cargarTabla() {
    	var usuarios = this.usuarioController.listar();
    		
        usuarios.forEach(usuario -> modeloUsuario.addRow(
        		new Object[] { 
        				usuario.getId(), 
	    				usuario.getUsuario(),
	    				usuario.getNombre(), 
	    				usuario.getFechaNacimiento(),
	    				usuario.getNacionalidad(),
	    				usuario.getTelefono()
	    		})); 
	 }
	 
	 private void cargarTablaReservas() {
		var reservas = this.reservaController.listar();
		
		reservas.forEach(reserva -> modelo.addRow(
				new Object[] {
						reserva.getId(),
						reserva.getFechaEntrada(),
						reserva.getFechaSalida(),
						reserva.getValor(),
						reserva.getFormaPago()
				}));
	 }
	 
	 private void cargarTablaHuespedes() {
		 var huespedes = this.huespedController.listar();
 		
	        huespedes.forEach(huesped -> modeloHuesped.addRow(
	        		new Object[] { 
	        				huesped.getId(), 
	        				huesped.getNombre(), 
	        				huesped.getApellido(),
	        				huesped.getFechaNacimiento(),
	        				huesped.getNacionalidad(),
	        				huesped.getTelefono(),
	        				huesped.getNreserva()
		    		})); 	
	 }
	 
	 
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
		 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
		 }

		 private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
		 }
}
