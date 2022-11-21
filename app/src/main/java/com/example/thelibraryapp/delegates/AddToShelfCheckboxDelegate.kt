package com.example.thelibraryapp.delegates

import com.example.thelibraryapp.data.vos.ShelfVO

interface AddToShelfCheckboxDelegate {
    fun onCheckedCheckbox(
        shelf: ShelfVO,
        isChecked: Boolean
    )
}