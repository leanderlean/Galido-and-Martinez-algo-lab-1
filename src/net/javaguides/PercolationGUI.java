package net.javaguides;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PercolationGUI extends JFrame {
    private final int n;
    private final boolean[][] grid;
    private final Percolation percolation;
    private final DrawingPanel drawingPanel;
    private final JTextField countField;
    private final JButton openButton;
    private final JButton resetButton;

    public PercolationGUI(int size) {
        n = size > 0 ? size : 20;
        grid = new boolean[n][n];
        percolation = new Percolation(n);

        setTitle("Percolation Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        countField = new JTextField(10);
        openButton = new JButton("Open Random Sites");
        resetButton = new JButton("Reset");

        controlPanel.add(new JLabel("Sites to open:"));
        controlPanel.add(countField);
        controlPanel.add(openButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);

        openButton.addActionListener(e -> openRandomSites());
        resetButton.addActionListener(e -> resetGrid());

        setSize(600, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openRandomSites() {
        try {
            int count = Integer.parseInt(countField.getText());
            int opened = 0;
            Random rand = new Random();

            while (opened < count) {
                int r = rand.nextInt(n);
                int c = rand.nextInt(n);
                if (!percolation.isOpen(r, c)) {
                    percolation.open(r, c);
                    grid[r][c] = true;
                    opened++;
                }
            }

            drawingPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number.");
        }
    }

    private void resetGrid() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) grid[i][j] = false;

        // recreate Percolation object
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j]) percolation.open(i, j);

        drawingPanel.repaint();
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cellW = getWidth() / n;
            int cellH = getHeight() / n;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (percolation.isFull(i, j)) g.setColor(Color.BLUE);
                    else if (grid[i][j]) g.setColor(Color.WHITE);
                    else g.setColor(Color.BLACK);
                    g.fillRect(j * cellW, i * cellH, cellW, cellH);

                    g.setColor(Color.GRAY);
                    g.drawRect(j * cellW, i * cellH, cellW, cellH);
                }
            }
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter grid size N:");
        int size = 20;
        try { size = Integer.parseInt(input); } catch (Exception ignored) {}
        new PercolationGUI(size);
    }
}
