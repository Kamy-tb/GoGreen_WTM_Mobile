package com.example.conduitechangement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.conduitechangement.databinding.FragmentFeedBinding
import com.example.conduitechangement.databinding.FragmentTreeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException


class FeedFragment : Fragment() {
    lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate( inflater , container , false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            GetFeeds()
        }
        catch (_: Exception){
            Toast.makeText(
                context,
                "Error",
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    private fun GetFeeds() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<List<Feed>> = RetrofitService.endpoint.getfeed()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            binding.recyclerview.layoutManager = GridLayoutManager(requireActivity(), 1)
                            binding.recyclerview.adapter = FeedAdapter(requireActivity(), data)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "No data",
                            Toast.LENGTH_SHORT
                        ).show();
                                           }
                }
            } catch (_: InterruptedException) {

            } catch (_: IOException) {

            } catch (_: NullPointerException) {

            } catch (_: IllegalStateException) {

            }
        }
    }
}