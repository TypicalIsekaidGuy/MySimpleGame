package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FoodFragment : Fragment() {

    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var btnShawarma : Button = view.findViewById(R.id.shawarma)
        var btnPopsicle : Button = view.findViewById(R.id.popsicle)
        var btnBurger : Button = view.findViewById(R.id.burger)
        var btnNigger : Button = view.findViewById(R.id.bagger)
        btnShawarma.setOnClickListener {
            GameManager.ChangeSaturation(20.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeMoney(-200.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(0.5)
            mainActivity.UpdateTime()
        }
        btnPopsicle.setOnClickListener {
            GameManager.ChangeSaturation(1.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeHappiness(1.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.HAPPINESS)
            GameManager.ChangeMoney(-5.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(0.5)
            mainActivity.UpdateTime()
        }
        btnBurger.setOnClickListener {
            GameManager.ChangeSaturation(10.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeMoney(-100.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(1.0)
            mainActivity.UpdateTime()
        }
        btnNigger.setOnClickListener {
            GameManager.ChangeSaturation(30.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.SATURATION)
            GameManager.ChangeMoney(-300.0)
            mainActivity.UpdateTxtv(MainActivity.Textviews.MONEY)
            GameManager.ChangeDateTime(2.0)
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