package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import java.io.IOException;
import java.util.List;

/** Class for displaying information to the user. */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  private Appendable out;
  // ... any other fields you need

  /**
   * constructor for view.
   *
   * @param model takes in model
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  /**
   * constructor for view.
   *
   * @param model model
   * @param out appendable object
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  /**
   * function creates a string with the given n whitespaces.
   *
   * @return string of whitespaces
   */
  private String createWhiteSpace(int whitespaces) {

    return " ".repeat(Math.max(0, whitespaces));
  }

  @Override
  public String toString() {
    try {
      model.getScore();
    } catch (Exception e) {
      return "";
    }
    if (model.getScore() == 0) {
      return "You win!";
    } else if (model.isGameOver()) {
      return String.format("Game over. Score: %d", model.getScore());
    } else {
      int rows = model.getNumRows();
      StringBuilder out = new StringBuilder();
      for (int ii = 0; ii < rows; ii++) {
        out.append(createWhiteSpace((rows - ii - 1) * 2));
        for (int jj = 0; jj < ii; jj++) {
          if (model.getCardAt(ii, jj) == null) {
            out.append(".   ");
          } else if (model.getCardAt(ii, jj).toString().length() == 2) {
            out.append(model.getCardAt(ii, jj).toString()).append("  ");
          } else {
            out.append(model.getCardAt(ii, jj).toString()).append(" ");
          }
        }
        if (model.getCardAt(ii, ii) == null) {
          out.append(".\n");
        } else {
          out.append(model.getCardAt(ii, ii).toString()).append("\n");
        }
      }

      // draw cards
      out.append("Draw: ");
      List<?> drawCards = model.getDrawCards();
      if (drawCards.size() >= 1) {
        out.append(drawCards.get(0).toString());
      }
      for (int ii = 1; ii < drawCards.size(); ii++) {
        out.append(", ").append(drawCards.get(ii).toString());
      }
      return out.toString();
    }
  }

  @Override
  public void render() throws IOException {
    if (out != null) {
      this.out.append(this.toString());
    } else {
      throw new IOException("out is null");
    }
  }
}
