package com.example.calendarmeirlen.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.calendarmeirlen.R;
import com.example.calendarmeirlen.interactors.AUCalendar;
import com.example.calendarmeirlen.interactors.ViewInteractor;
import com.example.calendarmeirlen.presenter.interfeaces.CustomizableCalendarPresenter;

import org.joda.time.DateTime;

import java.util.Locale;


public class SubView extends RelativeLayout implements com.example.calendarmeirlen.view.SubView {
    TextView monthTxt;

    private ViewInteractor viewInteractor;

    private @LayoutRes int layoutResId = R.layout.sub_view;
    private CustomizableCalendarPresenter presenter;
    private Context context;

    public SubView(Context context) {
        this(context, null);
    }

    public SubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomizableCalendar);
        if (typedArray != null) {
            layoutResId = typedArray.getResourceId(R.styleable.CustomizableCalendar_layout, R.layout.sub_view);
            typedArray.recycle();
        }

        LayoutInflater.from(context).inflate(layoutResId, this);
        monthTxt = (TextView) findViewById(android.R.id.message);
    }

    @Override
    public void onMonthChanged(String month) {
        monthTxt.setText(month.toLowerCase()+" 2018");
        if (viewInteractor != null) {
            viewInteractor.onSubViewBindView(this, month);
        }
    }

    @Override
    public void refreshData() {
    }

    @Override
    public void setLayoutResId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }

    @Override
    public void injectViewInteractor(ViewInteractor viewInteractor) {
        this.viewInteractor = viewInteractor;
        DateTime firstMonth = AUCalendar.getInstance().getFirstMonth();
        String month = firstMonth.toString("MMMMM", Locale.getDefault());
        if (!TextUtils.isEmpty(month)) {
            String formattedMonth = month.substring(0, 1).toUpperCase(Locale.getDefault()) + month.substring(1);
            onMonthChanged(formattedMonth);
        }
    }

    @Override
    public void injectPresenter(CustomizableCalendarPresenter presenter) {
        this.presenter = presenter;
        this.presenter.injectSubView(this);
    }
}