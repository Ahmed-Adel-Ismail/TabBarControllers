package com.tab.bar.controller;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({1,2,3,4})
public @interface TabsCount {
}
