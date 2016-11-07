import com.roc.core.Utils.FileUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rocwu on 2016/10/27.
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 250; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        TestMain.test();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    private static void test() throws Exception {
        String postData = FileUtil.readFile(TestMain.class.getClassLoader().getResource("").getPath()+"/input.txt");
        String url = "http://223.3.92.84:8081/API/check.action?app_id=test_user&app_secret=test_secret&hospital=WHTJ";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/raw");
        con.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(postData);
        wr.flush();

        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while (null != (line = br.readLine())) {
                sb.append(line).append("\n");
            }
            br.close();
            System.out.println(Thread.currentThread().getId()+": " + sb.toString());
        } else {
            System.out.println(con.getResponseMessage());
        }
    }

}