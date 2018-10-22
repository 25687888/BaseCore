package talex.zsw.sample.module.main.ui

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import talex.zsw.basecore.model.ActionItem
import talex.zsw.basecore.util.LogTool
import talex.zsw.basecore.util.glide.GlideTool
import talex.zsw.basecore.view.other.slidedatetimepicker.SlideDateTimeListener
import talex.zsw.basecore.view.other.slidedatetimepicker.SlideDateTimePicker
import talex.zsw.basecore.view.popupwindow.PopLayout
import talex.zsw.basecore.view.popupwindow.PopListView
import talex.zsw.sample.R
import talex.zsw.sample.base.BaseMVPActivity
import talex.zsw.sample.module.main.contract.MainContract
import talex.zsw.sample.module.main.presenter.MainPresenter
import java.util.*

/**
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2018/5/24 15:46 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MainActivity : BaseMVPActivity<MainContract.Presenter>(), MainContract.View
{
    private var popListView: PopListView? = null
    private var popLayout: PopLayout? = null

    override fun initArgs(intent: Intent?)
    {
    }

    override fun initView()
    {
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this)
    }

    override fun initData()
    {
        GlideTool.loadImgCircleCrop(mImageView,
                                    "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1182022639,405039723&fm=27&gp=0.jpg")
    }

    @OnClick(R.id.mBtn1, R.id.mBtn2, R.id.mBtn3)
    fun onViewClicked(view: View)
    {
        EventBus.getDefault()
                .post("onViewClicked")
        when (view.id)
        {
            R.id.mBtn1 ->
            {
                if (popLayout == null)
                {
                    popLayout = PopLayout(this@MainActivity, "厉害了")
                }
                else
                {
                    popLayout =
                            PopLayout(this@MainActivity,
                                      ViewGroup.LayoutParams.WRAP_CONTENT,
                                      ViewGroup.LayoutParams.WRAP_CONTENT,
                                      R.layout.activity_main)
                }
                popLayout?.show(mBtn1)
            }
            R.id.mBtn2 ->
            {
                initPopupView()
                popListView?.show(mBtn2, 0)
            }
            R.id.mBtn3 ->
            {
                SlideDateTimePicker.Builder(supportFragmentManager)
                        .setInitialDate(Date())
                        .setListener(object : SlideDateTimeListener()
                                     {
                                         override fun onDateTimeSet(date: Date?)
                                         {
                                         }
                                     })
                        .setMinDate(Date())
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .setIndicatorColor(Color.parseColor("#990000"))
                        .setShowTime(true)
                        .setThemeColor(Color.parseColor("#FFFF00"))
                        .setTitleColor(Color.parseColor("#FF0000"))
                        .build()
                        .show()
            }
        }
    }

    private fun initPopupView()
    {
        popListView =
                PopListView(this@MainActivity, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popListView?.addAction(ActionItem("标清"))
        popListView?.addAction(ActionItem("高清"))
        popListView?.addAction(ActionItem("超清"))
        popListView?.setItemOnClickListener { item, position ->
            LogTool.d(popListView?.getAction(position).toString())
        }
    }

    @Subscribe
    fun onEvent(event: String)
    {
        LogTool.a(event)
        LogTool.a(event)
        LogTool.a(event)
        LogTool.a(event)
    }
}