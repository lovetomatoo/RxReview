# RxReview
##RxJavaSamples


###扩展的观察者模式
    rxjava的异步实现，是通过一种扩展的观察者设计模式来实现的。

###rxjava的3个基本概念
    1.Observable    被观察者
    2.Observer/Subscriber      观察者
    3.subscribe     订阅
        Observable和Observer通过subscribe()方法实现订阅关系，从而Observable
    可以在需要的时候发出事件来通知Observer。

###基本实现
    1.创建Observer
        Observer即观察者，它决定事件触发的时候将有怎样的行为。

    2.创建Observeable
        .creat()    .from()     .just()

    3.Subscribe(订阅)
        observable.subscribe(observer)
        或observable.subscribe(subscriber)

###




