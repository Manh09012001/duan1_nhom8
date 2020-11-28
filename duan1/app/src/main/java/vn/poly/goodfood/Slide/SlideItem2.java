package vn.poly.goodfood.Slide;

public class SlideItem2 {
    private int image;
    public String ten;
    public int gia;

    public SlideItem2() {

    }

    public SlideItem2(int image, String ten, int gia) {
        this.image = image;
        this.ten = ten;
        this.gia = gia;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
