package com.ppandroid.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.ppandroid.app.bean.ErrorBody
import com.ppandroid.app.bean.login.CaptchaBody
import com.ppandroid.app.bean.login.LoginBody
import com.ppandroid.app.http.Http
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.utils.SecurityUtils
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
            beginLogin2(account,password)
        }
    }

    /**
     * 新登录方式
     */
    private fun beginLogin2(account: String, password: String) {
        var url="user/captcha.json"
        Http.get(this@AC_Login,url, CaptchaBody::class.java,object :MyCallBack<CaptchaBody>{
            override fun onResponse(response: CaptchaBody?) {
                response?.let {
                    var password2=SecurityUtils.decode(password)
                    password2 += it.message
                    var sign=SecurityUtils.decode(password2)
                    Utils_UserInfo.saveSign(this@AC_Login,sign)
                    beginLogin3(account,it.message,sign)
                }
            }

            override fun onError(error: ErrorBody?) {
                toast(error?.message?:"")
            }

        })
    }

    private fun beginLogin3(account: String, captcha: String?, sign: String?) {
        var url="user/login/check.json?username=$account&captcha=$captcha&sign_=$sign"
        Http.get(this@AC_Login, url, LoginBody::class.java, object : MyCallBack<LoginBody> {
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

    private fun beginLogin(account: String, password: String) {

        var password2=SecurityUtils.decode(password)
        var url= "user/login/check.json?username=$account&password=$password2"
        Http.get(this@AC_Login, url, LoginBody::class.java, object : MyCallBack<LoginBody> {
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
