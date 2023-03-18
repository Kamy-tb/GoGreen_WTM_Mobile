package com.example.conduitechangement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.conduitechangement.databinding.FragmentFeedBinding
import com.example.conduitechangement.databinding.FragmentRankingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException



class RankingFragment : Fragment() {
    lateinit var binding: FragmentRankingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRankingBinding.inflate( inflater , container , false )
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = requireActivity().getSharedPreferences("fileConnexion" , AppCompatActivity.MODE_PRIVATE)
        val pnts = pref.getInt("pnt" , 0)
        val nom_user = pref.getString("nom" ,"Kamelia" )
        binding.textView2.text = "$pnts's Points"
        binding.textView4.text = "$nom_user"

        try {
            GetRankings()
        }
        catch (_: Exception){
            Toast.makeText(
                context,
                "Error",
                Toast.LENGTH_SHORT
            ).show();
        }
        binding.textView2.setOnClickListener(){

        }
    }
    private fun GetRankings() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<List<Ranking>> = RetrofitService.endpoint.getranking()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            binding.recyclerview2.layoutManager = GridLayoutManager(requireActivity(), 1)
                            binding.recyclerview2.adapter = RankingAdapter(requireActivity(), data)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Error",
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