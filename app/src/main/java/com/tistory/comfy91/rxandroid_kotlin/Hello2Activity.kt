package com.tistory.comfy91.rxandroid_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_hello2.*

class Hello2Activity : AppCompatActivity() {
    private val TAG = Hello2Activity::class.simpleName

    private lateinit var mDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello2)

        Observable.create<String>{
            it.onNext("Hello World!")
            it.onComplete()
        }.subscribe{o -> textView.text = o}

        // just 사용
//        Observable.just("Hello World!")
//            .subscribe(textView::setText)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!mDisposable.isDisposed){
            mDisposable.dispose()
        }
    }
}
