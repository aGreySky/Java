package cglibProxy;

public class Client {
    public static void main(String[] args) {
        cglibProxy proxy = new cglibProxy();
        Train t = (Train) proxy.getProxy(Train.class);
        t.move();
    }
}
