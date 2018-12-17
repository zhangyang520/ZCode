package com.kekstudio.dachshundtablayout;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 工具类
 * Created by Andy671
 */
public class HelperUtils {

    /**
     * px 转换为 dp
     * @param px
     * @return
     */
    public static int pxToDp(float px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * dp 转换为 px
     * @param dp
     * @return
     */
    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
