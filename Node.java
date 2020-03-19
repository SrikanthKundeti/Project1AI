package IntroToAI;

public class Node {
    EvalObject C;
    Node next;
    public Node(EvalObject C,Node next) {
        this.C = C;
        this.next = next;

    }
}