package com.tab.bar.controller;

class TabData<T> {

    final T item;
    final int iconResource;
    final int labelResource;
    final OnTabClick<T> onClick;
    final int selectedColor;
    final int unselectedColor;

    private TabData(Builder<T> builder) {
        item = builder.item;
        iconResource = builder.iconResource;
        labelResource = builder.labelResource;
        onClick = builder.onClick;
        selectedColor = builder.selectedColor;
        unselectedColor = builder.unselectedColor;
    }


    /**
     * {@code TabData} builder static inner class.
     */
    static final class Builder<T> {
        private T item;
        private int iconResource;
        private int labelResource;
        private OnTabClick<T> onClick;
        private int selectedColor;
        private int unselectedColor;

        Builder() {
        }

        /**
         * Sets the {@code item} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param item the {@code item} to set
         * @return a reference to this Builder
         */
        Builder<T> item(T item) {
            this.item = item;
            return this;
        }

        /**
         * Sets the {@code iconResource} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param iconResource the {@code iconResource} to set
         * @return a reference to this Builder
         */
        Builder<T> iconResource(int iconResource) {
            this.iconResource = iconResource;
            return this;
        }

        /**
         * Sets the {@code labelResource} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param labelResource the {@code labelResource} to set
         * @return a reference to this Builder
         */
        Builder<T> labelResource(int labelResource) {
            this.labelResource = labelResource;
            return this;
        }

        /**
         * Sets the {@code onClick} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param onClick the {@code onClick} to set
         * @return a reference to this Builder
         */
        Builder<T> onClick(OnTabClick<T> onClick) {
            this.onClick = onClick;
            return this;
        }

        /**
         * Sets the {@code selectedColor} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param selectedColor the {@code selectedColor} to set
         * @return a reference to this Builder
         */
        Builder<T> selectedColor(int selectedColor) {
            this.selectedColor = selectedColor;
            return this;
        }

        /**
         * Sets the {@code unselectedColor} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param unselectedColor the {@code unselectedColor} to set
         * @return a reference to this Builder
         */
        Builder<T> unselectedColor(int unselectedColor) {
            this.unselectedColor = unselectedColor;
            return this;
        }

        /**
         * Returns a {@code TabData} built from the parameters previously set.
         *
         * @return a {@code TabData} built with parameters of this {@code TabData.Builder}
         */
        TabData<T> build() {
            return new TabData<>(this);
        }
    }
}
