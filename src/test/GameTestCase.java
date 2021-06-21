package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.GameController;

/** Mudar para público todas os atributos da classe controller para conseguir testar */
class GameTestCase {

//	@Test
//	void initialSetTest() {
//		GameController game = GameController.getIntance();
//		int actualSet 		= game.set.getSet();
//		assertEquals(1, actualSet);
//	}
//	
//	@Test
//	void failSetTest() {
//		GameController game = GameController.getIntance();
//		int actualSet	    = game.set.getSet();
//		assertEquals(5, actualSet);
//	}
//	
//	@Test
//	void startTeamAScoreTest() {
//		GameController game = GameController.getIntance();
//		int actualScore 	= game.team1.getScore();
//		assertEquals(0, actualScore);
//	}
//	
//	@Test
//	void startTeamBScoreTest() {
//		GameController game = GameController.getIntance();
//		int actualScore 	= game.team2.getScore();
//		assertEquals(0, actualScore);
//	}
//	
//	@Test
//	void AddScoreATest() {
//		/** Get Team A score */
//		GameController game = GameController.getIntance();
//		int actualScore 	= game.team1.getScore();
//		/** Update Team A score */
//		game.addScore(1);
//		int updateScore 	= game.team1.getScore();
//		assertEquals(1, updateScore);
//	}
//	
//	@Test
//	void subScoreATest() { 
//		GameController game = GameController.getIntance(); 
//		/** Update Team A score */
//		game.addScore(1);
//		game.addScore(1);
//		/** Sub Team A score */
//		game.subScore(1);
//		int updateScore = game.team1.getScore();
//		assertEquals(1, updateScore);
//	}
}
