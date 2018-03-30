import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Panel for the loaded and transformed images.
 * @author JLepere2
 * @date 03/29/2017
 */
public class ImagesPanel extends JPanel {

	/**
	 * Creates the panel for housing the image components.
	 * @param m the Model for MVC
	 */
	public ImagesPanel(Model m) {
		this.setLayout(new BorderLayout());
		
		TransformedImageComponent tIC = new TransformedImageComponent(m);
		LoadedImageComponent lIC = new LoadedImageComponent(m);
		
		this.add(lIC, BorderLayout.WEST);
		this.add(tIC, BorderLayout.EAST);
	}
	
	private static final long serialVersionUID = 1353901L;
	
}
