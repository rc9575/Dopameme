package com.example.memes.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.memes.API.MemeApi
import com.example.memes.Model.PostRes
import com.example.memes.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [memeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [memeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class memeFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val meme = view.findViewById<ImageView>(R.id.memeImage)
        MemeApi.retrofitService.makeMeme(arguments?.getString("id"), "", "",
            arguments?.getString("caption1"), arguments?.getString("caption2")).enqueue(
            object: Callback<PostRes> {
                override fun onResponse(call: Call<PostRes>, response: Response<PostRes>) {

                    val data: String = response.body()?.data?.url ?: ""
                    val imgUri = data.toUri().buildUpon().scheme("https").build()
                    Glide.with(meme.context)
                        .load(imgUri)
                        .into(meme)

                }
                override fun onFailure(call: Call<PostRes>, t: Throwable) {

                }

            })

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meme, container, false)
    }
}
