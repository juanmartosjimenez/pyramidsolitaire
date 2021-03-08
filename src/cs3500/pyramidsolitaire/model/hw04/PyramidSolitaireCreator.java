package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * creator class for pyramid solitaire returns one of basic, relaxed or three pyramid game
 * variation.
 */
public class PyramidSolitaireCreator {
  /** enum function that represents the three different pyramid solitaire variations. */
  public enum GameType {
    BASIC,
    RELAXED,
    MULTIPYRAMID
  }

  /**
   * method returns new object of given pyramid solitaire game mode.
   *
   * @param type type of game mode to be played.
   * @return returns object that corresponds to given type
   */
  public static PyramidSolitaireModel<Card> create(GameType type) {
    switch (type) {
      case BASIC:
        return new BasicPyramidSolitaire();
      case RELAXED:
        return new RelaxedPyramidSolitaire();
      case MULTIPYRAMID:
        return new MultiPyramid();
      default:
        return null;
    }
  }
}
