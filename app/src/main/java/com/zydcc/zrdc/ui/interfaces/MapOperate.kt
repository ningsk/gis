package com.zydcc.zrdc.ui.interfaces

import com.zydcc.zrdc.base.BaseView

/**
 * =======================================
 *
 * Create by ningsikai 2020/5/20:4:52 PM
 * ========================================
 */
interface MapOperate : BaseView {


    // 跳转到媒体页面
    fun navToMedia()

    // 调转到截图管理页面
    fun navToScreenshotManager()

    // 弹出绘制图层
    fun drawLayerDialog()

    // 弹出工程管理
    fun projectManagerDialog()



    // 定位到当前位置
    fun onLocation()

    // 全图
    fun fullMap()

    // 测距
    fun measureDistance()

    // 测面积
    fun measureArea()

    // 取消测量
    fun confirmHide()



}