import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.MockPyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import static org.junit.Assert.assertEquals;

/** Tester Class for controller of Pyramid Solitaire. */
public class ControllerTest {
  /**
   * method is used for testing generates a game of the given dimensions and returns the model of
   * the started model.
   *
   * @param row num of rows in pyramid
   * @param draw num of draw cards
   * @return model of started game
   */
  public PyramidSolitaireTextualController makeGame(
      int row, int draw, Readable in, Appendable out) {
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, row, draw);
    return controller;
  }

  @Test(expected = IllegalStateException.class)
  public void testModel1() {
    Readable in = new StringReader("rm1 1 -1");
    Appendable out = new StringBuilder();
    makeGame(4, 4, in, out);
  }

  @Test
  public void testIncorrectMove1() {
    Readable in = new StringReader("-1 rm1 -1 -1 -1 -1  -1 -1 -1 -1 rm2 1 -1 -1 -1 -1 dd 1 q");
    Appendable out = new StringBuilder();
    makeGame(2, 2, in, out);
    assertEquals(
        out.toString(),
        "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 4♥, 5♥\n"
            + "Score: 6\n"
            + "Invalid move. Play again. rm1 -1 -1\n"
            + "Invalid move. Play again. rm2 1 -1 -1 -1\n"
            + "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 6♥, 5♥\n"
            + "Score: 6\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 6♥, 5♥\n"
            + "Score: 6");
  }

  @Test
  public void testIncorrectMove3() {
    Readable in = new StringReader("rmwd 0 -1 -1 Q");
    Appendable out = new StringBuilder();
    makeGame(2, 2, in, out);
    assertEquals(
        out.toString(),
        "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 4♥, 5♥\n"
            + "Score: 6\n"
            + "Invalid move. Play again. rmwd 0 -1 -1\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 4♥, 5♥\n"
            + "Score: 6");
  }

  @Test
  public void testGameOver1() {
    Readable in = new StringReader("rmwd 0 q");
    Appendable out = new StringBuilder();
    makeGame(4, 4, in, out);
    assertEquals(
        out.toString(),
        "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + "7♥  8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣\n"
            + "Score: 55\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + "7♥  8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣\n"
            + "Score: 55");
  }

  @Test
  public void testGameOver2() {
    Readable in = new StringReader("rmwd 9 4 1 q");
    Appendable out = new StringBuilder();
    makeGame(4, 13, in, out);
    assertEquals(
        out.toString(),
        "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + "7♥  8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n"
            + "Score: 55\n"
            + "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + ".   8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣, 2♣, 3♣, 4♣, 5♣, J♣, 7♣, 8♣, 9♣, 10♣\n"
            + "Score: 48\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + ".   8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣, 2♣, 3♣, 4♣, 5♣, J♣, 7♣, 8♣, 9♣, 10♣\n"
            + "Score: 48");
  }

  @Test
  public void testGameEnd() {
    Readable in =
        new StringReader(
            "rmwd 1 9 9 rmwd 1 9 8 rmwd 1 9 7 42 rm2 4 44 4 4 rmwd 1 9 6 rmwd 1 9 5 rmwd 1 9 4"
                + " rm1 9 3 rm2 8 4 8 5 rm2 8 3 8 6 rm1 7 5 dd 1");
    Appendable out = new StringBuilder();
    makeGame(9, 7, in, out);
    assertEquals(
        out.toString(),
        "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  A♦  2♦  3♦  4♦  5♦  6♦\n"
            + "Draw: 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "Score: 294\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  A♦  2♦  3♦  4♦  5♦  .\n"
            + "Draw: 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "Score: 288\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  A♦  2♦  3♦  4♦  .   .\n"
            + "Draw: 9♦, 10♦, J♦, Q♦, K♦\n"
            + "Score: 283\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  A♦  2♦  3♦  .   .   .\n"
            + "Draw: 10♦, J♦, Q♦, K♦\n"
            + "Score: 279\n"
            + "Invalid move. Play again. rm2 4 44 4 4\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  A♦  2♦  .   .   .   .\n"
            + "Draw: J♦, Q♦, K♦\n"
            + "Score: 276\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  A♦  .   .   .   .   .\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 274\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  K♠  .   .   .   .   .   .\n"
            + "Draw: K♦\n"
            + "Score: 273\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠\n"
            + "J♠  Q♠  .   .   .   .   .   .   .\n"
            + "Draw: K♦\n"
            + "Score: 260\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  5♠  .   .   8♠  9♠  10♠\n"
            + "J♠  Q♠  .   .   .   .   .   .   .\n"
            + "Draw: K♦\n"
            + "Score: 247\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  K♣  A♠  2♠\n"
            + "  3♠  4♠  .   .   .   .   9♠  10♠\n"
            + "J♠  Q♠  .   .   .   .   .   .   .\n"
            + "Draw: K♦\n"
            + "Score: 234\n"
            + "                A♥\n"
            + "              2♥  3♥\n"
            + "            4♥  5♥  6♥\n"
            + "          7♥  8♥  9♥  10♥\n"
            + "        J♥  Q♥  K♥  A♣  2♣\n"
            + "      3♣  4♣  5♣  6♣  7♣  8♣\n"
            + "    9♣  10♣ J♣  Q♣  .   A♠  2♠\n"
            + "  3♠  4♠  .   .   .   .   9♠  10♠\n"
            + "J♠  Q♠  .   .   .   .   .   .   .\n"
            + "Draw: K♦\n"
            + "Score: 221\n"
            + "Game over. Score: 221");
  }

  @Test
  public void testInvalidMove() {
    Readable in = new StringReader("rm2 4 1 4 2 q");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = makeGame(4, 4, in, out);
    assertEquals(
        out.toString(),
        "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + "7♥  8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣\n"
            + "Score: 55\n"
            + "Invalid move. Play again. rm2 4 1 4 2\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "      A♥\n"
            + "    2♥  3♥\n"
            + "  4♥  5♥  6♥\n"
            + "7♥  8♥  9♥  10♥\n"
            + "Draw: J♥, Q♥, K♥, A♣\n"
            + "Score: 55");
  }

  @Test
  public void gameWin1() {
    Readable in = new StringReader("rmwd 11 1 1");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = makeGame(1, 14, in, out);
    assertEquals(
        out.toString(),
        "A♥\n"
            + "Draw: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♣, 2♣\n"
            + "Score: 1\n"
            + "You win!");
  }

  @Test
  public void gameWin2() {
    Readable in = new StringReader("rmwd 11  rm1 1 1 q");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = makeGame(1, 14, in, out);
    assertEquals(
        out.toString(),
        "A♥\n"
            + "Draw: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♣, 2♣\n"
            + "Score: 1\n"
            + "You win!");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDeck() {
    Readable in = new StringReader("rmwd 11 1 1 q");
    Appendable out = new StringBuilder();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, null, false, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    Readable in = new StringReader("rmwd 2 2 2 2 q");
    Appendable out = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(null, deck, false, 2, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void testBadRow() {
    Readable in = new StringReader("rmwd 11 1 1 q");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = makeGame(0, 14, in, out);
  }

  @Test(expected = IllegalStateException.class)
  public void testIO() {
    Readable in = new StringReader("");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = makeGame(0, 14, in, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIO2() {
    Readable in = new StringReader("");
    PyramidSolitaireTextualController controller = makeGame(0, 14, in, null);
  }

  @Test
  public void testRender() throws IOException {
    Readable in = new StringReader("rm1 1 1 1 q");
    Appendable out = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 2, 2);
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, out);
    view.render();
    assertEquals(
        "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 4♥, 5♥\n"
            + "Score: 6\n"
            + "Invalid move. Play again. rm1 1 1\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 4♥, 5♥\n"
            + "Score: 6  A♥\n"
            + "2♥  3♥\n"
            + "Draw: 4♥, 5♥",
        out.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderNull() throws IOException {
    Readable in = new StringReader("rm1 1 1 1 q");
    Appendable out = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    BasicPyramidSolitaire model = new BasicPyramidSolitaire();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 2, 2);
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model);
    view.render();
  }

  @Test
  public void testInput() {
    Readable in = new StringReader("rm1 1 1 1 q");
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    MockPyramidSolitaireModel model = new MockPyramidSolitaireModel(log);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 2, 2);
    assertEquals(
        "startGame, shuffle:false, numRows:2, numDraw:2\nrm1, row: 0, card: 0\n", log.toString());
  }

  @Test
  public void testInput2() {
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    MockPyramidSolitaireModel model = new MockPyramidSolitaireModel(log);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, -1, 2);
    assertEquals("startGame, shuffle:false, numRows:-1, numDraw:2\n", log.toString());
  }

  @Test
  public void testInput3() {
    Readable in = new StringReader("rm2 1 2 3 rm1 2 2 q");
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    MockPyramidSolitaireModel model = new MockPyramidSolitaireModel(log);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, -1, 2);
    assertEquals(
        "startGame, shuffle:false, numRows:-1, numDraw:2\nrm2, row1:0, card1:1, row2:2, card2:1\n",
        log.toString());
  }

  @Test
  public void testInput4() {
    Readable in = new StringReader("dd 1 2 3 rm1 2 2 32 34 rmwd 3 3 44 3 q");
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Card> deck = new BasicPyramidSolitaire().getDeck();
    MockPyramidSolitaireModel model = new MockPyramidSolitaireModel(log);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, -1, 2);
    assertEquals(
        "startGame, shuffle:false, numRows:-1, numDraw:2\n"
            + "dd, drawIndex: 0\n"
            + "rm1, row: 1, card: 1\n"
            + "rmwd, drawIndex: 2, row: 2, card: 43\n",
        log.toString());
  }
}
