# RxReview
## RxJavaSamples


### 扩展的观察者模式
    rxjava的异步实现，是通过一种扩展的观察者设计模式来实现的。

### rxjava的3个基本概念
    1.Observable    被观察者
    2.Observer/Subscriber      观察者
    3.subscribe     订阅
        Observable和Observer通过subscribe()方法实现订阅关系，从而Observable
    可以在需要的时候发出事件来通知Observer。

### 基本实现
    1.创建Observer
        Observer即观察者，它决定事件触发的时候将有怎样的行为。

    2.创建Observeable
        .creat()    .from()     .just()

    3.Subscribe(订阅)
        observable.subscribe(observer)
        或observable.subscribe(subscriber)

### Samples(同步)
            //1.展示图片
    //        showPicsComm(pics);
            showPicsRxJava(pics);

            //2.打印数组字符串
            printStr(str_names);

            //3.根据资源id展示图片
            showPicFromResId(drawableId);

### 线程控制Scheduler(调度器)

* subscribeOn()
    * 指定subscribe()所发生的的线程/事件产生的线程
* observeOn()
    * 指定Subscriber所运行的线程/事件消费的线程

> Schedulers.immediate() 直接在当前线程运行，相当于不指定线程。

> Schedulers.newThread() 总是启用新线程，并在新线程执行操作。

> Schedulers.io() 行为和newThread()差不多，区别在于io()的内部实现是使用一个
无数量上限的线程池，可以启用空闲线程。

> Schedulers.computation() 计算所使用的的Scheduler。

> Android有一个专门的AndroidSchedulers.mainThread(),它指定的操作将在Android主线程运行。







