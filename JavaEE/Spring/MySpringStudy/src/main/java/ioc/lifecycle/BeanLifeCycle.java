package ioc.lifecycle;

public class BeanLifeCycle {

    // @Override
    // public void destroy() throws Exception {
    // System.out.println("Bean destroy");
    //
    // }
    //
    // @Override
    // public void afterPropertiesSet() throws Exception {
    // System.out.println("Bean afterPropertiesSet");
    //
    // }

    public void defautInit() {
        System.out.println("Bean defautInit.");
    }

    public void defaultDestroy() {
        System.out.println("Bean defaultDestroy.");
    }

    // @Override
    // public void destroy() throws Exception {
    // System.out.println("Bean destroy.");
    // }
    //
    // @Override
    // public void afterPropertiesSet() throws Exception {
    // System.out.println("Bean afterPropertiesSet.");
    // }

    // public void start() {
    // System.out.println("Bean start .");
    // }
    //
    // public void stop() {
    // System.out.println("Bean stop.");
    // }

}
