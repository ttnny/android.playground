package ttnny.dev.android.androidconcurrency.uithread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.PointerIcon
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import timber.log.Timber
import ttnny.dev.android.androidconcurrency.R
import ttnny.dev.android.androidconcurrency.databinding.CustomHandlerDemoFragmentBinding
import ttnny.dev.android.androidconcurrency.databinding.UiThreadDemoFragmentBinding
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

class CustomHandlerDemoFragment : Fragment() {

    companion object {
        val POISON = Runnable {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val mQueue: BlockingDeque<Runnable> = LinkedBlockingDeque<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CustomHandlerDemoFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.custom_handler_demo_fragment,
                container,
                false
            )

        binding.sendJobButton.setOnClickListener {
            sendJob()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initWorkerThread()
    }

    override fun onStop() {
        super.onStop()
        mQueue.clear()
        mQueue.add(POISON)
    }

    private fun initWorkerThread() {
        Thread {
            while (true) {
                val runnable = mQueue.take()

                if (runnable == POISON) {
                    return@Thread
                }

                runnable.run()
            }
        }.start()
    }

    private fun sendJob() {
        val job = Runnable {
            for (i in 0 until 5) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    return@Runnable
                }

                Timber.d("CustomHandler: Iteration: $i")
            }
        }

        mQueue.add(job)
    }

}