package com.tistory.comfy91.rxandroid_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_main.*

/**
 * HelloWorld 출력
 */
class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.simpleName

    private lateinit var mDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DisposableObserver 인터페이스 이용하여 observer 생성
        val observer: DisposableObserver<String> = object : DisposableObserver<String>() {
            override fun onNext(s: String) {
                // 가장 마지막으로 발행된 String 값을 이용하여 TextView 업데이트
                text_view.text = s
            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }

        mDisposable = Observable.create(object: ObservableOnSubscribe<String>{
            override fun subscribe(e: ObservableEmitter<String>) {
                // Hello World 문자열 발행하고 종료하는 Observable
                e.onNext("Hello World");
                e.onComplete()
            }
        }).subscribeWith(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!mDisposable.isDisposed){
            mDisposable.dispose()
        }
    }
}
