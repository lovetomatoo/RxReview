package com.example.guo.rxreview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guo.rxreview.R;
import com.example.guo.rxreview.utils.JsonUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by guo_hx on 2017/3/20 10:41.
 */

public class OperatorAdapter extends RecyclerView.Adapter<OperatorAdapter.Holder> {

    private static final String TAG = OperatorAdapter.class.getSimpleName();

    private String[] mArray = {"TITLE__" + "Creating 创建操作",     "just", "from", "repeat", "repeatWhen", "create", "defer", "range", "interval", "timer", "empty", "error", "never",
            "TITLE__" + "Creating Transforming 变换操作",           "map", "flatMap", "concatMap", "flatMapIterable", "switchMap", "scan", "groupBy", "buffer", "window", "cast",
            "TITLE__" + "Filtering 过滤操作",                       "filter", "takeLast", "last", "lastOrDefault", "takeLastBuffer", "skip", "skipLast", "take", "first", "takeFirst", "firstOrDefault", "elementAt", "elementAtOrDefault", "sample", "throttleLast", "throttleFirst", "throttleWithTimeout", "debounce", "timeout", "distinct", "distinctUntilChanged", "ofType", "ignoreElements",
            "TITLE__" + "Combining 结合操作",                       "startWith", "merge", "mergeDelayError", "zip", "and", "then", "when", "combineLatest", "join", "groupJoin", "switchOnNext",
            "TITLE__" + "Error Handling 错误处理",                  "onErrorResumeNext", "onErrorReturn", "onExceptionResumeNext", "retry", "retryWhen",
            "TITLE__" + "Utility 辅助操作",                         "materialize", "dematerialize", "timestamp", "serialize", "cache", "observeOn", "subscribeOn", "doOnEach", "doOnCompleted", "doOnError", "doOnTerminate", "doOnSubscribe", "doOnUnsubscribe", "finallyDo", "delay", "delaySubscription", "timeInterval", "using", "single", "singleOrDefault", "toFuture", "toIterable", "toList",
            "TITLE__" + "Conditional 条件和布尔操作",                "amb", "defaultIfEmpty", "doWhile", "ifThen", "skipUntil", "skipWhile", "switchCase", "takeUntil", "takeWhile", "takeWhileWithIndex", "whileDo", "all", "contains", "exists", "isEmpty", "sequenceEqual",
            "TITLE__" + "Mathematical 算术和聚合操作",               "averageInteger", "averageLong", "averageFloat", "averageDouble", "max", "maxBy", "min", "minBy", "sumInteger", "sumLong", "sumFloat", "sumDouble", "concat", "count", "countLong", "reduce", "collect", "toList", "toSortedList", "toMap", "toMultiMap",
            "TITLE__" + "Async 异步操作",                           "start", "toAsync", "asyncAction", "asyncFunc", "startFuture", "deferFuture", "forEachFuture", "fromAction", "fromCallable", "fromRunnable", "runAsync",
            "TITLE__" + "Connect 连接操作",                         "ConnectableObservable.connect", "Observable.publish", "Observable.replay", "ConnectableObservable.refCount",
            "TITLE__" + "Convert 转换操作",
            "TITLE__" + "Blocking 阻塞操作",                        "forEach", "first", "firstOrDefault", "last", "lastOrDefault", "mostRecent", "next", "latest", "single", "singleOrDefault", "toFuture", "toIterable", "getIterator",
            "TITLE__" + "String 字符串操作",                         "byLine", "decode", "encode", "from", "join", "split", "stringConcat",

            "TITLE__" + "待续..."};

    private static final int TYPE_HEAD = 1;

    @Override
    public int getItemViewType(int position) {
        if (mArray[position].startsWith("TITLE__")) {
            return TYPE_HEAD;
        }
        return 0;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                View itemViewHead = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_use, parent, false);
                return new Holder(itemViewHead);
            default:
                View itemViewContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_operator, parent, false);
                return new Holder(itemViewContent);
        }

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mTvTitle.setText(mArray[position]);
        holder.mLlItemRvRoot.setOnClickListener(v -> {
            Toast.makeText(holder.mLlItemRvRoot.getContext(), mArray[position], Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0:

                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArray.length;
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView mTvTitle;
        private final View mLlItemRvRoot;

        public Holder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mLlItemRvRoot = itemView.findViewById(R.id.ll_item_rv_root);
        }

    }

}
