package com.example.todoapprst.service.security;

public class SecurityConstant {

    public static final String SIGN_IN_URLS = "/auth/*";
    public static final String SECRET = "SecretKeyGenJWT";
    public static final long EXPIRATION_TIME = 1_800_000; //30min

}
