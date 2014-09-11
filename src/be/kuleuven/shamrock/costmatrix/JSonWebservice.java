package be.kuleuven.shamrock.costmatrix;

import javax.json.*;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Joris on 22/08/2014.
 */
public class JSonWebservice {

    static class KeyValuePair {
        private String key;
        private String value;

        public KeyValuePair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    // Contains code from http://www.mkyong.com/java/java-httpurlconnection-follow-redirect-example/
    static JsonStructure callService(boolean rootedArray, String serverUrl, String command, KeyValuePair ... URLParameters) {
        try {
            String call = serverUrl + "/" + command + "?";
            boolean first = true;
            for (KeyValuePair pair : URLParameters) {
                call += ((first? "" : "&") + pair);
                first = false;
            }
            //System.out.println(call);
            URL obj = new URL(call);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
            //conn.addRequestProperty("Referer", "google.com");

            //System.out.println("Request URL ... " );

            boolean redirect = false;

            // normally, 3xx is redirect
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                if (status == HttpURLConnection.HTTP_MOVED_TEMP
                        || status == HttpURLConnection.HTTP_MOVED_PERM
                        || status == HttpURLConnection.HTTP_SEE_OTHER)
                    redirect = true;
            }

            //System.out.println("Response Code ... " + status);

            if (redirect) {

                // get redirect url from "location" header field
                String newUrl = conn.getHeaderField("Location");

                // get the cookie if need, for login
                String cookies = conn.getHeaderField("Set-Cookie");

                // open the new connnection again
                conn = (HttpURLConnection) new URL(newUrl).openConnection();
                conn.setRequestProperty("Cookie", cookies);
                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn.addRequestProperty("User-Agent", "Mozilla");
                conn.addRequestProperty("Referer", "google.com");

                //System.out.println("Redirect to URL : " + newUrl);

            }
            InputStream is = conn.getInputStream();//url.openStream();
            if (is.available() != 0) {
                /*java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
                System.out.println(s.hasNext() ? s.next() : "");*/

                JsonReader rdr = Json.createReader(is);
                if (rootedArray) {
                    JsonArray arr = rdr.readArray();
                    rdr.close();
                    return arr;
                } else {
                    JsonObject jobj = rdr.readObject();
                    rdr.close();
                    return jobj;
                }
            } else {
                is.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Verbindingsprobleem. Sluit de toepassing.");
        }
        return null;
    }

}
