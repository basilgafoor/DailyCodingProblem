/**
 * Given a N by M matrix of numbers, print out the matrix in a clockwise spiral.
 *
 * For example, given the following matrix:
 *
 * <pre>
 * [[1,  2,  3,  4,  5],
 *  [6,  7,  8,  9,  10],
 *  [11, 12, 13, 14, 15],
 *  [16, 17, 18, 19, 20]]
 *  </pre>
 * You should print out the following:
 * <pre>
 * 1
 * 2
 * 3
 * 4
 * 5
 * 10
 * 15
 * 20
 * 19
 * 18
 * 17
 * 16
 * 11
 * 6
 * 7
 * 8
 * 9
 * 14
 * 13
 * 12
 * </pre>
 *
 * @author Oleg Cherednik
 * @since 31.10.2018
 */
public class Problem65 {

    public static void main(String... args) {
        int[][] matrix = {
                { 1, 2, 3, 4, 5 },
                { 6, 7, 8, 9, 10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 } };
        printClockwiseSpiral(matrix);
    }

    public static void printClockwiseSpiral(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;

        int x = 0;
        int y = 0;
        Direction direction = Direction.RIGHT;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        while (direction != Direction.NONE) {
            System.out.println(matrix[y][x]);
            visited[y][x] = true;

            if (!direction.isNextMoveAvailable(x, y, visited))
                direction = direction.next().isNextMoveAvailable(x, y, visited) ? direction.next() : Direction.NONE;

            x = direction.moveX(x);
            y = direction.moveY(y);
        }

    }

    private enum Direction {
        NONE,
        RIGHT {
            public boolean isNextMoveAvailable(int x, int y, boolean[][] visited) {
                return x + 1 < visited[y].length && !visited[y][x + 1];
            }

            public int moveX(int x) {
                return ++x;
            }

        },
        DOWN {
            public boolean isNextMoveAvailable(int x, int y, boolean[][] visited) {
                return y + 1 < visited.length && !visited[y + 1][x];
            }

            public int moveY(int y) {
                return ++y;
            }
        },
        LEFT {
            public boolean isNextMoveAvailable(int x, int y, boolean[][] visited) {
                return x - 1 >= 0 && !visited[y][x - 1];
            }

            public int moveX(int x) {
                return --x;
            }
        },
        UP {
            public boolean isNextMoveAvailable(int x, int y, boolean[][] visited) {
                return y - 1 >= 0 && !visited[y - 1][x];
            }

            public int moveY(int y) {
                return --y;
            }
        };

        public Direction next() {
            if (this == RIGHT)
                return DOWN;
            if (this == DOWN)
                return LEFT;
            if (this == LEFT)
                return UP;
            return RIGHT;
        }

        public boolean isNextMoveAvailable(int x, int y, boolean[][] visited) {
            return false;
        }

        public int moveX(int x) {
            return x;
        }

        public int moveY(int y) {
            return y;
        }

    }

}
