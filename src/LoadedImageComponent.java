import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Model;

/**
 * The Loaded Image Component of the application.
 * @author JLepere2
 * @date 03/28/2018
 */
public class LoadedImageComponent extends JLabel {

	/**
	 * Creates a Loaded Image Component representing the loaded image for processing.
	 * @param m the Model for MVC
	 */
	public LoadedImageComponent(Model m) {
		
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setToolTipText("Select to load a new image.");
		
		JFileChooser imageChooser = new JFileChooser();
		imageChooser.setFileFilter(new FileNameExtensionFilter("JPEG FILES", "jpg", "jpeg"));
		
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if (imageChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File image = imageChooser.getSelectedFile();
					try {
						loadImage(image, m);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		try {
			loadImage(new File(defaultImagePath), m);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private void loadImage(File image, Model m) throws IOException {
		/*
		 * Load the default Lena image and creates the loaded gray scale image buffer.
		 * https://groups.google.com/forum/#!msg/comp.lang.java.programmer/EwqPncLARFQ/1M7UZKs2xNQJ
		 */
		BufferedImage defaultImage = ImageIO.read(image);
		loadedImage = new BufferedImage(defaultImage.getWidth(), defaultImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	    ColorConvertOp op = new ColorConvertOp(defaultImage.getColorModel().getColorSpace(), loadedImage.getColorModel().getColorSpace(), null);
	    op.filter(defaultImage, loadedImage);
	    
	    // Set icon.
	 	ImageIcon icon = new ImageIcon(loadedImage);
	 	this.setIcon(icon);
	    
	    /*
	     * Loads the pixel matrix from the gray scale values.
	     * Each rgb pixel is in the following format:
	     * 	aaaaaaaa | rrrrrrrr | gggggggg | bbbbbbbb
	     * Since the image was set to gray scale (BufferedImage.TYPE_BYTE_GRAY),
	     *  the r, g and b components are all the same. Therefore, we need only
	     *  extract of these values to store in the matrix.
	     */
	    int[][] pixelMatrix = new int[loadedImage.getHeight()][loadedImage.getWidth()];
		for (int y = 0; y < loadedImage.getHeight(); y ++) {
			for (int x = 0; x < loadedImage.getWidth(); x ++) {
				int p = loadedImage.getRGB(x, y);
				//pixelMatrix[y][x] = p & 0xff;
				pixelMatrix[y][x] = p;
			}
		}
		
		// Alert Model.
		m.setPixelMatrix(pixelMatrix);
		
	}
	
	private BufferedImage loadedImage;
	private static final String defaultImagePath = "lena.jpg";
	private static final long serialVersionUID = 113423452L;

}