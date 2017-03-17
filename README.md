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
> TODO......

### 其他操作符

#### Creating 创建操作

> just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable

> from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable

> repeat( ) — 创建一个重复发射指定数据或数据序列的Observable

> repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据

> create( ) — 使用一个函数从头创建一个Observable

> defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable

> range( ) — 创建一个发射指定范围的整数序列的Observable

> interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable

> timer( ) — 创建一个在给定的延时之后发射单个数据的Observable

> empty( ) — 创建一个什么都不做直接通知完成的Observable

> error( ) — 创建一个什么都不做直接通知错误的Observable

> never( ) — 创建一个不发射任何数据的Observable

#### Creating Transforming 变换操作

> map( ) — 对序列的每一项都应用一个函数来变换Observable发射的数据序列

> flatMap( ), concatMap( ), and flatMapIterable( ) — 将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable

> switchMap( ) — 将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据

> scan( ) — 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值

> groupBy( ) — 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据

> buffer( ) — 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个

> window( ) — 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项

> cast( ) — 在发射之前强制将Observable发射的所有数据转换为指定类型

#### Filtering 过滤操作

> filter( ) — 过滤数据

> takeLast( ) — 只发射最后的N项数据

> last( ) — 只发射最后的一项数据

> lastOrDefault( ) — 只发射最后的一项数据，如果Observable为空就发射默认值

> takeLastBuffer( ) — 将最后的N项数据当做单个数据发射

> skip( ) — 跳过开始的N项数据

> skipLast( ) — 跳过最后的N项数据

> take( ) — 只发射开始的N项数据

> first( ) and takeFirst( ) — 只发射第一项数据，或者满足某种条件的第一项数据

> firstOrDefault( ) — 只发射第一项数据，如果Observable为空就发射默认值

> elementAt( ) — 发射第N项数据

> elementAtOrDefault( ) — 发射第N项数据，如果Observable数据少于N项就发射默认值

> sample( ) or throttleLast( ) — 定期发射Observable最近的数据

> throttleFirst( ) — 定期发射Observable发射的第一项数据

> throttleWithTimeout( ) or debounce( ) — 只有当Observable在指定的时间后还没有发射数据时，才发射一个数据

> timeout( ) — 如果在一个指定的时间段后还没发射数据，就发射一个异常

> distinct( ) — 过滤掉重复数据

> distinctUntilChanged( ) — 过滤掉连续重复的数据

> ofType( ) — 只发射指定类型的数据

> ignoreElements( ) — 丢弃所有的正常数据，只发射错误或完成通知

#### Combining 结合操作

> startWith( ) — 在数据序列的开头增加一项数据

> merge( ) — 将多个Observable合并为一个

> mergeDelayError( ) — 合并多个Observables，让没有错误的Observable都完成后再发射错误通知

> zip( ) — 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果

> and( ), then( ), and when( ) — (rxjava-joins) 通过模式和计划组合多个Observables发射的数据集合

> combineLatest( ) — 当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果

> join( ) and groupJoin( ) — 无论何时，如果一个Observable发射了一个数据项，只要在另一个Observable发射的数据项定义的时间窗口内，就将两个Observable发射的数据合并发射

> switchOnNext( ) — 将一个发射Observables的Observable转换成另一个Observable，后者发射这些Observables最近发射的数据

#### Error Handling 错误处理

> onErrorResumeNext( ) — 指示Observable在遇到错误时发射一个数据序列

> onErrorReturn( ) — 指示Observable在遇到错误时发射一个特定的数据

> onExceptionResumeNext( ) — instructs an Observable to continue emitting items after it encounters an exception (but not another variety of throwable)指示Observable遇到错误时继续发射数据

> retry( ) — 指示Observable遇到错误时重试

> retryWhen( ) — 指示Observable遇到错误时，将错误传递给另一个Observable来决定是否要重新给订阅这个Observable

#### Utility 辅助操作

> materialize( ) — 将Observable转换成一个通知列表convert an Observable into a list of Notifications

> dematerialize( ) — 将上面的结果逆转回一个Observable

> timestamp( ) — 给Observable发射的每个数据项添加一个时间戳

> serialize( ) — 强制Observable按次序发射数据并且要求功能是完好的

> cache( ) — 记住Observable发射的数据序列并发射相同的数据序列给后续的订阅者

> observeOn( ) — 指定观察者观察Observable的调度器

> subscribeOn( ) — 指定Observable执行任务的调度器

> doOnEach( ) — 注册一个动作，对Observable发射的每个数据项使用

> doOnCompleted( ) — 注册一个动作，对正常完成的Observable使用

> doOnError( ) — 注册一个动作，对发生错误的Observable使用

> doOnTerminate( ) — 注册一个动作，对完成的Observable使用，无论是否发生错误

> doOnSubscribe( ) — 注册一个动作，在观察者订阅时使用

> doOnUnsubscribe( ) — 注册一个动作，在观察者取消订阅时使用

> finallyDo( ) — 注册一个动作，在Observable完成时使用

> delay( ) — 延时发射Observable的结果

> delaySubscription( ) — 延时处理订阅请求

> timeInterval( ) — 定期发射数据

> using( ) — 创建一个只在Observable生命周期存在的资源

> single( ) — 强制返回单个数据，否则抛出异常

> singleOrDefault( ) — 如果Observable完成时返回了单个数据，就返回它，否则返回默认数据

> toFuture( ), toIterable( ), toList( ) — 将Observable转换为其它对象或数据结构

#### Conditional 条件和布尔操作

> amb( ) — 给定多个Observable，只让第一个发射数据的Observable发射全部数据

> defaultIfEmpty( ) — 发射来自原始Observable的数据，如果原始Observable没有发射数据，就发射一个默认数据

> (rxjava-computation-expressions) doWhile( ) — 发射原始Observable的数据序列，然后重复发射这个序列直到不满足这个条件为止

> (rxjava-computation-expressions) ifThen( ) — 只有当某个条件为真时才发射原始Observable的数据序列，否则发射一个空的或默认的序列

> skipUntil( ) — 丢弃原始Observable发射的数据，直到第二个Observable发射了一个数据，然后发射原始Observable的剩余数据

> skipWhile( ) — 丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发射原始Observable剩余的数据

> (rxjava-computation-expressions) switchCase( ) — 基于一个计算结果，发射一个指定Observable的数据序列

> takeUntil( ) — 发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知

> takeWhile( ) and takeWhileWithIndex( ) — 发射原始Observable的数据，直到一个特定的条件为真，然后跳过剩余的数据

> (rxjava-computation-expressions) whileDo( ) — 如果条件为true，则发射源Observable数据序列，并且只要条件保持为true就重复发射此数据序列

***
> all( ) — 判断是否所有的数据项都满足某个条件

> contains( ) — 判断Observable是否会发射一个指定的值

> exists( ) and isEmpty( ) — 判断Observable是否发射了一个值

> sequenceEqual( ) — 判断两个Observables发射的序列是否相等

#### Mathematical 算术和聚合操作

> averageInteger( ) — 求序列平均数并发射

> averageLong( ) — 求序列平均数并发射

> averageFloat( ) — 求序列平均数并发射

> averageDouble( ) — 求序列平均数并发射

> max( ) — 求序列最大值并发射

> maxBy( ) — 求最大key对应的值并发射

> min( ) — 求最小值并发射

> minBy( ) — 求最小Key对应的值并发射

> sumInteger( ) — 求和并发射

> sumLong( ) — 求和并发射

> sumFloat( ) — 求和并发射

> sumDouble( ) — 求和并发射

***

> concat( ) — 顺序连接多个Observables

> count( ) and countLong( ) — 计算数据项的个数并发射结果

> reduce( ) — 对序列使用reduce()函数并发射最终的结果

> collect( ) — 将原始Observable发射的数据放到一个单一的可变的数据结构中，然后返回一个发射这个数据结构的Observable

> toList( ) — 收集原始Observable发射的所有数据到一个列表，然后返回这个列表

> toSortedList( ) — 收集原始Observable发射的所有数据到一个有序列表，然后返回这个列表

> toMap( ) — 将序列数据转换为一个Map，Map的key是根据一个函数计算的

> toMultiMap( ) — 将序列数据转换为一个列表，同时也是一个Map，Map的key是根据一个函数计算的

#### Async 异步操作

> start( ) — 创建一个Observable，它发射一个函数的返回值

> toAsync( ) or asyncAction( ) or asyncFunc( ) — 将一个函数或者Action转换为已Observable，它执行这个函数并发射函数的返回值

> startFuture( ) — 将一个返回Future的函数转换为一个Observable，它发射Future的返回值

> deferFuture( ) — 将一个返回Observable的Future转换为一个Observable，但是并不尝试获取这个Future返回的Observable，直到有订阅者订阅它

> forEachFuture( ) — 传递Subscriber方法给一个Subscriber，但是同时表现得像一个Future一样阻塞直到它完成

> fromAction( ) — 将一个Action转换为Observable，当一个订阅者订阅时，它执行这个action并发射它的返回值

> fromCallable( ) — 将一个Callable转换为Observable，当一个订阅者订阅时，它执行这个Callable并发射Callable的返回值，或者发射异常

> fromRunnable( ) — convert a Runnable into an Observable that invokes the runable and emits its result when a Subscriber subscribes将一个Runnable转换为Observable，当一个订阅者订阅时，它执行这个Runnable并发射Runnable的返回值

> runAsync( ) — 返回一个StoppableObservable，它发射某个Scheduler上指定的Action生成的多个actions

#### Connect 连接操作

> ConnectableObservable.connect( ) — 指示一个可连接的Observable开始发射数据

> Observable.publish( ) — 将一个Observable转换为一个可连接的Observable

> Observable.replay( ) — 确保所有的订阅者看到相同的数据序列，即使它们在Observable开始发射数据之后才订阅

> ConnectableObservable.refCount( ) — 让一个可连接的Observable表现得像一个普通的Observable

#### Convert 转换操作

#### Blocking 阻塞操作

> forEach( ) — 对Observable发射的每一项数据调用一个方法，会阻塞直到Observable完成

> first( ) — 阻塞直到Observable发射了一个数据，然后返回第一项数据

> firstOrDefault( ) — 阻塞直到Observable发射了一个数据或者终止，返回第一项数据，或者返回默认值

> last( ) — 阻塞直到Observable终止，然后返回最后一项数据

> lastOrDefault( ) — 阻塞直到Observable终止，然后返回最后一项的数据，或者返回默认值

> mostRecent( ) — 返回一个总是返回Observable最近发射的数据的iterable

> next( ) — 返回一个Iterable，会阻塞直到Observable发射了另一个值，然后返回那个值

> latest( ) — 返回一个iterable，会阻塞直到或者除非Observable发射了一个iterable没有返回的值，然后返回这个值

> single( ) — 如果Observable终止时只发射了一个值，返回那个值，否则抛出异常

> singleOrDefault( ) — 如果Observable终止时只发射了一个值，返回那个值，否则否好默认值

> toFuture( ) — 将Observable转换为一个Future

> toIterable( ) — 将一个发射数据序列的Observable转换为一个Iterable

> getIterator( ) — 将一个发射数据序列的Observable转换为一个Iterator

#### String 字符串操作

> byLine( ) — 将一个字符串的Observable转换为一个行序列的Observable，这个Observable将原来的序列当做流处理，然后按换行符分割

> decode( ) — 将一个多字节的字符流转换为一个Observable，它按字符边界发射字节数组

> encode( ) — 对一个发射字符串的Observable执行变换操作，变换后的Observable发射一个在原始字符串中表示多字节字符边界的字节数组

> from( ) — 将一个字符流或者Reader转换为一个发射字节数组或者字符串的Observable

> join( ) — 将一个发射字符串序列的Observable转换为一个发射单个字符串的Observable，后者用一个指定的字符串连接所有的字符串

> split( ) — 将一个发射字符串的Observable转换为另一个发射字符串的Observable，后者使用一个指定的正则表达式边界分割前者发射的所有字符串

> stringConcat( ) — 将一个发射字符串序列的Observable转换为一个发射单个字符串的Observable，后者连接前者发射的所有字符串
























