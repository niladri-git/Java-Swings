package com.zetcode;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class ProgressBarExample extends JFrame {

    private ActionListener updateProBar;
    private Timer timer;
    private JProgressBar progressBar;
    private JButton button;

    public ProgressBarExample() {
        
        initUI();
    }

    private void initUI() {
        
        setTitle("JProgressBar");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        progressBar = new JProgressBar();

        progressBar.setMaximumSize(new Dimension(150, 20));
        progressBar.setMinimumSize(new Dimension(150, 20));
        progressBar.setPreferredSize(new Dimension(150, 20));

        progressBar.setAlignmentX(0f);

        panel.add(progressBar);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        button = new JButton("Start");
        button.setFocusable(false);
        button.setMaximumSize(button.getPreferredSize());

        updateProBar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int val = progressBar.getValue();
                if (val >= 100) {
                    timer.stop();
                    button.setText("End");
                    return;
                }

                progressBar.setValue(++val);
            }
        };

        timer = new Timer(50, updateProBar);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    button.setText("Start");

                } else if (!"End".equals(button.getText())) {
                    timer.start();
                    button.setText("Stop");
                }

            }
        });


        panel.add(button);
        add(panel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProgressBarExample ex = new ProgressBarExample();
                ex.setVisible(true);
            }
        });
    }
}