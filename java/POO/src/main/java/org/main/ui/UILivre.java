package org.main.ui;


import jdk.jshell.execution.Util;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.main.biblo.Livre;
import org.main.service.LivreManagerService;
import org.main.util.DateLabelFormatter;
import org.main.util.DocumentUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Properties;
public class UILivre extends JFrame {
    private JLabel labelId;
    private JLabel labelTitre;
    private JLabel labelDatePublication;
    private JLabel labelNombrePages;
    private JTextField textFieldId;
    private JTextField textFieldTitre;
    private JTextField textFieldDatePublication;
    private JTextField textFieldNombrePages;

    private JDatePicker publicationDatePicker;
    private JSpinner spinnerNombresPage;
    private Date selectedDate;
    private SimpleDateFormat dateFormat ;
    private UtilDateModel utilDateModel;
    private SpinnerModel    spinnerModel;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JButton buttonPrint;
    private JTable table;
    private JPanel panel;
    DefaultTableModel model;
    private String[] columns = {"ID", "TITRE", "DATE_PUBLICATION","NBRES_PAGES"};
    private JScrollPane scrollPane;
    private LivreManagerService service = new LivreManagerService();
    public UILivre() {
        init();
        setTitle("Interface Livre");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Livre livre = new Livre();
                livre.setId(Integer.parseInt(textFieldId.getText()));
                livre.setTitre(textFieldTitre.getText());
                //livre.setDatePublication((Date) publicationDatePicker.getModel().getValue().);
//                livre.setDatePublication((publicationDatePicker.getModel());
                livre.setNombrePages((Integer) spinnerNombresPage.getModel().getValue());
                try {
                    service.updateLivre(livre);
                    JOptionPane.showMessageDialog(null, "Livre updated !");
                    actualiser();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Livre update error !");
                }
            }
        });


        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Livre livre = new Livre();
                livre.setId(Integer.parseInt(textFieldId.getText()));
                livre.setTitre(textFieldTitre.getText());
               // livre.setDatePublication((Date) publicationDatePicker.getModel().getValue());
               // livre.setNombrePages((Integer) spinnerNombresPage.getModel().getValue());
                try {
                    service.supprimerLivre(livre);
                    JOptionPane.showMessageDialog(null, "Livre supprimer !");
                    actualiser();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Livre non supprimer error !");
                }
            }
        });

        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float[] widths = {20,80,120,30};
                DocumentUtils Utils= new DocumentUtils(columns, model, "Auteur", widths);
                Utils.print();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed (ActionEvent e){
                        if (buttonAdd.getText().equals("nouveau")) {
                            emptyFilds();
                        }else {
                        Livre livre = new Livre();
                        livre.setId(Integer.parseInt(textFieldId.getText()));
                        livre.setTitre(textFieldTitre.getText());
                        // livre.setDatePublication(Date.valueOf(textFieldDatePublication.getText()));
                        livre.setNombrePages((Integer) spinnerNombresPage.getModel().getValue());
                        try {
                            service.ajoutLivre(livre);
                            JOptionPane.showMessageDialog(null, "Livre ajouter !");
                            actualiser();
                            buttonAdd.setText("nouveau");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Livre non ajouter error !");
                        }
                    }
                 }});

    }

    public static void main(String args []){
        new UILivre();
    };

    void emptyFilds(){
        textFieldId.setText("-1");
        textFieldTitre.setText("");
       // publicationDatePicker
        spinnerNombresPage.setValue(0);
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonAdd.setText("Enregistrer");

    }
    private void init(){
        panel = new JPanel(new GridBagLayout());
        labelId = new JLabel("Id");
        labelTitre = new JLabel("Titre");
        labelDatePublication = new JLabel("DatePublication");
        labelNombrePages = new JLabel("nombrePages");
        textFieldId = new JTextField("-1",5);
        textFieldId.setEditable(false);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        utilDateModel = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today","Today") ;
        properties.put("text.month","Month") ;
        properties.put("text.year","Year") ;
        JDatePanelImpl datePanel = new JDatePanelImpl(utilDateModel, properties);
        publicationDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        publicationDatePicker.addActionListener(e ->{
            java.util.Date date = (java.util.Date)publicationDatePicker.getModel().getValue();
            selectedDate = new Date(date.getTime());
        });

        spinnerModel = new SpinnerNumberModel(0 , 0 ,2000,1);
        spinnerNombresPage = new JSpinner(spinnerModel);
        spinnerNombresPage.setPreferredSize(new Dimension(80,30));

        textFieldTitre = new JTextField(30);
        textFieldDatePublication = new JTextField(30);
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
                        String Titre = table.getValueAt(selectedRow, 1).toString();
                        String DatePublication = table.getValueAt(selectedRow, 2).toString();
                        textFieldId.setText(String.valueOf(Id));
                        textFieldTitre.setText(Titre);
                        textFieldDatePublication.setText(DatePublication);
                        buttonUpdate.setEnabled(true);
                        buttonDelete.setEnabled(true);
                        buttonAdd.setText("nouveau");
                    }
//                    else {
//                        textFieldId.setText("-1");
//                        textFieldTitre.setText("");
//                        textFieldDatePublication.setText("");
//                        buttonUpdate.setEnabled(false);
//                        buttonDelete.setEnabled(false);
//                    }
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
        panel.add(labelTitre,gbc);
        gbc.gridx = 1;
        panel.add(textFieldTitre,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelDatePublication,gbc);
        gbc.gridx = 1;
        panel.add((Component)publicationDatePicker,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelNombrePages,gbc);
        gbc.gridx = 1;
        panel.add(spinnerNombresPage,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(buttonAdd,gbc);
        gbc.gridx = 1;
        panel.add(buttonUpdate,gbc);
        gbc.gridx = 2;
        panel.add(buttonDelete,gbc);
        gbc.gridx=3;
        panel.add(buttonPrint,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
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
            for(Livre livre : service.getLivres()){
                model.addRow(new Object[]{livre.getId(), livre.getTitre(), livre.getDatePublication(),livre.getNombrePages()});
            }
            model.fireTableDataChanged();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
