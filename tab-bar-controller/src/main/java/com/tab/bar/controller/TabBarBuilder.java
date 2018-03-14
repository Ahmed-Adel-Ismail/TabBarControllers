package com.tab.bar.controller;

import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import java.util.LinkedHashMap;

public class TabBarBuilder<T> {

    @TabsCount
    final int tabsCount;
    @LayoutRes
    int tabView = R.layout.view_tab;

    LinkedHashMap<Integer, TabData<T>> tabsData = new LinkedHashMap<>(5, 1f);

    @ColorRes
    private int selectedColor = android.R.color.holo_red_dark;
    @ColorRes
    private int unselectedColor = android.R.color.holo_red_dark;

    private TabBarBuilder(@TabsCount int tabsCount) {
        this.tabsCount = tabsCount;
    }

    public static <T> TabBarFactor<T> withDataSourceType(Class<T> type) {
        return new TabBarFactor<>();
    }

    public TabBarBuilder<T> withSelectedColor(@ColorRes int selectedColorResource) {
        this.selectedColor = selectedColorResource;
        return this;
    }

    public TabBarBuilder<T> withUnSelectedColor(@ColorRes int unselectedColorResource) {
        this.unselectedColor = unselectedColorResource;
        return this;
    }

    public TabBuilder<T> withNextTab() {
        return withTab(tabsData.keySet().size());
    }

    public TabBuilder<T> withTab(@TabIndex int index) {
        if (index < TabIndex.MIN_INDEX || index > TabIndex.MAX_INDEX) {
            throw new UnsupportedOperationException("invalid view index, minimum is "
                    + TabIndex.MIN_INDEX + " and maximum is " + TabIndex.MAX_INDEX);
        }
        return new TabBuilder<>(this, index);
    }


    TabBarBuilder<T> buildTab(TabBuilder<T> tabBuilder) {
        tabsData.put(tabBuilder.index, tabData(tabBuilder));
        return this;
    }

    @NonNull
    private TabData<T> tabData(TabBuilder<T> tabBuilder) {
        return new TabData.Builder<T>()
                .selectedColor(selectedColor)
                .unselectedColor(unselectedColor)
                .item(tabBuilder.item)
                .iconResource(tabBuilder.icon)
                .labelResource(tabBuilder.label)
                .onClick(tabBuilder.onClick)
                .build();
    }

    public void draw(TabBarController tabBarController) {
        tabBarController.build(this);
    }

    public static class TabBarFactor<T> {

        private TabBarFactor() {

        }

        public TabBarBuilder<T> withTabsCount(@TabsCount int tabsCount) {
            if (tabsCount < TabsCount.MIN_COUNT || tabsCount > TabsCount.MAX_COUNT) {
                throw new UnsupportedOperationException("invalid tabs count, minimum is "
                        + TabsCount.MIN_COUNT + " and maximum is " + TabsCount.MAX_COUNT);
            }
            return new TabBarBuilder<>(tabsCount);
        }

    }

}
