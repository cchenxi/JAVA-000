package io.github.cchenxi.w2.nio.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 客户端 调用 http://127.0.0.1:8808/test
 * Date: 2020-10-26
 *
 * @author chenxi
 */
public class HttpClient {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:8808/test")
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            System.out.println(body.string());
        }
    }
}
