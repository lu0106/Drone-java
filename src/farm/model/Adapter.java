package farm.model;

import java.io.IOException;

import javafx.animation.KeyValue;
import farm.view.DroneDisplay;
import farm.view.InterfaceBoundary;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import farm.model.Constants;

public class Adapter extends DroneDisplay implements Adapt{

	TelloDrone drone;
	
	int Foot_to_CM = Constants.CENTIMETERS_PER_MODEL_FOOT/Constants.PIXELS_TO_ONE_MODEL_FOOT/10;

	public Adapter(TelloDrone drone) {
		this.drone = drone;
	}
	
	public void scanItem(int item_x, int item_y, int height) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		try {
			drone.activateSDK();
	//		drone.streamOn();
	//		drone.streamViewOn();
			drone.takeoff();
			
			drone.turnCW(90);
			drone.gotoXYZ(item_x, -item_y, height, 100);
			drone.turnCW(180);
			drone.gotoXY(item_x, -item_y, 100);
			drone.turnCW(90);
			
			drone.land();
	//		drone.streamViewOff();
	//		drone.streamOff();
			drone.end();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void scanFarm() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		drone.activateSDK();
//		drone.streamOn();
//		drone.streamViewOn();
		drone.takeoff();

		drone.turnCW(90);
		drone.gotoXY(800, 0, 100);
		drone.turnCCW(90);
		drone.gotoXY(200, 0, 100);
		drone.turnCCW(90);
		drone.gotoXY(800, 0, 100);
		drone.turnCW(90);
		drone.gotoXY(200, 0, 100);
		drone.turnCW(90);
		drone.gotoXY(800, 0, 100);
		drone.turnCCW(90);
		drone.gotoXY(200, 0, 100);
		drone.turnCCW(90);
		drone.gotoXY(800, 0, 100);
		drone.turnCW(90);
		drone.gotoXY(200, 0, 100);
		drone.turnCW(90);
		drone.gotoXY(800, 0, 100);
		drone.turnCW(90);
		drone.gotoXY(800, 600, 100);
		drone.turnCW(180);

		
		drone.land();
//		drone.streamViewOff();
//		drone.streamOff();
		drone.end();
	}

}
