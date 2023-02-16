package farm.model;

import java.io.IOException;

import farm.view.DroneDisplay;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Drone extends DroneDisplay implements Adapt{
	
	public void scanItem(int item_x, int item_y, int height) {
		
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
	public void scanFarm() {
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
	    tt2.setToX(800);
	    tt2.setToY(600);
	    tt2.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt3 = new TranslateTransition();
	    tt3.setNode(drone);
	    tt3.setToX(800);
	    tt3.setToY(0);
	    tt3.setDuration(Duration.seconds(2));
	    
	    TranslateTransition tt4 = new TranslateTransition();
	    tt4.setNode(drone);
	    tt4.setToX(0);
	    tt4.setToY(0);
	    tt4.setDuration(Duration.seconds(2));
	    
	    TranslateTransition landing = new TranslateTransition();
	    landing.setNode(drone);
	    landing.setToZ(0);
	    landing.setDuration(Duration.seconds(2));
	    
	    RotateTransition rotate = new RotateTransition();
	    rotate.setNode(drone);
	    rotate.setByAngle(90);
	    rotate.setDuration(Duration.seconds(2));
	    
	    RotateTransition rotate2 = new RotateTransition();
	    rotate2.setNode(drone);
	    rotate2.setByAngle(-90);
	    rotate2.setDuration(Duration.seconds(2));
	    
	    RotateTransition rotate3 = new RotateTransition();
	    rotate3.setNode(drone);
	    rotate3.setByAngle(-90);
	    rotate3.setDuration(Duration.seconds(2));
	    
	    RotateTransition rotate4 = new RotateTransition();
	    rotate4.setNode(drone);
	    rotate4.setByAngle(-90);
	    rotate4.setDuration(Duration.seconds(2));

	    RotateTransition rotate5 = new RotateTransition();
	    rotate5.setNode(drone);
	    rotate5.setByAngle(-180);
	    rotate5.setDuration(Duration.seconds(2));

	    SequentialTransition s = new SequentialTransition(takeoff,rotate,tt,rotate2,tt2,rotate3,tt3,rotate4,tt4,rotate5,landing);
//	    s.setCycleCount(TranslateTransition.INDEFINITE);
	    s.play();
	    s.setOnFinished((e)->{
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	alert.setHeaderText("Finished");
	    	alert.show();
	    });
	}
}
