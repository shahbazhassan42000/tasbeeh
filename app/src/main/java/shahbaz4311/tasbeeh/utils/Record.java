package shahbaz4311.tasbeeh.utils;

public class Record {
    int id;
    String name;
    boolean recited;
    int count;

    String date;

    public Record(int id, String name, boolean recited, int count, String date) {
        this.id = id;
        this.name = name;
        this.recited = recited;
        this.count = count;
        this.date = date;
    }

    public Record(String name, boolean recited, int count, String date) {
        this.id = -1;
        this.name = name;
        this.recited = recited;
        this.count = count;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRecited() {
        return recited;
    }

    public void setRecited(boolean recited) {
        this.recited = recited;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRecited(){
        if(recited){
            return 1;
        }
        return 0;
    }
}