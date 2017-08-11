package com.ppandroid.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.ppandroid.app.bean.login.LoginBody
import com.ppandroid.app.http.MyCallBack
import com.ppandroid.app.http.OKUtils
import com.ppandroid.app.utils.SnackbarUtils
import com.ppandroid.im.base.AC_Base
import com.ppandroid.im.bean.BaseBody
import kotlinx.android.synthetic.main.activity_ac__login.*

class AC_Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__login)
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
            beginLogin(account,password)
        }
    }

    private fun beginLogin(account: String, password: String) {
        var url="user/login/check.json"
        OKUtils.get(this@AC_Login, url, LoginBody::class.java, object : MyCallBack<LoginBody> {
            override fun onResponse(response: LoginBody) {

            }

            override fun onError(error: BaseBody) {

            }
        })
    }

    private fun toast(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            SnackbarUtils.with(AC_Base.getContentView(this)).setMessage(msg).show()
        }
    }
}
