
import java.util.ArrayList;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Lucas
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deck deck = new Deck();
        
        int handsize = 5;
        
        String name = "War"; 
        
        War war = new War(deck, handsize, name);
        war.play();
        
    }
        
    
}
