package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Readable in = new InputStreamReader(System.in);
        Appendable out = System.out;

        List<Card> deck = new BasicPyramidSolitaire().getDeck();
        BasicPyramidSolitaire model = new BasicPyramidSolitaire();
        PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
        controller.playGame(model, deck, true, 2, 5);
    }
}
