package com.example.starwarstest.ui.screens.searchscreens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarstest.R
import com.example.starwarstest.domain.model.UIModel
import com.example.starwarstest.ui.MainApp
import com.example.starwarstest.ui.screens.adapter.StarWarsAdapter
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private val adapter = StarWarsAdapter { starWarsModel ->
        doStarWarsEntityFavourite(starWarsModel)
    }

    private val viewModel: SearchViewModel by viewModels { vmFactory }

    @Inject
    lateinit var vmFactory: SearchViewModel.SearchVMFactory

    override fun onAttach(context: Context) {
        (context.applicationContext as MainApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.rvSearch)
        searchView = view.findViewById(R.id.searchView)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(createSearchListener())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter

        viewModel.result.observe(viewLifecycleOwner) { resultList ->
            adapter.submitList(resultList)
        }

        (requireActivity() as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) =
                menuInflater.inflate(R.menu.toolbar_menu, menu)

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.icFavourite) {
                    navigateToFormFragment(view)
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun navigateToFormFragment(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_searchFragment_to_favouriteFragment)
    }

    private fun createSearchListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean = false

        override fun onQueryTextChange(newText: String): Boolean {
            viewModel.onTextChanged(newText)
            return true
        }
    }

    private fun doStarWarsEntityFavourite(starWarsModel: UIModel) =
        viewModel.onLikeClicked(starWarsModel)
}