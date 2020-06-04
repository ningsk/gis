package com.zydcc.arcgis.utils.func;

/**
 * =======================================
 * 函数对象<br>
 * 接口灵感来自于<a href="http://actframework.org/">ActFramework</a><br>
 * 一个函数接口代表一个一个函数，用于包装一个函数为对象<br>
 * 在JDK8之前，Java的函数并不能作为参数传递，也不能作为返回值存在，此接口用于将一个函数包装成为一个对象，从而传递对象
 *
 * @param <P> 参数类型
 * Create by ningsikai 2020/6/3:8:36 AM
 * ========================================
 */
@FunctionalInterface
public interface VoidFunc<P> {

    /**
     * 执行函数
     *
     * @param parameters 参数列表
     * @throws Exception 自定义异常
     */
    @SuppressWarnings("unchecked")
    void call(P... parameters) throws Exception;
}