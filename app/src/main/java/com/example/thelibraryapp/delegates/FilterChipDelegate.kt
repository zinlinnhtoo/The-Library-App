package com.example.thelibraryapp.delegates

import com.example.thelibraryapp.data.vos.CategoryVO

interface FilterChipDelegate {
    fun onTapChip(
        category: CategoryVO
    )
}