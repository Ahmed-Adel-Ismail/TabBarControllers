package com.tab.bar.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class Tab<T> {

    final View view;
    final View indicator;
    final TextView label;
    final ImageView icon;
    final TabData<T> data;

    private Tab(Builder<T> builder) {
        view = builder.tab;
        indicator = builder.indicator;
        label = builder.label;
        icon = builder.icon;
        data = builder.tabData;
    }


    /**
     * {@code Tab} builder static inner class.
     */
    public static final class Builder<T> {
        private View tab;
        private View indicator;
        private TextView label;
        private ImageView icon;
        private TabData<T> tabData;

        public Builder() {
        }

        /**
         * Sets the {@code view} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param tab the {@code view} to set
         * @return a reference to this Builder
         */
        public Builder<T> tab(View tab) {
            this.tab = tab;
            return this;
        }

        /**
         * Sets the {@code indicator} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param indicator the {@code indicator} to set
         * @return a reference to this Builder
         */
        public Builder<T> indicator(View indicator) {
            this.indicator = indicator;
            return this;
        }

        /**
         * Sets the {@code label} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param label the {@code label} to set
         * @return a reference to this Builder
         */
        public Builder<T> label(TextView label) {
            this.label = label;
            return this;
        }

        /**
         * Sets the {@code icon} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param icon the {@code icon} to set
         * @return a reference to this Builder
         */
        public Builder<T> icon(ImageView icon) {
            this.icon = icon;
            return this;
        }

        /**
         * Sets the {@code data} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param tabData the {@code data} to set
         * @return a reference to this Builder
         */
        public Builder<T> tabData(TabData<T> tabData) {
            this.tabData = tabData;
            return this;
        }

        /**
         * Returns a {@code Tab} built from the parameters previously set.
         *
         * @return a {@code Tab} built with parameters of this {@code Tab.Builder}
         */
        public Tab build() {
            return new Tab<>(this);
        }
    }
}
