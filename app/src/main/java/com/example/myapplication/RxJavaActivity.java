package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);


        try {
            Observable<Anything> anythingObservable = Observable
                    .fromIterable(DataSource.createAnythingList())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            anythingObservable.subscribe(new Observer<Anything>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    makeToast("onSubscribe" + Thread.currentThread().getName());
                }

                @Override
                public void onNext(@NonNull Anything anything) {
                    makeToast("onNext" + Thread.currentThread().getName());
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    makeToast("onError" + Thread.currentThread().getName());
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete: ");
                }
            });
        }
        catch (Exception e) {
        makeToast(e.getMessage());

        }
    }






    public void makeToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

}
