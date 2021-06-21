package view.command.gameCommands;

import controller.GameController;
import view.command.GameCommander;

public class DefineGameRefereeCommand extends GameCommander {
	
	private String name;
	
	public DefineGameRefereeCommand(GameController game, String name) {
		super(game);
		this.name = name;
	}
	
	@Override
	public void execute() {  
		controller.setGameReferee(name);
	} 
}
