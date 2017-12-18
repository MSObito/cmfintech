package com.example.obito.listviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by obito on 2017/12/14.
 */

public class FruitAdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Fruit> mFruitList;

    private static final int TYPE_ITEM   = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE     = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;

        View fruitView;

        public ViewHolder(View view){
            super(view);
            fruitView=view;
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView loadTextView;
        LinearLayout loadLinearLayout;

        View footerView;

        public FooterViewHolder(View view){
            super(view);
            footerView=view;
            progressBar=(ProgressBar)footerView.findViewById(R.id.pbLoad);
            loadTextView=(TextView)footerView.findViewById(R.id.tvLoadText);
            loadLinearLayout=(LinearLayout)footerView.findViewById(R.id.load_more_layout);
        }
    }

    public FruitAdapterRecycler(List<Fruit> fruitList){
        mFruitList=fruitList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ITEM){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            holder.fruitView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=holder.getAdapterPosition();
                    Fruit fruit=mFruitList.get(position);
                    Toast.makeText(v.getContext(),"you clicked view"+fruit.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            holder.fruitImage.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=holder.getAdapterPosition();
                    Fruit fruit=mFruitList.get(position);

                    Toast.makeText(v.getContext(),"you clicked is image!",Toast.LENGTH_SHORT).show();
                }
            });
            return holder;
        }
        else if(viewType==TYPE_FOOTER){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more,parent,false);
            final FooterViewHolder footerViewHolder=new FooterViewHolder(view);
            return footerViewHolder;
        }
            return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder=(ViewHolder)holder;
            Fruit fruit = mFruitList.get(position);
            viewHolder.fruitImage.setImageResource(fruit.getImageId());
            viewHolder.fruitName.setText(fruit.getName());
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.loadTextView.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.loadTextView.setText("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.loadLinearLayout.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFruitList.size()+1;
    }

    @Override
    public int getItemViewType(int position){
        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void AddHeaderItem(List<Fruit> items){
        mFruitList.addAll(0,items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<Fruit> items){
        mFruitList.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     * @param status
     */
    public void changeMoreStatus(int status){
        mLoadMoreStatus=status;
        notifyDataSetChanged();
    }


}
