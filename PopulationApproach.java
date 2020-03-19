package IntroToAI;

public class PopulationApproach {

    public static int[][] Mutation(int[][] board, int previousEvaluation) {
        System.out.println(board.length-1); //board.length - 1 is N.
        int N = board.length - 1;
        int iterations = 5; //Over-Mutation isn't possible in this case.
        int i = 1;
        int prevVal;
        while(iterations > 0) {

            if(i < N) {
                if(i + board[i][N] == N) {
                    prevVal = board[i][N];
                    board[i][N] = (int)Math.floor(Math.random()*N) + 1;
                    if(previousEvaluation >= Board.BFS(N,board)) {
                               board[i][N] = prevVal;
                    } else {
                        Board.PrintBoard(N,board);
                    }
                }

                if(i + board[N][i] == N) {
                    prevVal = board[N][i];
                    board[N][i] = (int) Math.floor(N*Math.random()) + 1;
                    if(prevVal >= Board.BFS(N,board)) {
                        board[N][i] = prevVal;
                    } else {
                        Board.PrintBoard(N,board);
                    }
                }
            }
            i++;
            iterations--;

        }

        System.out.println("New Board Evaluation: " + Board.BFS(N,board));

        return  board;
    }

    public static int[][] Selection(int[][] board,int PrevEvaluation,int iterations) {
        int N = board.length-1;
        int X = N; int Y = N;
        while(iterations > 0) {
            while (X == N && Y == N) {
                X = (int) Math.floor(Math.random() * N) + 1;
                Y = (int) Math.floor(Math.random() * N) + 1;

            }

            int MaxVal = 1;
            if (X + board[X][Y] <= N) {
                if (MaxVal < N - board[X][Y]) {
                    MaxVal = N - board[X][Y];
                }
            }
            if (Y + board[X][Y] <= N) {
                if (MaxVal < N - board[X][Y]) {
                    MaxVal = N - board[X][Y];
                }
            }
            if (X - board[X][Y] > 0) {
                if (MaxVal < board[X][Y] - 1) {
                    MaxVal = board[X][Y] - 1;
                }
            }
            if (Y - board[X][Y] > 0) {
                if (MaxVal < board[X][Y] - 1) {
                    MaxVal = board[X][Y] - 1;
                }
            }

            int BestEval = Board.BFS(N, board);
            int prevValue = board[X][Y];
            int K;
            for (int i = 1; i <= MaxVal; i++) {
                board[X][Y] = i;
                K = Board.BFS(N, board);
                if (BestEval < K) {
                    BestEval = K;
                    board[X][Y] = i;
                    Board.PrintBoard(N,board);
                }
            }
            iterations--;
        }


        return board;
    }

    public static int[][] Cross(int [][] board, int iterations) {
        System.out.println("INITIAL BOARD");


        int N = board.length-1;
        Board.PrintBoard(N,board);
        int PrevEvaluation = Board.BFS(N,board);
        int [][] board1 = Selection(board,PrevEvaluation,10);
        int [][] board2 = Selection(board,PrevEvaluation,10);
        int prevValue;
        int EV1 = Board.BFS(N,board1);
        int EV2 = Board.BFS(N,board2);
        int NewEval;
        while(iterations > 0) {
            if (EV1 > PrevEvaluation && EV1 > EV2) {
                for(int i = 1; i <= N;i++) {
                    for(int j = 1; j <= N;j++) {
                        prevValue = board[i][j];
                        board[i][j] = board1[i][j];
                        NewEval = Board.BFS(N,board);
                        if(NewEval <= PrevEvaluation ) {
                            board[i][j] = prevValue;
                        } else {
                            PrevEvaluation = NewEval;
                        }
                    }
                }
            } else if(EV2 > PrevEvaluation && EV2 > EV1) {
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= N; j++) {
                        prevValue = board[i][j];
                        board[i][j] = board2[i][j];
                        NewEval = Board.BFS(N,board);
                        if(NewEval <= PrevEvaluation ) {
                            board[i][j] = prevValue;
                        } else {
                            PrevEvaluation = NewEval;
                        }
                    }
                }
            }
        iterations--;
        }

        return board;
    }


}
