package exopizzas_etu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoiteAjouter extends JDialog implements ActionListener {
// Attibuts graphiques
JButton btAnnuler = new JButton("Annuler");
    JButton btValider = new JButton("Valider");
    JLabel labelNom = new JLabel();
    JLabel labelPrix = new JLabel();
    JLabel labelIngredients = new JLabel();
    JTextField txtNom = new JTextField();
    JTextField txtPrix = new JTextField();
    JTextArea txtIngredient = new JTextArea();
    Pizza pizza=null;
    public BoiteAjouter(MaFenetre owner) {
        super(owner, true);
        //constructeur de la classe Mère: owner propriétaire de la fenêtre (son parent),le second paramètre est true pour la rendre modale
        //placement des éléments graphiques

        initialisation();
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
    }

    private void initialisation() {
        JPanel pano = new JPanel();
        pano.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill=GridBagConstraints.HORIZONTAL;
        cont.gridx=0;
        cont.gridy=0;
        labelNom.setText("Pizza:");
        pano.add(labelNom,cont);
        cont.gridx = 1;
        txtNom.setText("");
        pano.add(txtNom,cont);
        cont.gridx=0;
        cont.gridy=1;
        labelPrix.setText("Prix:");
        pano.add(labelPrix,cont);
        cont.gridx = 1;
        txtPrix.setText("");
        pano.add(txtPrix,cont);
        cont.gridy=3;
        cont.fill=GridBagConstraints.CENTER;
        cont.gridx = 0;
        cont.gridwidth=2;
        labelIngredients.setText("Ingredients:");
        pano.add(labelIngredients,cont);
        cont.fill=GridBagConstraints.BOTH;
        txtIngredient.setColumns(20);
        txtIngredient.setRows(10);
        txtIngredient.setLineWrap(true);
        txtIngredient.setWrapStyleWord(true);

        txtIngredient.setText("");
        cont.gridy=4;
        pano.add(txtIngredient,cont);
        cont.gridwidth=1;
        cont.gridx=0;
        cont.gridy = 5;
        pano.add(btAnnuler, cont);
        cont.gridx = 1;
        pano.add(btValider, cont);
        this.setContentPane(pano);
        this.pack();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler){
            pizza=null; //aucune pizza
            this.setVisible(false); //on ferme la fenêtre
        }
        if (e.getSource() == btValider){
            pizza=new Pizza(txtNom.getText(),txtIngredient.getText(),Double.parseDouble(txtPrix.getText()));
                this.setVisible(false); //on ferme la fenêtre
        }
    }
    public Pizza showDialog(){
        this.setVisible(true); //on ouvre la fenêtre
        return pizza; //on retourne le résultat
    }
}