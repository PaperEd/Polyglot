package compapered.httpsgithub.firebase;

/**
 * Created by dwg76 on 2017-09-01.
 */

public class Time {
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
    int hour;
    int minute;

    public int getHour(){
        return hour;
    }
    public void setHour(int hour){
        this.hour = hour;
    }
    public int getMinute(){
        return minute;
    }
    public void setMinute(int minute){
        this.minute = minute;
    }
}
