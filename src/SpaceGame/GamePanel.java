package SpaceGame;


import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(5, this);

    private int time = 0;
    private int wastedBullets = 0;
    private BufferedImage bufferedImage;
    private ArrayList<Bullet> bulletArrayList = new ArrayList<Bullet>();
    private int bullet_dir_y = 1;

    private int ball_x = 0;
    private int ball_dir_x = 2;

    private int space_ship_x = 0;
    private int space_ship_dir_x = 20;

    public GamePanel() {
        try {
            bufferedImage = ImageIO.read(new FileInputStream(new File("C:\\Users\\swat_\\Documents\\IntellijIdeaProjects\\Main4\\SpaceShip.png")));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(Color.black);

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Ball
        g.setColor(Color.red);
        g.fillOval(ball_x, 0, 20, 20);

        //SpaceShip
        g.drawImage(bufferedImage, space_ship_x, 490, bufferedImage.getWidth()/50, bufferedImage.getHeight()/50, this);

        // Bullet
        for (Bullet b : bulletArrayList){
            if (b.getY() < 0){
                bulletArrayList.remove(b);
            }
        }

        g.setColor(Color.blue);
        for (Bullet b : bulletArrayList){
            g.fillRect(b.getX(), b.getY(), 10, 20);
        }

        // Intersection
        if (checkIntersaction()){
            timer.stop();
            String message = "You won! \n"
                    + "Time: " + time / 1000.0 + " seconds"
                    + "Wasted Bullets: " + wastedBullets;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        // SpaceShip
        if (c == KeyEvent.VK_LEFT){
            if (space_ship_x <= 0){
                space_ship_x = 0;
            }
            else {
                space_ship_x -= space_ship_dir_x;
            }
        }
        else if (c == KeyEvent.VK_RIGHT){
            if (space_ship_x >= 750){
                space_ship_x = 750;
            }
            else {
                space_ship_x += space_ship_dir_x;
            }
        }

        //Bullets
        else if (c == KeyEvent.VK_CONTROL){
            bulletArrayList.add(new Bullet(space_ship_x + 15, 470));
            wastedBullets++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Bullet
        for (Bullet b : bulletArrayList){
            b.setY(b.getY() - bullet_dir_y);
        }

        // Ball
        ball_x += ball_dir_x;
        if (ball_x > 750) {
            ball_dir_x = -ball_dir_x;
        }
        if (ball_x < 0){
            ball_dir_x = -ball_dir_x;
        }

        repaint();
    }

    public boolean checkIntersaction(){
        for (Bullet b : bulletArrayList){
            if (new Rectangle(b.getX(), b.getY(), 10, 20).intersects(new Rectangle(ball_x, 0, 20, 20))){
                return true;
            }
        }
        return false;
    }


    public class Bullet {
        private int x;
        private int y;

        public Bullet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
