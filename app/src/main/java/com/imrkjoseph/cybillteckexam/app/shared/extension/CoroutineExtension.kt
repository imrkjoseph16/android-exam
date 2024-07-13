package com.imrkjoseph.cybillteckexam.app.shared.extension

import java.util.concurrent.CancellationException

@Suppress("RedundantSuspendModifier")
suspend inline fun <T, R> T.coRunCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}