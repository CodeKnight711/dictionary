package uz.salikhdev.dictonariy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.zerobranch.layout.SwipeLayout
import uz.salikhdev.dictonariy.R
import uz.salikhdev.dictonariy.databinding.ItemWordBinding
import uz.salikhdev.dictonariy.db.WordModel

class WordAdapter : BaseAdapter() {

    private val data = ArrayList<WordModel>()
    var onClickDelete: ((id: Int) -> Unit)? = null
    var onClickSave: ((id: Int) -> Unit)? = null

    fun setData(data: List<WordModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): WordModel {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemWordBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        val model = getItem(position)
        binding.textUzb.text = model.uzb
        binding.textEng.text = model.eng

        if (model.isFav == 0) {
            binding.rightView.setImageResource(R.drawable.ic_fav_border)
        } else {
            binding.rightView.setImageResource(R.drawable.ic_fav_fill)
        }

        binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.LEFT) {
                    onClickSave?.invoke(model.id)
                    binding.swipeLayout.close()
                }

            }

            override fun onClose() {

            }

        })

        binding.root.setOnLongClickListener {
            onClickDelete?.invoke(model.id)
            true
        }

        return binding.root
    }
}