package exopizzas_etu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoiteSelectionner extends JDialog implements ActionListener {
// Attibuts graphiques
    JButton btAnnuler = new JButton("Annuler");
    JLabel txt = new JLabel();
    JButton btValider = new JButton("Valider");
    //1 attribut de type pizza
    Pizza pizza = null;
    JComboBox pizzaDispo;
    ArrayList<Pizza>listePizza=new ArrayList();
    public BoiteSelectionner(MaFenetre owner, ArrayList<Pizza> listePizzas) {
        super(owner, true);
        //constructeur de la classe Mère: owner propriétaire de la fenêtre (son parent),le second paramètre est true pour la rendre modale
        //placement des éléments graphiques
        listePizza=listePizzas;
        initialisation();
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
    }

    private void initialisation() {
        JPanel pano = new JPanel();
        pano.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        String nombrePizza[] = new String[listePizza.size()];
       for (int i = 0; i < listePizza.size(); i++) {
            nombrePizza[i]=listePizza.get(i).getNom();
        }
        pizzaDispo=new JComboBox(nombrePizza);
        cont.gridx=0;
        cont.gridy=0;
        txt.setText("Choix de la pizza:");
        pano.add(txt,cont);
        cont.gridy=1;
        pano.add(pizzaDispo,cont);
        cont.gridy=3;
        pano.add(btAnnuler,cont);
        cont.gridx=1;
        pano.add(btValider,cont);
        this.setContentPane(pano);
        this.pack();
       }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            pizza = null; //aucune pizza
            this.setVisible(false); //on ferme la fenêtre
        }
        if (e.getSource() == btValider) {
            String nom="";
            nom=pizzaDispo.getSelectedItem().toString();
            for (Pizza r:listePizza) {
                if(r.getNom()==nom){
                    pizza=r;
                }
            }
            this.setVisible(false); //on ferme la fenêtre
        }
    }

    public Pizza showDialog() {
        this.setVisible(true); //on ouvre la fenêtre
        return pizza; //on retourne le résultat
    }
}
