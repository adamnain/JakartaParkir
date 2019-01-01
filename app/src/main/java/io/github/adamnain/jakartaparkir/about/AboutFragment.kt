package io.github.adamnain.jakartaparkir.about


import android.app.FragmentManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.adamnain.jakartaparkir.R
import io.github.adamnain.jakartaparkir.adamFragment
import io.github.adamnain.jakartaparkir.fauziFragment
import io.github.adamnain.jakartaparkir.listparkir.ListParkirFragment
import io.github.adamnain.jakartaparkir.luthfiFragment
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*
import org.jetbrains.anko.support.v4.ctx

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_about, container, false)
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.main_container, adamFragment(), adamFragment::class.java.simpleName)?.commit()
        v.btn_adam.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container, adamFragment(), adamFragment::class.java.simpleName)?.commit()
        }
        v.btn_luthfi.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container, luthfiFragment(), luthfiFragment::class.java.simpleName)?.commit()
        }
        v.btn_fauzi.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container, fauziFragment(), fauziFragment::class.java.simpleName)?.commit()
        }
        return v
        }




}
