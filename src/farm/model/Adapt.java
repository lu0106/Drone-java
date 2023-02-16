package farm.model;

import java.io.IOException;

interface Adapt  {
	
	public void scanItem(int item_x, int item_y, int height) throws IOException, InterruptedException;
	public void scanFarm() throws IOException, InterruptedException;

}