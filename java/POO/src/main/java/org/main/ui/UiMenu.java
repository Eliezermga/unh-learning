package org.main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UiMenu extends JFrame {

    public UiMenu(){
        init();
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = getToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new UiMenu();
    }

    private  void init(){

        JMenuBar menuBar = new JMenuBar();
        JMenu operationMenu = new JMenu("Operation");
        JMenuItem livreItem = new JMenuItem("Livres");
        JMenuItem auteurItem = new JMenuItem("Auteur");
        JMenuItem publicationItem = new JMenuItem("Publication");
        JMenuItem exitItem = new JMenuItem("Quitter");

        livreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UILivre livreUI = new UILivre();
                livreUI.setVisible(true);
            }
        });

        auteurItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UiAuteur auteurUI = new UiAuteur();
                auteurUI.setVisible(true);
            }
        });

        publicationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPublication uiPublication = null;
                try {
                    uiPublication = new UIPublication();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                uiPublication.setVisible(true);
            }
        });


        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null,"vous etes sur de vouloire quiter");
                if(option == JOptionPane.YES_OPTION){
                    System.exit(0);
                }

            }
        });
        operationMenu.add(livreItem);
        operationMenu.add(auteurItem);
        operationMenu.add(publicationItem);
        operationMenu.addSeparator();
        operationMenu.add(exitItem);
        menuBar.add(operationMenu);

        JPanel panel = new JPanel();
        panel.add(menuBar);
        this.setContentPane(panel);


    }
}
