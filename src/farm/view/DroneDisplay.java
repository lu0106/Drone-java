package farm.view;

import java.io.IOException;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DroneDisplay {

	private static DroneDisplay instance = null;
	
	public DroneDisplay() {
	}
//	@FXML
//	private RadioButton scanfarm, scanitem;
	
    private Stage dialogStage;
	
//	@FXML
    protected ImageView drone;
    
    @FXML
	private Button flyDroneButton;
    
	
//	@FXML
//	protected AnchorPane dashboard;
	
 /*   public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
 */   
    /*
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
    	scanfarm.setOnMouseClicked(event->{
			SF();
		});
    	scanitem.setOnMouseClicked(event->{
			SI();
		});
		
	}*/
    
    // Constructor
    public DroneDisplay(Stage dialogStage, ImageView droneImageView) {
    	this.dialogStage = dialogStage;
    	this.drone = droneImageView;
    }
    
//	@FXML
	public void scanFarm() throws InterruptedException, IOException {
		// Scan Farm
		TranslateTransition takeoff = new TranslateTransition();
		takeoff.setNode(drone);
		takeoff.setToZ(100);
		takeoff.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt = new TranslateTransition();
	    tt.setNode(drone);
	    tt.setToX(0);
	    tt.setToY(600);
	    tt.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt2 = new TranslateTransition();
	    tt2.setNode(drone);
	    tt2.setToX(200);
	    tt2.setToY(600);
	    tt2.setDuration(Duration.seconds(1));
	    
	    TranslateTransition tt3 = new TranslateTransition();
	    tt3.setNode(drone);
	    tt3.setToX(200);
	    tt3.setToY(0);
	    tt3.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt4 = new TranslateTransition();
	    tt4.setNode(drone);
	    tt4.setToX(400);
	    tt4.setToY(0);
	    tt4.setDuration(Duration.seconds(1));
	    
	    TranslateTransition tt5 = new TranslateTransition();
	    tt5.setNode(drone);
	    tt5.setToX(400);
	    tt5.setToY(600);
	    tt5.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt6 = new TranslateTransition();
	    tt6.setNode(drone);
	    tt6.setToX(600);
	    tt6.setToY(600);
	    tt6.setDuration(Duration.seconds(1));
	    
	    TranslateTransition tt7 = new TranslateTransition();
	    tt7.setNode(drone);
	    tt7.setToX(600);
	    tt7.setToY(0);
	    tt7.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt8 = new TranslateTransition();
	    tt8.setNode(drone);
	    tt8.setToX(800);
	    tt8.setToY(0);
	    tt8.setDuration(Duration.seconds(1));
	    
	    TranslateTransition tt9 = new TranslateTransition();
	    tt9.setNode(drone);
	    tt9.setToX(800);
	    tt9.setToY(600);
	    tt9.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt10 = new TranslateTransition();
	    tt10.setNode(drone);
	    tt10.setToX(0);
	    tt10.setToY(0);
	    tt10.setDuration(Duration.seconds(3));
	    
	    TranslateTransition landing = new TranslateTransition();
	    landing.setNode(drone);
	    landing.setToZ(0);
	    landing.setDuration(Duration.seconds(2));
	    
	    RotateTransition rotate = new RotateTransition();
	    rotate.setNode(drone);
	    rotate.setByAngle(90);
	    rotate.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate2 = new RotateTransition();
	    rotate2.setNode(drone);
	    rotate2.setByAngle(-90);
	    rotate2.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate3 = new RotateTransition();
	    rotate3.setNode(drone);
	    rotate3.setByAngle(-90);
	    rotate3.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate4 = new RotateTransition();
	    rotate4.setNode(drone);
	    rotate4.setByAngle(90);
	    rotate4.setDuration(Duration.seconds(1));

	    RotateTransition rotate5 = new RotateTransition();
	    rotate5.setNode(drone);
	    rotate5.setByAngle(90);
	    rotate5.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate6 = new RotateTransition();
	    rotate6.setNode(drone);
	    rotate6.setByAngle(-90);
	    rotate6.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate7 = new RotateTransition();
	    rotate7.setNode(drone);
	    rotate7.setByAngle(-90);
	    rotate7.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate8 = new RotateTransition();
	    rotate8.setNode(drone);
	    rotate8.setByAngle(90);
	    rotate8.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate9 = new RotateTransition();
	    rotate9.setNode(drone);
	    rotate9.setByAngle(90);
	    rotate9.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate10 = new RotateTransition();
	    rotate10.setNode(drone);
	    rotate10.setByAngle(90);
	    rotate10.setDuration(Duration.seconds(1));
	    
	    RotateTransition rotate11 = new RotateTransition();
	    rotate11.setNode(drone);
	    rotate11.setByAngle(180);
	    rotate11.setDuration(Duration.seconds(1));

//	    SequentialTransition s = new SequentialTransition(takeoff,rotate,tt,rotate2,tt2,rotate3,tt3,rotate4,tt4,rotate5,landing);
	    SequentialTransition s = new SequentialTransition(takeoff,rotate,tt,rotate2,tt2,rotate3,tt3,rotate4,tt4,rotate5,tt5,rotate6,tt6,rotate7,tt7,rotate8,tt8,rotate9,tt9,rotate10,tt10,rotate11,landing);
//	    s.setCycleCount(TranslateTransition.INDEFINITE);
	    s.play();
	    s.setOnFinished((e)->{
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	alert.setHeaderText("Finished");
	    	alert.show();
	    });
	    }
//	@FXML
	public void scanItem(int item_x, int item_y, int height) throws IOException, InterruptedException {
		
	    // Scan Item
//		FarmItem item = new FarmItem("root");
//		int item_x = item.getLocationX();
//		int item_y = item.getLocationY();
		
		
		TranslateTransition takeoff = new TranslateTransition();
		takeoff.setNode(drone);
		takeoff.setToZ(100);
		takeoff.setDuration(Duration.seconds(2));
	    
	    TranslateTransition t = new TranslateTransition();
	    t.setNode(drone);
	    t.setToX(item_x);
	    t.setToY(item_y);
	    t.setDuration(Duration.seconds(2));
	    
	    RotateTransition r = new RotateTransition();
	    r.setNode(drone);
	    r.setByAngle(180);
	    r.setDuration(Duration.seconds(2));
	    
	    TranslateTransition t2 = new TranslateTransition();
	    t2.setNode(drone);
	    t2.setToX(0);
	    t2.setToY(0);
	    t2.setDuration(Duration.seconds(2));
	    
	    RotateTransition r2 = new RotateTransition();
	    r2.setNode(drone);
	    r2.setByAngle(180);
	    r2.setDuration(Duration.seconds(2));
	    
		TranslateTransition landing = new TranslateTransition();
		landing.setNode(drone);
		landing.setToZ(0);
		landing.setDuration(Duration.seconds(2));
		
	    SequentialTransition s2 = new SequentialTransition(takeoff,t,r,t2,r2,landing);
//	    s2.setCycleCount(TranslateTransition.INDEFINITE);
	    s2.play();
	    s2.setOnFinished((e)->{
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	alert.setHeaderText("Finished");
	    	alert.show();
	    });
	}
	

/*	public void setMainApp(MainApp mainApp) {
		
	}*/
	
	static DroneDisplay getInstance() {
		if (instance == null) {
			instance = new DroneDisplay();
		}
		return instance;
	}
}