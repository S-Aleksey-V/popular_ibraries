package me.tolkstudio.popularlibraries.mvp.presenter

import me.tolkstudio.popularlibraries.R
import me.tolkstudio.popularlibraries.mvp.model.CountersModel
import me.tolkstudio.popularlibraries.mvp.view.MainView

class Presenter(val view: MainView) {
    val model = CountersModel()

    fun counterOneClick() {
        val nextValue = model.next(0)
        view.setButtonOneText(nextValue.toString())
    }

    fun counterTwoClick() {
        val nextValue = model.next(1)
        view.setButtonTwoText(nextValue.toString())
    }

    fun counterThreeClick() {
        val nextValue = model.next(2)
        view.setButtonThreeText(nextValue.toString())

    }
}
