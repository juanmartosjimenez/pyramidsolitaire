package cs3500.pyramidsolitaire.model.hw02;

/** Class Suite used to represent four suites of a card. */
public enum Suite {
  HEARTS,
  CLUBS,
  SPADES,
  DIAMONDS;

  /**
   * gets symbol representation of suite.
   *
   * @return String with symbol of suite
   */
  public String getSymbol() {
    if (this == HEARTS) {
      return String.format("%s", '♥');
    } else if (this == SPADES) {
      return String.format("%s", '♠');
    } else if (this == CLUBS) {
      return String.format("%s", '♣');
    } else if (this == DIAMONDS) {
      return String.format("%s", '♦');
    }
    return null;
  }
}
