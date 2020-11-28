package vn.poly.goodfood.Adapter;

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
import vn.poly.goodfood.model.MonChinh;
import vn.poly.goodfood.model.MonPhu;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterMonPhu extends BaseAdapter {
    Context context;
    List<MonPhu>list;
    double tongTien=0;

    public AdapterMonPhu(Context context, List<MonPhu> list) {
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

    public class ViewHolder{
        ImageView imgAnh,imgShare;
        TextView txtTen,txtDonGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mon_chinh,parent, false);
            holder.imgAnh = convertView.findViewById(R.id.img_monChinhAdapter);
            holder.txtTen = convertView.findViewById(R.id.txt_tenMonChinh);
            holder.txtDonGia = convertView.findViewById(R.id.txt_giaMonChinh);
            holder.imgShare = convertView.findViewById(R.id.image_shareMonChinh);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Locale locale = new Locale("en","US");
        NumberFormat fmt =NumberFormat.getCurrencyInstance(locale);

        Picasso.with(context).load(list.get(position).getLinkAnh()).into(holder.imgAnh);
        holder.txtTen.setText(list.get(position).getNameAnh());
        holder.txtDonGia.setText(fmt.format(list.get(position).getDonGia()/100) +" VNĐ");
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

                MonPhu monPhu =list.get(position);
                Picasso.with(context).load(monPhu.getLinkAnh()).into(imgAnh);
                txtTen.setText(monPhu.getNameAnh());
                txtGia.setText("Giá: "+String.valueOf(monPhu.getDonGia())+"VNĐ");

                soLuong.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tongTien = 0;
                        tongTien = monPhu.getDonGia() * Integer.parseInt(soLuong.getNumber());
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
