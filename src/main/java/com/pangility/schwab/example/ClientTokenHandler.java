package com.pangility.schwab.example;

import com.pangility.schwab.api.client.oauth2.SchwabAccount;
import com.pangility.schwab.api.client.oauth2.SchwabTokenHandler;

public class ClientTokenHandler implements SchwabTokenHandler {
    @Override
    public void onAccessTokenChange(SchwabAccount schwabAccount) {
        // ...Do something with the Access Token here
    }

    @Override
    public void onRefreshTokenChange(SchwabAccount schwabAccount) {
        // ...Do something with the Refresh Token here
        System.out.print(schwabAccount.getRefreshToken());
        System.out.print(" expires on ");
        System.out.println(schwabAccount.getRefreshExpiration());
    }
}
