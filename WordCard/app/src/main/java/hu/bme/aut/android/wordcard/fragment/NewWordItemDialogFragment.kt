package hu.bme.aut.android.wordcard.fragment

import hu.bme.aut.android.wordcard.R
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.wordcard.data.word.Word
import hu.bme.aut.android.wordcard.databinding.FragmentNewWordItemDialogBinding

class NewWordItemDialogFragment : DialogFragment() {
    interface NewWordDialogListener {
        fun onWordCreated(newItem: Word)
    }

    private lateinit var listener: NewWordDialogListener
    private lateinit var binding: FragmentNewWordItemDialogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewWordDialogListener
            ?: throw RuntimeException("Activity must implement the NewWordItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNewWordItemDialogBinding.inflate(LayoutInflater.from(context))

        binding.btnOk.setOnClickListener {
            listener.onWordCreated(getWord())
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_word)
            .setView(binding.root)
            .create()
    }

    companion object {
        const val TAG = "NewWordItemDialogFragment"
    }

    private fun getWord() = Word(
        first_language = binding.etWordFirst.text.toString(),
        second_language = binding.etWordSecond.text.toString()
    )
}