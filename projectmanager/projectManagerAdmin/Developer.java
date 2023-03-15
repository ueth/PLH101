package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.util.Arrays;

public class Developer extends Employee{
    private Globals.programmingLanguages[] topRatedLanguages;

    Developer(String  id,  String name, String empOfficeNo, String empOfficePhone, Globals.programmingLanguages[] languages){
        super(id, name, empOfficeNo, empOfficePhone);
        topRatedLanguages = languages;

    }

    @Override
    public String toString() {
        return  "Developer{" +
                "topRatedLanguages=" + Arrays.toString(topRatedLanguages) +
                super.toString() +
                '}';
    }

    public Globals.programmingLanguages[] getTopRatedLanguages() {
        return topRatedLanguages;
    }

    public void setTopRatedLanguages(Globals.programmingLanguages[] topRatedLanguages) {
        this.topRatedLanguages = topRatedLanguages;
    }
}
