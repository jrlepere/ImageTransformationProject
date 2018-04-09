import main_frame.MainFrame;
import model.Model;

/**
 * Main class to run the Image Transformation Project
 * @author JLepere2
 * @date 03/29/2018
 */
public class Driver {

	/**
	 * Main method to run the project.
	 * @param args command line arguments, not used.
	 */
	public static void main(String[] args) {
		
		Model m = new Model();
		
		MainFrame frame = new MainFrame(m);
		frame.setVisible(true);
		
	}
	
}
