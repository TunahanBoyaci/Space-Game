package SpaceGame;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame("Game Frame");

        gameFrame.setResizable(false);
        gameFrame.setFocusable(false);
        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();

        gamePanel.requestFocus(true);
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.setFocusTraversalKeysEnabled(true);

        gameFrame.add(gamePanel);
        gamePanel.setVisible(true);
    }
}

