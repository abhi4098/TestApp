package com.payworks.ui.adapters;

/**
 * Created by Abhinandan on 21/9/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.payworks.R;
import com.payworks.generated.model.Product;
import com.payworks.ui.activities.AddProductActivity;
import com.payworks.ui.activities.MyProductActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.ArrayList;




import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;


public class MyProductAdapter extends ArrayAdapter<Product> {

    int groupid;
    ArrayList<Product> myProductList;
    Context context;
    String generatedCode ;


    public MyProductAdapter(MyProductActivity myProductActivity, int layout_my_product_list, int date, ArrayList<Product> myProductList) {
        super(myProductActivity,layout_my_product_list,date,myProductList);
        Log.e("abhi", "MyProductAdapter: " );
        groupid=layout_my_product_list;
        this.context = myProductActivity;
        this.myProductList = myProductList;

    }

    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView productName;
        public TextView productShipping;
        public TextView productSold;
        public TextView productPrice;
        public TextView productUpdateDate;
        public ImageView editProductBtn;
        public ImageView sendProductBtn;
        public ImageView generateCodeBtn;





    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        Log.e("abhi", "getView:------------------------- " );

        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.productName= (TextView) rowView.findViewById(R.id.product_name);
            viewHolder.productPrice= (TextView) rowView.findViewById(R.id.product_price);
            viewHolder.productShipping= (TextView) rowView.findViewById(R.id.product_shipping);
            viewHolder.productSold= (TextView) rowView.findViewById(R.id.product_sold);
            viewHolder.productUpdateDate= (TextView) rowView.findViewById(R.id.update_date);
            viewHolder.editProductBtn= (ImageView) rowView.findViewById(R.id.edit_product);
            viewHolder.generateCodeBtn= (ImageView) rowView.findViewById(R.id.generate_code);
            viewHolder.sendProductBtn= (ImageView) rowView.findViewById(R.id.send_product);


            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        final Product product = getItem(position);
        final ViewHolder holder = (ViewHolder) rowView.getTag();

        if (product !=null) {


            holder.productName.setText(product.getProductname());
            holder.productShipping.setText(product.getProductshipping());
            holder.productSold.setText(product.getSold());
            holder.productPrice.setText(product.getProductprice());
            holder.productUpdateDate.setText(product.getUpdatedDate());
            holder.generateCodeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    OkHttpClient client = new OkHttpClient();
                    String urlTemplate = "http://tinyurl.com/api-create.php?url=%s";
                    String uri = String.format(urlTemplate, URLEncoder.encode("https://www.payworks.bs/processor.html?product=193&member=markbrosnon@hotmail.com&action=product&send=yes"));
                    Log.e("abhi", "onClick:............url " +uri );
                    Request request = new Request.Builder()
                            .url(uri)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("abhi", "onFailure: " +e.getMessage() );
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //Log.e("abhi", "onResponse: " +response.body().string() );
                            if (response.isSuccessful()) {
                                generatedCode = response.body().string();
                               //openDialog(generatedCode, view);
                                // Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            });


            holder.editProductBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(getContext(), AddProductActivity.class);
                    i.putExtra("PRODUCT_NAME",product.getProductname());
                    i.putExtra("PRODUCT_PRICE",product.getProductprice());
                    i.putExtra("PRODUCT_SHIPPING",product.getProductshipping());
                    i.putExtra("PRODUCT_DESCRIPTION",product.getProductdescription());
                    i.putExtra("PRODUCT_BUTTON",product.getProductbutton());
                    i.putExtra("PRODUCT_ID",product.getId());
                    Log.e("abhi", "onClick: product_id" +product.getId());
                    i.putExtra("PRODUCT_FEE",product.getPaidby());
                    Log.e("abhi", "onClick: fee" +product.getPaidby());
                    i.putExtra("INTENT_FROM","EditButton");

                    getContext().startActivity(i);

                }
            });

            holder.sendProductBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
              if (generatedCode == null)
              {
                  Toast.makeText(getContext(),"Please Generate code",Toast.LENGTH_SHORT).show();
              }
              else
              {

                  Toast.makeText(getContext(),generatedCode,Toast.LENGTH_SHORT).show();
              }
                }
            });




        }



        return rowView;
    }

    private void openDialog(String generatedCode, View view) {
        //Log.e("abhi", "openDialog: "+generatedCode );

        LayoutInflater li = LayoutInflater.from((Activity) view.getContext());
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                (Activity) view.getContext());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        userInput.setText(generatedCode);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                // result.setText(userInput.getText());
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ea466b"));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ea466b"));
    }


}

