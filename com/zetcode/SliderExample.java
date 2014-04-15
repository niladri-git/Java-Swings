package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SliderExample extends JFrame {

    private JSlider slider;
    private JLabel label;

    private ImageIcon mute;
    private ImageIcon min; 
    private ImageIcon med; 
    private ImageIcon max; 

    public SliderExample() {
        
        loadImages();        
        initUI();
    }
    
    private void loadImages() {
        
        mute = new ImageIcon(getClass().getResource("mute.jpg"));
        min = new ImageIcon(getClass().getResource("min.jpg"));
        med = new ImageIcon(getClass().getResource("med.jpg"));
        max = new ImageIcon(getClass().getResource("max.jpg"));        
    }

    public final void initUI() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setLayout(new BorderLayout());

        panel.add(Box.createHorizontalGlue());
        slider = new JSlider(0, 150, 0);

        slider.setPreferredSize(new Dimension(150, 30));

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                int value = slider.getValue();
                if (value == 0) {
                    label.setIcon(mute);
                } else if (value > 0 && value <= 30) {
                    label.setIcon(min);
                } else if (value > 30 && value < 80) {
                    label.setIcon(med);
                } else {
                    label.setIcon(max);
                }
            }
        });

        panel.add(slider);
        panel.add(Box.createRigidArea(new Dimension(5, 0)));

        label = new JLabel(mute, JLabel.CENTER);
        panel.add(label);
        panel.add(Box.createHorizontalGlue());
        add(panel, BorderLayout.CENTER);

        pack();
        
        setTitle("JSlider");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SliderExample ex = new SliderExample();
                ex.setVisible(true);
            }
        });
    }
}