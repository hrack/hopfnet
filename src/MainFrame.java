import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Grid grid;
	private HopfieldNetwork net;
	private JButton btnTrain;
	private JButton btnRecognise;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		initialize();
		if(grid != null)
			net = new HopfieldNetwork(grid.getRows()*grid.getColumns());
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		grid = new Grid(6, 6);
		grid.setBounds(56, 34, 453, 415);
		contentPane.add(grid);
		
		btnTrain = new JButton("Train");
		btnTrain.addActionListener(new BtnTrainActionListener());
		btnTrain.setBounds(106, 515, 117, 25);
		contentPane.add(btnTrain);
		
		btnRecognise = new JButton("Recognise");
		btnRecognise.addActionListener(new BtnRecogniseActionListener());
		btnRecognise.setBounds(325, 515, 117, 25);
		contentPane.add(btnRecognise);
	}
	private class BtnTrainActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			net.train(grid.getImage());
		}
	}
	private class BtnRecogniseActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			grid.setImage(net.recognise(grid.getImage()));
		}
	}
}
