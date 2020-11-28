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
import vn.poly.goodfood.Slide.SlideItem2;
import vn.poly.goodfood.model.MonChinh;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterMonChinh extends BaseAdapter {
    Context context;
    List<MonChinh> list;
    double tongTien=0;
    public AdapterMonChinh(Context context,List<MonChinh> list){
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
        TextView txtTen,txtLuotThich,txtDonGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mon_chinh,parent, false);
            viewHolder.imgAnh = convertView.findViewById(R.id.img_monChinhAdapter);
            viewHolder.txtTen = convertView.findViewById(R.id.txt_tenMonChinh);
            viewHolder.txtDonGia = convertView.findViewById(R.id.txt_giaMonChinh);
            viewHolder.imgShare = convertView.findViewById(R.id.image_shareMonChinh);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Locale locale = new Locale("en","US");
        NumberFormat fmt =NumberFormat.getCurrencyInstance(locale);

        Picasso.with(context).load(list.get(position).getUrlAnh()).into(viewHolder.imgAnh);
        viewHolder.txtTen.setText(list.get(position).getTenAnh());
        viewHolder.txtDonGia.setText(fmt.format(list.get(position).getDonGia()/100) +" VNĐ");
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
                TextView txtTong = dialog.findViewById(R.id.txt_Dialog_TongTien);
                ElegantNumberButton soLuong = dialog.findViewById(R.id.numberButton_SoLuong);
                Button btnAdd = dialog.findViewById(R.id.btn_Dialog_ADD);

                MonChinh monChinh =list.get(position);
                Picasso.with(context).load(monChinh.getUrlAnh()).into(imgAnh);
                txtTen.setText(monChinh.getTenAnh());
                txtGia.setText("Giá: "+String.valueOf(monChinh.getDonGia())+"VNĐ");

                soLuong.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tongTien = 0;
                        tongTien = monChinh.getDonGia() * Integer.parseInt(soLuong.getNumber());
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
