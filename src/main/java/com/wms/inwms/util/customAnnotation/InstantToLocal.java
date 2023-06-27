package com.wms.inwms.util.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : com.wms.inwms.util.customAnnotation
 * fileName       : InstantToLocal
 * author         : akfur
 * date           : 2023-06-19
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InstantToLocal {
}
