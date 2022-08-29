package collectiontype;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
    private String[] name;

    private List<String> list;

    private Map<String,String> map;

    private Set<String> set;

    public void setName(String[] name) {
        this.name = name;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    private List<Course> courseList;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void test(){
        System.out.println(name);
        System.out.println(list);
        System.out.println(map);
        System.out.println(set);
        System.out.println(courseList);
    }
}
