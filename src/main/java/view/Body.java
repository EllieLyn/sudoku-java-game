package view;

import javax.swing.*;
import java.awt.*;

/*
 build up sudoku body by putting 9 AreaBlocks together,
 setting up the color and line border between areaBlocks
 */
class Body extends JPanel {
    private GridLayout grid = new GridLayout(3, 3);

    Body() {
        init();
    }

    // initialization
    private void init() {
        setLayout(grid);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                AreaBlock areaBlocks = new AreaBlock(row, col);
                areaBlocks.setBackground(Color.gray);
                areaBlocks.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
                add(areaBlocks);
            }
        }
    }

    // set up a new next game
    void next() {
        removeAll();
        init();
        updateUI();
    }
}

