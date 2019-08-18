package ru.mozgovoy.rubetek_test_task.app.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface StartView: MvpView {
    fun toggleMessageLoading(isLoading: Boolean)
    fun showText(text: String)
}