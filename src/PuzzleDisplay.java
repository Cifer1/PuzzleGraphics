import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class PuzzleDisplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuzzleDisplay frame = new PuzzleDisplay();
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
	public PuzzleDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel puzzlePanel = new PuzzlePanel();
		contentPane.add(puzzlePanel, BorderLayout.CENTER);
		
		JPanel sidePanel = new SidePanel();
		contentPane.add(sidePanel, BorderLayout.EAST);
		
		JPanel controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.NORTH);
		
		JButton resetButton = new JButton("Reset");
		controlPanel.add(resetButton);
		
		JButton solveButton = new JButton("Solve");
		controlPanel.add(solveButton);
	}

}
