package cs3500.pyramidsolitaire.model.hw02;

import java.util.List;

/**
 * Mock model class used to test the input sent from the user to controller to the model is correct.
 */
public class MockPyramidSolitaireModel implements PyramidSolitaireModel {
  private StringBuilder log;

  /**
   * Constructor that initializes the log object that keeps track of the input passed to the model.
   *
   * @param log StringBuilder object that keeps track of input
   */
  public MockPyramidSolitaireModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public List getDeck() {
    return null;
  }

  @Override
  public void startGame(List deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {
    log.append(
        String.format(
            "startGame, shuffle:%b, numRows:%d, numDraw:%d\n", shuffle, numRows, numDraw));
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    log.append(
        String.format("rm2, row1:%d, card1:%d, row2:%d, card2:%d\n", row1, card1, row2, card2));
  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {

    log.append(String.format("rm1, row: %d, card: %d\n", row, card));
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("rmwd, drawIndex: %d, row: %d, card: %d\n", drawIndex, row, card));
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("dd, drawIndex: %d\n", drawIndex));
  }

  @Override
  public int getNumRows() {
    // log.append("getNumRows\n");
    return 0;
  }

  @Override
  public int getNumDraw() {
    // log.append("getNumDraw\n");
    return 0;
  }

  @Override
  public int getRowWidth(int row) {
    // log.append("getRowWidth, row: %d\n");
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    // log.append("isGameOver\n");
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    // log.append("getScore\n");
    return 0;
  }

  @Override
  public Object getCardAt(int row, int card) throws IllegalStateException {
    // log.append("getCardAt, row: %d, card: %d\n");
    return null;
  }

  @Override
  public List getDrawCards() throws IllegalStateException {
    // log.append("getCardAt\n");
    return null;
  }
}
