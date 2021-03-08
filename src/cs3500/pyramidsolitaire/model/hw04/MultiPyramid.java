package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardValue;
import cs3500.pyramidsolitaire.model.hw02.Suite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** class for three pyramid variation of Pyramid Solitaire. */
public class MultiPyramid extends AbstractPyramidSolitaire {

  public MultiPyramid() {
    super();
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (int ii = 0; ii < 2; ii++) {
      for (Suite s : Suite.values()) {
        for (CardValue val : CardValue.values()) {
          deck.add(new Card(val, s));
        }
      }
    }

    return deck;
  }

  /**
   * makes list of lists of cards, first list is top of pyramid holds one card, second list is 2nd
   * row of pyramid hold two cards etc.
   */
  protected void makePyramid() {
    // iterates through every row

    int count = 0;
    int score = 0;
    int not_overlapping = this.numRows / 2;
    for (int ii = 1; ii < this.numRows + 1; ii++) {
      ArrayList<Card> row = new ArrayList<>();
      ArrayList<Boolean> rowCovered = new ArrayList<>();

      // iterates through every column of the row ii
      if (ii > not_overlapping) {
        int num_iter = 3 * not_overlapping + (ii - not_overlapping);
        for (int kk = 0; kk < num_iter; kk++) {
          count += 1;
          Card c = this.stockCards.remove(0);
          score += c.getCardValue().getValue();
          row.add(c);
          if (this.numRows == ii) {
            rowCovered.add(false);
          } else {
            rowCovered.add(true);
          }
        }

      } else {
        for (int kk = 0; kk < 3; kk++) {
          for (int dd = 0; dd < ii; dd++) {
            count += 1;
            Card c = this.stockCards.remove(0);
            score += c.getCardValue().getValue();
            row.add(c);
            rowCovered.add(true);
          }
          if (kk != 2) {
            for (int nn = ii; nn < not_overlapping; nn++) {
              row.add(null);
              rowCovered.add(false);
            }
          }
        }
      }
      this.coveredCards.add(rowCovered);
      this.pyramid.add(row);
    }
    this.pyramidCardNum = count;

    this.score = score;
  }

  /**
   * checks if a deck has 104 cards and each card exists only 2 times.
   *
   * @param deck a list of cards
   * @return true if the object is valid, false if it is invalid
   */
  private boolean checkDeck(List<Card> deck) {
    if (deck == null) {
      return false;
    }

    if (deck.size() != 104) {
      return false;
    }

    List<Card> trueDeck = getDeck();
    for (Card c : deck) {
      if (!(trueDeck.contains(c))) {
        return false;
      } else {
        trueDeck.remove(c);
      }
    }

    return trueDeck.size() == 0;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {

    this.pyramid = new ArrayList<>();
    this.coveredCards = new ArrayList<>();
    this.drawCards = new ArrayList<>();
    this.stockCards = new ArrayList<>();

    if (!(checkDeck(deck))) {
      throw new IllegalArgumentException("Deck is not valid");
    }

    for (int ii = 0; ii < 104; ii++) {
      this.stockCards.add(new Card(deck.get(ii).getCardValue(), deck.get(ii).getSuite()));
    }

    if (shuffle) {
      Collections.shuffle(this.stockCards);
    }

    if (numRows < 1 || numRows > 8) {
      throw new IllegalArgumentException("Invalid number of rows");
    }

    this.gameStarted = true;
    this.numRows = numRows;
    this.numDraw = numDraw;
    makePyramid();
    if (numDraw < 0 || numDraw > (104 - this.pyramidCardNum)) {
      throw new IllegalArgumentException("Invalid number of draw cards");
    }

    for (int ii = 0; ii < this.numDraw; ii++) {
      this.drawCards.add(this.stockCards.remove(0));
    }
  }
}
