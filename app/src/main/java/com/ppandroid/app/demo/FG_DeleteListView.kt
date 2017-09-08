package com.ppandroid.app.demo

import com.ppandroid.app.R
import com.ppandroid.app.widget.deletelistview.ListViewSlideAdapter
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_delete_listview.*

/**
 * Created by yeqinfu on 2017/9/8.
 */
class FG_DeleteListView :FG_Base(){
    private val list = ArrayList<String>()
    override fun afterViews() {
        (0..19).mapTo(list) { "第" + it.toString() + "个item" }
        var adapter = ListViewSlideAdapter(activity, list)
        lv_list2.adapter = adapter
        adapter.setOnClickListenerEditOrDelete(object : ListViewSlideAdapter.OnClickListenerEditOrDelete {
            override fun OnClickListenerDelete(position: Int) {
                toast("OnClickListenerDelete" + position)
            }

            override fun OnClickListenerEdit(position: Int) {
                toast("OnClickListenerEdit" + position)

            }

        })
        lv_list2.setOnItemLongClickListener { adapterView, view, i, l ->
            toast("fdsafs")
            true
        }
        lv_list2.setOnItemClickListener { adapterView, view, i, l ->
            toast("setOnItemClickListener")
            true
        }
    }

    override fun fgRes(): Int= R.layout.fg_delete_listview

}