package com.dani_chuks.andeladeveloper.starwars.util

import android.content.Context
import androidx.appcompat.app.AlertDialog


class DialogBuilder(private val dialogContext: Context) {
    private var dialogMessage: String? = null
    private var positiveButtonText: String? = null
    private var negativeButtonText: String? = null
    private var cancellable: Boolean? = null
    private var positiveButtonClickListener: (() -> Unit)? = null
    private var negativeButtonClickListener: (() -> Unit)? = null
    private var dialogDismissListener: (() -> Unit)? = null

    fun setMessage(text: String?): DialogBuilder {
        this.dialogMessage = text
        return this
    }

    fun setCancellable(cancellable: Boolean): DialogBuilder {
        this.cancellable = cancellable
        return this
    }

    fun setPositiveAction(text: String?, action: (() -> Unit)? = null): DialogBuilder {
        positiveButtonText = text
        positiveButtonClickListener = action
        return this
    }

    fun setNegativeAction(text: String?, action: (() -> Unit)? = null): DialogBuilder {
        negativeButtonText = text
        negativeButtonClickListener = action
        return this
    }

    fun setDialogDismissListener(listener: () -> Unit): DialogBuilder {
        this.dialogDismissListener = listener
        return this
    }

    fun build(): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(dialogContext)
        dialogBuilder.apply {
            dialogMessage?.let { setMessage(it) }
            cancellable?.let { this.setCancelable(it) }
            positiveButtonText?.let{
                setPositiveButton(it) { _, _ -> positiveButtonClickListener?.invoke() }
            }
            negativeButtonText?.let{
                setNegativeButton(it) { _, _ -> negativeButtonClickListener?.invoke() }
            }
        }
        val dialog = dialogBuilder.create()
        dialogDismissListener?.let { listener ->
            dialog.setOnDismissListener { listener.invoke() }
        }
        return dialog
    }
}