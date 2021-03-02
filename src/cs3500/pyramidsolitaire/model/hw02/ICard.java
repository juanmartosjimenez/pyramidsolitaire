package cs3500.pyramidsolitaire.model.hw02;

/** ICard interface is used to represent a card. */
public interface ICard {
  /**
   * gets the numerical value of the card.
   *
   * @return CardValue object that holds the value of the card
   */
  public CardValue getCardValue();

  /**
   * gets the suite of the card.
   *
   * @return Suit object that holds the suite of the card
   */
  public Suite getSuite();
}
