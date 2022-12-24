package com.example.myapplication.datamodel;

import com.google.gson.annotations.SerializedName;

public class LogoutResponse {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("espires_in")
    private Integer expiresIn;
    @SerializedName("refresh_expires_in")
    private Integer refreshExpiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("id_token")
    private String idToken;
    @SerializedName("not-before-policy")
    private Integer notBeforePolicy;
    @SerializedName("session_state")
    private String sessionState;
    @SerializedName("scope")
    private  String scope;

    public String getRefreshToken(){
        return this.refreshToken;
    }
}
