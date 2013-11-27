package goljava1;

public class Main {

    public static final int SIZE = 12;
    public static final int CYCLES = 10;

    public static void main(String[] args) {

        Board board = new Board(SIZE);
        //board.initRandom();
        board.initLine();
        //board.initBox();
        //board.initToad();
        board.print();
        board.evolve(CYCLES);

    }

}
