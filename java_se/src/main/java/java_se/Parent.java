package java_se;

import java.util.Objects;

public class Parent {
    private String name;

    public Parent() {
        System.out.println("In Parent's no argument constructor");
    }

    public Parent(String name) {
        System.out.println("In Parent, name = " + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return Objects.equals(getName(), parent.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                '}';
    }
}
