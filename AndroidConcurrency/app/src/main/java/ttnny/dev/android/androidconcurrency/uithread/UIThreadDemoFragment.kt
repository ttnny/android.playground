package ttnny.dev.android.androidconcurrency.uithread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.ui_thread_demo_fragment.*
import timber.log.Timber
import ttnny.dev.android.androidconcurrency.R
import ttnny.dev.android.androidconcurrency.databinding.UiThreadDemoFragmentBinding

class UIThreadDemoFragment : Fragment() {

    companion object {
        const val TAG = "UIThreadDemo"
    }

    private val mUIHandler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logThreadInfo("onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: UiThreadDemoFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.ui_thread_demo_fragment, container, false)

        binding.callbackCheckButton.setOnClickListener {
            logThreadInfo("button callback")
        }

        binding.countIterationsButton.setOnClickListener {
            countIterations()
        }

        binding.customHandlerDemoButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_UIThreadDemoFragment_to_customHandlerDemoFragment)
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logThreadInfo("onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        logThreadInfo("onStart()")
    }

    override fun onResume() {
        super.onResume()
        logThreadInfo("onResume()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logThreadInfo("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logThreadInfo("onDestroy()")
    }

    private fun countIterations() {
        Thread {
            val startTimestamp = System.currentTimeMillis()
            val endTimestamp = startTimestamp + 5 * 1000

            var iterationsCount = 0
            while (System.currentTimeMillis() <= endTimestamp) {
                iterationsCount++
            }

            mUIHandler.post(Thread {
                Timber.d("UIHandler: Current thread: ${Thread.currentThread().name}")
                countIterationsButton.text = "$iterationsCount"
            })
        }.start()
    }

    private fun logThreadInfo(eventName: String) {
        Timber.d("Event\n $eventName; thread name: ${Thread.currentThread().name}; thread ID: ${Thread.currentThread().id}")
    }

}