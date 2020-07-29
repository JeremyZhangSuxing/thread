
Cglib和jdk动态代理的区别？
1、Jdk动态代理：利用拦截器（必须实现InvocationHandler）加上反射机制生成一个代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理
2、 Cglib动态代理：利用ASM框架，对代理对象类生成的class文件加载进来，通过修改其字节码生成子类来处理
什么时候用cglib什么时候用jdk动态代理？
1、目标对象生成了接口 默认用JDK动态代理
2、如果目标对象使用了接口，可以强制使用cglib
3、如果目标对象没有实现接口，必须采用cglib库，Spring会自动在JDK动态代理和cglib之间转换


      
    