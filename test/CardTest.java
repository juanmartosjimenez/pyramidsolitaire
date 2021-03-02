import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardValue;
import cs3500.pyramidsolitaire.model.hw02.Suite;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** tester class for the Card class. */
public class CardTest {

  @Test
  public void suitesTest() {
    Assert.assertEquals("♥", Suite.HEARTS.getSymbol());
    assertEquals("♣", Suite.CLUBS.getSymbol());
    assertEquals("♠", Suite.SPADES.getSymbol());
    assertEquals("♦", Suite.DIAMONDS.getSymbol());
  }

  @Test
  public void valuesTest() {
    int ii = 1;
    for (CardValue val : CardValue.values()) {
      assertEquals(ii, val.getValue());
      ii += 1;
    }
  }

  @Test
  public void toStringTest() {
    Card card1 = new Card(CardValue.Eight, Suite.CLUBS);
    Card card2 = new Card(CardValue.Seven, Suite.SPADES);
    Card card3 = new Card(CardValue.Five, Suite.DIAMONDS);
    Card card4 = new Card(CardValue.Ten, Suite.HEARTS);
    Card card5 = new Card(CardValue.Eleven, Suite.DIAMONDS);
    assertEquals("8♣", card1.toString());
    assertEquals("7♠", card2.toString());
    assertEquals("5♦", card3.toString());
    assertEquals("10♥", card4.toString());
    assertEquals("J♦", card5.toString());
  }

  @Test
  public void hashCodeTest() {
    Card card1 = new Card(CardValue.Eight, Suite.CLUBS);
    Card card2 = new Card(CardValue.Eight, Suite.CLUBS);
    Card card3 = new Card(CardValue.Seven, Suite.CLUBS);
    Card card4 = new Card(CardValue.Eight, Suite.HEARTS);

    assertEquals(true, card1.hashCode() == card2.hashCode());
    assertEquals(false, card1.hashCode() == card3.hashCode());
    assertEquals(true, card3.hashCode() == card3.hashCode());
    assertEquals(false, card2.hashCode() == card3.hashCode());
  }

  @Test
  public void equalsTest() {
    Card card1 = new Card(CardValue.Eight, Suite.CLUBS);
    Card card2 = new Card(CardValue.Eight, Suite.CLUBS);
    Card card3 = new Card(CardValue.Eight, Suite.HEARTS);
    Card card4 = new Card(CardValue.Ten, Suite.HEARTS);
    assertEquals(true, card1.equals(card2));
    assertEquals(false, card1.equals(card3));
    assertEquals(false, card3.equals(card4));
  }
}
