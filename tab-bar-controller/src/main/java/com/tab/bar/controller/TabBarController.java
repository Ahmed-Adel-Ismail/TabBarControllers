package com.tab.bar.controller;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedHashMap;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * a View that handles displaying a Tab-Bar Controller which is similar to IOS
 * <p>
 * Created by Ahmed Adel Ismail on 3/12/2018.
 */
public class TabBarController extends LinearLayout {

    private final LinkedHashMap<Integer, Tab> tabs = new LinkedHashMap<>(5, 1f);

    {
        inflate(getContext(), R.layout.view_tabs_bar, null);
    }

    public TabBarController(Context context) {
        super(context);
    }

    public TabBarController(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TabBarController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void build(TabBarBuilder builder) {
        this.setWeightSum(builder.tabsCount);
        for (int viewIndex = 0; viewIndex < builder.tabsCount; viewIndex++) {
            drawTabViews(viewIndex, builder);
        }
        getChildAt(0).performClick();
    }

    private void drawTabViews(int viewIndex, TabBarBuilder builder) {
        View view = inflate(getContext(), builder.tabView, null);
        tabs.put(viewIndex, tab(viewIndex, builder, view));
        addView(view, viewIndex);
    }

    @NonNull
    private Tab tab(int viewIndex, TabBarBuilder builder, View view) {
        Tab tab = createTab(viewIndex, builder, viewWithLayoutParams(view));
        updateToUnselectedState(tab);
        updateLabel(tab);
        updateIconResource(tab);
        updateOnClick(viewIndex, tab);
        return tab;
    }

    @NonNull
    private <T> Tab createTab(int viewIndex, TabBarBuilder<T> builder, View view) {
        return new Tab.Builder<T>()
                .tab(view)
                .indicator(view.findViewById(R.id.tab_indicator))
                .icon((ImageView) view.findViewById(R.id.tab_icon))
                .label((TextView) view.findViewById(R.id.tab_label))
                .tabData(builder.tabsData.get(viewIndex))
                .build();
    }

    private View viewWithLayoutParams(View view) {
        LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        params.weight = 1;
        view.setLayoutParams(params);
        return view;
    }


    private void updateToUnselectedState(Tab tab) {
        switchTab(tab, android.R.color.transparent, tab.data.unselectedColor, false);
    }

    private void switchTab(Tab tab, int indicator, int tint, boolean selected) {
        tab.indicator.setBackgroundResource(indicator);
        tab.label.setTextColor(getResources().getColor(tint));
        updateIconTint(tab.icon, tint);
        tab.view.setSelected(selected);
    }

    private void updateIconTint(ImageView imageView, int colorResource) {
        ImageViewCompat.setImageTintList(imageView,
                ColorStateList.valueOf(colorValue(colorResource)));
    }

    private void updateLabel(Tab tab) {
        tab.label.setText(tab.data.labelResource);
    }

    private void updateIconResource(Tab tab) {
        tab.icon.setImageResource(tab.data.iconResource);
    }

    private void updateOnClick(final int viewIndex, Tab tab) {
        tab.view.setTag(tab);
        tab.view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Tab tab = (Tab) v.getTag();
                unselectAllTabs();
                selectTab(tab);
                invokeOnTabClick(viewIndex, tab);
            }
        });
    }


    private int colorValue(int selectedColor) {
        return ContextCompat.getColor(getContext(), selectedColor);
    }


    private void unselectAllTabs() {
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            Tab tab = tabs.get(childIndex);
            if (tab.view.isSelected()) {
                updateToUnselectedState(tab);
            }
        }
    }

    private void selectTab(Tab tab) {
        switchTab(tab, tab.data.selectedColor, tab.data.selectedColor, true);
    }

    @SuppressWarnings("unchecked")
    private void invokeOnTabClick(int viewIndex, Tab tab) {
        tab.data.onClick.onClick(viewIndex, tab.data.item);
    }


}
