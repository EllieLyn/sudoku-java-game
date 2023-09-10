package view;

import controller.SudokuGameCLI;

import javax.swing.*;

/*
 add new-game, show-answer buttons to the toolbar, set up the correct responses
 also add timer to the toolbar, set up the logic
 */
class ToolBar extends JPanel {
    private ToolTimer toolTimer;

    ToolBar() {
        init();
    }

    // Initialization of a game
    private void init() {
        // new game button
        JButton btn_next = new JButton("New Game");
        btn_next.setFont(Window.ToolFont);
        btn_next.addActionListener(e -> {
            SudokuGameCLI.next();
        });
        add(btn_next);
        // show answer of current game button
        JButton btn_solve = new JButton("Show Answer");
        btn_solve.setFont(Window.ToolFont);
        btn_solve.addActionListener(e -> {
            SudokuGameCLI.solve();
        });
        add(btn_solve);
        // timer for current game
        toolTimer = new ToolTimer();
        toolTimer.init();
        add(toolTimer);
    }

    // stop the timer and initialize a new timer
    void next() {
        stop();
        toolTimer.init();
    }

    // stop the timer
    void stop() {
        toolTimer.stop();
    }
}

// timer tool for the game
class ToolTimer extends JLabel {
    private Timer timer;

    ToolTimer() {
        timer = new Timer(1000, e -> {
            setText(SudokuGameCLI.getTime());
        });
        setFont(Window.ToolFont);
    }

    // Initialization, start of a new timer
    void init() {
        timer.start();
        setText(SudokuGameCLI.getTime());
    }

    // stop the timer
    void stop() {
        timer.stop();
    }
}
