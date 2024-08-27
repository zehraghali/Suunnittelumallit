package Factory_method;

class Game {
    public static Map createMap(String mapType, int width, int height) {
        switch (mapType.toLowerCase()) {
            case "city":
                return new CityMap(width, height);
            case "wilderness":
                return new WildernessMap(width, height);
            default:
                throw new IllegalArgumentException("Unknown map type");
        }
    }

    public static void main(String[] args) {
        // Example of creating a city map
        Map cityMap = createMap("city", 5, 5);
        System.out.println("City Map:");
        cityMap.display();

        // Example of creating a wilderness map
        Map wildernessMap = createMap("wilderness", 5, 5);
        System.out.println("\nWilderness Map:");
        wildernessMap.display();
    }
}
