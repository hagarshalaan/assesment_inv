package com.flag.invadems.presentation.university_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flag.invadems.databinding.UniversityDetailsFragmentBinding

class UniversityDetailsFragment : Fragment() {

    private lateinit var binding: UniversityDetailsFragmentBinding

    private val args: UniversityDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UniversityDetailsFragmentBinding.inflate(inflater, container, false)


        binding.btnRefresh.setOnClickListener {
            // Navigate back to UniversitiesFragment and refresh the list
            findNavController().popBackStack()
        }

        setViews()


        return binding.root
    }
private fun  setViews(){
    val university = args.university

    binding.textUniversityName.text = university.name
    binding.textUniversityState.text = university.stateProvince
    binding.textCountry.text = university.country
    binding.textCountryCode.text = university.alphaTwoCode

    if (args.university.webPages?.isNotEmpty() == true)
        binding.textWebPage.text = university.webPages!![0]

}

}
