package suzp1984.github.io.exapidemo.app.design;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import suzp1984.github.io.exapidemo.R;

/**
 * Created by suzhenxi on 9/29/2016.
 */

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int COUNT = 50;
    private final int HEADER_VIEW_TYPE = 0;
    private final int NUMBER_VIEW_TYPE = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        switch (viewType) {
            case HEADER_VIEW_TYPE:
                View header = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item_layout, null);
                vh = new HeaderViewHolder(header);
                break;
            case NUMBER_VIEW_TYPE:
                View number = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_item_layout, null);
                vh = new NumberViewHolder(number);
                break;
            default:
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).header.setText("Header");
        } else if (holder instanceof NumberViewHolder) {
            ((NumberViewHolder) holder).number.setText(String.valueOf(position));
        }
    }

    @Override
    public int getItemCount() {
        return COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW_TYPE;
        }

        return NUMBER_VIEW_TYPE;
    }

    public static class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView number;
        public NumberViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView.findViewById(R.id.number);
        }
    }
}
