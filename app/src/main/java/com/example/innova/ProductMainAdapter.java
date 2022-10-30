package com.example.innova;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductMainAdapter extends FirebaseRecyclerAdapter<ProductMainModel,ProductMainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductMainAdapter(@NonNull FirebaseRecyclerOptions<ProductMainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ProductMainModel model) {

        holder.productname.setText(model.getProductname());
        holder.spec.setText("Spec. "+model.getSpec());
        holder.price.setText("Rs. "+model.getPrice());

        Glide.with(holder.img.getContext())
                .load(model.getPurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.product_update_popup))
                        .setExpanded(true,1800)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();
                EditText productname = view.findViewById(R.id.productname);
                EditText spec = view.findViewById(R.id.spec1);
                EditText price = view.findViewById(R.id.price1);
                EditText siteurl = view.findViewById(R.id.Siteurl);
                EditText purl = view.findViewById(R.id.img2url);
                Button btnUpdate = view.findViewById(R.id.btnUpdateprod);

                productname.setText(model.getProductname());
                spec.setText(model.getSpec());
                price.setText(model.getPrice());
                siteurl.setText(model.getsiturl());
                purl.setText(model.getPurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("productname",productname.getText().toString());
                        map.put("spec",spec.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("purl",purl.getText().toString());
                        map.put("siturl",siteurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("products")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.productname.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.productname.getContext(), "Error While Updatng.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.productname.getContext());
                builder.setTitle("Are you sure you want to delete");
                builder.setMessage("Deleted data can't be Undo.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("products")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.productname.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView productname,spec, price;
        Button btnEdit, btnDelete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.img2);
            productname = (TextView)itemView.findViewById(R.id.nametext);
            spec = (TextView)itemView.findViewById(R.id.spec);
            price = (TextView)itemView.findViewById(R.id.price);
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit1);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete1);
        }
    }
}
