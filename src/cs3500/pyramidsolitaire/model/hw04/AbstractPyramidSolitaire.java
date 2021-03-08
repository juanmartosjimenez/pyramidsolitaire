package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardValue;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.Suite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for pyramid solitaire that is extended by all the different implementations of
 * pyramid solitaire.
 */
public abstract class AbstractPyramidSolitaire implements PyramidSolitaireModel<Card> {

  protected int numRows;
  protected int pyramidCardNum;
  protected int numDraw;
  protected List<Card> stockCards;
  protected List<ArrayList<Card>> pyramid;
  protected List<ArrayList<Boolean>> coveredCards;
  protected List<Card> drawCards;
  protected boolean gameStarted;
  protected int score;

  /** constructor class for the AbstractPyramidSolitaire class. */
  public AbstractPyramidSolitaire() {
    this.gameStarted = false;
    this.numRows = -1;
    this.pyramidCardNum = -1;
    this.numDraw = -1;
    this.score = -1;
    this.pyramid = new ArrayList<>();
    this.coveredCards = new ArrayList<>();
    this.drawCards = new ArrayList<>();
    this.stockCards = new ArrayList<>();
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (Suite s : Suite.values()) {
      for (CardValue val : CardValue.values()) {
        deck.add(new Card(val, s));
      }
    }
    return deck;
  }

  /**
   * checks if a deck has 52 cards and each card is unique.
   *
   * @param deck a list of cards
   * @return true if the object is valid, false if it is invalid
   */
  private boolean checkDeck(List<Card> deck) {
    if (deck == null) {
      return false;
    }

    if (deck.size() != 52) {
      return false;
    }

    List<Card> trueDeck = getDeck();
    for (Card c : trueDeck) {
      if (!(deck.contains(c))) {
        return false;
      }
    }

    return true;
  }

  /**
   * makes list of lists of cards, first list is top of pyramid holds one card, second list is 2nd
   * row of pyramid hold two cards etc.
   */
  protected void makePyramid() {
    // iterates through every row

    int count = 0;
    int score = 0;
    for (int ii = 1; ii < this.numRows + 1; ii++) {
      ArrayList<Card> row = new ArrayList<>();
      ArrayList<Boolean> rowCovered = new ArrayList<>();
      // iterates through every column of the row ii
      for (int jj = 0; jj < ii; jj++) {

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
      this.coveredCards.add(rowCovered);
      this.pyramid.add(row);
    }
    this.score = score;
    if (this.pyramidCardNum != count) {
      throw new IllegalArgumentException("unexpected number of cards in pyramid");
    }
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

    for (int ii = 0; ii < 52; ii++) {
      this.stockCards.add(new Card(deck.get(ii).getCardValue(), deck.get(ii).getSuite()));
    }

    if (shuffle) {
      Collections.shuffle(this.stockCards);
    }

    this.pyramidCardNum = (int) ((0.5) * numRows * (numRows + 1));

    if (numRows < 1 || numRows > 9) {
      throw new IllegalArgumentException("Invalid number of rows");
    }

    if (numDraw < 0 || numDraw > (52 - this.pyramidCardNum)) {
      throw new IllegalArgumentException("Invalid number of draw cards");
    }

    this.gameStarted = true;
    this.numRows = numRows;
    this.numDraw = numDraw;
    makePyramid();
    for (int ii = 0; ii < this.numDraw; ii++) {
      this.drawCards.add(this.stockCards.remove(0));
    }
  }

  /**
   * checks if card removed from the pyramid makes another card unexposed.
   *
   * @param row row of card removed
   * @param card column number of card exposed
   */
  protected void newExposed(int row, int card) {
    if (row > 0) {
      // card is not in the edges of the row
      if (card > 0 && card < this.getRowWidth(row) - 1) {
        if (this.pyramid.get(row).get(card + 1) == null) {
          this.coveredCards.get(row - 1).set(card, false);
        }
        if (this.pyramid.get(row).get(card - 1) == null) {
          this.coveredCards.get(row - 1).set(card - 1, false);
        }
        // card is in the right edge of the row
      } else if (card > 0) {
        if (this.pyramid.get(row).get(card - 1) == null) {
          this.coveredCards.get(row - 1).set(card - 1, false);
        }
        // card is in the left edge of the row
      } else {
        if (this.pyramid.get(row).get(card) == null) {
          this.coveredCards.get(row - 1).set(card, false);
        }
      }
    }
  }

  /**
   * checks if card to be removed is not null, under another card, within allowed indexes.
   *
   * @param row row of card
   * @param card column of card
   */
  protected void checkCard(int row, int card) {
    if ((row > (this.numRows - 1)) || (card >= this.getRowWidth(row)) || card < 0) {
      throw new IllegalArgumentException("Card index or row index out of bounds");
    }
    if (this.coveredCards.get(row).get(card)) {
      throw new IllegalArgumentException("Card is not yet exposed");
    }

    if (this.pyramid.get(row).get(card) == null) {
      throw new IllegalArgumentException("Card already removed");
    }
  }

  /** throws an IllegalStateException if the game is not yet running. */
  protected void gameRunning() {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not started yet");
    }
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {

    gameRunning();

    checkCard(row1, card1);
    checkCard(row2, card2);

    if (card1 == card2 && row1 == row2) {
      throw new IllegalArgumentException("two of the same cards given");
    }

    if (this.pyramid.get(row1).get(card1).getCardValue().getValue()
            + this.pyramid.get(row2).get(card2).getCardValue().getValue()
        != 13) {
      throw new IllegalArgumentException("Sum of cards does not add up to 13");
    }

    this.score -= 13;

    // set removed cards to null
    this.pyramid.get(row1).set(card1, null);
    this.pyramid.get(row2).set(card2, null);

    // check if the card above is now uncovered
    newExposed(row1, card1);
    newExposed(row2, card2);
  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {

    gameRunning();
    checkCard(row, card);

    if (this.pyramid.get(row).get(card).getCardValue().getValue() != 13) {
      throw new IllegalArgumentException("Card does not add up to 13");
    }
    this.score -= 13;
    this.pyramid.get(row).set(card, null);

    newExposed(row, card);
  }

  /**
   * checks if draw attempt is valid.
   *
   * @param drawIndex index of card from draw pile
   */
  public void checkDrawCard(int drawIndex) {
    if (drawIndex > this.getNumDraw() - 1 || drawIndex < 0) {
      throw new IllegalArgumentException("drawIndex is invalid");
    }

    if (this.drawCards.get(drawIndex) == null) {
      throw new IllegalArgumentException("no card at given index");
    }
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {

    gameRunning();

    checkDrawCard(drawIndex);

    checkCard(row, card);

    if (this.drawCards.get(drawIndex).getCardValue().getValue()
            + this.pyramid.get(row).get(card).getCardValue().getValue()
        != 13) {
      throw new IllegalArgumentException("cards do not add up to 13");
    }

    this.score -= this.pyramid.get(row).get(card).getCardValue().getValue();
    this.pyramid.get(row).set(card, null);

    newExposed(row, card);

    // insert new card into the draw card pile
    if (this.stockCards.size() > 0) {
      this.drawCards.set(drawIndex, this.stockCards.remove(0));
    } else {
      this.drawCards.remove(drawIndex);
    }
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {

    gameRunning();

    checkDrawCard(drawIndex);

    if (this.stockCards.size() > 0) {
      this.drawCards.set(drawIndex, this.stockCards.remove(0));
    } else {
      this.drawCards.remove(drawIndex);
    }
  }

  @Override
  public int getNumRows() {
    return this.numRows;
  }

  @Override
  public int getNumDraw() {
    if (this.numDraw == -1) {
      return this.numDraw;
    }

    return Math.min(this.drawCards.size() + this.stockCards.size(), this.numDraw);
  }

  /**
   * checks if card is null.
   *
   * @param row row of card
   * @param card column of card
   * @return boolean if card is null or not
   */
  protected boolean isNotNull(int row, int card) {
    return this.pyramid.get(row).get(card) != null;
  }

  @Override
  public int getRowWidth(int row) throws IllegalArgumentException, IllegalStateException {

    gameRunning();
    if (row < 0 || row > this.numRows - 1) {
      throw new IllegalArgumentException("Row is invalid");
    }

    return this.pyramid.get(row).size();
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {

    gameRunning();
    if (this.score == 0) {
      return true;
    }

    if (this.drawCards.size() > 0) {
      return false;
    }

    List<Integer> tmp = new ArrayList<>();
    for (int row = 0; row < this.numRows; row++) {
      for (int col = 0; col < this.getRowWidth(row); col++) {
        if (isNotNull(row, col) && !(this.coveredCards.get(row).get(col))) {
          if (this.pyramid.get(row).get(col).getCardValue().getValue() == 13) {
            return false;
          } else {
            tmp.add(this.pyramid.get(row).get(col).getCardValue().getValue());
          }
        }
      }
    }

    for (int ii = 0; ii < tmp.size(); ii++) {
      for (Integer integer : tmp) {
        if (tmp.get(ii) + integer == 13) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (this.score != -1) {
      return score;
    } else {
      throw new IllegalStateException("Game has not started yet");
    }
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalArgumentException, IllegalStateException {

    gameRunning();
    if ((row > (this.numRows - 1)) || (card >= getRowWidth(row)) || card < 0) {
      throw new IllegalArgumentException("Card index or row index out of bounds");
    }
    if (this.pyramid.get(row).get(card) == null) {
      return null;
    }

    return new Card(
        this.pyramid.get(row).get(card).getCardValue(), this.pyramid.get(row).get(card).getSuite());
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    gameRunning();
    List<Card> tmp = new ArrayList<>();
    for (int ii = 0; ii < this.drawCards.size(); ii++) {
      tmp.add(new Card(this.drawCards.get(ii).getCardValue(), this.drawCards.get(ii).getSuite()));
    }
    return tmp;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    int count = 0;
    for (List<Card> row : this.pyramid) {
      out.append("row ").append(count).append(" ").append(row).append("\n");
      count += 1;
    }
    return out.toString();
  }
}
