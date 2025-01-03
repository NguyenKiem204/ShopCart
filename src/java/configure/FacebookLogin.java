/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configure;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.IconstantFacebook;
import entity.AccountFacebook;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author nkiem
 */
public class FacebookLogin {

    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(IconstantFacebook.FACEBOOK_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", IconstantFacebook.FACEBOOK_CLIENT_ID)
                                .add("client_secret", IconstantFacebook.FACEBOOK_CLIENT_SECRET)
                                .add("redirect_uri", IconstantFacebook.FACEBOOK_REDIRECT_URI)
                                .add("code", code)
                                .build()
                )
                .execute()
                .returnContent()
                .asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static AccountFacebook getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = IconstantFacebook.FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        AccountFacebook account = new Gson().fromJson(response, AccountFacebook.class);

        // Lấy URL của ảnh
        String pictureUrl = account.getPictureUrl();

        return account;
    }

}
