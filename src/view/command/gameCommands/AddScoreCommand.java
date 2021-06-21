package view.command.gameCommands;

import controller.GameController;
import view.command.GameCommander;

public class AddScoreCommand extends GameCommander {
		
		private int action;
		
		public AddScoreCommand(GameController game, int action) {
			super(game);
			this.action = action;
		}
		
		@Override
		public void execute() {  
			controller.addScore(action);
		} 
} 
