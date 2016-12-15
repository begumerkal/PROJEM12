package com.example.stevennl.tastysnake.controller.game;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stevennl.tastysnake.Config;
import com.example.stevennl.tastysnake.R;

/**
 * Data analysis page.
 * Author: LCY
 */
public class AnalysisFragment extends Fragment {
    private static final String TAG = "AnalysisFragment";
    private static final String ATTR_ALPHA = "alpha";
    private GameActivity act;
    private Handler handler;

    private TextView infoTxt;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act = (GameActivity)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_analysis, container, false);
        initInfoTxt(v);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAdded()) {
                    showInfo();

                }
            }
        }, Config.DELAY_ANALYSIS_FRAG);
        return v;
    }

    private void initInfoTxt(View v) {
        infoTxt = (TextView) v.findViewById(R.id.analysis_infoTxt);
    }

    /**
     * Show analysis result.
     */
    private void showInfo() {
        ObjectAnimator.ofFloat(infoTxt, ATTR_ALPHA, 0, 1)
                .setDuration(Config.DURATION_ANALYSIS_INFO).start();
    }
}