package com.zetcode;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DragGesture extends JFrame implements 
    DragGestureListener, Transferable {

    public DragGesture() {

        setTitle("Drag Gesture");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 15));

        JPanel left = new JPanel();
        left.setBackground(Color.red);
        left.setPreferredSize(new Dimension(120, 120));

        DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(left,
            DnDConstants.ACTION_COPY, this);

        panel.add(left);
        add(panel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void dragGestureRecognized(DragGestureEvent event) {
        System.out.println("grag gesture");
        Cursor cursor = null;
        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }
        event.startDrag(cursor, this);
    }

    public static void main(String[] args) {
        new DragGesture();
    }

    public Object getTransferData(DataFlavor flavor) {
        return null;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[0];
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return false;
    }
}