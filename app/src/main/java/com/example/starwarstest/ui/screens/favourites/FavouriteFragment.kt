package com.example.starwarstest.ui.screens.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarstest.R
import com.example.starwarstest.domain.model.UIModel
import com.example.starwarstest.ui.MainApp
import com.example.starwarstest.ui.screens.adapter.StarWarsAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    private lateinit var tvNoDataInfo: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val adapter = StarWarsAdapter { starWarsModel ->
        doStarWarsEntityNotFavourite(starWarsModel)
    }
    private val viewModel: FavouriteViewModel by viewModels { vmFactory }

    @Inject
    lateinit var vmFactory: FavouriteViewModel.FavouriteVMFactory

    override fun onAttach(context: Context) {
        (context.applicationContext as MainApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeFlow()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourites_list, container, false)
        recyclerView = view.findViewById(R.id.rvFavourites)
        tvNoDataInfo = view.findViewById(R.id.tvNoDataInfo)
        progressBar = view.findViewById(R.id.pbLoading)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = adapter
    }

    private fun observeFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.result.collect { result ->
                    when (result) {
                        is FavouriteViewResult.Successes -> {
                            progressBar.visibility = ViewGroup.INVISIBLE
                            progressBar.isIndeterminate = false

                            if (result.resultList.isNotEmpty()) {
                                tvNoDataInfo.visibility = TextView.INVISIBLE
                                adapter.submitList(result.resultList)
                            } else {
                                adapter.submitList(emptyList())
                                tvNoDataInfo.visibility = TextView.VISIBLE
                            }
                        }

                        FavouriteViewResult.Loading -> {
                            tvNoDataInfo.visibility = TextView.INVISIBLE
                            progressBar.visibility = ViewGroup.VISIBLE
                            progressBar.isIndeterminate = true
                        }
                    }
                }
            }
        }
    }

    private fun doStarWarsEntityNotFavourite(starWarsModel: UIModel) =
        viewModel.onUnlikeClicked(starWarsModel)
}