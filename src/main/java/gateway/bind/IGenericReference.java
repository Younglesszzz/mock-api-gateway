package gateway.bind;

/**
 * 一次泛化调用：可视为对一个服务其中一个方法的调用
 */
public interface IGenericReference {
    String $invoke(String args);
}
