package interfaz;

import javax.swing.JFrame;
import javax.swing.JTextField;

import logica.TFA_DescPrimos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class Visual {

	public JFrame frame;
	private TFA_DescPrimos logica;
	private JTextField tFValorA;
	private JButton btnCalcular;
	private JButton btnReset;
	private JLabel lblCreditos;
	private JLabel lblResultado;
	private String textoLabel;
	
	static LinkedList<Integer> primos;
	
	static Integer valorA;
	
	private TextoTranslucido _textoTranslucido;
	public Visual() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 100, 1000, 800);
		logica = new TFA_DescPrimos();
		primos = new LinkedList<Integer>();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		agregarElemsListaPrimos();
		inicializar_tfValorA();
		inicializar_BotonCalcular();
		inicializarLabelResultadoFinal();
		inicializarLabelCreditos();
		inicializar_BotonReset();
		
	}
	private void agregarElemsListaPrimos() {
		primos.addAll(logica.generadorDeNumerosPrimosHastaN(99999));
	}
	private void inicializar_tfValorA() {
		tFValorA = new JTextField();
		setTextoTranslucido(new TextoTranslucido("VALOR A DESCOMPONER", tFValorA));
		tFValorA.setBounds(365, 500, 250, 55);
		frame.getContentPane().add(tFValorA);
		tFValorA.setColumns(10);
		limitarInputUsuario(tFValorA);
	}
	
	private void inicializar_BotonCalcular() {
		btnCalcular = new JButton("CALCULAR!");
		btnCalcular.setBounds(410, 650, 164, 55);
		frame.getContentPane().add(btnCalcular);
		accionBotonCalcular();
	}
	
	private void inicializar_BotonReset() {
		btnReset = new JButton("REINICIAR");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.RED);
		btnReset.setBounds(870, 700, 90, 35);
		frame.getContentPane().add(btnReset);
		accionBotonReset();
	}
	
	private void inicializarLabelResultadoFinal() {
		lblResultado = new JLabel("<html><body><center><h1><br>TFA (Descomposicion de primos)</h1><br></center></body></html>");
		lblResultado.setBounds(310, 75, 429, 158);
		frame.getContentPane().add(lblResultado);
	}
	
	private void inicializarLabelCreditos() {
		lblCreditos = new JLabel("By: maxisandoval37");
		lblCreditos.setBounds(40, 630, 429, 158);
		frame.getContentPane().add(lblCreditos);
	}
	
	private void accionBotonCalcular() {
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sonNumeros(tFValorA.getText().toString())) {
					capturarInputs();
					if (valorA > 1 && valorA <100000)
						asignarTextoAlLabelValorX(logica.mostrarTFA(valorA, primos));
					else
						JOptionPane.showMessageDialog(null, "INGRESE UN VALOR NUMERICO >1 Y <100000 PARA CONTINUAR");
				}
				else
					JOptionPane.showMessageDialog(null, "INGRESE UN VALOR NUMERICO >1 Y <100000 PARA CONTINUAR");
			}
		});
	}
	
	private void accionBotonReset() {
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				capturarInputs();
				valorA=0;
				tFValorA.setText("");
				lblResultado.setText("<html><body><center><h1><br>TFA (Descomposicion de primos)</h1><br></center></body></html>");
			}
		});
	}

	private void capturarInputs() {
		if (sonNumeros(tFValorA.getText().toString())) 
			valorA = Integer.parseInt(tFValorA.getText() + 0) / 10;
	}
	
	private boolean sonNumeros(String s) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(s);
		
		return matcher.matches();
	}
	
	private void asignarTextoAlLabelValorX(String valorX) {
		textoLabel="<html><body><center><h1><br>El valor de X es: <br/>"+valorX+"</h1></center></body></html>";
		lblResultado.setText(textoLabel);
	}
	
	private void limitarInputUsuario(JTextField tf) {
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() < '0') || (e.getKeyChar() > '9') && (e.getKeyChar() != '\b')) {
					e.consume();
				} else {
					if (tf.getText().length() >= 5)
						e.consume();
				}
			}
		});
	}
	
	public TextoTranslucido getTp() {
		return _textoTranslucido;
	}

	public void setTextoTranslucido(TextoTranslucido tt) {
		this._textoTranslucido = tt;
	}
}
