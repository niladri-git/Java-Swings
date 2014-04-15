package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;


public class ListModels extends JFrame {

    private DefaultListModel model;
    private JList list;
    private JPanel rightPanel;

    public ListModels() {
     
        createList();
        createButtons();
        initUI();
    }
    
    private void createList() {
        
        model = new DefaultListModel();
        model.addElement("Amelie");
        model.addElement("Aguirre, der Zorn Gottes");
        model.addElement("Fargo");
        model.addElement("Exorcist");
        model.addElement("Schindler's list");   
        
        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                  int index = list.locationToIndex(e.getPoint());
                  Object item = model.getElementAt(index);
                  String text = JOptionPane.showInputDialog("Rename item", item);
                  String newitem = null;
                  if (text != null) 
                     newitem = text.trim();
                  else 
                     return;

                  if (!newitem.isEmpty()) {
                    model.remove(index);
                    model.add(index, newitem);
                    ListSelectionModel selmodel = list.getSelectionModel();
                    selmodel.setLeadSelectionIndex(index);
                  }
                }
            }

        });        
    }
    
    private void createButtons() {
        
        rightPanel = new JPanel();
        
        JButton remallbtn = new JButton("Remove All");
        JButton addbtn = new JButton("Add");
        addbtn.setMaximumSize(remallbtn.getMaximumSize());
        JButton renbtn = new JButton("Rename");
        renbtn.setMaximumSize(remallbtn.getMaximumSize());
        JButton delbtn = new JButton("Delete");
        delbtn.setMaximumSize(remallbtn.getMaximumSize());

        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = JOptionPane.showInputDialog("Add a new item");
                String item = null;

                if (text != null) 
                    item = text.trim();
                else 
                    return;

                if (!item.isEmpty())
                    model.addElement(item);
            }
        });

        delbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ListSelectionModel selmodel = list.getSelectionModel();
                int index = selmodel.getMinSelectionIndex();
                if (index >= 0)
                    model.remove(index);
            }

        });

        renbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ListSelectionModel selmodel = list.getSelectionModel();
                int index = selmodel.getMinSelectionIndex();
                if (index == -1) return;
                Object item = model.getElementAt(index);
                String text = JOptionPane.showInputDialog("Rename item", item);
                String newitem = null;

                if (text != null) {
                    newitem = text.trim();
                } else
                    return;

                if (!newitem.isEmpty()) {
                    model.remove(index);
                    model.add(index, newitem);
                }
            }
        });

        remallbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.clear();
                }
        });

        rightPanel.add(addbtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0,4)));
        rightPanel.add(renbtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0,4)));
        rightPanel.add(delbtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0,4)));
        rightPanel.add(remallbtn);

        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));        
    }

    private void initUI() {                

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollpane = new JScrollPane();
        scrollpane.getViewport().add(list);  
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        leftPanel.add(scrollpane);

        panel.add(leftPanel);
        panel.add(rightPanel);

        add(panel);

        setTitle("JList models");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ListModels ex = new ListModels();
                ex.setVisible(true);
            }
        });
    }
}