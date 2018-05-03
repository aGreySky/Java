package ioc.beanannotation.javabased;

public class IntegerStore implements Store<Integer> {
    public void init() {
        System.out.println("This is init.");
    }

    public void destroy() {
        System.out.println("This is destroy.");
    }
}
