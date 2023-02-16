package farm.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.Optional;

import farm.MainApp;
import farm.MarketPriceVisitor;
import farm.PurchasePriceVisitor;
import farm.model.FarmItem;
import farm.model.StoragePlace;
import farm.model.TelloDrone;
import farm.model.Adapter;

import farm.model.Component;


public class InterfaceBoundary  {
	// Related to what type of scan a drone is choosen by the user.
	@FXML
	private RadioButton scanFarmRadio, scanItemRadio;
	
    private Stage dialogStage;
	
	private MainApp mainApp;
	
	/* NOTE: This name could be better.  It should probably be droneImage.  This is an image representing the drone. */
	@FXML
	protected ImageView drone;
	
	@FXML
	protected AnchorPane dashboard;
	
	
	private DroneDisplay droneDisplay;
	
	/* Controls in the Upper Left Quadrant */

	@FXML 
	private TreeView <Component> itemTreeView;
	// This is the model representation of the Component class, this is how we store the tree.
	private Component itemComponents;
	
	@FXML
	private Button btnRename,btnChangeLocation,btnChangePrice,btnChangeDimensions,btnDelete,btnAddItem,btnAddItemContainer;
	// Drone related buttons
	@FXML
	private Button flyDroneButton, flyAgainButton, btnLaunchDrone;
	@FXML
	private Button btnChangeMarketValue;
	
	// Labels related to the Market Value and Purchase Price
	@FXML
	private Label lblCurrentMarketValue;
	@FXML
	private Label lblPurchasePrice;
	@FXML
	private Label lblDescription;
	
	// these are CheckBoxes
	@FXML
	private CheckBox cbAerialPhoto, cbPropertyBoundary;
	
	//Image icon
//	private Image folderIcon = new Image(getClass().getResourceAsStream("/img/folder-icon.png"));
//	private Image fileIcon = new Image(getClass().getResourceAsStream("/img/file-icon.png"));
	private Image folderIcon = new Image("/img/folder-icon.png");
	private Image fileIcon = new Image("/img/file-icon.png");

	// private ImageView aerialImageView = new ImageView("/img/aerial/farm_raster.jpeg");
	// private ImageView aerialImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/aerial/farm_raster.jpeg")));
	

	
	private MapLayers mapLayers;
		
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
 
	// Function shows/hides buttons based on the class of the Component.
    public void onSelectionChange () {
		TreeItem<Component> item = itemTreeView.getSelectionModel().getSelectedItem();
		
		// no item selected, so hide the buttons.
		if(item == null) {
    		btnRename.setVisible(false);
    		btnChangeLocation.setVisible(false);
    		btnChangePrice.setVisible(false);
    		btnChangeDimensions.setVisible(false);
    		btnDelete.setVisible(false);
    		btnChangeMarketValue.setVisible(false);
    		btnAddItem.setVisible(false);
    		btnAddItemContainer.setVisible(false);
    		return;
		}
		
		// System.out.println("class is: " + item.getValue().getClass());
		
		// Is the selected item of the StoragePlace class?
    	if (item.getValue() instanceof StoragePlace) {
    		// Show all the buttons.
    		btnRename.setVisible(true);
    		btnChangeLocation.setVisible(true);
    		btnChangePrice.setVisible(true);
    		btnChangeDimensions.setVisible(true);
    		btnDelete.setVisible(true);
    		btnChangeMarketValue.setVisible(false);
    		btnAddItem.setVisible(true);
    		btnAddItemContainer.setVisible(true);		
    	} else { /* default for FarmItems and other classes */
    		btnRename.setVisible(true);
    		btnChangeLocation.setVisible(true);
    		btnChangePrice.setVisible(true);
    		btnChangeDimensions.setVisible(true);
    		btnDelete.setVisible(true);
    		btnChangeMarketValue.setVisible(true);
    		btnAddItem.setVisible(false);
    		btnAddItemContainer.setVisible(false);	
    	}
    }
    
    // Singleton
	private static InterfaceBoundary instance; 
	
    public static InterfaceBoundary getInstance() {

    	if(instance == null){
    	       instance = new  InterfaceBoundary();
    	}
    	return instance;
    }


    
    /*
     *  Show/Hide the Farm's Property Boundary
     */
    @FXML
	private void handleCheckBoxPropertyBoundary() {   	
	   	// toggle the boundaryVisible variable
    	boolean boundaryVisible = cbPropertyBoundary.isSelected();

    	mapLayers.propertyBoundarySetVisible(boundaryVisible);
    }
    
    /*
     *  Show/Hide the Farm's Aerial Photo
     */
    @FXML
	private void handleCheckBoxAerialPhoto() {   	
	   	// toggle the aerialPhotoVisible variable
    	boolean aerialPhotoVisible = cbAerialPhoto.isSelected();

    	mapLayers.aerialPhotoSetVisible(aerialPhotoVisible);
    }

	@FXML
	private void handleFlyDroneButton() throws InterruptedException, IOException {
		System.out.println("DEBUG: calling handleFlyDroneButton()");
		
		
		// The below code works however, for some reason the radio buttons are not working..
		if(scanFarmRadio.isSelected()) {
			System.out.println("DEBUG: Dispatch Drone to Scan the Farm");
			droneDisplay.scanFarm();
			flyDroneButton.setDisable(true);
		} else if(scanItemRadio.isSelected()) {

			if(itemTreeView.getSelectionModel().getSelectedItem() != null) {  
				System.out.println("DEBUG: Dispatch Drone to Scan an Item");
				
				Component comp = itemTreeView.getSelectionModel().getSelectedItem().getValue();
				droneDisplay.scanItem(comp.getLocationX(), comp.getLocationY(), comp.getHeight());
				
			} else {
				// no item selected
				// Alert user that no item is selected.
				Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Item Selected.");
	            alert.setHeaderText("Choose an option from item.");
	            alert.setContentText("Please choose a item before trying to fly the drone.");
	            
	            alert.showAndWait();
			}
	       


			flyDroneButton.setDisable(true);
		} else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No radio chosen.");
            alert.setHeaderText("Choose an option from radio.");
            alert.setContentText("Please choose a radio option before trying to fly the drone.");
            
            alert.showAndWait();
			
		}
	}
	
	@FXML
	private void onBtnFlyAgain(){
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Information");
        alert.setHeaderText("When drone is flying, do not Refly.");
        
        alert.showAndWait();
        flyDroneButton.setDisable(false);
	}

	public void onBtnRename(ActionEvent event) {
		TreeItem<Component> item = itemTreeView.getSelectionModel().getSelectedItem();

		// Set the dialog box field to the name of the component
		TextInputDialog dialog = new TextInputDialog(item.getValue().getName());
		
		dialog.setTitle("Rename");
		dialog.setHeaderText("Enter name to update.");
		dialog.setGraphic(null);
		Optional<String> result = dialog.showAndWait();
		
		if(!result.isPresent()) {
			return;
		}
		// Proceed with renaming.
		String newName = result.get();
		item.getValue().setName(newName);
		
		// update itemTreeView, so that the renamed item can appear
		itemTreeView.refresh();
	}

	public void onBtnChangeLocation(ActionEvent event) {
		// Get the item selected on the TreeView
       	TreeItem<Component> selectedTreeItemComponent = itemTreeView.getSelectionModel().getSelectedItem();
       	
	    // no item selected
	    if(selectedTreeItemComponent == null) {
			// Alert user that no item is selected.
			Alert alert = new Alert(AlertType.ERROR);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Item Selected.");
	        alert.setHeaderText("Choose an option from item.");
	        alert.setContentText("Please choose a item.");
	        
	        alert.showAndWait();
	        return;
	    }
	    
       	Component comp = selectedTreeItemComponent.getValue();

		Dialog<Pair<String, String>> dialog = new Dialog<>();
	    dialog.setTitle("Change location");

	    // Set the button types.
	    ButtonType loginButtonType = new ButtonType("OK");	
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        // convert the x & y values to a string
        String xStr = Integer.toString(comp.getLocationX());
        String yStr = Integer.toString(comp.getLocationY());

        // Feed those values into the TextField
        TextField xTextField = new TextField(xStr);
        TextField yTextField = new TextField(yStr);
        
        xTextField.setPromptText("X");
        yTextField.setPromptText("Y");
        gridPane.add(new Label("X:"), 0, 0);
        gridPane.add(xTextField, 0, 1);
        gridPane.add(new Label("Y:"), 0, 2);
        gridPane.add(yTextField, 0, 3);

        dialog.getDialogPane().setContent(gridPane);

        Platform.runLater(() -> xTextField.requestFocus());
        dialog.setResultConverter(dialogButton -> {
        	if (dialogButton == loginButtonType) {
        		return new Pair<>(xTextField.getText(), yTextField.getText());
        	}
        	return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        
        Pair<String, String> pair = result.get();
        Integer x = Integer.parseInt(pair.getKey());
        Integer y = Integer.parseInt(pair.getValue());

        if (x>800 || x<0 || y>600 || y<0){
        	
//        	result = dialog.showAndWait();
//        	pair = result.get();
//        	x = Integer.parseInt(pair.getKey());
//        	y = Integer.parseInt(pair.getValue());
        	
        	Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error");
            alert.setHeaderText("x should be in  range 0 to 800\ny should be in range 0 to 600");
            
            alert.showAndWait();
        }


        // TODO: Add alert if X (800) and Y (600) are out of bounds
        result.ifPresent(p -> {
	        System.out.println("X=" + p.getKey() + ", Y=" + p.getValue());
	
	        comp.setLocationX(Integer.parseInt(p.getKey()));
	        comp.setLocationY(Integer.parseInt(p.getValue()));

	        mapLayers.moveComponentOnMap(comp);
        });
	}

	
	public void onBtnChangePrice(ActionEvent event) {
	    TreeItem<Component> item = itemTreeView.getSelectionModel().getSelectedItem();
		Component comp = item.getValue();
		
		// convert the price double to a string
		String strPrice = String.format("%.2f", comp.getPriceOnly());
		
		TextInputDialog dialog = new TextInputDialog(strPrice);
		
		dialog.setTitle("Enter Price");
		dialog.setHeaderText("Enter Price to update.");
		dialog.setGraphic(null);
		Optional<String> result = dialog.showAndWait();
		 
		if (result.isPresent()) {
			// set the purchase price, if present
			comp.setPrice(Double.parseDouble(result.get()));
		}
		
		// call function to update the price labels
		updatePriceLabels(comp);
	}
	
	/*
	 * This is a carb
	 */
	public void onBtnChangeMarketValue(ActionEvent event) {
	    TreeItem<Component> item = itemTreeView.getSelectionModel().getSelectedItem();
	    Component comp = item.getValue();
		
		// convert the price double to a string
		String strPrice = String.format("%.2f", comp.getMarketValue());
		
		TextInputDialog dialog = new TextInputDialog(strPrice);
		
		dialog.setTitle("Enter Market Value");
		dialog.setHeaderText("Enter Market Value to update.");
		dialog.setGraphic(null);
		Optional<String> result = dialog.showAndWait();
		 
		if (result.isPresent()) {
			// set the price
			comp.setMarketValue(Double.parseDouble(result.get()));
		}
		
		// call function to update the price labels
		updatePriceLabels(comp);
	}
	
	

	public void onBtnChangeDimensions(ActionEvent event) {
	    TreeItem<Component> selectedTreeItemComponent = itemTreeView.getSelectionModel().getSelectedItem();
		Component comp = selectedTreeItemComponent.getValue();
		
		Dialog<Pair<String, Pair<String, String>>> dialog = new Dialog<>();
	    dialog.setTitle("Change location");

	    // Set the button types.
	    ButtonType loginButtonType = new ButtonType("OK");	
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(30, 150, 20, 20));

        // convert the length, width, & height (Integers) values to Strings
        String lengthStr = Integer.toString(comp.getLength());
        String widthStr = Integer.toString(comp.getWidth());
        String heightStr = Integer.toString(comp.getHeight());

        // Feed those Strings into the TextField
        TextField lengthTextField = new TextField(lengthStr);
        lengthTextField.setPromptText("Length");
        
        TextField widthTextField = new TextField(widthStr);
        widthTextField.setPromptText("Width");
        
        TextField heightTextField = new TextField(heightStr);
        heightTextField.setPromptText("Height");
       
        gridPane.add(new Label("Length"), 0, 0);
        gridPane.add(lengthTextField, 1, 0);
        gridPane.add(new Label("Width:"), 0, 1);
        gridPane.add(widthTextField, 1, 1);
        gridPane.add(new Label("Height:"), 0, 2);
        gridPane.add(heightTextField, 1, 2);

        dialog.getDialogPane().setContent(gridPane);

        Platform.runLater(() -> lengthTextField.requestFocus());
        dialog.setResultConverter(dialogButton -> {
        	if (dialogButton == loginButtonType) {
        	return new Pair<>(lengthTextField.getText(), new Pair<>(widthTextField.getText(),heightTextField.getText()));
        	}
        	return null;
        });

        Optional<Pair<String, Pair<String, String>>> result = dialog.showAndWait();

        result.ifPresent(pair -> {        	
				
			comp.setLength(Integer.parseInt(pair.getKey()));
			comp.setWidth(Integer.parseInt(pair.getValue().getKey())); 
			comp.setHeight(Integer.parseInt(pair.getValue().getValue()));
			
        });
		
		
	}
	

	public void onBtnDelete(ActionEvent event) {			
		TreeItem<Component> selectedTreeItemComponent = itemTreeView.getSelectionModel().getSelectedItem();
	    Component parent = selectedTreeItemComponent.getParent().getValue();
	    Component removeComp = selectedTreeItemComponent.getValue();
	    selectedTreeItemComponent.getParent().getChildren().remove(selectedTreeItemComponent);
	
	    // remove ImageView from map
	    mapLayers.deleteComponentOnMap(removeComp);

	    // Remove the Component Object from the parent.
	    parent.removeComponent(removeComp);
	    
	    clearPriceLabels();
	}

	
	public void onBtnAddItem(ActionEvent event) {
		Dialog<Pair<String, Pair<String, String>>> dialog = new Dialog<>();
	    dialog.setTitle("Add Item");

	    // Set the button types.
	    ButtonType loginButtonType = new ButtonType("OK");	
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(30, 150, 20, 20));

        TextField item = new TextField();
        item.setPromptText("Item");
        
        TextField x = new TextField();
        x.setPromptText("X");
        
        TextField y = new TextField();
        y.setPromptText("Y");
       
        gridPane.add(new Label("Item"), 0, 0);
        gridPane.add(item, 1, 0);
        gridPane.add(new Label("X:"), 0, 1);
        gridPane.add(x, 1, 1);
        gridPane.add(new Label("Y:"), 0, 2);
        gridPane.add(y, 1, 2);

        dialog.getDialogPane().setContent(gridPane);

        Platform.runLater(() -> item.requestFocus());
        dialog.setResultConverter(dialogButton -> {
        	if (dialogButton == loginButtonType) {
        	return new Pair<>(item.getText(), new Pair<>(x.getText(),y.getText()));
        	}
        	return null;
        });

        Optional<Pair<String, Pair<String, String>>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
        	System.out.println("item=" + pair.getKey() + ", x=" + pair.getValue().getKey() + ", y=" + pair.getValue().getValue());
        	
        	int xValue = Integer.parseInt(pair.getValue().getKey());
        	int yValue = Integer.parseInt(pair.getValue().getValue());
        	
    	    TreeItem<Component> selectedTreeItemComponent = itemTreeView.getSelectionModel().getSelectedItem();
 		    Component comp = selectedTreeItemComponent.getValue();
 		    FarmItem newFarmItem = new FarmItem(pair.getKey(), xValue, yValue, 50, 50, comp.getLength(), comp.getPrice(), null);
 			TreeItem<Component> newItem = new TreeItem<>(newFarmItem, new ImageView(fileIcon));
 		    selectedTreeItemComponent.getChildren().add(newItem);

 		    // Add the item model representation of the Component class (object itemComponents)
 		    comp.addComponent(newFarmItem);

 			// call function to update the price labels
 			updatePriceLabels(newFarmItem);

        	// Draw a 50x50 pixel red square where the newly added item is located.
        	Rectangle additem = new Rectangle();
        	
        	additem.setX(xValue);
        	additem.setY(yValue);
        	additem.setWidth(50);
        	additem.setHeight(50);
        	additem.setFill(Color.TRANSPARENT);
        	additem.setStroke(Color.RED);
        	additem.setStrokeWidth(1);
        	
        	dashboard.getChildren().add(additem);
        });



	}

	

	public void onBtnAddItemContainer(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog("Enter Item Container");
		
		dialog.setTitle("Item Container");
		dialog.setHeaderText("Enter Item Container to update.");
		dialog.setGraphic(null);
		Optional<String> result = dialog.showAndWait();
		String entered = "none.";
		 
		if (result.isPresent()) {
		    entered = result.get();
		    System.out.println(entered);
		    
		    TreeItem<Component> selectedTreeItemComponent = itemTreeView.getSelectionModel().getSelectedItem();
		    Component comp = selectedTreeItemComponent.getValue();
		    
		    
		    // This copies various values from the parent component object
		    StoragePlace newStoragePlace = new StoragePlace(entered, comp.getLocationX(), comp.getLocationY(), comp.getWidth(), comp.getHeight(), comp.getLength(), comp.getPriceOnly(), null);
			TreeItem<Component> newItem = new TreeItem<>(newStoragePlace, new ImageView(folderIcon));
				
			selectedTreeItemComponent.getChildren().add(newItem);
		    
 		    // Add the StoragePlace to the model representation of the Component class (object itemComponents)
		    comp.addComponent(newStoragePlace);

			// call function to update the price labels
 			updatePriceLabels(newStoragePlace);

		}
	}
	
	public void onBtnLaunchDrone(ActionEvent event) throws InterruptedException {
			
		TelloDrone telloDrone;
		Adapter adapter = null;
		try {
			telloDrone = new TelloDrone();
			adapter = new Adapter(telloDrone);
		} catch (SocketException | UnknownHostException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(scanFarmRadio.isSelected()) {
			System.out.println("DEBUG: Dispatch Drone to Scan the Farm");
			
			if (adapter != null) {
				try {
					adapter.scanFarm();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else if(scanItemRadio.isSelected()) {
			System.out.println("DEBUG: Dispatch Drone to Scan an Item");
			
			if (adapter != null) {
				
				Component comp = itemTreeView.getSelectionModel().getSelectedItem().getValue();
				if(itemTreeView.getSelectionModel().getSelectedItem() != null) {  
					System.out.println("DEBUG: Dispatch Drone to Scan an Item");
					
					try {
						adapter.scanItem(comp.getLocationX(), comp.getLocationY(), comp.getHeight());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					// no item selected
					// Alert user that no item is selected.
					Alert alert = new Alert(AlertType.ERROR);
		            alert.initOwner(mainApp.getPrimaryStage());
		            alert.setTitle("No Item Selected.");
		            alert.setHeaderText("Choose an option from item.");
		            alert.setContentText("Please choose a item before trying to fly the drone.");
		            
		            alert.showAndWait();
				}
			}
			
		} else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No radio chosen.");
            alert.setHeaderText("Choose an option from radio.");
            alert.setContentText("Please choose a radio option before trying to fly the drone.");
            
            alert.showAndWait();
			
		}
	}

	//selection from tree view and show list view base on that
	public void selectItem(MouseEvent event) {
		TreeItem<Component> selectedTreeItemComponent = itemTreeView.getSelectionModel().getSelectedItem();

		// check if no item is selected
		if(selectedTreeItemComponent == null) {
			// reset labels
			clearPriceLabels();
			return;
		}
		
		
		Component selectedComponent = selectedTreeItemComponent.getValue();

		// print message to console about which item was selected
		System.out.println("Selected " + selectedComponent.getName());
		onSelectionChange();

		// call function to update the price labels
		updatePriceLabels(selectedComponent);
		
	}

	/*
	 *  This function clears the Market Price, Purchase Price, and Decription labels.
	 */
	private void clearPriceLabels() {
		lblDescription.setText("Description: ");
		lblCurrentMarketValue.setText("Current Market Value: ");
		lblPurchasePrice.setText("Purchase Price: ");
	}
	
	/*
	 * This function updates the prices labels for the Market Price, Purchase Price, and Description on the console
	 * for a given Component.
	 */
	private void updatePriceLabels(Component selectedComponent) {
		// object changes the format doubles to a currency format
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		
		lblDescription.setText("Description: " + selectedComponent.getName());
		
		// Update the price labels based using various Visitors
		MarketPriceVisitor marketVisitor = new MarketPriceVisitor();
		selectedComponent.accept(marketVisitor);
		lblCurrentMarketValue.setText("Current Market Value: " + currencyFormat.format(marketVisitor.visit(selectedComponent)));
		
		PurchasePriceVisitor purchaseVisitor = new PurchasePriceVisitor();		
		selectedComponent.accept(purchaseVisitor);
		lblPurchasePrice.setText("Purchase Price: " + currencyFormat.format(purchaseVisitor.visit(selectedComponent)));
		
	}
	/*
	 * Called presumably when the GUI is "initialized".
	 */
	@FXML
	public void initialize(){
		mapLayers = new MapLayers(dashboard);
    	droneDisplay = new DroneDisplay(dialogStage, drone);
    	
		mapLayers.initializeAerialPhoto();
    	mapLayers.initializePropertyBoundary();
    	
    	StoragePlace rootStoragePlace = new StoragePlace("Root");
		TreeItem<Component> rootTreeItem = new TreeItem<Component>(rootStoragePlace, new ImageView(folderIcon) );
		
		rootTreeItem.setExpanded(true);
		
		StoragePlace barnStoragePlace = new StoragePlace("Barn", 600, 500, 20, 10, 20, 10000, "/img/barn.png");
		TreeItem<Component> barnTreeItem = new TreeItem<>(barnStoragePlace, iconComponent(barnStoragePlace));
		StoragePlace livestockAreaStoragePlace = new StoragePlace("Live Stock Area",600, 500, 20, 10, 20, 10000,null);
		TreeItem<Component> livestockAreaTreeItem = new TreeItem<>(livestockAreaStoragePlace, new ImageView(folderIcon));
		FarmItem cowFarmItem = new FarmItem("Cow", 530, 500, 2, 2, 1, 2000, "/img/cow.png");
		TreeItem<Component> cowTreeItem = new TreeItem<>(cowFarmItem, iconComponent(cowFarmItem));
		FarmItem milkStorageFarmItem = new FarmItem("Milk Storage", 600, 500, 2, 2, 1, 2000, null);
		TreeItem<Component> milkStorageTreeItem = new TreeItem<>(milkStorageFarmItem, new ImageView(fileIcon));
		StoragePlace commandCenterStoragePlace = new StoragePlace("Command Center", 20, 0, 20, 10, 20, 10000, "/img/drone-cmd-center.png");
		TreeItem<Component> commandCenterTreeItem = new TreeItem<>(commandCenterStoragePlace, iconComponent(commandCenterStoragePlace));
		FarmItem droneFarmItem = new FarmItem("Drone", 600, 500, 20, 10, 20, 10000, "/img/drone.png");
		TreeItem<Component> droneTreeItem = new TreeItem<>(droneFarmItem, iconComponent(droneFarmItem));
		FarmItem cropFarmItem = new FarmItem("Crop", 600, 500, 20, 10, 20, 10000, null);
		TreeItem<Component> cropTreeItem = new TreeItem<>(cropFarmItem, new ImageView(fileIcon));
		
		StoragePlace eastFieldStoragePlace = new StoragePlace("East Field", 700, 100, 20, 10, 20, 30000, null);
		TreeItem<Component> eastFieldTreeItem = new TreeItem<>(eastFieldStoragePlace, new ImageView(folderIcon));		
		FarmItem dairyCowFarmItem = new FarmItem("Dairy Cow", 600, 100, 20, 10, 20, 1000, "/img/dairy-cow.png");
		TreeItem<Component> dairyCowTreeItem = new TreeItem<>(dairyCowFarmItem, iconComponent(dairyCowFarmItem));
		FarmItem pigFarmItem = new FarmItem("Pig", 700, 300, 20, 10, 20, 600, "/img/pig.png");
		TreeItem<Component> pigTreeItem = new TreeItem<>(pigFarmItem, iconComponent(pigFarmItem));		
		FarmItem goatFarmItem = new FarmItem("Goat", 700, 40, 20, 10, 20, 300, "/img/goat.png");
		TreeItem<Component> goatTreeItem = new TreeItem<>(goatFarmItem, iconComponent(goatFarmItem));		

		StoragePlace westFieldStoragePlace = new StoragePlace("West Field", 300, 400, 20, 10, 20, 30000, null);
		TreeItem<Component> westFieldTreeItem = new TreeItem<>(westFieldStoragePlace, new ImageView(folderIcon));		
		FarmItem sheepFarmItem = new FarmItem("Sheep", 400, 40, 20, 10, 20, 200, "/img/sheep.png");
		TreeItem<Component> sheepTreeItem = new TreeItem<>(sheepFarmItem, iconComponent(sheepFarmItem));
		FarmItem llamaFarmItem = new FarmItem("Llama", 100, 350, 20, 10, 20, 300, "/img/llama.png");
		TreeItem<Component> llamaTreeItem = new TreeItem<>(llamaFarmItem, iconComponent(llamaFarmItem));

		
		// This sets up the itemTreeView's child relationships on the GUI TreeView.
		rootTreeItem.getChildren().add(barnTreeItem);
		barnTreeItem.getChildren().add(livestockAreaTreeItem);
		livestockAreaTreeItem.getChildren().add(cowTreeItem);
		livestockAreaTreeItem.getChildren().add(milkStorageTreeItem);
		
		rootTreeItem.getChildren().add(commandCenterTreeItem);
		commandCenterTreeItem.getChildren().add(droneTreeItem);
		commandCenterTreeItem.getChildren().add(cropTreeItem);
		
		rootTreeItem.getChildren().add(eastFieldTreeItem);
		eastFieldTreeItem.getChildren().add(dairyCowTreeItem);
		eastFieldTreeItem.getChildren().add(pigTreeItem);
		eastFieldTreeItem.getChildren().add(goatTreeItem);

		rootTreeItem.getChildren().add(westFieldTreeItem);
		westFieldTreeItem.getChildren().add(sheepTreeItem);
		westFieldTreeItem.getChildren().add(llamaTreeItem);

		
		itemTreeView.setRoot(rootTreeItem);	
		
		// This setups up the model in itemComponents
		itemComponents = rootStoragePlace;
		itemComponents.addComponent(barnStoragePlace);
		barnStoragePlace.addComponent(livestockAreaStoragePlace);
		livestockAreaStoragePlace.addComponent(cowFarmItem);
		livestockAreaStoragePlace.addComponent(milkStorageFarmItem);
		itemComponents.addComponent(commandCenterStoragePlace);
		commandCenterStoragePlace.addComponent(droneFarmItem);
		commandCenterStoragePlace.addComponent(cropFarmItem);

		itemComponents.addComponent(eastFieldStoragePlace);
		eastFieldStoragePlace.addComponent(dairyCowFarmItem);
		eastFieldStoragePlace.addComponent(pigFarmItem);
		eastFieldStoragePlace.addComponent(goatFarmItem);

		itemComponents.addComponent(westFieldStoragePlace);
		westFieldStoragePlace.addComponent(llamaFarmItem);
		westFieldStoragePlace.addComponent(sheepFarmItem);

		
    	mapLayers.drawComponentOnMap(cowFarmItem, 60);
    	mapLayers.drawComponentOnMap(barnStoragePlace, 100);
    	mapLayers.drawComponentOnMap(commandCenterStoragePlace, 160);
       	mapLayers.drawComponentOnMap(dairyCowFarmItem, 60);
       	mapLayers.drawComponentOnMap(pigFarmItem, 60);
       	mapLayers.drawComponentOnMap(goatFarmItem, 60);
    	mapLayers.drawComponentOnMap(llamaFarmItem, 60);
    	mapLayers.drawComponentOnMap(sheepFarmItem, 60);
          
       	// Debug Messages
    	// commandCenterStoragePlace.printVariableDebug();
    	// cowFarmItem.printVariableDebug();
	}
	

	
	/*
	 * 	Give an ImageView icon for the TreeView
	 */
	public ImageView iconComponent(Component comp) {
//		System.out.print("Icon URL: " + comp.getImageURL() + "\n");

		ImageView anImageView = new ImageView(comp.getImageURL());
    	anImageView.setPreserveRatio(true);
    	anImageView.setFitWidth(30);
    	
    	return anImageView;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		
	}

}
