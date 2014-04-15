package com.zetcode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonModel extends JFrame {

    private JButton okbtn;
    private JLabel enabled;
    private JLabel pressed;
    private JLabel armed;
    private JCheckBox cb;

    public ButtonModel() {

        createButtonsAndLabels();
        initUI();
    }

    private void createButtonsAndLabels() {

        okbtn = new JButton("ok");

        cb = new JCheckBox("Enabled", false);

        okbtn.setBounds(40, 30, 80, 25);
        okbtn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
                DefaultButtonModel model = (DefaultButtonModel) okbtn.getModel();
                
                if (model.isEnabled()) {
                    enabled.setText("Enabled: true");
                } else {
                    enabled.setText("Enabled: false");
                }

                if (model.isArmed()) {
                    armed.setText("Armed: true");
                } else {
                    armed.setText("Armed: false");
                }

                if (model.isPressed()) {
                    pressed.setText("Pressed: true");
                } else {
                    pressed.setText("Pressed: false");
                }
            }

        });

        cb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (okbtn.isEnabled()) {
                    okbtn.setEnabled(false);
                } else {
                    okbtn.setEnabled(true);
                }
            }
        });

        cb.setLocation(180, 30);
        cb.setSize(cb.getPreferredSize());

        enabled = new JLabel("Enabled: true");
        enabled.setSize(enabled.getPreferredSize());
        enabled.setLocation(40, 90);
        pressed = new JLabel("Pressed: false");
        pressed.setSize(pressed.getPreferredSize());
        pressed.setLocation(40, 120);
        armed = new JLabel("Armed: false");
        armed.setSize(armed.getPreferredSize());
        armed.setLocation(40, 150);
    }

    private void initUI() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(okbtn);
        panel.add(cb);
        panel.add(enabled);
        panel.add(pressed);
        panel.add(armed);

        add(panel);

        setTitle("ButtonModel");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ButtonModel ex = new ButtonModel();
                ex.setVisible(true);
            }
        });
    }
}