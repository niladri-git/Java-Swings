/* Partly Working */

package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class DocumentModel extends JFrame {

    private StyledDocument sdoc;
    private JTextPane textpane;

    public DocumentModel() {
     
        createToolbar();
        initUI();
    }

    private void createToolbar() {
        
        JToolBar toolbar = new JToolBar();

        ImageIcon bold = new ImageIcon("bold.jpg");
        ImageIcon italic = new ImageIcon("italic.jpg");
        ImageIcon strike = new ImageIcon("strike.jpg");
        ImageIcon underline = new ImageIcon("underline.jpg");

        JButton boldb = new JButton(bold);
        JButton italb = new JButton(italic);
        JButton strib = new JButton(strike);
        JButton undeb = new JButton(underline);

        toolbar.add(boldb);
        toolbar.add(italb);
        toolbar.add(strib);
        toolbar.add(undeb);

        add(toolbar, BorderLayout.NORTH);
        
        boldb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sdoc.setCharacterAttributes(textpane.getSelectionStart(), 
                    textpane.getSelectionEnd() - textpane.getSelectionStart(),
                    textpane.getStyle("Bold"), false);
            }
        });

        italb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sdoc.setCharacterAttributes(textpane.getSelectionStart(), 
                    textpane.getSelectionEnd() - textpane.getSelectionStart(),
                    textpane.getStyle("Italic"), false);
            }

        });

        strib.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sdoc.setCharacterAttributes(textpane.getSelectionStart(), 
                    textpane.getSelectionEnd() - textpane.getSelectionStart(),
                    textpane.getStyle("Strike"), false);
            }

        });

        undeb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sdoc.setCharacterAttributes(textpane.getSelectionStart(), 
                    textpane.getSelectionEnd() - textpane.getSelectionStart(),
                    textpane.getStyle("Underline"), false);
            }
        });          
                                     
    }
    
    private void initUI() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane pane = new JScrollPane();
        textpane = new JTextPane(); 
        textpane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        sdoc = textpane.getStyledDocument();

        Style style = textpane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);

        style = textpane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);

        style = textpane.addStyle("Underline", null);
        StyleConstants.setUnderline(style, true);

        style = textpane.addStyle("Strike", null);
        StyleConstants.setStrikeThrough(style, true);

        pane.getViewport().add(textpane);
        panel.add(pane);

        add(panel);

        setTitle("Document Model");
        setSize(new Dimension(380, 320));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DocumentModel ex = new DocumentModel();
                ex.setVisible(true);
            }
        });
    }
}