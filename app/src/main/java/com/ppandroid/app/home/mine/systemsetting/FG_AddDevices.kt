package com.ppandroid.app.home.mine.systemsetting

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.ppandroid.app.R
import com.ppandroid.app.utils.BitmapUtils
import com.ppandroid.app.utils.Utils_Common
import com.ppandroid.app.widget.CustomDialog
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_add_devices.*
import kotlinx.android.synthetic.main.layout_head_view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

/**
 * Created by yeqinfu on 2017/8/28.
 * 新增设备
 */
class FG_AddDevices : FG_Base() {
    override fun fgRes(): Int = R.layout.fg_add_devices
    private var listAdapter: MyListAdapter? = null
    override fun afterViews() {
        head_view.init(activity)
        head_view.setRightText("保存") {
            postInfo()
        }
        relAddPhoto.setOnClickListener {
            if (Bitmaps.size >= 9) {
                toast("最多添加9张图片")
            } else {
                showChooseDialog()
            }

        }
        listAdapter = MyListAdapter()
        horizontalListView1.adapter = listAdapter
    }

    private fun postInfo() {
        if (TextUtils.isEmpty(et_name.text)) {
            toast("请输入名称")
            return
        }
        if (TextUtils.isEmpty(et_model.text)) {
            toast("请输入型号")
            return
        }
    }


    /**
     * 最近一张照片Bitmap
     */
    private var bitmap: Bitmap? = null
    /**
     * 每次从本地传一张照片就添加一张
     */
    var Bitmaps: ArrayList<Bitmap> = ArrayList()
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
                    bitmap?.let {
                        Bitmaps.add(it)
                        listAdapter?.notifyDataSetChanged()
                    }


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
                    bitmap?.let {
                        Bitmaps.add(it)
                        listAdapter?.notifyDataSetChanged()
                    }
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
        if (dialog != null) {
            dialog!!.dismiss()
        }
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

    internal inner class MyListAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return Bitmaps.size
        }

        override fun getItem(position: Int): Any? {
            return Bitmaps[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            val layout = activity.layoutInflater.inflate(R.layout.want_to_sell_imgview, null)
            val userImg = layout.findViewById(R.id.userImg) as ImageView
            val deleteImg = layout.findViewById(R.id.deleteImg) as ImageView
            userImg.setImageBitmap(Bitmaps[position])
            deleteImg.setOnClickListener {
                Bitmaps.removeAt(position)
                listAdapter?.notifyDataSetChanged()
                toast("删除成功")
            }

            return layout
        }

    }

}