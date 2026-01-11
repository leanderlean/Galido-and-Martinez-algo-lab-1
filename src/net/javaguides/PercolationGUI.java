package net.javaguides;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PercolationGUI extends JFrame {
    private int n;
    private boolean[][] grid;
    private Percolation percolation;
    private DrawingPanel drawingPanel;
    private JTextField countField;
    private JButton openButton;
    private JButton resetButton;

    public PercolationGUI() {
        // Ask for grid size N
        String inputN = JOptionPane.showInputDialog(null, "Enter grid size N:", "Percolation Setup", JOptionPane.QUESTION_MESSAGE);
        try {
            n = Integer.parseInt(inputN);
        } catch (NumberFormatException e) {
            n = 20; // Default
        }

        // Initialize grid (all false/black)
        grid = new boolean[n][n];
        percolation = new Percolation();

        initUI();
    }

    private void initUI() {
        setTitle("Percolation Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Drawing Panel
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Blocks to open:");
        countField = new JTextField(10);
        openButton = new JButton("Open");
        resetButton = new JButton("Reset");

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBlocks();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGrid();
            }
        });

        controlPanel.add(label);
        controlPanel.add(countField);
        controlPanel.add(openButton);
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.SOUTH);

        setSize(600, 650);
        setLocationRelativeTo(null);
    }

    private void openBlocks() {
        try {
            int count = Integer.parseInt(countField.getText());
            int opened = 0;
            Random random = new Random();
            
            // Safety check to prevent infinite loop if asking for more than available
            int currentOpen = 0;
            for(int i=0; i<n; i++) for(int j=0; j<n; j++) if(grid[i][j]) currentOpen++;
            
            if (currentOpen + count > n * n) {
                JOptionPane.showMessageDialog(this, "Cannot open more blocks than available!");
                return;
            }

            while (opened < count) {
                int r = random.nextInt(n);
                int c = random.nextInt(n);
                if (!grid[r][c]) {
                    grid[r][c] = true;
                    opened++;
                }
            }

            // check percolation
            percolation.percolates(grid, n);
            
            // update UI
            drawingPanel.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void resetGrid() {
        grid = new boolean[n][n];
        // Reset percolation path state implies re-running percolates or just simple repaint
        // Since percolates stores state in the object, we just re-run it on empty grid or handle null path
        // To be clean, we can re-run percolates(grid, n) which returns false and sets path to null/empty
        percolation.percolates(grid, n); 
        drawingPanel.repaint();
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (n == 0) return;

            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int cellWidth = panelWidth / n;
            int cellHeight = panelHeight / n;

            boolean[][] path = percolation.getPercolatedPath();

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int x = c * cellWidth;
                    int y = r * cellHeight;

                    // Logic for colors
                    // Path of percolation -> Blue
                    // Opened -> White
                    // Not Opened -> Black
                    
                    if (path != null && path[r][c]) {
                        g.setColor(Color.BLUE);
                    } else if (grid[r][c]) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    
                    g.fillRect(x, y, cellWidth, cellHeight);
                    
                    // Optional: Draw grid lines for better visibility
                    g.setColor(Color.GRAY);
                    g.drawRect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PercolationGUI().setVisible(true);
            }
        });
    }
}
