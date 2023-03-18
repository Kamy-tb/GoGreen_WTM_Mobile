package com.example.conduitechangement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import com.example.conduitechangement.databinding.FragmentGameBinding
import com.example.conduitechangement.databinding.FragmentTreeBinding


class TreeFragment : Fragment() {
    lateinit var bindingtree: FragmentTreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingtree = FragmentTreeBinding.inflate( inflater , container , false )
        return bindingtree.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Change the state of the tree and number of points and the name of the user
        val pref = requireActivity().getSharedPreferences("fileConnexion" , AppCompatActivity.MODE_PRIVATE)
        val nom_user = pref.getString("nom" ,"Kamelia" )
        val pnts = pref.getInt("pnt" , 0)
        bindingtree.treetxt.text = "$nom_user's Tree"
        bindingtree.textView5.text = "You have a total of $pnts points"
        bindingtree.textView2.text = "$pnts points"
        bindingtree.textView4.text = "$nom_user"


        if (pnts < 300){
            bindingtree.imageView7.setImageResource(R.drawable.tree_niv1)
            bindingtree.imageView12.setImageResource(R.drawable.twostars)
        }
        else if ((pnts > 300) && (pnts < 2000)) {
            bindingtree.imageView7.setImageResource(R.drawable.tree_niv2)
            bindingtree.imageView12.setImageResource(R.drawable.twostars)
        }
        else if ((pnts >= 2000) && (pnts < 5000)){
            bindingtree.imageView7.setImageResource(R.drawable.tree_niv3)
            bindingtree.imageView12.setImageResource(R.drawable.threestars)
        }
        else {
            bindingtree.imageView7.setImageResource(R.drawable.tree_niv4)
            bindingtree.imageView12.setImageResource(R.drawable.fourstars)
        }
        bindingtree.gainptn.setOnClickListener(){
            val navController = findNavController()
            navController.navigate(R.id.action_treeFragment_to_gameFragment)
        }


    }


}