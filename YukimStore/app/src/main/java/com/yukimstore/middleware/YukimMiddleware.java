package com.yukimstore.middleware;

import android.app.Activity;

public interface YukimMiddleware {
    boolean verify_and_redirect(Activity activity);
    void redirect(Activity activity);
}
