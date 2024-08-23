package com.pangility.schwab.example;

import com.pangility.schwab.api.client.oauth2.SchwabAccount;
import com.pangility.schwab.api.client.oauth2.SchwabTokenHandler;
import lombok.NonNull;

public class ClientTokenHandler implements SchwabTokenHandler {
    @Override
    public void onAccessTokenChange(@NonNull SchwabAccount schwabAccount) {
        // ...Do something with the Access Token here
    }

    @Override
    public void onRefreshTokenChange(@NonNull SchwabAccount schwabAccount) {
        // ...Do something with the Refresh Token here
        System.out.print(schwabAccount.getRefreshToken());
        System.out.print(" expires on ");
        System.out.println(schwabAccount.getRefreshExpiration());
    }
}
