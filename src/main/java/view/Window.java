package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Body gameBody;
    private ToolBar toolBar;
    static Font GameFont = new Font("Monospaced", Font.BOLD, 36);
    static Font ToolFont = new Font("Monospaced", Font.PLAIN, 20);

    public Window() {
        gameBody = new Body();
        toolBar = new ToolBar();
        init();
    }

    // initialization of GUI
    private void init() {
        add(gameBody, BorderLayout.CENTER);
        add(toolBar, BorderLayout.PAGE_END);
        setTitle("Sudoku Game");
        setSize(550, 650);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // new game (start next game)
    public void next() {
        gameBody.next();
        toolBar.next();
    }

    // stop the timer
    public void stop() {
        gameBody.next();
        toolBar.stop();
    }
}

