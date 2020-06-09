package pieces;

import board.Chessboard;
import management.Player;
import board.Location;

import java.util.HashSet;

/**
 * Class defines bishop's movement
 * 
 * @author Xuefeng Zhu
 *
 */
public class Bishop extends Piece {

	/**
	 * Construct Bishop and define its directions
	 * 
	 * @param location
	 * @param player
	 */
	public Bishop(Location location, Player player) {
		super(location, player);

		this.iconDir = "res/" + this.color + "/bishop.png";
	}

	@Override
	public HashSet<Location> updateAvaiableLocations() {
		HashSet<Location> result = new HashSet<Location>();

		checkFront(result, 1);
		checkFront(result, -1);
		checkSide(result, 1);
		checkSide(result, -1);

		availableLocations = result;
		return result;
	}

	/**
	 * Helper function for pawn' updateAvailable Locations Check if the location
	 * in front of the pawn if available
	 *
	 * @param result
	 * @param stepSize
	 *            how many steps need to move forward
	 */
	private void checkFront(HashSet<Location> result, int stepSize) {
		Chessboard chessboard = Chessboard.getInstance();
		Location targetLocation = new Location(location);

		targetLocation.shiftLocation(stepSize, 0);
		if (chessboard.validateLocation(targetLocation)
				&& chessboard.getPiece(targetLocation) == null) {
			result.add(chessboard.getLocatoin(targetLocation.getRow(),
					targetLocation.getCol()));
		}
	}

	/**
	 * Helper function for pawn' updateAvailable Locations Check if there is
	 * enemy located at the left or right corner of the pawn
	 *
	 * @param result
	 * @param direction
	 */

	private void checkSide(HashSet<Location> result, int direction) {


		Chessboard chessboard = Chessboard.getInstance();
		Location targetLocation = new Location(location);

		targetLocation.shiftLocation(0, direction);
		try {
			Piece targetPiece = chessboard.getPiece(targetLocation);
			if (targetPiece == null ) {
				result.add(chessboard.getLocatoin(targetLocation.getRow(),
						targetLocation.getCol()));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}

	}


}

