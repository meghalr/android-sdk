package com.loginradius.sdk.api;


import java.util.HashMap;
import java.util.Map;

import com.loginradius.sdk.models.lrAccessToken;
import com.loginradius.sdk.models.userprofile.LoginRadiusUltimateUserProfile;
import com.loginradius.sdk.util.AsyncHandler;
import com.loginradius.sdk.util.JsonDeserializer;
import com.loginradius.sdk.util.RestRequest;

/**
 * Used to retrieve social profile data from the user's social account after authentication.
 * The social profile will be retrieved via oAuth and OpenID protocols.
 * The data is normalized into LoginRadius' standard data format.
 *
 */
public class UserProfileAPI 
{
   private static final String API_V2_USERPROFILE = "/api/v2/userprofile";
   /**
    * Retrieves User Profile details
    * @param token Authentication token from LoginRadius
    * @param handler Used to handle the success and failure events
    */
	public void getResponse(lrAccessToken token,final AsyncHandler<LoginRadiusUltimateUserProfile> handler) {


		Map<String, String> params = new HashMap<String, String>();
	    params.put("access_token", token.access_token);
        RestRequest.get(API_V2_USERPROFILE,params,new AsyncHandler<String>()
       {
		@Override
		public void onSuccess(String response) {
			LoginRadiusUltimateUserProfile userprofile = JsonDeserializer.deserializeJson(response,LoginRadiusUltimateUserProfile.class);
			 handler.onSuccess(userprofile);
		}

		@Override
		public void onFailure(Throwable error, String response) {
			handler.onFailure(error, response);
		}
		});
	}
}
