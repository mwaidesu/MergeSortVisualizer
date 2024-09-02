package com.example.merge.presentation.state


import androidx.compose.ui.graphics.Color
import com.example.merge.domain.model.SortState

data class SortInfoUiItem(
    val id: String,
    val depth: Int,
    val sortState: SortState,
    val sortParts: List<List<Int>>,
    val color: Color
)
