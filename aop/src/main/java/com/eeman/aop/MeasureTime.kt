package com.eeman.aop

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class MeasureTime(
    val tag: String = "",
    val warnAboveMs: Long = 16,
    val logArgs: Boolean = false
)

@Aspect
class PerformanceAspect {
    @Around("execution(@com.eeman.aop.MeasureTime * *(..)) && @annotation(measureTime)")
    fun measure(joinPoint: ProceedingJoinPoint, measureTime: MeasureTime): Any? {
        Log.d("compTest", "inside PerformanceAspect measure: ")
        val start = System.nanoTime()
        val result: Any?
        try {
            result = joinPoint.proceed()
        } finally {
            val durationMs = (System.nanoTime() - start) / 1_000_000
            val methodName = joinPoint.signature.toShortString()
            val tag = measureTime.tag.ifBlank { "PERF" }
            if (measureTime.logArgs) {
                Log.d(tag, "Args: ${joinPoint.args.joinToString()}")
            }
            if (durationMs > measureTime.warnAboveMs) {
                Log.w(tag, "SLOW: $methodName took ${durationMs}ms")
            } else {
                Log.d(tag, "$methodName took ${durationMs}ms")
            }
        }
        return result
    }
}