import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


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
	private JButton btnClear;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmLoad;
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
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new mntmSaveActionListener());
		mnFile.add(mntmSave);
		
		mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new mntmLoadActionListener());
		mnFile.add(mntmLoad);
		
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
		btnTrain.setBounds(66, 488, 117, 25);
		contentPane.add(btnTrain);
		
		btnRecognise = new JButton("Recognise");
		btnRecognise.addActionListener(new BtnRecogniseActionListener());
		btnRecognise.setBounds(217, 488, 117, 25);
		contentPane.add(btnRecognise);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new BtnClearActionListener());
		btnClear.setBounds(377, 488, 117, 25);
		contentPane.add(btnClear);
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
	private class BtnClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			grid.clear();
		}
	}
	private class mntmLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Image img = Storage.loadImage();
			grid.setImage(img);
		}
	}
	private class mntmSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Storage.saveImage(grid);
		}
	}
}
