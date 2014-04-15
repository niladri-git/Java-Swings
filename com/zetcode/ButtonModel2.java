package com.zetcode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ButtonModel2 extends JFrame {

    private JButton okbtn;
    private JLabel enabled;
    private JLabel pressed;
    private JLabel armed;
    private JCheckBox cb;

    public ButtonModel2() {

        createButtonsAndLabels();
        initUI();
    }
    
    private void createButtonsAndLabels() {    
        
        okbtn = new JButton("ok");
        cb = new JCheckBox("Enabled", true);

        okbtn.setBounds(40, 30, 80, 25);

        cb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (okbtn.isEnabled())
                    okbtn.setEnabled(false);
                else 
                    okbtn.setEnabled(true);
            }
        });

        cb.setBounds(180, 30, 100, 25);

        enabled = new JLabel("Enabled: true");
        enabled.setBounds(40, 90, 120, 25);
        pressed = new JLabel("Pressed: false");
        pressed.setBounds(40, 120, 120, 25);
        armed  = new JLabel("Armed: false");
        armed.setBounds(40, 150, 120, 25);

        ButtonModel model = new DefaultButtonModel() {
            
            @Override
            public void setEnabled(boolean b) {
                if (b)
                    enabled.setText("Enabled: true");
                else 
                    enabled.setText("Enabled: false");

                super.setEnabled(b);
            }

            @Override
            public void setArmed(boolean b) {
                if (b)
                    armed.setText("Armed: true");
                else
                    armed.setText("Armed: false");

                super.setArmed(b);
            }
            
            @Override
            public void setPressed(boolean b) {
                if (b)
                    pressed.setText("Pressed: true");
                else 
                    pressed.setText("Pressed: false");

                super.setPressed(b);
            }

        };        
        
        okbtn.setModel(model);
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

        setTitle("Custom button model");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ButtonModel2 ex = new ButtonModel2();
                ex.setVisible(true);
            }
        });
    }
}