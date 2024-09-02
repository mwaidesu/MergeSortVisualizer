package com.example.merge.domain.model

data class SortInfo (
    val id:String,
    val depth:Int,
    val sortParts: List<Int>,
    val sortState:SortState

)

enum class SortState (val value:Int){
    DIVIDED(0),
    MERGED(1)
}
