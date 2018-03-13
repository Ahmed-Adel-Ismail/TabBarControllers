package com.tab.bar.controller;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class TabBuilder<T> {

    @TabIndex
    final int index;
    private final TabBarBuilder<T> tabBarBuilder;

    @DrawableRes
    Integer icon;
    @StringRes
    Integer label;
    T item;
    OnTabClick<T> onClick;


    TabBuilder(TabBarBuilder<T> tabBarBuilder, @TabIndex int index) {
        this.tabBarBuilder = tabBarBuilder;
        this.index = index;
    }

    public TabBuilder<T> withIcon(@DrawableRes int icon) {
        this.icon = icon;
        return this;
    }

    public TabBuilder<T> withLabel(@StringRes int label) {
        this.label = label;
        return this;
    }

    public TabBuilder<T> withOnClick(@NonNull OnTabClick<T> onTabClick) {
        this.onClick = onTabClick;
        return this;
    }

    public TabBuilder<T> withItem(@NonNull T item) {
        this.item = item;
        return this;
    }

    public TabBarBuilder<T> add() {
        if (icon == null || label == null || onClick == null) {
            throw new UnsupportedOperationException("you mus set the icon, label, item, " +
                    "and onTabClick before invoking add()");
        }
        return tabBarBuilder.buildTab(this);
    }

}
