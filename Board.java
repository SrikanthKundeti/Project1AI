package IntroToAI;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Math;

public class Board {

    public static int[][]  MakeRandomBoard(int Size) {

        int TotalFeasibleSteps;
        int[][] Board = new int[Size+1][Size+1];
        if(Size == 5 || Size == 7 || Size == 9 || Size == 11 ) {
            for (int i = 1; i <= Size; i++) {
                for (int j = 1; j <= Size; j++) {
                    TotalFeasibleSteps = 1 ;
                    if((Size-i) >= TotalFeasibleSteps) {
                        TotalFeasibleSteps = Size - i;
                    }
                    if((Size-j) >= TotalFeasibleSteps) {
                        TotalFeasibleSteps = Size - j;
                    }
                    if((i-1) >= TotalFeasibleSteps) {
                        TotalFeasibleSteps = i - 1;
                    }
                    if((j-1) >= TotalFeasibleSteps) {
                        TotalFeasibleSteps = j - 1;
                    }
                    Board[i][j] = (int) Math.ceil((TotalFeasibleSteps) * Math.random());
                }
            }
            Board[Size][Size] = 0;

            //Modify Board Accordingly before Printing
           // PrintBoard(Size,Board);
        } else {
            System.out.println("Please Choose Valid Board Size of (5,7,9 or 11)");
        }
        return Board;


    }
    //I'm leaving it private, so I can consider if printing board in this fashion is feasible.
    public static void PrintBoard(int Size,int[][] board) {
        for (int j = 1; j <= Size; j++) {

            System.out.print("\u001B[32m----");
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 1; i <= Size; i++) {
            for (int j = 1; j <= Size; j++) {

                System.out.print("|  |");
                System.out.print(" ");

            }
            System.out.println();
            for (int j = 1; j <= Size; j++) {
                if(board[i][j] < 10) {
                    System.out.print("|" + "\u001B[32m" + board[i][j] + " |");
                } else {
                    System.out.print("|" + "\u001B[32m" + board[i][j] + "|");
                }
                System.out.print(" ");
            }
            System.out.println();

            for (int j = 1; j <= Size; j++) {

                System.out.print("----");
                System.out.print(" ");
            }


            //To Sperate Line-By-Line
            System.out.println(" ");
        }
    }

    public static int BFS(int SizeOfBoard,int[][] board) {
        int[][] Depth = new int[SizeOfBoard + 1][SizeOfBoard + 1];
        int [][] Visited = new int[SizeOfBoard + 1][SizeOfBoard + 1];// Zero for false(not Visited) and One for true (Visited)
        for(int i = 1;i <= SizeOfBoard;i++ ) { //Initializes false (not visited) for all cells.
            for(int j = 1; j <= SizeOfBoard; j++) {
                Visited[i][j] = 0;
                Depth[i][j] = SizeOfBoard*SizeOfBoard;
            }
        }

       Queue<Integer> A = new LinkedList<>(); Queue<Integer> B = new LinkedList<>();
        A.add(1); B.add(1); //Initial Value (i,j).
        Depth[1][1] = 0;
        int x,y; //x for rows and y for columns.

         while (!A.isEmpty() || !B.isEmpty()) {

        x = A.remove(); y = B.remove();
        if (Visited[x][y] == 0) { //Cell NOT Visited.
            Visited[x][y] = 1;
            if (y > board[x][y]) { //Left
                A.add(x);
                B.add((y - board[x][y]));

                if (Depth[x][(y - board[x][y])] >= Depth[x][y] + 1) {
                    Depth[x][(y - board[x][y])] = Depth[x][y] + 1;
                }
            }
            if ((y + board[x][y]) <= SizeOfBoard) { //Right
                A.add(x);
                B.add(y + board[x][y]);

                if (Depth[x][(y + board[x][y])] >= Depth[x][y] + 1) {
                    Depth[x][(y + board[x][y])] = Depth[x][y] + 1;
                }
            }
            if (x > board[x][y]) { //Up
                A.add(x - board[x][y]);
                B.add(y);

                if (Depth[x - board[x][y]][y] >= Depth[x][y] + 1) {
                    Depth[x - board[x][y]][y] = Depth[x][y] + 1;
                }
            }
            if ((x + board[x][y]) <= SizeOfBoard) { //Down
                A.add(x + board[x][y]);
                B.add(y);

                if (Depth[x + board[x][y]][y] >= Depth[x][y] + 1) {
                    Depth[x + board[x][y]][y] = Depth[x][y] + 1;
                }
            }
        } else if (Visited[x][y] == 1) {
            if (y > board[x][y]) { //Left
                if (Depth[x][(y - board[x][y])] >= Depth[x][y] + 1) {
                    Depth[x][(y - board[x][y])] = Depth[x][y] + 1;
                }
            }
            if ((y + board[x][y]) <= SizeOfBoard) { //Right
                if (Depth[x][y + board[x][y]] >= Depth[x][y] + 1) {
                    Depth[x][(y + board[x][y])] = Depth[x][y] + 1;
                }
            }
            if (x > board[x][y]) { //Up
                if (Depth[x - board[x][y]][y] >= Depth[x][y] + 1) {
                    Depth[x - board[x][y]][y] = Depth[x][y] + 1;
                }
            }
            if ((x + board[x][y]) <= SizeOfBoard) { //Down
                if (Depth[x + board[x][y]][y] >= Depth[x][y] + 1) {
                    Depth[x + board[x][y]][y] = Depth[x][y] + 1;
                }
            }

        }
       // if (A.isEmpty() || B.isEmpty()) {
           // break;
        //}
 //   }
}

//        for(int i = 1;i <= SizeOfBoard; i++) {
//            for(int j = 1; j <= SizeOfBoard;j++) {
//                System.out.print(Depth[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println("Visited Elements");
//        for(int i = 1;i <= SizeOfBoard; i++) {
//            for(int j = 1; j <= SizeOfBoard;j++) {
//                System.out.print(Visited[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println();
        System.out.println("Depths");

        for(int i = 1;i <= SizeOfBoard; i++) {
            for(int j = 1; j <= SizeOfBoard;j++) {
                if(Visited[i][j] == 0) {
                    if(!((i == 1) && (j == 1))) {
                        System.out.print("X" + " ");
                    }
                } else if(Visited[i][j] == 1) {
                    System.out.print(Depth[i][j] + " ");
                }
            }
            System.out.println();
        }
        int FailCounter = 0;
        System.out.println();
        //Value Function
        if(Visited[SizeOfBoard][SizeOfBoard] == 0) {
            for(int i = 1;i <= SizeOfBoard; i++) {
                for(int j = 1; j <= SizeOfBoard;j++) {
                    if(Visited[i][j] == 0) {
                        FailCounter++;
                    }
                }
            }
            System.out.println("Evaluation Function = " + (-1)*FailCounter);
            return  (-1)*FailCounter;
        } else { //Be Careful for Rare Case where Visited[x][y] is a value different from 1 or 0.
            System.out.println("Evaluation Function = " + Depth[SizeOfBoard][SizeOfBoard]);
            return Depth[SizeOfBoard][SizeOfBoard];
        }



    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);

            System.out.println("Please Input Board Size (of 5,7,9 or 11): ");
            //Because of n-by-n requirement for the board

            int SizeOfBoard = Integer.parseInt(scanner.nextLine());

            int[][] board = MakeRandomBoard(SizeOfBoard);
            int PrevEvalFunction =  BFS(SizeOfBoard,board);
            PrintBoard(SizeOfBoard,board);
            System.out.println();

        System.out.println("Choose 'H' for Hill Climbing.");
        System.out.println("Choose 'G' for Population-Based Approach");
            while(scanner.hasNext()) {
                System.out.println("Choose 'H' for Hill Climbing.");
                System.out.println("Choose 'G' for Population-Based Approach");
                if ("H".contains(scanner.nextLine())) {
                    System.out.println("For Hill Climbing Algorithm, Choose Number of Iterations: "); //Limit to Iterations is not specified
                    int NumberOfIterations = Integer.parseInt(scanner.nextLine());

                    System.out.println(NumberOfIterations);
                    int[][] NewBoard = HillClimbing.HillClimb(board, NumberOfIterations, PrevEvalFunction);
                    System.out.println("Time for A* Search");
                   A_Star.A_Search(NewBoard);

                } else if("G".contains(scanner.nextLine())) {

                    System.out.println("Implementing Population Based Approach Choose 'M' for Mutation, 'C' for Cross-Over, and 'S' for Selection");

                    while (scanner.hasNext()) {
                        System.out.println("Choose 'M' for Mutation, 'C' for Cross-Over, and 'S' for Selection and 'E' to Exit");
                        if("M".contains(scanner.nextLine())) {

                            board = PopulationApproach.Mutation(board,PrevEvalFunction);

                        }

                        if("S".contains(scanner.nextLine())) {
                            System.out.println("Choose Number of Iterations for Selection");
                            int iterations = Integer.parseInt(scanner.nextLine());
                            board = PopulationApproach.Selection(board,PrevEvalFunction,iterations);

                        }

                        if("C".contains((scanner.nextLine()))) {
                            System.out.println("Choose Number of Iterations for Cross-Over");
                            int iterations = Integer.parseInt(scanner.nextLine());
                            board = PopulationApproach.Cross(board,iterations);

                        }

                        if("E".contains(scanner.nextLine())) {
                            break;
                        }


                        System.out.println("Choose 'M' for Mutation, 'C' for Cross-Over, and 'S' for Selection and 'E' to Exit");


                    }
                }
            }





    //Want to Create a Random Board with Length and Height

    }
}
