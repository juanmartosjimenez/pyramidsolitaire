
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramid;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** class used to test the new game modes for hw4. */
public class Hw4Tests {

  /**
   * method is used for testing generates a game of the given dimensions and returns the model of
   * the started model.
   *
   * @param row num of rows in pyramid
   * @param draw num of draw cards
   * @return model of started game
   */
  public PyramidSolitaireTextualController makeGame(
      PyramidSolitaireModel<Card> model, int row, int draw, Readable in, Appendable out) {
    List<Card> deck = model.getDeck();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, row, draw);
    return controller;
  }

  @Test
  public void testRelaxed() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
    Readable in = new StringReader("rmwd f 2 f 7 f 1 rm2 f 7 f 2 f 6  f 1 f q");
    Appendable out = new StringBuilder();
    makeGame(model, 7, 3, in, out);
    assertEquals(
        "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 4♠, 5♠\n"
            + "Score: 185\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + ".   10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 6♠, 5♠\n"
            + "Score: 176\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  .   4♣  5♣  6♣  7♣  8♣\n"
            + ".   .   J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 6♠, 5♠\n"
            + "Score: 163\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  .   4♣  5♣  6♣  7♣  8♣\n"
            + ".   .   J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 6♠, 5♠\n"
            + "Score: 163",
        out.toString());
  }

  @Test
  public void testThreePyramid() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    makeGame(model, 5, 4, in, out);
    assertEquals(
        "        A♥  .   2♥  .   3♥\n"
            + "      4♥  5♥  6♥  7♥  8♥  9♥\n"
            + "    10♥ J♥  Q♥  K♥  A♣  2♣  3♣\n"
            + "  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣\n"
            + "Q♣  K♣  A♠  2♠  3♠  4♠  5♠  6♠  7♠\n"
            + "Draw: 8♠, 9♠, 10♠, J♠\n"
            + "Score: 210\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        A♥  .   2♥  .   3♥\n"
            + "      4♥  5♥  6♥  7♥  8♥  9♥\n"
            + "    10♥ J♥  Q♥  K♥  A♣  2♣  3♣\n"
            + "  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣\n"
            + "Q♣  K♣  A♠  2♠  3♠  4♠  5♠  6♠  7♠\n"
            + "Draw: 8♠, 9♠, 10♠, J♠\n"
            + "Score: 210",
        out.toString());
  }

  @Test
  public void testThreePyramidGameWin() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in =
        new StringReader(
            "rm2 f 2 f 4 f 2 f 3 rmwd f 1 f 2 f 2 f rmwd 1"
                + " 3 1 rmwd 1 1 3 rmwd 2 2 1 rmwd 2 1 1 dd f 2 rmwd 1 1 2 q");
    Appendable out = new StringBuilder();
    makeGame(model, 2, 2, in, out);
    assertTrue(model.isGameOver());
  }

  @Test
  public void testRelaxedGameWin() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
    Readable in =
        new StringReader(
            "rmwd f 3 f 3 f 1 f rmwd 2 3 2  rmwd 3 2 1 rmwd 1 3 3 rmwd 4 2 2 dd f 3 rmwd 2 1  1 q");
    Appendable out = new StringBuilder();
    makeGame(model, 3, 4, in, out);
    assertTrue(model.isGameOver());
  }

  @Test
  public void testRelaxedRm1() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
    Readable in = new StringReader("rm1 fsd 5 f 3 f rmew rm2 f 5 f 2 f 5 f 4 f q");
    Appendable out = new StringBuilder();
    makeGame(model, 5, 5, in, out);
    assertEquals(
        "        A♥\n"
            + "      2♥  3♥\n"
            + "    4♥  5♥  6♥\n"
            + "  7♥  8♥  9♥  10♥\n"
            + "J♥  Q♥  K♥  A♣  2♣\n"
            + "Draw: 3♣, 4♣, 5♣, 6♣, 7♣\n"
            + "Score: 94\n"
            + "        A♥\n"
            + "      2♥  3♥\n"
            + "    4♥  5♥  6♥\n"
            + "  7♥  8♥  9♥  10♥\n"
            + "J♥  Q♥  .   A♣  2♣\n"
            + "Draw: 3♣, 4♣, 5♣, 6♣, 7♣\n"
            + "Score: 81\n"
            + "        A♥\n"
            + "      2♥  3♥\n"
            + "    4♥  5♥  6♥\n"
            + "  7♥  8♥  9♥  10♥\n"
            + "J♥  .   .   .   2♣\n"
            + "Draw: 3♣, 4♣, 5♣, 6♣, 7♣\n"
            + "Score: 68\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        A♥\n"
            + "      2♥  3♥\n"
            + "    4♥  5♥  6♥\n"
            + "  7♥  8♥  9♥  10♥\n"
            + "J♥  .   .   .   2♣\n"
            + "Draw: 3♣, 4♣, 5♣, 6♣, 7♣\n"
            + "Score: 68",
        out.toString());
  }

  @Test
  public void testGetRowWidth() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
    Readable in = new StringReader("rm1 fsd 5 f 3 f rwm rm2 f 5 f 2 f 5 f 4 f q");
    Appendable out = new StringBuilder();
    makeGame(model, 5, 5, in, out);
    assertEquals(3, model.getRowWidth(2));
    assertEquals(4, model.getRowWidth(3));
    assertEquals(5, model.getRowWidth(4));
    PyramidSolitaireModel<Card> model2 =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in2 = new StringReader("rm1 fsd 5 f 3 f  rmew rm2 f 5 f 2 f 5 f 4 f q");
    Appendable out2 = new StringBuilder();
    makeGame(model2, 5, 5, in2, out2);
    assertEquals(7, model2.getRowWidth(2));
    assertEquals(8, model2.getRowWidth(3));
    assertEquals(9, model2.getRowWidth(4));
  }

  @Test
  public void testGetRowWidth2() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    makeGame(model, 8, 5, in, out);
    assertEquals(9, model.getRowWidth(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testTooManyRowThreePyramid() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    makeGame(model, 9, 5, in, out);
  }

  @Test(expected = IllegalStateException.class)
  public void testTooLittleRowThreePyramid() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    makeGame(model, 0, 5, in, out);
  }

  @Test(expected = IllegalStateException.class)
  public void testTooLittleRowRelax() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    makeGame(model, 0, 5, in, out);
  }

  @Test(expected = IllegalStateException.class)
  public void testTooManyRowRelax() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    makeGame(model, 9, 10, in, out);
  }

  @Test
  public void testBasicPyramidSolitaire() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.BASIC);
    Readable in =
        new StringReader("dd f 5 rmwd f 2 f 7 f 1 f rm2 f 7 f 4 f 7 f 6 f rmw rm1 f 7 f 5 f q");
    Appendable out = new StringBuilder();
    makeGame(model, 7, 5, in, out);
    assertEquals(
        "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 4♠, 5♠, 6♠, 7♠\n"
            + "Score: 185\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 4♠, 5♠, 6♠, 8♠\n"
            + "Score: 185\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + ".   10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "Draw: 3♠, 9♠, 5♠, 6♠, 8♠\n"
            + "Score: 176\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + ".   10♣ J♣  .   K♣  .   2♠\n"
            + "Draw: 3♠, 9♠, 5♠, 6♠, 8♠\n"
            + "Score: 163\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + ".   10♣ J♣  .   .   .   2♠\n"
            + "Draw: 3♠, 9♠, 5♠, 6♠, 8♠\n"
            + "Score: 150\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "            A♥\n"
            + "          2♥  3♥\n"
            + "        4♥  5♥  6♥\n"
            + "      7♥  8♥  9♥  10♥\n"
            + "    J♥  Q♥  K♥  A♣  2♣\n"
            + "  3♣  4♣  5♣  6♣  7♣  8♣\n"
            + ".   10♣ J♣  .   .   .   2♠\n"
            + "Draw: 3♠, 9♠, 5♠, 6♠, 8♠\n"
            + "Score: 150",
        out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testBadDeck() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in =
        new StringReader("dd f 5 rmwd f 2 f 7 f 1 f rm2 f 7 f 4 f 7 f 6 f rmw rm1 f 7 f 5 f q");
    Appendable out = new StringBuilder();
    List<Card> deck = model.getDeck();
    deck.remove(1);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 2, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void testBadDeck2() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in =
        new StringReader("dd f 5 rmwd f 2 f 7 f 1 f rm2 f 7 f 4 f 7 f 6 f rmw rm1 f 7 f 5 f q");
    Appendable out = new StringBuilder();
    List<Card> deck = model.getDeck();
    Card c1 = deck.remove(1);
    deck.remove(1);
    deck.add(c1);
    deck.add(c1);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadDeck3() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in =
        new StringReader("dd f 5 rmwd f 2 f 7 f 1 f rm2 f 7 f 4 f 7 f 6 f rmw rm1 f 7 f 5 f q");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, null, false, 2, 2);
  }

  @Test
  public void testRender() throws IOException {
    Readable in = new StringReader("rm1 1 1 1 q");
    Appendable out = new StringBuilder();
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, model.getDeck(), false, 2, 2);
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, out);
    view.render();
    assertEquals(
        "  A♥  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥\n"
            + "Draw: 8♥, 9♥\n"
            + "Score: 28\n"
            + "Invalid move. Play again. rm1 1 1\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "  A♥  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥\n"
            + "Draw: 8♥, 9♥\n"
            + "Score: 28  A♥  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥\n"
            + "Draw: 8♥, 9♥",
        out.toString());
  }

  @Test
  public void testInvalidMoveRelaxed() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
    Readable in = new StringReader("dd f 6 rmwd 3 3 3 3 rm2 3 4 4 4 rm1 42 3 q");
    Appendable out = new StringBuilder();
    makeGame(model, 5, 5, in, out);
    assertEquals(
        "        A♥\n"
            + "      2♥  3♥\n"
            + "    4♥  5♥  6♥\n"
            + "  7♥  8♥  9♥  10♥\n"
            + "J♥  Q♥  K♥  A♣  2♣\n"
            + "Draw: 3♣, 4♣, 5♣, 6♣, 7♣\n"
            + "Score: 94\n"
            + "Invalid move. Play again. dd 6\n"
            + "Invalid move. Play again. rmwd 3 3 3\n"
            + "Invalid move. Play again. rm2 3 4 4 4\n"
            + "Invalid move. Play again. rm1 42 3\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        A♥\n"
            + "      2♥  3♥\n"
            + "    4♥  5♥  6♥\n"
            + "  7♥  8♥  9♥  10♥\n"
            + "J♥  Q♥  K♥  A♣  2♣\n"
            + "Draw: 3♣, 4♣, 5♣, 6♣, 7♣\n"
            + "Score: 94",
        out.toString());
  }

  @Test
  public void testInvalidMoveThree() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    Readable in = new StringReader("dd f 6 rmwd 3 3 3 3 rm2 3 4 4 4 rm1 42 3 q");
    Appendable out = new StringBuilder();
    makeGame(model, 1, 5, in, out);
    assertEquals(
        "A♥\n"
            + "Draw: 2♥, 3♥, 4♥, 5♥, 6♥\n"
            + "Score: 1\n"
            + "Invalid move. Play again. dd 6\n"
            + "Invalid move. Play again. rmwd 3 3 3\n"
            + "Invalid move. Play again. rm2 3 4 4 4\n"
            + "Invalid move. Play again. rm1 42 3\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "A♥\n"
            + "Draw: 2♥, 3♥, 4♥, 5♥, 6♥\n"
            + "Score: 1",
        out.toString());
  }

  @Test
  public void checkFactory() {
    PyramidSolitaireModel<Card> model =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    assertEquals(model.getClass(), MultiPyramid.class);
    PyramidSolitaireModel<Card> model2 =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
    assertEquals(model2.getClass(), RelaxedPyramidSolitaire.class);
    PyramidSolitaireModel<Card> model3 =
        PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.BASIC);
    assertEquals(model3.getClass(), BasicPyramidSolitaire.class);
  }
}
