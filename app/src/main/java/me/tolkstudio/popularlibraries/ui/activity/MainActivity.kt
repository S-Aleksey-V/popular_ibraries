package me.tolkstudio.popularlibraries.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import me.tolkstudio.popularlibraries.R
import me.tolkstudio.popularlibraries.databinding.ActivityMainBinding
import me.tolkstudio.popularlibraries.mvp.presenter.MainPresenter
import me.tolkstudio.popularlibraries.mvp.view.MainView
import me.tolkstudio.popularlibraries.ui.App
import me.tolkstudio.popularlibraries.ui.BackClickListener
import me.tolkstudio.popularlibraries.ui.navigation.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackClickListener && it.backPressed()) {
                return
            }
        }
        presenter.bacClicked()
    }

}
