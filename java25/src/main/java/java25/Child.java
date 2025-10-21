package java25;

import java.util.Objects;

public class Child extends Parent {
    private String name;

    public Child(String name) {
        System.out.println("In Child, name = " + name);
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
        Child child = (Child) o;
        return Objects.equals(getName(), child.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                '}';
    }
}
