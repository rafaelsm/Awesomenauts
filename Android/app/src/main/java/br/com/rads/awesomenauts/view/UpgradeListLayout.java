package br.com.rads.awesomenauts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import br.com.rads.awesomenauts.adapter.UpgradeListAdapter;

/**
 * Created by rafael_2 on 09/03/14.
 */
public class UpgradeListLayout extends LinearLayout {

    private UpgradeListAdapter adapter;
    private View view;

    public UpgradeListLayout(Context context) {
        super(context);
    }

    public UpgradeListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpgradeListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setAdapter(UpgradeListAdapter adapter) {
        this.adapter = adapter;
        setOrientation(VERTICAL);

        if (adapter != null) {
            this.removeAllViews();
            for (int i = 0; i < adapter.getCount(); i++) {
                view = adapter.getView(i, null, null);
                this.addView(view);
            }
        }

    }
}
