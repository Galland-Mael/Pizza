/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exopizzas_etu;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.*;
/**
 * @author Brice Effantin
 */
public class MaFenetre extends JFrame {

    private ArrayList<Pizza> listePizzas = new ArrayList();
    private ArrayList<JLabel> listePizzasAffichage = new ArrayList<>();
    private ArrayList<JComboBox> choixNombrePizza = new ArrayList<>();
    private JButton btCalculer = new JButton("Calculer");
    private JButton btRaz = new JButton("RAZ");
    JLabel Addition = new JLabel();

    public MaFenetre() {
        this.setTitle("Pizza helper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialisation();
    }

    public void initialisation() {
        ajoutPizza("Quatre Fromages", "Tomate,Mozzarella,Chedar,Gorgonzola", 8.0);
        ajoutPizza("Diabolico", "Mozzarella,Merguez,Tomate,Poivron", 7.5);
        ajoutPizza("Hawaienne", "Mozzarella,Jambon,Ananas,Tomate", 9.0);
        ajoutPizza("Reine", "Mozzarella,Tomate,Jambon,Champignon", 7.0);
        ajoutPizza("Marguerite", "Tomate,Mozzarella", 6.5);
        ajoutPizza("Calzone", "Mozzarella,Tomate,Jambon,Oeuf", 11.0);

        //creation des listes
        for (int i = 0; i < listePizzas.size(); i++) {
            JLabel temp = new JLabel(listePizzas.get(i).toString());
            listePizzasAffichage.add(temp);
            String nombrePizza[] = new String[]{"1","2","3","4","5"};
            JComboBox tmp = new JComboBox(nombrePizza);
            choixNombrePizza.add(tmp);
        }
        //Creation du pano et de la contrainte
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
        cont.gridx=3;
        cont.gridy=0;
        cont.gridheight=2;
        btRaz.setBackground(Color.RED);
        pano.add(btRaz,cont);
        cont.fill=GridBagConstraints.VERTICAL;
        cont.gridy=2;
        pano.add(btCalculer,cont);
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


}
