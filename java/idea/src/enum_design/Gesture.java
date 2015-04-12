package enum_design;

/**
 * Created by zzt on 2/10/15.
 */
public enum Gesture {
    Paper, Strone, Scissors(){
        @Override
        public boolean win(Gesture gesture) {
            if(gesture == Paper){
                return true;
            }
            return false;
        }
    };

    public boolean win(Gesture gesture){
        return gesture.ordinal() < this.ordinal();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
