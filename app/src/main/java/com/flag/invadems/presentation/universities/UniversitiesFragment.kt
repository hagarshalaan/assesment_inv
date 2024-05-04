package com.flag.invadems.presentation.universities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flag.invadems.Constants.DATA
import com.flag.invadems.Constants.FRAGMENT_KEY_ARGS
import com.flag.invadems.databinding.UniversitiesFragmentBinding
import com.flag.invadems.presentation.universities.adapter.UniversitiesAdapter

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversitiesFragment : Fragment() {

    private var _binding: UniversitiesFragmentBinding? = null

    private lateinit var binding: UniversitiesFragmentBinding
    private val viewModel: UniversitiesViewModel by viewModels()

    private lateinit var universitiesAdapter: UniversitiesAdapter //by lazy { UniversitiesAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UniversitiesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        observeViewModel()
        binding.swipeRefresh.setOnRefreshListener {
            searchUniversities()
        }
        searchUniversities()
    }
private  fun  searchUniversities(){
    viewModel.searchUniversities("United Arab Emirates")

}
    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState.isLoading) {
                binding.progressBar.visibility = View.VISIBLE

            } else {
                binding.progressBar.visibility = View.GONE
            }

            universitiesAdapter.updateData(uiState.universitiesList)

            binding.swipeRefresh.isRefreshing = false

            if (uiState.universityClicked != null) {

                setFragmentResult(FRAGMENT_KEY_ARGS, bundleOf(DATA to uiState.universityClicked ))
                val action = UniversitiesFragmentDirections.actionUniversityFragmentToDetailsFragment(uiState.universityClicked!!)

                findNavController().navigate(action)
                uiState.universityClicked = null
            }
        }
    }

    private fun initRecyclerView() {
        universitiesAdapter = UniversitiesAdapter(viewModel)
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = universitiesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
//data class University (
//    val domains: List<String>,
//    val stateProvince: String?,
//    val webPages: List<String>?,
//    val country: String?,
//    val alphaTwoCode: String?
//):Serializable