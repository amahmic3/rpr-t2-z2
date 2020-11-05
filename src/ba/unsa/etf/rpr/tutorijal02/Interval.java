package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
        private double x,y;
        private  boolean pripadaX,pripadaY;
    public Interval() {
        x=y=0;
        pripadaX=pripadaY=false;
    }
    public Interval(double _x,double _y,boolean _pripadaX,boolean _pripadaY){
        if(_x>_y) throw new IllegalArgumentException();
        x=_x;
        y=_y;
        pripadaX=_pripadaX;
        pripadaY=_pripadaY;
    }
    public boolean isNull(){
        return x==0 && y==0;
    }
    public boolean isIn(double z){
        if(pripadaX && pripadaY)  return z>=x && z<=y;
        if(pripadaX) return z>=x && z<y;
        if(pripadaY) return z>x && z<=y;
        return z>x && z<y;
    }
    public static Interval intersect(Interval prvi,Interval drugi){
        Interval povratni = new Interval();
        if(prvi.x>drugi.x){
            povratni.x= prvi.x;
            if(prvi.pripadaX) povratni.pripadaX=true;
        }else if(prvi.x== drugi.x){
            povratni.x= prvi.x;
            if(prvi.pripadaX && drugi.pripadaX) povratni.pripadaX=true;
        }else{
            povratni.x= drugi.x;
            if(drugi.pripadaX) povratni.pripadaX=true;
        }
        if(prvi.y<drugi.y){
            povratni.y= prvi.y;
            if(prvi.pripadaY) povratni.pripadaY=true;
        }else if(prvi.y== drugi.y){
            povratni.y= prvi.y;
            if(prvi.pripadaY && drugi.pripadaY) povratni.pripadaY=true;
        }else{
            povratni.y= drugi.y;
            if(drugi.pripadaY) povratni.pripadaY=true;
        }
        return povratni;
    }
    public Interval intersect(Interval drugi){
        return Interval.intersect(this,drugi);
    }
    @Override
    public String toString(){
        if(isNull()) return "()";
        StringBuilder bilderica = new StringBuilder();
        if(pripadaX){
            bilderica.append("[");
        }else bilderica.append("(");
        bilderica.append(x).append(",").append(y);
        if(pripadaY){
            bilderica.append("]");
        }else bilderica.append(")");
        return bilderica.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (Double.compare(interval.x, x) != 0) return false;
        if (Double.compare(interval.y, y) != 0) return false;
        if (pripadaX != interval.pripadaX) return false;
        return pripadaY == interval.pripadaY;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (pripadaX ? 1 : 0);
        result = 31 * result + (pripadaY ? 1 : 0);
        return result;
    }
}
