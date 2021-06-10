package org.adaschool.instrumentaltesting

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

/**
 * @author Santiago Carrillo
 * 10/06/21.
 */
object TestsUtils {

    fun getResourceString(@StringRes id: Int): String {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }
}