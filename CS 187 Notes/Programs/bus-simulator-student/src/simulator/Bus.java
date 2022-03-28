package simulator;

public class Bus {
	public final int number;
	private final RoadMap roadMap;
	private int x;
	private int y;
	boolean move;
	String direction;

	public Bus(int number, RoadMap roadMap, int x, int y) {
		this.number = number;
		this.roadMap = roadMap;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * 
	 * (north/south/east/west), not diagonally.
	 * 
	 * If the bus is stopped (that is, if it was just placed, or if it didn't
	 * move last time move() was called), then it should attempt to move north.
	 * If it cannot (no road, or off the map), then it should attempt south,
	 * then east, then west. If no move is available, it should stay in its
	 * current position.
	 * 
	 * If the bus is moving (that is, if it successfully moved the last time
	 * move() was called), then it should attempt to continue moving in the same
	 * direction.
	 * 
	 * If it cannot (no road, or off the map), then it should attempt to turn
	 * right. For example, if the bus was moving north, but there is no more
	 * road to the north, it should move east if possible.
	 * 
	 * If it cannot turn right, it should turn left. If it cannot turn left, it
	 * should reverse direction (that is, move backward, if possible). 
	 * If it cannot do any of these things, it should stay in its current position.
	 */
	
	boolean isNorth() {
		if( (roadMap.isRoad(getX(), getY()-1) && (getY() > 0))) {
			return true;
		}
		return false;
	}
	
	boolean isSouth() {
		if((roadMap.isRoad(getX(), getY()+1) && (getY() < roadMap.ySize-1))) {
			return true;
		}
		return false;
	} 
	
	boolean isEast() {
		if(roadMap.isRoad(getX()+1, getY()) && (getX() < roadMap.xSize-1)) {
			return true;
		}
		return false;
	}
	
	boolean isWest() {
		if((getX() > 0) && (roadMap.isRoad(getX()-1, getY()))) {
			return true;
		}
		return false;
	}
	
	public void move() {
		
		
		if(!move) {
			if(isNorth()) {
				y--;
				direction = "north";
				move = true;
			} 
			else if(isSouth()) {
				y++;
				direction = "south";
				move = true;
			} 
			else if(isEast()) {
				x++;
				direction = "east";
				move = true;
			}
			else if(isWest()) {
				x--;
				direction = "north";
				move = true;
			} 
			else{
				return;
			}
			return;
		}
	
		
		if(move) {
			if(direction == "north") {
				if(isNorth()) {
					y--;
					direction = "north";
				} 
				else if(isSouth()) {
					y++;
					direction = "south";
				}
				else if(isEast()) {
					x++;
					direction = "east";
				}
				else if(isWest()) {
					x--;
					direction = "west";
				}
				return;
			} 
			else if(direction == "south") {
				if(isNorth()) {
					y--;
					direction = "north";
				} 
				else if(isSouth()) {
					y++;
					direction = "south";
				}
				else if(isEast()) {
					x++;
					direction = "east";
				}
				else if(isWest()) {
					x--;
					direction = "west";
				}
				return;
				
			}
			else if(direction == "east") {
				if(isNorth()) {
					y--;
					direction = "north";
				} 
				else if(isSouth()) {
					y++;
					direction = "south";
				}
				else if(isEast()) {
					x++;
					direction = "east";
				}
				else if(isWest()) {
					x--;
					direction = "west";
				}
				return;
			}
			else if(direction == "west") {
				if(isNorth()) {
					y--;
					direction = "north";
				} 
				else if(isSouth()) {
					y++;
					direction = "south";
				}
				else if(isEast()) {
					x++;
					direction = "east";
				}
				else if(isWest()) {
					x--;
					direction = "west";
				}
				return;
			}
		}
		
	}
		
}
