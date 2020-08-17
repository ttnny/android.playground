package ttnny.dev.android.rxjavarxandroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.main_fragment.*
import ttnny.dev.android.rxjavarxandroid.R
import java.util.concurrent.TimeUnit

class MainFragment : Fragment() {

    // private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        init()

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun noSugar() {
        val emitter = PublishSubject.create<View>()
        clickMeButton.setOnClickListener {
            emitter.onNext(it)
        }

        emitter.map {
            incrementCounter1()
        }
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                incrementCounter2()
            }
    }

    private fun incrementCounter1() {
        var value = counterTextView1.text.toString().toInt()
        value++
        counterTextView1.text = value.toString()
    }

    private fun incrementCounter2() {
        var value = counterTextView2.text.toString().toInt()
        value++
        counterTextView2.text = value.toString()
    }

    private fun init() {
        noSugar()
    }

}