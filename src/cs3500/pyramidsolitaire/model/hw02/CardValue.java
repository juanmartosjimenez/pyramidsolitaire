package cs3500.pyramidsolitaire.model.hw02;

/** enum class to represent values of cards. */
public enum CardValue {
  One(1),
  Two(2),
  Three(3),
  Four(4),
  Five(5),
  Six(6),
  Seven(7),
  Eight(8),
  Nine(9),
  Ten(10),
  Eleven(11),
  Twelve(12),
  Thirteen(13);
  private final int value;

  CardValue(int value) {
    this.value = value;
  }

  /**
   * gets the numerical value of the card.
   *
   * @return int value of the card
   */
  public int getValue() {
    return this.value;
  }

  /**
   * overrides toString method. Gets string value of card.
   *
   * @return String with numerical value of card
   */
  @Override
  public String toString() {
    if (this.value == 11) {
      return "J";
    } else if (this.value == 12) {
      return "Q";
    } else if (this.value == 13) {
      return "K";
    } else if (this.value == 1) {
      return "A";
    }
    return String.format("%d", this.value);
  }
}
