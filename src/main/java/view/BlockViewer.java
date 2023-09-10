package view;

import controller.SudokuGameCLI;

import javax.swing.*;
import java.awt.*;

/*
 setting for individual little block of sudoku
 */
public class BlockViewer extends JLabel {
    BlockViewer(int row, int col) {
        super(String.valueOf(SudokuGameCLI.sudoku.getNum(row, col)), JLabel.CENTER);
        setFont(Window.GameFont);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        setForeground(Color.black);
    }
}
