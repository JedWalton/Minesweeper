package minesweeper;

public class NearMineCell extends Tile {
    public NearMineCell(int distanceFromMine) {
        StringRepresentation = Integer.toString(distanceFromMine);
    }
}
