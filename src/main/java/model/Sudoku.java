package model;

import java.util.Random;

public class Sudoku {
    private SudokuConstructor numbers[][]; // 9x9 sudoku model
    private SudokuConstructor emptyCollect[]; // collection of empty values
    private int emptyCount; // number count for empty values
    private boolean hasSolution;
    private double difficultyLevel;

    private class SudokuConstructor {
        int row;
        int column;
        int number;
        boolean canBeEdit; // whether or not it can be edit
        SudokuConstructor(int row, int column, int number) {
            this.row = row;
            this.column = column;
            this.number = number;
            this.canBeEdit = false;
        }
        SudokuConstructor(int row, int column){
            this.row = row;
            this.column = column;
            this.number = 0;
            this.canBeEdit = true;
        }
    }

    // public constructor for sudoku class, setting up by difficultyLevel
    public Sudoku(double difficultyLevel){
        this.difficultyLevel = difficultyLevel;
        next();
    }

    // private constructor for creating a shadow copy (read only version) of current game
    private Sudoku(SudokuConstructor readOnly[][]){
        update(readOnly);
    }

    // initialization of the game, setting up gameBoard
    private void init() {
        numbers = new SudokuConstructor[11][11]; // 9 plus 2 borders separating areaBlocks
        emptyCollect = new SudokuConstructor[81]; // 9 x 9 = 81
        emptyCount = 0;
        hasSolution = false;
    }

    // sudoku auto-generator, depending on input difficulty level
    public void next(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        if(difficultyLevel>1 || difficultyLevel<0){
            difficultyLevel=0.45;
        }
        int cnt = (int) Math.ceil(difficultyLevel * 81);
        do {
            init();
            for (int i = 0; i < cnt; i++) {
                int row = random.nextInt(9) + 1;
                int column = random.nextInt(9) + 1;
                int times = 0;
                if(numbers[row][column]==null || numbers[row][column].number==0){
                    do {
                        numbers[row][column] = new SudokuConstructor(row, column, random.nextInt(9) + 1);
                        update(numbers);
                        times++;
                        if (times == 9) {
                            numbers[row][column] = null;
                            update(numbers);
                            i--;
                            break;
                        }
                    } while (check(row, column)==false);
                } else {
                    i--;
                }
            }
            setHasSolution();
        } while (hasSolution==false);
    }

    // attempt the current input
    public boolean solve(int count) {
        if (count == emptyCount) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            setNum(emptyCollect[count].row, emptyCollect[count].column, i);
            if (check(emptyCollect[count].row, emptyCollect[count].column) && solve(count + 1)) {
                return true;
            }
        }
        setNum(emptyCollect[count].row, emptyCollect[count].column, 0);
        return false;
    }

    // check if the current input is correct
    public boolean check(int row, int col) {
        for (int i = 1; i <= 9; i++) {
            if (i != col && numbers[row][col].number == numbers[row][i].number) {
                return false;
            }
            if (i != row && numbers[row][col].number == numbers[i][col].number) {
                return false;
            }
        }
        for (int r = (row - 1) / 3 * 3 + 1; r <= (row + 2) / 3 * 3; r++) {
            for (int c = (col - 1) / 3 * 3 + 1; c <= (col + 2) / 3 * 3; c++) {
                if (r != row && c != col && numbers[row][col].number == numbers[r][c].number) {
                    return false;
                }
            }
        }
        return true;
    }

    // handle the updates
    private void update(SudokuConstructor toRead[][]) {
        init();
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                numbers[row][col] = toRead[row][col];
                if (numbers[row][col] == null || numbers[row][col].number == 0) {
                    numbers[row][col] = new SudokuConstructor(row, col);
                    emptyCollect[emptyCount] = numbers[row][col];
                    emptyCount++;
                }
            }
        }
    }


    // get the value of current index
    public int getNum(int row, int col) {
        return numbers[row][col].number;
    }


    // get the boolean result of canBeEdit for current index
    public boolean canEdit(int row, int col) {
        return numbers[row][col].canBeEdit;
    }


    // set the value of current index
    public void setNum(int row, int col, int num) {
        numbers[row][col].number = num;
    }


    // check if the current sudoku has a solution
    private void setHasSolution() {
        Sudoku sudoku = new Sudoku(numbers);
        sudoku.solve(0);
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                if (sudoku.numbers[row][col].number != numbers[row][col].number) {
                    hasSolution = true;
                    return;
                }
            }
        }
        hasSolution = false;
    }


    // check if the current inputs are all correct for winning criteria
    public boolean gameWinning() {
        for (int r = 1; r <= 9; r++) {
            for (int c = 1; c <= 9; c++) {
                if (numbers[r][c].number == 0 || check(r, c) == false) {
                    return false;
                }
            }
        }
        return true;
    }
}
