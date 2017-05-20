import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/* In the display class, the private data is a content pane. We create solve and reset buttons and a JPanel on which
 everything is drawn. */
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100, (int)screenSize.getWidth()/2, (int)screenSize.getWidth()/2);
		//setBounds((int)screenSize.getHeight()-100, (int)screenSize.getWidth()-100, (int)screenSize.getWidth()/2, (int)screenSize.getWidth()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Creating the control panel
		JPanel controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.NORTH);
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		
		// Creating the puzzle panel with the title Puzzle
		PuzzlePanel puzzlePanel = new PuzzlePanel();
		puzzlePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Puzzle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(puzzlePanel, BorderLayout.CENTER);
		// Creating a JButton for solve
		JButton solveButton = new JButton("Solve");
		// when the solve button is clicked, the solve method is called
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				puzzlePanel.solve();
				repaint();
			}
		});
		solveButton.setHorizontalAlignment(SwingConstants.LEFT);
		controlPanel.add(solveButton);
		// Creating a JButton for reset
		JButton resetButton = new JButton("Reset");
		// when the button is clicked, the reset method is called
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				puzzlePanel.reset();
				repaint();
			}
		});
		resetButton.setHorizontalAlignment(SwingConstants.LEFT);
		controlPanel.add(resetButton);
		// Adding a label to give instructions on how to click
		JLabel lblRotateControllclick = new JLabel("Rotate: Control+Click ");
		controlPanel.add(lblRotateControllclick);
		
		
		
	}


}
