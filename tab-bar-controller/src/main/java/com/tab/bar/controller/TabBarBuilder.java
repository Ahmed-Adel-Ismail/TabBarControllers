package com.tab.bar.controller;

import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;

import java.util.LinkedHashMap;
import java.util.Map;

public class TabBarBuilder<T> {

    @TabsCount
    final int tabsCount;
    @ColorRes
    int indicatorsColor = android.R.color.holo_red_dark;
    @ColorRes
    int labelsColor = android.R.color.holo_red_dark;
    @LayoutRes
    int tabView = R.layout.view_tab;

    LinkedHashMap<Integer, T> items = new LinkedHashMap<>(5, 1f);
    LinkedHashMap<Integer, Integer> icons = new LinkedHashMap<>(5, 1f);
    LinkedHashMap<Integer, Integer> labels = new LinkedHashMap<>(5, 1f);
    LinkedHashMap<Integer, OnTabClick<T>> onTabClicks = new LinkedHashMap<>(5, 1f);

    private TabBarBuilder(@TabsCount int tabsCount) {
        this.tabsCount = tabsCount;
    }

    public static <T> TabBarBuilder<T> withTabsCount(@TabsCount int tabsCount) {
        return new TabBarBuilder<>(tabsCount);
    }

    public TabBarBuilder<T> withIndicatorsColor(@ColorRes int indicatorsColor) {
        this.indicatorsColor = indicatorsColor;
        return this;
    }

    public TabBarBuilder<T> withLabelsColor(@ColorRes int labelsColor) {
        this.labelsColor = labelsColor;
        return this;
    }

    public TabBuilder<T> withTab(@TabIndex int index) {
        return new TabBuilder<>(this, index);
    }

    TabBarBuilder<T> buildTab(TabBuilder<T> tabBuilder) {
        items.put(tabBuilder.index, tabBuilder.item);
        icons.put(tabBuilder.index, tabBuilder.icon);
        labels.put(tabBuilder.index, tabBuilder.label);
        onTabClicks.put(tabBuilder.index, tabBuilder.onClick);
        return this;
    }

    public void draw(TabBarController tabBarController) {
        tabBarController.build(this);
    }

}
