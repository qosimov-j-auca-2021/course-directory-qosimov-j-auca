import java.util.Scanner;

public class Problem01 {
    static int turtleX, turtleY;
    static int turtleDX, turtleDY;
    static boolean turtlePenDown;
    static final char EMPTY_CELL = '.';
    static final char MARKED_CELL = '*';
    static final char TURTLE_CELL = 'T';
    static final int FIELD_WIDTH = 20;
    static final int FIELD_HEIGHT = 20;
    static char[][] field;

    static void constructTurtle() {
        turtleX = turtleY = 0;
        turtleDX = 1;
        turtleDY = 0;
        turtlePenDown = false;
    }

    static void turtlePutPenUp() {
        turtlePenDown = false;
    }

    static void turtlePutPenDown() {
        turtlePenDown = true;
    }

    static void turtleTurnRight() {
        int temp = turtleDX;
        turtleDX = -turtleDY;
        turtleDY = temp;
    }

    static void turtleTurnLeft() {
        int temp = turtleDX;
        turtleDX = turtleDY;
        turtleDY = -temp;
    }

    static void turtleMove(int steps) {
        for (int i = 0; i < steps; ++i) {
            int nextX = turtleX + turtleDX;
            int nextY = turtleY + turtleDY;
            if (!areCoordinatesInsideField(nextX, nextY)) {
                break;
            }

            if (turtlePenDown) {
                markField(turtleX, turtleY);
            }

            turtleX = nextX;
            turtleY = nextY;
        }
    }
    static void constructField() {
        field = new char[FIELD_HEIGHT][FIELD_WIDTH];
        for (int y = 0; y < FIELD_HEIGHT; ++y) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                field[y][x] = EMPTY_CELL;
            }
        }
    }

    static void displayField() {
        for (int y = 0; y < FIELD_HEIGHT; ++y) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                boolean isTurtle = (x == turtleX && y == turtleY);
                System.out.print(isTurtle ? TURTLE_CELL : field[y][x]);
            }
            System.out.println();
        }
    }

    static boolean areCoordinatesInsideField(int x, int y) {
        return x >= 0 && x < FIELD_WIDTH && y >= 0
                && y < FIELD_HEIGHT;
    }

    static void markField(int x, int y) {
        if (areCoordinatesInsideField(x, y)) {
            field[y][x] = MARKED_CELL;
        }
    }

    public static void main(String[] args) {
        constructField();
        constructTurtle();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (!(parts.length > 0 && parts[0].trim().length() > 0)) {
                System.err.println("No instructions were given to carry out. Kindly, attempt once more.");
            }
            String command = parts[0].trim();
            switch (command) {
                case "pen-up":
                    turtlePutPenUp();
                    break;
                case "pen-down":
                    turtlePutPenDown();
                    break;
                case "turn-right":
                    turtleTurnRight();
                    break;
                case "turn-left":
                    turtleTurnLeft();
                    break;
                case "move":
                    if (parts[1].trim().length() == 0) {
                        System.err.println("The quantity of steps was not supplied. Please, make another attempt.");
                    }
                    if (parts.length > 2) {
                        System.err.println("Excessive arguments were given for the 'move' command. Please, try again.");
                    }

                    int steps = Integer.parseInt(parts[1]);
                    if (steps < 0) {
                        System.err.println("The step count cannot be negative. Please, attempt again.");
                    }
                    turtleMove(steps);
                    break;
                case "display":
                    displayField();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.err.println("Unrecognized command. Please, try again.");
            }
 }
}
}