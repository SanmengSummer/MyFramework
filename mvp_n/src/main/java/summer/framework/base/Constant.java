
package summer.framework.base;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import summer.framework.utils.AppUtil;
import summer.framework.utils.FileUtil;

/**
 * Summer
 *
 * @date 16/8/5.
 */
public class Constant {
    public static final String API_BASE_URL = "http://lexueinterface.huan.tv/";

    public static final String IS_NIGHT = "isNight";

    //可代替Menu，占用空间更小
    @StringDef({
            SortType.DEFAULT,
            SortType.COMMENT_COUNT,
            SortType.CREATED,
            SortType.HELPFUL
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortType {
        String DEFAULT = "updated";

        String CREATED = "created";

        String HELPFUL = "helpful";

        String COMMENT_COUNT = "comment-count";
    }

    public static List<String> sortTypeList = new ArrayList<String>() {{
        add(SortType.DEFAULT);
        add(SortType.CREATED);
        add(SortType.COMMENT_COUNT);
        add(SortType.HELPFUL);
    }};

}
