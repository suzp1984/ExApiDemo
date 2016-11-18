package suzp1984.github.io.exapidemo.app.nav

import android.support.design.internal.NavigationMenu
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

import suzp1984.github.io.exapidemo.R

/**
 * Created by moses on 10/8/2016.
 */

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val menuItems = ArrayList<MenuItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder? {
        var vh: MenuViewHolder? = null

        when (viewType) {
            MENU_TITLE_TYPE -> {
                val titleView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.menu_title_layout, null)
                vh = MenuTitleViewHolder(titleView)
            }
            MENU_ITEM_TYPE -> {
                val itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.menu_item_layout, null)
                vh = MenuItemViewHolder(itemView)
            }
            else -> {
            }
        }

        return vh
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuItems[position]

        if (holder is MenuTitleViewHolder) {
            holder.title.text = item.title

        } else if (holder is MenuItemViewHolder) {
            holder.title.text = item.title
            holder.icon.setImageDrawable(item.icon)
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = menuItems[position]

        if (item.hasSubMenu()) {
            return MENU_TITLE_TYPE
        }

        return MENU_ITEM_TYPE
    }

    fun setMenuItems(menu: NavigationMenu) {
        menuItems.clear()

        for (i in 0..menu.size() - 1) {
            val menuItem = menu.getItem(i)
            menuItems.add(menuItem)

            if (menuItem.hasSubMenu()) {
                val subMenu = menuItem.subMenu
                for (j in 0..subMenu.size() - 1) {
                    val subItem = subMenu.getItem(j)
                    menuItems.add(subItem)
                }
            }
        }
    }

    open inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class MenuTitleViewHolder(itemView: View) : MenuViewHolder(itemView) {

        internal var title: TextView

        init {

            title = itemView.findViewById(R.id.menu_title) as TextView
        }
    }

    inner class MenuItemViewHolder(itemView: View) : MenuViewHolder(itemView) {

        internal var icon: ImageView
        internal var title: TextView

        init {

            icon = itemView.findViewById(R.id.menu_icon) as ImageView
            title = itemView.findViewById(R.id.menu_title) as TextView
        }
    }

    companion object {

        private val MENU_TITLE_TYPE = 100
        private val MENU_ITEM_TYPE = 101
    }
}
