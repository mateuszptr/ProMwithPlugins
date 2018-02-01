package ama.visualiser;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JPanel2 extends JPanel {
	private List<LabelLine> lines = new ArrayList<LabelLine>();
		
	public void addLine2(LabelLine line) {
		lines.add(line);
	}
		
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(LabelLine line : lines) {
			//System.out.println(line.getLabelStart().getLocation() + " " + line.getLabelEnd().getLocation());
			//System.out.println(SwingUtilities.convertPoint(line.getLabelStart(), line.getLabelStart().getX(), line.getLabelStart().getY(), this));
			
			//System.out.println(line.getLabelStart().getParent().getClass());
						
			Point pointStart = SwingUtilities.convertPoint(line.getLabelStart().getParent(), line.getLabelStart().getX(), line.getLabelStart().getY(), this);
			Point pointEnd = SwingUtilities.convertPoint(line.getLabelEnd().getParent(), line.getLabelEnd().getX(), line.getLabelEnd().getY(), this);
						
			g.drawLine(pointStart.x+line.getLabelStart().getWidth()/2, pointStart.y+line.getLabelStart().getHeight()-VisualiserConfig.margin, pointEnd.x+line.getLabelEnd().getWidth()/2, pointEnd.y+VisualiserConfig.margin);
			
			g.drawPolygon(new Polygon());
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
