package com.patriciafiona.mario_world.core.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patriciafiona.mario_world.core.domain.model.Character
import com.patriciafiona.mario_world.core.R
import com.patriciafiona.mario_world.core.databinding.ItemListCharacterBinding
import com.patriciafiona.mario_world.core.utils.MediaPlayerManager
import com.patriciafiona.mario_world.core.utils.Utils.fadeVisibility
import java.util.ArrayList

class MarioAdapter(private val context: Context) : RecyclerView.Adapter<MarioAdapter.ListViewHolder>() {

    private var listData = ArrayList<Character>()
    var onItemClick: ((Character) -> Unit)? = null

    fun setData(newListData: List<Character>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_character, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCharacterBinding.bind(itemView)

        var isExpand: Boolean = false
        var prevHeight: Int = 0
        val soundManager = MediaPlayerManager(context)

        fun bind(character: Character) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(if (isExpand) character.imageOpen else character.imageClose)
                    .into(ivPhoto)

                tvName.text = character.name
                tvDesc.text = character.description

                val bgColors = character.backgroundColor.split(",")
                cardContainer.setCardBackgroundColor(
                    Color.rgb(bgColors[0].toInt(), bgColors[1].toInt(), bgColors[2].toInt())
                )

                btnExpand.setOnClickListener {
                    isExpand = !isExpand

                    Glide.with(itemView.context)
                        .load(if (isExpand) character.imageOpen else character.imageClose)
                        .into(ivPhoto)

                    btnExpand.setImageResource(if (isExpand) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)

                    if(prevHeight == 0){ prevHeight =  cardContainer.measuredHeight }

                    val anim = ValueAnimator.ofInt(cardContainer.measuredHeight, if (isExpand) prevHeight + 200 else prevHeight)
                    anim.addUpdateListener { valueAnimator ->
                        val `val` = valueAnimator.animatedValue as Int
                        val layoutParams: ViewGroup.LayoutParams = cardContainer.layoutParams
                        layoutParams.height = `val`
                        cardContainer.layoutParams = layoutParams
                    }
                    anim.duration = 700
                    anim.start()

                    if (isExpand){
                        btnSeeDetail.fadeVisibility(View.VISIBLE, 700)
                        soundManager.startSound(R.raw.slide_down)
                    }else{
                        btnSeeDetail.fadeVisibility(View.GONE, 2000)
                        soundManager.startSound(R.raw.slide_up)
                    }
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}