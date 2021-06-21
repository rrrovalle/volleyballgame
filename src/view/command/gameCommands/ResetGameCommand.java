package view.command.gameCommands;

import controller.GameController;
import controller.IGame;
import view.command.GameCommander;

public class ResetGameCommand extends GameCommander {
		
		public ResetGameCommand(GameController game) {
			super(game);
		}
		
		@Override
		public void execute() {  
			controller.resetGame();
		} 
}
