package controller;
import model.Sudoku;
import view.Window;


public class SudokuGameCLI {
    private static Window window;
    public static Sudoku sudoku;

    // difficultyLevel set between 0 to 1
    private static double difficultyLevel = 0.45;

    // start the program, initialize sudoku class and window class
    private SudokuGameCLI(){
        Timer.init();
        sudoku = new Sudoku(difficultyLevel);
        window = new Window();
    }

    // start a new next game
    public static void next() {
        Timer.init();
        sudoku.next();
        window.next();
    }

    // solution for the current game
    public static void solve() {
        sudoku.solve(0);
        window.stop();
    }

    // get time for current game
    public static String getTime() {
        String gameTime = Timer.getTime();
        Timer.updateTime();
        return gameTime;
    }

    public static void main(String[] args) {
        new SudokuGameCLI();
    }

}