package com.example.memes.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.memes.databinding.FragmentCaptionBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.memes.R


class CaptionFragment : Fragment(){
    private lateinit var binding: FragmentCaptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_caption, container, false)
        binding.button.setOnClickListener {
            val bundle = Bundle()
            if(binding.caption1.text.toString().isNotEmpty() and binding.caption2.text.toString().isNotEmpty()) {
                bundle.putString("id", arguments?.getString("id"))
                bundle.putString("caption1", binding.caption1.text.toString())
                bundle.putString("caption2", binding.caption2.text.toString())
                Navigation.findNavController(it).navigate(R.id.action_captionFragment_to_memeFragment, bundle)
            }
            else {
                Toast.makeText(context, "error: fill in caption(s)!", Toast.LENGTH_SHORT).show()
            }


        }

        return binding.root


    }


}

