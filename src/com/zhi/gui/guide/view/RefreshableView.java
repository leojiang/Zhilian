package com.zhi.gui.guide.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhi.gui.guide.R;

public class RefreshableView extends LinearLayout implements OnTouchListener, AbsListView.OnScrollListener {

    /**
     * 下拉状态
     */
    public static final int STATUS_PULL_TO_REFRESH = 0;

    /**
     * 释放立即刷新状态
     */
    public static final int STATUS_RELEASE_TO_REFRESH = 1;

    /**
     * 正在刷新状态
     */
    public static final int STATUS_REFRESHING = 2;

    /**
     * 刷新完成或未刷新状态
     */
    public static final int STATUS_REFRESH_FINISHED = 3;

    /**
     * 下拉头部回滚的速度
     */
    public static final int SCROLL_SPEED = -20;

    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    /**
     * 上次更新时间的字符串常量，用于作为SharedPreferences的键值
     */
    private static final String UPDATED_AT = "updated_at";

    /**
     * 下拉刷新的回调接口
     */
    private RefreshAndLoadListener mListener;

    /**
     * 用于存储上次更新时间
     */
    private SharedPreferences preferences;

    /**
     * 下拉头的View
     */
    private View header;
    private View footer;

    /**
     * 需要去下拉刷新的ListView
     */
    private ListView listView;

    /**
     * 刷新时显示的进度条
     */
    private ProgressBar progressBar;

    /**
     * 指示下拉和释放的箭头
     */
    private ImageView arrow;

    /**
     * 指示下拉和释放的文字描述
     */
    private TextView description;

    /**
     * 上次更新时间的文字描述
     */
    private TextView updateAt;

    /**
     * 下拉头的布局参数
     */
    private MarginLayoutParams headerLayoutParams;

    /**
     * 上次更新时间的毫秒值
     */
    private long lastUpdateTime;

    /**
     * 为了防止不同界面的下拉刷新在上次更新时间上互相有冲突，使用id来做区分
     */
    private int mId = -1;

    /**
     * 下拉头的高度
     */
    private int hideHeaderHeight;

    /**
     * 当前处理什么状态，可选值有STATUS_PULL_TO_REFRESH, STATUS_RELEASE_TO_REFRESH,
     * STATUS_REFRESHING 和 STATUS_REFRESH_FINISHED
     */
    private int currentStatus = STATUS_REFRESH_FINISHED;
    ;

    /**
     * 记录上一次的状态是什么，避免进行重复操作
     */
    private int lastStatus = currentStatus;

    /**
     * 手指按下时的屏幕纵坐标
     */
    private float yDown;

    /**
     * 在被判定为滚动之前用户手指可以移动的最大值。
     */
    private int touchSlop;
    private boolean loadOnce;
    private boolean ableToPull;
    private ProgressBar footerProgress;
    private TextView footerTextView;

    public RefreshableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        header = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh, null, true);
        progressBar = (ProgressBar) header.findViewById(R.id.progress_bar);
        arrow = (ImageView) header.findViewById(R.id.arrow);
        description = (TextView) header.findViewById(R.id.description);
        updateAt = (TextView) header.findViewById(R.id.updated_at);
        footer = LayoutInflater.from(context).inflate(R.layout.list_footer, null, true);
        footerProgress = (ProgressBar) footer.findViewById(R.id.progress_bar);
        footerTextView = (TextView) footer.findViewById(R.id.description);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        refreshUpdatedAtValue();
        setOrientation(VERTICAL);
        addView(header, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !loadOnce) {
            hideHeaderHeight = -header.getHeight();
            headerLayoutParams = (MarginLayoutParams) header.getLayoutParams();
            headerLayoutParams.topMargin = hideHeaderHeight;
            listView = (ListView) getChildAt(1);
            listView.addFooterView(footer);
            listView.setOnTouchListener(this);
            listView.setOnScrollListener(this);
            loadOnce = true;
            header.setLayoutParams(headerLayoutParams);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setIsAbleToPull(event);
        if (ableToPull) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    yDown = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float yMove = event.getRawY();
                    int distance = (int) (yMove - yDown);
                    // 如果手指是下滑状态，并且下拉头是完全隐藏的，就屏蔽下拉事件
                    if (distance <= 0 && headerLayoutParams.topMargin <= hideHeaderHeight) {
                        return false;
                    }
                    if (distance < touchSlop) {
                        return false;
                    }
                    if (currentStatus != STATUS_REFRESHING) {
                        if (headerLayoutParams.topMargin > 0) {
                            currentStatus = STATUS_RELEASE_TO_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL_TO_REFRESH;
                        }
                        // 通过偏移下拉头的topMargin值，来实现下拉效果
                        headerLayoutParams.topMargin = (distance / 2) + hideHeaderHeight;
                        header.setLayoutParams(headerLayoutParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                default:
                    if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                        // 松手时如果是释放立即刷新状态，就去调用正在刷新的任务
                        new RefreshingTask().execute();
                    } else if (currentStatus == STATUS_PULL_TO_REFRESH) {
                        // 松手时如果是下拉状态，就去调用隐藏下拉头的任务
                        new HideHeaderTask().execute();
                    }
                    break;
            }
            // 时刻记得更新下拉头中的信息
            if (currentStatus == STATUS_PULL_TO_REFRESH
                    || currentStatus == STATUS_RELEASE_TO_REFRESH) {
                updateHeaderView();
                // 当前正处于下拉或释放状态，要让ListView失去焦点，否则被点击的那一项会一直处于选中状态
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                lastStatus = currentStatus;
                // 当前正处于下拉或释放状态，通过返回true屏蔽掉ListView的滚动事件
                return true;
            }
        }
        return false;
    }

    private boolean isLoadingMore = false;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                if (listView.getLastVisiblePosition() == (listView.getCount() - 1) && !isLoadingMore) {
                    new LoadMoreTask().execute();
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public void setOnRefreshListener(RefreshAndLoadListener listener, int id) {
        mListener = listener;
        mId = id;
    }

    /**
     * 当所有的刷新逻辑完成后，记录调用一下，否则你的ListView将一直处于正在刷新状态。
     */
    public void finishRefreshing() {
        currentStatus = STATUS_REFRESH_FINISHED;
        preferences.edit().putLong(UPDATED_AT + mId, System.currentTimeMillis()).commit();
        new HideHeaderTask().execute();
    }

    /**
     * 根据当前ListView的滚动状态来设定 {@link #ableToPull}
     * 的值，每次都需要在onTouch中第一个执行，这样可以判断出当前应该是滚动ListView，还是应该进行下拉。
     *
     * @param event
     */
    private void setIsAbleToPull(MotionEvent event) {
        View firstChild = listView.getChildAt(0);
        if (firstChild != null) {
            int firstVisiblePos = listView.getFirstVisiblePosition();
            if (firstVisiblePos == 0 && firstChild.getTop() == 0) {
                if (!ableToPull) {
                    yDown = event.getRawY();
                }
                // 如果首个元素的上边缘，距离父布局值为0，就说明ListView滚动到了最顶部，此时应该允许下拉刷新
                ableToPull = true;
            } else {
                if (headerLayoutParams.topMargin != hideHeaderHeight) {
                    headerLayoutParams.topMargin = hideHeaderHeight;
                    header.setLayoutParams(headerLayoutParams);
                }
                ableToPull = false;
            }
        } else {
            // 如果ListView中没有元素，也应该允许下拉刷新
            ableToPull = true;
        }
    }

    private void updateFooterView() {
        if (isLoadingMore) {
            footerTextView.setVisibility(View.GONE);
            footerProgress.setVisibility(View.VISIBLE);
        } else {
            footerTextView.setVisibility(View.VISIBLE);
            footerProgress.setVisibility(View.GONE);
        }
    }

    /**
     * 更新下拉头中的信息。
     */
    private void updateHeaderView() {
        if (lastStatus != currentStatus) {
            if (currentStatus == STATUS_PULL_TO_REFRESH) {
                description.setText(getResources().getString(R.string.pull_to_refresh));
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                description.setText(getResources().getString(R.string.release_to_refresh));
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_REFRESHING) {
                description.setText(getResources().getString(R.string.refreshing));
                progressBar.setVisibility(View.VISIBLE);
                arrow.clearAnimation();
                arrow.setVisibility(View.GONE);
            }
            refreshUpdatedAtValue();
        }
    }

    /**
     * 根据当前的状态来旋转箭头。
     */
    private void rotateArrow() {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (currentStatus == STATUS_PULL_TO_REFRESH) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }

    /**
     * 刷新下拉头中上次更新时间的文字描述。
     */
    private void refreshUpdatedAtValue() {
        lastUpdateTime = preferences.getLong(UPDATED_AT + mId, -1);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;
        if (lastUpdateTime == -1) {
            updateAtValue = getResources().getString(R.string.not_updated_yet);
        } else if (timePassed < 0) {
            updateAtValue = getResources().getString(R.string.time_error);
        } else if (timePassed < ONE_MINUTE) {
            updateAtValue = getResources().getString(R.string.updated_just_now);
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_YEAR) {
            timeIntoFormat = timePassed / ONE_MONTH;
            String value = timeIntoFormat + "个月";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else {
            timeIntoFormat = timePassed / ONE_YEAR;
            String value = timeIntoFormat + "年";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        }
        updateAt.setText(updateAtValue);
    }

    private class RefreshingTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            int topMargin = headerLayoutParams.topMargin;
            while (true) {
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin <= 0) {
                    topMargin = 0;
                    break;
                }
                publishProgress(topMargin);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentStatus = STATUS_REFRESHING;
            publishProgress(0);
            if (mListener != null) {
                mListener.onRefresh();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            updateHeaderView();
            headerLayoutParams.topMargin = topMargin[0];
            header.setLayoutParams(headerLayoutParams);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finishRefreshing();
        }
    }

    private class LoadMoreTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isLoadingMore = true;
            updateFooterView();
        }

        @Override
        protected Void doInBackground(Void... params) {

            if (mListener != null) {
                mListener.onLoad();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            isLoadingMore = false;
            updateFooterView();
        }
    }

    private class HideHeaderTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            int topMargin = headerLayoutParams.topMargin;
            while (true) {
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin <= hideHeaderHeight) {
                    topMargin = hideHeaderHeight;
                    break;
                }
                publishProgress(topMargin);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            headerLayoutParams.topMargin = topMargin[0];
            header.setLayoutParams(headerLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer topMargin) {
            headerLayoutParams.topMargin = topMargin;
            header.setLayoutParams(headerLayoutParams);
            currentStatus = STATUS_REFRESH_FINISHED;
        }
    }

    public interface RefreshAndLoadListener {
        void onRefresh();

        void onLoad();
    }

}
