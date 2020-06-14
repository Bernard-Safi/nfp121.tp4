package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(null);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(new pushOperation());
        boutons.add(add);   add.addActionListener(new addOperation());
        boutons.add(sub);   sub.addActionListener(new subOperation());
        boutons.add(mul);   mul.addActionListener(new mulOperation());
        boutons.add(div);   div.addActionListener(new divOperation());
        boutons.add(clear); clear.addActionListener(new clearOperation());
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        // à compléter
        //if pile is full you can't push anymore
         if (pile.estPleine())
            push.setEnabled(false);
        //if pile is empty you can't clear , add , sum , mul , div
        else if (pile.estVide()){
            push.setEnabled(true);
            clear.setEnabled(false);
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        } 
        //ife pile has 1 element you can't add , sum , mul , div
        else if (pile.taille() == 1){
            push.setEnabled(true);
            clear.setEnabled(true);
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        } 
        // else you can do everything so all are enabled
        else {
            push.setEnabled(true);
            clear.setEnabled(true);
            add.setEnabled(true);
            sub.setEnabled(true);
            mul.setEnabled(true);
            div.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)
    
    private class pushOperation implements ActionListener {
        public void actionPerformed (ActionEvent event){
            try {
                pile.empiler(operande());
            }
            catch (NumberFormatException exception){}
            catch (PilePleineException exception){}

            actualiserInterface();
        }
    }
    
    private class addOperation  implements ActionListener {
        public void actionPerformed (ActionEvent event){
            int number1 = 0, number2 = 0, result = 0;

            try {
                number1 = pile.depiler();
                number2 = pile.depiler();
                result = number1 + number2;
                pile.empiler(result);
            }
            catch (PileVideException exception){}
            catch (PilePleineException exception){}

            actualiserInterface();
        }
    }
    
  
    
    private class subOperation  implements ActionListener {
        public void actionPerformed (ActionEvent event){
            int number1 = 0, number2 = 0, result = 0;

            try {
             
                number1 = pile.depiler();
                number2 = pile.depiler();
                result = number1-number2;
                pile.empiler(result);
            }
            catch (PileVideException exception){}
            catch (PilePleineException exception){}

            actualiserInterface();
        }
    }
    
    private class mulOperation  implements ActionListener {
        public void actionPerformed (ActionEvent event){
            int number1 = 0, number2 = 0, result = 0;

            try {
                number1 = pile.depiler();
                number2 = pile.depiler();
                result = number1 * number2;
                pile.empiler(result);
            }
            catch (PileVideException exception){}
            catch (PilePleineException exception){}

            actualiserInterface();
        }
    }

     private class divOperation  implements ActionListener {
        public void actionPerformed (ActionEvent event){
            int number1 = 0, number2 = 0, result = 0;

            try {
                number1 = pile.depiler();
                number2 = pile.depiler();
                // can't devide by 0 get them back to the pile
                if (number1 == 0 ) {
                    pile.empiler(number2);
                    pile.empiler(number1);
                }
                else {
                    result = number2 / number1;
                    pile.empiler(result);
                }
            }
            catch (PileVideException exception){}
            catch (PilePleineException exception){}

            actualiserInterface();
        }
    }
    
        private class clearOperation  implements ActionListener {
        public void actionPerformed (ActionEvent event){
            
            while (!pile.estVide()){
                try {
                    pile.depiler();
                }
                catch (PileVideException exception){}
            }

            actualiserInterface();
        }
    }
    
    
    
    

}
