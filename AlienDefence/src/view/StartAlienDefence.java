package view;

import controller.AlienDefenceController;
import model.persistence.IPersistence;
import model.persistenceDummy.PersistenceDummy;
import view.menue.MainMenue;

public class StartAlienDefence {

	public static void main(String[] args) {
		
		IPersistence 		   alienDefenceModel      = new PersistenceDummy();
		AlienDefenceController alienDefenceController = new AlienDefenceController(alienDefenceModel);
		MainMenue              mainMenue              = new MainMenue(alienDefenceController);
		
		mainMenue.setVisible(true);
	}
}
