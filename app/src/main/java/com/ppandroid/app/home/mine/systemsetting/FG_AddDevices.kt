/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.systemsetting

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.google.gson.Gson
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Base
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.systemsetting.BN_AddDeviceDetail
import com.ppandroid.app.bean.mine.systemsetting.ET_SyStemSetting
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.Http.get
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.BitmapUtils
import com.ppandroid.app.utils.Utils_Bitmap
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.utils.Utils_Dialog
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_add_devices.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.forEachChild
import org.jetbrains.anko.uiThread
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by yeqinfu on 2017/8/28.
 * 新增设备
 */
class FG_AddDevices : FG_Base() {
    var operatorId: String = ""
    /**page type default:0 添加设备 1 修改设备*/
    private var pageType = "0"

    companion object {
        fun createBundle(operatorId: String): Bundle {
            var b = Bundle()
            b.putString("operatorId", operatorId)
            b.putString("pageType", "1")
            return b
        }
    }


    override fun fgRes(): Int = R.layout.fg_add_devices
    override fun afterViews() {
        arguments?.let {
            operatorId = it.getString("operatorId", "")
            pageType = it.getString("pageType", "0")
        }
        if (pageType == "1") {
            loadDeviceDetail()
        }

        isNeedEventBus = true
        head_view.init(activity)
        head_view.setRightText("保存") {
            postInfo()
        }
        relAddPhoto.setOnClickListener {
            showChooseDialog()
        }
        deleteImg.setOnClickListener {
            bitmap = null
            isModified=false
            myLayout.visibility = View.GONE
            relAddPhoto.visibility = View.VISIBLE
        }

        ll_add_properties.setOnClickListener {
            lv_content.addView(addPropertiesViews())
        }
        ll_about_cate.setOnClickListener {
            //添加关联分项
            startAC(FG_AboutCate::class.java.name)
        }
        ll_about_instrument.setOnClickListener {
            //添加关联仪表
            startAC(FG_AboutInstrument::class.java.name)
        }
        ll_about_area.setOnClickListener {
            //添加关联区域
            startAC(FG_AboutArea::class.java.name)

        }
    }

    private fun loadDeviceDetail() {
        var url = "user/sysSet/device/details.json?id=$operatorId"
        get(activity, url, BN_AddDeviceDetail::class.java, object : MyCallBack<BN_AddDeviceDetail> {
            override fun onResponse(response: BN_AddDeviceDetail?) {
                response?.let {
                    et_name.setText(it.message.name)
                    et_model.setText(it.message.model)
                    it.message.photo?.let {
                        GlideUtils.displayImage(activity, it, userImg)
                        userImg.isDrawingCacheEnabled = true
                        myLayout.visibility = View.VISIBLE
                        relAddPhoto.visibility = View.GONE
                    }

                    tv_cate.text = it.message.deviceCateName
                    tv_instrument.text = it.message.instrumentName
                    tv_area.text = it.message.deviceAreaName
                    chooseCateId = it.message.deviceCateId.toString()
                    chooseAreaId = it.message.deviceAreaId.toString()
                    chooseInstrumentId = it.message.instrumentId.toString()




                    var modelBody=Utils_Common.parseJson(it.message.propertiesJson,ModelBody::class.java)
                    modelBody?.list?.forEach { item->
                        lv_content.addView(addViewByItem(item))
                    }
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })


    }

    private fun addViewByItem(item: Model): View? {
        var view = addPropertiesViews()
        var et_key=view?.find<EditText>(R.id.et_key)
        et_key?.setText(item.key)
        var et_value=view?.find<EditText>(R.id.et_value)
        et_value?.setText(item.value)
        return view
    }

    private fun addPropertiesViews(): View? {
        var view = activity.layoutInflater.inflate(R.layout.item_add_properties_view, null)
        view.find<ImageView>(R.id.iv_del).setOnClickListener {
            lv_content.removeView(view)
        }
        return view
    }

    class Model {
        var key: String? = null
        var value: String? = null
    }

    class ModelBody{
        var list:ArrayList<Model>?=null
    }

    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text)) {
            et_name.error = "请输入名称"
            toast("请输入名称")
            return
        }
        if (TextUtils.isEmpty(et_model.text)) {
            et_model.error = "请输入型号"
            toast("请输入型号")
            return
        }

        if (bitmap == null) {
            if (isModified){
                bitmap = userImg.drawingCache
            }
            if (bitmap == null){
                toast("请至少添加一张图片")
            }
            return
        }

        var list = ArrayList<Model>()
        lv_content.forEachChild { childView ->

            var et_key = childView.find<EditText>(R.id.et_key)
            var et_value = childView.find<EditText>(R.id.et_value)
            if (et_key.text.isEmpty() || et_value.text.isEmpty()) {
                toast("属性未完善")
                return
            }
            var model = Model()
            model.key = et_key.text.toString()
            model.value = et_value.text.toString()
            list.add(model)
        }

        if (TextUtils.isEmpty(chooseCateId)) {
            toast("请选择关联分项")
            return
        }
      /*  if (TextUtils.isEmpty(chooseInstrumentId)) {
            toast("请选择关联仪表")
            return
        }*/
        if (TextUtils.isEmpty(chooseAreaId)) {
            toast("请选择关联区域")
            return
        }


        var gson = Gson()
        var modelBody=ModelBody()
        modelBody.list=list
        Utils_Dialog.showLoading(activity)
        var url = "user/sysSet/device/add.json"
        async {
            var map = TreeMap<String, String>()
            map.apply {
                if (pageType == "1") {
                    put("id", operatorId)
                }
                put("name", et_name.text.toString())
                put("model", et_model.text.toString())
                put("deviceAreaId", chooseAreaId)
                put("deviceCateId", chooseCateId)
                if (!TextUtils.isEmpty(chooseInstrumentId)) {
                    put("instrumentId", chooseInstrumentId)
                }
                put("propertiesJson", gson.toJson(modelBody))
                val photo = Utils_Bitmap.bitmapToBase64(bitmap)
                photo?.let { put("photoFile", it) }
            }

            uiThread {
                Http.post(activity, url, map, BaseBody::class.java, object : MyCallBack<BaseBody> {
                    override fun onResponse(response: BaseBody?) {
                        Utils_Dialog.disMissLoading()
                        response?.let {
                            if (pageType=="1"){
                                toast("修改成功")
                            }else{
                                toast("添加成功")
                            }
                            EventBus.getDefault().post(ET_SyStemSetting(ET_SyStemSetting.TASKID_REFRESH_IMPORTANT_DEVICE))
                            finish()
                        }
                    }

                    override fun onError(error: ErrorBody?) {
                        Utils_Dialog.disMissLoading()
                        toast(error)
                    }

                })
            }
        }


    }


    /**
     * 最近一张照片Bitmap
     */
    private var bitmap: Bitmap? = null
    private var tempFile: File? = null
    private var isModified=true

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                val uri = data.data
                val resolver = activity.contentResolver
                try {
                    // 获取到图片文件
                    bitmap = MediaStore.Images.Media.getBitmap(resolver, uri)
                    bitmap = BitmapUtils.compressImage(bitmap)
                    userImg.setImageBitmap(bitmap)
                    myLayout.visibility = View.VISIBLE
                    relAddPhoto.visibility = View.GONE

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (Utils_Common.hasSdcard()) {
                tempFile = File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)
                val uri = Uri.fromFile(tempFile)

                val resolver = activity.contentResolver
                try {
                    // 获取到图片文件
                    bitmap = MediaStore.Images.Media.getBitmap(resolver, uri)
                    bitmap = BitmapUtils.compressImage(bitmap)
                    userImg.setImageBitmap(bitmap)
                    myLayout.visibility = View.VISIBLE
                    relAddPhoto.visibility = View.GONE
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } else {
                toast("未找到存储卡，无法存储照片！")
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }


    internal var dialog: CustomDialog? = null

    private fun showChooseDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_upload_pic, null)
        dialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.takePhoto).setOnClickListener(dialogListener)
        mView.findViewById(R.id.galley).setOnClickListener(dialogListener)
        mView.findViewById(R.id.close).setOnClickListener(dialogListener)
        dialog!!.show()
    }

    private val dialogListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.takePhoto -> takePic()
            R.id.galley -> takeGalley()
            R.id.close -> if (dialog != null) {
                dialog!!.dismiss()
            }
        }
    }

    private fun takeGalley() {
        dialog?.dismiss()
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY)
    }

    private fun takePic() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        // 判断存储卡是否可以用，可用进行存储
        if (Utils_Common.hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)))
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA)
    }

    private val PHOTO_REQUEST_CAMERA = 1                // 拍照
    private val PHOTO_REQUEST_GALLERY = 2                // 从相册中选择
    private val PHOTO_FILE_NAME = "temp_photo2.jpg"


    class ET_AddDevices(taskId: Int, chooseId: String, chooseName: String) : ET_Base(taskId) {
        var chooseId = chooseId
        var chooseName = chooseName

        companion object {
            /**添加仪器，选择关联分项 */
            val TASKID_ADD_DEVICES_ABOUT_CATE = UUID.randomUUID().hashCode()
            /**添加仪器，选择关联区域 */
            val TASKID_ADD_DEVICES_ABOUT_AREA = UUID.randomUUID().hashCode()
            /**添加仪器，选择关联分项 */
            val TASKID_ADD_DEVICES_ABOUT_INSTRUMENT = UUID.randomUUID().hashCode()
        }

    }

    var chooseCateId: String = ""
    var chooseInstrumentId: String = ""
    var chooseAreaId: String = ""
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_AddDevices) {
        when {
            event.taskId == ET_AddDevices.TASKID_ADD_DEVICES_ABOUT_CATE -> {
                tv_cate.text = event.chooseName
                chooseCateId = event.chooseId
            }
            event.taskId == ET_AddDevices.TASKID_ADD_DEVICES_ABOUT_INSTRUMENT -> {
                chooseInstrumentId = event.chooseId
                tv_instrument.text = event.chooseName
            }
            event.taskId == ET_AddDevices.TASKID_ADD_DEVICES_ABOUT_AREA -> {
                chooseAreaId = event.chooseId
                tv_area.text = event.chooseName
            }
        }
    }
}