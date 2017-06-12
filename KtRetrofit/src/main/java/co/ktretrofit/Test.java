package co.ktretrofit;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import rx.functions.Action1;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/6/6.
 */
public class Test {
    public static void main(String args[]){
        test();
    }
    static String []names={"dd","sdf","xxx"};
    public static void test(){



        Observable.fromArray(names)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.e("ssss",value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
