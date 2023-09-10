package view;
import javax.swing.*;
import java.awt.*;
import controller.SudokuGameCLI;


/*
 setting up for building block, 3 x 3 grid layout, total 9 of them
 for building up a sudoku game
*/
public class AreaBlock extends JPanel {
    private GridLayout grid = new GridLayout(3, 3);
    private int Row;
    private int Column;

    public AreaBlock(int Row, int Column){
        this.Row = Row;
        this.Column = Column;
        init();
    }

    private void init(){
        setLayout(grid);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                // get position of the current grid
                int row = Row * 3 + r + 1;
                int col = Column * 3 + c + 1;
                if (SudokuGameCLI.sudoku.canEdit(row, col)) {
                    add(new BlockEditor(row, col));
                } else {
                    add(new BlockViewer(row, col));
                }
            }
        }
    }
}
