//: net/mindview/atunit/Test.java
// The @Test tag.
package com.java.atunit;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {} ///:~
