package com.example.kotlinapp.utils.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class RxJavaExample {
    fun observableJust1() {
        val developers: Observable<String> = Observable.just(
            "IOS",
            "Android",
            "Flutter"
        )
        developers.doAfterNext {
            Log.w("next", it)
        }.doOnError {
            //here will be excption if thrown
        }.doOnComplete {
            Log.w("completed", "finish")
        }.subscribe()


        val developersAnotherWay: Observable<String> = Observable.just(
            "IOS",
            "Android",
            "Flutter"
        )
        developersAnotherWay.subscribe(
            {
                Log.w("next", it)
            }, {
                //here will be excption if thrown
            }, {
                Log.w("completed", "finish")
            }
        )
    }

        fun createObs(devlist:List<String>) {

            Observable.create<String> { emitter ->
                devlist.forEach { developer ->
                    if (developer.isEmpty()) {
                        emitter.onError(Exception("value is empty"))
                    }
                    emitter.onNext(developer)
                }
            }.doAfterNext {
                Log.w("next", it)
            }.doOnComplete {
                Log.w("complete", "finished")
            }.subscribe({}, { Log.w("error", it.message.toString()) })
        }



    fun observableFlatMap(){
        Observable.just("IOS", "Android", "Flutter")
            .subscribeOn(Schedulers.io())
            .flatMap{
                Observable.just("$it 2")
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.w("result", it.toString())
            }
    }

    fun observableZip(){
        Observable.zip(
            Observable.just("IOS", "Android", "Flutter"),
            Observable.just("Swift", "Kotlin", "Dart")
        ){
                dev, lang ->
            "$dev writes in $lang"
        }.subscribe{ Log.w("result zip", it) }


    }
    fun observableConcat(){
        val devs = Observable.just("IOS", "Android", "Flutter")
        val langs = Observable.just("Swift", "Kotlin", "Dart")
        val comps = Observable.just("Apple", "Google")
        Observable.concat(devs, langs, comps)
            .subscribe{ Log.w("result concat", it) }
    }

    fun publishSubject(){
        val publishSubject = PublishSubject.create<Int>()
        publishSubject.onNext(1)
        publishSubject.onNext(2)
        publishSubject.onNext(3)
        publishSubject.subscribe{Log.w("publish value1", it.toString())}
        publishSubject.onNext(4)
        publishSubject.onNext(5)
        publishSubject.subscribe{Log.w("publish value2", it.toString())}
    }
    fun replaySubject(){
        val replaySubject = ReplaySubject.create<Int>()
        replaySubject.onNext(1)
        replaySubject.onNext(2)
        replaySubject.onNext(3)
        replaySubject.subscribe{Log.w("Early", it.toString())}
        replaySubject.onNext(4)
        replaySubject.onNext(5)
        replaySubject.subscribe{Log.w("Later", it.toString())}
    }
    fun behaviorSubject(){
        val behaviorSubject = BehaviorSubject.create<Int>()
        behaviorSubject.onNext(1)
        behaviorSubject.onNext(2)
        behaviorSubject.onNext(3)
        behaviorSubject.subscribe{Log.w("behavior value1", it.toString())}
        behaviorSubject.onNext(4)
        behaviorSubject.subscribe{Log.w("behavior value2", it.toString())}
        behaviorSubject.onNext(5)
    }
    fun asyncSubject(){
        val asyncSubject = AsyncSubject.create<Int>()
        asyncSubject.subscribe{Log.w("async value", it.toString())}
        asyncSubject.onNext(1)
        asyncSubject.onNext(2)
        asyncSubject.onNext(3)
        asyncSubject.onNext(4)
        asyncSubject.onNext(5)
        asyncSubject.onComplete()
    }
}
