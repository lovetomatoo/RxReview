package com.example.guo.rxreview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guo.rxreview.R;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;


/**
 * Created by guo_hx on 2017/3/20 10:41.
 */

public class OperatorAdapter extends RecyclerView.Adapter<OperatorAdapter.Holder> {

    private static final String TAG = OperatorAdapter.class.getSimpleName();

    private String[] mArray = {"TITLE__" + "Creating 创建操作", "just", "from", "repeat", "repeatWhen", "create", "defer", "range", "interval", "timer", "empty", "error", "never",
            "TITLE__" + "Creating Transforming 变换操作", "map", "flatMap", "concatMap", "flatMapIterable", "switchMap", "scan", "groupBy", "buffer", "window", "cast",
            "TITLE__" + "Filtering 过滤操作", "filter", "takeLast", "last", "lastOrDefault", "takeLastBuffer", "skip", "skipLast", "take", "first", "takeFirst", "firstOrDefault", "elementAt", "elementAtOrDefault", "sample", "throttleLast", "throttleFirst", "throttleWithTimeout", "debounce", "timeout", "distinct", "distinctUntilChanged", "ofType", "ignoreElements",
            "TITLE__" + "Combining 结合操作", "startWith", "merge", "mergeDelayError", "zip", "and", "then", "when", "combineLatest", "join", "groupJoin", "switchOnNext",
            "TITLE__" + "Error Handling 错误处理", "onErrorResumeNext", "onErrorReturn", "onExceptionResumeNext", "retry", "retryWhen",
            "TITLE__" + "Utility 辅助操作", "materialize", "dematerialize", "timestamp", "serialize", "cache", "observeOn", "subscribeOn", "doOnEach", "doOnCompleted", "doOnError", "doOnTerminate", "doOnSubscribe", "doOnUnsubscribe", "finallyDo", "delay", "delaySubscription", "timeInterval", "using", "single", "singleOrDefault", "toFuture", "toIterable", "toList",
            "TITLE__" + "Conditional 条件和布尔操作", "amb", "defaultIfEmpty", "doWhile", "ifThen", "skipUntil", "skipWhile", "switchCase", "takeUntil", "takeWhile", "takeWhileWithIndex", "whileDo", "all", "contains", "exists", "isEmpty", "sequenceEqual",
            "TITLE__" + "Mathematical 算术和聚合操作", "averageInteger", "averageLong", "averageFloat", "averageDouble", "max", "maxBy", "min", "minBy", "sumInteger", "sumLong", "sumFloat", "sumDouble", "concat", "count", "countLong", "reduce", "collect", "toList", "toSortedList", "toMap", "toMultiMap",
            "TITLE__" + "Async 异步操作", "start", "toAsync", "asyncAction", "asyncFunc", "startFuture", "deferFuture", "forEachFuture", "fromAction", "fromCallable", "fromRunnable", "runAsync",
            "TITLE__" + "Connect 连接操作", "ConnectableObservable.connect", "Observable.publish", "Observable.replay", "ConnectableObservable.refCount",
            "TITLE__" + "Convert 转换操作",
            "TITLE__" + "Blocking 阻塞操作", "forEach", "first", "firstOrDefault", "last", "lastOrDefault", "mostRecent", "next", "latest", "single", "singleOrDefault", "toFuture", "toIterable", "getIterator",
            "TITLE__" + "String 字符串操作", "byLine", "decode", "encode", "from", "join", "split", "stringConcat",

            "TITLE__" + "待续..."};

    private static final int TYPE_HEAD = 1;

    private String[] mTestArray = {"11111", "22222", "33333"};

    @Override
    public int getItemCount() {
        return mArray.length;
    }

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
            switch (mArray[position]) {
                //-----------------------------------------------------Creat
                case "just":
                    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                            .subscribe(integer -> {
                                Log.i(TAG + "just", integer + "");
                            });
                    break;

                case "from":
                    ArrayList<String> list = new ArrayList<>();
                    list.add("from__1");
                    list.add("from__2");
                    list.add("from__3");
                    Observable.from(list)
                            .subscribe(s -> {
                                Log.i(TAG + "from", s);
                            });
                    break;

                case "repeat":
//                    Observable.just(1, 2)
//                            .repeat(5, Schedulers.io())
//                            .subscribe(integer -> {
//                                Log.i(TAG + "repeat", integer + "");
//                            });
                    Observable.from(mTestArray)
                            .repeat(2)
                            .subscribe(s -> {
                                Log.i(TAG + "repeat", s);
                            });
                    break;

                case "repeatWhen"://暂时有点没明白啊
                    Observable.just(1, 2)
                            .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
                                @Override
                                public Observable<?> call(Observable<? extends Void> completed) {
                                    return Observable.timer(5, TimeUnit.SECONDS);
                                }
                            })
                            .map(new Func1<Integer, String>() {
                                @Override
                                public String call(Integer integer) {
                                    return integer + "";
                                }
                            })
                            .subscribe(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    Log.i(TAG + "repeatWhen", s);
                                }
                            });
                    break;

                case "create":

                    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
                        @Override
                        public void call(Subscriber<? super String> subscriber) {
                            subscriber.onNext("create_onNext");
                            subscriber.onCompleted();
                            subscriber.onError(new Throwable());
                        }
                    });

                    Observer<String> observer = new Observer<String>() {
                        @Override
                        public void onCompleted() {
                            Log.i(TAG + "creat", "onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            Log.i(TAG + "creat", s);
                        }
                    };

                    observable.subscribe(observer);
                    break;

                case "defer"://只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable
                    Observable.defer(() -> Observable.just(1)).map(integer -> integer + "").subscribe(s -> {
                        Log.i(TAG + "defer", s);
                    });
                    break;

                case "range"://创建一个发射指定范围的整数序列的Observable
                    Observable.range(1, 5)//1 2 3 4 5
                            .map(integer -> integer + "")
                            .subscribe(s -> {
                                Log.i(TAG + "range", s);
                            });
                    break;
                case "interval":
                    Observable
                            .interval(2, TimeUnit.SECONDS)
                            .subscribe(aLong -> {
                                Log.i(TAG + "interval", aLong + "");//aLong 第几次
                            });
                    break;
                case "timer":
                    Observable.timer(3, TimeUnit.SECONDS)
                            .subscribe(aLong -> {
                                Log.i(TAG + "timer", aLong + "");//aLong == 0
                            });

                    break;
                case "empty":
                    Observable.empty()
                            .subscribe(new Observer<Object>() {
                                @Override
                                public void onCompleted() {
                                    Log.i(TAG + "empty", "empty__onCompleted");
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(Object o) {

                                }
                            });
                    break;
                case "error":
                    Observable.error(new Throwable("自定义"))
                            .subscribe(new Subscriber<Object>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i(TAG + "error", e.getMessage());
                                }

                                @Override
                                public void onNext(Object o) {

                                }
                            });
                    break;
                case "never":
                    Observable.never()//回调都不走，搞不懂
                            .subscribe(new Subscriber<Object>() {
                                @Override
                                public void onCompleted() {
                                    Log.i(TAG + "never", "......");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i(TAG + "never", e.toString() + "......");
                                }

                                @Override
                                public void onNext(Object o) {
                                    Log.i(TAG + "never", o.toString() + "......");
                                }
                            });
                    break;
                //-----------------------------------------------------Chanege
                case "map":
                    Observable.from(mTestArray)
                            .map(s -> s + "__map")
                            .subscribe(s -> {
                                Log.i(TAG + "map", s);
                            });
                    break;
                case "flatMap":
                    Observable.from(mTestArray)
                            .flatMap(new Func1<String, Observable<String>>() {
                                @Override
                                public Observable<String> call(String s) {
                                    return Observable.just(s);
                                }
                            })
                            .map(s -> s + "__floatMap")
                            .subscribe(s -> {
                                Log.i(TAG + "floatMap", s);
                            });

                    break;
                case "concatMap":
                    Observable.from(mTestArray)
                            .concatMap(new Func1<String, Observable<String>>() {
                                @Override
                                public Observable<String> call(String s) {
                                    return Observable.just(s);
                                }
                            })
                            .map(s -> s + "__concatMap")
                            .subscribe(s -> {
                                Log.i(TAG + "concatMap", s);
                            });

                case "flatMapIterable"://shit

                    break;
                case "switchMap":

                    break;
                case "scan":

                    break;
                case "groupBy":

                    break;
                case "buffer":

                    break;
                case "window":

                    break;
                case "cast":

                    break;
            }
        });
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
