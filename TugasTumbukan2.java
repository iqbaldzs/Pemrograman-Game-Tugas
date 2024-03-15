/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tumbukan;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TugasTumbukan2 extends JPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int CIRCLE_RADIUS = 30;

    private int circle1X = 0;
    private int circle1Y = HEIGHT / 2;
    private int circle2X = WIDTH - CIRCLE_RADIUS * 2;
    private int circle2Y = HEIGHT / 2;

    private int circle1Speed = 2; // Kecepatan lingkaran 1
    private int circle2Speed = -2; // Kecepatan lingkaran 2

    private boolean stickToBlue = false; // Apakah lingkaran merah menempel dengan lingkaran biru

    public TugasTumbukan2() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menggerakkan lingkaran 1
                circle1X += circle1Speed;
                if (circle1X <= 0 || circle1X >= WIDTH - CIRCLE_RADIUS * 2) {
                    circle1Speed *= -1; // memantulkan lingkaran 1
                }

                // Menggerakkan lingkaran 2
                if (!stickToBlue) {
                    circle2X += circle2Speed;
                    if (circle2X <= 0 || circle2X >= WIDTH - CIRCLE_RADIUS * 2) {
                        circle2Speed *= -1; // memantulkan lingkaran 2
                    }
                } else {
                    // Mengikuti lintasan lingkaran biru
                    circle2X += circle1Speed;
                }

                // Pemeriksaan tumbukan
                if (circle1X + CIRCLE_RADIUS * 2 >= circle2X && circle1X <= circle2X + CIRCLE_RADIUS * 2) {
                    if (circle1Y + CIRCLE_RADIUS * 2 >= circle2Y && circle1Y <= circle2Y + CIRCLE_RADIUS * 2) {
                        // Menyesuaikan posisi lingkaran 2 agar menempel pada sisi lingkaran 1
                        circle2X = circle1X + CIRCLE_RADIUS * 2;
                        stickToBlue = true;
                    }
                }

                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Menggambar lingkaran 1
        g.setColor(Color.BLUE);
        g.fillOval(circle1X, circle1Y, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);

        // Menggambar lingkaran 2
        g.setColor(Color.RED);
        g.fillOval(circle2X, circle2Y, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Circles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new TugasTumbukan2());
        frame.setVisible(true);
    }
}

