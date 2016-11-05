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
        for (int i = 0; i < 10000; i++) {
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
        String postData = "post string";
        String url = "http://223.3.92.84:8081/API/MailSample.action?app_id=test_user&app_secret=test_secret";
        //String url = "http://www.wonderoc.com/";
        //String url = "http://localhost:8080";
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
                sb.append(line);
            }
            br.close();
            System.out.println(Thread.currentThread().getId()+": " + sb.toString());
        } else {
            System.out.println(con.getResponseMessage());
        }
    }

}
