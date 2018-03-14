package logan.dl.com.myapplication.other.utils;

/**
 * Created by zhjzhang on 3/14/18.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
