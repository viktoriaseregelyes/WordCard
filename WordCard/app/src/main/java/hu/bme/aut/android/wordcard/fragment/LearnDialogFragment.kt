package hu.bme.aut.android.wordcard.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.wordcard.databinding.FragmentLearnDialogBinding

class LearnDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentLearnDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentLearnDialogBinding.inflate(LayoutInflater.from(context))

        binding.btnPrevious.setOnClickListener {
            dismiss()
        }

        binding.btnNext.setOnClickListener {
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    companion object {
        const val TAG = "LeardDialogFragment"
    }
}