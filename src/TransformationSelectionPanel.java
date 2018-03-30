import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Panel for selecting the transformation to perform on the loaded image.
 * @author JLepere2
 * @date 03/29/2018
 */
public class TransformationSelectionPanel extends JPanel {

	/**
	 * Creates the selection panel for making transformations on the loaded image.
	 * @param m the Model for MVC.
	 */
	public TransformationSelectionPanel(Model m) {
		
		this.setLayout(new GridLayout(1, 1));
		
		BitPrecisionSlider bPS = new BitPrecisionSlider(m);
		
		this.add(bPS);
		
	}
	
	private static final long serialVersionUID = 2232421L;

}
