package com.example.starwarstest.ui.screens.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.starwarstest.R
import com.example.starwarstest.domain.model.UIModel

class StarWarsAdapter(
    private val favouriteListener: (uiModel: UIModel) -> Unit,
) : ListAdapter<UIModel, ViewHolder>(StarshipComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.starship_item -> StarshipViewHolder(view)
            R.layout.people_item -> PeopleViewHolder(view)
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is UIModel.PeopleModel -> R.layout.people_item
        is UIModel.StarshipModel -> R.layout.starship_item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is PeopleViewHolder -> holder.bind(item as UIModel.PeopleModel, favouriteListener)
            is StarshipViewHolder -> holder.bind(item as UIModel.StarshipModel, favouriteListener)
        }
    }

    class StarshipViewHolder(view: View) : ViewHolder(view) {
        private var nameStarship = view.findViewById<TextView?>(R.id.tvNameOfShip)
        private var modelOfShip = view.findViewById<TextView?>(R.id.tvModel)
        private var manufacturer = view.findViewById<TextView?>(R.id.tvManufacturer)
        private var passengers = view.findViewById<TextView?>(R.id.tvPassengers)
        private val favouriteBT = view.findViewById<ImageView>(R.id.ivFavourite)

        fun bind(item: UIModel.StarshipModel, favouriteListener: (uiModel: UIModel) -> Unit) {
            nameStarship.text = item.starship.name
            modelOfShip.text = item.starship.model
            manufacturer.text = item.starship.manufacturer
            passengers.text = item.starship.passengers.toString()
            favouriteBT.setOnClickListener {
                favouriteListener(item)
            }
        }
    }

    class PeopleViewHolder(view: View) : ViewHolder(view) {
        private var name = view.findViewById<TextView?>(R.id.tvName)
        private var gender = view.findViewById<TextView?>(R.id.tvGender)
        private var countStarships = view.findViewById<TextView?>(R.id.tvMannedStarShip)
        private val favouriteBT = view.findViewById<ImageView>(R.id.ivFavourite)

        fun bind(item: UIModel.PeopleModel, favouriteListener: (uiModel: UIModel) -> Unit) {
            name.text = item.people.name
            gender.text = item.people.gender
            countStarships.text = item.people.numberOfMannedShips.toString()
            favouriteBT.setOnClickListener {
                favouriteListener(item)
            }
        }
    }

    class StarshipComparator : DiffUtil.ItemCallback<UIModel>() {
        override fun areItemsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
            val isSameRepoItem = oldItem is UIModel.StarshipModel
                    && newItem is UIModel.StarshipModel
                    && oldItem.starship.name == newItem.starship.name

            val isSameSeparatorItem = oldItem is UIModel.PeopleModel
                    && newItem is UIModel.PeopleModel
                    && oldItem.people.name == newItem.people.name

            return isSameRepoItem || isSameSeparatorItem
        }

        override fun areContentsTheSame(oldItem: UIModel, newItem: UIModel): Boolean =
            oldItem == newItem
    }
}