package IntroToAI;

public class EvalObject {

    //Object Made to Use in the Queue for an Evaluated Object that can record number of iterations

    public int X,Y;

    public int gVal;


    public int step;

    public EvalObject(int X, int Y, int gVal,int step) {
        this.X = X;
        this.Y = Y;
        this.gVal = gVal;
        this.step = step;
    }

    public int getgVal() {
        return gVal;
    }

    EvalObject C;




}


