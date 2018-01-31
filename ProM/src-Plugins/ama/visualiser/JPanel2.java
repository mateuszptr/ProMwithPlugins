package ama.visualiser;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JPanel2 extends JPanel {
	private List<LabelLine> lines2 = new ArrayList<LabelLine>();
		
	public void addLine2(LabelLine line) {
		lines2.add(line);
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(LabelLine line : lines2) {
			//System.out.println(line.getLabelStart().getLocation() + " " + line.getLabelEnd().getLocation());
			//System.out.println(SwingUtilities.convertPoint(line.getLabelStart(), line.getLabelStart().getX(), line.getLabelStart().getY(), this));
			
			System.out.println(line.getLabelStart().getParent().getClass());
			
			
			Point pointStart = SwingUtilities.convertPoint(line.getLabelStart().getParent(), line.getLabelStart().getX(), line.getLabelStart().getY(), this);
			Point pointEnd = SwingUtilities.convertPoint(line.getLabelEnd().getParent(), line.getLabelEnd().getX(), line.getLabelEnd().getY(), this);
			
			//g.drawLine(line.getLabelStart().getX(),line.getLabelStart().getY(),line.getLabelEnd().getX(),line.getLabelEnd().getY());
			
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
		}
//		System.out.println(lines2.size());
//		System.out.println(this.getComponentCount());
//		System.out.println(this.getX()+ " "+this.getY());
//		System.out.println(this.getWidth()+ " "+this.getHeight());
		//System.out.println(this.getParent());
		//System.out.println(this.getTopLevelAncestor());
		//System.out.println(this.getRootPane());
		//getParent
		//getTopLevelAncestor
		//getRootPane
	}
}
