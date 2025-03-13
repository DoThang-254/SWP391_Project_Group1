package Dal;

import Model.GoogleAccount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 * @author thang
 */
public class GoogleAccountDao {

    private static final String GOOGLE_CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");
    private static final String GOOGLE_CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    private static final String GOOGLE_REDIRECT_URI = "http://localhost:9999/SWP391_Group1/login";
    private static final String GOOGLE_GRANT_TYPE = "authorization_code";
    private static final String GOOGLE_LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";
    private static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public String getToken(String code) throws IOException {
        if (GOOGLE_CLIENT_ID == null || GOOGLE_CLIENT_SECRET == null) {
            throw new IllegalStateException("⚠ Google Client ID hoặc Secret không được đặt trong biến môi trường!");
        }

        try {
            System.out.println("GOOGLE_CLIENT_ID: " + GOOGLE_CLIENT_ID);

            String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                    .setHeader("Content-Type", "application/x-www-form-urlencoded")
                    .bodyForm(Form.form()
                            .add("client_id", GOOGLE_CLIENT_ID)
                            .add("client_secret", GOOGLE_CLIENT_SECRET)
                            .add("redirect_uri", GOOGLE_REDIRECT_URI)
                            .add("code", code)
                            .add("grant_type", GOOGLE_GRANT_TYPE)
                            .build())
                    .execute()
                    .returnContent()
                    .asString();

            JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
            return jobj.get("access_token").getAsString();
        } catch (Exception e) {
            throw new IOException("⚠ Lỗi khi gọi API Google: " + e.getMessage());
        }
    }

    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        if (response == null || response.isEmpty()) {
            throw new IOException("⚠ Google user info response is empty!");
        }

        return new Gson().fromJson(response, GoogleAccount.class);
    }
}
