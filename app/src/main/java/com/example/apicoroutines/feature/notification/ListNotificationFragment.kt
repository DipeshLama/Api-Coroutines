package com.example.apicoroutines.feature.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentListNotificationBinding
import com.example.apicoroutines.feature.shared.adapter.NotificationListAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.response.PushNotification
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class ListNotificationFragment : BaseFragment() {
    private lateinit var binding: FragmentListNotificationBinding
    private val notificationViewModel: NotificationViewModel by viewModels()
    private val notificationList = arrayListOf<PushNotification>()
    private lateinit var notificationListAdapter: NotificationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_list_notification,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationViewModel
        initRecyclerView()
        getPushNotification()
    }

    private fun getPushNotification() {
        notificationViewModel.getPushNotification(getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onGetNotificationSuccess(it)
                    Status.ERROR -> onGetNotificationError(it.message)
                    Status.LOADING->{

                    }
                }
            }
    }

    private fun onGetNotificationSuccess(it: Resource<Response<BaseArrayResponse<PushNotification>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                notificationList.clear()
                notificationList.addAll(it.body()?.data ?: emptyList())
                notificationListAdapter.notifyItemRangeInserted(0, notificationList.count())
            } else {
                onGetNotificationError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onGetNotificationError(msg: String?) {
        showMessage(msg)
    }

    private fun initRecyclerView() {
        notificationListAdapter = NotificationListAdapter(notificationList)
        binding.ryvNotification.apply {
            adapter = notificationListAdapter
            itemAnimator = null
        }
    }
}