// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Game;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.O_Tetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a "hard drop". That is, when the tetromino is immediately dropped
 * all the way down as far as it can go.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class DropMove extends AbstractMove {
	@Override
	public boolean isValid(Board board) {
		return true;
	}

	@Override
	protected Board step(Board board) {
		return null;
	}

	@Override
	public Board apply(Board board) {
		boolean otherTetrominoFound = false;
		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Create a copy of this board which will be updated.
		ActiveTetromino tetromino = board.getActiveTetromino();
		Rectangle boundingBox = tetromino.getBoundingBox();
		board.setActiveTetromino(tetromino);
		while (!Game.checkIfAtBottom(tetromino,board)){
			if(!Game.checkForTetrominosBelow(tetromino,board)) {
				tetromino = board.getActiveTetromino().translate(0, -1);
				board.setActiveTetromino(tetromino);
			} else if(Game.checkForTetrominosBelow(tetromino,board)){
				break;
			}
		}
		// Return updated version of this board.
		return board;

	}

	@Override
	public String toString() {
		return "drop";
	}
}
