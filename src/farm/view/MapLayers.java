package farm.view;

import farm.model.Component;
import farm.model.StoragePlace;

import java.util.HashMap;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * MapLayers keeps track of everything displayed on the map with the exception of the drone.
 * Notably: aerialPhotography, propertyBoundary, and ImageViews for the Component.
 */
public class MapLayers {
	// Destination for the maps graphics to be drawn onto should be passed from the InterfaceBoundary class.
	private Pane pane;

	// This stores the Component UUID with their ImageViews
	private HashMap<Integer, ImageView> hashIdImageViews = new HashMap<Integer, ImageView>();

	// Rectangle for farm's property boundary
	private Rectangle propertyBoundaryRect;

	// this is the Aerial Photo
	private ImageView aerialImageView = new ImageView("/img/aerial/farm_raster.jpeg");

	/*
	 * Constructor, this is the pane from the InterfaceBoundary
	 */
	public MapLayers(Pane pane) {
		this.pane = pane;
	}
	
	/*
	 * Draws a Component object on the map at a particular pixel size width.
	 */
	public void drawComponentOnMap(Component comp, Integer size) {
//		System.out.print("URL: " + comp.getImageURL() + "\n");
		
    	ImageView anImageView = new ImageView(comp.getImageURL());

    	anImageView.setPreserveRatio(true);
    	anImageView.setFitWidth(size);
    	
    	anImageView.setX(comp.getLocationX());
    	anImageView.setY(comp.getLocationY());
    	
    	// adds the ImageView to the on screen "map"
    	pane.getChildren().add(anImageView);
    	
    	// store the Key = Component's UUID, Value = ImageView
    	hashIdImageViews.put(comp.getId(), anImageView);

 //    	System.out.print("add id: " + comp.getId() + "\n");
	}

	/*
	 * Moves the ImageView on the map.
	 * 
	 * returns true on success, false if there is not an ImageView associated with the Component.
	 */	
	public boolean moveComponentOnMap(Component comp) {
//		System.out.print("moveComponentOnMap()\n");
//		System.out.print("this id: " + comp.getId() + "\n");
		
		// if the ID is not in the hash, there is no ImageView associated, so skip moving the ImageView
		if (hashIdImageViews.containsKey(comp.getId()) == false) {
//			System.out.print(hashIdImageViews.containsValue(comp.getId()));
			return false;
		}
		
		ImageView anImageView = hashIdImageViews.get(comp.getId());
    	anImageView.setX(comp.getLocationX());
    	anImageView.setY(comp.getLocationY());
    	
    	return true;
	}
	
	/*
	 * Delete the ImageView from the map.
	 * 
	 * returns true on success, false if there is not an ImageView associated with the Component.
	 */
	public boolean deleteComponentOnMap(Component comp) {
		// if the ID is not in the hash, there is no ImageView associated, so skip deletingImageView
		if (hashIdImageViews.containsKey(comp.getId()) == false) {
			return false;
		}
		ImageView anImageView = hashIdImageViews.get(comp.getId());
		
		// This is not as good of an alternative.
		// anImageView.setVisible(false);
			
		if(comp instanceof StoragePlace) {
			for (Component childComp : comp.getChildren()) {
				// recursively call delete on all the elements that this one stores
				deleteComponentOnMap(childComp);
			}
		}
		pane.getChildren().remove(anImageView);
		
		// remove the ImageView from the hash
		hashIdImageViews.remove(comp.getId());
		
		return true;
	}
	
	/*
	 * Draw the farm's property boundary to the anchorPane.
	 */
	public void initializePropertyBoundary() {
		// Rectangle(double x, double y, double width, double height)
//		propertyBoundaryRect = new Rectangle(20, 20, 800 + 96, 600 + 86);		
		propertyBoundaryRect = new Rectangle(20, 20, 800, 600);
		propertyBoundaryRect.setFill(Color.TRANSPARENT);
    	propertyBoundaryRect.setStroke(Color.BLACK);
    	propertyBoundaryRect.getStrokeDashArray().addAll(50.0, 10.0, 5.0, 10.0);
    	propertyBoundaryRect.setStrokeWidth(1);

    	// Default to not showing the Property Boundary on startup
    	propertyBoundaryRect.setVisible(false);
    	pane.getChildren().add(propertyBoundaryRect);
	}

	/*
	 * Boolean value telling the farm's property boundary is visible.
	 */
	public void propertyBoundarySetVisible(boolean visible) {
		propertyBoundaryRect.setVisible(visible);
	}
	
	/*
	 * Initialized the aerial photography.
	 */
	public void initializeAerialPhoto() {
		aerialImageView.setVisible(false);  // not visible when first added
		aerialImageView.setPreserveRatio(true);
		aerialImageView.setTranslateX(-20);
		aerialImageView.setTranslateY(-50);
		aerialImageView.setFitWidth(1140);
		
		pane.getChildren().add(aerialImageView);
		// Change the Z-order of the aerial photography such that it is placed behind the drone's image and the property boundary rectangle.
		aerialImageView.toBack();
	}

	/*
	 * Boolean value telling the farm's property boundary is visible.
	 */
	public void aerialPhotoSetVisible(boolean visible) {
		aerialImageView.setVisible(visible);
		
		// When the aerial photo is on, it shifts the property boundary to white, then when off it shifts to black
    	if(visible == true) {
    		propertyBoundaryRect.setStroke(Color.WHITE);    		
    	} else {
    		propertyBoundaryRect.setStroke(Color.BLACK);
    	}	
	}

}
