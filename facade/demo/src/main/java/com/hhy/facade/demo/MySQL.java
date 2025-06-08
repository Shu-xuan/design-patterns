package com.hhy.facade.demo;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class MySQL implements ServerFacade{
    void initData() {
        System.out.println("初始化MySQL");
    }

    void checkLog() {
        System.out.println("校验日志，恢复可能没有提交的数据");
    }

    void unlock() {
        System.out.println("释放锁");
    }

    void listenPort() {
        System.out.println("监听端口");
    }

    @Override
    public void start() {
        initData();
        checkLog();
        unlock();
        listenPort();
    }
}
