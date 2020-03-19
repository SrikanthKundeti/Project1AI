package IntroToAI;

import java.lang.Math;
import java.util.PrimitiveIterator;

public class HillClimbing {





    public static int[][] HillClimb(int[][]board,int iterations,int PrevEval) {


        int newEval;


        int RandX = (board.length-1);
        int RandY = (board.length-1);

        while(iterations > 0) {


            while ((RandX == board.length-1 && RandY == board.length-1) || RandX > board.length-1 || RandY > board.length-1) {
                //while loop makes sure random number isn't the size of the board so that the goal cell will not be chosen
                RandX = (int) Math.floor(Math.random() * (board.length -1)) + 1;
                RandY = (int) Math.floor(Math.random() * (board.length-1)) + 1;


                //Ceiling Function avoids the possibility of random number being 1
            }


            int TotalFeasibleSteps;
            int prevNum = board[RandX][RandY];
            while (board[RandX][RandY] == prevNum || board[RandX][RandY] < 1 || board[RandX][RandY] > (board.length-1)) {
                TotalFeasibleSteps = 0;
                if ((board.length - 1 - RandX) >= TotalFeasibleSteps) {
                    TotalFeasibleSteps = board.length - 1 - RandX;
                }
                if ((board.length - 1 - RandY) >= TotalFeasibleSteps) {
                    TotalFeasibleSteps = board.length - 1 - RandY;
                }
                if ((RandX - 1) >= TotalFeasibleSteps) {
                    TotalFeasibleSteps = RandX - 1;
                }
                if ((RandY - 1) >= TotalFeasibleSteps) {
                    TotalFeasibleSteps = RandY - 1;
                }

                board[RandX][RandY] = (int) Math.floor((TotalFeasibleSteps) * Math.random()) + 1;

            }
           // System.out.println("Element Modified: (" + RandX + "," + RandY + ")");
          //  Board.PrintBoard(board.length-1,board);
            newEval = Board.BFS(board.length-1, board);

            System.out.println("\u001B[32m Iterations Left: " + iterations);

            if (newEval >= PrevEval) {
                PrevEval = newEval;
                System.out.println("Element Modified: (" + RandX + "," + RandY + ")");
                Board.PrintBoard(board.length-1,board);

            } else {
                board[RandX][RandY] = prevNum;
                iterations++;
            }
            RandX = (board.length-1);
            RandY = (board.length-1);
            iterations--;

         }
        return board;

    }
}
