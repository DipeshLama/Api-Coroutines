package com.example.apicoroutines.feature.faq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentFaqBinding
import com.example.apicoroutines.feature.shared.adapter.FaqAdapter
import com.example.apicoroutines.feature.shared.listener.OnExpandListener
import com.example.apicoroutines.feature.shared.model.response.Faq

class FaqFragment : Fragment(), OnExpandListener {
    private lateinit var binding: FragmentFaqBinding
    private var faqList = ArrayList<Faq>()
    private lateinit var adapter : FaqAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_faq,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        faqList = faqList()
        adapter = FaqAdapter(faqList, this)
        binding.rvyFaq.adapter = adapter
    }

    private fun faqList(): ArrayList<Faq> {
        val list = ArrayList<Faq>()
        val faq1 = Faq("Can I try App Press before I buy?",
            "If you cancel your subscription, your app’s content will stop displaying to your users. You can choose to publish a bundled app. Bundled apps will continue to work even if you cancel your subscription.")

        val faq2 = Faq("Can I try App Press before I buy?",
            "If you cancel your subscription, your app’s content will stop displaying to your users. You can choose to publish a bundled app. Bundled apps will continue to work even if you cancel your subscription.")
        val faq3 = Faq("Can I try App Press before I buy?",
            "If you cancel your subscription, your app’s content will stop displaying to your users. You can choose to publish a bundled app. Bundled apps will continue to work even if you cancel your subscription.")
        val faq4 = Faq("Can I try App Press before I buy?",
            "If you cancel your subscription, your app’s content will stop displaying to your users. You can choose to publish a bundled app. Bundled apps will continue to work even if you cancel your subscription.")

        list.add(faq1)
        list.add(faq2)
        list.add(faq3)
        list.add(faq4)
        return list
    }

    override fun onExpand(position: Int) {
        val faq = faqList[position]
        faq.isExpanded = faq.isExpanded == false
        adapter.notifyItemChanged(position)
    }
}

