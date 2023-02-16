/*
 * This is the Farm GUI Project.
 * 
 * By Grad Team 2
 * CS520-Z1 Lab
 * Fall 2021
 * Date: 2021-11-21
 * 
 * This a simple Farm GUI Dashboard.
 *
 * Initial code was based off of a sample application tutorial for JavaFX & SceneBuider:
 * https://code.makery.ch/library/javafx-tutorial/
 */

package farm;

import java.io.IOException;
// import farm.model.Dashboard;
import farm.model.FarmItem;
import farm.model.StoragePlace;
import farm.view.InterfaceBoundary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	
	private InterfaceBoundary controller;
    private Stage primaryStage;
    private BorderPane rootLayout;

    
    public MainApp() {
    	
    	// This is adding more test data.
    	// new items to test the Farm Item list
    	// This should look something like this in the farmItemList (which it currently doesn't)
    	//
    	//  shed
    	//     bay 1
    	//        hammer
    	//        fence posts
    	StoragePlace itemRoot = new StoragePlace();
    	StoragePlace shed = new StoragePlace("shed");
    	StoragePlace room1 = new StoragePlace("bay 1");
    	FarmItem hammer = new FarmItem("hammer");
    	FarmItem fencePosts = new FarmItem("fence posts");
    	room1.addComponent(hammer);
    	room1.addComponent(fencePosts);
    	shed.addComponent(room1);
    	// farmItemData.add(room1);
//    	farmItemData.add(shed);
    	itemRoot.addComponent(shed);

    }

    
    @Override
    public void start(Stage primaryStage) {
		
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Farm Dashboard");

        initRootLayout();

        showFarmOverview();
    }

 
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFarmOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FarmOverview.fxml"));
            AnchorPane farmOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(farmOverview);
            
            // Singleton
            controller = controller.getInstance();
            // Give the controller access to the main app.
            controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
