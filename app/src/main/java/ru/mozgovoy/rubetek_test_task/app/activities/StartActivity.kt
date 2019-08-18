package ru.mozgovoy.rubetek_test_task.app.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.mozgovoy.rubetek_test_task.R
import ru.mozgovoy.rubetek_test_task.app.presenters.StartPresenter
import ru.mozgovoy.rubetek_test_task.app.views.StartView

class StartActivity : MvpAppCompatActivity(), StartView {

    @InjectPresenter
    lateinit var startPresenter: StartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            startPresenter.updateData()
        }
    }

    override fun toggleMessageLoading(isLoading: Boolean) {
        button.isEnabled = !isLoading
        progress_view.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showText(text: String) {
        textView.text = text
    }
}
