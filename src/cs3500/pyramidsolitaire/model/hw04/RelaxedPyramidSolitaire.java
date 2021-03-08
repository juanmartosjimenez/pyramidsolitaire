package cs3500.pyramidsolitaire.model.hw04;

/** class for relaxed pyramid solitaire variation of pyramid solitaire. */
public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaire {

  /** constructor for the relaxed pyramid solitaire just calls constructor for abstract class. */
  public RelaxedPyramidSolitaire() {
    super();
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {

    gameRunning();

    for (int ii = 0; ii < this.numRows; ii++) {
      if (ii != 0) {
        for (int jj = 0; jj < this.getRowWidth(ii); jj++) {
          if (!(this.coveredCards.get(ii).get(jj))) {
            if (jj != ii && isNotNull(ii, jj) && isNotNull(ii - 1, jj) && !isNotNull(ii, jj + 1)) {
              if ((getCardAt(ii, jj).getCardValue().getValue()
                      + getCardAt(ii - 1, jj).getCardValue().getValue())
                  == 13) {
                return false;
              }

            } else if (jj != 0
                && isNotNull(ii, jj)
                && isNotNull(ii - 1, jj - 1)
                && !isNotNull(ii, jj - 1)) {
              if ((getCardAt(ii, jj).getCardValue().getValue()
                      + getCardAt(ii - 1, jj - 1).getCardValue().getValue())
                  == 13) {
                return false;
              }
            }
          }
        }
      }
    }

    return super.isGameOver();
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {

    gameRunning();

    if ((row1 > (this.numRows - 1))
        || (card1 > row1)
        || card1 < 0
        || (row2 > (this.numRows - 1))
        || (card2 > row2)
        || card2 < 0) {
      throw new IllegalArgumentException("Card index or row index out of bounds");
    }

    if (this.pyramid.get(row1).get(card1) == null || this.pyramid.get(row2).get(card2) == null) {
      throw new IllegalArgumentException("Card already removed");
    }

    if (card1 == card2 && row1 == row2) {
      throw new IllegalArgumentException("two of the same cards given");
    }

    if (this.pyramid.get(row1).get(card1).getCardValue().getValue()
            + this.pyramid.get(row2).get(card2).getCardValue().getValue()
        != 13) {
      throw new IllegalArgumentException("Sum of cards does not add up to 13");
    }

    if (this.coveredCards.get(row1).get(card1)) {
      if (this.coveredCards.get(row2).get(card2)) {
        throw new IllegalArgumentException("Card is not yet exposed");
      } else {
        if (row1 == (row2 - 1) && card1 == card2) {
          if (this.coveredCards.get(row2).get(card2)) {
            throw new IllegalArgumentException("Card is covered");
          }

        } else if (row1 == (row2 - 1) && card1 == card2 - 1) {
          if (this.coveredCards.get(row2).get(card2 - 1)) {
            throw new IllegalArgumentException("Card is covered");
          }
        } else {
          throw new IllegalArgumentException("Invalid Cards");
        }
      }
    } else if (this.coveredCards.get(row2).get(card2)) {
      if (row2 == (row1 - 1) && card1 == card2) {
        if (this.coveredCards.get(row1).get(card1)) {
          throw new IllegalArgumentException("Card is covered");
        }

      } else if (row2 == (row1 - 1) && card2 == card1 - 1) {
        if (this.coveredCards.get(row1).get(card1 - 1)) {
          throw new IllegalArgumentException("Card is covered");
        }
      } else {
        throw new IllegalArgumentException("Invalid Cards");
      }
    }

    this.score -= 13;

    // set removed cards to null
    this.pyramid.get(row1).set(card1, null);
    this.pyramid.get(row2).set(card2, null);

    // check if the card above is now uncovered
    newExposed(row1, card1);
    newExposed(row2, card2);
  }
}
