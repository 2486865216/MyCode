package bean;
//部门类
public class Dept {
    private String Dname;

    public void setDname(String dname) {
        Dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "Dname='" + Dname + '\'' +
                '}';
    }
}
