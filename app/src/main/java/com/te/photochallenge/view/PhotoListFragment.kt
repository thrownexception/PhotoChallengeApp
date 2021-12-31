package com.te.photochallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.te.photochallenge.databinding.FragmentPhotoListBinding
import com.te.photochallenge.model.Photo
import com.te.photochallenge.view.adapter.PhotoListAdapter
import com.te.photochallenge.viewmodel.PhotoListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PhotoListFragment : Fragment() {

    private lateinit var binding: FragmentPhotoListBinding
    private val viewModel: PhotoListViewModel by viewModel()
    private var list = arrayListOf<Photo>()
    private val listAdapter: PhotoListAdapter by inject { (parametersOf(list)) }

    private val photoListObserver = Observer<List<Photo>> {
        it?.let {
            if (it.isEmpty()) {
                viewModel.fetchData(childFragmentManager)
                listAdapter.updateList(it)
            } else {
                listAdapter.updateList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchData(childFragmentManager)
        viewModel.photos.observe(viewLifecycleOwner, photoListObserver)

        binding.photoList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.photoList.visibility = View.GONE
            binding.loadingView.visibility = View.VISIBLE

            viewModel.fetchData(childFragmentManager)

            binding.refreshLayout.isRefreshing = false
            binding.photoList.visibility = View.VISIBLE
            binding.loadingView.visibility = View.GONE
        }
    }
}