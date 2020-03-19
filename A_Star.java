package IntroToAI;

import java.util.*;


public class A_Star {

    public static void A_Search(int[][] board) {
        System.out.println(board.length-1);
        int [][] HeuristicMatrix = GetHeuristic(board);
        int [][] Visited = MatrixOfZeros(board.length-1);
        Node head = null;
        Node ptr = head;
        for(int i = 1;i <= board.length - 1; i++) {
            for(int j = 1; j <= board.length - 1;j++) {
                System.out.print(HeuristicMatrix[i][j] + " ");
            }
            System.out.println();
        }
        int Depth;
        final int N = board.length - 1;
        int hVal;
        int CurrentPathLength = 0;
        int ThisPathLength;
        PriorityQueue<EvalObject> PQueue = new PriorityQueue<EvalObject>(N*N, new Comparator<EvalObject>() {
            @Override
            public int compare(EvalObject o1, EvalObject o2) {
                if(o1.gVal < o2.gVal) {
                    return -1;
                } else if(o1.gVal > o2.gVal) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
//        Queue<EvalObject> finalQueue = new Queue<EvalObject>() {
//            @Override
//            public boolean add(EvalObject evalObject) {
//                return true;
//            }
//
//            @Override
//            public boolean offer(EvalObject evalObject) {
//                return false;
//            }
//
//            @Override
//            public EvalObject remove() {
//                return null;
//            }
//
//            @Override
//            public EvalObject poll() {
//                return null;
//            }
//
//            @Override
//            public EvalObject element() {
//                return null;
//            }
//
//            @Override
//            public EvalObject peek() {
//                return null;
//            }
//
//            @Override
//            public int size() {
//                return N*N;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<EvalObject> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] a) {
//                return null;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return true;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends EvalObject> c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//        };
        int StepBacks = 0;
        int steps = 0;
        PQueue.add(new EvalObject(1,1,HeuristicMatrix[1][1],steps));
        EvalObject CurrentEval;
        while(!PQueue.isEmpty()) {
            CurrentEval = PQueue.remove();
            if(CurrentPathLength >= CurrentEval.gVal - HeuristicMatrix[CurrentEval.X][CurrentEval.Y]) {
                StepBacks++;

            }
            CurrentPathLength = CurrentEval.gVal - HeuristicMatrix[CurrentEval.X][CurrentEval.Y];
            //if(Visited[CurrentEval.X][CurrentEval.Y] == 1)  {

            //}
            //finalQueue.add(CurrentEval);
            if(CurrentEval.X == N && CurrentEval.Y == N) { // Reached Goal

              //  break;
            }
                Visited[CurrentEval.X][CurrentEval.Y] = 1;
                ThisPathLength = CurrentPathLength + 1;
                if(head == null) {
                    head = new Node(CurrentEval,null);
                    head.next = null;
                    ptr = head;
                } else {
                    Node newNode = new Node(CurrentEval,null);
                    newNode.next = null;
                    ptr.next = newNode;
                    ptr = ptr.next;


                if(CurrentEval.X + board[CurrentEval.X][CurrentEval.Y] <= N) {
                        hVal = HeuristicMatrix[CurrentEval.X + board[CurrentEval.X][CurrentEval.Y]][CurrentEval.Y];
                        if(Visited[(CurrentEval.X+board[CurrentEval.X][CurrentEval.Y])][CurrentEval.Y] == 0) {
                            PQueue.add(new EvalObject(CurrentEval.X + board[CurrentEval.X][CurrentEval.Y], CurrentEval.Y, ThisPathLength + hVal, CurrentEval.step + 1));
                        }
                }
                if(CurrentEval.Y + board[CurrentEval.X][CurrentEval.Y] <= N) {

                    hVal = HeuristicMatrix[CurrentEval.X ][CurrentEval.Y + board[CurrentEval.X][CurrentEval.Y]];
                    if(Visited[CurrentEval.X][(CurrentEval.Y + board[CurrentEval.X][CurrentEval.Y])] == 0) {
                        PQueue.add(new EvalObject(CurrentEval.X, CurrentEval.Y + board[CurrentEval.X][CurrentEval.Y], ThisPathLength + hVal, CurrentEval.step + 1));
                    }
                }
                if(CurrentEval.X - board[CurrentEval.X][CurrentEval.Y] > 0) {
                    hVal = HeuristicMatrix[CurrentEval.X - board[CurrentEval.X][CurrentEval.Y]][CurrentEval.Y];
                    if(Visited[CurrentEval.X - board[CurrentEval.X][CurrentEval.Y]][CurrentEval.Y] == 0) {
                        PQueue.add(new EvalObject(CurrentEval.X, CurrentEval.Y, ThisPathLength + hVal, CurrentEval.step + 1));
                    }
                }
                if(CurrentEval.Y - board[CurrentEval.X][CurrentEval.Y] > 0) {
                    hVal = HeuristicMatrix[CurrentEval.X ][CurrentEval.Y - board[CurrentEval.X][CurrentEval.Y]];
                    if(Visited[CurrentEval.X][CurrentEval.Y - board[CurrentEval.X][CurrentEval.Y]] == 0)
                    PQueue.add(new EvalObject(CurrentEval.X,CurrentEval.Y,ThisPathLength+hVal,CurrentEval.step+1));
                }
            }

        }

        ptr = head;
        int t = 0;
        while (ptr!=null) {
            if(ptr.next != null) {
                System.out.print("(" + ptr.C.X + "," + ptr.C.Y + ") with stepCount: "+ ptr.C.step +"->");
                t ++;
                if(t >3) {
                    t = 0;
                    System.out.println();
                }
            }
            if(ptr.next == null) {
                System.out.print("(" + ptr.C.X + "," + ptr.C.Y + ") with stepCount: " + ptr.C.step +" ");
            }
            ptr = ptr.next;
        }
    }



    public static int [][] MatrixOfZeros(int Size) {
        int[][] MatrixOfZeros = new int[Size + 1][Size + 1];

        for (int i = 1; i <= Size; i ++) {
            for (int j = 1; j <= Size; j++) {
                MatrixOfZeros[i][j] = 0;
            }
        }

        return MatrixOfZeros;
    }


    public static int[][] GetHeuristic(int[][] board) {

        int [][] HeuristicBoard = new int[board.length][board.length];
        int N = board.length-1;
        for(int i = 1; i<= N;i++) {
            for(int j = 1; j <= N;j++) {
                if( (i < N) && (j < N)) {
                    if(i+board[i][j] != N) {
                        HeuristicBoard[i][j] = 3;
                    }
                    if(j + board[i][j] != N) {
                        HeuristicBoard[i][j] = 3;
                    }
                    if(i + board[i][j] == N) {
                        HeuristicBoard[i][j] = 2;
                    }
                    if(j + board[i][j] == N ) {
                        HeuristicBoard[i][j] = 2;
                    }
                } else {
                    if(i + board[i][j] == N || j + board[i][j] == N) {
                        HeuristicBoard[i][j] = 1;
                    } else {

                        HeuristicBoard[i][j] = 2;
                    }
                }
            }
        }



        HeuristicBoard[N][N] = 0;

        return HeuristicBoard;


    }

}


