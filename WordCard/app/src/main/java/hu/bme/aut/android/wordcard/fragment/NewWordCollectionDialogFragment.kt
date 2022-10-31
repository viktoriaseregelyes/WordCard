package hu.bme.aut.android.wordcard.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.wordcard.R
import hu.bme.aut.android.wordcard.data.collection.WordCollection
import hu.bme.aut.android.wordcard.databinding.FragmentNewWordCollectionDialogBinding

class NewWordCollectionDialogFragment() : DialogFragment() {
    interface NewWordCollectionDialogListener {
        fun onWordCollectionCreated(newItem: WordCollection)
    }

    private lateinit var listener: NewWordCollectionDialogListener
    private lateinit var binding: FragmentNewWordCollectionDialogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewWordCollectionDialogListener
            ?: throw RuntimeException("Activity must implement the NewWordCollectionDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNewWordCollectionDialogBinding.inflate(LayoutInflater.from(context))

        binding.btnOk.setOnClickListener {
            listener.onWordCollectionCreated(getWordCollection())
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_collection)
            .setView(binding.root)
            .create()
    }

    companion object {
        const val TAG = "NewWordCollectionDialogFragment"
    }

    private fun getWordCollection() = WordCollection(
        name = binding.etName.text.toString(),
        description = binding.etDescription.text.toString(),
    )
}