package com.example.kiwan.calculatorapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OperationsAdapter extends RecyclerView.Adapter<OperationsAdapter.OperationsHolder> {

    Cursor cursor;
    Context context;

    public OperationsAdapter( Cursor cursor, Context context ) {
        this.cursor = cursor;
        this.context = context;
    }

    // to inflate the item layout in recycler view
    @Override
    public OperationsHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        return new OperationsHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder( OperationsHolder holder, int position ) {

        if (!cursor.moveToPosition(position)) {
            return;
        }
        String operation = cursor.getString(cursor.getColumnIndex(OperationsContract.OperationEntry.COLUMN_OPERATION));
        long id = cursor.getLong(cursor.getColumnIndex(OperationsContract.OperationEntry._ID));
        holder.itemView.setTag(id);
        holder.operationText.setText(operation);
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor( Cursor newCursor ) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class OperationsHolder extends RecyclerView.ViewHolder {
        TextView operationText;

        public OperationsHolder( View itemView ) {
            super(itemView);
            operationText = itemView.findViewById(R.id.itemOperator);

        }
    }
}
