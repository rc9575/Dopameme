package com.example.memes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memes.Adapter.MainAdapter
import com.example.memes.Model.GetRes
import com.example.memes.API.MemeApi
import com.example.memes.Model.MemeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.memes.databinding.FragmentOverviewBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class OverviewFragment : Fragment(), View.OnClickListener {


    private lateinit var binding: FragmentOverviewBinding
    var mainAdapter: MainAdapter? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOverviewBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MemeApi.retrofitService.getProperties().enqueue(
            object: Callback<GetRes> {
                override fun onResponse(call: Call<GetRes>, response: Response<GetRes>) {

                    val data: List<MemeData> = response.body()?.data?.memes ?: listOf(
                        MemeData(
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        )
                    )
                    mainAdapter = MainAdapter(data, this@OverviewFragment)
                    binding.recyclerView.adapter = mainAdapter

                }
                override fun onFailure(call: Call<GetRes>, t: Throwable) {

                }

        })

    }
    override fun onClick(v: View?) {

    }



}
