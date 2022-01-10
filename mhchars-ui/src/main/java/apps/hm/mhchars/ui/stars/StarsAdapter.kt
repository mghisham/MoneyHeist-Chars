package apps.hm.mhchars.ui.stars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.ui.databinding.ItemStarBinding
import apps.hm.mhchars.ui.widgets.setOnSafeClickListener

/**
 * RecyclerView Adapter to display *Characters*.
 *
 * @property list the list of Characters in this Adapter.
 * @property onStarClick is the item click listener.
 */
class StarsAdapter(
    private val list: ArrayList<CharacterEntity>,
    private val onStarClick: (details: CharacterEntity, view: View) -> Unit
) : RecyclerView.Adapter<StarsAdapter.StarHolder>() {

    /**
     * RecyclerView ViewHolder to display a Character.
     *
     * @property binding the binding class item layout.
     */
    inner class StarHolder(private val binding: ItemStarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Method to bind data to layout.
         */
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

    /**
     * Method to update the data set of adapter.
     */
    fun update(newList: List<CharacterEntity>) {
        list.clear()
        list.addAll(newList)
        notifyItemRangeChanged(0, list.size)
    }
}