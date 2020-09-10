package com.wlwl.party.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.wlwl.framework.utils.AppUtils;
import com.wlwl.framework.utils.CommonTools;
import com.wlwl.party.R;

public class RoundImageView extends AppCompatImageView {


    float width, height;
    private int corner;

    public RoundImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RoundImageView,
                defStyleAttr,
                defStyleAttr
        );
        corner = a.getDimensionPixelSize(R.styleable.RoundImageView_corner, 5);
        corner= AppUtils.dp2px(corner);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width >= corner && height > corner) {
            Path path = new Path();
            //四个圆角
            path.moveTo(corner, 0);
            path.lineTo(width - corner, 0);
            path.quadTo(width, 0, width, corner);
            path.lineTo(width, height - corner);
            path.quadTo(width, height, width - corner, height);
            path.lineTo(corner, height);
            path.quadTo(0, height, 0, height - corner);
            path.lineTo(0, corner);
            path.quadTo(0, 0, corner, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

}
