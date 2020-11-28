package vn.poly.goodfood.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import vn.poly.goodfood.R;
import vn.poly.goodfood.model.Combo;
import vn.poly.goodfood.model.MonChinh;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterCombo extends BaseAdapter {
    Context context;
    List<Combo>list;
    double tongTien=0;

    public AdapterCombo(Context context, List<Combo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolDer{
        ImageView imgAnh;
        TextView txtTen,txtGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolDer holDer;
        if (convertView==null){
            holDer = new ViewHolDer();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_kfc,parent,false);
            holDer.imgAnh =(ImageView)convertView.findViewById(R.id.img_anhKfc);
            holDer.txtTen =(TextView)convertView.findViewById(R.id.txt_tenKfc);
            holDer.txtGia =(TextView)convertView.findViewById(R.id.txt_giaKfc);
            convertView.setTag(holDer);
        }else {
            holDer =(ViewHolDer)convertView.getTag();
        }
        Locale locale = new Locale("en","US");
        NumberFormat fmt =NumberFormat.getCurrencyInstance(locale);

        Picasso.with(context).load(list.get(position).getUrlAnh()).into(holDer.imgAnh);
        holDer.txtTen.setText(list.get(position).getTenAnh());
        holDer.txtGia.setText("Giá: "+fmt.format(list.get(position).getDonGia()/100)+"VNĐ");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_them_vao_giohang);

                ImageView imgDimiss =dialog.findViewById(R.id.img_Dialog_dimiss);
                ImageView imgAnh = dialog.findViewById(R.id.img_Dialog_hienThi);
                TextView txtTen = dialog.findViewById(R.id.txt_Dialog_tenMon);
                TextView txtGia = dialog.findViewById(R.id.txt_Dialog_donGia);
                ElegantNumberButton soLuong = dialog.findViewById(R.id.numberButton_SoLuong);
                TextView txtTong = dialog.findViewById(R.id.txt_Dialog_TongTien);
                Button btnAdd = dialog.findViewById(R.id.btn_Dialog_ADD);

                Combo combo =list.get(position);
                Picasso.with(context).load(combo.getUrlAnh()).into(imgAnh);
                txtTen.setText(combo.getTenAnh());
                txtGia.setText("Giá: "+String.valueOf(combo.getDonGia())+"VNĐ");

                soLuong.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tongTien = 0;
                        tongTien = combo.getDonGia() * Integer.parseInt(soLuong.getNumber());
                        txtTong.setText(tongTien+"VNĐ");
                    }
                });

                imgDimiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return convertView;
    }
}
