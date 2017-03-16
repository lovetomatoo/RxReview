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
#### 1.API
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

#### 2.原理


### 变换

所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。

#### 1.API

* map()

* flatMap()

map()和flatMap()的

> 共同点：把传入的参数转换之后返回另一个对象

> 不同点：flatMap()返回的是个Observable对象，并且这个Observable对象并不是
被直接发送到Subscriber的回调方法中。

flatMap()的原理：

> 1.使用传入的事件对象创建一个Observable对象

> 2.并不发送这个Observable，而是将它激活，于是它开始发送事件

> 3.每一个创建出来的Observable对象发送的事件都被汇入同一个Observable，而这个
Observable负责将这些事件统一交给Subscriber的回调方法。

> 这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。

#### 2.原理
##### lift()
> 这些变换功能各有不同，但实质上都是针对事件序列的处理和再发送。再rxjava的内部，
他们是基于同一个基础的变换方法：lift(Operator)。

> TODO......这里，待理解

#### 3.compose: 对 Observable 整体的变换
>















