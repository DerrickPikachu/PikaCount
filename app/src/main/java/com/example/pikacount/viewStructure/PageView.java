package com.example.pikacount.viewStructure;

import android.content.Context;
<<<<<<< HEAD
import android.widget.RelativeLayout;

public abstract class PageView extends RelativeLayout {
    public PageView(Context context) {
        super(context);
    }

    public void updateList() {}
=======

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class PageView extends ConstraintLayout {

    public PageView(Context context) {
        super(context);
    }
>>>>>>> 8ef4810534be616c5feb1ef06348630bb242dfa2
}
