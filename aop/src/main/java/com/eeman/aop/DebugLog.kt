package com.eeman.aop

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@Retention(RetentionPolicy.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class DebugLog

@Aspect
class MyAspect {
    init {
        Log.d(TAG, "my aspect login: ")
    }

    // 切点
    @Pointcut(POINTCUT)
    fun debugLog() {
        Log.d(TAG, "debugLog: ")
    }

    @Before("debugLog()")
    fun beforeDebugLog(joinPoint: JoinPoint) {
        try {
            val className = joinPoint.getThis().javaClass.simpleName
            val methodName = joinPoint.signature.name
            val signature = joinPoint.signature as MethodSignature
            val method = signature.method
            val annotation = method.getAnnotation(DebugLog::class.java)
            if (annotation == null) {
                Log.e(TAG, "before: annotation == null")
            } else {
                Log.i(TAG, "Method invoked: $className.$methodName")
            }
        } catch (e: Throwable) {
            Log.e(TAG, "beforeDebugLog: failed on error: ", e)
        }
    }

    companion object {
        private val TAG = MyAspect::class.java.simpleName
        const val POINTCUT = "execution(@com.eeman.aop.DebugLog * *(..))"
    }
}