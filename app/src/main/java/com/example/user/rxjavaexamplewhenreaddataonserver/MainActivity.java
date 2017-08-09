package com.example.user.rxjavaexamplewhenreaddataonserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    SimpleViewAdapter adapter;
    ProgressBar progressBar;
    SongClient songClient;
    Subscription songSubscription;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.activity_recyclerview);
        progressBar=(ProgressBar) findViewById(R.id.activity_progressbar);
        songClient=new SongClient();
        adapter=new SimpleViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        createObservable2();
        //createObservable();
    }

    private void createObservable(){
        Observable<List<String>> listObservable=Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return songClient.getSongs();
            }
        });

        songSubscription=listObservable//1 observable
                .subscribeOn(Schedulers.io())//move the obserable to a different thread
                .observeOn(AndroidSchedulers.mainThread())//allows us to observe/manuplate values on a different thread
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        adapter.setmStringList(strings);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                });
    }

    //To understand map
    //map to manuplate data that come from server than show to the user
    private void createObservable2(){
        Observable<List<String>> listObservable=Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return songClient.getSongs();
            }
        });

        songSubscription=listObservable//1 observable
                .subscribeOn(Schedulers.io())//move the obserable to a different thread
                .map(new Func1<List<String>, List<String>>() {
                    @Override
                    public List<String> call(List<String> strings) {
                        List<String> newList=new ArrayList<String>();
                        String newString;
                        int index=0;
                        for(String string:strings){
                            newString=string+" this is song number "+index;
                            newList.add(newString);
                            index++;
                        }
                        return newList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//allows us to observe/manuplate values on a different thread
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        adapter.setmStringList(strings);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                });
    }
}


