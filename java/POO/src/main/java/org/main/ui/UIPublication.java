package org.main.ui;

//import humain.Auteur;
//import humain.Specialites;
//import librairie.Livre;
//import librairie.Publication;
//import manager.*;
//import utils.DocumentUtils;

import org.main.humain.Sexe;
import org.main.humain.Specialites;
import org.main.biblo.Livre;
import org.main.biblo.Publication;
import org.main.humain.Auteur;
import org.main.service.*;
import org.main.util.DocumentUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UIPublication extends JFrame {
    private AuteurManager serviceAuteur = new AuteurManagerService();
    private LivreManager serviceLivre = new LivreManagerService();
    private PublicationManager servicePublication = new PublicationManagerService();
    private JPanel panel;
    private List<Auteur> auteurs = new ArrayList<>();
    private List<Livre> livres = new ArrayList<>();
    private List<Publication> publications = new ArrayList<>();
    private JTextField textFieldId;
    private JList<String> jListAuteurs;
    private JButton enregistrerButton;
    private JButton printButton;
    private JLabel labelId;
    private JLabel labelLivre;
    private JLabel labelAuteurs;
    private JComboBox comboBoxLivres;

    private DefaultListModel<String> listModel;
    private JTable table;
    private DefaultTableModel model;
    private String[] columnNames = {"ID", "LIVRE", "AUTEURS"};

    public UIPublication() throws SQLException {
        init();
        setTitle("Publication");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enregistrerButton.getText().equals("Nouveau")) {
                    emptyFields();
                } else {
                    try {
                        int selectedIndex = comboBoxLivres.getSelectedIndex();
                        int idLivre = livres.get(selectedIndex).getId();
                        int[] selectedIndices =  jListAuteurs.getSelectedIndices();
                        List<Integer> idAuteurs = new ArrayList<>();
                        for (int i: selectedIndices) {
                            idAuteurs.add(auteurs.get(i).getId());
                        }

                        servicePublication.savePublication(idLivre, idAuteurs);
                        JOptionPane.showMessageDialog(null, "Publication bien enregistree!");
                        actualiser();
                        enregistrerButton.setText("Nouveau");
                    } catch (Exception es) {
                        System.out.println(es);


                    }
                }
            }
        });


        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float[] columnWidths = {20, 80, 120};
                new DocumentUtils(columnNames, model, "publications", columnWidths).print();
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new UIPublication();
    }

    void emptyFields() {
        textFieldId.setText("-1");
        enregistrerButton.setText("Enregistrer");
    }

    void actualiser() throws SQLException {
        auteurs = serviceAuteur.getAuteurs();
        livres = serviceLivre.getLivres();
        publications = servicePublication.getPublications();

        for (Auteur auteur : auteurs) {
            listModel.addElement(auteur.getNom());
        }


        for (Livre livre : livres) {
            comboBoxLivres.addItem(livre.getTitre());
        }
        try {
            model.setRowCount(0);
            for (Publication pub : publications) {
                model.addRow(new Object[]{pub.getId(), pub.getTitre(), pub.getAuteurs()});
            }
            model.fireTableDataChanged();
        } catch (Exception es) {
            System.out.println(es);
        }

    }

    public void init() throws SQLException {
        panel = new JPanel(new GridBagLayout());
        labelId = new JLabel("Id");
        labelLivre = new JLabel("Livre");
        labelAuteurs = new JLabel("Auteurs");

        textFieldId = new JTextField("-1", 5);
        textFieldId.setEditable(false);
        comboBoxLivres = new JComboBox();
        enregistrerButton = new JButton("Enregistrer");
        printButton = new JButton("Print");

        listModel = new DefaultListModel<>();
        jListAuteurs = new JList<>(listModel);
        jListAuteurs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jListAuteurs.setVisibleRowCount(4);
        JScrollPane listScrollPane = new JScrollPane(jListAuteurs);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelId, gbc);
        gbc.gridx = 2;
        panel.add(textFieldId, gbc);
        gbc.gridx = 4;
        panel.add(enregistrerButton, gbc);
        gbc.gridx = 5;
        panel.add(printButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelLivre, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 6;
        panel.add(comboBoxLivres, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelAuteurs, gbc);
        gbc.gridx = 2;
        gbc.gridheight = 9;
        panel.add(listScrollPane, gbc);

        gbc.gridx = 4;
        gbc.gridy = gbc.gridy + gbc.gridheight;
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        actualiser();
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridy += 4;
        gbc.gridx = 0;
        gbc.gridwidth = 8;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        this.setContentPane(panel);
    }

}
