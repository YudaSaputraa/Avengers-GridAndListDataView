package com.kom.avengerslist.presentation.avengerslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kom.avengerslist.R
import com.kom.avengerslist.data.datasource.AvengersDataSourceImpl
import com.kom.avengerslist.data.datasource.AvengersDataSrouce
import com.kom.avengerslist.data.model.Avenger
import com.kom.avengerslist.databinding.FragmentAvengersListBinding
import com.kom.avengerslist.presentation.avengerdetail.AvengersDetailFragment
import com.kom.avengerslist.presentation.avengerslist.adapter.AvengersAdapter

class AvengersListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAvengersListBinding
    private var adapter: AvengersAdapter? = null
    private val dataSource: AvengersDataSrouce by lazy {
        AvengersDataSourceImpl()
    }

    private var isUsingGridMode: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAvengersListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAvengersList(true)
        setClickAction()
    }

    private fun setClickAction() {
        binding.btnChangeListMode.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setButtonText(isUsingGridMode)
            bindAvengersList(isUsingGridMode)
        }
    }

    private fun setButtonText(usingGridMode: Boolean) {
        binding.btnChangeListMode.setText(
            if (isUsingGridMode)
                R.string.text_list_mode
            else
                R.string.text_grid_mode
        )
    }

    private fun bindAvengersList(isUsingGrid: Boolean) {
        val listMode =
            if (isUsingGrid)
                AvengersAdapter.MODE_GRID
            else
                AvengersAdapter.MODE_LIST
        adapter =
            AvengersAdapter(
                listMode = listMode,
                listener = object : AvengersAdapter.OnItemClickedListener<Avenger> {
                    override fun onItemClicked(item: Avenger) {
                        navigateToDetail(item)
                    }
                }
            )
        binding.rvAvengersList.apply {
            adapter = this@AvengersListFragment.adapter
            layoutManager = GridLayoutManager(
                requireContext(),
                if (isUsingGrid)
                    2
                else

                    1
            )
        }
        adapter?.submitData(dataSource.getAvengerMembers())
    }

    private fun navigateToDetail(item: Avenger) {
    val navController = findNavController()
        val bundle = bundleOf(Pair(AvengersDetailFragment.EXTRAS_ITEM, item))
        navController.navigate(R.id.action_avengersListFragment_to_avengersDetailFragment, bundle)
    }

}