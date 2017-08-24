package com.ppandroid.app.home.mine.userinfo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.ppandroid.app.R
import com.ppandroid.app.bean.ET_Refresh
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.mine.BN_UserInfo
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.BitmapUtils
import com.ppandroid.app.utils.Utils_Bitmap
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.utils.Utils_Dialog
import com.ppandroid.app.utils.glide.GlideUtils
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.app.widget.popwindow.Pop_ChooseArea
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.fg_user_info.*
import kotlinx.android.synthetic.main.layout_head_view.*
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

/**
 * Created by yeqinfu on 2017/8/23.
 * 个人信息
 */
class FG_UserInfo : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_user_info

    override fun afterViews() {
        head_view.init(activity)
        head_view.setCenterTitle("个人信息")
        head_view.setRightText("保存") {
            postInfo()
        }
        rl_pic.setOnClickListener {
            showChooseDialog()
        }
        loadContent()
        rl_gender.setOnClickListener {
            showGenderDialog()
        }
        rl_area.setOnClickListener {
            showAreaDialog()
        }
    }

    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text.toString().trim())){
            toast("更改的姓名不能为空")
            return
        }
        val params = TreeMap<String, String>()

        Utils_Dialog.showLoading(activity)
        bitmap?.let {
            val photo = Utils_Bitmap.bitmapToBase64(it)
            params.put("photoFile",photo)
        }

        params.apply {
            put("realName",et_name.text.toString().trim())
            put("sex",selectedGender.toString())
            put("provinceId",provinceId)
            put("cityId",cityId)
            put("areaId",countryId)
            put("areaName",tv_area.text.toString())
        }
        var url="user/personal/save.json"
        Http.post(activity,url,params,BaseBody::class.java,object :MyCallBack<BaseBody>{
            override fun onResponse(response: BaseBody?) {
                response?.let {
                    Utils_Dialog.disMissLoading()
                    toast("保存成功")
                    EventBus.getDefault().post(ET_Refresh(ET_Refresh.TASKID_REFRESH_MINE))
                    finish()
                }
            }

            override fun onError(error: ErrorBody?) {
                Utils_Dialog.disMissLoading()
                toast(error)
            }

        })

    }

    /**地理位置选择*/
    private var provinceId = ""
    private var cityId = ""
    private var countryId = ""
    private fun showAreaDialog() {
        var pop= Pop_ChooseArea(activity)
        pop.setAutoShowInputMethod(false)
        pop.listener=object : Pop_ChooseArea.IChooseListener{
            override fun choose(item: String, provinceId1: String, cityId1: String, contryId1: String) {
                tv_area.text=item
                provinceId=provinceId1
                cityId=cityId1
                countryId=contryId1
                pop.dismiss()

            }

        }

        pop.showPopupWindow()
    }

    internal var dialog: CustomDialog? = null
    internal var genderDialog: CustomDialog? = null

    private fun showGenderDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_gender, null)
        genderDialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.rl_woman).setOnClickListener(dialogListener)
        mView.findViewById(R.id.rl_man).setOnClickListener(dialogListener)
        genderDialog?.show()
    }

    private fun showChooseDialog() {
        val mView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_upload_pic, null)
        dialog = CustomDialog(activity, R.style.family_dialog_theme, mView, Gravity.CENTER, 4)
        mView.findViewById(R.id.takePhoto).setOnClickListener(dialogListener)
        mView.findViewById(R.id.galley).setOnClickListener(dialogListener)
        mView.findViewById(R.id.close).setOnClickListener(dialogListener)
        dialog?.show()
    }

    private var selectedGender = 1

    private val dialogListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.takePhoto -> takePic()
            R.id.galley -> takeGalley()
            R.id.close -> dialog?.dismiss()
            R.id.rl_woman -> {
                selectedGender = 0
                tv_gender.text = "女"
                genderDialog?.dismiss()
            }
            R.id.rl_man -> {
                selectedGender = 1
                tv_gender.text = "男"
                genderDialog?.dismiss()
            }
        }
    }
    private val PHOTO_REQUEST_CAMERA = 1                // 拍照
    private val PHOTO_REQUEST_GALLERY = 2                // 从相册中选择
    private val PHOTO_FILE_NAME = "temp_photo2.jpg"
    private fun takePic() {
        dialog?.dismiss()
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        // 判断存储卡是否可以用，可用进行存储
        if (Utils_Common.hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)))
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA)
    }

    private fun takeGalley() {
        dialog?.dismiss()
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY)
    }

    /**
     * 最近一张照片Bitmap
     */
    private var bitmap: Bitmap? = null
    private var tempFile: File? = null
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
                    iv_head.setImageBitmap(bitmap)

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
                    iv_head.setImageBitmap(bitmap)

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

    private fun loadContent() {
        var url = "user/personal/get.json"
        Http.get(activity, url, BN_UserInfo::class.java, object : MyCallBack<BN_UserInfo> {
            override fun onResponse(response: BN_UserInfo?) {
                response?.let {
                    et_name.setText(it.message.employeeEntity.realName)
                    et_name.setSelection(it.message.employeeEntity.realName.length)
                    tv_account.text = it.message.employeeEntity.mobile
                    if (it.message.employeeEntity.sex == 1) {
                        tv_gender.text = "男"
                    } else {
                        tv_gender.text = "女"
                    }
                    tv_company_name.text=it.message.companyEntity.name
                    tv_type.text=it.message.companyEntity.industryType
                    tv_area.text=it.message.employeeEntity.areaName
                    it.message.employeeEntity.headPhoto?.let {
                        GlideUtils.displayImage(activity,it,iv_head)
                    }

                }

            }

            override fun onError(error: ErrorBody?) {
                toast(error)
            }

        })

    }






}