package ru.mozgovoy.rubetek_test_task.app.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mozgovoy.rubetek_test_task.exceptions.SmallNumberException
import ru.mozgovoy.rubetek_test_task.app.App
import ru.mozgovoy.rubetek_test_task.app.views.StartView
import ru.mozgovoy.rubetek_test_task.data.room.DataEntity
import kotlin.random.Random

@InjectViewState
class StartPresenter : MvpPresenter<StartView>() {
    private val TAG: String = "StartPresenter"
    private val db = App.appDB
    private val MAX_VAL_RANDOM = 20
    private val MIN_VAL_EXCEPTION: Int = 5
    private val DELAY_RESPONSE: Long = 5000

    init {
        val dispose = db.getValue()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isEmpty()) {
                    updateData()
                }else {
                    checkValue(it[0].value)
                }
            }, {
                Log.e(TAG, "DB ERROR: ${it.message}")
            })
    }

    private fun checkValue(value: Int){
        try {
            if (value > MIN_VAL_EXCEPTION) {
                viewState.showText(value.toString())
                viewState.toggleMessageLoading(false)
            } else {
                throw SmallNumberException("Error: too small number")
            }
        }
        catch (e: SmallNumberException) {
            viewState.showText(e.localizedMessage)
            viewState.toggleMessageLoading(false)
        }
    }

    private fun saveValue(value: Int) {
        val dispose = Observable.fromCallable {
            db.insert(DataEntity(id = 0, value = value))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d(TAG, "DB insert OK: $value")
            }, {
                Log.e(TAG, "DB insert ERROR: ${it.localizedMessage}")
            })
    }

    fun updateData() {
        viewState.toggleMessageLoading(true)

        val dispose = sourceData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveValue(it)
            }, {
                Log.e(TAG,"UpdateData: ERROR ${it.localizedMessage}")
            })
    }

    private fun sourceData(): Observable<Int> {
        return Observable.create { subscriber ->
            Thread.sleep(DELAY_RESPONSE)
            subscriber.onNext(Random.nextInt(MAX_VAL_RANDOM))
            subscriber.onComplete()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}