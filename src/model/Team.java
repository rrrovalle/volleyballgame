package model;

public class Team {

    private int Score;
    private int SetsWons;

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void addScore() {
        this.Score++;
    }

    public void subScore() {
        if (this.Score > 0) {
            this.Score--;
        }
    }

    public int getSetsWons() {
        return SetsWons;
    }

    public void wonSet() {
        SetsWons++;
    }
}
