package com.example.apicoroutines.feature.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentNotificationBinding
import com.example.apicoroutines.feature.faq.FaqFragment
import com.example.apicoroutines.feature.shared.adapter.NotificationAdapter
import com.google.android.material.tabs.TabLayoutMediator

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_notification,
                container,
                false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabLayout()
    }

    private fun setTabLayout() {
        val lstFragment = addFragmentList()
        val lstTabTitle = addTitleToList()
        val iconList = addIconToList()
        val adapter =
            activity?.supportFragmentManager?.let {
                NotificationAdapter(lstFragment,
                    it, lifecycle)
            }
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tbLayout, binding.viewPager) { tab, position ->
            tab.text = lstTabTitle[position]
            tab.setIcon(iconList[position])
        }.attach()
    }

    private fun addFragmentList(): ArrayList<Fragment> {
        val lstFragment = ArrayList<Fragment>()
        lstFragment.add(FaqFragment())
        lstFragment.add(FaqFragment())
        lstFragment.add((FaqFragment()))
        return lstFragment
    }

    private fun addTitleToList(): ArrayList<String> {
        val lstTitle = ArrayList<String>()
        lstTitle.add("Notifications")
        lstTitle.add("Order")
        lstTitle.add("Promo")
        return lstTitle
    }

    private fun addIconToList () : ArrayList<Int>{
        val lstTitle = ArrayList<Int>()
        lstTitle.add(R.drawable.ic_notification_iconly)
        lstTitle.add(R.drawable.ic_notification_iconly)
        lstTitle.add(R.drawable.ic_ticket_iconly)
        return lstTitle
    }
}