package Divisas;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Divisas extends JFrame implements ActionListener {
		
	public JPanel contentPane;
	public JTextField txtCantidad;
	public JTextField textField_2_IngreseTemperatura;
	public JComboBox<String> comboBox_2_TemperaturaOrigen;
	public JComboBox<String> comboBox_2_TemperaturaDestino;
	public JComboBox<String> cboDivisasOrigen;
	public JComboBox<String> cboDivisasDestino;
	private JButton btnNewButton_ConvertirTemp;
	private JButton btnConvertir;
	private JLabel lblResultado;
	@SuppressWarnings("unused")
	private Component textField;
	private JLabel lblResultado_temp;
	private static final String[] DIVISAS = {"USD", "EUR", "GBP", "JPY", "KRW", "PEN"};
	private static final String[] TEMPERATURA = {"Centigrados", "Farenheit"};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Divisas frame = new Divisas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
		}	
	
	@SuppressWarnings("unused")
	public Divisas() {
		//imagen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 516);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setForeground(new Color(64, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Vialardi\\Desktop\\PROYECTOS ALURA\\Conversor Divisas\\descarga.png"));
		lblNewLabel_1.setBounds(460, 165, 233, 201);
		contentPane.add(lblNewLabel_1);
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Vialardi\\Desktop\\PROYECTOS ALURA\\Conversor Divisas\\oracle + alura.png"));
		lblNewLabel.setBounds(197, 26, 351, 92);
		contentPane.add(lblNewLabel);
		
		//divisa
		txtCantidad = new JTextField();
		txtCantidad.setBounds(55, 153, 150, 25);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(0);
		JTextField textField = new JTextField();
		
		cboDivisasOrigen = new JComboBox<String>(DIVISAS);
		cboDivisasOrigen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cboDivisasOrigen.setBounds(55, 188, 150, 25);
		contentPane.add(cboDivisasOrigen);

		cboDivisasDestino = new JComboBox<String>(DIVISAS);
		cboDivisasDestino.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cboDivisasDestino.setBounds(55, 221, 150, 25);
		contentPane.add(cboDivisasDestino);
	      
		btnConvertir = new JButton("CONVERTIR DIV.");
		btnConvertir.addActionListener(this);
		btnConvertir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConvertir.setBounds(55, 256, 150, 21);
		contentPane.add(btnConvertir);
			
		lblResultado = new JLabel("");
		lblResultado.setForeground(new Color(0, 0, 0));
		lblResultado.setBackground(new Color(255, 255, 255));
		lblResultado.setBounds(229, 188, 150, 57);
		contentPane.add(lblResultado);
		
		
		//temperatura
		textField_2_IngreseTemperatura = new JTextField();
		textField_2_IngreseTemperatura.setColumns(10);
		textField_2_IngreseTemperatura.setBounds(78, 341, 150, 25);
		contentPane.add(textField_2_IngreseTemperatura);
		
		comboBox_2_TemperaturaOrigen = new JComboBox<String>(TEMPERATURA);
		comboBox_2_TemperaturaOrigen.setBounds(229, 341, 150, 25);
		contentPane.add(comboBox_2_TemperaturaOrigen);
		
		comboBox_2_TemperaturaDestino = new JComboBox<String>(TEMPERATURA);
		comboBox_2_TemperaturaDestino.setBounds(229, 376, 150, 25);
		contentPane.add(comboBox_2_TemperaturaDestino);
		
		btnNewButton_ConvertirTemp = new JButton("CONVIERTE TEMP.");
		btnNewButton_ConvertirTemp.addActionListener(this);
		btnNewButton_ConvertirTemp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_ConvertirTemp.setBounds(78, 376, 150, 25);
		contentPane.add(btnNewButton_ConvertirTemp);
		
		lblResultado_temp = new JLabel("");
		lblResultado_temp.setForeground(Color.BLACK);
		lblResultado_temp.setBackground(Color.WHITE);
		lblResultado_temp.setBounds(383, 345, 150, 57);
		contentPane.add(lblResultado_temp);
		
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//divisa
		if (e.getSource() == btnConvertir) {
			try {
				double cantidad = Double.parseDouble(txtCantidad.getText());
				String divisaOrigen = (String) cboDivisasOrigen.getSelectedItem();
				String divisaDestino = (String) cboDivisasDestino.getSelectedItem();
				double tasaOrigen = obtenerTasa(divisaOrigen);
				double tasaDestino = obtenerTasa(divisaDestino);
				double resultado = cantidad * (tasaDestino / tasaOrigen);
				lblResultado.setText(String.format("%.2f %s", resultado, divisaDestino));
			} catch (NumberFormatException ex) {
				lblResultado.setText("Cantidad no válida");	
			}
		
		//temperatura
		}else if (e.getSource() == btnNewButton_ConvertirTemp) {
			try {
				double cantidadTemp = Double.parseDouble(textField_2_IngreseTemperatura.getText());
				String TemperaturaOrigen = (String) comboBox_2_TemperaturaOrigen.getSelectedItem();
				String TemperaturaDestino = (String) comboBox_2_TemperaturaDestino.getSelectedItem();
				double tempOrigen = obtenerTemperatura(TemperaturaOrigen);
				double tempDestino = obtenerTemperatura(TemperaturaDestino);
				double resultado = cantidadTemp * (tempDestino / tempOrigen);
				lblResultado_temp.setText(String.format("%.2f %s", resultado, TemperaturaDestino));		
			} catch (NumberFormatException ex) {
				lblResultado_temp.setText("Cantidad no válida");	
			}
		}
}


	private double obtenerTasa(String divisa) {
		switch (divisa) {
			case "USD":
				return 1.0;
			case "EUR":
				return 0.92;
			case "GBP":
				return 0.81;
			case "JPY":
				return 132.94;
			case "KRW":
				return 1298.03;
			case "PEN":
				return 3.75;
			default:
				return 1.0;
		}
	}
	
	private double obtenerTemperatura(String Temperatura) {
		switch (Temperatura) {
		case "Centigrados":
			return (28.0 * 9 / 5) + 32;
		case "Farenheit":
			return 28.0;
		default:
			return 1.0;
		}
	}
}