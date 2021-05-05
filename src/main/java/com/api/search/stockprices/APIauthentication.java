package com.api.search.stockprices;


import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.logging.Logger;

@Singleton
public class APIauthentication implements AuthenticationProvider {
    private static final Logger LOG = Logger.getLogger(APIauthentication.class.getName());

    @Override
    public org.reactivestreams.Publisher<AuthenticationResponse> authenticate(io.micronaut.http.HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter -> {
            if (authenticationRequest.getIdentity().equals("myYahoo") &&
                    authenticationRequest.getSecret().equals("myYahoo")) {
                LOG.info("API REQUEST AUTHENTICATED");
                emitter.onNext(new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>()));
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);
    }
}
