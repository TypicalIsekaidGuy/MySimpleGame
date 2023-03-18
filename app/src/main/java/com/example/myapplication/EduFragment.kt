package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class EduFragment : Fragment() {

    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var btnBook : Button = view.findViewById(R.id.book)
        var btnPay : Button = view.findViewById(R.id.pay)
        var btnLecture : Button = view.findViewById(R.id.lecture)
        var btnGenius : Button = view.findViewById(R.id.genius)

        btnBook.setOnClickListener {
            GameManager.ChangeSaturation(-10.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeHappiness(-5.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.HAPPINESS)
            GameManager.ChangePerfomance(5.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.EDUCATION)
            GameManager.ChangeDateTime(2.0)
            mainActivity.UpdateTime()
        }
        btnPay.setOnClickListener {
            GameManager.ChangeMoney(-2000.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangePerfomance(5.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.EDUCATION)
            GameManager.ChangeDateTime(0.5)
            mainActivity.UpdateTime()
        }
        btnLecture.setOnClickListener {
            GameManager.ChangeMoney(-60.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangePerfomance(5.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.EDUCATION)
            GameManager.ChangeDateTime(3.0)
            mainActivity.UpdateTime()
        }
        btnGenius.setOnClickListener {
            GameManager.ChangeSaturation(-30.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeDateTime(12.0)
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