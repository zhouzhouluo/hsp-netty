package com.cmcc.hsp.netty.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface STBRequestMapping {

    String value();

}
