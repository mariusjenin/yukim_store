package com.yukimstore.middleware;

import android.app.Activity;

import com.yukimstore.activity.unused.MiddlewareActivity;

public interface YukimMiddleware {
    boolean verify_and_redirect(MiddlewareActivity activity);
    void redirect(MiddlewareActivity activity);
}
