package goljava1;

public class Cell {

    private boolean isAlive = false;


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String toString() {
        if (isAlive) {
            return "O";
        } else {
            return "_";
        }

    }
}
