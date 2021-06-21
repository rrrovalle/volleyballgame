package view.command;

import controller.GameController;

public abstract class GameCommander implements Command {
	
	public GameController controller;
	
	public GameCommander(GameController controller) {
		this.controller = controller;
	}  
}