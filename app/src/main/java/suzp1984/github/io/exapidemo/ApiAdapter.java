package suzp1984.github.io.exapidemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by jacobsu on 9/3/16.
 */
public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ApiViewHolder> {

    private final List<Map<String, Object>> mApiObjs;

    public ApiAdapter(@NonNull  List<Map<String, Object>> objs) {
        mApiObjs = objs;
    }

    @Override
    public ApiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ApiViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final ApiViewHolder holder, int position) {
        final Map<String, Object> item = mApiObjs.get(position);

        String title = (String) item.get("title");
        holder.textView.setText(title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Intent) item.get("intent"));
                intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mApiObjs.size();
    }

    public static class ApiViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ApiViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
