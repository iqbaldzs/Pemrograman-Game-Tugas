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

public class TugasTumbukan1 extends JPanel implements ActionListener {
    private int x1 = 50; // Koordinat x lingkaran merah
    private int y1 = 150; // Koordinat y lingkaran merah
    private int x2 = 250; // Koordinat x lingkaran biru
    private int y2 = 150; // Koordinat y lingkaran biru

    private int diameter = 50; // Diameter lingkaran
    private int speed1 = 3; // Kecepatan lingkaran merah
    private int speed2X = 2; // Kecepatan lingkaran biru - sumbu X
    private int speed2Y = 2; // Kecepatan lingkaran biru - sumbu Y

    private Timer timer;

    public TugasTumbukan1() {
        timer = new Timer(10, this);
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(128, 0, 128)); // Warna ungu
        g2d.fillOval(x1, y1, diameter, diameter); // Menggambar lingkaran merah
        g2d.setColor(new Color(128, 0, 0)); // Warna marun
        g2d.fillOval(x2, y2, diameter, diameter); // Menggambar lingkaran biru
    }

    public void actionPerformed(ActionEvent e) {
        // Pergerakan lingkaran merah
        x1 += speed1;
        if (x1 + diameter > x2 && x1 < x2 + diameter && y1 + diameter > y2 && y1 < y2 + diameter) {
            // Jika terjadi tabrakan dengan lingkaran biru
            speed1 = -speed1; // Mengubah arah pergerakan lingkaran merah
            // Memantulkan lingkaran biru dengan mengubah arah pergerakan pada sumbu X dan Y
            speed2X = -speed2X;
            speed2Y = -speed2Y;
        } else if (x1 + diameter > getWidth() || x1 < 0) {
            // Jika mencapai tepi layar pada sumbu X
            speed1 = -speed1; // Mengubah arah pergerakan lingkaran merah
        }

        // Pergerakan lingkaran biru
        x2 += speed2X;
        y2 += speed2Y;
        // Memantulkan lingkaran biru saat mencapai tepi layar pada sumbu X dan Y
        if (x2 + diameter > getWidth() || x2 < 0) {
            speed2X = -speed2X;
        }
        if (y2 + diameter > getHeight() || y2 < 0) {
            speed2Y = -speed2Y;
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tumbukan Lingkaran");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new TugasTumbukan1());
        frame.setVisible(true);
    }
}
