package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Pyramid solitaire controller implementation, takes in a Readable in, Appendable out, model and
 * view. Filters and feeds input to the model and then renders that input through the view.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  private final Readable in;
  private final Appendable out;
  private PyramidSolitaireModel<?> model;
  private PyramidSolitaireTextualView view;

  /**
   * Constructor class for the controller.
   *
   * @param rd takes in input via a readable object
   * @param ap returns output via an appendable object
   * @throws IllegalArgumentException whenever one of the passed objects is null
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException();
    }
    this.in = rd;
    this.out = ap;
  }

  /**
   * checks scan input until the input is either q Q or an integer.
   *
   * @param scan takes in scanner
   * @return integer with desired value or throws exception if q or Q
   */
  private int checkVal(Scanner scan) throws IllegalArgumentException {
    String tmp;
    while (scan.hasNext()) {
      tmp = scan.next();
      if (tmp.equals("q") || tmp.equals("Q")) {
        throw new IllegalArgumentException();
      } else {
        try {
          return Integer.parseInt(tmp) - 1;
        } catch (NumberFormatException ignored) {

        }
      }
    }
    throw new IllegalStateException("Game command not finished");
  }

  private void addOut(String message) throws IllegalStateException {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Can't append to out");
    }
  }

  private void updateState() {
    try {
      view.render();
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    if (!(model.isGameOver())) {
      addOut("\nScore: " + this.model.getScore());
    }
  }

  @Override
  public <K> void playGame(
      PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle, int numRows, int numDraw) {
    if (model == null || deck == null) {
      throw new IllegalArgumentException("null pointer in deck or model");
    }

    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Invalid num of rows or draw cards");
    }
    this.model = model;
    this.view = new PyramidSolitaireTextualView(this.model, this.out);
    Scanner scanner = new Scanner(this.in);
    updateState();
    while (scanner.hasNext()) {
      if (model.isGameOver()) {
        return;
      }

      String input = scanner.next();
      int int1;
      int int2;
      int int3;
      int int4;
      switch (input) {
        case "rm1":
          addOut("\n");
          try {
            int1 = checkVal(scanner);
            int2 = checkVal(scanner);
          } catch (IllegalArgumentException e) {
            addOut("Game quit!\nState of game when quit:\n");
            updateState();
            return;
          }
          try {
            model.remove(int1, int2);
            updateState();
          } catch (IllegalArgumentException e) {
            addOut(String.format("Invalid move. Play again. rm1 %d %d", int1 + 1, int2 + 1));
          }

          break;
        case "rm2":
          addOut("\n");
          try {
            int1 = checkVal(scanner);
            int2 = checkVal(scanner);
            int3 = checkVal(scanner);
            int4 = checkVal(scanner);
          } catch (IllegalArgumentException e) {
            addOut("Game quit!\nState of game when quit:\n");
            updateState();
            return;
          }
          try {
            model.remove(int1, int2, int3, int4);
            updateState();
          } catch (IllegalArgumentException e) {
            addOut(
                String.format(
                    "Invalid move. Play again. rm2 %d %d %d %d",
                    int1 + 1, int2 + 1, int3 + 1, int4 + 1));
          }

          break;
        case "rmwd":
          addOut("\n");
          try {
            int1 = checkVal(scanner);
            int2 = checkVal(scanner);
            int3 = checkVal(scanner);
          } catch (IllegalArgumentException e) {
            addOut("Game quit!\nState of game when quit:\n");
            updateState();
            return;
          }
          try {
            model.removeUsingDraw(int1, int2, int3);
            updateState();
          } catch (IllegalArgumentException e) {
            addOut(
                String.format(
                    "Invalid move. Play again. rmwd %d %d %d", int1 + 1, int2 + 1, int3 + 1));
          }

          break;
        case "dd":
          addOut("\n");
          try {
            int1 = checkVal(scanner);
          } catch (IllegalArgumentException e) {
            addOut("Game quit!\nState of game when quit:\n");
            updateState();
            return;
          }
          try {
            model.discardDraw(int1);
            updateState();
          } catch (IllegalArgumentException e) {
            addOut(String.format("Invalid move. Play again. dd %d", int1 + 1));
          }

          break;
        case "q":
        case "Q":
          addOut("\n");
          addOut("Game quit!\nState of game when quit:\n");
          updateState();
          return;
        default:
          break;
      }
    }
    if (!(model.isGameOver())) {
      throw new IllegalStateException("Ran out of readable object before game ended");
    }
  }
}
