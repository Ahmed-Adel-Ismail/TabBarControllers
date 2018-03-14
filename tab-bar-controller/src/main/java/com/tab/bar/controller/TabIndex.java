package com.tab.bar.controller;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({0, 1, 2, 3})
public @interface TabIndex {

    int MIN_INDEX = 0;
    int MAX_INDEX = 3;
}
