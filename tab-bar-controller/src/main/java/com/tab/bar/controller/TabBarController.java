package com.tab.bar.controller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * a View that handles displaying a Tab-Bar Controller which is similar to IOS
 * <p>
 * Created by Ahmed Adel Ismail on 3/12/2018.
 */
public class TabBarController extends LinearLayout {

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
        updateLayoutParams(view);
        resetIndicatorColor(view);
        updateLabel(viewIndex, view, builder);
        updateIcon(viewIndex, view, builder);
        updateOnClick(viewIndex, view, builder);
        addView(view,viewIndex);
    }

    private void updateLayoutParams(View view) {
        LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        params.weight = 1;
        view.setLayoutParams(params);
    }


    private void resetIndicatorColor(View view) {
        view.findViewById(R.id.tab_indicator)
                .setBackgroundResource(android.R.color.transparent);
    }

    private void updateLabel(int viewIndex, View view, TabBarBuilder builder) {
        TextView label = (TextView) view.findViewById(R.id.tab_label);
        label.setTextColor(getResources().getColor(builder.labelsColor));
        label.setText((Integer) builder.labels.get(viewIndex));
    }

    private void updateIcon(int viewIndex, View view, TabBarBuilder builder) {
        ((ImageView) view.findViewById(R.id.tab_icon))
                .setImageResource((Integer) builder.icons.get(viewIndex));
    }

    private void updateOnClick(final int viewIndex, View view, final TabBarBuilder builder) {
        view.setTag(builder.items.get(viewIndex));
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                unselectOtherTabs();
                selectCurrentTab(v, builder);
                invokeOnTabClick(v, builder, viewIndex);
            }
        });
    }

    private void unselectOtherTabs() {
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            resetIndicatorColor(getChildAt(childIndex));
        }
    }

    private void selectCurrentTab(View v, TabBarBuilder builder) {
        v.findViewById(R.id.tab_indicator)
                .setBackgroundResource(builder.indicatorsColor);
    }

    @SuppressWarnings("unchecked")
    private void invokeOnTabClick(View v, TabBarBuilder builder, int viewIndex) {
        Object item = v.getTag();
        OnTabClick onTabClick = (OnTabClick) builder.onTabClicks.get(viewIndex);
        onTabClick.onClick(viewIndex, item);
    }


}
