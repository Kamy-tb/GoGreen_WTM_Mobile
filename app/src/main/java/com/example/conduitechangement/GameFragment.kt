package com.example.conduitechangement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.conduitechangement.databinding.FragmentGameBinding
import androidx.navigation.fragment.findNavController


class GameFragment : Fragment() {
    lateinit var bindinggames: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindinggames = FragmentGameBinding.inflate( inflater , container , false )
        return bindinggames.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = requireActivity().getSharedPreferences("fileConnexion" , AppCompatActivity.MODE_PRIVATE)
        val pnts = pref.getInt("pnt" , 0)
        val nom_user = pref.getString("nom" ,"Kamelia" )
        bindinggames.textView2.text = "$pnts's Points"
        bindinggames.textView4.text = "$nom_user"

        bindinggames.beforeafter.setOnClickListener(){
            // just to test if the tree is growing
            val pref = requireActivity().getSharedPreferences("fileConnexion", AppCompatActivity.MODE_PRIVATE)
            var pnts = pref.getInt("pnt" , 0)
            pnts += 1000
            pref.edit {
                putInt("pnt" , pnts)
            }
            Toast.makeText(context, "update points : $pnts", Toast.LENGTH_SHORT).show();
        }

        bindinggames.tree.setOnClickListener(){
            val navController = findNavController()
            navController.navigate(R.id.action_gameFragment_to_treeFragment)
        }

    }


}