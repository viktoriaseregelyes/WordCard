package hu.bme.aut.android.wordcard.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.wordcard.WordActivity
import hu.bme.aut.android.wordcard.adapter.WordAdapter
import hu.bme.aut.android.wordcard.data.word.Word
import hu.bme.aut.android.wordcard.databinding.FragmentLearnDialogBinding

class LearnDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentLearnDialogBinding
    private var first: ArrayList<String>? = null
    private var second: ArrayList<String>? = null
    private var ind = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentLearnDialogBinding.inflate(LayoutInflater.from(context))

        val bundle = arguments
        first = bundle!!.getStringArrayList("first")
        second = bundle!!.getStringArrayList("second")

        binding.llearn.text = first?.get(ind).toString()

        binding.btnPrevious.setOnClickListener {
            ind--
            if(ind == -1)
                ind = first!!.size - 1
            binding.llearn.text = first?.get(ind).toString()
        }

        binding.btnNext.setOnClickListener {
            ind++
            if(ind == first!!.size)
                ind = 0
            binding.llearn.text = first?.get(ind).toString()
        }

        binding.llearn.setOnClickListener {
            if(binding.llearn.text == second?.get(ind).toString())
                binding.llearn.text = first?.get(ind).toString()
            else
                binding.llearn.text = second?.get(ind).toString()
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    companion object {
        const val TAG = "LearnDialogFragment"
    }
}