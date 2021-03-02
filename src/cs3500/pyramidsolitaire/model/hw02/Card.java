package cs3500.pyramidsolitaire.model.hw02;

/**
 * Class that represents a card in a deck with two fields cardValue and Suite to represent the value
 * and suite.
 */
public class Card implements ICard {
  private final CardValue cardValue;
  private final Suite suite;

  /**
   * card constructor.
   *
   * @param cardValue represents value of card
   * @param suite represents suite of card
   */
  public Card(CardValue cardValue, Suite suite) {
    if (suite == null) {
      throw new IllegalArgumentException("Suite can't be null");
    }
    if (cardValue == null) {
      throw new IllegalArgumentException("Value can't be null");
    }
    this.cardValue = cardValue;
    this.suite = suite;

    if (suite != Suite.HEARTS
        && suite != Suite.CLUBS
        && suite != Suite.DIAMONDS
        && suite != Suite.SPADES) {
      throw new IllegalArgumentException("Suite does not exist");
    }
  }

  @Override
  public CardValue getCardValue() {
    return this.cardValue;
  }

  @Override
  public Suite getSuite() {
    return this.suite;
  }

  @Override
  public String toString() {
    return String.format("%s%s", this.cardValue, this.suite.getSymbol());
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Card)) {
      return false;
    }

    Card temp = (Card) that;

    return temp.cardValue == this.cardValue && temp.suite == this.suite;
  }

  @Override
  public int hashCode() {
    return this.cardValue.getValue() + this.suite.toString().hashCode();
  }
}
