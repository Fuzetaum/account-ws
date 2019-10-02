package com.revolut.test.backend.accountWs.service;

import io.javalin.http.Context;

public interface JavalinServiceWrapper {
    Object handle(Context ctx);
}
