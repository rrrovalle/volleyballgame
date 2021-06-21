package controller;

public interface IGame {
	
    void resetGame();

    void refreshScore(int x, int y);

    public void wonSet(int i, int set, String[] list);

    public void finishGame(int i);
    
    public void defineGameReferee(String name);
}
