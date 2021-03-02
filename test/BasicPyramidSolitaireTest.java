import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardValue;
import cs3500.pyramidsolitaire.model.hw02.Suite;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/** Tester class for the basicPyramidSolitaire class. */
public class BasicPyramidSolitaireTest {
  /**
   * method is used for testing generates a game of the given dimensions and returns the model of
   * the started model.
   *
   * @param row num of rows in pyramid
   * @param draw num of draw cards
   * @return model of started game
   */
  public BasicPyramidSolitaire makeGame(int row, int draw) {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(deck, false, row, draw);
    return model;
  }

  @Test
  public void testDeck() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    String sampleDeck =
        "[A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣"
            + ", 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♦, 2"
            + "♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦]";
    assertEquals(sampleDeck, deck.toString());
  }

  @Test
  public void testDeckUnchanged() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    String sampleDeck =
        "[A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, "
            + "8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠"
            + ", A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦]";
    new BasicPyramidSolitaire().startGame(deck, true, 4, 4);
    assertEquals(sampleDeck, deck.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkDeckTest() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    deck.remove(12);
    new BasicPyramidSolitaire().startGame(deck, true, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkDeckTest2() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    deck.remove(12);
    deck.add(new Card(CardValue.Ten, Suite.HEARTS));
    new BasicPyramidSolitaire().startGame(deck, true, 4, 4);
  }

  @Test
  public void checkDeckTest3() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    deck.remove(12);
    deck.add(new Card(CardValue.Thirteen, Suite.HEARTS));
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(deck, false, 4, 4);
    assertEquals(55, model.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkNumDraw() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    new BasicPyramidSolitaire().startGame(deck, true, 4, 43);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkNumDraw2() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    new BasicPyramidSolitaire().startGame(deck, true, 4, -40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkNumRow() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    new BasicPyramidSolitaire().startGame(deck, true, 9, 11);
  }

  @Test
  public void testShuffle() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(deck, true, 4, 4);
    assertEquals(4, model.getNumDraw());
  }

  @Test
  public void testMakePyramid() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(deck, false, 4, 4);
    assertEquals(
        "row 0 [A♥]\nrow 1 [2♥, 3♥]\nrow 2 [4♥, 5♥, 6♥]\nrow 3 [7♥, 8♥, 9♥, 10♥]\n",
        model.toString());
  }

  @Test
  public void testGetScore() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.startGame(deck, false, 4, 4);
    assertEquals(55, model.getScore());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetScore2() {
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    model.getScore();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove() {
    BasicPyramidSolitaire model = makeGame(2, 2);
    model.remove(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove2() {
    BasicPyramidSolitaire model = makeGame(2, 2);
    model.remove(1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove3() {
    BasicPyramidSolitaire model = makeGame(2, 2);
    model.remove(2, 3);
  }

  @Test
  public void testRemove4() {
    BasicPyramidSolitaire model = makeGame(5, 5);
    model.remove(4, 1, 4, 3);

    assertEquals(
        "row 0 [A♥]\nrow 1 [2♥, 3♥]\nrow 2 [4♥, 5♥, 6♥]\nrow 3 "
            + "[7♥, 8♥, 9♥, 10♥]\nrow 4 [J♥, null, K♥, null, 2♣]\n",
        model.toString());
  }

  @Test
  public void testRemove5() {
    BasicPyramidSolitaire model = makeGame(5, 1);
    assertEquals(
        "row 0 [A♥]\n"
            + "row 1 [2♥, 3♥]\n"
            + "row 2 [4♥, 5♥, 6♥]\n"
            + "row 3 [7♥, 8♥, 9♥, 10♥]\n"
            + "row 4 [J♥, Q♥, K♥, A♣, 2♣]\n",
        model.toString());
    model.remove(4, 2);
    assertEquals(
        "row 0 [A♥]\n"
            + "row 1 [2♥, 3♥]\n"
            + "row 2 [4♥, 5♥, 6♥]\n"
            + "row 3 [7♥, 8♥, 9♥, 10♥]\n"
            + "row 4 [J♥, Q♥, null, A♣, 2♣]\n",
        model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove6() {
    BasicPyramidSolitaire model = makeGame(4, 2);
    model.remove(2, 3, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDrawPile() {
    BasicPyramidSolitaire model = makeGame(4, 0);
    model.discardDraw(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDrawPile2() {
    BasicPyramidSolitaire model = makeGame(4, 2);
    model.discardDraw(2);
  }

  @Test
  public void testDrawPile3() {
    BasicPyramidSolitaire model = makeGame(4, 2);
    assertEquals("[J♥, Q♥]", model.getDrawCards().toString());
    model.discardDraw(0);
    model.discardDraw(1);
    model.discardDraw(0);
    model.discardDraw(0);
    assertEquals("[3♣, A♣]", model.getDrawCards().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDrawPile4() {
    BasicPyramidSolitaire model = makeGame(1, 51);
    model.discardDraw(50);
    model.discardDraw(50);
  }

  @Test
  public void testDrawPile5() {
    BasicPyramidSolitaire model = makeGame(2, 49);
    model.removeUsingDraw(7, 1, 0);
    model.removeUsingDraw(6, 1, 1);
    assertEquals(47, model.getNumDraw());
  }

  @Test
  public void testDrawPile6() {
    BasicPyramidSolitaire model = makeGame(2, 47);
    model.removeUsingDraw(7, 1, 0);
    model.removeUsingDraw(6, 1, 1);
    assertEquals(47, model.getNumDraw());
  }

  @Test
  public void testDrawRemove() {
    BasicPyramidSolitaire model = makeGame(3, 10);
    model.removeUsingDraw(1, 2, 1);
    model.removeUsingDraw(0, 2, 2);
    assertEquals("[5♣, 4♣, 9♥, 10♥, J♥, Q♥, K♥, A♣, 2♣, 3♣]", model.getDrawCards().toString());
    model.removeUsingDraw(3, 1, 1);
    assertEquals(
        "row 0 [A♥]\n" + "row 1 [2♥, null]\n" + "row 2 [4♥, null, null]\n", model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDrawRemove2() {
    BasicPyramidSolitaire model = makeGame(5, 2);
    model.removeUsingDraw(1, 4, 2);
  }

  @Test
  public void testGetNumRow() {
    BasicPyramidSolitaire model = makeGame(5, 2);
    assertEquals(2, model.getNumDraw());
    assertEquals(5, model.getNumRows());
    BasicPyramidSolitaire model2 = new BasicPyramidSolitaire();
    assertEquals(-1, model2.getNumRows());
    assertEquals(-1, model2.getNumDraw());
  }

  @Test
  public void testGetRowWidth() {
    BasicPyramidSolitaire model = makeGame(5, 2);

    assertEquals(1, model.getRowWidth(0));
    assertEquals(4, model.getRowWidth(3));
    model.remove(4, 4, 4, 0);
    assertEquals(5, model.getRowWidth(4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidth2() {
    BasicPyramidSolitaire model = makeGame(5, 2);
    model.getRowWidth(5);
  }

  @Test
  public void testGetCardAt() {
    BasicPyramidSolitaire model = makeGame(4, 2);
    assertEquals(new Card(CardValue.One, Suite.HEARTS), model.getCardAt(0, 0));
    assertEquals(new Card(CardValue.Six, Suite.HEARTS), model.getCardAt(2, 2));
    assertEquals(new Card(CardValue.Four, Suite.HEARTS), model.getCardAt(2, 0));
    assertEquals(new Card(CardValue.Nine, Suite.HEARTS), model.getCardAt(3, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCard2() {
    BasicPyramidSolitaire model = makeGame(4, 2);
    model.getCardAt(4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCard3() {
    BasicPyramidSolitaire model = makeGame(4, 2);
    model.getCardAt(4, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameIllegalRow() {
    BasicPyramidSolitaire model = makeGame(0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameIllegalRow2() {
    BasicPyramidSolitaire model = makeGame(10, 2);
  }

  @Test
  public void viewEmpty() {
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model);
    assertEquals("", view.toString());
  }

  @Test
  public void testViewToString() {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model);
    model.startGame(deck, false, 3, 2);
    assertEquals("    A♥\n" + "  2♥  3♥\n" + "4♥  5♥  6♥\n" + "Draw: 7♥, 8♥", view.toString());
    model.removeUsingDraw(0, 2, 2);
    assertEquals("    A♥\n" + "  2♥  3♥\n" + "4♥  5♥  .\n" + "Draw: 9♥, 8♥", view.toString());
  }

  @Test
  public void testGetRowWidth3() {
    BasicPyramidSolitaire model = makeGame(1, 13);
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model);
    assertEquals(1, model.getRowWidth(0));
    model.removeUsingDraw(10, 0, 0);
    assertEquals(1, model.getRowWidth(0));
  }

  @Test
  public void testGameEnd() {
    BasicPyramidSolitaire model = makeGame(1, 51);
    assertFalse(model.isGameOver());
    model.removeUsingDraw(10, 0, 0);
    assertTrue(model.isGameOver());

    BasicPyramidSolitaire model2 = makeGame(9, 7);
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model2);
    assertEquals(false, model2.isGameOver());
    model2.removeUsingDraw(0, 8, 8);
    model2.removeUsingDraw(0, 8, 7);
    model2.removeUsingDraw(0, 8, 6);
    model2.removeUsingDraw(0, 8, 5);
    model2.removeUsingDraw(0, 8, 4);
    model2.removeUsingDraw(0, 8, 3);
    model2.remove(8, 2);
    assertEquals(false, model2.isGameOver());
    model2.remove(7, 3, 7, 4);
    model2.remove(7, 2, 7, 5);
    model2.remove(6, 4);
    model2.discardDraw(0);
    assertEquals(true, model2.isGameOver());
  }
}
