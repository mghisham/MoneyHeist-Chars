package apps.hm.mhchars.ui.stars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.ui.databinding.ItemStarBinding
import apps.hm.mhchars.ui.widgets.setOnSafeClickListener

class StarsAdapter(
    private val list: ArrayList<CharacterEntity>,
    private val onStarClick: (details: CharacterEntity, view: View) -> Unit
) : RecyclerView.Adapter<StarsAdapter.StarHolder>() {

    inner class StarHolder(private val binding: ItemStarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterEntity) {
            binding.item = item
            binding.position = adapterPosition
            binding.root.setOnSafeClickListener {
                onStarClick.invoke(item, binding.profPic)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStarBinding.inflate(inflater, parent, false)
        return StarHolder(binding)
    }

    override fun onBindViewHolder(holder: StarHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun update(newList: List<CharacterEntity>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, itemCount)
    }
}