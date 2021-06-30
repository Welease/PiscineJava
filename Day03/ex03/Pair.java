public class Pair <T, U>{
    private T first;
    private U second;

    public Pair(T f, U s) {
        first = f;
        second = s;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }

    public void setFirst(T f) { first = f; }
    public void setSecond(U s) { second = s; }
}
