package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var grid = new Grid();

        grid.printGrid();
        grid.gameMoves();
    }

    static class Grid {
        private final String[][] gameGrid;

        Grid() {
            this.gameGrid = new String[3][3];
            for (int i = 0; i < gameGrid.length; i++) {
                for (int j = 0; j < gameGrid[i].length; j++) {
                    gameGrid[i][j] = "_";
                }
            }
        }

        private void printGrid() {
            System.out.println("---------");
            for (int i = 0; i < gameGrid.length; i++) {
                System.out.print("|");
                for (int j = 0; j < gameGrid[i].length; j++) {
                    System.out.print(" " + gameGrid[i][j]);
                }
                System.out.print(" " + "|");
                System.out.println();
            }
            System.out.println("---------");
        }

        private void gameMoves() {
            var scanner = new Scanner(System.in);

            int turn = 1;
            int xPlayer = 0;
            int oPlayer = 0;

            while (true) {
                try {
                    int x = scanner.nextInt() - 1;
                    int y = scanner.nextInt() - 1;

                    if (gameGrid[x][y].equals("X") || gameGrid[x][y].equals("O")) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        if (turn % 2 == 0) {
                            gameGrid[x][y] = "O";
                            oPlayer++;
                        } else {
                            gameGrid[x][y] = "X";
                            xPlayer++;
                        }
                        printGrid();
                        turn++;
                        if (winsXorO()) break;
                        else if (xPlayer + oPlayer == 9) {
                            System.out.println("Draw");
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            scanner.close();
        }

        private boolean winsXorO() {
            String[] row1 = new String[]{gameGrid[0][0], gameGrid[0][1], gameGrid[0][2]};
            String[] row2 = new String[]{gameGrid[1][0], gameGrid[1][1], gameGrid[1][2]};
            String[] row3 = new String[]{gameGrid[2][0], gameGrid[2][1], gameGrid[2][2]};

            boolean rowX = Arrays.stream(row1).allMatch(s -> s.equals("X")) 
                    || Arrays.stream(row2).allMatch(s -> s.equals("X")) 
                    || Arrays.stream(row3).allMatch(s -> s.equals("X"));

            boolean rowO = row[1].equals("O") 
                    && (isWin(row1[0], row1[1], row1[2]) 
                        || isWin(row2[0], row2[1], row2[2]) 
                        || isWin(row3[0], row3[1], row3[2])
                    );

            String[] column1 = new String[]{gameGrid[0][0], gameGrid[1][0], gameGrid[2][0]};
            String[] column2 = new String[]{gameGrid[0][1], gameGrid[1][1], gameGrid[2][1]};
            String[] column3 = new String[]{gameGrid[0][2], gameGrid[1][2], gameGrid[2][2]};

            boolean columnX = Arrays.stream(column1)
                    .allMatch(s -> s.equals("X")) || Arrays.stream(column2)
                    .allMatch(s -> s.equals("X")) || Arrays.stream(column3)
                    .allMatch(s -> s.equals("X"));

            boolean columnO = Arrays.stream(column1)
                    .allMatch(s -> s.equals("O")) || Arrays.stream(column2)
                    .allMatch(s -> s.equals("O")) || Arrays.stream(column3)
                    .allMatch(s -> s.equals("O"));

            String[] diagonal1 = new String[]{gameGrid[0][0], gameGrid[1][1], gameGrid[2][2]};
            String[] diagonal2 = new String[]{gameGrid[0][2], gameGrid[1][1], gameGrid[2][0]};

            boolean diagonalX = Arrays.stream(diagonal1)
                    .allMatch(s -> s.equals("X")) || Arrays.stream(diagonal2)
                    .allMatch(s -> s.equals("X"));

            boolean diagonalO = Arrays.stream(diagonal1)
                    .allMatch(s -> s.equals("O")) || Arrays.stream(diagonal2)
                    .allMatch(s -> s.equals("O"));

            if (rowX || columnX || diagonalX) {
                System.out.println("X wins");
                return true;
            } else if (rowO || columnO || diagonalO) {
                System.out.println("O wins");
                return true;
            }
            return false;
        }

        private boolean isWin(String x, String y, String z) {
            return x.equals(y) && y.equals(z);
        }
    }
}