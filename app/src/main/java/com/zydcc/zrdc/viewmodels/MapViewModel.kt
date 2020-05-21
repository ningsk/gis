package com.zydcc.zrdc.viewmodels

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.esri.arcgisruntime.geometry.GeometryEngine
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.zydcc.zrdc.R
import com.zydcc.zrdc.data.CodeBrush
import com.zydcc.zrdc.data.CodeBrushRepository
import com.zydcc.zrdc.interfaces.MapOperate
import com.zydcc.zrdc.model.bean.LocationData
import com.zydcc.zrdc.utilities.PositionUtil
import com.zydcc.zrdc.view.MainFragmentDirections
import kotlinx.android.synthetic.main.fragment_map.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * =======================================
 *
 * Create by ningsikai 2020/5/20:11:00 AM
 * ========================================
 */
class MapViewModel internal constructor(
    repository: CodeBrushRepository,
    view: MapOperate,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var mView = view

    // 图层编号 0 默认影像图 1 谷歌影像图
    var layerCode = 0
    // 当前坐标
    var currentPt: Point? = null

    // 重置主操作栏状态
    var resetMainOperate = ObservableBoolean(false)
    // 重置次操作栏状态
    var clearSubOperateChecked = ObservableBoolean(false)
    // 选择操作栏状态
    var chooseOperateIsGone = ObservableBoolean(true)
    // 绘制操作栏状态
    var drawOperateIsGone = ObservableBoolean(true)
    // 工具状态栏状态
    var toolOperateIsGone = ObservableBoolean(true)





    // 百度回调监听
    var bdListener = MyBDLocationListener()

    var locationData = LocationData(ObservableField(""), ObservableField(""), ObservableField(""), ObservableField(""))


    var onLocationCallback: (Point) -> Unit =  {}

    var onErrorCallback: (String) -> Unit = {}


    inner class MyBDLocationListener : BDAbstractLocationListener() {

        override fun onReceiveLocation(bdLocation: BDLocation?) {
            val df = DecimalFormat("0.000")
            df.roundingMode = RoundingMode.HALF_UP
            if (bdLocation != null && bdLocation.locType != BDLocation.TypeServerError) {
                // 获取纬度信息
                val latitude = bdLocation.latitude
                // 获取经度信息
                val longitude = bdLocation.longitude
                val gps = PositionUtil.gcj_To_Gps84(latitude, longitude)
                val d1 = gps.wgLon
                val d2 = gps.wgLat

                val point =
                    when(layerCode) {
                        0 -> {
                            locationData.latitude.set(df.format(d2))
                            locationData.longitude.set(df.format(d1))
                            Point(d1, d2, SpatialReferences.getWgs84())
                        }
                        1 -> {
                            locationData.latitude.set(df.format(latitude))
                            locationData.longitude.set(df.format(longitude))
                            Point(longitude, latitude, SpatialReferences.getWgs84())
                        }
                        else -> null
                    }
                val p = GeometryEngine.project(point, SpatialReferences.getWebMercator())
                currentPt = p.extent.center
                currentPt?.let {
                    // 定位
                    locationData.x.set(df.format(it.x))
                    locationData.y.set(df.format(it.y))
                    onLocationCallback.invoke(it)
                }
            } else {
                // Error
                onErrorCallback.invoke("定位出错，请检查定位权限是否授予")
            }

        }

    }

    fun extend() {
        mView.onLocation()
    }

    fun click(view: View) {
        when (view.id) {

            R.id.rb_code_brush -> {

                mView.showCodeBrushList(codeBrushList)
            }

            R.id.rb_all_map -> {
                resetSubOperate()
                resetMainOperate.set(true)
            }

            R.id.rb_choose -> {
                resetSubOperate()
                chooseOperateIsGone.set(false)

            }
            R.id.rb_draw -> {
                resetSubOperate()
                drawOperateIsGone.set(false)

            }
            R.id.rb_tools -> {;
                resetSubOperate()
                toolOperateIsGone.set(false)
            }
            R.id.rb_confirm_info -> {
                resetSubOperate()

            }
        }
    }

    private fun resetSubOperate() {
        // 先重置
        clearSubOperateChecked.set(false)
        chooseOperateIsGone.set(true)
        drawOperateIsGone.set(true)
        toolOperateIsGone.set(true)
        resetMainOperate.set(false)
        clearSubOperateChecked.set(true)
    }

    val codeBrushList: LiveData<List<CodeBrush>> = repository.getCodeBrushList()

}