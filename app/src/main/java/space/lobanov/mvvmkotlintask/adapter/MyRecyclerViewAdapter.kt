package space.lobanov.mvvmkotlintask.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.internal.userAgent
import space.lobanov.mvvmkotlintask.R
import space.lobanov.mvvmkotlintask.model.Hits

class MyRecyclerViewAdapter(listOfHits: List<Hits>) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    private val listOfHits: List<Hits> = listOfHits
    private var mClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfHits[position]
        val url: String = item.userImageUrl
        val name:String = item.userName
        Glide.with(holder.imgView.context).load(url).into(holder.imgView)
        holder.textView.setText(name)

    }

    override fun getItemCount(): Int {
        return listOfHits.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var imgView: ImageView = itemView.findViewById(R.id.img)
        var textView:TextView = itemView.findViewById(R.id.userTxtView)
        var textView1:TextView = itemView.findViewById(R.id.emailText)

        fun create(parent: ViewGroup): ViewHolder {//companion object petqa lini
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_row, parent, false)
            return ViewHolder(view)

        }
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }


    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}