import java.awt.Graphics;

import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PuzzlePanel() {

	}
	public void paintComponent(Graphics g){
		int botLeftX = getWidth()/4;
		int botLeftY = getHeight()/4;
		int sideLen = Math.min(getWidth()/2, getHeight()/2);
		g.drawRect(botLeftX, botLeftY, sideLen, sideLen);
		
		int horizLineInterval = (sideLen)/3;
		int vertLineInterval  = (sideLen)/3;
		
		for(int i = 1; i < 3; i++){
			g.drawLine(botLeftX+horizLineInterval*i, botLeftY, botLeftX+horizLineInterval*i, botLeftY+sideLen);
			g.drawLine(botLeftX, botLeftY+vertLineInterval*i, botLeftX+sideLen, botLeftY+vertLineInterval*i);

		}
	}

}
