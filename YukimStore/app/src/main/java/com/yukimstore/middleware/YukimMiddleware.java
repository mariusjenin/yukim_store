package com.yukimstore.middleware;

import com.yukimstore.activity.abstract_activity.MiddlewareActivity;

public interface YukimMiddleware {
    boolean verify_and_redirect(MiddlewareActivity activity);
    void redirect(MiddlewareActivity activity);
}
