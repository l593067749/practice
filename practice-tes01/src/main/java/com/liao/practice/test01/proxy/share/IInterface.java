package com.liao.practice.test01.proxy.share;

/**
 * 接口
 */
public interface IInterface {
    /**
     * 执行任务
     * @param taskName 任务名称
     */
    public void dealTask (String taskName);

    /**
     * 停止任务
     * @param taskName
     */
    public void stopTask(String taskName);
}
