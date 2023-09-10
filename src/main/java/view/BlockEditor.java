package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import controller.SudokuGameCLI;

/*
 blockEditor response to user input, can handle and respond to user insert/remove/change input
 and response with colors depending on whether or not the input is correct answer
 */
public class BlockEditor extends JTextField {
    BlockEditor(int row, int col) {
        if (SudokuGameCLI.sudoku.getNum(row, col) != 0) {
            setText(String.valueOf(SudokuGameCLI.sudoku.getNum(row, col)));
        }
        setFont(Window.GameFont);
        setBorder(BorderFactory.createLineBorder(Color.white, 1));
        setRightColor();
        setHorizontalAlignment(JTextField.CENTER);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (getDocument().getLength() > 1) {
                    SudokuGameCLI.sudoku.setNum(row, col, 0);
                    setWrongColor();
                } else {
                    changedUpdate(e);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getDocument().getLength() == 0) {
                    SudokuGameCLI.sudoku.setNum(row, col, 0);
                    setRightColor();
                } else {
                    changedUpdate(e);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    int value = Integer.valueOf(getDocument().getText(0, 1));
                    SudokuGameCLI.sudoku.setNum(row, col, value);
                    if (SudokuGameCLI.sudoku.check(row, col)) {
                        setRightColor();
                    } else {
                        throw new Exception();
                    }
                } catch (Exception err) {
                    SudokuGameCLI.sudoku.setNum(row, col, 0);
                    setWrongColor();
                    return;
                }
                if (SudokuGameCLI.sudoku.gameWinning()) {
                    SudokuGameCLI.solve();
                    int choice = JOptionPane.showConfirmDialog(null, "your time: " + SudokuGameCLI.getTime() + ", new game?", "Sudoku | Completed", JOptionPane.DEFAULT_OPTION);
                    if (choice == 0) {
                        SudokuGameCLI.next();
                    }
                }
            }
        });
    }

    // color response for correct input
    private void setRightColor() {
        setForeground(Color.green);
        setBackground(Color.black);
    }

    // color response for incorrect input
    private void setWrongColor() {
        setForeground(Color.white);
        setBackground(Color.red);
    }
}
