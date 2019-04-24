// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Provides some mechanisms which are common across all moves.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public abstract class AbstractMove implements Move {

	@Override
	public boolean isValid(Board board) {
		Board tempBoard = step(board);
		Rectangle boundingBox = tempBoard.getActiveTetromino().getBoundingBox();
		ActiveTetromino activeTetromino = tempBoard.getActiveTetromino();
		if(boundingBox.getMinX()>=0&&boundingBox.getMaxX()<board.getWidth()){
			if(boundingBox.getMinY()>=1&&boundingBox.getMaxY()<board.getHeight()) {
				for(int y = boundingBox.getMinY();y<= boundingBox.getMaxY();y++){
					for(int x = boundingBox.getMinX();x<=boundingBox.getMaxX();x++){
						if( board.getTetrominoAt(x,y) != board.getActiveTetromino() && board.getTetrominoAt(x,y)!=null){
							if(activeTetromino.isWithin(x,y)) {
								return false;
							}
						}
					}
				}
				return true;
			} else{
				return false;
			}
		}
		return false;
	}

	/**
	 * Apply a single step of this move to a given board, producing an updated
	 * board. This allows us to easily check every intermediate step for a
	 * multi-part move is valid.
	 *
	 * @param board
	 */
	protected abstract Board step(Board board);

}
