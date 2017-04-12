import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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

public class PuzzleDisplay extends JFrame {

	private JPanel contentPane;
	private JPanel sidePanel;

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
		
				JPanel controlPanel = new JPanel();
				contentPane.add(controlPanel, BorderLayout.NORTH);
				controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JButton solveButton = new JButton("Solve");
				solveButton.setHorizontalAlignment(SwingConstants.LEFT);
				controlPanel.add(solveButton);
				
				JButton resetButton = new JButton("Reset");
				resetButton.setHorizontalAlignment(SwingConstants.LEFT);
				controlPanel.add(resetButton);
		JPanel puzzlePanel = new PuzzlePanel();
		puzzlePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Puzzle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(puzzlePanel, BorderLayout.CENTER);
		
		sidePanel = new SidePanel();
		sidePanel.setBorder(new TitledBorder(null, "Pieces", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(sidePanel, BorderLayout.SOUTH);
		sidePanel.setMinimumSize(new Dimension(getWidth(), getHeight()/2));
		System.out.println(sidePanel.getBounds());
		
		
		
	}


}
