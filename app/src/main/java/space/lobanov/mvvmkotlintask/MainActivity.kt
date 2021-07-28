package space.lobanov.mvvmkotlintask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import space.lobanov.mvvmkotlintask.adapter.MyRecyclerViewAdapter
import space.lobanov.mvvmkotlintask.model.Hits
import space.lobanov.mvvmkotlintask.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import space.lobanov.mvvmkotlintask.model.ApiInterface

class MainActivity : AppCompatActivity() {

    private val viewmodel: MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("dwd", "getList")
        viewmodel.getMutableLiveData()
        viewmodel.hitList.observe(this, Observer {
            Log.d("dwd", it.size.toString())
            if (it.isNotEmpty() && it != null) {
                initRecyclerView(it)
            }
        })

    }

    fun initRecyclerView(list: List<Hits>) {
        findViewById<RecyclerView>(R.id.mainImage).apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            PagerSnapHelper().attachToRecyclerView(this)
            adapter = MyRecyclerViewAdapter( list)
        }
    }
}


