package com.example.starwarstest.ui.screens.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarstest.R
import com.example.starwarstest.domain.model.UIModel
import com.example.starwarstest.ui.MainApp
import com.example.starwarstest.ui.screens.adapter.StarWarsAdapter
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    private lateinit var tvNoDataInfo: TextView
    private lateinit var recyclerView: RecyclerView
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourites_list, container, false)
        recyclerView = view.findViewById(R.id.rvFavourites)
        tvNoDataInfo = view.findViewById(R.id.tvNoDataInfo)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter

        viewModel.result.observe(viewLifecycleOwner) { resultList ->
            if (resultList.isNotEmpty()) {
                tvNoDataInfo.visibility = TextView.INVISIBLE
                adapter.submitList(resultList)
            } else {
                adapter.submitList(emptyList())
                tvNoDataInfo.visibility = TextView.VISIBLE
            }
        }
        viewModel.onStartViewModel()
    }

    private fun doStarWarsEntityNotFavourite(starWarsModel: UIModel) =
        viewModel.onUnlikeClicked(starWarsModel)
}