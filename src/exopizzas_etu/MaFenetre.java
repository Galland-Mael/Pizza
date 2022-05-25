/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exopizzas_etu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author Brice Effantin
 */
public class MaFenetre extends JFrame implements ActionListener {

    private ArrayList<Pizza> listePizzas = new ArrayList();

    private ArrayList<JComboBox> choixNombrePizza = new ArrayList<>();
    private JButton btCalculer = new JButton("Calculer");
    private JButton btRaz = new JButton("RAZ");
    JLabel addition = new JLabel();
    JMenuBar bar = new JMenuBar();
    JMenuItem ajouter = new JMenuItem("Ajouter");
    JMenuItem supprimer = new JMenuItem("Supprimer");
    JMenuItem modifier = new JMenuItem("Modifier");
    JMenu menu = new JMenu("Pizza");
    public MaFenetre() {
        this.setTitle("Pizza helper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialisation();
        affichage();
        btRaz.addActionListener(this);
        btCalculer.addActionListener(this);
        ajouter.addActionListener(this);
        supprimer.addActionListener(this);
        modifier.addActionListener(this);
    }

    public void initialisation() {
        listePizzas.clear();
        ajoutPizza("Quatre Fromages", "Tomate,Mozzarella,Chedar,Gorgonzola", 8.0);
        ajoutPizza("Diabolico", "Mozzarella,Merguez,Tomate,Poivron", 7.5);
        ajoutPizza("Hawaienne", "Mozzarella,Jambon,Ananas,Tomate", 9.0);
        ajoutPizza("Reine", "Mozzarella,Tomate,Jambon,Champignon", 7.0);
        ajoutPizza("Marguerite", "Tomate,Mozzarella", 6.5);
        ajoutPizza("Calzone", "Mozzarella,Tomate,Jambon,Oeuf", 11.0);

    }
    public void affichage(){
        addition.setText("0.0€");
        //creation des listes
        ArrayList<JLabel> listePizzasAffichage = new ArrayList<>();
        for (int i = 0; i < listePizzas.size(); i++) {
            JLabel temp = new JLabel();
            temp.setText(listePizzas.get(i).toString());
            listePizzasAffichage.add(temp);
            String nombrePizza[] = new String[]{"0","1","2","3","4","5"};
            JComboBox tmp = new JComboBox(nombrePizza);
            choixNombrePizza.add(tmp);
        }
        JPanel pano = new JPanel();
        pano.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill=GridBagConstraints.HORIZONTAL;
        for (int i = 0; i <listePizzas.size() ; i++) {
            cont.gridx=0;cont.gridy=i;
            pano.add(listePizzasAffichage.get(i),cont);
            cont.gridx=1;
            pano.add(choixNombrePizza.get(i),cont);
        }
        //Creation du pano et de la contrainte

        //ajout des menu
        bar.add(menu);
        this.setJMenuBar(bar);
        menu.add(ajouter);
        menu.add(supprimer);
        menu.add(modifier);
        //ajout des composant dans le panneau
        cont.gridx=3;
        cont.gridy=0;
        cont.gridheight=2;
        btRaz.setBackground(Color.RED);
        pano.add(btRaz,cont);
        cont.fill=GridBagConstraints.VERTICAL;
        cont.gridy=2;
        pano.add(btCalculer,cont);
        cont.gridy=4;
        pano.add(addition,cont);
        this.setContentPane(pano);
        this.pack();
    }



    /**
     * Ajoute une pizza à la liste
     *
     * @param nomPizza
     * @param ingredients
     * @param tarif
     */
    public void ajoutPizza(String nomPizza, String ingredients, double tarif) {
        listePizzas.add(new Pizza(nomPizza, ingredients, tarif));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btCalculer){
            double somme=0;
            for (int i = 0; i < listePizzas.size() ; i++) {
                somme+=listePizzas.get(i).getTarif()*Double.parseDouble(choixNombrePizza.get(i).getSelectedItem().toString());
            }
            addition.setText(somme+"€");
        }else if(e.getSource()==btRaz){
            for (int i = 0; i < listePizzas.size() ; i++) {
                choixNombrePizza.get(i).setSelectedItem("0");
            }
            addition.setText("0.0€");
        }else if(e.getSource()==ajouter){
            BoiteAjouter boite = new BoiteAjouter(this);
            Pizza p = boite.showDialog();
            listePizzas.add(p);
            affichage();
        }else if(e.getSource()==supprimer){
            BoiteSelectionner boite = new BoiteSelectionner(this,listePizzas);
            Pizza p = boite.showDialog();
            listePizzas.remove(p);
            affichage();
        }else if(e.getSource()==modifier){
            BoiteSelectionner boite = new BoiteSelectionner(this,listePizzas);
            Pizza base = boite.showDialog();
            BoiteModifier box = new BoiteModifier(this,base);
            Pizza after = box.showDialog();
            base=after;
            affichage();
        }
    }
}
