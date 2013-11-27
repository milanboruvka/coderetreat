package goljava1;

import java.util.Arrays;
import java.util.Random;

public class Board {

    private Cell[][] cells;

    public Board(int size) {
        Cell[][] cells = new Cell[size][size];
        this.cells = cells;
        for (int row=0; row < cells.length; row++) {
            for (int column=0; column < cells[row].length; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    public void initRandom() {
        Random r = new Random();
        for (int row=0; row < cells.length; row++) {
            for (int column=0; column < cells[row].length; column++) {
                cells[row][column].setAlive(r.nextBoolean());
            }
        }
    }


    public void initLine() {
        for (int row=0; row < cells.length; row++) {
            for (int column=0; column < cells[row].length; column++) {
                if (row==1 && (column>0 && column<4)) {
                    cells[row][column].setAlive(true);
                }
            }
        }
    }

    public void initBox() {
        for (int row=0; row < cells.length; row++) {
            for (int column=0; column < cells[row].length; column++) {
                if ((row==2 || row==3) && (column>1 && column<4)) {
                    cells[row][column].setAlive(true);
                }
            }
        }
    }

    public void initToad() {
        for (int row=0; row < cells.length; row++) {
            for (int column=0; column < cells[row].length; column++) {
                if (row==3 && (column>1 && column<5)) {
                    cells[row][column].setAlive(true);
                } else if (row==2 && (column>2 && column<6)) {
                    cells[row][column].setAlive(true);
                }
            }
        }
    }


    public void print() {
        for (int row=0; row < cells.length; row++) {
            Cell[] rowCols = cells[row];
            String rowAsString = Arrays.toString(rowCols);
            System.out.println(rowAsString);
        }
    }

    public void evolve(int cycles) {

        for (int step=1; step <=cycles; step++) {

            System.out.println();
            System.out.println("== CYCLE " + step + " ==");

            this.nextStep();
            this.print();
        }


    }

    public void nextStep() {

        Cell[][] nextCells = new Cell[this.cells.length][this.cells[0].length];

        for (int row=0; row < cells.length; row++) {
            for (int column=0; column < cells[row].length; column++) {
                Cell newCell = new Cell();
                nextCells[row][column] = newCell;
                newCell.setAlive(shouldBeAlive(row,column));
            }
        }

        cells = nextCells;
    }


    private boolean shouldBeAlive(int row, int column) {

        Cell currentCell = cells[row][column];
        boolean currentlyAlive = currentCell.isAlive();
        int countOfLivingNeighbours = 0;

        int minRow = row-1 >= 0 ? (row-1) : 0;
        int maxRow = row+1 < cells.length ? (row+1) : (cells.length-1);
        int minCol = column-1 >= 0 ? (column-1) : 0;
        int maxCol = column+1 < cells[0].length ? (column+1) : (cells[0].length-1);

//        System.out.println("");
//        System.out.print("row = " + row);
//        System.out.println(" col = " + column);
//        System.out.print("minRow = " + minRow);
//        System.out.println(" maxRow = " + maxRow);
//        System.out.print("minCol = " + minCol);
//        System.out.println(" maxCol = " + maxCol);


        for (int x=minRow; x<=maxRow; x++) {
            for (int y=minCol; y<=maxCol; y++) {

//                System.out.print("x = " + x);
//                System.out.println(" y = " + y);

                if (x==row && y==column) {
//                    System.out.println("skipping");
                } else {
                    if ((cells[x][y]).isAlive()) {
                        countOfLivingNeighbours++;
                    }
                }

            }
        }

//        System.out.println("countOfLivingNeighbours = " + countOfLivingNeighbours);

        if (currentlyAlive && countOfLivingNeighbours<2) {
            //should die
            //System.out.println("["+row+","+column+"] F "+"currentlyAlive && countOfLivingNeighbours<2");
            return false;
        }
        else if (currentlyAlive && (countOfLivingNeighbours==2 || countOfLivingNeighbours==3)) {
            //should remain alive
            //System.out.println("["+row+","+column+"] T "+"currentlyAlive && (countOfLivingNeighbours==2 || countOfLivingNeighbours==3");
            return true;
        }
        else if (currentlyAlive && countOfLivingNeighbours>2) {
            //should die
            //System.out.println("["+row+","+column+"] F "+"currentlyAlive && countOfLivingNeighbours>2");
            return false;
        }
        else if (!currentlyAlive && countOfLivingNeighbours==3) {
            //should become alive
            //System.out.println("["+row+","+column+"] T "+"!currentlyAlive && countOfLivingNeighbours==3");
            return true;
        }

        return false;
    }



}
