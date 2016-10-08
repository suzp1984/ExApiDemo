package suzp1984.github.io.exapidemo.app.nav;

import android.support.design.internal.NavigationMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 10/8/2016.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final static int MENU_TITLE_TYPE = 100;
    private final static int MENU_ITEM_TYPE = 101;

    private final ArrayList<MenuItem> menuItems = new ArrayList<>();

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MenuViewHolder vh = null;

        switch (viewType) {
            case MENU_TITLE_TYPE:
                View titleView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_title_layout, null);
                vh = new MenuTitleViewHolder(titleView);
                break;
            case MENU_ITEM_TYPE:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_item_layout, null);
                vh = new MenuItemViewHolder(itemView);
                break;
            default:
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);

        if (holder instanceof MenuTitleViewHolder) {
            MenuTitleViewHolder titleVH = (MenuTitleViewHolder) holder;
            titleVH.title.setText(item.getTitle());

        } else if (holder instanceof MenuItemViewHolder) {
            MenuItemViewHolder itemVH = (MenuItemViewHolder) holder;
            itemVH.title.setText(item.getTitle());
            itemVH.icon.setImageDrawable(item.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        MenuItem item = menuItems.get(position);

        if (item.hasSubMenu()) {
            return MENU_TITLE_TYPE;
        }

        return MENU_ITEM_TYPE;
    }

    public void setMenuItems(NavigationMenu menu) {
        menuItems.clear();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItems.add(menuItem);

            if (menuItem.hasSubMenu()) {
                SubMenu subMenu = menuItem.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subItem = subMenu.getItem(j);
                    menuItems.add(subItem);
                }
            }
        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        public MenuViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MenuTitleViewHolder extends MenuViewHolder {

        TextView title;

        public MenuTitleViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.menu_title);
        }
    }

    public class MenuItemViewHolder extends MenuViewHolder {

        ImageView icon;
        TextView title;

        public MenuItemViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.menu_icon);
            title = (TextView) itemView.findViewById(R.id.menu_title);
        }
    }
}
