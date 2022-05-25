package exopizzas_etu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class BoiteModifier extends JDialog implements ActionListener, FocusListener {
    // Attibuts graphiques
    JButton btAnnuler = new JButton("Annuler");
    JButton btValider = new JButton("Valider");
    JLabel labelNom = new JLabel();
    JLabel labelPrix = new JLabel();
    JLabel labelIngredients = new JLabel();
    JTextField txtNom = new JTextField();
    JTextField txtPrix = new JTextField();
    JTextArea txtIngredient = new JTextArea();

    //1 attribut de type pizza
    Pizza pizza = null;

    public BoiteModifier(MaFenetre owner, Pizza p) {
        super(owner, true);
        pizza=p;
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
        txtNom.setText(pizza.getNom());
        pano.add(txtNom,cont);
        cont.gridx=0;
        cont.gridy=1;
        labelPrix.setText("Prix:");
        pano.add(labelPrix,cont);
        cont.gridx = 1;
        txtPrix.setText(String.valueOf(pizza.getTarif()));
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

        txtIngredient.setText(pizza.getIngredients());
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
        if (e.getSource() == btAnnuler) {
            pizza = null; //aucune pizza
            this.setVisible(false); //on ferme la fenêtre
        }
        if (e.getSource() == btValider) {
           pizza.setIngredients(txtIngredient.getText());
            pizza.setTarif(Double.parseDouble(txtPrix.getText()));
            pizza.setNom(txtNom.getText());
            this.setVisible(false); //on ferme la fenêtre
        }
    }

    public Pizza showDialog() {
        this.setVisible(true); //on ouvre la fenêtre
        return pizza; //on retourne le résultat
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==txtNom){
            txtNom.setText("");
        }
        if(e.getSource()==txtPrix){
            txtPrix.setText("");
        }if(e.getSource()==txtIngredient){
            txtIngredient.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
