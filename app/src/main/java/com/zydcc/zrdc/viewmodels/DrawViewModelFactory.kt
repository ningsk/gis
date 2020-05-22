package com.zydcc.zrdc.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * =======================================
 *
 * Create by ningsikai 2020/5/22:8:34 AM
 * ========================================
 */
class DrawViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrawViewModel() as T
    }

}