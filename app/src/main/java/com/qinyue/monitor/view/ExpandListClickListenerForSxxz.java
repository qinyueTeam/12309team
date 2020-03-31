package com.qinyue.monitor.view;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public interface ExpandListClickListenerForSxxz {
    /**
     * 展开子Item
     *
     * @param entity
     */
    void onExpandChildren(ParentEntity entity);

    /**
     * 隐藏子Item
     *
     * @param entity
     */
    void onHideChildren(ParentEntity entity);

}
