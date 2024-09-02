package com.example.merge.domain.model.use_case

import com.example.merge.domain.model.SortInfo
import com.example.merge.domain.model.SortState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.UUID

class MergeSortUseCase {
    val sortFlow = MutableSharedFlow<SortInfo>()

    suspend operator fun  invoke(list:List<Int>, depth:Int): List<Int>{
        delay(500)
        sortFlow.emit(
            SortInfo(
                UUID.randomUUID().toString(),
                depth = depth,
                sortParts = list,
                sortState = SortState.DIVIDED
            )
        )

        val listSize = list.size
        if(listSize <=1){
            return list
        }
        var leftList = list.slice(0 until  (listSize + 1)/2)
        var rightList = list.slice((listSize + 1)/2 until listSize)

        leftList = this(leftList, depth + 1)
        rightList = this( rightList, depth + 1)

        return merge(
            leftList.toMutableList(),
            rightList.toMutableList(),
            depth
        )


    }

    private suspend fun merge(
        leftList: MutableList<Int>,
        righttList: MutableList<Int>,
        depth: Int
    ): List<Int>{
      val mergeList = mutableListOf<Int>()
      while(leftList.isNotEmpty() && righttList.isNotEmpty()){
          if (leftList.first() <= righttList.first()){
(              mergeList.add(mergeList.size, leftList.removeFirst())
)          } else
          {
              mergeList.add(mergeList.size, righttList.removeFirst())
          }
      }
        mergeList.addAll(leftList)
        mergeList.addAll(righttList)

        delay(500)
        sortFlow.emit(
            SortInfo(
                UUID.randomUUID().toString(),
                depth = depth,
                sortParts =  mergeList,
                sortState =  SortState.MERGED
            )
        )

        return  mergeList;
    }

}