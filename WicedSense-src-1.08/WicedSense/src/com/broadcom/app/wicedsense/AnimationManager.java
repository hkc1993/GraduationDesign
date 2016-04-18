package com.broadcom.app.wicedsense;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.animation.LinearInterpolator;

/**
 * 动画管理
 *
 */
public class AnimationManager {

    public interface Animated extends AnimatorUpdateListener {
        public void showFirstAnimatedValues();

        public boolean hasAnimatedValuesChanged();

        public void saveAnimatedValues();

        public void prepareAnimatedValues(List<PropertyValuesHolder> values);
    }

    private final int mFrameDelayMs;
    private final int mAnimateIntervalMs;
    private final ValueAnimator mAnimator;
    private long mLastTime;
    private long mCurrentTime;
    private boolean mIsReady;
    private final ArrayList<PropertyValuesHolder> mPropertyValues;

    public AnimationManager(int frameDelayMs, int animateIntervalMs) {
        mAnimator = new ValueAnimator();
        mAnimator.setInterpolator(new LinearInterpolator());
        mFrameDelayMs = frameDelayMs;
        mAnimateIntervalMs = animateIntervalMs;
        ValueAnimator.setFrameDelay(mFrameDelayMs);

        mLastTime = 0;
        mCurrentTime = 0;
        mPropertyValues = new ArrayList<PropertyValuesHolder>();
    }

    public boolean useAnimation() {
        return Settings.animate();
    }

    public void addAnimated(Animated a) {
        ArrayList<Animator.AnimatorListener> l = mAnimator.getListeners();
        if (l == null || !l.contains(a)) {
            mAnimator.addUpdateListener(a);
        }
    }

    public void removeAnimated(Animated a) {
        mAnimator.removeUpdateListener(a);
    }

    public void init() {
        mCurrentTime = System.currentTimeMillis();

        long duration = mCurrentTime - mLastTime;
        if (duration < mAnimateIntervalMs) {
            mIsReady = false;
            return;
        }

        if (mAnimator.isRunning()) {
            // mAnimator.end();
            mIsReady = false;
            return;
        }

        mIsReady = true;
    }

    public void prepareAnimated(Animated a) {
        if (!mIsReady) {
            return;
        }

        // Check if this is the first values: don't animate first value
        if (mLastTime == 0) {
            a.showFirstAnimatedValues();
            a.saveAnimatedValues();
            mLastTime = mCurrentTime;
            return;
        }

        if (a.hasAnimatedValuesChanged()) {
            a.prepareAnimatedValues(mPropertyValues);
            a.saveAnimatedValues();
        } else {

        }
    }

    public void animate() {
        if (mPropertyValues.size() > 0) {
            PropertyValuesHolder[] values = new PropertyValuesHolder[mPropertyValues.size()];
            mPropertyValues.toArray(values);
            mAnimator.setValues(values);
            mAnimator.setDuration(mAnimateIntervalMs);
            mAnimator.start();
            mLastTime = mCurrentTime;
            mPropertyValues.clear();
            mIsReady = false;
        }
    }

}
