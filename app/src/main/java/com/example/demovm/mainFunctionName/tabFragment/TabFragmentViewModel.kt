package com.example.demovm.mainFunctionName.tabFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demovm.base.BaseViewModel
import com.example.demovm.mainFunctionName.tabFragment.item.ItemDetail
import javax.inject.Inject

private const val TAG = "TabFragmentViewModel"
class TabFragmentViewModel @Inject constructor(): BaseViewModel(){

    var _itemDetail = MutableLiveData<ItemDetail>().apply { Log.i(TAG, "000 _itemDetail: ")}
    var itemDetail : LiveData<ItemDetail> = _itemDetail.apply { Log.i(TAG, "111 itemDetail: ")}
    var itemList : MutableList<ItemDetail> = arrayListOf()

    var itemListEvent = MutableLiveData<Boolean>().apply { value = true }
    var tabKind = MutableLiveData<Boolean>()
    init {
        // add itemList into view
        itemListAdd()
    }

    private fun itemListAdd() {
        itemList = arrayListOf()
        itemList.add(
            ItemDetail(
                itemID = 1,
                kind = 1,
                name = "one",
                imgName = "one"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 2,
                kind = 2,
                name = "two",
                imgName = "two"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 3,
                kind = 1,
                name = "three",
                imgName = "three"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 4,
                kind = 2,
                name = "four",
                imgName = "four"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 5,
                kind = 1,
                name = "five",
                imgName = "five"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 6,
                kind = 2,
                name = "six",
                imgName = "six"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 7,
                kind = 1,
                name = "seven",
                imgName = "seven"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 8,
                kind = 2,
                name = "eight",
                imgName = "eight"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 9,
                kind = 1,
                name = "nine",
                imgName = "nine"
            )
        )
        itemList.add(
            ItemDetail(
                itemID = 0,
                kind = 2,
                name = "zero",
                imgName = "zero"
            )
        )
        itemListEvent.value = true
    }

    fun itemClick(itemDetail: ItemDetail){
        this._itemDetail.value = itemDetail
        this._itemDetail.value = null
//        itemDetail
    }

}