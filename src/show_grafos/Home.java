package show_grafos;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup Grbt1 = new ButtonGroup();
	private final ButtonGroup Grbt2 = new ButtonGroup();
	private JTextField txtVertices;
	private JButton btnPrximo;
	private JRadioButton orientadoN;
	private JRadioButton orientadoS;
	private JRadioButton valoradoN;
	private JRadioButton valoradoS;
	private JComboBox<String> cbV1;
	private JComboBox<String> cbV2;
	private JButton btnMostrar;
	private JEditorPane edtDTWinfo;

	public Grafo g = new Grafo();
	boolean valorado, orientado;
	String msg = "Arestas\n";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel tela1 = new JPanel();
		contentPane.add(tela1, "tela1");
		tela1.setLayout(null);

		JLabel lblOGrafo = new JLabel("O grafo \u00E9 orientado ?");
		lblOGrafo.setBounds(52, 111, 178, 20);
		tela1.add(lblOGrafo);

		orientadoN = new JRadioButton("N\u00E3o");
		orientadoN.setSelected(true);
		Grbt1.add(orientadoN);
		orientadoN.setBounds(282, 107, 110, 29);
		tela1.add(orientadoN);

		orientadoS = new JRadioButton("Sim");
		Grbt1.add(orientadoS);
		orientadoS.setBounds(444, 107, 103, 29);
		tela1.add(orientadoS);

		JLabel label = new JLabel("O grafo \u00E9 Valorado ?");
		label.setBounds(52, 189, 178, 20);
		tela1.add(label);

		valoradoN = new JRadioButton("N\u00E3o");
		valoradoN.setSelected(true);
		Grbt2.add(valoradoN);
		valoradoN.setBounds(282, 185, 110, 29);
		tela1.add(valoradoN);

		valoradoS = new JRadioButton("Sim");
		Grbt2.add(valoradoS);
		valoradoS.setBounds(444, 185, 103, 29);
		tela1.add(valoradoS);

		JLabel lblRepresentaoDe = new JLabel("------ Representa\u00E7\u00E3o de Grafos ------");
		lblRepresentaoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepresentaoDe.setBounds(15, 35, 572, 20);
		tela1.add(lblRepresentaoDe);

		JLabel lblQuantiaDeVrtices = new JLabel("Quantia de V\u00E9rtices (Conjunto V) :");
		lblQuantiaDeVrtices.setBounds(88, 274, 245, 20);
		tela1.add(lblQuantiaDeVrtices);

		txtVertices = new JTextField();
		txtVertices.setBounds(421, 271, 93, 26);
		tela1.add(txtVertices);
		txtVertices.setColumns(10);

		btnPrximo = new JButton("Pr\u00F3ximo");
		btnPrximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorado = valoradoS.isSelected();
				orientado = orientadoS.isSelected();

				criaGrafo();
				colocaComboBox();
				CardLayout card = (CardLayout) (contentPane.getLayout());
				card.show(contentPane, "tela2");
			}
		});
		btnPrximo.setBounds(243, 380, 115, 29);
		tela1.add(btnPrximo);

		JPanel tela2 = new JPanel();
		contentPane.add(tela2, "tela2");
		tela2.setLayout(null);

		JLabel lblVrtice = new JLabel("V\u00E9rtice:   ");
		lblVrtice.setBounds(56, 99, 69, 20);
		tela2.add(lblVrtice);

		JLabel lblDefinaAs = new JLabel("------ Defina as liga\u00E7\u00F5es dos v\u00E9rtices ------");
		lblDefinaAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefinaAs.setBounds(33, 43, 554, 20);
		tela2.add(lblDefinaAs);

		cbV1 = new JComboBox<String>();
		cbV1.setBounds(175, 96, 106, 26);
		tela2.add(cbV1);

		JLabel label_1 = new JLabel("V\u00E9rtice:   ");
		label_1.setBounds(331, 99, 69, 20);
		tela2.add(label_1);

		cbV2 = new JComboBox<String>();
		cbV2.setBounds(450, 96, 106, 26);
		tela2.add(cbV2);

		btnMostrar = new JButton("Mostrar todos as Representa\u00E7\u00F5es");
		btnMostrar.setBounds(33, 399, 554, 29);
		tela2.add(btnMostrar);

		JButton btnPrximaAresta = new JButton("Pr\u00F3xima Aresta");
		btnPrximaAresta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int v1 = cbV1.getSelectedIndex();
				int v2 = cbV2.getSelectedIndex();

				g.addArestas(v1, v2, valorado, orientado);

				msg = msg + g.nomeAresta(g.arestas.size() - 1) + " | " + cbV1.getSelectedItem();

				if (orientado) {
					msg = msg + " ---> " + cbV2.getSelectedItem();
				} else {
					msg = msg + " --- " + cbV2.getSelectedItem();
				}

				if (valorado) {
					msg = msg + " | Peso: " + g.pesoAresta(g.arestas.size() - 1);
				}

				msg = msg + "\n";

				edtDTWinfo.setText(msg);

			}
		});
		btnPrximaAresta.setBounds(194, 169, 217, 29);
		tela2.add(btnPrximaAresta);

		JSeparator separator = new JSeparator();
		separator.setBounds(15, 373, 572, 14);
		tela2.add(separator);

		edtDTWinfo = new JEditorPane();
		edtDTWinfo.setEditable(false);
		JScrollPane spEditor = new JScrollPane(edtDTWinfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spEditor.setBounds(33, 216, 554, 130);
		edtDTWinfo.setText(msg);
		tela2.add(spEditor);

		JPanel tela3 = new JPanel();
		contentPane.add(tela3, "tela3");
		tela3.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(15, 16, 572, 277);
		tela3.add(textPane);

		JButton btnIncio = new JButton("In\u00EDcio");
		btnIncio.setBounds(243, 355, 115, 29);
		tela3.add(btnIncio);
		setLocationRelativeTo(null);
	}

	public void criaGrafo() {

		int nVertices = Integer.parseInt(txtVertices.getText());
		g.addVertices(nVertices);

	}

	public void colocaComboBox() {
		for (int i = 0; i < g.vertices.size(); i++) {
			cbV1.addItem(g.vertices.get(i).nome);
			cbV2.addItem(g.vertices.get(i).nome);
		}
	}
}