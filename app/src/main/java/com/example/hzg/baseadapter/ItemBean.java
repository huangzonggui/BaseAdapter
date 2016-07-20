package com.example.hzg.baseadapter;

/**
 * Created by hzg on 2016/7/20.
 * 创建bean对象可以用来封装对象
 */
public class ItemBean {
    public int itemImageResId;
    public String itemTitle;
    public String itemContent;

    public ItemBean(int itemImageResId, String itemTitle, String itemContent) {
        this.itemImageResId = itemImageResId;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }
}
