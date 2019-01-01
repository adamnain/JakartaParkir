package io.github.adamnain.jakartaparkir.about


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout

import io.github.adamnain.jakartaparkir.R
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_about, container, false)

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.main_container,
                adamFragment(), adamFragment::class.java.simpleName)?.commit()


        v.btn_adam.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container,
                    adamFragment(), adamFragment::class.java.simpleName)?.commit()
        }
        v.btn_luthfi.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container,
                    luthfiFragment(), luthfiFragment::class.java.simpleName)?.commit()
        }
        v.btn_fauzi.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container,
                    fauziFragment(), fauziFragment::class.java.simpleName)?.commit()
        }
        return v
        }




}
