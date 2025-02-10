package org.main.ui;


import org.main.humain.Auteur;
import org.main.humain.Sexe;
import org.main.humain.Specialites;
import org.main.service.AuteurManagerService;
import org.main.util.DocumentUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UiAuteur extends JFrame {
    private JLabel labelId;
    private JLabel labelNom;
    private JLabel labelEmail;
    private JTextField textFieldId;
    private JTextField textFieldNom;
    private JTextField textFieldEmail;
    private JLabel labelSexe;
    private JLabel labelSpecialites;
    private JRadioButton radioButtonM;
    private JRadioButton radioButtonF;
    private JRadioButton radioButtonN;
    private ButtonGroup buttonGroup;
    private JComboBox <Specialites> comboBoxSpecialites;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JButton buttonPrint;
    private JTable table;
    private JPanel panel;
    DefaultTableModel model;
    private String[] columns = {"ID", "NOM", "EMAIL","SEXE","SPECIALITES"};
    private JScrollPane scrollPane;
    private AuteurManagerService service = new AuteurManagerService();
    public UiAuteur() {
        init();
        setTitle("Interface Auteur");
        setSize(600, 600);
        setLocationRelativeTo(null);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auteur auteur = new Auteur();
                auteur.setId(Integer.parseInt(textFieldId.getText()));
                auteur.setNom(textFieldNom.getText());
                auteur.setEmail(textFieldEmail.getText());
                auteur.setSpecialites(Specialites.valueOf((comboBoxSpecialites.getSelectedItem().toString())));
                if (radioButtonM.isSelected()) {
                    auteur.setSexe((Sexe.M));
                } else if (radioButtonF.isSelected()) {
                    auteur.setSexe((Sexe.F));
                }
                else {
                    auteur.setSexe(Sexe.NULL);
                }
                try {
                    service.updateAuteur(auteur);
                    JOptionPane.showMessageDialog(null, "Auteur updated !");
                    actualiser();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Auteur update error !");
                }
            }
        });


        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auteur auteur = new Auteur();
                auteur.setId(Integer.parseInt(textFieldId.getText()));
                auteur.setNom(textFieldNom.getText());
                auteur.setEmail(textFieldEmail.getText());

                try {
                    service.supprimerAuteur(auteur);
                    JOptionPane.showMessageDialog(null, "Auteur supprimer !");
                    actualiser();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Auteur non supprimer error !");
                }
            }
        });
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               float[] widths = {20,80,120,30,100};
                DocumentUtils documentUtils= new DocumentUtils(columns, model, "Auteur", widths);
                documentUtils.print();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonAdd.getText().equals("nouveau")) {
                    emptyFilds();
                }else {

                Auteur auteur = new Auteur();
                auteur.setId(Integer.parseInt(textFieldId.getText()));
                auteur.setNom(textFieldNom.getText());
                auteur.setEmail(textFieldEmail.getText());
                auteur.setSpecialites(Specialites.valueOf((comboBoxSpecialites.getSelectedItem().toString())));
                if (radioButtonM.isSelected()) {
                    auteur.setSexe((Sexe.M));
                } else if (radioButtonF.isSelected()) {
                    auteur.setSexe((Sexe.F));
                }
                else {
                    auteur.setSexe(Sexe.NULL);
                }
                try {
                    service.ajoutAuteur(auteur);
                    JOptionPane.showMessageDialog(null, "Auteur ajouter !");
                    actualiser();
                    buttonAdd.setText("nouveau");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Auteur non ajouter Erreur 003E!");
                }
            }
        }});

    }

    public static void main(String args []){
        new UiAuteur();
    };
     void emptyFilds(){
        textFieldId.setText("-1");
        textFieldNom.setText("");
        textFieldEmail.setText("");
        comboBoxSpecialites.setSelectedIndex(0);
         buttonUpdate.setEnabled(false);
         buttonDelete.setEnabled(false);
         buttonAdd.setText("Enregistrer");

    }

    private void init(){
        panel = new JPanel(new GridBagLayout());
        labelId = new JLabel("Id");
        labelNom = new JLabel("Nom");
        labelEmail = new JLabel("Email");
        textFieldId = new JTextField("-1",5);
        textFieldId.setEditable(false);
        textFieldNom = new JTextField(30);
        textFieldEmail = new JTextField(30);
        labelSexe = new JLabel("Sexe");
        labelSpecialites = new JLabel("specialites");
        radioButtonM = new JRadioButton("M");
        radioButtonF = new JRadioButton("F");
        radioButtonN = new JRadioButton("N");
        buttonGroup = new ButtonGroup();
        comboBoxSpecialites = new JComboBox<>(Specialites.values());
        buttonGroup.add(radioButtonM);
        buttonGroup.add(radioButtonF);
        buttonGroup.add(radioButtonN);
        buttonAdd = new JButton("Enregistrer");
        buttonDelete = new JButton("Supprimer");
        buttonUpdate = new JButton("Update");
        buttonPrint = new JButton("imprimer");
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
        model = new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        actualiser();
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    int selectedRow = table.getSelectedRow();

                    if(selectedRow != -1){
                        int Id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        String Nom = table.getValueAt(selectedRow, 1).toString();
                        String Email = table.getValueAt(selectedRow, 2).toString();
                        textFieldId.setText(String.valueOf(Id));
                        textFieldNom.setText(Nom);
                        textFieldEmail.setText(Email);
//
                        buttonUpdate.setEnabled(true);
                        buttonDelete.setEnabled(true);
                        buttonAdd.setText("nouveau");
                        String sexe = (String) table.getValueAt(selectedRow, 3);
                        if (sexe.valueOf(sexe).equals(Sexe.M)) {
                            radioButtonM.setSelected(true);
                        } else if (sexe.valueOf(sexe).equals(Sexe.F)) {
                            radioButtonF.setSelected(true);
                        }
                        else {
                            radioButtonN.setSelected(true);
                        }
                        String specialites = (String) table.getValueAt(selectedRow, 4);
                        comboBoxSpecialites.setSelectedItem(specialites.valueOf(specialites));
                    }


                    else {
                        textFieldId.setText("-1");
                        textFieldNom.setText("");
                        textFieldEmail.setText("");

                    }
                }
            }
        });
        scrollPane = new JScrollPane(table);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =new Insets(1,1,1,1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelId,gbc);
        gbc.gridx = 1;
        panel.add(textFieldId,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelNom,gbc);
        gbc.gridx = 1;
        panel.add(textFieldNom,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelEmail,gbc);
        gbc.gridx = 1;
        panel.add(textFieldEmail,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelSexe,gbc);
        gbc.gridx = 1;
        panel.add(radioButtonM,gbc);
        radioButtonM.setSelected(true);
        gbc.gridx = 2;
        panel.add(radioButtonF,gbc);
        gbc.gridx = 3;
        panel.add(radioButtonN,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelSpecialites,gbc);
        gbc.gridx = 1;
        panel.add(comboBoxSpecialites,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(buttonAdd,gbc);
        gbc.gridx = 1;
        panel.add(buttonUpdate,gbc);
        gbc.gridx = 2;
        panel.add(buttonDelete,gbc);
        gbc.gridx = 3;
        panel.add(buttonPrint,gbc);
        gbc.gridx=0;
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane,gbc);
        this.setContentPane(panel);


    }

    private void actualiser(){
        try {
            model.setRowCount(0);
            for(Auteur auteur : service.getAuteurs()){
                model.addRow(new Object[]{auteur.getId(), auteur.getNom(), auteur.getEmail(), auteur.getSexe() , auteur.getSpecialites()
                                 });
            }
            model.fireTableDataChanged();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
