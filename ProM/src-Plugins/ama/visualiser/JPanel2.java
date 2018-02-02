package ama.visualiser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JPanel2 extends JPanel {
	private List<LabelLine> lines = new ArrayList<LabelLine>();
	private List<LabelLine> serviceLines = new ArrayList<LabelLine>();
	
	public void addLine(LabelLine line) {
		lines.add(line);
	}
	
	public void setServiceLines (List<LabelLine> serviceLines) {
		this.serviceLines = serviceLines;
	}
		
	@Override
	public void paint(Graphics g) {
		super.paint(g);
				
		for(LabelLine line : lines) {						
			Point pointStart = SwingUtilities.convertPoint(line.getLabelStart().getParent(), line.getLabelStart().getX(), line.getLabelStart().getY(), this);
			Point pointEnd = SwingUtilities.convertPoint(line.getLabelEnd().getParent(), line.getLabelEnd().getX(), line.getLabelEnd().getY(), this);
			
			pointStart.x += line.getLabelStart().getWidth()/2;
			pointStart.y += line.getLabelStart().getHeight()-VisualiserConfig.margin;
			pointEnd.x += line.getLabelEnd().getWidth()/2;
			pointEnd.y += VisualiserConfig.margin/2;
			
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);			

			double angle = (pointEnd.y - pointStart.y) == 0 ? Math.PI : Math.PI - Math.atan((pointEnd.x - pointStart.x) / (pointEnd.y - pointStart.y));
			double triangleWidth = 8;
			double triangleHeight = 8;
			Polygon triangle = new Polygon();
			Double[] coordinates = {-triangleWidth/2, triangleWidth/2, 0.0, triangleHeight, triangleHeight, -triangleHeight};
			Double[] coordinatesPrim = new Double[6];
			for	(int i = 0; i < 3; i++) {
				coordinatesPrim[i] = coordinates[i] * Math.cos(angle) - coordinates[i+3] * Math.sin(angle);
				coordinatesPrim[i+3] = coordinates[i] * Math.sin(angle) + coordinates[i+3] * Math.cos(angle);
				
				coordinatesPrim[i] += pointEnd.x;
				coordinatesPrim[i+3] += pointEnd.y;
				
				triangle.addPoint(coordinatesPrim[i].intValue(), coordinatesPrim[i+3].intValue());
			}
			
			g.drawPolygon(triangle);
			g.setColor(Color.BLACK);
			g.fillPolygon(triangle);
		}
		
		for(LabelLine line : serviceLines) {
			Point pointStart = SwingUtilities.convertPoint(line.getLabelStart().getParent(), line.getLabelStart().getX(), line.getLabelStart().getY(), this);
			Point pointEnd = SwingUtilities.convertPoint(line.getLabelEnd().getParent(), line.getLabelEnd().getX(), line.getLabelEnd().getY(), this);
			
			if(pointStart.x < pointEnd.x) {
				pointStart.x += line.getLabelStart().getWidth() - VisualiserConfig.margin;
				pointStart.y += line.getLabelStart().getHeight()/2;
				pointEnd.x += VisualiserConfig.margin/2;
				pointEnd.y += line.getLabelEnd().getHeight()/2;
			}
			else {
				pointStart.x += VisualiserConfig.margin;
				pointStart.y += line.getLabelStart().getHeight()/2;
				pointEnd.x += line.getLabelEnd().getWidth() - VisualiserConfig.margin/2;
				pointEnd.y += line.getLabelEnd().getHeight()/2;
			}
			
			g.setColor(Color.BLUE);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);			
			
			double angle = (-pointEnd.y + pointStart.y) == 0 ? Math.PI/2 : Math.atan((pointEnd.x - pointStart.x) / (-pointEnd.y + pointStart.y));
			double triangleWidth = 8;
			double triangleHeight = 8;
			Polygon triangle = new Polygon();
			Double[] coordinates = {-triangleWidth/2, triangleWidth/2, 0.0, triangleHeight, triangleHeight, -triangleHeight};
			Double[] coordinatesPrim = new Double[6];
			for	(int i = 0; i < 3; i++) {
				coordinatesPrim[i] = coordinates[i] * Math.cos(angle) - coordinates[i+3] * Math.sin(angle);
				coordinatesPrim[i+3] = coordinates[i] * Math.sin(angle) + coordinates[i+3] * Math.cos(angle);
				
				coordinatesPrim[i] += pointEnd.x;
				coordinatesPrim[i+3] += pointEnd.y;
				
				triangle.addPoint(coordinatesPrim[i].intValue(), coordinatesPrim[i+3].intValue());
			}
			
			g.drawPolygon(triangle);
			g.setColor(Color.BLUE);
			g.fillPolygon(triangle);
		}
	}
}
