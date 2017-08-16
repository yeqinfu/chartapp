package com.ppandroid.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.login.LoginBody
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.http.OKUtils
import com.ppandroid.app.utils.info.Utils_UserInfo
import com.ppandroid.app.utils.toast.ToastUtil
import kotlinx.android.synthetic.main.activity_ac__login.*

class AC_Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__login)
        var tempAccount=Utils_UserInfo.getTempAccount(this)
        if (!TextUtils.isEmpty(tempAccount)){
            et_account.setText(tempAccount)
            checkbox.isChecked=true
            et_account.setSelection(tempAccount.length)//将光标移至文字末尾
        }
        btn_login.setOnClickListener {

            var account=et_account.text.toString()
            var password=et_password.text.toString()
            if (TextUtils.isEmpty(account)){
                toast("账号不能为空")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)){
                toast("密码不能为空")
                return@setOnClickListener
            }

            if (checkbox.isChecked){
                Utils_UserInfo.saveTempAccount(this,account)
            }
            beginLogin(account,password)
        }
    }

    private fun beginLogin(account: String, password: String) {
        var url= "user/login/check.json?username=$account&password=$password"
        OKUtils.get(this@AC_Login, url, LoginBody::class.java, object : MyCallBack<LoginBody> {
            override fun onResponse(response: LoginBody) {
                response?.let {
                    Utils_UserInfo.saveInfo(this@AC_Login,response)
                    var it= Intent()
                    it.setClass(this@AC_Login,AC_Main::class.java)
                    startActivity(it)
                    finish()
                }
            }
            override fun onError(error: ErrorBody) {
                toast(error.message)
            }
        })
    }

    private fun toast(msg: String) {
        ToastUtil.toast(this@AC_Login,msg)
    }
}
