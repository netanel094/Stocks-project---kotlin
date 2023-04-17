package com.example.buildingblocks.ui
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.buildingblocks.Item
import com.example.buildingblocks.ItemMenager
import com.example.buildingblocks.R
import com.example.buildingblocks.databinding.AddItemBinding


class AddItemFragment : Fragment() {
    private var _binding :AddItemBinding? = null
    private val binding get() = _binding!!
    private var imageUri : Uri? = null
    val pickImageLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()){
            binding.stockLogo.setImageURI(it)
            if (it != null) {
                requireActivity().contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                imageUri = it
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddItemBinding.inflate(inflater, container, false)

        binding.finishBtn.setOnClickListener {
            if(binding.itemTitle.text.toString().isBlank() || binding.itemTitle.text.toString().matches(".*\\d.*".toRegex())) {
                Toast.makeText(requireContext(), "Please enter a valid stock symbol", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.itemTitle.text.toString().matches(".*\\d.*".toRegex())) {
                Toast.makeText(requireContext(), "The symbol must not contain numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.itemTitle.text.toString().length > 5) {
                Toast.makeText(requireContext(), "The symbol is more than 5 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.itemTitle.text.toString().length < 2) {
                Toast.makeText(requireContext(), "The symbol is less than 2 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.compName.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Please enter the company name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.compName.text.toString().matches(".*\\d.*".toRegex())) {
                Toast.makeText(requireContext(), "Company name must not include numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(imageUri == null) {
                Toast.makeText(requireContext(), "Please select an image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = Item(
                binding.itemTitle.text.toString(),
                binding.compName.text.toString(),
                binding.stockPrice.text.toString(),
                imageUri
            )
            ItemMenager.add(item)
            findNavController().navigate(R.id.action_addItemFragment_to_allItemsFragment)
        }


        binding.imageButton.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}