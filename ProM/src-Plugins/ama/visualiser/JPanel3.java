package ama.visualiser;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JPanel3 extends JPanel {
	private BufferedImage graphics;

	public void setGraphics(BufferedImage g) {
		this.graphics = g;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(graphics, 0, 0, null);
	}
}
