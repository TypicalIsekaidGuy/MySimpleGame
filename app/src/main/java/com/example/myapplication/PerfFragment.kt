package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class PerfFragment : Fragment() {

    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perf, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var btnbagging : Button = view.findViewById(R.id.bagging)
        var btntaking : Button = view.findViewById(R.id.taking)
        var btnactions : Button = view.findViewById(R.id.actions)
        var btntorg : Button = view.findViewById(R.id.torg)

        btnbagging.setOnClickListener {
            GameManager.ChangeSaturation(-20.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeMoney(10.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(0.1)
            mainActivity.UpdateTime()
        }
        btntaking.setOnClickListener {
            GameManager.ChangeSaturation(-1.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeHappiness(-5.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.HAPPINESS)
            GameManager.ChangeMoney(100.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(0.5)
            mainActivity.UpdateTime()
        }
        btnactions.setOnClickListener {
            GameManager.ChangeSaturation(-10.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeMoney(500.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(3.0)
            mainActivity.UpdateTime()
        }
        btntorg.setOnClickListener {
            GameManager.ChangeSaturation(-30.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeMoney(3000.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(6.0)
            mainActivity.UpdateTime()
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivity = context
        }
    }
}