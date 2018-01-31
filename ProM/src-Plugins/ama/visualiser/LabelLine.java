package ama.visualiser;

import javax.swing.JLabel;

public class LabelLine {
	private JLabel labelStart;
	private JLabel labelEnd;
	
	public LabelLine(JLabel labelStart, JLabel labelEnd)
	{
		this.labelStart = labelStart;
		this.labelEnd = labelEnd;
	}
	
	public JLabel getLabelStart() {
		return labelStart;
	}
	
	public void setLabelStart(JLabel labelStart) {
		this.labelStart = labelStart;
	}
	
	public JLabel getLabelEnd() {
		return labelEnd;
	}
	
	public void setLabelEnd(JLabel labelEnd) {
		this.labelEnd = labelEnd;
	}
}
