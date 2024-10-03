package org.checkerframework.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@UnitsMultiple(prefix = Prefix.kilo, quantity = InterfaceC3018g.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({Mass.class})
/* renamed from: org.checkerframework.checker.units.qual.kg */
/* loaded from: classes3.dex */
public @interface InterfaceC3020kg {
}
