// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
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
		Board tempBoard = new Board(board);
		// Create a copy of this board which will be updated.
		ActiveTetromino tetromino = board.getActiveTetromino();
		Rectangle boundingBox = tetromino.getBoundingBox();
		for(int y = (boundingBox.getMaxY()-boundingBox.getMinY())/2;y<board.getHeight();y = y + boundingBox.getMaxY()-boundingBox.getMinY()){
			ActiveTetromino movedTetromino = tetromino.translate(0, y - ((boundingBox.getMaxY()-boundingBox.getMinY())/2+boundingBox.getMinY()));
			Rectangle tempBoundingBox = movedTetromino.getBoundingBox();
			if(tempBoundingBox.getMinX()>=0&&tempBoundingBox.getMaxX()<board.getWidth()){
				if(tempBoundingBox.getMinY()>=1&&tempBoundingBox.getMaxY()<board.getHeight()) {
					for(int y1 = tempBoundingBox.getMinY();y1<= tempBoundingBox.getMaxY();y1++){
						for(int x = tempBoundingBox.getMinX();x<=tempBoundingBox.getMaxX();x++){
							if( board.getTetrominoAt(x,y1) != board.getActiveTetromino() && board.getTetrominoAt(x,y)!=null){
								if(movedTetromino.isWithin(x,y1)) {
									otherTetrominoFound = true;
								}
							}
						}
					}
				}
			}
		}
		// Apply the move to the new board, rather than to this board.
		tempBoard.setActiveTetromino(tetromino);
		// Return updated version of this board.
		return tempBoard;

	}

	@Override
	public String toString() {
		return "drop";
	}
}
