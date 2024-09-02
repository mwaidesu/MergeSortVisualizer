package com.example.merge.presentation.state

import androidx.compose.runtime.mutableStateListOf
import com.example.merge.domain.model.use_case.MergeSortUseCase
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.UUID


class SortViewModel (
    private val mergeSortUseCase: MergeSortUseCase = MergeSortUseCase()

):ViewModel(){
    var listToSort = mutableListOf<Int>()

    init{
        for(i in 0 until 8) {
            listToSort.add(
                (10..99).random()
            )

        }
    }

    var sortInfoUiItemList = mutableStateListOf<SortInfoUiItem>()
    fun startSorting(){
        viewModelScope.launch {
            mergeSortUseCase(listToSort, 0)
        }
        subscribeToSortChanges()
    }

    private var job:Job? = null

    private fun subscribeToSortChanges(){
        job?.cancel()
        job = viewModelScope.launch {
            mergeSortUseCase.sortFlow.collect { sortInfo ->
                val depthAlreadyExistsListIndex = sortInfoUiItemList.indexOfFirst {
                    it.depth == sortInfo.depth && it.sortState == sortInfo.sortState
                }

                if(depthAlreadyExistsListIndex == -1){
                    sortInfoUiItemList.add(
                        SortInfoUiItem(
                            id = UUID.randomUUID().toString(),
                            depth = sortInfo.depth,
                            sortState = sortInfo.sortState,
                            sortParts = listOf(sortInfo.sortParts),
//                            color =  Color(
//                                (0 .. 255).random(),
//                                (0 .. 255).random(),
//                                (0 .. 255).random(),
//                                255,
//                            )

                            color = Color.White,


                        )
                    )
                }
                else{
                    val currentPartList =
                        sortInfoUiItemList[depthAlreadyExistsListIndex].sortParts.toMutableList()
                    currentPartList.add(sortInfo.sortParts)
                    sortInfoUiItemList[depthAlreadyExistsListIndex] =
                        sortInfoUiItemList[depthAlreadyExistsListIndex].copy(
                            sortParts = currentPartList
                        )

                }
                sortInfoUiItemList.sortedWith(
                    compareBy(
                        {
                            it.sortState
                        },
                        {
                            it.depth
                        }
                    )
                )

            }
        }

    }

}