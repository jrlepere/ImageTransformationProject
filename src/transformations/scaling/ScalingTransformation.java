package transformations.scaling;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main_frame.slider_component.NoValueConversion;
import main_frame.slider_component.SliderComponent;
import model.Model;
import transformations.TransformationAlgorithm;

/**
 * Abstract class for definition of scaling algorithms.
 * @author JLepere2
 * @date 03/31/3018
 */
public abstract class ScalingTransformation implements TransformationAlgorithm {

	/**
	 * Abstract scaling transformation algorithm definition.
	 */
	public ScalingTransformation(Model m) {
		
		// Initial resolution
		zoomInResolution = Model.MAX_RESOLUTION;
		zoomOutResolution = Model.MAX_RESOLUTION;
		
		// Frame Initialization
		parameterSelectionFrame = new JFrame("Parameter Selection: " + toString());
		parameterSelectionFrame.setLayout(new GridLayout(2, 1));
		parameterSelectionFrame.setSize(PARAMETER_FRAME_WIDTH, PARAMETER_FRAME_HEIGHT);
		parameterSelectionFrame.setLocationRelativeTo(null);
		parameterSelectionFrame.setAlwaysOnTop(true);
		parameterSelectionFrame.setResizable(false);
		parameterSelectionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// zoom slider components
		SliderComponent zoomInSlider = new SliderComponent(m, " Zoom in: ", Model.MIN_RESOLUTION, Model.MAX_RESOLUTION, Model.MAX_RESOLUTION, new NoValueConversion());
		SliderComponent zoomOutSlider = new SliderComponent(m, " Zoom out: ", Model.MIN_RESOLUTION, Model.MAX_RESOLUTION, Model.MAX_RESOLUTION, new NoValueConversion());
		
		zoomInSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoomInResolution = zoomInSlider.getValue();
				zoomOutSlider.setMinimum(zoomInResolution);
				m.algorithmParametersChanged();
			}
		});
		
		zoomOutSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoomOutResolution = zoomOutSlider.getValue();
				m.algorithmParametersChanged();
			}
		});
		
		zoomOutSlider.setMinimum(zoomInResolution);
		
		parameterSelectionFrame.add(zoomInSlider);
		parameterSelectionFrame.add(zoomOutSlider);
		
		parameterSelectionFrame.pack();
		
	}
	
	public void showSelectionFrame(boolean visible) {
		parameterSelectionFrame.setVisible(visible);
	}
	
	protected int[][] scaleDown(int[][] pixelMatrix) {
		
		// temp matrix
		int[][] temp = new int[zoomInResolution][zoomInResolution];
				
		// ratio to scale down the image from 512 to zoomInResolution
		double ratio = ((double) pixelMatrix.length) / zoomInResolution;
		
		// set temp matrix
		for (int y = 0; y < zoomInResolution; y ++) {
			for (int x = 0; x < zoomInResolution; x ++) {
				temp[y][x] = pixelMatrix[(int) (y * ratio)][(int) (x * ratio)];
			}
		}
		
		return temp;
	}
	
	protected int zoomInResolution;
	protected int zoomOutResolution;
	protected JFrame parameterSelectionFrame;
	private static final int PARAMETER_FRAME_WIDTH = 600;
	private static final int PARAMETER_FRAME_HEIGHT = 200;
	
}
