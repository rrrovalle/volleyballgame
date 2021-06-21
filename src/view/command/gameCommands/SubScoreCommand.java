package view.command.gameCommands;

import controller.GameController;
import view.command.GameCommander;

public class SubScoreCommand extends GameCommander {
	
	private int action;
	
	public SubScoreCommand(GameController game, int action) {
		super(game);
		this.action = action;
	}
	
	@Override
	public void execute() {  
		controller.subScore(action);
	} 
}
