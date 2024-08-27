package Factory_method;

import java.util.Random;

class CityMap extends Map {
    public CityMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected Tile createTile() {
        Random rand = new Random();
        int tileType = rand.nextInt(3); // Randomly select between 0, 1, 2

        switch (tileType) {
            case 0: return new RoadTile();
            case 1: return new ForestTile();
            case 2: return new BuildingTile();
            default: return null; // This should never happen
        }
    }
}

class WildernessMap extends Map {
    public WildernessMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected Tile createTile() {
        Random rand = new Random();
        int tileType = rand.nextInt(3); // Randomly select between 0, 1, 2

        switch (tileType) {
            case 0: return new SwampTile();
            case 1: return new WaterTile();
            case 2: return new ForestTile();
            default: return null; // This should never happen
        }
    }
}
